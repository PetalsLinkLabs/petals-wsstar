package com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.w3c.dom.Document;

import com.ebmwebsourcing.easycommons.research.util.dom.DOMUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfWriter;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.WsrfbfJAXBContext;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;

public class WsrfbfWriterImpl implements WsrfbfWriter {
	
	//private Logger logger = Logger.getLogger(WsrfbfWriterImpl.class.getName());
	private WsrfbfJAXBContext resourceJaxbContext = null;
		
	/**
     * Default constructor
     * 
     * @throws WSNotificationException
     */
    protected  WsrfbfWriterImpl(){
//		try {
			this.resourceJaxbContext = WsrfbfJAXBContext.getInstance();
//		} catch (Exception e) {
//			this.log.log(Level.WARNING, "WS-BaseFaults Jaxb Model Writer initialisation failed !", e);
//		}	
	} 
        
    protected  WsrfbfWriterImpl(String[] nsAndPrefixForMarshalling){
//		try {
			this.resourceJaxbContext = WsrfbfJAXBContext.getInstance(nsAndPrefixForMarshalling);
//		} catch (Exception e) {
//			this.log.log(Level.WARNING, "WS-BaseFaults Jaxb Model Writer initialisation failed !", e);
//		}	
	}
   
    @Override
    public final Document writeBaseFaultTypeAsDOM(BaseFaultType fault)
    throws WsrfbfException {
    	Document result = null;
    	if (fault instanceof BaseFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourceJaxbContext.createWSBaseFaultsMarshaller();

//    			result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();
    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType> element =
    				WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFault(BaseFaultTypeImpl.toJaxbModel(fault, null));
//    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType>(WsrfbfConstants.BASE_FAULT_QNAME,
//    						com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.class,
//    						((BaseFaultTypeImpl)fault).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfbfException(WsrfbfUtils.getBindingExMessage(fault), ex);
//    		} catch (final ParserConfigurationException ex) {
//    			throw new WsrfException(
//    					"Failed to build XML binding from "+ fault.getClass().getSimpleName() + " Api Java classes", ex);
    		} 
    	}
    	return result;	
    }


}
