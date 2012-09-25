package com.ebmwebsourcing.wsstar.wsrfrp.services.faults;

import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidResourcePropertyQNameFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public class InvalidResourcePropertyQNameFault extends AbsWSStarFault {

	private static final long serialVersionUID = 1L;
	
	public InvalidResourcePropertyQNameFault(Locale lang, String descr) throws WsrfrpException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrpFactory.getInstance().createInvalidResourcePropertyQNameFaultType(new Date());
		this.init(lang,descr);
			
	}
	
	public InvalidResourcePropertyQNameFault(Document faultAsDocument) throws WsrfrpException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrpFactory.getInstance().getWsrfrpReader().readInvalidResourcePropertyQNameFaultType(faultAsDocument);
		this.init();
		}	

	@Override
	public Document getDocumentFragment() throws WsrfrpException {
			return RefinedWsrfrpFactory.getInstance().getWsrfrpWriter().writeInvalidResourcePropertyQNameFaultTypeAsDOM(
					(InvalidResourcePropertyQNameFaultType)this.wsnFault);	
	}

}
