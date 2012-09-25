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
package com.ebmwebsourcing.wsstar.wsnb.services.impl.engines;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.wsnb.services.ICreatePullPoint;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.WsnbNotImplementedException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public class CreatePullPointEngine implements ICreatePullPoint {

	private Logger logger;
	//private PullPointEngine pullPtMgr;
	
	public CreatePullPointEngine(Logger logger, PullPointEngine manager) {
		super();
		this.logger = logger;		
		//this.pullPtMgr = manager;
	}
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.notification.service.basenotification.WsnbCreatePullPoint#createPullPoint(com.ebmwebsourcing.wsstar.notification.definition.basenotification.api.CreatePullPoint)
	 */
	public CreatePullPointResponse createPullPoint(
			com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPoint request)
			throws WsnbException, AbsWSStarFault {
		logger.log(Level.FINE, "performs a \"CreatePullPoint\" request ...");
		//TODO : implement this method !!!
		throw new WsnbNotImplementedException(this.getClass().getName(),"CreatePullPoint");
	}
}
