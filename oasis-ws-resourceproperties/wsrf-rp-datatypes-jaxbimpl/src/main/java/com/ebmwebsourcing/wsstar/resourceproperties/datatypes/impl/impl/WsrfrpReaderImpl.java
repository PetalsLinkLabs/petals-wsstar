package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidModificationFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidResourcePropertyQNameFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UnableToModifyResourcePropertyFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesRequestFailedFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpReader;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class WsrfrpReaderImpl implements WsrfrpReader {
	
	private WsrfrpJAXBContext resourcePropertiesJaxbContext = null;
	
	/**
	 * Default constructor
	 */
	protected WsrfrpReaderImpl() {
		this.resourcePropertiesJaxbContext = WsrfrpJAXBContext.getInstance();
	}
	
	protected WsrfrpReaderImpl(String[] nsAndPrefixForMarshalling) {
		this.resourcePropertiesJaxbContext = WsrfrpJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}
	
	@Override
	public QName readGetResourceProperty(Document document)
			throws WsrfrpException {
		QName propertyName = null;
		
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<QName> schemaBinding = unmarshaller.unmarshal(new DOMSource(document),QName.class);
			if (schemaBinding.getValue() != null){
				propertyName = schemaBinding.getValue();
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		
		return propertyName;
	}

	@Override
	public GetResourcePropertyResponse readGetResourcePropertyResponse(
			Document document) throws WsrfrpException {
		GetResourcePropertyResponse result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse.class);
			if (schemaBinding.getValue() != null){
				result = new GetResourcePropertyResponseImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}

	@Override
	public InvalidResourcePropertyQNameFaultType readInvalidResourcePropertyQNameFaultType(
			Document document) throws WsrfrpException {
		
		InvalidResourcePropertyQNameFaultType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new InvalidResourcePropertyQNameFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
	
	@Override
	public UpdateType readUpdateType(Document document) throws WsrfrpException {
		UpdateType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType.class);
			if (schemaBinding.getValue() != null){
				result = new UpdateTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
	
	@Override
	public UpdateResourceProperties readUpdateResourceProperties(
			Document document) throws WsrfrpException {
		UpdateResourceProperties result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties.class);
			if (schemaBinding.getValue() != null){
				result = new UpdateResourcePropertiesImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
	
	@Override
	public UpdateResourcePropertiesResponse readUpdateResourcePropertiesResponse(
			Document document) throws WsrfrpException {
		UpdateResourcePropertiesResponse result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse.class);
			if (schemaBinding.getValue() != null){
				result = new UpdateResourcePropertiesResponseImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
	
	@Override
	public InvalidModificationFaultType readInvalidModificationFaultType(
			Document document) throws WsrfrpException {
		InvalidModificationFaultType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new InvalidModificationFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
	
	@Override
	public UnableToModifyResourcePropertyFaultType readUnableToModifyResourcePropertyFaultType(
			Document document) throws WsrfrpException {
		UnableToModifyResourcePropertyFaultType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnableToModifyResourcePropertyFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
	
	@Override
	public UpdateResourcePropertiesRequestFailedFaultType readUpdateResourcePropertiesRequestFailedFaultType(
			Document document) throws WsrfrpException {
		UpdateResourcePropertiesRequestFailedFaultType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UpdateResourcePropertiesRequestFailedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
	
	@Override
	public ResourcePropertyValueChangeNotificationType readResourcePropertyValueChangeNotificationType(
			Document document) throws WsrfrpException {
		ResourcePropertyValueChangeNotificationType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.class);
			if (schemaBinding.getValue() != null){
				result = new ResourcePropertyValueChangeNotificationTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrpException(e);
		} 	
		return result;
	}
}
