package com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;

public interface WstopReader {

	 QueryExpressionType readMessagePattern(Document document) throws WstopException;
	
	 TopicNamespaceType readTopicNamespaceType(Document document) throws WstopException;
	
	 TopicSetType readTopicSetType(Document document) throws WstopException;

	 TopicSetType readTopicSetType(InputSource source)	throws WstopException;
		
	 TopicType readTopicType(Document document) throws WstopException;
}
