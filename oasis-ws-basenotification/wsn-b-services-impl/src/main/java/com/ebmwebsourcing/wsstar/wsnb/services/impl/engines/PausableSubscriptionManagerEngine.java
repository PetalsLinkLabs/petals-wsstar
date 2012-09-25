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

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.wsnb.services.IPausableSubscriptionManager;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.WsnbNotImplementedException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public class PausableSubscriptionManagerEngine extends SubscriptionManagerEngine implements IPausableSubscriptionManager{

	public PausableSubscriptionManagerEngine(Logger logger) {
		super(logger);
	}
		
	// ############################################################################################
	// 	----- Methods' implementation of WS-Notification PausableSubscriptionManager Interface ----
	// ############################################################################################
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.wsnb.services.IPausableSubscriptionManager#pauseSubscription(com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscription)
	 */
	public PauseSubscriptionResponse pauseSubscription(PauseSubscription request)
			throws WsnbException, AbsWSStarFault {
		logger.log(Level.FINE, "performs a \"PauseSubscription\" request ...");
		//TODO : implement this method !!!
		throw new WsnbNotImplementedException(this.getClass().getName(),"PauseSubscription");
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.wsnb.services.IPausableSubscriptionManager#resumeSubscription(com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscription)
	 */
	public ResumeSubscriptionResponse resumeSubscription(
			ResumeSubscription request) throws WsnbException,
			AbsWSStarFault {
		logger.log(Level.FINE, "performs a \"ResumeSubscription\" request ...");
		//TODO : implement this method !!!
		throw new WsnbNotImplementedException(this.getClass().getName(),"ResumeSubscription");
	}

	
}
