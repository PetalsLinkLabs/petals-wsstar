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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.Wsnb4ServUtils;

/**
 * @author tdejean - eBM WebSourcing
 */
class XPathDOMAnalyzer {

	private static Logger log = Logger.getLogger(XPathDOMAnalyzer.class.getName());

	XPathDOMAnalyzer() {

	}

	final synchronized NodeList evaluate(final String xpathExpression, final Document document,
			final Map<String, String> contextMap) throws WsnbException {
		final List<String> xpathExpressions = new ArrayList<String>();
		xpathExpressions.add(xpathExpression);
		
		NamespaceContext context = new NamespaceContextMap(contextMap);
				
		NodeList nodes = null;
		try {
		
			if(document != null) {
				for(final String xpathExprItem: xpathExpressions) {

					final XPath engine = XPathFactory.newInstance().newXPath();
					engine.setNamespaceContext(context);
					XPathExpression expr;

					expr = engine.compile(xpathExprItem);

					final Object result = expr.evaluate(document, XPathConstants.NODESET);
					
					if (result instanceof NodeList) {
						nodes = (NodeList) result;
					
						
						
//						if (log.isLoggable(Level.FINE)) {
//							log.fine("this xpath expression " + xpathExpression + " matchs with: \n " + Wsnb4ServUtils.prettyPrint(document.getOwnerDocument()));
//						}
						break;
					}
				}

				if (nodes == null && log.isLoggable(Level.FINE)) {
					log.fine("No xpath expressions " + xpathExpressions + " match with: \n " + Wsnb4ServUtils.prettyPrint(document.getOwnerDocument()));
				}
			}
		} catch (XPathExpressionException e) {
			throw new WsnbException(e);
		}
		
		return nodes;
	}

	/**
	 * An implementation of <a
	 * href="http://java.sun.com/javase/6/docs/api/javax/xml/namespace/NamespaceContext.html">
	 * NamespaceContext </a>. Instances are immutable.
	 * 
	 * @author McDowell
	 */
	private class NamespaceContextMap implements
	    NamespaceContext {

	  private final Map<String, String> prefixMap;
	  private final Map<String, Set<String>> nsMap;

	  /**
	   * Constructor that takes a map of XML prefix-namespaceURI values. A defensive
	   * copy is made of the map. An IllegalArgumentException will be thrown if the
	   * map attempts to remap the standard prefixes defined in the NamespaceContext
	   * contract.
	   * 
	   * @param prefixMappings
	   *          a map of prefix:namespaceURI values
	   */
	  NamespaceContextMap(
	      Map<String, String> prefixMappings) {
	    prefixMap = createPrefixMap(prefixMappings);
	    nsMap = createNamespaceMap(prefixMap);
	  }

//	  /**
//	   * Convenience constructor.
//	   * 
//	   * @param mappingPairs
//	   *          pairs of prefix-namespaceURI values
//	   */
//	  public NamespaceContextMap(String... mappingPairs) {
//	    this(toMap(mappingPairs));
//	  }

//	  private static Map<String, String> toMap(
//	      String... mappingPairs) {
//	    Map<String, String> prefixMappings = new HashMap<String, String>(
//	        mappingPairs.length / 2);
//	    for (int i = 0; i < mappingPairs.length; i++) {
//	      prefixMappings
//	          .put(mappingPairs[i], mappingPairs[++i]);
//	    }
//	    return prefixMappings;
//	  }

	  private Map<String, String> createPrefixMap(
	      Map<String, String> prefixMappings) {
	    Map<String, String> prefixMap = new HashMap<String, String>(
	        prefixMappings);
	    addConstant(prefixMap, XMLConstants.XML_NS_PREFIX,
	        XMLConstants.XML_NS_URI);
	    addConstant(prefixMap, XMLConstants.XMLNS_ATTRIBUTE,
	        XMLConstants.XMLNS_ATTRIBUTE_NS_URI);
	    return Collections.unmodifiableMap(prefixMap);
	  }

	  private void addConstant(Map<String, String> prefixMap,
	      String prefix, String nsURI) {
	    String previous = prefixMap.put(prefix, nsURI);
	    if (previous != null && !previous.equals(nsURI)) {
	      throw new IllegalArgumentException(prefix + " -> "
	          + previous + "; see NamespaceContext contract");
	    }
	  }

	  private Map<String, Set<String>> createNamespaceMap(
	      Map<String, String> prefixMap) {
	    Map<String, Set<String>> nsMap = new HashMap<String, Set<String>>();
	    for (Map.Entry<String, String> entry : prefixMap
	        .entrySet()) {
	      String nsURI = entry.getValue();
	      Set<String> prefixes = nsMap.get(nsURI);
	      if (prefixes == null) {
	        prefixes = new HashSet<String>();
	        nsMap.put(nsURI, prefixes);
	      }
	      prefixes.add(entry.getKey());
	    }
	    for (Map.Entry<String, Set<String>> entry : nsMap
	        .entrySet()) {
	      Set<String> readOnly = Collections
	          .unmodifiableSet(entry.getValue());
	      entry.setValue(readOnly);
	    }
	    return nsMap;
	  }

	  @Override
	  public String getNamespaceURI(String prefix) {
	    checkNotNull(prefix);
	    String nsURI = prefixMap.get(prefix);
	    return nsURI == null ? "" : nsURI;
	  }

	  @Override
	  public String getPrefix(String namespaceURI) {
	    checkNotNull(namespaceURI);
	    Set<String> set = nsMap.get(namespaceURI);
	    return set == null ? null : set.iterator().next();
	  }

	  @Override
	  public Iterator<String> getPrefixes(String namespaceURI) {
	    checkNotNull(namespaceURI);
	    Set<String> set = nsMap.get(namespaceURI);
	    return set.iterator();
	  }

	  private void checkNotNull(String value) {
	    if (value == null) {
	      throw new IllegalArgumentException("null");
	    }
	  }

//	  /**
//	   * @return an unmodifiable map of the mappings in the form prefix-namespaceURI
//	   */
//	  public Map<String, String> getMap() {
//	    return prefixMap;
//	  }
	}
}
