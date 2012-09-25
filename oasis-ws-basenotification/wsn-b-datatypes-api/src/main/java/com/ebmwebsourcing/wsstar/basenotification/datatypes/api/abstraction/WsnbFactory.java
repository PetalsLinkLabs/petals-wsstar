package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType.Message;


public interface WsnbFactory {
	
	// ~~~~~~~~~~ Datatype creation methods ~~~~~~~~~~~ 
	
	 CreatePullPoint createCreatePullPoint() ;
	
	 CreatePullPointResponse createCreatePullPointResponse();
	
	 DestroyPullPoint createDestroyPullPoint();
	
	 DestroyPullPointResponse createDestroyPullPointResponse();
	
	 FilterType createFilterType();
	
	 GetCurrentMessage createGetCurrentMessage(TopicExpressionType concreteTopic);
	
	 GetCurrentMessageResponse createGetCurrentMessageResponse(Message notification);
	
	 GetMessages createGetMessages();
	
	 GetMessagesResponse createGetMessagesResponse();
		
	 InvalidFilterFaultType createInvalidFilterFaultType(Date timestamp,List<QName> unknownFilters);
	
	 InvalidMessageContentExpressionFaultType createInvalidMessageContentExpressionFaultType(Date timestamp);
	
	 InvalidProducerPropertiesExpressionFaultType createInvalidProducerPropertiesExpressionFaultType(Date timestamp);
	
	 InvalidTopicExpressionFaultType createInvalidTopicExpressionFaultType(Date timestamp);
	
	 MessageContentExpression createMessageContentExpression(URI dialect);

	 MultipleTopicsSpecifiedFaultType createMultipleTopicsSpecifiedFaultType(Date timestamp);
	
	 NoCurrentMessageOnTopicFaultType createNoCurrentMessageOnTopicFaultType(Date timestamp);
	
	 NotificationMessageHolderType createNotificationMessageHolderType(NotificationMessageHolderType.Message message);
	
	 NotificationMessageHolderType.Message createNotificationMessageHolderTypeMessage(Element message);
	
	 NotificationProducerRP createNotificationProducerRP();
	
	 Notify createNotify(NotificationMessageHolderType notifMsg);
	
	 NotifyMessageNotSupportedFaultType createNotifyMessageNotSupportedFaultType(Date timestamp);
	
	 PauseFailedFaultType createPauseFailedFaultType(Date timestamp);
	
	 PauseSubscription createPauseSubscription();
	
	 PauseSubscriptionResponse createPauseSubscriptionResponse();	
		
	 ProducerPropertiesExpression createProducerPropertiesExpression(URI dialect);

	 Renew createRenew();
	
	 RenewResponse createRenewResponse(Date termTime);
	
	 ResumeFailedFaultType createResumeFailedFaultType(Date timestamp);
	
	 ResumeSubscription createResumeSubscription();
	
	 ResumeSubscriptionResponse createResumeSubscriptionResponse();
	
	 Subscribe createSubscribe(EndpointReferenceType consumerRef);
	
	 SubscribeCreationFailedFaultType createSubscribeCreationFailedFaultType(Date timestamp);
	
	 SubscribeResponse createSubscribeResponse(EndpointReferenceType subscriptionRef);
	
	 SubscriptionManagerRP createSubscriptionManagerRP(EndpointReferenceType consumerRef);
	
	 SubscriptionPolicyType createSubscriptionPolicyType();
	
	 TopicExpressionType createTopicExpressionType(URI dialect);
	
	 TopicExpressionDialectUnknownFaultType createTopicExpressionDialectUnknownFaultType(Date timestamp);
	
	 TopicNotSupportedFaultType createTopicNotSupportedFaultType(Date timestamp);
	
	 UnableToCreatePullPointFaultType createUnableToCreatePullPointFaultType(Date timestamp);
	
	 UnableToDestroyPullPointFaultType createUnableToDestroyPullPointFaultType(Date timestamp);
	
	 UnableToDestroySubscriptionFaultType createUnableToDestroySubscriptionFaultType(Date timestamp);
	
	 UnableToGetMessagesFaultType createUnableToGetMessagesFaultType(Date timestamp);
	
	 UnacceptableInitialTerminationTimeFaultType createUnacceptableInitialTerminationTimeFaultType(Date timestamp,Date minTermTime);
	
	 UnacceptableTerminationTimeFaultType createUnacceptableTerminationTimeFaultType(Date timestamp,Date minTermTime);
	
	 UnrecognizedPolicyRequestFaultType createUnrecognizedPolicyRequestFaultType(Date timestamp);
	
	 Unsubscribe createUnsubscribe();
	
	 UnsubscribeResponse createUnsubscribeResponse();
	
	 UnsupportedPolicyRequestFaultType createUnsupportedPolicyRequestFaultType(Date timestamp);
	
	 UseRaw createUseRaw();
	
	
	// ~~~~~~~~~ Reader/Writer singleton(s) getter ~~~~~~~~~~
	
	 WsnbReader getWsnbReader();
	
	 WsnbWriter getWsnbWriter();
	
	
}
