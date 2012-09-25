package com.ebmwebsourcing.wsstar.topics.datatypes.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

/**
 * Constants of WS-ResourceLiftime
 * 
 * @author Thierry Déjean - eBM WebSourcing
 */
public class WstopConstants {

	protected WstopConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	// ============== WS-Topics "XML Schema" constants ===========================
	
	public static final String WS_TOPICS_NAMESPACE_URI = "http://docs.oasis-open.org/wsn/t-1";

	public static final String WS_TOPICS_PREFIX = "wstop";
		
	public static final QName TOPIC_SET_QNAME = new QName(WS_TOPICS_NAMESPACE_URI, "TopicSet", WS_TOPICS_PREFIX);
	
	public static final QName TOPIC_NAMESPACE_QNAME = new QName(WS_TOPICS_NAMESPACE_URI, "TopicNamespace", WS_TOPICS_PREFIX);
	
	public static final QName TOPIC_QNAME = new QName(WS_TOPICS_NAMESPACE_URI, "Topic", WS_TOPICS_PREFIX);

	public static final QName MESSAGE_PATTERN_QNAME = new QName(WS_TOPICS_NAMESPACE_URI, "MessagePattern", WS_TOPICS_PREFIX);
	
	// ========= WS-Topics - TopicExpression concerns related constants ==========
	
	private static final String ERROR_URI = "The string value used to build java.net.URI Object " +
	"does not respect the URI Syntax (according to RFC-2396/RFC-2732)";	

	private static URI simpleUri;
	
	public static final URI SIMPLE_TOPIC_EXPRESSION_DIALECT_URI = (simpleUri != null)?simpleUri:initSimpleDialectURI();
		
	private static URI initSimpleDialectURI(){
		
		try{
			simpleUri = new URI(WS_TOPICS_NAMESPACE_URI  + "/TopicExpression/Simple");
		} catch (URISyntaxException e) {		
			Logger.getLogger(WstopConstants.class.getSimpleName()).log(Level.WARNING,ERROR_URI,e);
		}
		return simpleUri ;
	}
	
	private static URI concreteUri;
	
	public static final URI CONCRETE_TOPIC_EXPRESSION_DIALECT_URI = (concreteUri != null)?concreteUri:initConcreteDialectURI();
		
	private static URI initConcreteDialectURI(){		
		try{
			concreteUri = new URI(WS_TOPICS_NAMESPACE_URI  + "/TopicExpression/Concrete");
		} catch (URISyntaxException e) {		
			Logger.getLogger(WstopConstants.class.getSimpleName()).log(Level.WARNING,ERROR_URI,e);
		}
		return concreteUri ;
	}

	private static URI fullUri;
	
	public static final URI FULL_TOPIC_EXPRESSION_DIALECT_URI = (fullUri != null)?fullUri:initFullDialectURI();
	
	private static URI initFullDialectURI(){
		try {
			fullUri = new URI(WS_TOPICS_NAMESPACE_URI + "/TopicExpression/Full");
		} catch (URISyntaxException e) {		
			Logger.getLogger(WstopConstants.class.getSimpleName()).log(Level.WARNING,ERROR_URI,e);
		}
		return fullUri; 
	}
	
	private static URI xpathUri;
	
	public static final URI XPATH_TOPIC_EXPRESSION_DIALECT_URI = (xpathUri != null)?xpathUri:initXpathDialectURI();
	
	private static URI initXpathDialectURI(){
		try {
			xpathUri = new URI("http://www.w3.org/TR/1999/REC-xpath-19991116");
		} catch (URISyntaxException e) {		
			Logger.getLogger(WstopConstants.class.getSimpleName()).log(Level.WARNING,ERROR_URI,e);
		}
		return xpathUri; 
	} 	
	
	public static final char SEPARATOR_CHILD_OF = '/';
	
	public static final String SEPARATOR_SUB_TREE_OF = "//";
	 	
	public static final char STAR_WILDCARD = '*';
	
	public static final char POINT_WILDCARD = '.'; 
	
	// ============== WS-Topcis "WSDL" constants : Seems not need ================ //
	
	
}
