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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction;

import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidModificationFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidResourcePropertyQNameFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType.CurrentValue;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType.RequestedValue;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType.NewValues;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType.OldValues;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UnableToModifyResourcePropertyFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesRequestFailedFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpReader;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpWriter;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.implementor.WsrfrpModelFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;

/**
 * Implementation, following the "Singleton" design pattern, of
 *  the {@link WsrfrpFactory} factory interface.
 * 
 * NOTE : "RefinedWsrfrpFactory" class is the "refined abstraction"
 * part of the "Bridge" design pattern.
 * 
 * @author Thierry Déjean - EBM Websourcing
 *
 */
public class RefinedWsrfrpFactory implements WsrfrpFactory {

	private WsrfrpModelFactory model;
	
	private RefinedWsrfrpFactory() {
		
	}
	
	//	################# EXPERITMENTAL SINGLETON PATTERN FORM ######################
	
	
	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	 * or the first access to SingletonHolder.INSTANCE, not before.
	 */
	private static final class WsrfrpFactoryHolder { 		
		private static final RefinedWsrfrpFactory INSTANCE = new RefinedWsrfrpFactory();
		
		private WsrfrpFactoryHolder(){}
	}
	
	/**
	 * Get the singleton instance of the class once the model's factory
	 * has been initialized.
	 * 
	 * @return the singleton instance of the class
	 * @throws WsrfrpException
	 */
	public static WsrfrpFactory getInstance() throws WsrfrpException {
		RefinedWsrfrpFactory singleton = WsrfrpFactoryHolder.INSTANCE;
		if (singleton.model == null){ 			
			throw new WsrfrpException("\n\t/!\\ WARNING /!\\\n" +
					"The WsrfrpModelFactory have not been initialized !!!\n" +
					"Please create a \"WsrfrpModelFactory\" instance and \n" +
					"call the \"getInstance(WsrfrpModelFactory)\" method instead." +
					"\n\t/!\\ WARNING /!\\\n");
		}
		return singleton;		
	}	
	
	/**
	 * Initialize model's factory and get the singleton instance of the class
	 * 
	 * @param modelFactory then model's factory implementation.
	 * @return the singleton instance of the class
	 */
	public static WsrfrpFactory getInstance(WsrfrpModelFactory modelFactory) {		
		RefinedWsrfrpFactory singleton = WsrfrpFactoryHolder.INSTANCE;
		singleton.model = modelFactory;		 	
		return singleton;		
	}	 		
	//	#######################################################################
	
	/**
	 * Get the model's factory implementation used.
	 * In "Bridge" design pattern language it is the 
	 * "ConcreteImplementor" 
	 * 
	 * @return the model's factory implementation used
	 */	
	public WsrfrpModelFactory getModel() {
		return this.model;
	}
	
	/**
	 * Set the model's factory implementation to used.
	 * In "Bridge" design pattern language it is the 
	 * "ConcreteImplementor" 
	 * @param modelFactory an instance of model's factory implementation
	 */
	public void setModel(WsrfrpModelFactory model) {
		this.model = model;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#getWsrfrpReader()
	 */
	@Override
	public WsrfrpReader getWsrfrpReader() {
		return this.model.getWsrfrpModelReader();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#getWsrfrpWriter()
	 */
	@Override
	public WsrfrpWriter getWsrfrpWriter() {
		return this.model.getWsrfrpModelWriter();
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createGetResourcePropertyResponse()
	 */
	@Override
	public GetResourcePropertyResponse createGetResourcePropertyResponse() {
		return this.model.createWsrfrpModelGetResourcePropertyResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createInvalidResourcePropertyQNameFaultType(java.util.Date)
	 */
	@Override
	public InvalidResourcePropertyQNameFaultType createInvalidResourcePropertyQNameFaultType(Date timestamp) {
		return this.model.createWsrfrpModelInvalidResourcePropertyQNameFaultType(timestamp);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createUpdateType(java.util.List)
	 */
	@Override
	public UpdateType createUpdateType(List<Element> content) throws WsrfrpException {		
		return this.model.createWsrfrpModelUpdateType(content);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createUpdateResourceProperties(com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType)
	 */
	@Override
	public UpdateResourceProperties createUpdateResourceProperties(
			UpdateType content) {
		return this.model.createWsrfrpModelUpdateResourceProperties(content);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createUpdateResourcePropertiesResponse()
	 */
	@Override
	public UpdateResourcePropertiesResponse createUpdateResourcePropertiesResponse() {
		return this.model.createWsrfrpModelUpdateResourcePropertiesResponse();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createResourcePropertyChangeFailureType(boolean)
	 */
	@Override
	public ResourcePropertyChangeFailureType createResourcePropertyChangeFailureType(
			boolean value) {
		return this.model.createWsrfrpModelResourcePropertyChangeFailureType(value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createResourcePropertyChangeFailureTypeCurrentValue(java.util.List)
	 */
	@Override
	public CurrentValue createResourcePropertyChangeFailureTypeCurrentValue(List<Element> values) {
		return this.model.createWsrfrpModelResourcePropertyChangeFailureTypeCurrentValue(values);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createResourcePropertyChangeFailureTypeRequestedValue(java.util.List)
	 */
	@Override
	public RequestedValue createResourcePropertyChangeFailureTypeRequestedValue(List<Element> values) {
		return this.model.createWsrfrpModelResourcePropertyChangeFailureTypeRequestedValue(values);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createInvalidModificationFaultType(java.util.Date, com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType)
	 */
	@Override
	public InvalidModificationFaultType createInvalidModificationFaultType(
			Date timestamp, ResourcePropertyChangeFailureType value) {
		return this.model.createWsrfrpModelInvalidModificationFaultType(timestamp, value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createUnableToModifyResourcePropertyFaultType(java.util.Date, com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType)
	 */
	@Override
	public UnableToModifyResourcePropertyFaultType createUnableToModifyResourcePropertyFaultType(
			Date timestamp, ResourcePropertyChangeFailureType value) {
		return this.model.createWsrfrpModelUnableToModifyResourcePropertyFaultType(timestamp, value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createUpdateResourcePropertiesRequestFailedFaultType(java.util.Date, com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType)
	 */
	@Override
	public UpdateResourcePropertiesRequestFailedFaultType createUpdateResourcePropertiesRequestFailedFaultType(
			Date timestamp, ResourcePropertyChangeFailureType value) {
		return this.model.createWsrfrpModelUpdateResourcePropertiesRequestFailedFaultType(timestamp, value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createResourcePropertyValueChangeNotificationType(com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType.NewValues)
	 */
	@Override
	public ResourcePropertyValueChangeNotificationType createResourcePropertyValueChangeNotificationType(
			NewValues newValues) {
		return this.model.createWsrfrpModelResourcePropertyValueChangeNotificationType(newValues);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createResourcePropertyValueChangeNotificationTypeNewValues(org.w3c.dom.Element)
	 */
	@Override
	public NewValues createResourcePropertyValueChangeNotificationTypeNewValues(
			Element valueAsElt) {
		return this.model.createWsrfrpModelResourcePropertyValueChangeNotificationTypeNewValues(valueAsElt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory#createResourcePropertyValueChangeNotificationTypeOldValues(org.w3c.dom.Element)
	 */
	@Override
	public OldValues createResourcePropertyValueChangeNotificationTypeOldValues(
			Element valueAsElt) {
		return this.model.createWsrfrpModelResourcePropertyValueChangeNotificationTypeOldValues(valueAsElt);
	}
}
