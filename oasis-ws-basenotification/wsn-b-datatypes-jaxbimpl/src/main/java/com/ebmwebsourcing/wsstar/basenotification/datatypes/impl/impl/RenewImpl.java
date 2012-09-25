package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Renew;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class RenewImpl implements Renew {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(RenewImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected RenewImpl() {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createRenew();	
	}

	protected RenewImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final Object getTerminationTime() {
		Object termTime = null;

		String jaxbTermTime = this.jaxbTypeObj.getTerminationTime();
		if (jaxbTermTime != null && jaxbTermTime.length() >0){
			try {
				if (jaxbTermTime.startsWith("P")){				
					termTime = DatatypeFactory.newInstance().newDuration(jaxbTermTime);				
				} else if(jaxbTermTime.contains("T")){
					termTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(jaxbTermTime).toGregorianCalendar().getTime();
				}
			} catch (DatatypeConfigurationException e) {
				RenewImpl.logger.log(Level.WARNING,"The \"InitalTerminaionTime\" field " +
						"value of the \"Subsrcibe\" payload does correspond to a string representation" +
						"of a \"Duration\" xml type nor \"DateTime\" xml type (received : " + jaxbTermTime+ ") \n",e);	
			}	
		}	
		return termTime;
	}

	@Override
	public final void setTerminationTime(Duration value) {
		this.jaxbTypeObj.setTerminationTime(value.toString());
	}

	@Override
	public final void setTerminationTime(Date value) {
		this.jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(value, RenewImpl.logger).toString());
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType}
	 *  "Jaxb model type" object from a {@link NotificationMessageHolderType} "api type" one  
	 * @param apiTypeObj
	 * @return
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew toJaxbModel(Renew apiTypeObj) {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew jaxbTypeObj = null;
		
		if (apiTypeObj instanceof RenewImpl){
			jaxbTypeObj = ((RenewImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createRenew();
			
			// ~~~~ Set TerminationTime ~~~~~
			Object termiTime = apiTypeObj.getTerminationTime();
			if (termiTime != null){
				if (termiTime instanceof Duration){
					jaxbTypeObj.setTerminationTime(((Duration)termiTime).toString());
				} else if (termiTime instanceof Date){	
					jaxbTypeObj.setTerminationTime(((Date)termiTime).toString());
				}
			}
		}
		return jaxbTypeObj;
	}
}
