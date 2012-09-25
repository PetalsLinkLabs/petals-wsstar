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
 * Interface of the Reader that must provide methods to "unmarshall" WS-ResourceProperties
 * types from common representation formats : {@link Document},{@link File},...     
 * These methods can be seen as "input methods"
 * 
 * @author Thierry Déjean - EBM Websourcing
 *
 */
public interface WsrfrpReader {
	
	/**
	 * Unmarshall a {@link GetResourceProperty} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	QName readGetResourceProperty(Document document) throws WsrfrpException;

	/**
	 * Unmarshall a {@link GetResourcePropertyResponse} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	GetResourcePropertyResponse readGetResourcePropertyResponse(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link InvalidResourcePropertyQNameFaultType} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the fault type
	 * @return the unmarshalled fault type to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	InvalidResourcePropertyQNameFaultType readInvalidResourcePropertyQNameFaultType(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link UpdateType} object from its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the object
	 * @return the unmarshalled UpdateType} to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	UpdateType readUpdateType(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link UpdateResourceProperties} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	UpdateResourceProperties readUpdateResourceProperties(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link UpdateResourcePropertiesResponse} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	UpdateResourcePropertiesResponse readUpdateResourcePropertiesResponse(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link InvalidModificationFaultType} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	InvalidModificationFaultType readInvalidModificationFaultType(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link UnableToModifyResourcePropertyFaultType} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	UnableToModifyResourcePropertyFaultType readUnableToModifyResourcePropertyFaultType(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link UpdateResourcePropertiesRequestFailedFaultType} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	UpdateResourcePropertiesRequestFailedFaultType readUpdateResourcePropertiesRequestFailedFaultType(Document document) throws WsrfrpException;
	
	/**
	 * Unmarshall a {@link ResourcePropertyValueChangeNotificationType} object from
	 * its {@link Document} representation.
	 * 
	 * @param document the {@link Document} representation of the message exchange payload
	 * @return the unmarshalled message exchange payload to the given {@link Document} representation
	 * @throws WsrfrpException
	 */
	ResourcePropertyValueChangeNotificationType readResourcePropertyValueChangeNotificationType(Document document) throws WsrfrpException;
	
}
