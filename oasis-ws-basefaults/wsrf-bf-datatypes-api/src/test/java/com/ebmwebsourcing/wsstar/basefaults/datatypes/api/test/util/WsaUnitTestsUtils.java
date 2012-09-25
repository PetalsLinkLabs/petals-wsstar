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
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;


public class WsaUnitTestsUtils {
	
	// ---------------------------------------------------------
	public static final String WSA_SPECIFICATION_NAME = "W3C \"WS-Addressing\"";
	
	public static final String WSA_XML_SCHEMA_PATH = "/schema/wsaddressing10/wsaddressing10.xsd";
	
	public static final String[] WSA_XML_SCHEMAS_PATHS = {WsaUnitTestsUtils.WSA_XML_SCHEMA_PATH};
		
	public static final String DEFAULT_ENDPOINT_URI_VALUE = "http://myURL/for/test::MyServiceName@myEndpointAddress";
	
	public static final String TEMP_TEST_DIRECTORY = "tmp" + File.separatorChar;
	
	// --------------------------------------------------------
	
	public static String getFailedMessagePrefix(String specification){
		return "\nUnit Tests of "+ specification + " Model implementation Failed !\n Failure Cause :\n";
	}
	
	
	/**
	 * 	Validate a DOM Document representation of a "WS-* Specification" Java Datatype,
	 * 		according to its associated XMl Schema.
	 * 		
	 * 		Example : for W3C "WS-Addressing" specification Java Datatype to can be :
	 * 		 {@link EndpointReferenceType}, {@link ReferenceParameterType}, ...
	 * @param specification the name of the WS-* specification (W3C's WS-ADDRESSING, OASIS's WS-RESOURCE, ....)
	 * @param clazz the type - Java Class - of the original Java Object 
	 * @param isDebug TODO
	 * @param xmlSchemaPaths the path of the WS-* specification xml schema file. 
	 * @param objAsDom DOM Document representation associate to a given Ws-Addressing Datatype
	 * 
	 * @return true if the given DOM Document Object is valid respect to the W3C "WS-ADDRESSING" XML schema 
	 * @throws SAXException
	 * @throws IOException
	 * @throws WsaException 
	 */
	public static boolean validateResult(Document objAsDOMDoc, String[] xmlSchemasPaths, String specification, Class<?> clazz, boolean isDebug) throws SAXException{	
		boolean isValid = false;
		
		// 1. Lookup a factory for the W3C XML Schema language
		SchemaFactory factory = 
			SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		// 2. Compile the schema. 
		// Here the schema is loaded from a java.io.File, but you could use 
		// a java.net.URL or a javax.xml.transform.Source instead.
		//		File schemaLocation = new File(xmlSchemas);
		Source[] xmlSchemas = new StreamSource[xmlSchemasPaths.length];
		for (int i = 0; i < xmlSchemasPaths.length; i++) {
			xmlSchemas[i] = new StreamSource(clazz.getResourceAsStream(xmlSchemasPaths[i]));
		}
				
		Schema schema = factory.newSchema(xmlSchemas);

		// 3. Get a validator from the schema.
		Validator validator = schema.newValidator();

		// 4. Parse the document you want to check.
		Source source = new DOMSource(objAsDOMDoc);

		// 5. Check the document
		try {
			validator.validate(source);
			if (isDebug)
				System.out.println("\t(The created DOM Document representation of the \"" + clazz.getSimpleName() + 
						"\" Object\n\t is valid according to " + specification +" xml-schema)\n");
			isValid = true;
		}
		catch (SAXException ex) {
			isValid = false;
			throw new SAXException("\tThe created DOM Document representation of the \"" + clazz.getSimpleName() + 
					" \" Object\n\t is not valid according to " + specification + " xml-schema because : \n " + ex.getMessage()+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isValid;
	}
//	
//	/**
//	 * Create a Default DOM Element object to used as ReferenceParameterType content
//	 * @param referenceParameters TODO
//	 */
//	public static void addSomeReferenceParameters(ReferenceParametersType referenceParameters){
//		List<Element> result = new ArrayList<Element>();
//		
//		try {
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			dbFactory.setNamespaceAware(true);
//			Document document = dbFactory.newDocumentBuilder().newDocument();
//			
//			Element currentElt = null;
//			for (int i = 0; i < 4; i++) {
//				currentElt = document.createElement("refParam"+i);
//				currentElt.setTextContent("valueOfRefParm"+i);
//				result.add(currentElt);
//			}
//			
//			referenceParameters.addAllAny(result);
//
//		} catch (ParserConfigurationException e) {			
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * Create a Default DOM Element object to used as MetadataType content
//	 * 
//	 * @return a list of DOM Element representations of a metadata 
//	 */
//	public static void addSomeMetadata(MetadataType metadata){
//		
//		Document xmlFragment =
//			WsaUnitTestsUtils.fromStreamToDocument(WsaUnitTestsUtils.class.getResourceAsStream("/ws-metadataExchangeFragment.xml"));
//		
//		metadata.addAny(xmlFragment.getDocumentElement());		
//	}
//	
//	/**
//	 *  Create a Default DOM Element object to used as a WS-ADDRESSING extension 
//	 *  For example : a WS-Policy Java DataType, a WS-Management	Java DataType, ... 
//	 * @param endpoint 
//	 */
//	public static void addSomeExtensions(EndpointReferenceType endpoint){
//		
//		Document xmlFragment =
//			WsaUnitTestsUtils.fromStreamToDocument(WsaUnitTestsUtils.class.getResourceAsStream("/ws-policyFragment.xml"));
//		
//		endpoint.addAny(xmlFragment.getDocumentElement());
//	}
//	
//	/**
//	 * create a specific sub folder on the default "tmp/" Unit Tests Parent Folder
//	 * 
//	 * @param tempSubDirectoryPath 
//	 * @return
//	 */
//	public static File createTempTestDirectory(String tempSubDirectoryPath){
//		
//		String tempDirName = WsaUnitTestsUtils.TEMP_TEST_DIRECTORY;
//		String tmpRepositoryName = tempDirName + tempSubDirectoryPath + File.separatorChar;
//		File tempRepository = new File (tmpRepositoryName);		
//		tempRepository.mkdirs();
//		
//		return tempRepository;
//	}
//	
//	/**
//	 * clean and remove temporary UnitTests created folders and sub-folders
//	 * 
//	 * @param tmpSubDirectory subFolder to remove
//	 */
//	public static void deleteExistingTestDirectory(File tmpSubDirectory){
//
//		File[] storedRegFiles = tmpSubDirectory.listFiles();
//		for (File fileItem : storedRegFiles) {
//			fileItem.delete();				
//		}
//		
//		tmpSubDirectory.delete();
//		new File(WsaUnitTestsUtils.TEMP_TEST_DIRECTORY).delete();
//	}
//
	/**
	 *  Used to compare DOM Element content 
	 * @param Elt
	 * @return
	 */
	public static String formatToComparison(Element Elt){
		 
		String result = Elt.getNodeName()+((Elt.getNodeValue()!=null)?"_"+Elt.getNodeValue()+"_" :"_");
		
		// Build a String based on Node and all its descendants Names
		NodeList list = Elt.getElementsByTagName("*");
		for (int i=0; i<list.getLength(); i++) {						
			Element element = (Element)list.item(i);
//			System.out.println("\n[DEBUG - LEVEL2] --> current node is : " + element.getNodeName()+ "\n");
			
			result += element.getNodeName()+((element.getNodeValue()!=null)?"_"+element.getNodeValue()+"_" :"_");
			
			if ((element.getElementsByTagName("*")).getLength() == 0){
				String curentContentText = element.getNodeValue();
				if(curentContentText == null) {
					curentContentText = "";
				}
				curentContentText.replaceAll("\t","").replaceAll("\n", "").replaceAll(" ", "");
				
				// concat node text  content 
				if (!curentContentText.isEmpty())
					result += curentContentText+"_";
//				System.out.println("\n[DEBUG - LEVEL2] --> current node contains text : " + 
//						element.getTextContent().replaceAll("\t","").replaceAll("\n", "").replaceAll(" ", "") + "\n");
			}
		} 
		return result;
	}
		
	/**
	 * Stream To DOM Document transformer
	 *  
	 * @param stream
	 * @return
	 */
	public static Document fromStreamToDocument(InputStream stream){
		Document result = null;
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();  
			Document parsedXmlDoc = builder.parse(stream);
			result = parsedXmlDoc;
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(SAXException se){
			se.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return result;
	}

}
