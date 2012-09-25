/**
 * Addressing Descriptor - SOA Tools Platform.
 * Copyright (c) 2008 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * XmlUtils.java
 * -------------------------------------------------------------------------
 */

package com.ebmwebsourcing.wsstar.wsnb.services.impl.util;

//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsaddressing10.api.type.ReferenceParametersType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.implementor.WsrfbfModelFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction.RefinedWsrfbfFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbReader;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbWriter;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.implementor.WsnbModelFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.utils.WsstarCommonUtils;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.implementor.WsrfrModelFactory;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.refinedabstraction.RefinedWsrfrFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.WsrfrlConstants;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlReader;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlWriter;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.implementor.WsrfrlModelFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.refinedabstraction.RefinedWsrfrlFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpReader;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpWriter;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.implementor.WsrfrpModelFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopReader;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopWriter;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.implementor.WstopModelFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.refinedabstraction.RefinedWstopFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;

public abstract class Wsnb4ServUtils {

    private static boolean isInit = false;
    //	private static String transformerFactoryClass = "";

    /*
	private static WsaReader wsaReader = null;
	private static WsaWriter wsaWriter = null;

	private static WsrfbfReader wsrfbfReader = null;
	private static WsrfbfWriter wsrfbfWriter = null;

	private static WsrfrReader wsrfraReader = null;
	private static WsrfrWriter wsrfraWriter = null;
     */
    private static WsrfrlReader wsrfrlReader = null;
    private static WsrfrlWriter wsrfrlWriter = null;

    private static WstopReader wstopReader = null;
    private static WstopWriter wstopWriter = null;

    private static WsnbReader wsnbReader = null;
    private static WsnbWriter wsnbWriter = null;	

    private static WsrfrpReader wsrfrpReader = null;
    private static WsrfrpWriter wsrfrpWriter = null;

    public static WsnbReader getWsnbReader() throws WsnbException{
        if (Wsnb4ServUtils.wsnbReader == null){
            Wsnb4ServUtils.wsnbReader = RefinedWsnbFactory.getInstance().getWsnbReader();
        }
        return Wsnb4ServUtils.wsnbReader;
    }

    public static WstopReader getWstopReader() throws WstopException{
        if (Wsnb4ServUtils.wstopReader == null){
            Wsnb4ServUtils.wstopReader = RefinedWstopFactory.getInstance().getWstopReader();	
        }
        return Wsnb4ServUtils.wstopReader;
    }

    public static WsrfrlReader getWsrfrlReader() throws WsrfrlException{
        if (Wsnb4ServUtils.wsrfrlReader == null){
            Wsnb4ServUtils.wsrfrlReader = RefinedWsrfrlFactory.getInstance().getWsrfrlReader();
        }
        return Wsnb4ServUtils.wsrfrlReader;
    }

    public static WsrfrpReader getWsrfrpReader() throws WsrfrpException{
        if (Wsnb4ServUtils.wsrfrpReader == null){
            Wsnb4ServUtils.wsrfrpReader = RefinedWsrfrpFactory.getInstance().getWsrfrpReader();	
        }
        return Wsnb4ServUtils.wsrfrpReader;
    }

    public static WsnbWriter getWsnbWriter() throws WsnbException{
        if (Wsnb4ServUtils.wsnbWriter == null){
            Wsnb4ServUtils.wsnbWriter = RefinedWsnbFactory.getInstance().getWsnbWriter();
        }
        return Wsnb4ServUtils.wsnbWriter;
    }

    public static WstopWriter getWstopWriter() throws WstopException{
        if (Wsnb4ServUtils.wstopWriter == null) {
            Wsnb4ServUtils.wstopWriter = RefinedWstopFactory.getInstance().getWstopWriter();
        }
        return Wsnb4ServUtils.wstopWriter;
    }

    public static WsrfrlWriter getWsrfrlWriter() throws WsrfrlException{
        if (Wsnb4ServUtils.wsrfrlWriter == null) {
            Wsnb4ServUtils.wsrfrlWriter = RefinedWsrfrlFactory.getInstance().getWsrfrlWriter();
        }
        return Wsnb4ServUtils.wsrfrlWriter;
    }

