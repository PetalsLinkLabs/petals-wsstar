package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.implementor;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPoint;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPoint;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessages;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessagesResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidFilterFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidMessageContentExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidProducerPropertiesExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidTopicExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MessageContentExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MultipleTopicsSpecifiedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NoCurrentMessageOnTopicFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType.Message;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationProducerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotifyMessageNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ProducerPropertiesExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Renew;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.RenewResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeCreationFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionManagerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionDialectUnknownFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToCreatePullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroyPullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroySubscriptionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToGetMessagesFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableInitialTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnrecognizedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Unsubscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsupportedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UseRaw;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbReader;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbWriter;


public interface WsnbModelFactory {
				
	 WsnbReader getWsnbModelReader();
	
	 WsnbWriter getWsnbModelWriter();
	
	 CreatePullPoint createWsnbModelCreatePullPoint() ;
	
	 CreatePullPointResponse createWsnbModelCreatePullPointResponse();
	
	 DestroyPullPoint createWsnbModelDestroyPullPoint();
	
	 DestroyPullPointResponse createWsnbModelDestroyPullPointResponse();
	
	 FilterType createWsnbModelFilterType();
	
	 GetCurrentMessage createWsnbModelGetCurrentMessage(TopicExpressionType concreteTopic);
	
	 GetCurrentMessageResponse createWsnbModelGetCurrentMessageResponse(Message notification);
	
	 GetMessages createWsnbModelGetMessages();
	
	 GetMessagesResponse createWsnbModelGetMessagesResponse();
	
	 InvalidFilterFaultType createWsnbModelInvalidFilterFaultType(Date timestamp,List<QName> unknownFilters);
	
	 InvalidMessageContentExpressionFaultType createWsnbModelInvalidMessageContentExpressionFaultType(Date timestamp);
	
	 InvalidProducerPropertiesExpressionFaultType createWsnbModelInvalidProducerPropertiesExpressionFaultType(Date timestamp);
	
	 InvalidTopicExpressionFaultType createWsnbModelInvalidTopicExpressionFaultType(Date timestamp);

	 MessageContentExpression createWsnbModelMessageContentExpression(URI dialect);
		
	 MultipleTopicsSpecifiedFaultType createWsnbModelMultipleTopicsSpecifiedFaultType(Date timestamp);
	
	 NoCurrentMessageOnTopicFaultType createWsnbModelNoCurrentMessageOnTopicFaultType(Date timestamp);
	
	 NotificationMessageHolderType createWsnbModelNotificationMessageHolderType(Message message);
			
	 NotificationMessageHolderType.Message createWsnbModelNotificationMessageHolderTypeMessage(Element message);
	
	 NotificationProducerRP createWsnbModelNotificationProducerRP();
	
	 Notify createWsnbModelNotify(NotificationMessageHolderType notifMsg);
	
	 NotifyMessageNotSupportedFaultType createWsnbModelNotifyMessageNotSupportedFaultType(Date timestamp);
	
	 PauseFailedFaultType createWsnbModelPauseFailedFaultType(Date timestamp);
	
	 PauseSubscription createWsnbModelPauseSubscription();
	
	 PauseSubscriptionResponse createWsnbModelPauseSubscriptionResponse();

	 ProducerPropertiesExpression createWsnbModelProducerPropertiesExpression(URI dialect);
	
	 Renew createWsnbModelRenew();
	
	 RenewResponse createWsnbModelRenewResponse(Date termTime);
	
	 ResumeFailedFaultType createWsnbModelResumeFailedFaultType(Date timestamp);
	
	 ResumeSubscription createWsnbModelResumeSubscription();
	
	 ResumeSubscriptionResponse createWsnbModelResumeSubscriptionResponse();
	
	 Subscribe createWsnbModelSubscribe(EndpointReferenceType consumerRef);
	
	 SubscribeCreationFailedFaultType createWsnbModelSubscribeCreationFailedFaultType(Date timestamp);
	
	 SubscribeResponse createWsnbModelSubscribeResponse(EndpointReferenceType subscriptionRef);
	
	 SubscriptionManagerRP createWsnbModelSubscriptionManagerRP(EndpointReferenceType consumerRef);
	
	 SubscriptionPolicyType createWsnbModelSubscriptionPolicyType();
	
	 TopicExpressionType createWsnbModelTopicExpressionType(URI dialect);
	
	 TopicExpressionDialectUnknownFaultType createWsnbModelTopicExpressionDialectUnknownFaultType(Date timestamp);
	
	 TopicNotSupportedFaultType createWsnbModelTopicNotSupportedFaultType(Date timestamp);
	
	 UnableToCreatePullPointFaultType createWsnbModelUnableToCreatePullPointFaultType(Date timestamp);
	
	 UnableToDestroyPullPointFaultType createWsnbModelUnableToDestroyPullPointFaultType(Date timestamp);
	
	 UnableToDestroySubscriptionFaultType createWsnbModelUnableToDestroySubscriptionFaultType(Date timestamp);
	
	 UnableToGetMessagesFaultType createWsnbModelUnableToGetMessagesFaultType(Date timestamp);
	
	 UnacceptableInitialTerminationTimeFaultType createWsnbModelUnacceptableInitialTerminationTimeFaultType(Date timestamp,Date minTermTime);
	
	 UnacceptableTerminationTimeFaultType createWsnbModelUnacceptableTerminationTimeFaultType(Date timestamp,Date minTermTime);
	
	 UnrecognizedPolicyRequestFaultType createWsnbModelUnrecognizedPolicyRequestFaultType(Date timestamp);
	
	 Unsubscribe createWsnbModelUnsubscribe();
	
	 UnsubscribeResponse createWsnbModelUnsubscribeResponse();
	
	 UnsupportedPolicyRequestFaultType createWsnbModelUnsupportedPolicyRequestFaultType(Date timestamp);
	
	 UseRaw createWsnbModelUseRaw();
}
