package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidMessageContentExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class InvalidMessageContentExpressionFaultTypeImpl extends
		BaseFaultTypeImpl implements InvalidMessageContentExpressionFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link InvalidMessageContentExpressionFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected InvalidMessageContentExpressionFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(InvalidMessageContentExpressionFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected InvalidMessageContentExpressionFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(InvalidMessageContentExpressionFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType}
	 *  "Jaxb model type" object from a {@link InvalidMessageContentExpressionFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType toJaxbModel(InvalidMessageContentExpressionFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof InvalidMessageContentExpressionFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType)
			((InvalidMessageContentExpressionFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidMessageContentExpressionFaultType());
		}
		return jaxbTypeObj;
	}
}
