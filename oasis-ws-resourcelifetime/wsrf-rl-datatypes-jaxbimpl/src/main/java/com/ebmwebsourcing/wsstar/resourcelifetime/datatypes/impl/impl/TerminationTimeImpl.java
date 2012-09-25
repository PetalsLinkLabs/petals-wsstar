package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class TerminationTimeImpl implements TerminationTime {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime jaxbTypeObj;
	private static Logger logger = Logger.getLogger(TerminationTimeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected TerminationTimeImpl(Date time) {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createTerminationTime();		
		this.jaxbTypeObj.setValue(WsrfbfUtils.toXMLGregorianCalendar(time, TerminationTimeImpl.logger));					
	}
	
	protected TerminationTimeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}	
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final Date getValue() {	
		XMLGregorianCalendar tmpVar = this.jaxbTypeObj.getValue();
		return (tmpVar!= null)?tmpVar.toGregorianCalendar().getTime() : null;
	}

	@Override
	public final void setValue(Date value) {
		this.jaxbTypeObj.setValue(WsrfbfUtils.toXMLGregorianCalendar(value, TerminationTimeImpl.logger));	
	}
		
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime}
	 *  "Jaxb model type" object from a {@link TerminationTime} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime toJaxbModel(TerminationTime apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime jaxbTypeObj = null;
		if (apiTypeObj instanceof TerminationTimeImpl){
			jaxbTypeObj = ((TerminationTimeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createTerminationTime();

			Date time = apiTypeObj.getValue();
			if (time != null) {
				jaxbTypeObj.setValue(WsrfbfUtils.toXMLGregorianCalendar(time, TerminationTimeImpl.logger));
			}		
		}
		return jaxbTypeObj;
	}
}
