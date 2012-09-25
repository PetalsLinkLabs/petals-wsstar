package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

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
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.implementor.WsnbModelFactory;

public class WsnbModelFactoryImpl implements WsnbModelFactory {

	private WsnbReaderImpl wsnbModelReader;
	private WsnbWriterImpl wsnbModelWriter;
	
	public WsnbModelFactoryImpl() {
		this.wsnbModelReader = new WsnbReaderImpl();
		this.wsnbModelWriter = new WsnbWriterImpl();
	}
	
	@Override
	public final WsnbReader getWsnbModelReader() {
		return this.wsnbModelReader;
	}

	@Override
	public final WsnbWriter getWsnbModelWriter() {
		return this.wsnbModelWriter;
	}

	@Override
	public final CreatePullPoint createWsnbModelCreatePullPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final CreatePullPointResponse createWsnbModelCreatePullPointResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final DestroyPullPoint createWsnbModelDestroyPullPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final DestroyPullPointResponse createWsnbModelDestroyPullPointResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final FilterType createWsnbModelFilterType() {
		return new FilterTypeImpl();
	}

	@Override
	public final GetCurrentMessage createWsnbModelGetCurrentMessage(TopicExpressionType concreteTopic) {
		return new GetCurrentMessageImpl(concreteTopic);
	}

	@Override
	public final GetCurrentMessageResponse createWsnbModelGetCurrentMessageResponse(Message notification) {
		return new GetCurrentMessageResponseImpl(notification);
	}

	@Override
	public final GetMessages createWsnbModelGetMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final GetMessagesResponse createWsnbModelGetMessagesResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final InvalidFilterFaultType createWsnbModelInvalidFilterFaultType(
			Date timestamp, List<QName> unknownFilters) {		
		return new InvalidFilterFaultTypeImpl(timestamp, unknownFilters);
	}
	
	@Override
	public final InvalidMessageContentExpressionFaultType createWsnbModelInvalidMessageContentExpressionFaultType(
			Date timestamp) {
		return new InvalidMessageContentExpressionFaultTypeImpl(timestamp);
	}

	@Override
	public final InvalidProducerPropertiesExpressionFaultType createWsnbModelInvalidProducerPropertiesExpressionFaultType(
			Date timestamp) {
		return new InvalidProducerPropertiesExpressionFaultTypeImpl(timestamp);
	}

	@Override
	public final InvalidTopicExpressionFaultType createWsnbModelInvalidTopicExpressionFaultType(
			Date timestamp) {
		return new InvalidTopicExpressionFaultTypeImpl(timestamp);
	}
	
	@Override
	public final MessageContentExpression createWsnbModelMessageContentExpression(URI dialect) {
		return new MessageContentExpressionImpl(dialect);
	}
	
	@Override
	public final MultipleTopicsSpecifiedFaultType createWsnbModelMultipleTopicsSpecifiedFaultType(
			Date timestamp) {
			return new MultipleTopicsSpecifiedFaultTypeImpl(timestamp);
	}

	@Override
	public final NoCurrentMessageOnTopicFaultType createWsnbModelNoCurrentMessageOnTopicFaultType(
			Date timestamp) {
		return new NoCurrentMessageOnTopicFaultTypeImpl(timestamp);
	}

	@Override
	public final NotificationMessageHolderType createWsnbModelNotificationMessageHolderType(Message message) {
		return new NotificationMessageHolderTypeImpl(message);
	}

	@Override
	public final Message createWsnbModelNotificationMessageHolderTypeMessage(Element message) {
		return new NotificationMessageHolderTypeImpl.MessageImpl(message);
	}
	
	@Override
	public final NotificationProducerRP createWsnbModelNotificationProducerRP() {		
		return new NotificationProducerRPImpl();
	}

	@Override
	public final Notify createWsnbModelNotify(NotificationMessageHolderType notifMsg) {	
		return new NotifyImpl(notifMsg);
	}

	@Override
	public final NotifyMessageNotSupportedFaultType createWsnbModelNotifyMessageNotSupportedFaultType(
			Date timestamp) {
		return new NotifyMessageNotSupportedFaultTypeImpl(timestamp);
	}

	@Override
	public final PauseFailedFaultType createWsnbModelPauseFailedFaultType(
			Date timestamp) {
		return new PauseFailedFaultTypeImpl(timestamp);
	}

	@Override
	public final PauseSubscription createWsnbModelPauseSubscription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final PauseSubscriptionResponse createWsnbModelPauseSubscriptionResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final ProducerPropertiesExpression createWsnbModelProducerPropertiesExpression(URI dialect) {
		return new ProducerPropertiesExpressionImpl(dialect);
	}

	@Override
	public final Renew createWsnbModelRenew() {
		return new RenewImpl();
	}

