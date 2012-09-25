/**
 * Copyright (c) 2010 EBM Websourcing, http://www.ebmwebsourcing.com/
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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType.CurrentValue;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType.RequestedValue;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;

///** 
// * /!\ "WS-Addressing" Jaxb model unit test classes dependency !! 
// */

public class WsrfrpUnitTestsUtilsTests {
		
	// ---------------------------------------------------------
	public static final String WSRFBF_SPECIFICATION_NAME = "OASIS \"WS-BaseFaults\"";
	
	public static final String WSRFBF_XML_SCHEMA_PATH = "/schemas/bf-2.xsd";	
	
	public static final String WSRFRP_SPECIFICATION_NAME = "OASIS \"WS-ResourceProperties\"";
	
	public static final String WSRFRP_XML_SCHEMA_PATH = "/schemas/rp-2.xsd";
		
	public static final String XML_XML_SCHEMA_PATH = "/schemas/xml.xsd";
	
	public static final String WSTOP_XML_SCHEMA_PATH = "/t-1.xsd";
	
	public static final String WSNB_XML_SCHEMA_PATH = "/b-2.xsd";
	
	public static final String[] WSRFRP_XML_SCHEMAS_PATHS = {
		WsaUnitTestsUtils.WSA_XML_SCHEMA_PATH,
		WsrfrpUnitTestsUtilsTests.XML_XML_SCHEMA_PATH,
		WsrfrpUnitTestsUtilsTests.WSRFBF_XML_SCHEMA_PATH,		
		WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMA_PATH,
		WsrfrpUnitTestsUtilsTests.WSTOP_XML_SCHEMA_PATH,
		WsrfrpUnitTestsUtilsTests.WSNB_XML_SCHEMA_PATH};
	// --------------------------------------------------------

	/**
	 * Create a Default DOM Element object to used as FaultCauseType content.
	 * It should be a DOM Element representation of a BaseFaultType - or inherited - Type. 
	 *  		See WS-BaseFaults OASIS specification for details
	 */
	public static Element createDefaultFaultCauseContent(String faultCausePath){
		
		Document faultCauseContentAsDocument = 
			WsaUnitTestsUtils.fromStreamToDocument(WsrfrpUnitTestsUtilsTests.class.getResourceAsStream(faultCausePath));
		return faultCauseContentAsDocument.getDocumentElement();		
	}

	public static EndpointReferenceType createDefaultBaseFaultOriginator() throws URISyntaxException {
		EndpointReferenceType defaultMinimalEdp = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		URI address = new URI(WsaUnitTestsUtils.DEFAULT_ENDPOINT_URI_VALUE); 	
		Address defaultAddr = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
		defaultAddr.setValue(address);
		defaultMinimalEdp.setAddress(defaultAddr);			
		return defaultMinimalEdp;
	}

	public static Element createTopicSetPropertyValue(String topicSetPath) {
		Element supportedTopicsSetAsElt = null;
		
		InputStream supportedtopicsSet = WsrfrpUnitTestsUtilsTests.class.getResourceAsStream(topicSetPath);
			
			try{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				DocumentBuilder builder = factory.newDocumentBuilder();  
				Document parsedXmlDoc = builder.parse(supportedtopicsSet);
				supportedTopicsSetAsElt = parsedXmlDoc.getDocumentElement();
			}catch(ParserConfigurationException pce){
				pce.printStackTrace();
			}catch(SAXException se){
				se.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
		return supportedTopicsSetAsElt;
	}

	public static List<Element> createDefaultProperties() {
		List<Element> propertiesAsElts = new ArrayList<Element>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder;
			builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element currentElt = doc.createElementNS("http://docs.oasis-open.org/wsn/b-2","FixedTopicSet");
			currentElt.setPrefix("wsn-b");
			currentElt.setNodeValue("false");
			propertiesAsElts.add(currentElt);
			
			currentElt = doc.createElementNS("http://docs.oasis-open.org/wsn/b-2","TopicExpressionDialect");
			currentElt.setPrefix("wsn-b");
			currentElt.setNodeValue("http://www.w3.org/TR/1999/REC-xpath-19991116");
			propertiesAsElts.add(currentElt);
						
			propertiesAsElts.add(WsrfrpUnitTestsUtilsTests.createTopicSetPropertyValue("/SupportedTopicsSet.xml"));
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return propertiesAsElts;
	}

	public static CurrentValue createCurrentValues() throws WsrfrpException {
		
		List<Element> values = new ArrayList<Element>(); 
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder;
			builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			// ---- change "FixedTopicSet" property value ----
			Element currentElt = doc.createElementNS("http://docs.oasis-open.org/wsn/b-2","FixedTopicSet");
			currentElt.setPrefix("wsn-b");
			currentElt.setNodeValue("true");
			values.add(currentElt);						
			
			// ---- change "TopicSet" property value ----
			values.add(WsrfrpUnitTestsUtilsTests.createTopicSetPropertyValue("/SupportedTopicsSet.xml"));
	
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}		
		return RefinedWsrfrpFactory.getInstance().createResourcePropertyChangeFailureTypeCurrentValue(values);
	}

