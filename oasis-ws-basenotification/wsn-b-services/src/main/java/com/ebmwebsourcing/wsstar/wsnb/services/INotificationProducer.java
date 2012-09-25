/**
 * Copyright (c) 2010 EBM Websourcing, http://www.ebmwebsourcing.com/
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

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

/**
 * "NotificationProducer" service's interface
 * 
 * @author tdejean - eBM WebSourcing
 */
public interface INotificationProducer {
	 
    /**
     * Process a "Subscribe" request 
     * 
     * @param request the message exchange payload
     * @return a SubscribeResponse object as described in the WS-BaseNotification (p.15)  
     * @throws WSNotificationException 
     * @throws AbsWSStarFault 
     */
    SubscribeResponse subscribe(Subscribe request) throws WsnbException, AbsWSStarFault;
    
    /**
     * Get the last notification message sent respect to a given topic 
     * 
     * @param request the message exchange payload
     * @return a GetCurrentMessageResponse object as described in the WS-BaseNotification (p.22)  
     * @throws WSNotificationException
     */
    GetCurrentMessageResponse getCurrentMessage(GetCurrentMessage request) throws WsnbException, AbsWSStarFault;
}
