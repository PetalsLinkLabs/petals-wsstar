package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class TopicNotSupportedFaultTypeImpl extends BaseFaultTypeImpl implements
		TopicNotSupportedFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link TopicNotSupportedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected TopicNotSupportedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(TopicNotSupportedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected TopicNotSupportedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(TopicNotSupportedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType}
	 *  "Jaxb model type" object from a {@link TopicNotSupportedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType toJaxbModel(TopicNotSupportedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof TopicNotSupportedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType)
			((TopicNotSupportedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicNotSupportedFaultType());
		}
		return jaxbTypeObj;
	}
}
