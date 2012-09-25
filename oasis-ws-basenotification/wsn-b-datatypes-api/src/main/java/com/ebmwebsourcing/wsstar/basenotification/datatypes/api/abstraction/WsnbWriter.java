package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;

public interface WsnbWriter {
	
	 Document writeCreatePullPointAsDOMAsDOM(CreatePullPoint value) throws WsnbException;
	
//	 String writeCreatePullPointAsString(CreatePullPoint value) throws WsnException throws WsnException;
//	
//	 void writeCreatePullPointToFilesystem(CreatePullPoint value,String path) throws WsnException;
	
	 Document writeCreatePullPointResponseAsDOM(CreatePullPointResponse value) throws WsnbException;
	
	 Document writeDestroyPullPointAsDOM(DestroyPullPoint value) throws WsnbException;
	
	 Document writeDestroyPullPointResponseAsDOM(DestroyPullPointResponse value) throws WsnbException;
	
	 Document writeFilterTypeAsDOM(FilterType value) throws WsnbException;
	
	 Document writeGetCurrentMessageAsDOM(GetCurrentMessage value) throws WsnbException;
	
	 Document writeGetCurrentMessageResponseAsDOM(GetCurrentMessageResponse value) throws WsnbException;

	 Document writeGetMessagesAsDOM(GetMessages value) throws WsnbException;
	
	 Document writeGetMessagesResponseAsDOM(GetMessagesResponse value) throws WsnbException;
	
	 Document writeInvalidFilterFaultTypeAsDOM(InvalidFilterFaultType value) throws WsnbException;
	
	 Document writeInvalidMessageContentExpressionFaultTypeAsDOM(InvalidMessageContentExpressionFaultType value) throws WsnbException;
	
	 Document writeInvalidProducerPropertiesExpressionFaultTypeAsDOM(InvalidProducerPropertiesExpressionFaultType value) throws WsnbException;
	
	 Document writeInvalidTopicExpressionFaultTypeAsDOM(InvalidTopicExpressionFaultType value) throws WsnbException;

	 Document writeMessageContentExpressionAsDOM(MessageContentExpression value) throws WsnbException;

	 Document writeMultipleTopicsSpecifiedFaultTypeAsDOM(MultipleTopicsSpecifiedFaultType value) throws WsnbException;
	
	 Document writeNoCurrentMessageOnTopicFaultTypeAsDOM(NoCurrentMessageOnTopicFaultType value) throws WsnbException;
	
	 Document writeNotificationMessageHolderTypeAsDOM(NotificationMessageHolderType value) throws WsnbException;
	
	 Document writeNotificationProducerRPAsDOM(NotificationProducerRP value) throws WsnbException;
	
	 Document writeNotifyAsDOM(Notify value) throws WsnbException;
	
	 Document writeNotifyMessageNotSupportedFaultTypeAsDOM(NotifyMessageNotSupportedFaultType value) throws WsnbException;
	
	 Document writePauseFailedFaultTypeAsDOM(PauseFailedFaultType value) throws WsnbException;
	
	 Document writePauseSubscriptionAsDOM(PauseSubscription value) throws WsnbException;
	
	 Document writePauseSubscriptionResponseAsDOM(PauseSubscriptionResponse value) throws WsnbException;
	
	 Document writeProducerPropertiesExpressionAsDOM(ProducerPropertiesExpression value) throws WsnbException;
		
	 Document writeRenewAsDOM(Renew value) throws WsnbException;
	
	 Document writeRenewResponseAsDOM(RenewResponse value) throws WsnbException;
	
	 Document writeResumeFailedFaultTypeAsDOM(ResumeFailedFaultType value) throws WsnbException;
	
	 Document writeResumeSubscriptionAsDOM(ResumeSubscription value) throws WsnbException;
	
	 Document writeResumeSubscriptionResponseAsDOM(ResumeSubscriptionResponse value) throws WsnbException;
	
	 Document writeSubscribeAsDOM(Subscribe value) throws WsnbException;
	
	 Document writeSubscribeCreationFailedFaultTypeAsDOM(SubscribeCreationFailedFaultType value) throws WsnbException;
	
	 Document writeSubscribeResponseAsDOM(SubscribeResponse value) throws WsnbException;
	
	 Document writeSubscriptionManagerRPAsDOM(SubscriptionManagerRP value) throws WsnbException;
	
	 void writeSubscriptionManagerRPToFilesystem(SubscriptionManagerRP value,String path) throws WsnbException;

	 Document writeSubscriptionPolicyTypeAsDOM(SubscriptionPolicyType value) throws WsnbException;
	
	 Document writeTopicExpressionTypeAsDOM(TopicExpressionType value) throws WsnbException;
	
	 Document writeTopicExpressionDialectUnknownFaultTypeAsDOM(TopicExpressionDialectUnknownFaultType value) throws WsnbException;
	
	 Document writeTopicNotSupportedFaultTypeAsDOM(TopicNotSupportedFaultType value) throws WsnbException;
	
	 Document writeUnableToCreatePullPointFaultTypeAsDOM(UnableToCreatePullPointFaultType value) throws WsnbException;
	
	 Document writeUnableToDestroyPullPointFaultTypeAsDOM(UnableToDestroyPullPointFaultType value) throws WsnbException;
	
	 Document writeUnableToDestroySubscriptionFaultTypeAsDOM(UnableToDestroySubscriptionFaultType value) throws WsnbException;
	
	 Document writeUnableToGetMessagesFaultTypeAsDOM(UnableToGetMessagesFaultType value) throws WsnbException;
	
	 Document writeUnacceptableInitialTerminationTimeFaultTypeAsDOM(UnacceptableInitialTerminationTimeFaultType value) throws WsnbException;
	
	 Document writeUnacceptableTerminationTimeFaultTypeAsDOM(UnacceptableTerminationTimeFaultType value) throws WsnbException;
	
	 Document writeUnrecognizedPolicyRequestFaultTypeAsDOM(UnrecognizedPolicyRequestFaultType value) throws WsnbException;
	
	 Document writeUnsubscribeAsDOM(Unsubscribe value) throws WsnbException;
	
	 Document writeUnsubscribeResponseAsDOM(UnsubscribeResponse value) throws WsnbException;
	
	 Document writeUnsupportedPolicyRequestFaultTypeAsDOM(UnsupportedPolicyRequestFaultType fault) throws WsnbException;
	
	 Document writeUseRawAsDOM(UseRaw value) throws WsnbException;
	
}
