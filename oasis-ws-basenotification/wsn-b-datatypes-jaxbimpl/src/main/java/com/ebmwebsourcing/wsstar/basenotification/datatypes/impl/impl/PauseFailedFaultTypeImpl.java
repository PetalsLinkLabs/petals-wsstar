package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class PauseFailedFaultTypeImpl extends BaseFaultTypeImpl implements
		PauseFailedFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link PauseFailedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected PauseFailedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(PauseFailedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected PauseFailedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(PauseFailedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType}
	 *  "Jaxb model type" object from a {@link PauseFailedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType toJaxbModel(PauseFailedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof PauseFailedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType)
			((PauseFailedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createPauseFailedFaultType());
		}
		return jaxbTypeObj;
	}
}
