package com.ebmwebsourcing.wsstar.resource.datatypes.impl.impl;

import java.util.Date;

import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnavailableFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnknownFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrReader;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrWriter;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.implementor.WsrfrModelFactory;

public class WsrfrModelFactoryImpl implements WsrfrModelFactory {

	private WsrfrReaderImpl wsrfrModelReader;
	private WsrfrWriterImpl wsrfrModelWriter;
	
	public WsrfrModelFactoryImpl() {	
		this.wsrfrModelReader = new WsrfrReaderImpl();
		this.wsrfrModelWriter = new WsrfrWriterImpl();		
	}
	
	@Override
	public final ResourceUnknownFaultType createWsrfrModelResourceUnknownFaultType(
			Date timestamp) {
		return new ResourceUnknownFaultTypeImpl(timestamp);
	}
	
	public final ResourceUnknownFaultType createWsrfrModelResourceUnknownFaultType(
			com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType jaxbObj) {
		return new ResourceUnknownFaultTypeImpl(jaxbObj);
	}
	
	@Override
	public final ResourceUnavailableFaultType createWsrfrModelResourceUnavailableFaultType(
			Date timestamp) {
		return new ResourceUnavailableFaultTypeImpl(timestamp);
	}
	
	public final ResourceUnavailableFaultType createWsrfrModelResourceUnavailableFaultType(
			com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType jaxbObj) {
		return new ResourceUnavailableFaultTypeImpl(jaxbObj);
	}
	@Override
	public final WsrfrReader getWsrfrModelReader() {
		return this.wsrfrModelReader;
	}

	@Override
	public final WsrfrWriter getWsrfrModelWriter() {
		return this.wsrfrModelWriter;
	}

}
