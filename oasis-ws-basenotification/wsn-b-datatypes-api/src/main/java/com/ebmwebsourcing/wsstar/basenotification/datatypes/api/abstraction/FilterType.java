package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.util.List;

public interface FilterType {

	 List<TopicExpressionType> getTopicExpressions();
	
	 void addTopicExpression(TopicExpressionType value);
	
	 List<MessageContentExpression> getMessageContentExpressions();
	
	 void addMessageContentExpression(MessageContentExpression value);
	
	 List<ProducerPropertiesExpression> getProducerPropertiesExpressions();
	
	 void addProducerPropertiesExpression(ProducerPropertiesExpression value);

}
