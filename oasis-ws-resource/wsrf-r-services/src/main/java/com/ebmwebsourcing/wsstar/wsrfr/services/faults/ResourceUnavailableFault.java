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
 */package com.ebmwebsourcing.wsstar.wsrfr.services.faults;

import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnavailableFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.refinedabstraction.RefinedWsrfrFactory;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.utils.WsrfrException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

/**
 * 
 * @author tdejean - eBM WebSourcing
 *
 */
public class ResourceUnavailableFault extends AbsWSStarFault {

	private static final long serialVersionUID = 1L;
		
	public ResourceUnavailableFault(Locale lang, String descr) throws WsrfrException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrFactory.getInstance().createResourceUnavailableFaultType(new Date());
		this.init(lang,descr);			
	}
	
	public ResourceUnavailableFault(Document faultAsDocument) throws WsrfrException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsrfrFactory.getInstance().getWsrfrReader().readResourceUnavailableFaultType(faultAsDocument);
		this.init();
		
	}	

	@Override
	public final Document getDocumentFragment() throws WsrfrException {
		return RefinedWsrfrFactory.getInstance().getWsrfrWriter().writeResourceUnavailableFaultTypeAsDOM(
				(ResourceUnavailableFaultType)this.wsnFault);		
	}
}
