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
 * $id.java
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction;

import javax.xml.namespace.QName;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;

/**
 * Interface of the Writer that must provide methods to "marshall" WS-ResourceProperties
 * types to common representation formats : {@link Document},{@link File},....     
 * These methods can be seen as "output methods"
 * 
 * @author Thierry Déjean - EBM Websourcing
 *
 */
public interface WsrfrpWriter {
	
	/**
	 * marshall a ResourceProperty {@link QName} object to a {@link Document} representation object
	 * of the GetResourceProperty message exchange.
	 * 
	 * @param propertyName the {@link QName} object to marshall
	 * @return an {@link Document} representation object of the GetResourceProperty
	 * 		 message exchange payload
	 * @throws WsrfrpException
	 */
	Document writeGetResourcePropertyAsDOM(QName propertyName) throws WsrfrpException;
	
	/**
	 * marshall a {@link GetResourcePropertyResponse} object to a {@link Document} representation object
	 * 
	 * @param payload the {@link GetResourcePropertyResponse} object to marshall
	 * @return an {@link Document} representation object of the GetResourcePropertyResponse
	 *  	message exchange payload
	 * @throws WsrfrpException
	 */
	Document writeGetResourcePropertyResponseAsDOM(GetResourcePropertyResponse payload) throws WsrfrpException;
	
	/**
	 * marshall a {@link InvalidResourcePropertyQNameFaultType} object to a {@link Document} representation object
	 * 
	 * @param value the {@link InvalidResourcePropertyQNameFaultType} object to marshall
	 * @return an {@link Document} representation object of "fault type" object
	 * 				
	 * @throws WsrfrpException
	 */
	Document writeInvalidResourcePropertyQNameFaultTypeAsDOM(InvalidResourcePropertyQNameFaultType value) throws WsrfrpException;
		
	/**
	 * marshall a {@link UpdateType} object to a {@link Document} representation object
	 * 
	 * @param instance the {@link UpdateType} object to marshall
	 * @return an {@link Document} representation object of the UpdateType object
	 * 
	 * @throws WsrfrpException
	 */
	Document writeUpdateTypeAsDOM(UpdateType instance) throws WsrfrpException;
	
	/**
	 * marshall a {@link UpdateResourceProperties} object to a {@link Document} representation object
	 * 
	 * @param payload the {@link UpdateResourceProperties} object to marshall
	 * @return an {@link Document} representation object of the UpdateResourceProperties
	 *  	message exchange payload
	 * @throws WsrfrpException
	 */
	Document writeUpdateResourcePropertiesAsDOM(UpdateResourceProperties payload) throws WsrfrpException;
	
	/**
	 * marshall a {@link UpdateResourcePropertiesResponse} object to a {@link Document} representation object
	 * 
	 * @param payload the {@link UpdateResourcePropertiesResponse} object to marshall
	 * @return an {@link Document} representation object of the UpdateResourcePropertiesResponse
	 *  	message exchange payload
	 * @throws WsrfrpException
	 */
	Document writeUpdateResourcePropertiesResponseAsDOM(UpdateResourcePropertiesResponse payload) throws WsrfrpException;
	
	/**
	 * marshall a {@link InvalidModificationFaultType} object to a {@link Document} representation object
	 * 
	 * @param value the {@link InvalidModificationFaultType} object to marshall
	 * @return an {@link Document} representation object of "fault type" object
	 * 				
	 * @throws WsrfrpException
	 */
	Document writeInvalidModificationFaultTypeAsDOM(InvalidModificationFaultType fault) throws WsrfrpException;
	
	/**
	 * marshall a {@link UnableToModifyResourcePropertyFaultType} object to a {@link Document} representation object
	 * 
	 * @param value the {@link UnableToModifyResourcePropertyFaultType} object to marshall
	 * @return an {@link Document} representation object of "fault type" object
	 * 				
	 * @throws WsrfrpException
	 */
	Document writeUnableToModifyResourcePropertyFaultTypeAsDOM(UnableToModifyResourcePropertyFaultType fault) throws WsrfrpException;
	
	/**
	 * marshall a {@link UpdateResourcePropertiesRequestFailedFaultType} object to a {@link Document} representation object
	 * 
	 * @param value the {@link UpdateResourcePropertiesRequestFailedFaultType} object to marshall
	 * @return an {@link Document} representation object of "fault type" object
	 * 				
	 * @throws WsrfrpException
	 */
	Document writeUpdateResourcePropertiesRequestFailedFaultTypeAsDOM(UpdateResourcePropertiesRequestFailedFaultType fault) throws WsrfrpException;
	
	/**
	 * marshall a {@link ResourcePropertyValueChangeNotificationType} object to a {@link Document} representation object
	 * 
	 * @param value the {@link ResourcePropertyValueChangeNotificationType} object to marshall
	 * @return an {@link Document} representation object of "fault type" object
	 * 				
	 * @throws WsrfrpException
	 */
	Document writeResourcePropertyValueChangeNotificationTypeAsDOM(ResourcePropertyValueChangeNotificationType value) throws WsrfrpException;

}
