package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotifyMessageNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class NotifyMessageNotSupportedFaultTypeImpl extends BaseFaultTypeImpl
		implements NotifyMessageNotSupportedFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link NotifyMessageNotSupportedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected NotifyMessageNotSupportedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(NotifyMessageNotSupportedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType	jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected NotifyMessageNotSupportedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(NotifyMessageNotSupportedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType}
	 *  "Jaxb model type" object from a {@link NotifyMessageNotSupportedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType toJaxbModel(NotifyMessageNotSupportedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof NotifyMessageNotSupportedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType)
			((NotifyMessageNotSupportedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotifyMessageNotSupportedFaultType());
		}
		return jaxbTypeObj;
	}
}
