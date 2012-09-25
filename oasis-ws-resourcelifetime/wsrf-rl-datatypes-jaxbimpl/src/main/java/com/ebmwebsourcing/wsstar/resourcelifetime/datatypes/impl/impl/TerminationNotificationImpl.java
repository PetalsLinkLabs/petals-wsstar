package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationNotification;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class TerminationNotificationImpl implements TerminationNotification {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification jaxbTypeObj;
	private static Logger logger = Logger.getLogger(TerminationTimeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected TerminationNotificationImpl(Date termTime) {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createTerminationNotification();		
		this.jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(termTime, TerminationNotificationImpl.logger));					
	}
	
	protected TerminationNotificationImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}	
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification getJaxbTypeObj() {
		return jaxbTypeObj;
	} 
	
	@Override
	public final Date getTerminationTime() {
		XMLGregorianCalendar tmpVar = jaxbTypeObj.getTerminationTime();
		return (tmpVar!= null)? tmpVar.toGregorianCalendar().getTime() : null;
	}

	@Override
	public final void setTerminationTime(Date value) {
		this.jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(value, TerminationNotificationImpl.logger));
	}

	@Override
	public final Object getTerminationReason() {
		return this.jaxbTypeObj.getTerminationReason();
	}

	@Override
	public final void setTerminationReason(Object value) {
		this.jaxbTypeObj.setTerminationReason(value);
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification}
	 *  "Jaxb model type" object from a {@link TerminationNotification} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification toJaxbModel(TerminationNotification apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification jaxbTypeObj = null;

		if (apiTypeObj instanceof TerminationNotificationImpl){
			jaxbTypeObj = ((TerminationNotificationImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createTerminationNotification();

			Date termTime = apiTypeObj.getTerminationTime();
			if (termTime != null) {
				jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(termTime,TerminationNotificationImpl.logger));
			}
			Object termReason  = apiTypeObj.getTerminationReason();		
			if (termReason != null){
				jaxbTypeObj.setTerminationReason(termReason);
			}
		}
		return jaxbTypeObj;
	}
}
