package com.ebmwebsourcing.wsstar.wsnb.services.impl.util;

import java.net.URI;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.w3c.dom.Document;

import com.ebmwebsourcing.easycommons.research.util.SOAException;
import com.ebmwebsourcing.easycommons.research.util.easybox.DefaultFramework;
import com.ebmwebsourcing.easycommons.research.util.easybox.SOAUtil;
import com.ebmwebsourcing.easycommons.research.util.jaxb.SOAJAXBContext;
import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.element.ReferenceParameters;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType.Message;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl.WsnbModelFactoryImpl;
import com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify;
import com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe;
import com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType;

public class WSNHelper {


	public static com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe createSubscription(String consumerAddress, QName topicUsed)
			throws WsnbException, SOAException {
		EndpointReferenceType epr = SOAUtil.getInstance().getXmlContext(DefaultFramework.getInstance()).getXmlObjectFactory().create(EndpointReferenceType.class);
		Address value =  SOAUtil.getInstance().getXmlContext(DefaultFramework.getInstance()).getXmlObjectFactory().create(Address.class);
		value.setValue(URI.create(consumerAddress));
		epr.setAddress(value);
		com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe payload = RefinedWsnbFactory.getInstance(new WsnbModelFactoryImpl()).createSubscribe(epr); 

		com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType notifyTopicExpr = RefinedWsnbFactory.getInstance().createTopicExpressionType(URI.create("http://www.w3.org/TR/1999/REC-xpath-19991116"));

		notifyTopicExpr.addTopicNamespace(topicUsed.getPrefix(),
				URI.create(topicUsed.getNamespaceURI()));
		notifyTopicExpr.setContent(topicUsed.getPrefix() + ":" + topicUsed.getLocalPart());

		com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType filter = RefinedWsnbFactory.getInstance().createFilterType();
		filter.addTopicExpression(notifyTopicExpr);
		payload.setFilter(filter);

		Document doc = Wsnb4ServUtils.getWsnbWriter().writeSubscribeAsDOM(payload);
		com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe subscribe = SOAJAXBContext.getInstance().marshallAnyType(doc, com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe.class);

		return payload;
	}

	public static Subscribe marshallSubscribe(Document doc) throws WsnbException {
		com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe subscribe = Wsnb4ServUtils.getWsnbReader().readSubscribe(doc);
		return convert2JaxbElement(subscribe);
	}

	public static Subscribe convert2JaxbElement(com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe subscribe) throws WsnbException {
		Subscribe res = null;
		Document doc = Wsnb4ServUtils.getWsnbWriter().writeSubscribeAsDOM(subscribe);

		try {
			res = SOAJAXBContext.getInstance().marshallAnyType(doc, Subscribe.class);
		} catch (SOAException e) {
			throw new WsnbException(e);
		}

		System.out.println(subscribe.getFilter().getTopicExpressions().get(0).getTopicNamespaces());
		System.out.println(((TopicExpressionType)((JAXBElement)res.getFilter().getAny().get(0)).getValue()).getOtherAttributes());

		((TopicExpressionType)((JAXBElement)res.getFilter().getAny().get(0)).getValue()).getOtherAttributes().put(new QName("http://www.w3.org/XML/1998/namespace", subscribe.getFilter().getTopicExpressions().get(0).getTopicNamespaces().get(0).getLocalPart()), subscribe.getFilter().getTopicExpressions().get(0).getTopicNamespaces().get(0).getNamespaceURI());

		System.out.println("apres: " + ((TopicExpressionType)((JAXBElement)res.getFilter().getAny().get(0)).getValue()).getOtherAttributes());


		return res;
	}


