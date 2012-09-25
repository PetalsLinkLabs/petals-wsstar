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

import java.util.Date;

import javax.xml.datatype.Duration;

public interface WsrfrlFactory {
	
	CurrentTime createCurrentTime(Date value);
	
	Destroy createDestroy();
	
	DestroyResponse createDestroyResponse();
	
	ResourceNotDestroyedFaultType createResourceNotDestroyedFaultType(Date timestamp);
	
	ScheduledResourceTerminationRP createScheduledResourceTerminationRP(CurrentTime curTime, TerminationTime termTime);
	
	SetTerminationTime createSetTerminationTime(Date value);
	
	SetTerminationTime createSetTerminationTime(Duration value);
	
	SetTerminationTimeResponse createSetTerminationTimeResponse(Date curTime, Date newTermTime);
	
	TerminationNotification createTerminationNotification(Date termTime);
	
	TerminationTime createTerminationTime(Date value);
	
	TerminationTimeChangeRejectedFaultType createTerminationTimeChangeRejectedFaultType(Date timestamp);
	
	UnableToSetTerminationTimeFaultType createUnableToSetTerminationTimeFaultType(Date timestamp);
	
	WsrfrlReader getWsrfrlReader();
	
	WsrfrlWriter getWsrfrlWriter();

}
