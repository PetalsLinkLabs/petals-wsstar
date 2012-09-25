package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MultipleTopicsSpecifiedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class MultipleTopicsSpecifiedFaultTypeImpl extends BaseFaultTypeImpl
		implements MultipleTopicsSpecifiedFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link MultipleTopicsSpecifiedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected MultipleTopicsSpecifiedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(MultipleTopicsSpecifiedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected MultipleTopicsSpecifiedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(MultipleTopicsSpecifiedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType}
	 *  "Jaxb model type" object from a {@link MultipleTopicsSpecifiedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType toJaxbModel(MultipleTopicsSpecifiedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof MultipleTopicsSpecifiedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType)
			((MultipleTopicsSpecifiedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createMultipleTopicsSpecifiedFaultType());
		}
		return jaxbTypeObj;
	}
}