	public static com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify createNotification(String consumerAddress, String producerAddress, QName topicUsed, Object payload)
			throws WsnbException, SOAException {
		com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify notifyPayload = null;

		Message mess = null;
		if(payload != null) {
			if(payload instanceof Document) {
				mess = RefinedWsnbFactory.getInstance()
						.createNotificationMessageHolderTypeMessage(((Document)payload).getDocumentElement());
			} else {
				mess = RefinedWsnbFactory.getInstance()
						.createNotificationMessageHolderTypeMessage(SOAJAXBContext.getInstance().unmarshallAnyElement(payload).getDocumentElement());
			}
		} else {
			mess = RefinedWsnbFactory.getInstance()
					.createNotificationMessageHolderTypeMessage(null);
		}
		NotificationMessageHolderType msg = RefinedWsnbFactory
				.getInstance().createNotificationMessageHolderType(mess);
		notifyPayload = RefinedWsnbFactory.getInstance().createNotify(msg);

		if(topicUsed != null) {
			final com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType notifyTopicExpr = createTopicExpression(topicUsed, "http://docs.oasis-open.org/wsn/t-1/TopicExpression/Concrete");
			msg.setTopic(notifyTopicExpr);
		}

		if(consumerAddress != null) {
			final EndpointReferenceType registrationRef = SOAUtil.getInstance().getXmlContext(DefaultFramework.getInstance()).getXmlObjectFactory().create(EndpointReferenceType.class);
			Address address = SOAUtil.getInstance().getXmlContext(DefaultFramework.getInstance()).getXmlObjectFactory().create(Address.class);
			address.setValue(URI.create(consumerAddress));
			registrationRef.setAddress(address);

			final ReferenceParameters ref = SOAUtil.getInstance().getXmlContext(DefaultFramework.getInstance()).getXmlObjectFactory().create(ReferenceParameters.class);
			registrationRef.setReferenceParameters(ref);

			msg.setSubscriptionReference(registrationRef);
		}
		final EndpointReferenceType producerRef = SOAUtil.getInstance().getXmlContext(DefaultFramework.getInstance()).getXmlObjectFactory().create(EndpointReferenceType.class);
		Address address = SOAUtil.getInstance().getXmlContext(DefaultFramework.getInstance()).getXmlObjectFactory().create(Address.class);
		address.setValue(URI.create(producerAddress));
		producerRef.setAddress(address);
		msg.setProducerReference(producerRef);

		return notifyPayload;
	}

	public static com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType createTopicExpression(QName topicUsed, String dialect) throws WsnbException {
		com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType notifyTopicExpr =  null;

		notifyTopicExpr = RefinedWsnbFactory.getInstance().createTopicExpressionType(URI.create(dialect));

		if(topicUsed.getPrefix() == null) {
			throw new WsnbException("prefix of topicUsed cannot be null");
		}

		notifyTopicExpr.addTopicNamespace(topicUsed.getPrefix(),
				URI.create(topicUsed.getNamespaceURI()));
		notifyTopicExpr.setContent(topicUsed.getPrefix() + ":" + topicUsed.getLocalPart());

		return notifyTopicExpr;
	}


	public static Notify marshallNotify(Document doc) throws WsnbException {
		com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify notify = Wsnb4ServUtils.getWsnbReader().readNotify(doc);
		return convert2JaxbElement(notify);
	}

	public static Notify convert2JaxbElement(com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify notify) throws WsnbException {
		Notify res = null;
		Document doc = Wsnb4ServUtils.getWsnbWriter().writeNotifyAsDOM(notify);

		try {
			res = SOAJAXBContext.getInstance().marshallAnyType(doc, Notify.class);
		} catch (SOAException e) {
			throw new WsnbException(e);
		}

		System.out.println(notify.getNotificationMessage().get(0).getTopic().getTopicNamespaces());
		System.out.println(res.getNotificationMessage().get(0).getTopic().getOtherAttributes());

		res.getNotificationMessage().get(0).getTopic().getOtherAttributes().put(new QName("http://www.w3.org/XML/1998/namespace", notify.getNotificationMessage().get(0).getTopic().getTopicNamespaces().get(0).getLocalPart()), notify.getNotificationMessage().get(0).getTopic().getTopicNamespaces().get(0).getNamespaceURI());

		System.out.println("apres: " + res.getNotificationMessage().get(0).getTopic().getOtherAttributes());


		return res;
	}


}
