package com.ebmwebsourcing.wsstar.wsnb.services.impl.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * Utils class : provides some xml file converting
 * 				methods
 * 
 * @author tdejean - PetalsLink
 * 
 */
public class WsnbServiceUnitTestUtils {
	
	public static Document convertFromUriToDocument(URI uri){
		Document result = null;
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();    		    		
			File xml = new File(uri.getPath());
			result = builder.parse(xml);

		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(SAXException se){
			se.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return result;
	}
	
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
	
	public static Document fromFileTodocument(File file) {

		Document result = null;		
		try{
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			dbfac.setNamespaceAware(true);
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			result = docBuilder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static org.jdom.Document fromStreamToJDOMDocument(InputStream xmlFileAstream){
		org.jdom.Document result = null;
		try {
			SAXBuilder builder = new SAXBuilder();
			result = builder.build(xmlFileAstream);
		} catch(JDOMException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	public static void displayEltAndItsChilds(Element elt, String ns, int level){				

		System.out.println(" ---  xml tree depth : " + level); 
		System.out.println("topic name : " + elt.getLocalName());
		System.out.println("topic namespace : " + ((elt.getNamespaceURI() != null)?elt.getNamespaceURI():ns));
		
		NodeList childs = elt.getChildNodes();
		for (int i = 0; i < childs.getLength();i++) {
			if (childs.item(i).getNodeType() == Node.ELEMENT_NODE){
				WsnbServiceUnitTestUtils.displayEltAndItsChilds((Element)childs.item(i),((elt.getNamespaceURI() != null)?elt.getNamespaceURI():ns), level+1);
			}
		}
		System.out.println("~~~~~~~~~~~~~");
	}
	
}
