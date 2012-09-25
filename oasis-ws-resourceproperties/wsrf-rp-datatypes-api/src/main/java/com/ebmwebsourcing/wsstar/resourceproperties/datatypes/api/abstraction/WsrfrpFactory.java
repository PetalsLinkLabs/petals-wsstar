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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction;

import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType.NewValues;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;

/**
 * Interface of the factory that must provide creation methods
 * of WS-ResourceProperties Types. Mainly expected methods are those
 * which help to build {@link GetResourcePropertyResponse},
 * {@link InvalidResourcePropertyQNameFaultType}, {@link UpdateType},
 * {@link UpdateResourceProperties}, {@link UpdateResourcePropertiesResponse}
 * typed objects. 
 * Also provide getters for {@link WsrfrpReader} and {WsrfrpWriter} objects.
 * 
 * NOTE : the factory is implemented following the "Bridge" design pattern
 * and the "WsrfrpFactory" class represents the "abstraction" part    
 * 
 * @author Thierry Déjean - EBM Websourcing
 *
 */
public interface WsrfrpFactory {
	
	/**
	 * Get a instance of {@link WsrfrpReader} class ,
	 * respect to "model implementation"
	 * 
	 * @return an instance of {@link WsrfrpReader} class
	 */
	WsrfrpReader getWsrfrpReader();
	
	/**
	 * Get a instance of {@link WsrfrpWriter} class,
	 * respect to "model implementation"
	 * 
	 * @return an instance of of {@link WsrfrpWriter} class
	 */
	WsrfrpWriter getWsrfrpWriter();
	
	/**
	 * create a minimal {@link GetResourcePropertyResponse} object, respect to
	 * "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @return new {@link GetResourcePropertyResponse} instance
	 */
	GetResourcePropertyResponse createGetResourcePropertyResponse();
	
	/**
	 * create a minimal {@link InvalidResourcePropertyQNameFaultType} object, respect to
	 * "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @return new {@link InvalidResourcePropertyQNameFaultType} instance
	 */
	InvalidResourcePropertyQNameFaultType createInvalidResourcePropertyQNameFaultType(Date timestamp);
	
	/**
	 * create a minimal {@link UpdateType} object, respect to "model implementation"
	 * with mandatory field (At least one property expected) 
	 * 
	 * @param content the list of properties to update
	 * @return new {@link UpdateType} instance
	 * @throws WsrfrpException 
	 */
	UpdateType createUpdateType(List<Element> content) throws WsrfrpException;
	
	/**
	 * create a minimal {@link UpdateResourceProperties} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 * 
	 * @param content the list of properties to update
	 * @return new {@link UpdateResourceProperties} instance
	 */
	UpdateResourceProperties createUpdateResourceProperties(UpdateType content);
	
	/**
	 * create a minimal {@link UpdateResourcePropertiesResponse} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 * 
	 * @param content the list of properties to update
	 * @return new {@link UpdateResourceProperties} instance
	 */
	UpdateResourcePropertiesResponse createUpdateResourcePropertiesResponse();
	
	/**
	 * create a minimal {@link ResourcePropertyChangeFailureType} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 * 
	 * @param value value of "isResotored" parameter to set
	 * @return new {@link ResourcePropertyChangeFailureType} instance
	 */
	ResourcePropertyChangeFailureType createResourcePropertyChangeFailureType(boolean value);
	
	/**
	 * create a minimal {@link ResourcePropertyChangeFailureType.CurrentValue} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 * 
	 * @param values currents values of requested property
	 * 
	 * @return new {@link ResourcePropertyChangeFailureType.CurrentValue} instance
	 */
	ResourcePropertyChangeFailureType.CurrentValue createResourcePropertyChangeFailureTypeCurrentValue(List<Element> values);	
	
	/**
	 * create a minimal {@link ResourcePropertyChangeFailureType.RequestedValue} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 * 
	 * @param values requested values of requested property
	 * 
	 * @return new {@link ResourcePropertyChangeFailureType.RequestedValue} instance
	 */
	ResourcePropertyChangeFailureType.RequestedValue createResourcePropertyChangeFailureTypeRequestedValue(List<Element> values);
	
	/**
	 * create a minimal {@link InvalidModificationFaultType} object, respect to "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @param value state of requested resource property changes 
	 * 
	 * @return new {@link InvalidModificationFaultType} instance
	 */ 
	InvalidModificationFaultType createInvalidModificationFaultType(Date timestamp,ResourcePropertyChangeFailureType value);
	
	/**
	 * create a minimal {@link UnableToModifyResourcePropertyFaultType} object, respect to "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @param value state of requested resource property changes 
	 * 
	 * @return new {@link UnableToModifyResourcePropertyFaultType} instance
	 */ 
	UnableToModifyResourcePropertyFaultType createUnableToModifyResourcePropertyFaultType(Date timestamp,ResourcePropertyChangeFailureType value);
	
	/**
	 * create a minimal {@link UpdateResourcePropertiesRequestFailedFaultType} object, respect to "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @param value state of requested resource property changes 
	 * 
	 * @return new {@link UpdateResourcePropertiesRequestFailedFaultType} instance
	 */ 
	UpdateResourcePropertiesRequestFailedFaultType createUpdateResourcePropertiesRequestFailedFaultType(Date timestamp,ResourcePropertyChangeFailureType value);
	
	/**
	 * create a minimal {@link ResourcePropertyValueChangeNotificationType} object, respect to "model implementation"
	 * 
	 * @param newValues new RP's values
	 * 
	 * @return new {@link ResourcePropertyValueChangeNotificationType} instance
	 */ 
	ResourcePropertyValueChangeNotificationType createResourcePropertyValueChangeNotificationType(NewValues newValues);
	
	/**
	 * create a minimal {@link ResourcePropertyValueChangeNotificationType.NewValues} object, respect to "model implementation"
	 * 
	 * @param newValues new RP's values
	 * 
	 * @return new {@link ResourcePropertyValueChangeNotificationType.NewValues} instance
	 */ 
	ResourcePropertyValueChangeNotificationType.NewValues createResourcePropertyValueChangeNotificationTypeNewValues(Element valueAsElt);
	
	/**
	 * create a minimal {@link ResourcePropertyValueChangeNotificationType.OldValues} object, respect to "model implementation"
	 * 
	 * @param oldValues new RP's values
	 * 
	 * @return new {@link ResourcePropertyValueChangeNotificationType.OldValues} instance
	 */ 
	ResourcePropertyValueChangeNotificationType.OldValues createResourcePropertyValueChangeNotificationTypeOldValues(Element valueAsElt);
}
