package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;

public interface NotificationMessageHolderType {

	 interface Message{
		
		 Element getAny();
		
		 void setAny(Element value);
		
	}

	 TopicExpressionType getTopic();
	
	 void setTopic(TopicExpressionType concreteTopic);

	 Message getMessage();
	
	 void setMessage(Message msg);

	 EndpointReferenceType getSubscriptionReference();
	
	 void setSubscriptionReference(EndpointReferenceType value);

	 EndpointReferenceType getProducerReference();
	
	 void setProducerReference(EndpointReferenceType newNotificationMsgOwner);
}
