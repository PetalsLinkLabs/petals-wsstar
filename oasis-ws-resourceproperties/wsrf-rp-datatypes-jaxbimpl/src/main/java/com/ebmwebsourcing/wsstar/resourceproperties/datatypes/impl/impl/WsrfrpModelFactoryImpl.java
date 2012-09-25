package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

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
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpReader;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpWriter;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.implementor.WsrfrpModelFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;

public class WsrfrpModelFactoryImpl implements WsrfrpModelFactory {

	private WsrfrpReaderImpl wsrfrpModelReader;
	private WsrfrpWriterImpl wsrfrpModelWriter; 
	
	public WsrfrpModelFactoryImpl() {	
		this.wsrfrpModelReader = new WsrfrpReaderImpl();
		this.wsrfrpModelWriter = new WsrfrpWriterImpl();		
	}

	@Override
	public WsrfrpReader getWsrfrpModelReader() {
		return this.wsrfrpModelReader;
	}

	@Override
	public WsrfrpWriter getWsrfrpModelWriter() {
		return this.wsrfrpModelWriter;
	}
	
	@Override
	public GetResourcePropertyResponse createWsrfrpModelGetResourcePropertyResponse() {
		return new GetResourcePropertyResponseImpl();
	}

	@Override
	public InvalidResourcePropertyQNameFaultType createWsrfrpModelInvalidResourcePropertyQNameFaultType(Date timestamp) {
		return new InvalidResourcePropertyQNameFaultTypeImpl(timestamp);
	}

	@Override
	public UpdateType createWsrfrpModelUpdateType(List<Element> content) throws WsrfrpException {
		return new UpdateTypeImpl(content);
	}
	
	@Override
	public UpdateResourceProperties createWsrfrpModelUpdateResourceProperties(UpdateType content) {
		return new UpdateResourcePropertiesImpl(content);
	}
	
	@Override
	public UpdateResourcePropertiesResponse createWsrfrpModelUpdateResourcePropertiesResponse() {
		return new UpdateResourcePropertiesResponseImpl();
	}
	
	@Override
	public ResourcePropertyChangeFailureType createWsrfrpModelResourcePropertyChangeFailureType(
			boolean value) {
		return new ResourcePropertyChangeFailureTypeImpl(value);
	}
	
	@Override
	public CurrentValue createWsrfrpModelResourcePropertyChangeFailureTypeCurrentValue(List<Element> values) {
		return new ResourcePropertyChangeFailureTypeImpl.CurrentValueImpl(values);
	}
	
	@Override
	public RequestedValue createWsrfrpModelResourcePropertyChangeFailureTypeRequestedValue(List<Element> values) {
		return new ResourcePropertyChangeFailureTypeImpl.RequestedValueImpl(values);
	}
	
	@Override
	public InvalidModificationFaultType createWsrfrpModelInvalidModificationFaultType(
			Date timestamp, ResourcePropertyChangeFailureType value) {
		return new InvalidModificationFaultTypeImpl(timestamp,value);
	}
	
	@Override
	public UnableToModifyResourcePropertyFaultType createWsrfrpModelUnableToModifyResourcePropertyFaultType(
			Date timestamp, ResourcePropertyChangeFailureType value) {
		return new UnableToModifyResourcePropertyFaultTypeImpl(timestamp,value);
	}
	
	@Override
	public UpdateResourcePropertiesRequestFailedFaultType createWsrfrpModelUpdateResourcePropertiesRequestFailedFaultType(
			Date timestamp, ResourcePropertyChangeFailureType value) {
		return new UpdateResourcePropertiesRequestFailedFaultTypeImpl(timestamp,value);
	}
	
	@Override
		public ResourcePropertyValueChangeNotificationType createWsrfrpModelResourcePropertyValueChangeNotificationType(
				NewValues newValues) {		
			return new ResourcePropertyValueChangeNotificationTypeImpl(newValues);
		}
	
	@Override
	public NewValues createWsrfrpModelResourcePropertyValueChangeNotificationTypeNewValues(
			Element valueAsElt) {
		return new ResourcePropertyValueChangeNotificationTypeImpl.NewValuesImpl(valueAsElt);
	}
	
	@Override
	public OldValues createWsrfrpModelResourcePropertyValueChangeNotificationTypeOldValues(
			Element valueAsElt) {
		return new ResourcePropertyValueChangeNotificationTypeImpl.OldValuesImpl(valueAsElt);
	}
	
}
