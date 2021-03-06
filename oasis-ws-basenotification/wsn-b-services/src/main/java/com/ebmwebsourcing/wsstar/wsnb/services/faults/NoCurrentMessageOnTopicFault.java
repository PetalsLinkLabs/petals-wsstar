/**
 * Copyright (c) 2009 EBM Websourcing, http://www.ebmwebsourcing.com/
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
package com.ebmwebsourcing.wsstar.wsnb.services.faults;

import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NoCurrentMessageOnTopicFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public class NoCurrentMessageOnTopicFault extends AbsWSStarFault {

	private static final long serialVersionUID = 1L;

	public NoCurrentMessageOnTopicFault(Locale lang, String descr) throws WsnbException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsnbFactory.getInstance().createNoCurrentMessageOnTopicFaultType(new Date());
		this.init(lang, descr);
	}
	public NoCurrentMessageOnTopicFault(Document faultAsDocument) throws WsnbException, WsrfbfException {
		super();
		this.wsnFault = RefinedWsnbFactory.getInstance().getWsnbReader().readNoCurrentMessageOnTopicFaultType(faultAsDocument);
		this.init();

	}

	@Override
	public final Document getDocumentFragment() throws WsnbException {
	return RefinedWsnbFactory.getInstance().getWsnbWriter().writeNoCurrentMessageOnTopicFaultTypeAsDOM(
			(NoCurrentMessageOnTopicFaultType) this.wsnFault);
	}
}
