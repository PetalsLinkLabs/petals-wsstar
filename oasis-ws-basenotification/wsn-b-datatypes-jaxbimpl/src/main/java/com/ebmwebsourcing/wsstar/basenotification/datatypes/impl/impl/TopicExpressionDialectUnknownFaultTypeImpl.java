package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionDialectUnknownFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class TopicExpressionDialectUnknownFaultTypeImpl extends
		BaseFaultTypeImpl implements TopicExpressionDialectUnknownFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link TopicExpressionDialectUnknownFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected TopicExpressionDialectUnknownFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(TopicExpressionDialectUnknownFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected TopicExpressionDialectUnknownFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(TopicExpressionDialectUnknownFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType}
	 *  "Jaxb model type" object from a {@link TopicExpressionDialectUnknownFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType toJaxbModel(TopicExpressionDialectUnknownFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof TopicExpressionDialectUnknownFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType)
			((TopicExpressionDialectUnknownFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicExpressionDialectUnknownFaultType());
		}
		return jaxbTypeObj;
	}
}
