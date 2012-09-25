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
 * $id.java
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;

public interface WsrfrlReader {

	CurrentTime readCurrentTime(Document document) throws WsrfrlException;
	
	Destroy readDestroy(Document document) throws WsrfrlException;
	
	DestroyResponse readDestroyResponse(Document document) throws WsrfrlException;
	
	ResourceNotDestroyedFaultType readResourceNotDestroyedFaultType(Document document) throws WsrfrlException;
	
	ScheduledResourceTerminationRP readScheduledResourceTerminationRP(Document document) throws WsrfrlException;
	
	SetTerminationTime readSetTerminationTime(Document document) throws WsrfrlException;
	
	SetTerminationTimeResponse readSetTerminationTimeResponse(Document document) throws WsrfrlException;
	
	TerminationNotification readTerminationNotification(Document document) throws WsrfrlException;
	
	TerminationTime readTerminationTime(Document document) throws WsrfrlException;
	
	TerminationTimeChangeRejectedFaultType readTerminationTimeChangeRejectedFaultType(Document document) throws WsrfrlException;
	
	UnableToSetTerminationTimeFaultType readUnableToSetTerminationTimeFaultType(Document document) throws WsrfrlException;
}
