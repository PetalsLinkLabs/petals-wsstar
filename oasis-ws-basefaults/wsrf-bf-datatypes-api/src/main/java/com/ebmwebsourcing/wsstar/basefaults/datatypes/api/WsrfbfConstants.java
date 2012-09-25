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
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api;

import javax.xml.namespace.QName;

/**
 * Constants of WS-BaseFaults
 * 
 * @author Thierry Déjean - eBM WebSourcing
 */
public class WsrfbfConstants {
	
	protected WsrfbfConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	// ============== WS-BaseFaults "XML Schema" constants ===========================
	/**
     * WS-BASEFAULTS "normative" namespace
     */
	public static final String WS_BASE_FAULTS_NAMESPACE_URI = "http://docs.oasis-open.org/wsrf/bf-2";

	/**
     * WS-BASEFAULTS commonly used prefix
     */
	public static final String WS_BASE_FAULTS_PREFIX = "wsrf-bf";
	
	/**
     * WS-BASEFAULTS XML schema QNames
     */
	public static final QName BASE_FAULT_QNAME = new QName(WS_BASE_FAULTS_NAMESPACE_URI, "BaseFault", WS_BASE_FAULTS_PREFIX);

	// ============== WS-BaseFaults "WSDL" constants : NO DEFINITION !!! =============
	
}
