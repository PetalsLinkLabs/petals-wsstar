/**
 * Copyright (c) 2009 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * $Id$
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.wsnb.services.impl.topic;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.namespace.QName;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ebmwebsourcing.easycommons.xml.XMLPrettyPrinter;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;

/**
 * 
 * This class must implement the singleton pattern
 * (solution chosen : see http://en.wikipedia.org/wiki/Singleton_pattern)
 * 
 * @author tdejean - eBM WebSourcing
 *
 */
public class TopicsManagerEngine {

//	private static final String PROBLEM_SUPPORTED_TOPICS_MSG = 
//		"There is a problem with your configuration file of \"supported TopicSet\". (does it  well formatted?)";
	
	private static final String FLAG_TOPIC_EQUAL_TRUE = "[@wstop:topic='true']";
			
//	private final URI[] supportedDialects = {
//    	WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI,
//    	WstopConstants.FULL_TOPIC_EXPRESSION_DIALECT_URI,
//    	WstopConstants.CONCRETE_TOPIC_EXPRESSION_DIALECT_URI,
//    	WstopConstants.SIMPLE_TOPIC_EXPRESSION_DIALECT_URI};
//	
	/**
	 * tree of current supported topics. Also contains current managed resources uuids
	 * (uuids of : subscription, registration, subscription on Producer, currentMessage, ...)
	 * Typed as JDOM Document in order to use XPATH engine.
	 */
	//private Document supportedTopics = null;

	/**
	 * Logger
	 */
	//private Logger logger = null;

	/**
	 * XPATH engine
	 */
	private final XPathDOMAnalyzer xpathAnalyser;
			
	/**
	 * Constructor 
	 * 
	 * @param supportedTopicSetConfig supported topic set tree as {@link InputStream}
	 */
	public TopicsManagerEngine(/*final InputStream supportedTopicSetConfig*/) {		

		//this.logger = Logger.getLogger(TopicsManagerEngine.class.getName());
		this.xpathAnalyser = new XPathDOMAnalyzer();	
	}
	
	/**
	 * build the {@link List} of "Concrete" {@link TopicExpressionType} parameter
	 * from the "XPAth" {@link TopicExpressionType} object given as parameter 
	 * 
	 * @param topicExpression topics describe as "full" or "Xpath" topic expression
	 * @return the {@link} of requested topics formatted as "Concrete" {@link TopicExpressionType} object
	 * @throws WsnbException
	 */
	public List<TopicExpressionType> getTopicsAsConcreteTopExpr(TopicExpressionType topicExpression, Document supportedTopicsAsDOM) throws WsnbException{
		
		List<TopicExpressionType> concreteTopics = new CopyOnWriteArrayList<TopicExpressionType>();
		List<QName> namespaces = topicExpression.getTopicNamespaces();
		String contentExpr = topicExpression.getContent();
		
		QName qExpr = QName.valueOf(contentExpr);
		if(qExpr.getNamespaceURI() != null && qExpr.getNamespaceURI().trim().length() > 0) {
			contentExpr = qExpr.getLocalPart() + ":" + qExpr.getLocalPart();
			namespaces.add(new QName(qExpr.getNamespaceURI(), qExpr.getLocalPart(), qExpr.getLocalPart()));
		} else if(contentExpr.contains(":")) {
			// damage mode to path CXF bug that omit namespace
			String prefix    = contentExpr.split(":")[0];
			String localpart = contentExpr.split(":")[1];
			boolean find = false;
			for(QName ns: namespaces) {
				if(ns.getLocalPart().equals(prefix)) {
					find = true;
					break;
				}
			}
			if(!find) {
				contentExpr = localpart;
			}
		}
		
		NodeList topicNodes = this.lookForNodes(contentExpr, namespaces,supportedTopicsAsDOM);
		
		// build list of concrete topicExpression representing targeted node
		for (int i = 0; i < topicNodes.getLength(); i++) {
			 Node currentNode = topicNodes.item(i);
			 if (currentNode.getNodeType() == Element.ELEMENT_NODE){
				concreteTopics.add(this.getConcreteTopicExpression(currentNode,namespaces));
			 }			
		}		
		return concreteTopics;
	}
	
