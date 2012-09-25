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
package com.ebmwebsourcing.wsstar.topics.datatypes.api.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;

public class WstopUnitTestsUtilsTests {
		
	// ---------------------------------------------------------
	public static final String WSTOP_SPECIFICATION_NAME = "OASIS \"WS-Topics\"";
	
	public static final String WSTOP_XML_SCHEMA_PATH = "/schemas/t-1.xsd";	
	
	public static final String[] WSTOP_XML_SCHEMAS_PATHS = {
		WstopUnitTestsUtilsTests.WSTOP_XML_SCHEMA_PATH};
	// --------------------------------------------------------

	/**
	 * create sample of topicsTrees like in OASIS WS-Topics specification (p.11/40) 
	 */
	public static List<Element> createDefaultTopicTrees() {
		List<Element> topicsTrees = new ArrayList<Element>();		
		try{

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder builder;
			builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element rootTopic = null, childTopic = null;
			Attr topicAttr = doc.createAttributeNS(WstopConstants.WS_TOPICS_NAMESPACE_URI,"topic");
			topicAttr.setPrefix(WstopConstants.WS_TOPICS_PREFIX);
			topicAttr.setNodeValue("true");
			
			// --- create first root topic :
			//	<tns:t1 wstop:topic="true">
			//		<t2 wstop:topic="true"/>
			//  <tns:t1/>
			rootTopic = doc.createElementNS("http://exemple.org/topics/exemple1","t1");
			rootTopic.setPrefix("tns");
			rootTopic.setAttributeNodeNS((Attr) topicAttr.cloneNode(true));
			
			childTopic = doc.createElement("t2");
			childTopic.setAttributeNodeNS((Attr) topicAttr.cloneNode(true));
			
			rootTopic.appendChild(childTopic);
			topicsTrees.add(rootTopic);
			// --- create second root topic :
			//	<tns:t4">
			//		<t5 wstop:topic="true"/>
			//  <tns:t4/>
			rootTopic = doc.createElementNS("http://exemple.org/topics/exemple1","t4");
			rootTopic.setPrefix("tns");
			
			childTopic = doc.createElement("t5");
			childTopic.setAttributeNodeNS((Attr) topicAttr.cloneNode(true));
			
			rootTopic.appendChild(childTopic);
			topicsTrees.add(rootTopic);
						
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}
		return topicsTrees;
	}

}
