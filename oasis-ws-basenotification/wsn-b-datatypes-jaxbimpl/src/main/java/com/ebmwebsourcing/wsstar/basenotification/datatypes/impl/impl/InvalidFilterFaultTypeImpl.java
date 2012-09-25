package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidFilterFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class InvalidFilterFaultTypeImpl extends BaseFaultTypeImpl implements
		InvalidFilterFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link InvalidFilterFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 * @param unknownFilters TODO
	 */
	protected InvalidFilterFaultTypeImpl(Date timestamp, List<QName> unknownFilters){
		super(Logger.getLogger(InvalidFilterFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType)jaxbTypeObj).getUnknownFilter().addAll(unknownFilters);
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected InvalidFilterFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(InvalidFilterFaultTypeImpl.class.getSimpleName()));
	}
	
	@Override
	public final List<QName> getUnknwonFilters() {
		return ((com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType)this.getJaxbTypeObj()).getUnknownFilter();
	}
	
	@Override
	public final void addUnknwonFilter(QName value) {
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType)this.getJaxbTypeObj()).getUnknownFilter().add(value);
	}
	
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType}
	 *  "Jaxb model type" object from a {@link InvalidFilterFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType toJaxbModel(InvalidFilterFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof InvalidFilterFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType)
				((InvalidFilterFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType) 
				BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
						WsnbJAXBContext.WSNB_JAXB_FACTORY.createInvalidFilterFaultType());
			
			// ~~~~ Set UnknownFilter QNames (at least one) ~~~~~
			jaxbTypeObj.getUnknownFilter().addAll(apiTypeObj.getUnknwonFilters());
		}
		return jaxbTypeObj;
	}
	
}
