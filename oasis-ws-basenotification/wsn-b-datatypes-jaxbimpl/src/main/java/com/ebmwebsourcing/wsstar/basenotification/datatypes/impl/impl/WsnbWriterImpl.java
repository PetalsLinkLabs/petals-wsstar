package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPoint;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPoint;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessages;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessagesResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidFilterFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidMessageContentExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidProducerPropertiesExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidTopicExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MessageContentExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MultipleTopicsSpecifiedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NoCurrentMessageOnTopicFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationProducerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotifyMessageNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ProducerPropertiesExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Renew;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.RenewResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeCreationFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionManagerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionDialectUnknownFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToCreatePullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroyPullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroySubscriptionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToGetMessagesFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableInitialTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnrecognizedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Unsubscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsupportedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UseRaw;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbWriter;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.utils.WsstarCommonUtils;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.utils.WstopUtils;

public class WsnbWriterImpl implements WsnbWriter {

	//	private Logger logger = Logger.getLogger(WsnbWriterImpl.class.getName());
	private WsnbJAXBContext wsnbJaxbContext = null;

	/**
	 * Default constructor
	 */
	protected  WsnbWriterImpl(){
		this.wsnbJaxbContext = WsnbJAXBContext.getInstance();
	} 

	protected  WsnbWriterImpl(String[] nsAndPrefixForMarshalling){
		this.wsnbJaxbContext = WsnbJAXBContext.getInstance(nsAndPrefixForMarshalling);	
	}

	@Override
	public final Document writeCreatePullPointAsDOMAsDOM(CreatePullPoint value)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeCreatePullPointResponseAsDOM(
			CreatePullPointResponse value) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeDestroyPullPointAsDOM(DestroyPullPoint value)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeDestroyPullPointResponseAsDOM(
			DestroyPullPointResponse value) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeFilterTypeAsDOM(FilterType value) throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			// get from corresponding TopicExpression model object, topicNS to add to Document result object.
			List<List<QName>> topicNSToAdd = new ArrayList<List<QName>>();

