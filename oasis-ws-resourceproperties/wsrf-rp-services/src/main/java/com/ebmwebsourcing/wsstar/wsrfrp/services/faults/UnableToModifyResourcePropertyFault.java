package com.ebmwebsourcing.wsstar.wsrfrp.services.faults;

import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UnableToModifyResourcePropertyFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public class UnableToModifyResourcePropertyFault extends AbsWSStarFault {

	private static final long serialVersionUID = 1L;
	
	public UnableToModifyResourcePropertyFault(Locale lang, String descr, ResourcePropertyChangeFailureType changeFailureDetails) throws WsrfrpException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrpFactory.getInstance().createUnableToModifyResourcePropertyFaultType(new Date(),
				changeFailureDetails);
		this.init(lang,descr);
			
	}
	
	public UnableToModifyResourcePropertyFault(Document faultAsDocument) throws WsrfrpException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrpFactory.getInstance().getWsrfrpReader().readUnableToModifyResourcePropertyFaultType(faultAsDocument);
		this.init();
	}	

	@Override
	public Document getDocumentFragment() throws WsrfrpException {
		return RefinedWsrfrpFactory.getInstance().getWsrfrpWriter().writeUnableToModifyResourcePropertyFaultTypeAsDOM(
				(UnableToModifyResourcePropertyFaultType)this.wsnFault);
	}
}
