package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidProducerPropertiesExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class InvalidProducerPropertiesExpressionFaultTypeImpl extends BaseFaultTypeImpl
		implements	InvalidProducerPropertiesExpressionFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link InvalidProducerPropertiesExpressionFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected InvalidProducerPropertiesExpressionFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(InvalidProducerPropertiesExpressionFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected InvalidProducerPropertiesExpressionFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(InvalidProducerPropertiesExpressionFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType}
	 *  "Jaxb model type" object from a {@link InvalidProducerPropertiesExpressionFaultType} api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType toJaxbModel(InvalidProducerPropertiesExpressionFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof InvalidProducerPropertiesExpressionFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType)
			((InvalidProducerPropertiesExpressionFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidProducerPropertiesExpressionFaultType());
		}
		return jaxbTypeObj;
	}
}
