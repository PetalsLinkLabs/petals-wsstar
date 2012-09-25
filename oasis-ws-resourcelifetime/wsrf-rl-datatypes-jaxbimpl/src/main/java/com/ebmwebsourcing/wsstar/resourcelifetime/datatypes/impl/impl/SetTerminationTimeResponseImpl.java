package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class SetTerminationTimeResponseImpl
		implements SetTerminationTimeResponse {

	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse jaxbTypeObj;
	private static Logger logger = Logger.getLogger(SetTerminationTimeResponseImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected SetTerminationTimeResponseImpl(Date currTime,Date newTermTime) {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createSetTerminationTimeResponse();		
		this.jaxbTypeObj.setCurrentTime(WsrfbfUtils.toXMLGregorianCalendar(currTime, SetTerminationTimeResponseImpl.logger));	
		this.jaxbTypeObj.setNewTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(newTermTime, SetTerminationTimeResponseImpl.logger));	
	}
	
	protected SetTerminationTimeResponseImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}	
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final Date getNewTerminationTime() {
		XMLGregorianCalendar tmpVar = this.jaxbTypeObj.getNewTerminationTime();
		return (tmpVar!=null)?tmpVar.toGregorianCalendar().getTime():null;
	}

	@Override
	public final void setNewTerminationTime(Date value) {
		this.jaxbTypeObj.setNewTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(value, SetTerminationTimeResponseImpl.logger)); 

	}

	@Override
	public final Date getCurrentTime() {
		XMLGregorianCalendar tmpVar = this.jaxbTypeObj.getCurrentTime();
		return (tmpVar!=null)?tmpVar.toGregorianCalendar().getTime():null;
	}

	@Override
	public final void setCurrentTime(Date value) {
		this.jaxbTypeObj.setCurrentTime(WsrfbfUtils.toXMLGregorianCalendar(value, SetTerminationTimeResponseImpl.logger));	
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse}
	 *  "Jaxb model type" object from a {@link SetTerminationTimeResponse} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse toJaxbModel(SetTerminationTimeResponse apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse jaxbTypeObj = null;
		if (apiTypeObj instanceof SetTerminationTimeResponseImpl){
			jaxbTypeObj = ((SetTerminationTimeResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createSetTerminationTimeResponse();

			Date currTime = apiTypeObj.getCurrentTime();
			if (currTime != null){
				jaxbTypeObj.setCurrentTime(WsrfbfUtils.toXMLGregorianCalendar(currTime,SetTerminationTimeResponseImpl.logger));
			}
			Date newTermTime = apiTypeObj.getNewTerminationTime();		
			if (newTermTime != null){
				jaxbTypeObj.setNewTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(newTermTime, SetTerminationTimeResponseImpl.logger));
			}
		}
		return jaxbTypeObj;
	}
	
}
