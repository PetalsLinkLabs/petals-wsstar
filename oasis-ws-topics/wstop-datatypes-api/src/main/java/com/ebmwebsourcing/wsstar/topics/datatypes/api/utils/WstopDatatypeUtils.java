package com.ebmwebsourcing.wsstar.topics.datatypes.api.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.easycommons.research.util.dom.DOMUtil;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.refinedabstraction.RefinedWstopFactory;

public final class WstopDatatypeUtils {

	private WstopDatatypeUtils(){}
	// ------------------------------------------------------------------------------------------------------
	// --- Manipulations on WS-Topics datatypes (creation of TopicSetType From given TopicNamespace, ...) ---
	// ------------------------------------------------------------------------------------------------------  

	/**
	 * Add topicNamespace topics to a given topicSet 
	 * 
	 * @param topicSet the topic Set to upgrade
	 * @param topicNamespace the topic namespace which topics have to be added to the topicSet
	 * @param prefixToUse prefic name to link to the targetNamespace
	 * @return the topicSet with new added topics
	 * @throws WstopException
	 */
	public static TopicSetType addTopicsFromTopicNamespaceToTopicSet(TopicSetType topicSet, TopicNamespaceType topicNamespace, String prefixToUse) throws WstopException {
		TopicSetType result;

		// --- duplicate given "TopicSet" type object 
		result = RefinedWstopFactory.getInstance().createTopicSetType();
		for (Element treeItem : topicSet.getTopicsTrees()) {					
			result.addTopicsTree(treeItem);
		}

		// --- convert TopicNamespace's topic to TopicTree XML Element
		List<Element> topicsToAddToTopicSet = WstopDatatypeUtils.fromTopicNamespaceToTopicTreesElt(topicNamespace, prefixToUse);

		// --- add topic's trees to TopicSet 
		for (Element topicTreeItem : topicsToAddToTopicSet) {
			result.addTopicsTree(topicTreeItem);
		}

		return result;
	}

	/**
	 * Convert a {@link TopicNamespaceType} object to a {@link List<Element>} representing
	 * its topic's trees
	 * 
	 * @param topicNamespace the {TopicNamespaceType} which topic's trees have to be converted
	 * @param prefixToUse prefix to link to the targetNamespace's of the {@link TopicNamespaceType} object
	 * @return list of {@link Element} representing the {@link TopicNamespaceType} intance's topics
	 * @throws WstopException
	 */
	private static List<Element> fromTopicNamespaceToTopicTreesElt(TopicNamespaceType topicNamespace, String prefixToUse) throws WstopException {

		List<Element> treesElt = new ArrayList<Element>();				
		List<TopicNamespaceType.Topic> topicsFromTopicNs = new ArrayList<TopicNamespaceType.Topic>();

		if(topicNamespace != null) {
			topicsFromTopicNs = topicNamespace.getTopics();
			Document doc = null;
			try {
				doc = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();
			} catch (ParserConfigurationException e) {
				throw new WstopException(e);
			}



			// --- build a topics'tree XML Element for each topic
			List<TopicType> children = null;
			for (TopicNamespaceType.Topic topicItem : topicsFromTopicNs) {
				Element currentTopicTree = doc.createElementNS(topicNamespace.getNamespace().toString(), topicItem.getName());
				Attr topicAttr = doc.createAttributeNS(WstopConstants.WS_TOPICS_NAMESPACE_URI, "topic");
				topicAttr.setPrefix(WstopConstants.WS_TOPICS_PREFIX);
				topicAttr.setNodeValue("true");
				currentTopicTree.setPrefix(prefixToUse);					
				currentTopicTree.setAttributeNodeNS(topicAttr);
				// --- recursive build : 
				children = topicItem.getTopics();
				if (children != null && children.size() > 0){
					try {
						currentTopicTree.appendChild(WstopDatatypeUtils.addChildren(currentTopicTree,topicItem.getTopics(),doc,(Attr) topicAttr.cloneNode(true)));
					} catch(Exception e) {
						// do notihng
					}
				}

				treesElt.add(currentTopicTree);
			}			
		}
		return treesElt;		
	}

	/**
	 * Add child topics' {@link Element} representation to current Element
	 * (recursively called)
	 * @param currentTopicElt current Topic {@link Element} representation 
	 * @param currentchildTopic currentChildTo convert as {@link Element} and add  
	 * @return 
	 */
	private static Element addChildren(Element parentTopicElt, List<TopicType> childTopics, Document docToBuildElt, Attr topicAttr){
		Element result = null, currentChild = null;
		List<TopicType> children = null;
		for (TopicType topicItem : childTopics) {
			currentChild = docToBuildElt.createElement(topicItem.getName());
			currentChild.setAttributeNodeNS((Attr) topicAttr.cloneNode(true));
			children = topicItem.getTopics();
			if (children != null && children.size() > 0){
				parentTopicElt.appendChild(WstopDatatypeUtils.addChildren(currentChild, topicItem.getTopics(), 
						docToBuildElt,(Attr) topicAttr.cloneNode(true)));
			}
		}				
		return result;
	}



