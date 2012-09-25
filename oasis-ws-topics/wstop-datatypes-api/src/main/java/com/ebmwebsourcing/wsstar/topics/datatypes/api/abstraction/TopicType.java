package com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction;

import java.util.List;

import javax.xml.namespace.QName;

public interface TopicType {

	boolean isFinal();
	
	 void setFinal(boolean value);
	
	 QueryExpressionType getMessagePattern();
	
	 void setMessagePattern(QueryExpressionType value);
	
	 List<QName> getMessageTypes();
	
	 void addMessageType(QName value);
	
	 String getName();
	
	 void setName(String value);
	
	 List<TopicType> getTopics();
	
	 void addTopic(TopicType value);
	
}
