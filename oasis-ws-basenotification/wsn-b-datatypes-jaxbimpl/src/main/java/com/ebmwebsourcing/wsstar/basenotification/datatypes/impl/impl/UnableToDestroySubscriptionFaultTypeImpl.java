package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroySubscriptionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnableToDestroySubscriptionFaultTypeImpl extends BaseFaultTypeImpl
		implements UnableToDestroySubscriptionFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnableToDestroySubscriptionFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected UnableToDestroySubscriptionFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(UnableToDestroySubscriptionFaultTypeImpl.class.getSimpleName()));

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}

	protected UnableToDestroySubscriptionFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnableToDestroySubscriptionFaultTypeImpl.class.getSimpleName()));
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType}
	 *  "Jaxb model type" object from a {@link UnableToDestroySubscriptionFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType toJaxbModel(UnableToDestroySubscriptionFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UnableToDestroySubscriptionFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType)
			((UnableToDestroySubscriptionFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToDestroySubscriptionFaultType());
		}
		return jaxbTypeObj;
	}
}