	@Override
	public final RenewResponse createWsnbModelRenewResponse(Date termTime) {
		return new RenewResponseImpl(termTime);
	}

	@Override
	public final ResumeFailedFaultType createWsnbModelResumeFailedFaultType(
			Date timestamp) {
		return new ResumeFailedFaultTypeImpl(timestamp);
	}

	@Override
	public final ResumeSubscription createWsnbModelResumeSubscription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final ResumeSubscriptionResponse createWsnbModelResumeSubscriptionResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Subscribe createWsnbModelSubscribe(EndpointReferenceType consumerRef) {
		return new SubscribeImpl(consumerRef);
	}

	@Override
	public final SubscribeCreationFailedFaultType createWsnbModelSubscribeCreationFailedFaultType(
			Date timestamp) {
		return new SubscribeCreationFailedFaultTypeImpl(timestamp);
	}

	@Override
	public final SubscribeResponse createWsnbModelSubscribeResponse(EndpointReferenceType subscriptionRef) {
		return new SubscribeResponseImpl(subscriptionRef);
	}

	@Override
	public final SubscriptionManagerRP createWsnbModelSubscriptionManagerRP(EndpointReferenceType consumerRef) {
		return new SubscriptionManagerRPImpl(consumerRef);
	}

	@Override
	public final SubscriptionPolicyType createWsnbModelSubscriptionPolicyType() {
		return new SubscriptionPolicyTypeImpl();
	}

	@Override
	public final TopicExpressionType createWsnbModelTopicExpressionType(URI dialect) {		
		return new TopicExpressionTypeImpl(dialect);
	}
	
	/**
	 * TODO add description ...
	 * @param jaxbModelTopicSet
	 * @return
	 */
	public final TopicExpressionType createWstopModelTopicExpression(
			com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbModelTopicExpression) {		
		return new TopicExpressionTypeImpl(jaxbModelTopicExpression);
	}
	
	@Override
	public final TopicExpressionDialectUnknownFaultType createWsnbModelTopicExpressionDialectUnknownFaultType(
			Date timestamp) {
		return new TopicExpressionDialectUnknownFaultTypeImpl(timestamp);
	}

	@Override
	public final TopicNotSupportedFaultType createWsnbModelTopicNotSupportedFaultType(
			Date timestamp) {
		return new TopicNotSupportedFaultTypeImpl(timestamp);
	}

	@Override
	public final UnableToCreatePullPointFaultType createWsnbModelUnableToCreatePullPointFaultType(
			Date timestamp) {
		return new UnableToCreatePullPointFaultTypeImpl(timestamp);
	}

	@Override
	public final UnableToDestroyPullPointFaultType createWsnbModelUnableToDestroyPullPointFaultType(
			Date timestamp) {
		return new UnableToDestroyPullPointFaultTypeImpl(timestamp);
	}

	@Override
	public final UnableToDestroySubscriptionFaultType createWsnbModelUnableToDestroySubscriptionFaultType(
			Date timestamp) {
		return new UnableToDestroySubscriptionFaultTypeImpl(timestamp);
	}

	@Override
	public final UnableToGetMessagesFaultType createWsnbModelUnableToGetMessagesFaultType(
			Date timestamp) {
		return new UnableToGetMessagesFaultTypeImpl(timestamp);
	}

	@Override
	public final UnacceptableInitialTerminationTimeFaultType createWsnbModelUnacceptableInitialTerminationTimeFaultType(
			Date timestamp, Date minTermTime) {
		return new UnacceptableInitialTerminationTimeFaultTypeImpl(timestamp,minTermTime);
	}
	
	@Override
	public final UnacceptableTerminationTimeFaultType createWsnbModelUnacceptableTerminationTimeFaultType(
			Date timestamp, Date minTermTime) {
		return new UnacceptableTerminationTimeFaultTypeImpl(timestamp, minTermTime);
	}
	
	
	@Override
	public final UnrecognizedPolicyRequestFaultType createWsnbModelUnrecognizedPolicyRequestFaultType(
			Date timestamp) {
		return new UnrecognizedPolicyRequestFaultTypeImpl(timestamp);
	}

	@Override
	public final Unsubscribe createWsnbModelUnsubscribe() {
		return new UnsubscribeImpl();
	}

	@Override
	public final UnsubscribeResponse createWsnbModelUnsubscribeResponse() {
		return new UnsubscribeResponseImpl();
	}

	@Override
	public final UnsupportedPolicyRequestFaultType createWsnbModelUnsupportedPolicyRequestFaultType(
			Date timestamp) {
		return new UnsupportedPolicyRequestFaultTypeImpl(timestamp);
	}

	@Override
	public final UseRaw createWsnbModelUseRaw() {		
		return new UseRawImpl();
	}

}
