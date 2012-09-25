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
package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api;

import javax.xml.namespace.QName;

/**
 * Constants of WS-ResourceLifetime
 * 
 * @author Thierry Déjean - eBM WebSourcing
 */
public class WsrfrlConstants {

	protected WsrfrlConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	// ============== WS-Resource "XML Schema" constants ===========================
	
	public static final String WS_RESOURCE_LIFETIME_NAMESPACE_URI = "http://docs.oasis-open.org/wsrf/rl-2";

	public static final String WS_RESOURCE_LIFETIME_PREFIX = "wsrf-rl";
	
	public static final QName SET_TERMINATION_TIME_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "SetTerminationTime", WS_RESOURCE_LIFETIME_PREFIX);

	public static final QName REQUESTED_TERMINATION_TIME_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "RequestedTerminationTime", WS_RESOURCE_LIFETIME_PREFIX);
	
	public static final QName SET_TERMINATION_TIME_RESPONSE_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "SetTerminationTimeResponse", WS_RESOURCE_LIFETIME_PREFIX);

	public static final QName DESTROY_RESPONSE_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "DestroyResponse", WS_RESOURCE_LIFETIME_PREFIX);
	
	public static final QName DESTROY_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "Destroy", WS_RESOURCE_LIFETIME_PREFIX);
	
	public static final QName RESOURCE_NOT_DERTOYED_FAULT_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "ResourceNotDestroyedFault", WS_RESOURCE_LIFETIME_PREFIX);

	public static final QName UNABLE_TO_SET_TERMINATION_TIME_FAULT_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "UnableToSetTerminationTimeFault", WS_RESOURCE_LIFETIME_PREFIX);

	public static final QName TERMINATION_TIME_CHANGE_REJECTED_FAULT_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI, "TerminationTimeChangeRejectedFault", WS_RESOURCE_LIFETIME_PREFIX);

	public static final QName CURRENT_TIME_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI,"CurrentTime",WS_RESOURCE_LIFETIME_PREFIX);
	
	public static final QName SCHEDULED_RESOURCE_TERMINATION_RP_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI,"ScheduledResourceTerminationRP",WS_RESOURCE_LIFETIME_PREFIX);
	
	public static final QName TERMINATION_NOTIFICATION_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI,"TerminationNotification",WS_RESOURCE_LIFETIME_PREFIX);
	
	public static final QName TERMINATION_TIME_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI,"TerminationTime",WS_RESOURCE_LIFETIME_PREFIX);

	public static final QName TERMINATION_REASON_QNAME = new QName(WS_RESOURCE_LIFETIME_NAMESPACE_URI,"TerminationReason",WS_RESOURCE_LIFETIME_PREFIX);

	public static final String RESOURCE_TERMINATION_TOPIC_EXPRESSION_CONTENT = WS_RESOURCE_LIFETIME_PREFIX + ":ResourceTermination";
	
	// ============== WS-Resource "WSDL" constants ===========================
	
	 public static final String WS_RESOURCE_LIFETIME_WSDL_NAMESPACE_URI = "http://docs.oasis-open.org/wsrf/rlw-2";	 
	 
	 public static final String WS_RESOURCE_LIFETIME_WSDL_PREFIX = "wsrf-rlw";

}
