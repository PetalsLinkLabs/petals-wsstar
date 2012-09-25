package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToGetMessagesFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnableToGetMessagesFaultTypeImpl extends BaseFaultTypeImpl
		implements UnableToGetMessagesFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnableToGetMessagesFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected UnableToGetMessagesFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(UnableToGetMessagesFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnableToGetMessagesFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnableToGetMessagesFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType}
	 *  "Jaxb model type" object from a {@link UnableToGetMessagesFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType toJaxbModel(UnableToGetMessagesFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UnableToGetMessagesFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType)
			((UnableToGetMessagesFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnableToGetMessagesFaultType());
		}
		return jaxbTypeObj;
	}
}
