package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.net.URI;
import java.util.List;

import javax.xml.namespace.QName;

public interface TopicExpressionType {

	 String getContent();
	
	 void setContent(String value);

	 URI getDialect();
	
	 void setDialect(URI value);
	
	/**
	 * implementation  of a TopicNamespace :
	 *  QName(uri, prefix), "xmlns");
	 */
	 List<QName> getTopicNamespaces();
	
	/**
	 * implementation  of a TopicNamespace :
	 *  QName(uri, prefix), "xmlns");
	 */
	 void addTopicNamespace(String prefix, URI uri);
		
}