    public static WsrfrpWriter getWsrfrpWriter() throws WsrfrpException{
        if (Wsnb4ServUtils.wsrfrpWriter == null) {
            Wsnb4ServUtils.wsrfrpWriter = RefinedWsrfrpFactory.getInstance().getWsrfrpWriter();
        }
        return Wsnb4ServUtils.wsrfrpWriter;
    }
    /**
     * /!\ Mandatory Call : 
     * --------------------		
     * 		must be called to set the "modal" layer,
     * 		as soon as possible
     * 
     * @param wsaModFact
     * @param wsrfbfModFact
     * @param wsrfrModFact
     * @param wsrfrlModFact
     * @param wsrfrpModFact TODO
     * @param wstopModFact
     * @param wsnbModFact
     * @param wsnbrModFact
     */

    public static void initModelFactories(WsrfbfModelFactory wsrfbfModFact,
            WsrfrModelFactory wsrfrModFact, WsrfrlModelFactory wsrfrlModFact, 
            WsrfrpModelFactory wsrfrpModFact, WstopModelFactory wstopModFact, WsnbModelFactory wsnbModFact){

        if(!isInit){
            //		RefinedWsaFactory.getInstance(wsaModFact);
            RefinedWsrfbfFactory.getInstance(wsrfbfModFact);
            RefinedWsrfrFactory.getInstance(wsrfrModFact);
            RefinedWsrfrlFactory.getInstance(wsrfrlModFact);
            RefinedWsrfrpFactory.getInstance(wsrfrpModFact);
            RefinedWstopFactory.getInstance(wstopModFact);
            RefinedWsnbFactory.getInstance(wsnbModFact);
            isInit = true;
        }
    }


    /**
     * convert xml File loaded as {@link File} Java object to {@link Document} Java object 
     * 
     * @param file {@lin File} Java object to convert
     * @return Document Representation 
     */
    public static Document convertFromFiletoDocument(File file) throws WsnbException {
        Document result = null;		
        try{
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            dbfac.setNamespaceAware(true);
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            result = docBuilder.parse(file);
        } catch (SAXException e) {
            throw new WsnbException(e);
        } catch (IOException e) {
            throw new WsnbException(e);
        } catch (ParserConfigurationException e) {
            throw new WsnbException(e);
        }

        return result;
    }

    /**
     * Format the subscription's Id as a WS-Addressing ReferenceParameters element 
     * 
     * @param uuid
     * @return
     * @throws WsaException
     */
    public static Element createSubscriptionIdAsReferenceParamElt(String uuid) {
        Element subscriptionUuidElt = null;

        DocumentBuilder docBuilder= WsstarCommonUtils.getNamespaceDocumentBuilder();
        Document document = docBuilder.newDocument();

        subscriptionUuidElt = document.createElementNS(WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getNamespaceURI(),
                WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getLocalPart());
        subscriptionUuidElt.setPrefix(WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getPrefix());			
        subscriptionUuidElt.setTextContent(uuid);		

        return subscriptionUuidElt;
    }

    /**
     * Format the subscription's Id as a WS-Addressing ReferenceParameters element 
     * 
     * @param uuid
     * @return
     * @throws WsaException
     */
    public static String getSubscriptionIdFromReferenceParams(ReferenceParametersType refParams) {
        String subscriptionUuid = null;

        List<Object> objs = new ArrayList<Object>();
        objs.addAll(Arrays.asList(refParams.getAny()));

        List<Element> paramsAsElt = new ArrayList<Element>();
        for(Object obj: objs) {
            paramsAsElt.add((Element) obj);
        }

        for (Element paramItem : paramsAsElt) {
            if (paramItem.getLocalName().equals(WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getLocalPart()) &&
                    paramItem.getNamespaceURI().equals(WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getNamespaceURI())){
                subscriptionUuid = paramItem.getTextContent();
                break;
            }
        }		
        return subscriptionUuid;
    }

    /**
     * Create a {@link Element} representation of a given resource property 
     * (See "WS-ResourceProperties" specification and related implementation) 
     * 
     * @param propertyQName
     * @param propertyValue
     * @return
     */
    public static Element createPropertyAsElement(QName propertyQName, String propertyValue){
        Element propertyAsElt = null;

        final DocumentBuilder docBuilder = WsstarCommonUtils.getNamespaceDocumentBuilder();
        Document doc = docBuilder.newDocument();
        propertyAsElt = doc.createElementNS(propertyQName.getNamespaceURI(),propertyQName.getLocalPart());
        propertyAsElt.setPrefix(propertyQName.getPrefix());
        propertyAsElt.setTextContent(propertyValue);

        return propertyAsElt;
    }

