package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidTopicExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class InvalidTopicExpressionFaultTypeImpl extends BaseFaultTypeImpl
		implements InvalidTopicExpressionFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link InvalidTopicExpressionFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected InvalidTopicExpressionFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(InvalidTopicExpressionFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected InvalidTopicExpressionFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(InvalidTopicExpressionFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType}
	 *  "Jaxb model type" object from a {@link InvalidTopicExpressionFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType toJaxbModel(InvalidTopicExpressionFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof InvalidTopicExpressionFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType)
			((InvalidTopicExpressionFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidTopicExpressionFaultType());
		}
		return jaxbTypeObj;
	}
}
