package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NoCurrentMessageOnTopicFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class NoCurrentMessageOnTopicFaultTypeImpl extends BaseFaultTypeImpl
		implements NoCurrentMessageOnTopicFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link NoCurrentMessageOnTopicFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected NoCurrentMessageOnTopicFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(NoCurrentMessageOnTopicFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected NoCurrentMessageOnTopicFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(NoCurrentMessageOnTopicFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType}
	 *  "Jaxb model type" object from a {@link NoCurrentMessageOnTopicFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType toJaxbModel(NoCurrentMessageOnTopicFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof NoCurrentMessageOnTopicFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType)
			((NoCurrentMessageOnTopicFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createNoCurrentMessageOnTopicFaultType());
		}
		return jaxbTypeObj;
	}
}
