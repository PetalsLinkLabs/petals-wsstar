package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.net.URI;
import java.util.List;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;

public interface NotificationProducerRP {

	 Boolean isFixedTopicSet();
	
	 void setFixedTopicSet(Boolean b);
	
	 List<TopicExpressionType> getTopicExpressions();
	
	 void addTopicExpression(TopicExpressionType value);
	
	 List<URI> getTopicExpressionDialects();
	
	 void addTopicExpressionDialect(URI dialect);
	
	 TopicSetType getTopicSet();
	
	 void setTopicSet(TopicSetType topicSet);

}
