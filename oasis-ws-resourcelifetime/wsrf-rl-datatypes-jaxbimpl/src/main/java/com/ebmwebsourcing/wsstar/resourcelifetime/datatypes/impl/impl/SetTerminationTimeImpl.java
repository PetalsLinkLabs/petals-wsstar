package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class SetTerminationTimeImpl implements SetTerminationTime {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime jaxbTypeObj;
	private static Logger logger = Logger.getLogger(SetTerminationTimeImpl.class.getSimpleName());
	
	/**
	 * Default constructor with {@link Date} Type argument
	 */
	protected SetTerminationTimeImpl(Date time) {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createSetTerminationTime();		
		this.jaxbTypeObj.setRequestedTerminationTime(SetTerminationTimeImpl.toXMLGregorianCalendarJaxbElt(time));
	}
	
	/**
	 * Default constructor with {@link Duration} Type argument
	 */
	protected SetTerminationTimeImpl(Duration duration) {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createSetTerminationTime();		
		this.jaxbTypeObj.setRequestedLifetimeDuration(duration);					
	}
	
	protected SetTerminationTimeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}	
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final Date getRequestedTerminationTime() {		
		JAXBElement<XMLGregorianCalendar> fromJaxbModel =  this.jaxbTypeObj.getRequestedTerminationTime();
		return (fromJaxbModel != null)? fromJaxbModel.getValue().toGregorianCalendar().getTime():null;
	}

	@Override
	public final void setRequestedTerminationTime(Date value) {
		this.jaxbTypeObj.setRequestedTerminationTime(SetTerminationTimeImpl.toXMLGregorianCalendarJaxbElt(value));		
	}

	@Override
	public final Duration getRequestedLifetimeDuration() {
		return this.jaxbTypeObj.getRequestedLifetimeDuration();
	}

	@Override
	public final void setRequestedLifetimeDuration(Duration value) {
		this.jaxbTypeObj.setRequestedLifetimeDuration(value);
	}
			
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime}
	 *  "Jaxb model type" object from a {@link SetTerminationTime} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime toJaxbModel(SetTerminationTime apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime jaxbTypeObj = null; 
	
		if (apiTypeObj instanceof SetTerminationTimeImpl){
			jaxbTypeObj = ((SetTerminationTimeImpl)apiTypeObj).getJaxbTypeObj();
		}
		else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createSetTerminationTime();

			Date reqTermTime = apiTypeObj.getRequestedTerminationTime();
			if (reqTermTime != null) {
				jaxbTypeObj.setRequestedTerminationTime(SetTerminationTimeImpl.toXMLGregorianCalendarJaxbElt(reqTermTime));
			}		

			Duration requestedLifeTimeDuration = apiTypeObj.getRequestedLifetimeDuration();

			if (requestedLifeTimeDuration != null){
				jaxbTypeObj.setRequestedLifetimeDuration(requestedLifeTimeDuration);
			}
		}
		return jaxbTypeObj;
	}
	
	/**
	 * Convert {@link JaxbElement<XMLGregorianCalendar>} type in order to avoid to duplicate piece of code
	 *    
	 * @param time The {@link GregorianCalendar} Object to convert to a {@link XMLGregorianCalendar} object
	 * @return the corresponding {@link XMLGregorianCalendar} object.
	 */
	private static JAXBElement<XMLGregorianCalendar> toXMLGregorianCalendarJaxbElt(Date time) {		
		XMLGregorianCalendar xmlTime = WsrfbfUtils.toXMLGregorianCalendar(time, SetTerminationTimeImpl.logger);
		
		return WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createSetTerminationTimeRequestedTerminationTime(xmlTime);
	}
}
