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
package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.implementor;


import java.util.Date;

import javax.xml.datatype.Duration;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ResourceNotDestroyedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ScheduledResourceTerminationRP;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationNotification;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTimeChangeRejectedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.UnableToSetTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlReader;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlWriter;

public interface WsrfrlModelFactory {

	CurrentTime createWsrfrlModelCurrentTime(Date value);
	
	Destroy createWsrfrlModelDestroy();
	
	DestroyResponse createWsrfrlModelDestroyResponse();
	
	ResourceNotDestroyedFaultType createWsrfrlModelResourceNotDestroyedFaultType(Date timestamp);
	
	ScheduledResourceTerminationRP createWsrfrlModelScheduledResourceTerminationRP(CurrentTime curTime, TerminationTime termTime);
	
	SetTerminationTime createWsrfrlModelSetTerminationTime(Date value);
	
	SetTerminationTime createWsrfrlModelSetTerminationTime(Duration value);
	
	SetTerminationTimeResponse createWsrfrlModelSetTerminationTimeResponse(Date curTime, Date newTermTime);
	
	TerminationNotification createWsrfrlModelTerminationNotification(Date termTime);
	
	TerminationTime createWsrfrlModelTerminationTime(Date value);

	TerminationTimeChangeRejectedFaultType createWsrfrlModelTerminationTimeChangeRejectedFaultType(Date timestamp);
	
	UnableToSetTerminationTimeFaultType createWsrfrlModelUnableToSetTerminationTimeFaultType(Date timestamp);
	
	WsrfrlReader getWsrfrlModelReader();
	
	WsrfrlWriter getWsrfrlModelWriter();

}
