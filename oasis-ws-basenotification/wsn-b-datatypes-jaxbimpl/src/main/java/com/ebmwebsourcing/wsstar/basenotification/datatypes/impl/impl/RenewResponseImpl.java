package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.RenewResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class RenewResponseImpl implements RenewResponse {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(RenewResponseImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected RenewResponseImpl(Date termTime) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createRenewResponse();	
		this.jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(termTime, RenewResponseImpl.logger));
	}

	protected RenewResponseImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final Date getTerminationTime() {
		return this.jaxbTypeObj.getTerminationTime().toGregorianCalendar().getTime();
	}

	@Override
	public final void setTerminationTime(Date value) {
		this.jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(value, RenewResponseImpl.logger));
	}

	@Override
	public final Date getCurrentTime() {
		Date currentTime = null;
		XMLGregorianCalendar jaxbCurrentTime =  this.jaxbTypeObj.getCurrentTime();
		if (jaxbCurrentTime != null){
			currentTime = jaxbCurrentTime.toGregorianCalendar().getTime();
		}
		return currentTime;
	}

	@Override
	public final void setCurrentTime(Date value) {
		this.jaxbTypeObj.setCurrentTime(WsrfbfUtils.toXMLGregorianCalendar(value, RenewResponseImpl.logger));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType}
	 *  "Jaxb model type" object from a {@link NotificationMessageHolderType} "api type" one  
	 * @param apiTypeObj
	 * @return
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse toJaxbModel(RenewResponse apiTypeObj) {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse jaxbTypeObj = null;
		
		if (apiTypeObj instanceof RenewResponseImpl){
			jaxbTypeObj = ((RenewResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createRenewResponse();
			
			// ~~~~ Set TerminationTime ~~~~~
			jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(apiTypeObj.getTerminationTime(), RenewResponseImpl.logger));
			
			// ~~~~ Set CurrentTime ~~~~
			Date currentTime = apiTypeObj.getCurrentTime();
			if (currentTime != null){
				jaxbTypeObj.setCurrentTime(WsrfbfUtils.toXMLGregorianCalendar(currentTime, RenewResponseImpl.logger));
			}
		}
		return jaxbTypeObj;
	}
}
