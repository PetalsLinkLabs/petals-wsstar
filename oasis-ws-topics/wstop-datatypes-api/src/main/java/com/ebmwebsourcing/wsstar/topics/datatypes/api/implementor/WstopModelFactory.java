package com.ebmwebsourcing.wsstar.topics.datatypes.api.implementor;

import java.net.URI;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType.Topic;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopReader;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopWriter;

public interface WstopModelFactory {

	 QueryExpressionType createWstopModelMessagePattern(URI dialect);
	
	 TopicNamespaceType createWstopModelTopicNamespaceType(URI targetNamespace);
	
	 Topic createWstopModelTopicNamespaceTypeTopic(String name);

	 TopicSetType createWstopModelTopicSetType();
	
	 TopicType createWstopModelTopicType(String name);
		
	 WstopReader getWstopModelReader();
	
	 WstopWriter getWstopModelWriter();

}