	//    public TopicSetType createTopicSetFromTopicNamespace(final TopicNamespaceType topicns, final List<String> topics) throws WSNotificationException {
	//		TopicSetType res = null;
	//
	//		TopicNamespaceType topicNS = null;
	//
	//		org.w3c.dom.Document domDocument = WSNotificationWriter.getInstance().writeTopicNamespaceType(topicns);
	//
	//		// convert dom to jdom
	//		final DOMBuilder builder = new DOMBuilder();
	//		final org.jdom.Document jdomDocument = builder.build(domDocument);
	//
	//		this.addSupportedTopicAttr(jdomDocument.getRootElement().getChildren(), topics);
	//
	//		// convert jdom to dom
	//		final DOMOutputter converter = new DOMOutputter();
	//		try {
	//			domDocument = converter.output(jdomDocument);
	//		} catch (final JDOMException e) {
	//			throw new WSNotificationException(e);
	//		}
	//
	//		// convert dom to topicSet
	//		topicNS = WSNotificationReader.getInstance().readTopicNamespaceType(domDocument);
	//
	//		res = this.createTopicSetFromSupportedTopicNamespace(topicNS);
	//
	//		return res;
	//	}
	//
	//	private void addSupportedTopicAttr(final List<org.jdom.Element> children, final List<String> topics) {
	//		for(final org.jdom.Element child: children) {
	//			if(child.getName().equals(WstopConstants.TOPIC_QNAME.getLocalPart())&&child.getNamespaceURI().equals(WstopConstants.TOPIC_QNAME.getNamespaceURI())) {
	//				if(topics.contains(child.getAttribute("name").getValue())) {
	//					child.setAttribute(WsnExtensionConstants.SUPPORTED_QNAME_ATTR.getLocalPart(),
	//							"true", Namespace.getNamespace(WsnExtensionConstants.SUPPORTED_QNAME_ATTR.getPrefix(),
	//									WsnExtensionConstants.SUPPORTED_QNAME_ATTR.getNamespaceURI()));
	//				}
	//				if(child.getChildren() != null && child.getChildren().size() > 0) {
	//					this.addSupportedTopicAttr(child.getChildren(), topics);
	//				}
	//			}
	//		}
	//	}
	//	
	//	public TopicSetType createTopicSetFromSupportedTopicNamespace(final TopicNamespaceType topicns) throws WSNotificationException {
	//		TopicSetType res = null;
	//
	//		/*
	//		Namespace wstop = Namespace.getNamespace("wstop", "http://docs.oasis-open.org/wsn/t-1");
	//		Namespace tns = Namespace.getNamespace("tns", topicns.getTargetNamespace());
	//		Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
	//
	//
	//		org.jdom.Element root = new org.jdom.Element("TopicSet", wstop);
	//		root.addNamespaceDeclaration(tns);
	//		root.addNamespaceDeclaration(xsi);
	//		root.setAttribute("schemaLocation", "http://docs.oasis-open.org/wsn/t-1 http://docs.oasis-open.org/wsn/t-1.xsd", xsi);
	//		Document doc = new Document(root);
	//		 */
	//		final Namespace tns = Namespace.getNamespace("tns", topicns.getTargetNamespace());
	//		final org.jdom.Element root = this.createEmptyTopicSet();
	//		final Document doc = new Document(root);
	//		this.createTopicSetTree(topicns.getTopics(), root, tns, true);
	//
	//		// convert jdom to dom
	//		org.w3c.dom.Document domDocument = null;
	//		final DOMOutputter converter = new DOMOutputter();
	//		try {
	//			domDocument = converter.output(doc);
	//		} catch (final JDOMException e) {
	//			throw new WSNotificationException(e);
	//		}
	//
	//		// convert dom to topicSet
	//		res = WSNotificationReader.getInstance().readTopicSetType(domDocument);
	//
	//		return res;
	//	}
	//
	//	private org.jdom.Element createEmptyTopicSet() {
	//		final Namespace wstop = Namespace.getNamespace(WstConstants.PREFIX, WstConstants.NAMESPACE_URI);
	//		final Namespace xsi = Namespace.getNamespace(WstConstants.XML_SCHEMA_PREFIX, WstConstants.XML_SCHEMA_NAMESPACE);
	//
	//		final org.jdom.Element root = new org.jdom.Element(WstConstants.TOPIC_SET_QNAME.getLocalPart(), wstop);
	//		root.addNamespaceDeclaration(xsi);
	//		root.setAttribute("schemaLocation", "http://docs.oasis-open.org/wsn/t-1 http://docs.oasis-open.org/wsn/t-1.xsd", xsi);
	//		return root;
	//	}
	//
	//	private void createTopicSetTree(final List<TopicType> topics,
	//			final org.jdom.Element root, final Namespace tns, final boolean first) {
	//
	//		final Namespace wstop = Namespace.getNamespace("wstop", "http://docs.oasis-open.org/wsn/t-1");
	//
	//		for(final TopicType topic: topics) {
	//			org.jdom.Element childTopic = null;
	//			if(first) {
	//				childTopic = new org.jdom.Element(topic.getName(), tns);
	//			} else {
	//				childTopic = new org.jdom.Element(topic.getName());
	//			}
	//			if(WsnSpecificTypeHelper.isTopicSupported(topic) != null && WsnSpecificTypeHelper.isTopicSupported(topic) == true) {
	//				childTopic.setAttribute("topic", "true", wstop);
	//			}
	//			if(topic.getChildren() != null && topic.getChildren().size() > 0) {
	//				this.createTopicSetTree(topic.getChildren(), childTopic, tns, false);
	//			}
	//			root.addContent(childTopic);
	//		}
	//
	//	}
}
