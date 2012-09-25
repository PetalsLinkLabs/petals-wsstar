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
package com.ebmwebsourcing.wsstar.resource.datatypes.api;

import javax.xml.namespace.QName;

/**
 * Constants of WS-Resource
 * 
 * @author Thierry Déjean - eBM WebSourcing
 */
public class WsrfrConstants {
	
	protected WsrfrConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	// ============== WS-Resource "XML Schema" constants ===========================
	
	public static final String WS_RESOURCE_NAMESPACE_URI = "http://docs.oasis-open.org/wsrf/r-2";

	public static final String WS_RESOURCE_PREFIX = "wsrf-r";
	
	public static final QName RESOURCE_UNKNOWN_FAULT_QNAME = new QName(WS_RESOURCE_NAMESPACE_URI, "ResourceUnknownFault", WS_RESOURCE_PREFIX);

	public static final QName RESOURCE_UNAVAILABLE_FAULT_QNAME = new QName(WS_RESOURCE_NAMESPACE_URI, "ResourceUnavailableFault", WS_RESOURCE_PREFIX);

	// ============== WS-Resource "WSDL" constants ===========================
	
	 public static final String WS_RESOURCE_WSDL_NAMESPACE_URI = "http://docs.oasis-open.org/wsrf/rw-2";	 
	 
	 public static final String WS_RESOURCE_WSDL_PREFIX = "wsrf-rw";
}
