package com.ebmwebsourcing.wsstar.resource.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnavailableFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnknownFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrReader;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.utils.WsrfrException;
import com.ebmwebsourcing.wsstar.resource.datatypes.impl.WsrfrJAXBContext;

public class WsrfrReaderImpl implements WsrfrReader {
	
	private WsrfrJAXBContext resourceJaxbContext = null;	
	//private Logger logger = Logger.getLogger(WsrfrReaderImpl.class.getName());
	
	/**
	 * Default constructor
	 * @throws WSNotificationException
	 */
	protected WsrfrReaderImpl() {
		this.resourceJaxbContext = WsrfrJAXBContext.getInstance();
	}
	
	protected WsrfrReaderImpl(String[] nsAndPrefixForMarshalling) {
		this.resourceJaxbContext = WsrfrJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}
	
	@Override
	public final ResourceUnknownFaultType readResourceUnknownFaultType(
			Document document) throws WsrfrException {
		ResourceUnknownFaultType result = null;
		try {

			Unmarshaller unmarshaller = this.resourceJaxbContext.createWSResourceUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),
						com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnknownFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new ResourceUnknownFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrException(e);
		} 	
		return result;
	}

	@Override
	public final ResourceUnavailableFaultType readResourceUnavailableFaultType(
			Document document) throws WsrfrException {
		ResourceUnavailableFaultType result = null;
		try {

			Unmarshaller unmarshaller = this.resourceJaxbContext.createWSResourceUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),
						com.ebmwebsourcing.wsstar.jaxb.resource.resource.ResourceUnavailableFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new ResourceUnavailableFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrException(e);
		} 	
		return result;
	}

}
