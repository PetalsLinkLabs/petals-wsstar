/**
 * Copyright (c) 2011 EBM Websourcing, http://www.ebmwebsourcing.com/
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * -------------------------------------------------------------------------
 * $Id$
 * -------------------------------------------------------------------------
 */package com.ebmwebsourcing.wsstar.wsrfrl.services.faults;

import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.UnableToSetTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.refinedabstraction.RefinedWsrfrlFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

/**
 * 
 * @author tdejean - eBM WebSourcing
 */
public class UnableToSetTerminationTimeFault extends AbsWSStarFault {

	private static final long serialVersionUID = 1L;
		
	public UnableToSetTerminationTimeFault(Locale lang, String descr) throws WsrfrlException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrlFactory.getInstance().createUnableToSetTerminationTimeFaultType(new Date());
		this.init(lang,descr);			
	}
	
	public UnableToSetTerminationTimeFault(Document faultAsDocument) throws WsrfrlException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrlFactory.getInstance().getWsrfrlReader().readUnableToSetTerminationTimeFaultType(faultAsDocument);
		this.init();	
	}	

	@Override
	public final Document getDocumentFragment() throws WsrfrlException {
		return RefinedWsrfrlFactory.getInstance().getWsrfrlWriter().writeUnableToSetTerminationTimeFaultTypeAsDOM(
				(UnableToSetTerminationTimeFaultType)this.wsnFault);
	}
}
