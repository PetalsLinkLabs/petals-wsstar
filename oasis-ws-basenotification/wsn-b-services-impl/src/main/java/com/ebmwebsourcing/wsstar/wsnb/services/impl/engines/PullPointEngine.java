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

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPoint;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessages;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessagesResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.wsnb.services.IPullPoint;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.WsnbNotImplementedException;
/**
 * @author tdejean - eBM WebSourcing
 * 
 */
public class PullPointEngine implements IPullPoint {

	private Logger logger;
	
	public PullPointEngine(Logger logger) {
		super();
		this.logger = logger;		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.notification.service.basenotification.WsnbPullPoint#destroyPullPoint(com.ebmwebsourcing.wsstar.notification.service.test.wsnotification.base.DestroyPullPoint)
	 */
	public DestroyPullPointResponse destroyPullPoint(DestroyPullPoint request)
			throws WsnbException {
		logger.log(Level.FINE, "performs a \"DestroyPullPoint\" request ...");
		//TODO : implement this method !!!
		throw new WsnbNotImplementedException(this.getClass().getName(),"DestroyPullPoint");
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.notification.service.basenotification.WsnbPullPoint#getMessages(com.ebmwebsourcing.wsstar.notification.service.test.wsnotification.base.GetMessagesResponse)
	 */
	public GetMessagesResponse getMessages(GetMessages request)
			throws WsnbException {
		logger.log(Level.FINE, "performs a \"GetMessages\" request ...");
		throw new WsnbNotImplementedException(this.getClass().getName(),"GetMessages");
	}

}