    public static Element createSimpleReasonElement(String reasonAsString){
        Element reasonElt = null;

        final DocumentBuilder docBuilder = WsstarCommonUtils.getNamespaceDocumentBuilder();
        Document doc = docBuilder.newDocument();

        reasonElt = doc.createElementNS(WsrfrlConstants.TERMINATION_REASON_QNAME.getNamespaceURI(), 
                WsrfrlConstants.TERMINATION_REASON_QNAME.getLocalPart());
        reasonElt.setTextContent(reasonAsString);

        return reasonElt;
    }



    //    /**
    //     * parse the xml String and return it pretty-printed (with correct
    //     * indentations, etc..)
    //     * 
    //     * @param xmlDocument
    //     *            the xml document to pretty print. Must be non null
    //     * @param encoding
    //     *            the encoding to use
    //     *   
    //     * @returnpretty printed string if no error occurs. If an error occurs,
    //     *               return an empty String
    //     */
    //    public static String prettyPrint(final Document xmlDocument,String encoding) {
    //        String result = "";
    //        try {
    //            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    //            Wsnb4ServUtils.prettify(xmlDocument, outStream,encoding);
    //            result = outStream.toString(encoding);
    //        } catch (final Exception e) {
    //            System.err.println("write_dom failed:" + e);
    //            // if an error occurs, the result will be the original string
    //        }
    //        return result;
    //
    //    }
    //
    /**
     * parse the xml Document and return it pretty-printed (with correct
     * indentations, etc..).
     * Use the encoding defined at the parsing or in the document 
     * (utf8 is used if no encoding is defined)
     * @param xmlDocument
     *            the xml document to pretty print. Must be non null
     * @returnpretty printed string if no error occurs. If an error occurs,
     *               return an empty String
     */
    public static String prettyPrint(final Document xmlDocument) {

        return WsstarCommonUtils.prettyPrint(xmlDocument,WsstarCommonUtils.getEncoding(xmlDocument));
    }


    //    /**
    //     * Prettify the node into the output stream.
    //     *
    //     * @param node
    //     * @param out
    //     * @throws Exception
    //     */
    //    public static void prettify(Node node, OutputStream out,String encoding) throws Exception {
    //        Source source = new DOMSource(node);
    //        Source stylesheetSource = getStyleSheetSource();
    //
    //        TransformerFactory tf = transformerFactoryThreadLocal.get();
    //        Templates templates = tf.newTemplates(stylesheetSource);
    //        Transformer transformer = templates.newTransformer();
    //        transformer.setOutputProperty(OutputKeys.ENCODING,encoding);
    //        transformer.transform(source, new StreamResult(out));
    //    }

    //    /**
    //     * Return the encoding of the document.
    //     * @param xmlDocument
    //     * @return InputEncoding or the XmlEncoding of the document, UTF-8 if not found
    //     */
    //    public static String getEncoding(Document xmlDocument){
    //        String encoding = xmlDocument.getInputEncoding();
    //        if(encoding == null){
    //                encoding = xmlDocument.getXmlEncoding();
    //        }
    //        if(encoding == null){
    //                encoding = "UTF-8";
    //        }
    //        return encoding;
    //    }
    //    
    //    private static Source getStyleSheetSource() {
    //        return new StreamSource(Wsnb4ServUtils.class.getResourceAsStream("/util/prettyPrint.xsl"));
    //    }

    //    /**
    //     * <p>
    //     * A thread safe TransformerFactory, based on 
    //     * com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl
    //     * </p>
    //     */
    //    public final static ThreadLocal<TransformerFactory> transformerFactoryThreadLocal = new ThreadLocal<TransformerFactory>() {
    //
    //            @Override
    //            protected TransformerFactory initialValue() {
    //                    try {
    //                            
    //                            if (System.getProperty("java.vendor").indexOf("Sun") != -1){
    //                                    transformerFactoryClass = "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl";
    //                            }else if (System.getProperty("java.vendor").indexOf("IBM") != -1){
    //                                    transformerFactoryClass = "org.apache.xalan.processor.TransformerFactoryImpl";
    //                            }
    //                            
    //                            Thread.currentThread().getContextClassLoader().loadClass(transformerFactoryClass);
    //                            System.setProperty("javax.xml.transform.TransformerFactory", transformerFactoryClass);
    //                    } catch (ClassNotFoundException e) {
    //                            System.err.println("Warning. WSNotificationUtils : TransformerFactory '"+transformerFactoryClass+"' not found, the standard TransformerFactory will be used.");
    //                    }
    //                    return TransformerFactory.newInstance();
    //            }
    //    };

}
