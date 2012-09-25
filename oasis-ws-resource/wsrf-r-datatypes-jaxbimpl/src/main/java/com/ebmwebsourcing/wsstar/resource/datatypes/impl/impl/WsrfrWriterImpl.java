package com.ebmwebsourcing.wsstar.resource.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.w3c.dom.Document;

import com.ebmwebsourcing.easycommons.research.util.dom.DOMUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnavailableFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnknownFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrWriter;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.utils.WsrfrException;
import com.ebmwebsourcing.wsstar.resource.datatypes.impl.WsrfrJAXBContext;

public class WsrfrWriterImpl implements WsrfrWriter {
	
	//private Logger logger = Logger.getLogger(WsrfrWriterImpl.class.getName());
	private WsrfrJAXBContext resourceJaxbContext = null;
		
	/**
     * Default constructor
     * 
     * @throws WSNotificationException
     */
    protected  WsrfrWriterImpl(){
    	this.resourceJaxbContext = WsrfrJAXBContext.getInstance();
	}     
    
    protected  WsrfrWriterImpl(String[] nsAndPrefixForMarshalling){
    	this.resourceJaxbContext = WsrfrJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}
	
	@Override
	public final Document writeResourceUnknwonFaultTypeAsDOM(ResourceUnknownFaultType fault) throws WsrfrException {
		Document result = null;
    	if (fault instanceof ResourceUnknownFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourceJaxbContext.createWSResourceMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType> element = 
    				WsrfrJAXBContext.WSRFR_JAXB_FACTORY.createResourceUnknownFault(ResourceUnknownFaultTypeImpl.toJaxbModel(fault));
    			
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrException(WsrfbfUtils.getBindingExMessage(fault), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeResourceUnavailableFaultTypeAsDOM(
			ResourceUnavailableFaultType fault) throws WsrfrException {
		Document result = null;
    	if (fault instanceof ResourceUnavailableFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourceJaxbContext.createWSResourceMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType> element = 
    				WsrfrJAXBContext.WSRFR_JAXB_FACTORY.createResourceUnavailableFault(ResourceUnavailableFaultTypeImpl.toJaxbModel(fault));
    				
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrException(WsrfbfUtils.getBindingExMessage(fault), ex);
    		} 
    	}
    	return result;	
	}

}
