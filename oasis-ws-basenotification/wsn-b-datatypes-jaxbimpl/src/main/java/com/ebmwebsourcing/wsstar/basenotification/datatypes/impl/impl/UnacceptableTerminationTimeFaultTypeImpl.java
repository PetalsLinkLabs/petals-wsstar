package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnacceptableTerminationTimeFaultTypeImpl extends BaseFaultTypeImpl
		implements UnacceptableTerminationTimeFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnacceptableTerminationTimeFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 * @param minTerminationTime TODO
	 */
	protected UnacceptableTerminationTimeFaultTypeImpl(Date timestamp, Date minTerminationTime){
		super(Logger.getLogger(UnacceptableTerminationTimeFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));				
		jaxbTypeObj.setMinimumTime(WsrfbfUtils.toXMLGregorianCalendar(minTerminationTime, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnacceptableTerminationTimeFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnacceptableTerminationTimeFaultTypeImpl.class.getSimpleName()));
	}
	
	@Override
	public final Date getMaximumTime() {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType refinedTypeFault = 
			((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType)
					this.getJaxbTypeObj());
		
		return (refinedTypeFault.getMaximumTime() != null)?refinedTypeFault.getMaximumTime().toGregorianCalendar().getTime(): null;
		 
	}
	
	@Override
	public final Date getMinimumTime() {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType refinedTypeFault = 
			((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType)
					this.getJaxbTypeObj());
		
		return (refinedTypeFault.getMinimumTime() != null)?refinedTypeFault.getMinimumTime().toGregorianCalendar().getTime(): null;
	}
	
	@Override
	public final void setMaximumTime(Date value) {
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType)
				this.getJaxbTypeObj()).setMaximumTime(WsrfbfUtils.toXMLGregorianCalendar(value, this.getLogger()));
	}
	
	@Override
	public final void setMinimumTime(Date value) {
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType)
				this.getJaxbTypeObj()).setMinimumTime(WsrfbfUtils.toXMLGregorianCalendar(value, this.getLogger()));
	};
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType}
	 *  "Jaxb model type" object from a {@link UnacceptableTerminationTimeFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType toJaxbModel(UnacceptableTerminationTimeFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UnacceptableTerminationTimeFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType)
			((UnacceptableTerminationTimeFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnacceptableTerminationTimeFaultType());
		
			// ~~~~~ Set MiminumTime value ~~~~~
			Date minTime = apiTypeObj.getMinimumTime();
			jaxbTypeObj.setMinimumTime(WsrfbfUtils.toXMLGregorianCalendar(minTime,
					Logger.getLogger(UnacceptableTerminationTimeFaultTypeImpl.class.getSimpleName())));

			// ~~~~~ Set MaximumTime value ~~~~~
			Date maxTime = apiTypeObj.getMaximumTime();
			if (maxTime != null){
				jaxbTypeObj.setMaximumTime(WsrfbfUtils.toXMLGregorianCalendar(maxTime,
						Logger.getLogger(UnacceptableTerminationTimeFaultTypeImpl.class.getSimpleName())));
			}		
		}
		return jaxbTypeObj;
	}
}
