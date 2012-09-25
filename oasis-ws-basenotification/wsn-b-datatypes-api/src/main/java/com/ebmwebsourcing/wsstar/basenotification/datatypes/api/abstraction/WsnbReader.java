package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.io.File;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;

public interface WsnbReader {

	 CreatePullPoint readCreatePullPoint(Document document) throws WsnbException;

//	 CreatePullPoint readCreatePullPoint(InputSource source) throws WsnException;
//	
//	 CreatePullPoint readCreatePullPoint(File file) throws WsnException;
//	
//	 CreatePullPoint readCreatePullPoint(URI uri) throws WsnException;
	
	 CreatePullPointResponse readCreatePullPointResponse(Document document) throws WsnbException;
	
	 DestroyPullPoint readDestroyPullPoint(Document document) throws WsnbException;
	
	 DestroyPullPointResponse readDestroyPullPointResponse(Document document) throws WsnbException;
	
	 FilterType readFilterType(Document document) throws WsnbException;
	
	 GetCurrentMessage readGetCurrentMessage(Document document) throws WsnbException;
	
	 GetCurrentMessageResponse readGetCurrentMessageResponse(Document document) throws WsnbException;	
	
	 GetMessages readGetMessages(Document document) throws WsnbException;
	
	 GetMessagesResponse readGetMessagesResponse(Document document) throws WsnbException;
	
	 InvalidFilterFaultType readInvalidFilterFaultType(Document document) throws WsnbException;
	
	 InvalidMessageContentExpressionFaultType readInvalidMessageContentExpressionFaultType(Document document) throws WsnbException;
	
	 InvalidProducerPropertiesExpressionFaultType readInvalidProducerPropertiesExpressionFaultType(Document document) throws WsnbException;
	
	 InvalidTopicExpressionFaultType readInvalidTopicExpressionFaultType(Document document) throws WsnbException;
	
	 MessageContentExpression readMessageContentExpression(Document document) throws WsnbException;

	 MultipleTopicsSpecifiedFaultType readMultipleTopicsSpecifiedFaultType(Document document) throws WsnbException;
	
	 NoCurrentMessageOnTopicFaultType readNoCurrentMessageOnTopicFaultType(Document document) throws WsnbException;
	
	 NotificationMessageHolderType readNotificationMessageHolderType(Document document) throws WsnbException;
	
	 NotificationProducerRP readNotificationProducerRP(Document document) throws WsnbException;
	
	 Notify readNotify(Document document) throws WsnbException;
	
	 NotifyMessageNotSupportedFaultType readNotifyMessageNotSupportedFaultType(Document document) throws WsnbException;
	
	 PauseFailedFaultType readPauseFailedFaultType(Document document) throws WsnbException;
	
	 PauseSubscription readPauseSubscription(Document document) throws WsnbException;
	
	 PauseSubscriptionResponse readPauseSubscriptionResponse(Document document) throws WsnbException;
	
	 ProducerPropertiesExpression readProducerPropertiesExpression(Document document) throws WsnbException;
		
	 Renew readRenew(Document document) throws WsnbException;
	
	 RenewResponse readRenewResponse(Document document) throws WsnbException;
	
	 ResumeFailedFaultType readResumeFailedFaultType(Document document) throws WsnbException;
	
	 ResumeSubscription readResumeSubscription(Document document) throws WsnbException;
	
	 ResumeSubscriptionResponse readResumeSubscriptionResponse(Document document) throws WsnbException;
	
	 Subscribe readSubscribe(Document document) throws WsnbException;
	
	 SubscribeCreationFailedFaultType readSubscribeCreationFailedFaultType(Document document) throws WsnbException;
	
	 SubscribeResponse readSubscribeResponse(Document document) throws WsnbException;
	
	 SubscriptionManagerRP readSubscriptionManagerRP(Document document) throws WsnbException;
	
	 SubscriptionManagerRP readSubscriptionManagerRP(File file)throws WsnbException;
	
	 SubscriptionPolicyType readSubscriptionPolicyType(Document document) throws WsnbException;
	
	 TopicExpressionType readTopicExpressionType(Document document) throws WsnbException;
	
	 TopicExpressionDialectUnknownFaultType readTopicExpressionDialectUnknownFaultType(Document document) throws WsnbException;
	
	 TopicNotSupportedFaultType readTopicNotSupportedFaultType(Document document) throws WsnbException;
	
	 UnableToCreatePullPointFaultType readUnableToCreatePullPointFaultType(Document document) throws WsnbException;
	
	 UnableToDestroyPullPointFaultType readUnableToDestroyPullPointFaultType(Document document) throws WsnbException;
	
	 UnableToDestroySubscriptionFaultType readUnableToDestroySubscriptionFaultType(Document document) throws WsnbException;
	
	 UnableToGetMessagesFaultType readUnableToGetMessagesFaultType(Document document) throws WsnbException;
	
	 UnacceptableInitialTerminationTimeFaultType readUnacceptableInitialTerminationTimeFaultType(Document document) throws WsnbException;
	
	 UnacceptableTerminationTimeFaultType readUnacceptableTerminationTimeFaultType(Document document) throws WsnbException;
	
	 UnrecognizedPolicyRequestFaultType readUnrecognizedPolicyRequestFaultType(Document document) throws WsnbException;
	
	 Unsubscribe readUnsubscribe(Document document) throws WsnbException;
	
	 UnsubscribeResponse readUnsubscribeResponse(Document document) throws WsnbException;
	
	 UnsupportedPolicyRequestFaultType readUnsupportedPolicyRequestFaultType(Document document) throws WsnbException;
	
	 UseRaw readUseRaw(Document document) throws WsnbException;

}
