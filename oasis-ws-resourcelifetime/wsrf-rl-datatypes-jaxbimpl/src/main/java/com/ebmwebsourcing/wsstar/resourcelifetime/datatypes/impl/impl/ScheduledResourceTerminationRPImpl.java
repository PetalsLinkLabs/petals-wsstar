package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ScheduledResourceTerminationRP;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class ScheduledResourceTerminationRPImpl	implements ScheduledResourceTerminationRP {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected ScheduledResourceTerminationRPImpl(CurrentTime currTime,TerminationTime termTime) {
		this.jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createScheduledResourceTerminationRP();		
		this.jaxbTypeObj.setCurrentTime(CurrentTimeImpl.toJaxbModel(currTime));
		this.jaxbTypeObj.setTerminationTime(TerminationTimeImpl.toJaxbModel(termTime));		
	}
	
	protected ScheduledResourceTerminationRPImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}	
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final CurrentTime getCurrentTime() {
		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime tmpVar = this.jaxbTypeObj.getCurrentTime();		
		return (tmpVar!=null)?new CurrentTimeImpl(this.jaxbTypeObj.getCurrentTime()):null;
	}

	@Override
	public final TerminationTime getTerminationTime() {
		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime tmpVar = this.jaxbTypeObj.getTerminationTime();		
		return (tmpVar!=null)?new TerminationTimeImpl(this.jaxbTypeObj.getTerminationTime()): null;
	}

	@Override
	public final void setCurrentTime(CurrentTime value) {
		this.jaxbTypeObj.setCurrentTime(CurrentTimeImpl.toJaxbModel(value));
	}

	@Override
	public final void setTerminationTime(TerminationTime value) {
		this.jaxbTypeObj.setTerminationTime(TerminationTimeImpl.toJaxbModel(value));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP}
	 *  "Jaxb model type" object from a {@link ScheduledResourceTerminationRP} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP toJaxbModel(ScheduledResourceTerminationRP apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP jaxbTypeObj = null;
		
		if(apiTypeObj instanceof ScheduledResourceTerminationRPImpl){
			jaxbTypeObj = ((ScheduledResourceTerminationRPImpl)apiTypeObj).getJaxbTypeObj();
		}
		else {
			jaxbTypeObj = WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createScheduledResourceTerminationRP();

			CurrentTime currentTime = apiTypeObj.getCurrentTime();
			if (currentTime != null){
				jaxbTypeObj.setCurrentTime(CurrentTimeImpl.toJaxbModel(currentTime));
			}
			TerminationTime terminationTime = apiTypeObj.getTerminationTime();

			if (terminationTime != null){
				jaxbTypeObj.setTerminationTime(TerminationTimeImpl.toJaxbModel(terminationTime));
			}
		}
		return jaxbTypeObj;
	}	

}
