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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils;

import java.util.Locale;

/**
 * 
 * @author tdejean - eBM WebSourcing
 *
 */
public class WsrfrpFaultMessageContentConstants {

	protected WsrfrpFaultMessageContentConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	public static final Locale FAULT_DESCRIPTION_LANGUAGE = Locale.ENGLISH;
	
	/**
	 * 	~~~~~ WS-ResourceProperties "GetResourceProperty" fault messages descriptions ~~~~~
	 * 				~~~~~ (WS-ResourceProperties - p.15-16) ~~~~~
	 * 
	 *  InvalidResourcePropertyQNameFault
	 *	   The QName in the request message did not correspond to a resource property
	 *    element of the WS-Resource referred to in the request message
   	 */
	public static class WsrfrpGetResourcePropertyFaultDescriptions {
		
		protected WsrfrpGetResourcePropertyFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String INVALID_RESOURCE_PROPERTY_QNAME_FAULT_DESC = 
			"The QName in the request message did not correspond to a resource property " +
			"element of the WS-Resource referred to in the request message";
		
	}
	
	/**
	 * ~~~~~ WS-ResourceProperties "UpdateResourceProperties" fault messages descriptions ~~~~~
	 * 					~~~~~ (WS-ResourceProperties - p33) ~~~~~
	 * 
	 * InvalidModificationFault
	 * 		The contents of the UpdateResourceProperties request component cause the resource
	 * 		properties document to no longer be able to validate.
	 * 
	 * UnableToModifyResourcePropertyFault
	 * 		A resource property identified by the UpdateResourceProperties request is not
	 * 		modifiable.
	 * 
	 * InvalidResourcePropertyQNameFault
	 * 		A resource property QName does not identify a resource property.
	 * 
	 * UpdateResourcePropertiesRequestFailedFault
	 * 		The UpdateResourceProperties request failed for some reason.
	 */
	public static class WsrfrpUpdateResourcePropertiesFaultDescriptions {
		
		protected WsrfrpUpdateResourcePropertiesFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String INVALID_MODIFICATION_FAULT_DESC = 
			"The contents of the UpdateResourceProperties request component cause the resource " +
			" properties document to no longer be able to validate";
		
		public static final String UNABLE_TO_MODIFY_RESOURCE_PROPERTY_FAULT_DESC =
			"A Resource property identified by the UpdateResourceProperties request is not " +
			"modifiable";
		
		public static final String INVALID_RESOURCE_PROPERTY_QNAME_FAULT_DESC = 
			"A resource property QName does not identify a resource property";
		
		public static final String UPDATE_RESOURCE_PROPERTIES_REQUEST_FAILED_FAULT_DESC = 
			"The UpdateResourceProperties request failed for some reason";
				
	}
		
}
