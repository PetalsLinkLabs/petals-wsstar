package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class GetCurrentMessageImpl implements GetCurrentMessage {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage jaxbTypeObj;
//	private static Logger logger  = Logger.getLogger(GetCurrentMessageImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected GetCurrentMessageImpl(TopicExpressionType concreteTopic) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createGetCurrentMessage();	
		this.jaxbTypeObj.setTopic(TopicExpressionTypeImpl.toJaxbModel(concreteTopic));
	}

	protected GetCurrentMessageImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final TopicExpressionType getTopic() {		
		return new TopicExpressionTypeImpl(this.jaxbTypeObj.getTopic());
	}

	@Override
	public final void setTopic(TopicExpressionType value) {
		this.jaxbTypeObj.setTopic(TopicExpressionTypeImpl.toJaxbModel(value));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.GetCurrentMessage}
	 *  "Jaxb model type" object from a {@link GetCurrentMessage} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage toJaxbModel(GetCurrentMessage apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage jaxbTypeObj = null;
		
		if (apiTypeObj instanceof GetCurrentMessageImpl){
			jaxbTypeObj = ((GetCurrentMessageImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createGetCurrentMessage();
		
			// ~~~~ Set Topic ~~~~
			jaxbTypeObj.setTopic(TopicExpressionTypeImpl.toJaxbModel(apiTypeObj.getTopic()));
		}
		
		return jaxbTypeObj;
	}
}
