package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroyPullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnableToDestroyPullPointFaultTypeImpl extends BaseFaultTypeImpl
		implements UnableToDestroyPullPointFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnableToDestroyPullPointFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected UnableToDestroyPullPointFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(UnableToDestroyPullPointFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnableToDestroyPullPointFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnableToDestroyPullPointFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType}
	 *  "Jaxb model type" object from a {@link UnableToDestroyPullPointFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType toJaxbModel(UnableToDestroyPullPointFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UnableToDestroyPullPointFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType)
			((UnableToDestroyPullPointFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToDestroyPullPointFaultType());
		}
		return jaxbTypeObj;
	}
}
