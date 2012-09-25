package com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfReader;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.WsrfbfJAXBContext;

public class WsrfbfReaderImpl implements WsrfbfReader {
	
	private WsrfbfJAXBContext resourceJaxbContext = null;
	//private Logger logger = Logger.getLogger(WsrfbfReaderImpl.class.getName());
		
	/**
	 * Default constructor
	 */
	protected WsrfbfReaderImpl() {
		this.resourceJaxbContext = WsrfbfJAXBContext.getInstance();
	}
	
	protected WsrfbfReaderImpl(String[] nsAndPrefixForMarshalling) {
		this.resourceJaxbContext = WsrfbfJAXBContext.getInstance(nsAndPrefixForMarshalling);
		this.resourceJaxbContext.addNsAndPrefixMapping(nsAndPrefixForMarshalling);
	}
	
	@Override
	public final BaseFaultType readBaseFaultType(Document document) throws WsrfbfException {
		BaseFaultType result = null;
		
		try {
			Unmarshaller unmarshaller = this.resourceJaxbContext.createWSBaseFaultsUnmarshaller();
			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new BaseFaultTypeImpl(schemaBinding.getValue(),null);
			}

		} catch (JAXBException e) {
			throw new WsrfbfException(e);
		} 	
		return result;
    }

}
