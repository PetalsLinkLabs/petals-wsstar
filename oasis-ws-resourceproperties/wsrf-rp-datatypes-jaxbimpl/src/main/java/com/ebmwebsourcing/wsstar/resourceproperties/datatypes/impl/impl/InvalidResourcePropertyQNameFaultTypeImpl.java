package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidResourcePropertyQNameFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class InvalidResourcePropertyQNameFaultTypeImpl extends BaseFaultTypeImpl
	implements	InvalidResourcePropertyQNameFaultType {

	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link InvalidResourcePropertyQNameFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected InvalidResourcePropertyQNameFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(InvalidResourcePropertyQNameFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected InvalidResourcePropertyQNameFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(InvalidResourcePropertyQNameFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType}
	 *  "Jaxb model type" object from a {@link InvalidResourcePropertyQNameFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType toJaxbModel(InvalidResourcePropertyQNameFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof InvalidResourcePropertyQNameFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType)
			((InvalidResourcePropertyQNameFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createInvalidResourcePropertyQNameFaultType());
		}
		return jaxbTypeObj;
	}
	

}
