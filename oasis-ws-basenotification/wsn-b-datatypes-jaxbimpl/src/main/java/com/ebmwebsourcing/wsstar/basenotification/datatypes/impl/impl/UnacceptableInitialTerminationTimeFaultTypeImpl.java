package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableInitialTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnacceptableInitialTerminationTimeFaultTypeImpl extends BaseFaultTypeImpl
	implements UnacceptableInitialTerminationTimeFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnacceptableInitialTerminationTimeFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 * @param minTerminationTime TODO
	 */
	protected UnacceptableInitialTerminationTimeFaultTypeImpl(Date timestamp, Date minTermTime){
		super(Logger.getLogger(UnacceptableInitialTerminationTimeFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));						
		jaxbTypeObj.setMinimumTime(WsrfbfUtils.toXMLGregorianCalendar(minTermTime, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnacceptableInitialTerminationTimeFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnacceptableInitialTerminationTimeFaultTypeImpl.class.getSimpleName()));
	}
	
	@Override
	public final Date getMaximumTime() {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType refinedTypeFault = 
			((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType)
					this.getJaxbTypeObj());
		
		return (refinedTypeFault.getMaximumTime() != null)?refinedTypeFault.getMaximumTime().toGregorianCalendar().getTime(): null;
		 
	}
	
	@Override
	public final Date getMinimumTime() {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType refinedTypeFault = 
			((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType)
					this.getJaxbTypeObj());
		
		return (refinedTypeFault.getMinimumTime() != null)?refinedTypeFault.getMinimumTime().toGregorianCalendar().getTime(): null;
	}
	
	@Override
	public final void setMaximumTime(Date value) {
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType)
				this.getJaxbTypeObj()).setMaximumTime(WsrfbfUtils.toXMLGregorianCalendar(value, this.getLogger()));
	}
	
	@Override
	public final void setMinimumTime(Date value) {
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType)
				this.getJaxbTypeObj()).setMinimumTime(WsrfbfUtils.toXMLGregorianCalendar(value, this.getLogger()));
	};
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType}
	 *  "Jaxb model type" object from a {@link UnacceptableInitialTerminationTimeFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType toJaxbModel(UnacceptableInitialTerminationTimeFaultType apiTypeObj){
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UnacceptableInitialTerminationTimeFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType)
			((UnacceptableInitialTerminationTimeFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnacceptableInitialTerminationTimeFaultType());
		
			// ~~~~~ Set MiminumTime value ~~~~~
			Date minTime = apiTypeObj.getMinimumTime();
			jaxbTypeObj.setMinimumTime(WsrfbfUtils.toXMLGregorianCalendar(minTime,
					Logger.getLogger(UnacceptableInitialTerminationTimeFaultTypeImpl.class.getSimpleName())));

			// ~~~~~ Set MaximumTime value ~~~~~
			Date maxTime = apiTypeObj.getMaximumTime();
			if (maxTime != null){
				jaxbTypeObj.setMaximumTime(WsrfbfUtils.toXMLGregorianCalendar(maxTime,
						Logger.getLogger(UnacceptableInitialTerminationTimeFaultTypeImpl.class.getSimpleName())));
			}
		}
		return jaxbTypeObj;
		
	}
}
