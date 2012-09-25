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
package com.ebmwebsourcing.wsstar.resource.datatypes.api.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;

///** 
// * /!\ "WS-Addressing" Jaxb model unit test classes dependency !! 
// */

public class WsrfrUnitTestsUtils {
		
	// ---------------------------------------------------------
	public static final String WSRFBF_SPECIFICATION_NAME = "OASIS \"WS-BaseFaults\"";
	
	public static final String WSRFBF_XML_SCHEMA_PATH = "/schemas/bf-2.xsd";
	
	public static final String WSRFR_SPECIFICATION_NAME = "OASIS \"WS-Resource\"";
	
	public static final String WSRFR_XML_SCHEMA_PATH = "/schemas/r-2.xsd";
	
	/*public static final String WSRFRL_SPECIFICATION_NAME = "OASIS \"WS-ResourceLifetime\"";
	
	public static final String WSRFRL_XML_SCHEMA_PATH = "/schemas/rl-2.xsd";
	
	public static final String WSRFRP_SPECIFICATION_NAME = "OASIS \"WS-ResourceProperties\"";
	
	public static final String WSRFRP_XML_SCHEMA_PATH = "/schemas/rp-2.xsd";*/
		
	public static final String XML_XML_SCHEMA_PATH = "/schemas/xml.xsd";
	
	public static final String[] WSRFR_XML_SCHEMAS_PATHS = {
		WsaUnitTestsUtils.WSA_XML_SCHEMA_PATH,
		WsrfrUnitTestsUtils.XML_XML_SCHEMA_PATH,
		WsrfrUnitTestsUtils.WSRFBF_XML_SCHEMA_PATH,
		WsrfrUnitTestsUtils.WSRFR_XML_SCHEMA_PATH/*,
		WsrfrUnitTestsUtils.WSRFRL_XML_SCHEMA_PATH,
		WsrfrUnitTestsUtils.WSRFRP_XML_SCHEMA_PATH*/};
	// --------------------------------------------------------

	/**
	 * Create a Default DOM Element object to used as FaultCauseType content.
	 * It should be a DOM Element representation of a BaseFaultType - or inherited - Type. 
	 *  		See WS-BaseFaults OASIS specification for details
	 */
	public static Element createDefaultFaultCauseContent(String faultCausePath){
		
		Document faultCauseContentAsDocument = 
			WsaUnitTestsUtils.fromStreamToDocument(WsrfrUnitTestsUtils.class.getResourceAsStream(faultCausePath));
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

	/*public static Element createTerminationReasonElt(String reason) {
		Element reasonAsElt = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder;
			builder = dbFactory.newDocumentBuilder();

			Document doc = builder.newDocument();
			reasonAsElt = doc.createElementNS(WsrfrlConstants.WS_RESOURCE_LIFETIME_NAMESPACE_URI,"TerminationReason");
			reasonAsElt.setPrefix(WsrfrlConstants.WS_RESOURCE_LIFETIME_PREFIX);
			reasonAsElt.setTextContent(reason);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			return reasonAsElt;
	}*/
	
	
	
	
	
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