			List<TopicExpressionType> topExprList = value.getTopicExpressions(); 
			for (TopicExpressionType topExprItem : topExprList) {
				topicNSToAdd.add(topExprItem.getTopicNamespaces());
				// remove (temporarily) namespace from java object  
				TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)topExprItem);
			}									

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType> element = 
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createFilter(FilterTypeImpl.toJaxbModel(value));

			marshaller.marshal(element, result);

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			NodeList topicExpressionNodes = result.getDocumentElement().getElementsByTagNameNS(
					WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
					WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());
			Element currentTopicExpressionAsElt = null;
			List<QName> currentTopicNSsToAdd = null;

			for (int i = 0; i < topicExpressionNodes.getLength(); i++) {
				currentTopicExpressionAsElt = (Element) topicExpressionNodes.item(i);                
				currentTopicNSsToAdd = topicNSToAdd.get(i);
				for (QName nsItem : currentTopicNSsToAdd) {
					currentTopicExpressionAsElt.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
							XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
							nsItem.getNamespaceURI());
				}
			} 

			// --- restore namespaces, previously remove, on java object
			for (TopicExpressionType topExpItem : topExprList) {
				for (QName qnItem : topicNSToAdd.get(topExprList.indexOf(topExpItem))) {    		
					topExpItem.addTopicNamespace(qnItem.getLocalPart(), new URI(qnItem.getNamespaceURI()));
				} 
			}

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}        	          
		return result;
	}

	@Override
	public final Document writeGetCurrentMessageAsDOM(GetCurrentMessage value)
			throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			// get from corresponding TopicExpression model object, topicNS to add to Document result object.
			List<QName>topicNSToAdd = new ArrayList<QName>();

			TopicExpressionType topic= value.getTopic(); 
			topicNSToAdd.addAll(topic.getTopicNamespaces());
			// remove (temporarily) namespace from java object  
			TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)topic);


			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage> element = 
					new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage>(WsnbConstants.GET_CURRENT_MESSAGE_QNAME,
							com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage.class,((GetCurrentMessageImpl)value).getJaxbTypeObj());

			marshaller.marshal(element, result);

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			NodeList topicNode = result.getDocumentElement().getElementsByTagNameNS(
					WsnbConstants.TOPIC_QNAME.getNamespaceURI(),
					WsnbConstants.TOPIC_QNAME.getLocalPart());

			if (topicNode != null && topicNode.getLength() == 1){
				Element topicAsElt = (Element) topicNode.item(0);
				for (QName nsItem : topicNSToAdd) {
					topicAsElt.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
							XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
							nsItem.getNamespaceURI());
				}
			}

			// --- restore namespaces, previously remove, on java object            
			for (QName qnItem : topicNSToAdd) {    		
				topic.addTopicNamespace(qnItem.getLocalPart(), new URI(qnItem.getNamespaceURI()));
			} 

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}        	          
		return result;
	}

	@Override
	public final Document writeGetCurrentMessageResponseAsDOM(
			GetCurrentMessageResponse value) throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();


			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse> element = 
					new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse>(WsnbConstants.GET_CURRENT_MESSAGE_RESPONSE_QNAME,
							com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse.class,((GetCurrentMessageResponseImpl)value).getJaxbTypeObj());

			marshaller.marshal(element, result);			

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}

		return result;
	}

	@Override
	public final Document writeGetMessagesAsDOM(GetMessages value)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeGetMessagesResponseAsDOM(GetMessagesResponse value)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeInvalidFilterFaultTypeAsDOM(
			InvalidFilterFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof InvalidFilterFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType> element =
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidFilterFault(InvalidFilterFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeInvalidMessageContentExpressionFaultTypeAsDOM(
			InvalidMessageContentExpressionFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof InvalidMessageContentExpressionFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidMessageContentExpressionFault(
								InvalidMessageContentExpressionFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeInvalidProducerPropertiesExpressionFaultTypeAsDOM(
			InvalidProducerPropertiesExpressionFaultType value)
					throws WsnbException {
		Document result = null;
		if (value instanceof InvalidProducerPropertiesExpressionFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidProducerPropertiesExpressionFault(
								InvalidProducerPropertiesExpressionFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeInvalidTopicExpressionFaultTypeAsDOM(
			InvalidTopicExpressionFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof InvalidTopicExpressionFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidTopicExpressionFault(InvalidTopicExpressionFaultTypeImpl.toJaxbModel(value));
				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeMessageContentExpressionAsDOM(MessageContentExpression value)
			throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType> element =
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createMessageContent(MessageContentExpressionImpl.toJaxbModel(value));

			marshaller.marshal(element, result);

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} 

		return result;
	}

	@Override
	public final Document writeMultipleTopicsSpecifiedFaultTypeAsDOM(
			MultipleTopicsSpecifiedFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof MultipleTopicsSpecifiedFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createMultipleTopicsSpecifiedFault(MultipleTopicsSpecifiedFaultTypeImpl.toJaxbModel(value));
				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeNoCurrentMessageOnTopicFaultTypeAsDOM(
			NoCurrentMessageOnTopicFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof NoCurrentMessageOnTopicFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createNoCurrentMessageOnTopicFault(NoCurrentMessageOnTopicFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeNotificationMessageHolderTypeAsDOM(
			NotificationMessageHolderType value) throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			// get from corresponding TopicExpression model object, topicNS to add to Document result object.
			TopicExpressionType concrTopExp = value.getTopic(); 
			List<QName> topicNSToAdd = null;
			if (concrTopExp != null){
				topicNSToAdd = concrTopExp.getTopicNamespaces();

				// remove (temporarily) namespace from java object  
				TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)concrTopExp);
			}			

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType> element = 
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotificationMessage(NotificationMessageHolderTypeImpl.toJaxbModel(value));

			marshaller.marshal(element, result);

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			if (topicNSToAdd != null) {
				NodeList topicNodes = result.getDocumentElement().getElementsByTagNameNS(
						WsnbConstants.TOPIC_QNAME.getNamespaceURI(),
						WsnbConstants.TOPIC_QNAME.getLocalPart());

				Element topicExpressionAsElt = (topicNodes.getLength() == 1)? (Element)topicNodes.item(0) :null;
				if (topicExpressionAsElt != null){
					for (QName nsItem : topicNSToAdd) {
						topicExpressionAsElt.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
								XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
								nsItem.getNamespaceURI());
					}
				}
				// --- restore namespaces, previously remove, on java object
				for (QName qnItem : topicNSToAdd) {    		
					concrTopExp.addTopicNamespace(qnItem.getLocalPart(), new URI(qnItem.getNamespaceURI()));
				} 
			}
		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}        	          
		return result;
	}

	@Override
	public final Document writeNotificationProducerRPAsDOM(
			NotificationProducerRP value) throws WsnbException {
		Document result = null;
		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			// get from corresponding TopicExpression model object, topicNS to add to Document result object.
			Map<Integer, List<QName>> topicNSToAdd = new HashMap<Integer,List<QName>>();

			List<TopicExpressionType> topicExpressions = value.getTopicExpressions(); 
			for (TopicExpressionType topicExprItem : topicExpressions) {
				topicNSToAdd.put(Integer.valueOf(topicExpressions.indexOf(topicExprItem)),topicExprItem.getTopicNamespaces());
				// remove (temporarily) namespace from java object  
				TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)topicExprItem);

			}			

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP> element = 
					new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP>(WsnbConstants.NOTIFICATION_PRODUCER_RP_QNAME,
							com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP.class, ((NotificationProducerRPImpl)value).getJaxbTypeObj());

			marshaller.marshal(element, result);    			

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			NodeList topicExpressionNodes = result.getDocumentElement().getElementsByTagNameNS(
					WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
					WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());

			Element currentTopicExprNode = null;            
			for (int i = 0; i < topicExpressionNodes.getLength(); i++) {
				currentTopicExprNode = (Element) topicExpressionNodes.item(0);            		
				List<QName> currentTopicsToAdd = topicNSToAdd.get(i);
				for (QName nsItem : currentTopicsToAdd) {
					currentTopicExprNode.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
							XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
							nsItem.getNamespaceURI());
				}
			}

			// --- restore namespaces, previously remove, on java object
			List<QName> currentQNames = null;
			for (TopicExpressionType topicExpressionItem : topicExpressions) {
				currentQNames = topicNSToAdd.get(topicExpressions.indexOf(topicExpressionItem));
				if (currentQNames != null){
					for (QName qnItem : currentQNames) {    		
						topicExpressionItem.addTopicNamespace(qnItem.getLocalPart(),
								new URI (qnItem.getNamespaceURI()));
					} 
				}
			}

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}             

		return result;
	}

	@Override
	public final Document writeNotifyMessageNotSupportedFaultTypeAsDOM(
			NotifyMessageNotSupportedFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof NotifyMessageNotSupportedFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotifyMessageNotSupportedFault(
								NotifyMessageNotSupportedFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writePauseFailedFaultTypeAsDOM(PauseFailedFaultType value)
			throws WsnbException {
		Document result = null;
		if (value instanceof PauseFailedFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createPauseFailedFault(PauseFailedFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writePauseSubscriptionAsDOM(PauseSubscription value)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writePauseSubscriptionResponseAsDOM(
			PauseSubscriptionResponse value) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeProducerPropertiesExpressionAsDOM(ProducerPropertiesExpression value)
			throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType> element =
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createProducerProperties(ProducerPropertiesExpressionImpl.toJaxbModel(value));

			marshaller.marshal(element, result);

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} 

		return result;
	}

	@Override
	public final Document writeRenewAsDOM(Renew value) throws WsnbException {
		Document result = null;
		if (value instanceof RenewImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew> element = 
						new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew>(WsnbConstants.RENEW_QNAME,
								com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew.class,((RenewImpl)value).getJaxbTypeObj());

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	

	}

	@Override
	public final Document writeRenewResponseAsDOM(RenewResponse value)
			throws WsnbException {
		Document result = null;
		if (value instanceof RenewResponseImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse> element = 
						new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse>(WsnbConstants.RENEW_RESPONSE_QNAME,
								com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse.class,((RenewResponseImpl)value).getJaxbTypeObj());

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeResumeFailedFaultTypeAsDOM(ResumeFailedFaultType value)
			throws WsnbException {
		Document result = null;
		if (value instanceof ResumeFailedFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createResumeFailedFault(ResumeFailedFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeResumeSubscriptionAsDOM(ResumeSubscription value)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeResumeSubscriptionResponseAsDOM(
			ResumeSubscriptionResponse value) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Document writeNotifyAsDOM(Notify value) throws WsnbException {
		Document result = null;
		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			// get from corresponding TopicExpression model object, topicNS to add to Document result object.
			Map<Integer, List<QName>> topicNSToAdd = new HashMap<Integer,List<QName>>();

			List<NotificationMessageHolderType> notifMessages = value.getNotificationMessage(); 
			TopicExpressionType currentConreteTopExpr = null;
			for (NotificationMessageHolderType notifMsgItem : notifMessages) {
				currentConreteTopExpr = notifMsgItem.getTopic();
				if (currentConreteTopExpr != null) { 
					topicNSToAdd.put(Integer.valueOf(notifMessages.indexOf(notifMsgItem)),currentConreteTopExpr.getTopicNamespaces());
					// remove (temporarily) namespace from java object  
					TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)currentConreteTopExpr);
				}
			}			

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify> element = 
					new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify>(WsnbConstants.NOTIFY_QNAME,
							com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify.class, ((NotifyImpl)value).getJaxbTypeObj());

			marshaller.marshal(element, result);    			

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			NodeList notifMsgNodes = result.getDocumentElement().getElementsByTagNameNS(
					WsnbConstants.NOTIFICATION_MSG_QNAME.getNamespaceURI(),
					WsnbConstants.NOTIFICATION_MSG_QNAME.getLocalPart());
			Element currentTopicNode = null;

			NodeList topicNodes = null;
			for (int i = 0; i < notifMsgNodes.getLength(); i++) {
				topicNodes = ((Element) notifMsgNodes.item(i)).getElementsByTagNameNS(
						WsnbConstants.TOPIC_QNAME.getNamespaceURI(),
						WsnbConstants.TOPIC_QNAME.getLocalPart());
				if (topicNodes.getLength() > 0) {
					currentTopicNode = (Element) topicNodes.item(0);

					List<QName> currentTopicsToAdd = topicNSToAdd.get(i);
					if(currentTopicsToAdd != null) {
						for (QName nsItem : currentTopicsToAdd) {
							if(nsItem.getNamespaceURI() != null && nsItem.getNamespaceURI().trim().length() > 0) {
								currentTopicNode.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
										XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
										nsItem.getNamespaceURI());
							}
						}
					}
				}
			}

			// --- restore namespaces, previously remove, on java object
			List<QName> currentQNames = null;
			for (NotificationMessageHolderType notifMsgItem : notifMessages) {
				currentQNames = topicNSToAdd.get(notifMessages.indexOf(notifMsgItem));
				if (currentQNames != null){
					for (QName qnItem : currentQNames) {    		
						((TopicExpressionTypeImpl)notifMsgItem.getTopic()).addTopicNamespace(qnItem.getLocalPart(),
								new URI (qnItem.getNamespaceURI()));
					} 
				}
			}

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}             

		return result;
	}

	@Override
	public final Document writeSubscribeAsDOM(Subscribe value) throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			// get from corresponding TopicExpression model object, topicNS to add to Document result object.
			FilterType filter = value.getFilter();
			List<List<QName>> topicNSToAdd = null;
			List<TopicExpressionType> topExprList = null;
			if (filter != null){
				topicNSToAdd = new ArrayList<List<QName>>();
				topExprList = filter.getTopicExpressions(); 
				for (TopicExpressionType topExprItem : topExprList) {
					topicNSToAdd.add(topExprItem.getTopicNamespaces());
					// remove (temporarily) namespace from java object  
					TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)topExprItem);
				}									
			}
			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe> element = 
					new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe>(WsnbConstants.SUBSCRIBE_QNAME,
							com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe.class, SubscribeImpl.toJaxbModel(value));     
			marshaller.marshal(element, result);

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			if (filter != null) {
				NodeList filterNode = result.getDocumentElement().getElementsByTagNameNS(
						WsnbConstants.FILTER_QNAME.getNamespaceURI(),
						WsnbConstants.FILTER_QNAME.getLocalPart());

				if (filterNode.getLength() == 1){
					NodeList topicExpressionNodes = ((Element)filterNode.item(0)).getElementsByTagNameNS(
							WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
							WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());
					Element currentTopicExpressionAsElt = null;
					List<QName> currentTopicNSsToAdd = null;

					for (int i = 0; i < topicExpressionNodes.getLength(); i++) {
						currentTopicExpressionAsElt = (Element) topicExpressionNodes.item(i);                
						currentTopicNSsToAdd = topicNSToAdd.get(i);
						for (QName nsItem : currentTopicNSsToAdd) {
							currentTopicExpressionAsElt.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
									XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
									nsItem.getNamespaceURI());
						}
					} 
				}

				// --- restore namespaces, previously remove, on java object
				for (TopicExpressionType topExpItem : topExprList) {
					for (QName qnItem : topicNSToAdd.get(topExprList.indexOf(topExpItem))) {    		
						topExpItem.addTopicNamespace(qnItem.getLocalPart(), new URI(qnItem.getNamespaceURI()));
					} 
				}
			}

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}        	          
		return result;
	}

	@Override
	public final Document writeSubscribeCreationFailedFaultTypeAsDOM(
			SubscribeCreationFailedFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof SubscribeCreationFailedFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscribeCreationFailedFault(SubscribeCreationFailedFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeSubscribeResponseAsDOM(SubscribeResponse value)
			throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse> element =
					new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse>(WsnbConstants.SUBSCRIBE_RESPONSE_QNAME,
							com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse.class, SubscribeResponseImpl.toJaxbModel(value));     
			marshaller.marshal(element, result);						

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}      	          
		return result;
	}

	@Override
	public final Document writeSubscriptionManagerRPAsDOM(SubscriptionManagerRP value)
			throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			// get from corresponding TopicExpression model object, topicNS to add to Document result object.
			FilterType filter = value.getFilter();
			List<List<QName>> topicNSToAdd = null;
			List<TopicExpressionType> topExprList = null;
			if (filter != null){
				topicNSToAdd = new ArrayList<List<QName>>();
				topExprList = filter.getTopicExpressions(); 
				for (TopicExpressionType topExprItem : topExprList) {
					topicNSToAdd.add(topExprItem.getTopicNamespaces());
					// remove (temporarily) namespace from java object  
					TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)topExprItem);
				}									
			}
			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP> element = 
					new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP>(WsnbConstants.SUBSCRIPTION_MGR_RP_QNAME,
							com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP.class, SubscriptionManagerRPImpl.toJaxbModel(value));     
			marshaller.marshal(element, result);

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			if (filter != null) {
				NodeList filterNode = result.getDocumentElement().getElementsByTagNameNS(
						WsnbConstants.FILTER_QNAME.getNamespaceURI(),
						WsnbConstants.FILTER_QNAME.getLocalPart());

				if (filterNode.getLength() == 1){
					NodeList topicExpressionNodes = ((Element)filterNode.item(0)).getElementsByTagNameNS(
							WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
							WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());
					Element currentTopicExpressionAsElt = null;
					List<QName> currentTopicNSsToAdd = null;

					for (int i = 0; i < topicExpressionNodes.getLength(); i++) {
						currentTopicExpressionAsElt = (Element) topicExpressionNodes.item(i);                
						currentTopicNSsToAdd = topicNSToAdd.get(i);
						for (QName nsItem : currentTopicNSsToAdd) {
							currentTopicExpressionAsElt.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
									XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
									nsItem.getNamespaceURI());
						}
					} 
				}

				// --- restore namespaces, previously remove, on java object
				for (TopicExpressionType topExpItem : topExprList) {
					for (QName qnItem : topicNSToAdd.get(topExprList.indexOf(topExpItem))) {    		
						topExpItem.addTopicNamespace(qnItem.getLocalPart(), new URI(qnItem.getNamespaceURI()));
					} 
				}
			}

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}        	          
		return result;
	}

	@Override
	public final void writeSubscriptionManagerRPToFilesystem(
			SubscriptionManagerRP subscription, String path) throws WsnbException {

		Document subscriptionAsDoc = this.writeSubscriptionManagerRPAsDOM(subscription);
		try{
			// Prepare the DOM document for writing		    
			Source source = new DOMSource(subscriptionAsDoc);

			// Prepare the output file		
			File persist = new File(path);
			if (!persist.exists()) {
				try {
					persist.createNewFile();
				} catch (IOException e) {
					throw new WsnbException("The persistance file can not be created respect to given path : \"" + path + "\"",e);
				}
			}        

			// Prepare the stream
			Result result = new StreamResult(persist);

			// Write the DOM document to the file
			Transformer xformer = WsstarCommonUtils.getDefaultTransformerFactoryThreadLocal();
			xformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			throw new WsnbException(WstopUtils.PERSISTENCE_EXCEPETION_MSG,e);
		} catch (TransformerFactoryConfigurationError e) {
			throw new WsnbException(WstopUtils.PERSISTENCE_EXCEPETION_MSG,e);
		} catch (TransformerException e) {			
			throw new WsnbException(WstopUtils.PERSISTENCE_EXCEPETION_MSG,e);
		}
	}

	@Override
	public final Document writeSubscriptionPolicyTypeAsDOM(
			SubscriptionPolicyType value) throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType> element = 
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscriptionPolicy(SubscriptionPolicyTypeImpl.toJaxbModel(value));

			marshaller.marshal(element, result);

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}         	          
		return result;
	}

	@Override
	public final Document writeTopicExpressionTypeAsDOM(TopicExpressionType value)
			throws WsnbException {
		Document result = null;    

		try {

			Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

			List<QName> additianalTopicNS = value.getTopicNamespaces();
			// remove (temporarily) namespace from java object  
			TopicExpressionTypeImpl.removeTopicNamspacesFromJaxbModel((TopicExpressionTypeImpl)value);

			result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument(); 

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType> element = 
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicExpression(TopicExpressionTypeImpl.toJaxbModel(value));

			marshaller.marshal(element, result);

			// Trick : Add saved topic additional namespaces to the generated
			// Document
			Element topicExpressionAsElt = result.getDocumentElement();
			for (QName nsItem : additianalTopicNS) {
				topicExpressionAsElt.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
						XMLConstants.XMLNS_ATTRIBUTE + ":" + nsItem.getLocalPart(),
						nsItem.getNamespaceURI());
			}

			// --- restore namespace on java object
			for (QName qnItem : additianalTopicNS) {			
				((TopicExpressionTypeImpl)value).addTopicNamespace(qnItem.getLocalPart(), new URI(qnItem.getNamespaceURI()));
			}	

		} catch (final JAXBException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		} catch (URISyntaxException ex) {
			throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
		}          	          
		return result;
	}

	@Override
	public final Document writeTopicExpressionDialectUnknownFaultTypeAsDOM(
			TopicExpressionDialectUnknownFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof TopicExpressionDialectUnknownFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicExpressionDialectUnknownFault(
								TopicExpressionDialectUnknownFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeTopicNotSupportedFaultTypeAsDOM(
			TopicNotSupportedFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof TopicNotSupportedFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicNotSupportedFault(TopicNotSupportedFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnableToCreatePullPointFaultTypeAsDOM(
			UnableToCreatePullPointFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof UnableToCreatePullPointFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToCreatePullPointFault(
								UnableToCreatePullPointFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnableToDestroyPullPointFaultTypeAsDOM(
			UnableToDestroyPullPointFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof UnableToDestroyPullPointFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToDestroyPullPointFault(UnableToDestroyPullPointFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnableToDestroySubscriptionFaultTypeAsDOM(
			UnableToDestroySubscriptionFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof UnableToDestroySubscriptionFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToDestroySubscriptionFault(
								UnableToDestroySubscriptionFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnableToGetMessagesFaultTypeAsDOM(
			UnableToGetMessagesFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof UnableToGetMessagesFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToGetMessagesFault(UnableToGetMessagesFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnacceptableInitialTerminationTimeFaultTypeAsDOM(
			UnacceptableInitialTerminationTimeFaultType value)
					throws WsnbException {
		Document result = null;
		if (value instanceof UnacceptableInitialTerminationTimeFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnacceptableInitialTerminationTimeFault(
								UnacceptableInitialTerminationTimeFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnacceptableTerminationTimeFaultTypeAsDOM(
			UnacceptableTerminationTimeFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof UnacceptableTerminationTimeFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnacceptableTerminationTimeFault(UnacceptableTerminationTimeFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnrecognizedPolicyRequestFaultTypeAsDOM(
			UnrecognizedPolicyRequestFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof UnrecognizedPolicyRequestFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnrecognizedPolicyRequestFault(UnrecognizedPolicyRequestFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnsubscribeAsDOM(Unsubscribe value)
			throws WsnbException {
		Document result = null;
		if (value instanceof UnsubscribeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe> element = 
						new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe>(WsnbConstants.UNSUBSCRIBE_QNAME,
								com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe.class,((UnsubscribeImpl)value).getJaxbTypeObj());

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnsubscribeResponseAsDOM(UnsubscribeResponse value)
			throws WsnbException {
		Document result = null;
		if (value instanceof UnsubscribeResponseImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse> element = 
						new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse>(WsnbConstants.UNSUBSCRIBE_RESPONSE_QNAME,
								com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse.class,((UnsubscribeResponseImpl)value).getJaxbTypeObj());

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUnsupportedPolicyRequestFaultTypeAsDOM(
			UnsupportedPolicyRequestFaultType value) throws WsnbException {
		Document result = null;
		if (value instanceof UnsupportedPolicyRequestFaultTypeImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType> element = 
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnsupportedPolicyRequestFault(UnsupportedPolicyRequestFaultTypeImpl.toJaxbModel(value));

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

	@Override
	public final Document writeUseRawAsDOM(UseRaw value) throws WsnbException {
		Document result = null;
		if (value instanceof UseRawImpl){
			try {

				Marshaller marshaller = this.wsnbJaxbContext.createWSNotificationMarshaller();

				result = WsstarCommonUtils.getNamespaceDocumentBuilder().newDocument();

				// TODO : Check if it is a Thread safe method
				final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw> element = 
						new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw>(WsnbConstants.USERAW_QNAME,
								com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw.class,((UseRawImpl)value).getJaxbTypeObj());

				marshaller.marshal(element, result);            

			} catch (final JAXBException ex) {
				throw new WsnbException(WsrfbfUtils.getBindingExMessage(value), ex);
			} 
		}
		return result;	
	}

}