	/**
	 * evaluate a "Qualified" TopicExpression content respect to
	 * a given TopicSet formatted as a DOM xml tree    
	 * 
	 * @param contentExpr
	 * @param namespaces
	 * @param supportedTopicsAsDOM
	 * @return
	 * @throws WsnbException
	 */
	private NodeList lookForNodes(String contentExpr, List<QName> namespaces, Document supportedTopicsAsDOM) throws WsnbException {
		
		// build context (NamespacePrefixMapper)		
		Map<String, String> prefixMappings = new ConcurrentHashMap<String, String>();		
		
		for (QName qNamItem : namespaces) {
			prefixMappings.put(qNamItem.getLocalPart(), qNamItem.getNamespaceURI());
		}		
		prefixMappings.put(WstopConstants.WS_TOPICS_PREFIX, WstopConstants.WS_TOPICS_NAMESPACE_URI);
		
		// Format the "XPATH" expression to evaluate
		String expressionToEvalute = "//" + contentExpr + ((!contentExpr.endsWith(FLAG_TOPIC_EQUAL_TRUE))?FLAG_TOPIC_EQUAL_TRUE:"");
		
		// Evalute expression respect to 
		return this.xpathAnalyser.evaluate(expressionToEvalute, supportedTopicsAsDOM, prefixMappings);
		
	}
	
	/**
	 * build the "Concrete" {@link TopicExpressionType} object
	 * 	associated to a Given topic {@link Node}
	 *  (Note : use "Concrete" Dialect to describe exactly one topic;
	 *   see WS-Topics specification)
	 * 
	 * @param topicNode the {@link Node} element in the XML tree
	 * @param namespaces the namespace associated to XML topicSet
	 * @return the "Concrete" TopicExp
	 * @throws WsnbException
	 */
	private TopicExpressionType getConcreteTopicExpression(Node topicNode,List<QName> namespaces) throws WsnbException{
		TopicExpressionType concreteTopExp = null;		
		
		Element currentElement = (Element) topicNode;
		Node currentNode = topicNode;
		
		String content = "";
		
		do {
			content = currentElement.getNodeName() + ((content.length()>0)?"/":"")+ content;
			currentNode = currentNode.getParentNode();
			
			currentElement = (currentNode != null)?(Element) currentNode:null;			
		} while (currentElement != null && 
				(!currentElement.getNodeName().equals(WstopConstants.TOPIC_SET_QNAME.getPrefix()+":"+WstopConstants.TOPIC_SET_QNAME.getLocalPart())));	
				
		concreteTopExp = RefinedWsnbFactory.getInstance().createTopicExpressionType(WstopConstants.CONCRETE_TOPIC_EXPRESSION_DIALECT_URI);
		concreteTopExp.setContent(content);
		try {
			for (QName qNameItem : namespaces) {
				concreteTopExp.addTopicNamespace(qNameItem.getLocalPart(), new URI(qNameItem.getNamespaceURI()));
			}						
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		}
		
		return concreteTopExp;
	}
	
	/**
	 * Check if a given topic, described as "concrete" TopicExpression, is still supported
	 * after "TopicSet" resource property update.
	 * 
	 * @param concreteTopicExpression the topic to check (as {@link TopicExpressionType} instance.
	 * @param supportedTopicsAsDOM the current "TopicSet" resource property value formated as {@link Document}
	 * 
	 * @return true if topic is still supported, false if not 
	 * @throws WsnbException 
	 */
	public boolean isSupportedTopic(TopicExpressionType concreteTopicExpression, Document supportedTopicsAsDOM) throws WsnbException{

		boolean result = false;

		if (concreteTopicExpression.getDialect().equals(WstopConstants.CONCRETE_TOPIC_EXPRESSION_DIALECT_URI)) { 

			NodeList nodes = 
				this.lookForNodes(concreteTopicExpression.getContent(), concreteTopicExpression.getTopicNamespaces(), supportedTopicsAsDOM);

			result = (nodes != null && nodes.getLength() == 1);
		} else {
			
		}
		return result;
	}
	
}
