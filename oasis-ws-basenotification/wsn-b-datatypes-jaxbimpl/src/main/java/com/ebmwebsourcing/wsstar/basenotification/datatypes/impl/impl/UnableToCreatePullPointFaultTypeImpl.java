package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToCreatePullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnableToCreatePullPointFaultTypeImpl extends BaseFaultTypeImpl
		implements UnableToCreatePullPointFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnableToCreatePullPointFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected UnableToCreatePullPointFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(UnableToCreatePullPointFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnableToCreatePullPointFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnableToCreatePullPointFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType}
	 *  "Jaxb model type" object from a {@link UnableToCreatePullPointFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType toJaxbModel(UnableToCreatePullPointFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UnableToCreatePullPointFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType)
			((UnableToCreatePullPointFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToCreatePullPointFaultType());
		}
		return jaxbTypeObj;
	}
}
