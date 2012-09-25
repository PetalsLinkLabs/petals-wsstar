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
package com.ebmwebsourcing.wsstar.wsnb.services;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

/**
 * "NotificationProducerRP" service's interface 
 * (Processing of WS-ResourceProperties related requests)
 * 
 * @author tdejean - eBM WebSourcing
 */
public interface INotificationProducerRP {

	/**
	 * Process a "GetResourceProperty" request
	 * 
	 * @param property the message exchange payload
	 * @return a "GetResourcePropertyResponse" object as described in the WS-ResourceProperties (p.15)
	 * @throws WsnbException
	 * @throws AbsWSStarFault
	 */
	GetResourcePropertyResponse getResourceProperty(QName property) throws WsnbException, AbsWSStarFault;
	
	/**
	 * Process a "UpdateResourceProperties" request
	 * 
	 * @param request the message exchange payload
	 * @return a "UpdateResourceProperties" object as described in the WS-ResourceProperties (p.32-33)
	 * @throws WsnbException
	 * @throws AbsWSStarFault
	 */
	UpdateResourcePropertiesResponse updateResourceProperties(UpdateResourceProperties request) throws WsnbException, AbsWSStarFault;
	
}
