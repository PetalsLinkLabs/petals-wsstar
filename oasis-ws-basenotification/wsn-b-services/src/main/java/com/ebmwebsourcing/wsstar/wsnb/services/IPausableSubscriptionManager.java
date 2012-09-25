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
package com.ebmwebsourcing.wsstar.wsnb.services;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

/**
 * @author tdejean - eBM WebSourcing
 *
 */
public interface IPausableSubscriptionManager extends
		ISubscriptionManager {

	 /**
     * pause an existing subscription 
     * 
     * @param request
     * @return an PauseSubscriptionResponse object as described in the WS-BaseNotification (p.35)
     * @throws WSNotificationException
     */
    PauseSubscriptionResponse pauseSubscription(PauseSubscription request) throws WsnbException, AbsWSStarFault;
    
    /**
     * resume an existing subscription previously paused
     * 
     * @param request
     * @return an PauseSubscriptionResponse object as described in the WS-BaseNotification (p.36)
     * @throws WSNotificationException
     */
    ResumeSubscriptionResponse resumeSubscription(ResumeSubscription request) throws WsnbException, AbsWSStarFault;
    
}
