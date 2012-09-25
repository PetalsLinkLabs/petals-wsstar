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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.implementor;

import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidModificationFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidResourcePropertyQNameFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType.NewValues;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UnableToModifyResourcePropertyFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesRequestFailedFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpReader;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpWriter;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;

/**
 * Interface of the "model's factory" that must provide creation methods
 * of WS-ResourceProperties Types, called by the "RefinedAbstraction"(see "Bridge"
 * design pattern details for more details ).
 * Mainly expected methods are those which help to build {@link GetResourcePropertyResponse} and 
 * {@link InvalidResourcePropertyQNameFaultType} typed objects.
 * Also provide getters for {@link WsrfrpReader} and {WsrfrpWriter} objects.
 * 
 * NOTE : the "model's factory" is implemented following the "Bridge" design pattern
 * and the "WsrfrpModelFatory" class represents the "implementor" part    
 * 
 * @author Thierry Déjean - EBM Websourcing
 *
 */
public interface WsrfrpModelFactory {
	
	/**
	 * Get a instance of {@link WsrfrpReader} class ,
	 * respect to "model implementation"
	 * 
	 * @return an instance of of {@link WsrfrpReader} class
	 */
	WsrfrpReader getWsrfrpModelReader();
	
	/**
	 * Get a instance of {@link WsrfrpWriter} class,
	 * respect to "model implementation"
	 * 
	 * @return an instance of of {@link WsrfrpWriter} class
	 */
	WsrfrpWriter getWsrfrpModelWriter();
	
	/**
	 * create a minimal {@link GetResourcePropertyResponse} object, respect to
	 * "model implementation", with only mandatory fields set
	 *  
	 * @return an {@link GetResourcePropertyResponse} object
	 */
	GetResourcePropertyResponse createWsrfrpModelGetResourcePropertyResponse();
	
	/**
	 * create a minimal {@link InvalidResourcePropertyQNameFaultType} object, respect to
	 * "model implementation", with only mandatory fields set
	 *  
	 * @param timestamp of the fault to create, as a {@link Date} object
	 *    
	 * @return an {@link InvalidResourcePropertyQNameFaultType} object
	 */
	InvalidResourcePropertyQNameFaultType createWsrfrpModelInvalidResourcePropertyQNameFaultType(Date timestamp);
	
	/**
	 * create a minimal {@link UpdateType} object, respect to
	 * "model implementation", with only mandatory fields set
	 *  
	 * @param content list of {@link Element} object representing properties
	 *    
	 * @return an {@link UpdateType} object
	 * @throws WsrfrpException 
	 */
	UpdateType createWsrfrpModelUpdateType(List<Element> content) throws WsrfrpException;
	
	/**
	 * create a minimal {@link UpdateResourceProperties} object, respect to
	 * "model implementation", with only mandatory fields set
	 *  
	 * @param content {@link UpdateType} object representing properties list to update
	 *    
	 * @return an {@link UpdateResourceProperties} object
	 */
	UpdateResourceProperties createWsrfrpModelUpdateResourceProperties(UpdateType content);
	
	/**
	 * create a minimal {@link UpdateResourcePropertiesResponse} object, respect to
	 * "model implementation", with only mandatory fields set
	 *      
	 * @return an {@link UpdateResourcePropertiesResponse} object
	 */
	UpdateResourcePropertiesResponse createWsrfrpModelUpdateResourcePropertiesResponse();
	
	/**
	 * create a minimal {@link ResourcePropertyChangeFailureType} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 * 
	 * @param value value of "isResotored" parameter to set
	 * @return new {@link ResourcePropertyChangeFailureType} instance
	 */
	ResourcePropertyChangeFailureType createWsrfrpModelResourcePropertyChangeFailureType(boolean value);
	
	/**
	 * create a minimal {@link ResourcePropertyChangeFailureType.CurrentValue} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 *
	 * @param values current values of requested property
	 * 
	 * @return new {@link ResourcePropertyChangeFailureType.CurrentValue} instance
	 */
	ResourcePropertyChangeFailureType.CurrentValue createWsrfrpModelResourcePropertyChangeFailureTypeCurrentValue(List<Element> values);	
	
	/**
	 * create a minimal {@link ResourcePropertyChangeFailureType.RequestedValue} object, respect to "model implementation"
	 * with mandatory field (according to associated XML Schema) 
	 * 
	 * @param values requested values of requested property
	 * 
	 * @return new {@link ResourcePropertyChangeFailureType.RequestedValue} instance
	 */
	ResourcePropertyChangeFailureType.RequestedValue createWsrfrpModelResourcePropertyChangeFailureTypeRequestedValue(List<Element> values);
	
	/**
	 * create a minimal {@link InvalidModificationFaultType} object, respect to "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @param value state of requested resource property changes 
	 * 
	 * @return new {@link InvalidModificationFaultType} instance
	 */ 
	InvalidModificationFaultType createWsrfrpModelInvalidModificationFaultType(Date timestamp,ResourcePropertyChangeFailureType value);
	
	/**
	 * create a minimal {@link UnableToModifyResourcePropertyFaultType} object, respect to "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @param value state of requested resource property changes 
	 * 
	 * @return new {@link UnableToModifyResourcePropertyFaultType} instance
	 */ 
	UnableToModifyResourcePropertyFaultType createWsrfrpModelUnableToModifyResourcePropertyFaultType(Date timestamp,ResourcePropertyChangeFailureType value);
	
	/**
	 * create a minimal {@link UpdateResourcePropertiesRequestFailedFaultType} object, respect to "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @param value state of requested resource property changes 
	 * 
	 * @return new {@link UpdateResourcePropertiesRequestFailedFaultType} instance
	 */ 
	UpdateResourcePropertiesRequestFailedFaultType createWsrfrpModelUpdateResourcePropertiesRequestFailedFaultType(Date timestamp,ResourcePropertyChangeFailureType value);
	
	/**
	 * create a minimal {@link ResourcePropertyValueChangeNotificationType} object, respect to "model implementation"
	 * 
	 * @param newValues new RP's values
	 * 
	 * @return new {@link ResourcePropertyValueChangeNotificationType} instance
	 */ 
	ResourcePropertyValueChangeNotificationType createWsrfrpModelResourcePropertyValueChangeNotificationType(NewValues newValues);
	
	/**
	 * create a minimal {@link ResourcePropertyValueChangeNotificationType.NewValues} object, respect to "model implementation"
	 * 
	 * @param newValues new RP's values
	 * 
	 * @return new {@link ResourcePropertyValueChangeNotificationType.NewValues} instance
	 */ 
	ResourcePropertyValueChangeNotificationType.NewValues createWsrfrpModelResourcePropertyValueChangeNotificationTypeNewValues(Element valueAsElt);
	
	/**
	 * create a minimal {@link ResourcePropertyValueChangeNotificationType.OldValues} object, respect to "model implementation"
	 * 
	 * @param oldValues new RP's values
	 * 
	 * @return new {@link ResourcePropertyValueChangeNotificationType.OldValues} instance
	 */ 
	ResourcePropertyValueChangeNotificationType.OldValues createWsrfrpModelResourcePropertyValueChangeNotificationTypeOldValues(Element valueAsElt);
}
