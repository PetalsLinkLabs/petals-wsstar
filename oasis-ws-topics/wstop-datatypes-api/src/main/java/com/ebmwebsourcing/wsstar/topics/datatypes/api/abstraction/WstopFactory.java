package com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction;

import java.net.URI;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType.Topic;

public interface WstopFactory {

	 QueryExpressionType createMessagePattern(URI dialect);
	
	 TopicNamespaceType createTopicNamespaceType(URI targetNamespace);
	
	 Topic createTopicNamespaceTypeTopic(String name);
	 
	 TopicSetType createTopicSetType();
	
	 TopicType createTopicType(String name);
		
	 WstopReader getWstopReader();
	
	 WstopWriter getWstopWriter();

}
