package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class CurrentTimeImpl implements CurrentTime {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(CurrentTimeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected CurrentTimeImpl(Date time) {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createCurrentTime();		
		this.jaxbTypeObj.setValue(WsrfbfUtils.toXMLGregorianCalendar(time,CurrentTimeImpl.logger));					
	}
	
	protected CurrentTimeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}	
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final Date getValue() {
		XMLGregorianCalendar tmpVar = this.jaxbTypeObj.getValue();
		return (tmpVar != null)?tmpVar.toGregorianCalendar().getTime():null;
	}

	@Override
	public final void setValue(Date value) {
		this.jaxbTypeObj.setValue(WsrfbfUtils.toXMLGregorianCalendar(value,CurrentTimeImpl.logger));
	}
	
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime}
	 *  "Jaxb model type" object from a {@link CurrentTime} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime toJaxbModel(CurrentTime apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime jaxbTypeObj = null; 
		
		if (apiTypeObj instanceof CurrentTimeImpl) {
			jaxbTypeObj = ((CurrentTimeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createCurrentTime();

			Date time = apiTypeObj.getValue();
			if (time != null) {
				jaxbTypeObj.setValue(WsrfbfUtils.toXMLGregorianCalendar(time, CurrentTimeImpl.logger));
			}		
		}
		return jaxbTypeObj;
	}
}
