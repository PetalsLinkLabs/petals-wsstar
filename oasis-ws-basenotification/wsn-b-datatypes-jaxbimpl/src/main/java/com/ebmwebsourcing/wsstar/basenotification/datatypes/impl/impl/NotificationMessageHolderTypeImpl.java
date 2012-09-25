package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.net.URI;
import java.util.Arrays;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class NotificationMessageHolderTypeImpl implements
		NotificationMessageHolderType {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType jaxbTypeObj;
//	private static Logger logger  = Logger.getLogger(NotificationMessageHolderTypeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected NotificationMessageHolderTypeImpl(Message message) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotificationMessageHolderType();
		this.jaxbTypeObj.setMessage(NotificationMessageHolderTypeImpl.MessageImpl.toJaxbModel(message));
		
	}

	protected NotificationMessageHolderTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final TopicExpressionType getTopic() {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType fromJaxbModel = this.jaxbTypeObj.getTopic();		
		return (fromJaxbModel!=null)?new TopicExpressionTypeImpl(fromJaxbModel):null;
	}

	@Override
	public final void setTopic(TopicExpressionType concreteTopic) {
		this.jaxbTypeObj.setTopic(TopicExpressionTypeImpl.toJaxbModel(concreteTopic));
	}

	@Override
	public final Message getMessage() {		
		return new NotificationMessageHolderTypeImpl.MessageImpl(this.jaxbTypeObj.getMessage());
	}

	@Override
	public final void setMessage(Message msg) {
		this.jaxbTypeObj.setMessage(NotificationMessageHolderTypeImpl.MessageImpl.toJaxbModel(msg));
	}

	@Override
	public final EndpointReferenceType getSubscriptionReference() {		
		EndpointReferenceType result = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		result.setAddress(WSNUtil.getInstance().getXmlObjectFactory().create(Address.class));
		// TODO: set all attribute
		result.getAddress().setValue(URI.create(this.jaxbTypeObj.getSubscriptionReference().getAddress().getValue()));
		
		if(this.jaxbTypeObj.getSubscriptionReference().getReferenceParameters() != null) {
			for(Object obj: this.jaxbTypeObj.getSubscriptionReference().getReferenceParameters().getAny()) {
				result.getReferenceParameters().addAny(obj);
			}
		}
		return result;		
	}

	@Override
	public final void setSubscriptionReference(EndpointReferenceType endpoint) {
		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(endpoint.getAddress().getValue().toString());
		// TODO: copy all attribute or easybox all spec..... :(
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(endpoint.getReferenceParameters().getAny()));

		
		this.jaxbTypeObj.setSubscriptionReference(jaxbModelObj);
	}

	@Override
	public final EndpointReferenceType getProducerReference() {		
		EndpointReferenceType result = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		result.setAddress(WSNUtil.getInstance().getXmlObjectFactory().create(Address.class));
		// TODO: set all attribute
		result.getAddress().setValue(URI.create(this.jaxbTypeObj.getProducerReference().getAddress().getValue()));
		if(this.jaxbTypeObj.getProducerReference().getReferenceParameters() != null) {
			for(Object obj: this.jaxbTypeObj.getProducerReference().getReferenceParameters().getAny()) {
				result.getReferenceParameters().addAny(obj);
			}
		}
		return result;	
	}

	@Override
	public final void setProducerReference(EndpointReferenceType endpoint) {
		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(endpoint.getAddress().getValue().toString());
		// TODO: copy all attribute or easybox all spec..... :(
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(endpoint.getReferenceParameters().getAny()));


		this.jaxbTypeObj.setProducerReference(jaxbModelObj);
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType}
	 *  "Jaxb model type" object from a {@link NotificationMessageHolderType} "api type" one  
	 * @param apiTypeObj
	 * @return
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType toJaxbModel(
			NotificationMessageHolderType apiTypeObj) {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof NotificationMessageHolderTypeImpl){
			jaxbTypeObj = ((NotificationMessageHolderTypeImpl)apiTypeObj).getJaxbTypeObj();
		}
		else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotificationMessageHolderType();
			
			// ~~~~ Set Message ~~~~~
			jaxbTypeObj.setMessage(NotificationMessageHolderTypeImpl.MessageImpl.toJaxbModel(apiTypeObj.getMessage()));
			
			// ~~~~ Set Topic (optional) ~~~~~
			TopicExpressionType topicExpr = apiTypeObj.getTopic();
			if (topicExpr != null){
				jaxbTypeObj.setTopic(TopicExpressionTypeImpl.toJaxbModel(topicExpr)); 
			}
			// ~~~~ Set ProducerReference (optional) ~~~~~
			EndpointReferenceType producerRef = apiTypeObj.getProducerReference();
			if (producerRef != null){	
				easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
				jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
				jaxbModelObj.getAddress().setValue(producerRef.getAddress().getValue().toString());
				jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
				jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(producerRef.getReferenceParameters().getAny()));


				jaxbTypeObj.setProducerReference(jaxbModelObj);
			}
			// ~~~~ Set SubscriptionReference (optional) ~~~~~
			EndpointReferenceType subscriptionRef = apiTypeObj.getSubscriptionReference();
			if (subscriptionRef != null){
				easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
				jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
				jaxbModelObj.getAddress().setValue(subscriptionRef.getAddress().getValue().toString());
				jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
				jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(producerRef.getReferenceParameters().getAny()));

				jaxbTypeObj.setSubscriptionReference(jaxbModelObj);
			}
		}
		return jaxbTypeObj;
	}
	
	public static class MessageImpl implements NotificationMessageHolderType.Message {
		
		private com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.Message jaxbTypeObj;
		//private static Logger logger  = Logger.getLogger(NotificationMessageHolderTypeImpl.MessageImpl.class.getSimpleName());
		
		/**
		 * Default constructor
		 */
		protected MessageImpl(Element content) {		
			this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotificationMessageHolderTypeMessage();
			this.jaxbTypeObj.setAny(content);		
		}

		protected MessageImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.Message jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}
		
		protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.Message getJaxbTypeObj() {
			return jaxbTypeObj;
		}
		
		@Override
		public final Element getAny() {
			Object jaxbAny = this.jaxbTypeObj.getAny();
			return (jaxbAny instanceof Element)?(Element)jaxbAny:null;
		}

		@Override
		public final void setAny(Element value) {
			this.jaxbTypeObj.setAny(value);
		}
		
		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.Message}
		 *  "Jaxb model type" object from a {@link NotificationMessageHolderType.Message} "api type" one  
		 * @param apiTypeObj
		 * @return
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.Message toJaxbModel(
				Message apiTypeObj) {
			com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.Message jaxbTypeObj = null;
			
			if (apiTypeObj instanceof NotificationMessageHolderTypeImpl.MessageImpl) {
				jaxbTypeObj = ((NotificationMessageHolderTypeImpl.MessageImpl)apiTypeObj).getJaxbTypeObj();
			} else {
				jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotificationMessageHolderTypeMessage();
				
				jaxbTypeObj.setAny(apiTypeObj.getAny());				
			}
			return jaxbTypeObj;
		}
		
	}
	
}
