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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api;

import javax.xml.namespace.QName;

/**
 * Constants of WS-ResourceProperties
 * 
 * @author Thierry Déjean - eBM WebSourcing
 */
public class WsrfrpConstants {

	protected WsrfrpConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	// ============== WS-Resource "XML Schema" constants ===========================
	
	public static final String WS_RESOURCE_PROPERTIES_NAMESPACE_URI = "http://docs.oasis-open.org/wsrf/rp-2";

	public static final String WS_RESOURCE_PROPERTIES_PREFIX = "wsrf-rp";
	
	public static final QName GET_RESOURCE_PROPERTY_RESPONSE_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "GetResourcePropertyResponse", WS_RESOURCE_PROPERTIES_PREFIX);
	
	public static final QName INVALID_RESOURCE_PROPERTY_QNAME_FAULT_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "InvalidResourcePropertyQNameFault", WS_RESOURCE_PROPERTIES_PREFIX);

	public static final QName UPDATE_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "Update", WS_RESOURCE_PROPERTIES_PREFIX);
	
	public static final QName UPDATE_RESOURCE_PROPERTIES_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "UpdateResourceProperties", WS_RESOURCE_PROPERTIES_PREFIX);
	
	public static final QName UPDATE_RESOURCE_PROPERTIES_RESPONSE_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "UpdateResourcePropertiesResponse", WS_RESOURCE_PROPERTIES_PREFIX);
	
	public static final QName INVALID_MODIFICATION_QNAME_FAULT_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "InvalidModificationFault", WS_RESOURCE_PROPERTIES_PREFIX);
	
	public static final QName UNABLE_TO_MODIFY_RESOURCE_PROPERTY_FAULT_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "UnableToModifyResourcePropertyFault", WS_RESOURCE_PROPERTIES_PREFIX);

	public static final QName UPDATE_RESOURCE_PROPERTIES_REQUEST_FAILED_FAULT_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI, "UpdateResourcePropertiesRequestFailedFault", WS_RESOURCE_PROPERTIES_PREFIX);

	public static final QName RESOURCE_PROPERTY_VALUE_CHANGE_NOTIFICATION_QNAME = new QName(WS_RESOURCE_PROPERTIES_NAMESPACE_URI,"ResourcePropertyValueChangeNotification",WS_RESOURCE_PROPERTIES_PREFIX);
	// ============== WS-Resource "WSDL" constants ===========================
	
	 public static final String WS_RESOURCE_PROPERTIES_WSDL_NAMESPACE_URI = "http://docs.oasis-open.org/wsrf/rpw-2";	 
	 
	 public static final String WS_RESOURCE_PROPERTIES_WSDL_PREFIX = "wsrf-rpw";

}
