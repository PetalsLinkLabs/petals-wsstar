package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;


import java.util.Date;

import javax.xml.datatype.Duration;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ResourceNotDestroyedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ScheduledResourceTerminationRP;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationNotification;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTimeChangeRejectedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.UnableToSetTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlReader;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlWriter;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.implementor.WsrfrlModelFactory;

public class WsrfrlModelFactoryImpl implements WsrfrlModelFactory {
	
	private WsrfrlReaderImpl wsrfrlModelReader;
	private WsrfrlWriterImpl wsrfrlModelWriter;
	
	public WsrfrlModelFactoryImpl() {	
		this.wsrfrlModelReader = new WsrfrlReaderImpl();
		this.wsrfrlModelWriter = new WsrfrlWriterImpl();		
	}
	
	@Override
	public final CurrentTime createWsrfrlModelCurrentTime(Date value) {
		return new CurrentTimeImpl(value);
	}

	@Override
	public final Destroy createWsrfrlModelDestroy() {
		return new DestroyImpl();
	}

	@Override
	public final DestroyResponse createWsrfrlModelDestroyResponse() {
		return new DestroyResponseImpl();
	}

	@Override
	public final ResourceNotDestroyedFaultType createWsrfrlModelResourceNotDestroyedFaultType(
			Date timestamp) {
		return new ResourceNotDestroyedFaultTypeImpl(timestamp);
	}

	@Override
	public final ScheduledResourceTerminationRP createWsrfrlModelScheduledResourceTerminationRP(
			CurrentTime currTime, TerminationTime termTime) {
		return new ScheduledResourceTerminationRPImpl(currTime, termTime);
	}

	@Override
	public final SetTerminationTime createWsrfrlModelSetTerminationTime(
			Date value) {	
		return new SetTerminationTimeImpl(value);
	}

	@Override
	public final SetTerminationTime createWsrfrlModelSetTerminationTime(Duration value) {
		return new SetTerminationTimeImpl(value);
	}

	@Override
	public final SetTerminationTimeResponse createWsrfrlModelSetTerminationTimeResponse(
			Date currTime, Date newTermTime) {
		return new SetTerminationTimeResponseImpl(currTime, newTermTime);
	}

	@Override
	public final TerminationNotification createWsrfrlModelTerminationNotification(Date terminationTime) {
		return new TerminationNotificationImpl(terminationTime);
	}

	@Override
	public final TerminationTime createWsrfrlModelTerminationTime(
			Date value) {
		return new TerminationTimeImpl(value);
	}

	@Override
	public final TerminationTimeChangeRejectedFaultType createWsrfrlModelTerminationTimeChangeRejectedFaultType(
			Date timestamp) {
		return new TerminationTimeChangeRejectedFaultTypeImpl(timestamp);
	}

	@Override
	public final UnableToSetTerminationTimeFaultType createWsrfrlModelUnableToSetTerminationTimeFaultType(
			Date timestamp) {
		return new UnableToSetTerminationTimeFaultTypeImpl(timestamp);
	}

	@Override
	public final WsrfrlReader getWsrfrlModelReader() {
		return this.wsrfrlModelReader;
	}

	@Override
	public final WsrfrlWriter getWsrfrlModelWriter() {
		return this.wsrfrlModelWriter;
	}

}