	public static RequestedValue createRequestedValues() throws WsrfrpException {
		
		List<Element> values = new ArrayList<Element>(); 
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder;
			builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// ---- change "FixedTopicSet" property value ----
			Element currentElt = doc.createElementNS("http://docs.oasis-open.org/wsn/b-2","FixedTopicSet");
			currentElt.setPrefix("wsn-b");
			currentElt.setNodeValue("true");
			values.add(currentElt);						
	
			// ---- change "TopicSet" property value ----
			values.add(WsrfrpUnitTestsUtilsTests.createTopicSetPropertyValue("/NewSupportedTopicsSet.xml"));
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}		
		return RefinedWsrfrpFactory.getInstance().createResourcePropertyChangeFailureTypeRequestedValue(values);
	}
	
		
//	public static Document fromStreamToDocument(InputStream stream){
//
//		Document result = null;
//		try{
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			factory.setNamespaceAware(true);
//			DocumentBuilder builder = factory.newDocumentBuilder();  
//			Document parsedXmlDoc = builder.parse(stream);
//			result = parsedXmlDoc;
//		}catch(ParserConfigurationException pce){
//			pce.printStackTrace();
//		}catch(SAXException se){
//			se.printStackTrace();
//		}catch(IOException ioe){
//			ioe.printStackTrace();
//		}
//		return result;
//	}
	
//	public static Document convertFromUriToDocument(URI uri){
//		Document result = null;
//		try {
//
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			factory.setNamespaceAware(true);
//			DocumentBuilder builder = factory.newDocumentBuilder();    		    		
//			File xml = new File(uri.getPath());
//			result = builder.parse(xml);
//
//		}catch(ParserConfigurationException pce){
//			pce.printStackTrace();
//		}catch(SAXException se){
//			se.printStackTrace();
//		}catch(IOException ioe){
//			ioe.printStackTrace();
//		}
//		return result;
//	}
//	
	
//	
//	public static Document fromFileTodocument(File file) {
//
//		Document result = null;		
//		try{
//			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
//			dbfac.setNamespaceAware(true);
//			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
//			result = docBuilder.parse(file);
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//	
//	public static void displayEltAndItsChilds(Element elt, String ns, int level){				
//
//		System.out.println(" ---  xml tree depth : " + level); 
//		System.out.println("topic name : " + elt.getLocalName());
//		System.out.println("topic namespace : " + ((elt.getNamespaceURI() != null)?elt.getNamespaceURI():ns));
//		
//		NodeList childs = elt.getChildNodes();
//		for (int i = 0; i < childs.getLength();i++) {
//			if (childs.item(i).getNodeType() == Node.ELEMENT_NODE){
//				Util.displayEltAndItsChilds((Element)childs.item(i),((elt.getNamespaceURI() != null)?elt.getNamespaceURI():ns), level+1);
//			}
//		}
//		System.out.println("~~~~~~~~~~~~~");
//	}

}
