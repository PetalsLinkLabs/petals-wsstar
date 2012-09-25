package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class ResumeFailedFaultTypeImpl extends BaseFaultTypeImpl implements
		ResumeFailedFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link ResumeFailedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected ResumeFailedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(ResumeFailedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected ResumeFailedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(ResumeFailedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType}
	 *  "Jaxb model type" object from a {@link ResumeFailedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType toJaxbModel(ResumeFailedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof ResumeFailedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType)
			((ResumeFailedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createResumeFailedFaultType());
		}
		return jaxbTypeObj;
	}
}
