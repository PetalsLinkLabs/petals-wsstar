package com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;

public interface WstopWriter {

	 Document writeMessagePatternAsDOM(QueryExpressionType value) throws WstopException;
	
	 Document writeTopicNamespaceTypeAsDOM(TopicNamespaceType value) throws WstopException;
	
	 Document writeTopicSetTypeAsDOM(TopicSetType value) throws WstopException;

	 Document writeTopicTypeAsDOM(TopicType value) throws WstopException;
	
}
