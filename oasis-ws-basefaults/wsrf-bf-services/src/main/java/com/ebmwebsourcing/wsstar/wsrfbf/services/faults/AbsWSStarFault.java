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
 */
package com.ebmwebsourcing.wsstar.wsrfbf.services.faults;

import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType.Description;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction.RefinedWsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;

/**
 * 
 * @author tdejean - eBM WebSourcing
 */
public abstract class AbsWSStarFault extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected BaseFaultType wsnFault;
	
	public AbsWSStarFault() throws WsrfbfException {	
		super();
		try {
			this.wsnFault = RefinedWsrfbfFactory.getInstance().createBaseFaultType(new Date());
		}catch (WsrfbfException e) {
			throw new WsrfbfException(e);
		}
	}
	
	public AbsWSStarFault(Document faultAsDocument) throws WsrfbfException {
		super();
		try {
			this.wsnFault = RefinedWsrfbfFactory.getInstance().getWsrfbfReader().readBaseFaultType(faultAsDocument);
			this.init();	
		} catch (WsrfbfException e) {
			throw new WsrfbfException(e);
		}		
	}
	
	public AbsWSStarFault(BaseFaultType fault) {
		super();
		this.wsnFault = fault;
	}
	
	protected final void init() throws WsrfbfException {
		this.wsnFault.setTimestamp(new Date());
	}
	
	protected final void init(Locale lang, String descContent) throws WsrfbfException {
			
		Description description;
		try {
			description = RefinedWsrfbfFactory.getInstance().createBaseFaultTypeDescription(null);			
		} catch (WsrfbfException e) {
			throw new WsrfbfException(e);
		}
		description.setLang(lang);
		description.setValue(descContent);
		if (this.wsnFault.getTimestamp() == null) {
			this.init();
		}
	}
		
	public final void setWsnFault(BaseFaultType wsnFault) {
		this.wsnFault = wsnFault;
	}
	
	public final BaseFaultType getWsnFault() {
		return wsnFault;
	}
	
	public abstract Document getDocumentFragment() throws Exception ;

}
