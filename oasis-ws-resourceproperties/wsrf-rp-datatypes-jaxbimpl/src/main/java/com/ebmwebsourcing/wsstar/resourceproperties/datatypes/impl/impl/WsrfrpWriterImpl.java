package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.w3c.dom.Document;

import com.ebmwebsourcing.easycommons.research.util.dom.DOMUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.WsrfrpConstants;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidModificationFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidResourcePropertyQNameFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UnableToModifyResourcePropertyFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesRequestFailedFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpWriter;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class WsrfrpWriterImpl implements WsrfrpWriter {
	
	private WsrfrpJAXBContext resourcePropertiesJaxbContext = null;
	
	/**
     * Default constructor
     */
    protected  WsrfrpWriterImpl(){
    	this.resourcePropertiesJaxbContext = WsrfrpJAXBContext.getInstance();
	} 
        
    protected  WsrfrpWriterImpl(String[] nsAndPrefixForMarshalling){
    	this.resourcePropertiesJaxbContext = WsrfrpJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}
    
	@Override
	public Document writeGetResourcePropertyAsDOM(QName propertyName)
			throws WsrfrpException {
		Document result = null;
		try {

			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

			// TODO : Check if it is a Thread safe method
			final JAXBElement<QName> element = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createGetResourceProperty(propertyName);

			marshaller.marshal(element, result);            

		} catch (final Exception ex) {
			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(propertyName), ex);
		} 
		return result;
	}

	@Override
	public Document writeGetResourcePropertyResponseAsDOM(
			GetResourcePropertyResponse payload) throws WsrfrpException {
		Document result = null;
    	if (payload instanceof GetResourcePropertyResponseImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse>(WsrfrpConstants.GET_RESOURCE_PROPERTY_RESPONSE_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse.class,
    						((GetResourcePropertyResponseImpl)payload).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(payload), ex);
    		} 
    	}
    	return result;
	}

	@Override
	public Document writeInvalidResourcePropertyQNameFaultTypeAsDOM(
			InvalidResourcePropertyQNameFaultType value) throws WsrfrpException {
		Document result = null;
    	if (value instanceof InvalidResourcePropertyQNameFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType>(WsrfrpConstants.INVALID_RESOURCE_PROPERTY_QNAME_FAULT_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidResourcePropertyQNameFaultType.class,
    						InvalidResourcePropertyQNameFaultTypeImpl.toJaxbModel(value));

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;
	}
	
	@Override
	public Document writeUpdateTypeAsDOM(UpdateType instance)
			throws WsrfrpException {
		Document result = null;
    	if (instance instanceof UpdateTypeImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType>(WsrfrpConstants.UPDATE_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType.class,
    						((UpdateTypeImpl)instance).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(instance), ex);
    		} 
    	}
    	return result;
	}
	
	@Override
	public Document writeUpdateResourcePropertiesAsDOM(
			UpdateResourceProperties payload) throws WsrfrpException {
		Document result = null;
    	if (payload instanceof UpdateResourcePropertiesImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties>(WsrfrpConstants.UPDATE_RESOURCE_PROPERTIES_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties.class,
    						((UpdateResourcePropertiesImpl)payload).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(payload), ex);
    		} 
    	}
    	return result;
	}
	
	@Override
	public Document writeUpdateResourcePropertiesResponseAsDOM(
			UpdateResourcePropertiesResponse payload) throws WsrfrpException {
		Document result = null;
    	if (payload instanceof UpdateResourcePropertiesResponseImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse>(WsrfrpConstants.UPDATE_RESOURCE_PROPERTIES_RESPONSE_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesResponse.class,
    						((UpdateResourcePropertiesResponseImpl)payload).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(payload), ex);
    		} 
    	}
    	return result;
	}
	
	@Override
	public Document writeInvalidModificationFaultTypeAsDOM(
			InvalidModificationFaultType fault) throws WsrfrpException {
		Document result = null;
    	if (fault instanceof InvalidModificationFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType>(WsrfrpConstants.INVALID_MODIFICATION_QNAME_FAULT_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType.class,
    						InvalidModificationFaultTypeImpl.toJaxbModel(fault));
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(fault), ex);
    		} 
    	}
    	return result;
	}
	
	@Override
	public Document writeUnableToModifyResourcePropertyFaultTypeAsDOM(
			UnableToModifyResourcePropertyFaultType fault)
			throws WsrfrpException {
		Document result = null;
    	if (fault instanceof UnableToModifyResourcePropertyFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType>(WsrfrpConstants.UNABLE_TO_MODIFY_RESOURCE_PROPERTY_FAULT_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType.class,
    						UnableToModifyResourcePropertyFaultTypeImpl.toJaxbModel(fault));
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(fault), ex);
    		} 
    	}
    	return result;
	}
	
	@Override
	public Document writeUpdateResourcePropertiesRequestFailedFaultTypeAsDOM(
			UpdateResourcePropertiesRequestFailedFaultType fault)
			throws WsrfrpException {
		Document result = null;
    	if (fault instanceof UpdateResourcePropertiesRequestFailedFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType>(WsrfrpConstants.UPDATE_RESOURCE_PROPERTIES_REQUEST_FAILED_FAULT_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType.class,
    						UpdateResourcePropertiesRequestFailedFaultTypeImpl.toJaxbModel(fault));
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(fault), ex);
    		} 
    	}
    	return result;
	}
	
	@Override
	public Document writeResourcePropertyValueChangeNotificationTypeAsDOM(
			ResourcePropertyValueChangeNotificationType value)
			throws WsrfrpException {
		Document result = null;
		try {

			Marshaller marshaller = this.resourcePropertiesJaxbContext.createWSResourcePropertiesMarshaller();

			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

			// TODO : Check if it is a Thread safe method
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType> element =
				WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotification(
						((ResourcePropertyValueChangeNotificationTypeImpl)value).getJaxbTypeObj());

			marshaller.marshal(element, result);            

		} catch (final Exception ex) {
			throw new WsrfrpException(WsrfbfUtils.getBindingExMessage(value), ex);
		} 
		return result;
	}
}
