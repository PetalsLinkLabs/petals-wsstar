package com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl;

import java.net.URI;
import java.util.Date;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType.Description;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType.ErrorCode;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType.FaultCause;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfReader;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfWriter;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.implementor.WsrfbfModelFactory;

public class WsrfbfModelFactoryImpl implements WsrfbfModelFactory {

	private WsrfbfReaderImpl wsrfbfModelReader;
	private WsrfbfWriterImpl wsrfbfModelWriter;
	
	public WsrfbfModelFactoryImpl() {	
		this.wsrfbfModelReader = new WsrfbfReaderImpl();
		this.wsrfbfModelWriter = new WsrfbfWriterImpl();		
	}
	
	@Override
	public final BaseFaultType createWsrfbfModelBaseFaultType(
			Date timestamp) {
		return new BaseFaultTypeImpl(timestamp,null);
	}

	@Override
	public final Description createWsrfbfModelBaseFaultTypeDescription(String value) {

		return new BaseFaultTypeImpl.DescriptionImpl(value);
	}

	@Override
	public final ErrorCode createWsrfbfModelBaseFaultTypeErrorCode(URI dialect) {
		return new BaseFaultTypeImpl.ErrorCodeImpl(dialect);
	}

	@Override
	public final FaultCause createWsrfbfModelBaseFaultTypeFaultCause(Element faultCause) {
		return new BaseFaultTypeImpl.FaultCauseImpl(faultCause);
	}

	@Override
	public final WsrfbfReader getWsrfbfModelReader() {
		return this.wsrfbfModelReader;
	}

	@Override
	public final WsrfbfWriter getWsrfbfModelWriter() {
		return this.wsrfbfModelWriter;
	}

}
