package com.ebmwebsourcing.wsstar.wsrfrp.services.faults;

import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidModificationFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public class InvalidModificationFault extends AbsWSStarFault {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidModificationFault(Locale lang, String descr, ResourcePropertyChangeFailureType changeFailureDetails) throws WsrfrpException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrpFactory.getInstance().createInvalidModificationFaultType(new Date(), changeFailureDetails);
		this.init(lang,descr);			
	}
	
	public InvalidModificationFault(Document faultAsDocument) throws WsrfrpException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrpFactory.getInstance().getWsrfrpReader().readInvalidModificationFaultType(faultAsDocument);
		this.init();		
	}	

	@Override
	public Document getDocumentFragment() throws Exception {
		try {
			return RefinedWsrfrpFactory.getInstance().getWsrfrpWriter().writeInvalidModificationFaultTypeAsDOM(
					(InvalidModificationFaultType)this.wsnFault);
		} catch (WsrfrpException e) {
			throw new WsrfrpException(e);
		}
	}

}
