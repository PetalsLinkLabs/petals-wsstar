package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction;

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
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbReader;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbWriter;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.implementor.WsnbModelFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;


public final class RefinedWsnbFactory implements WsnbFactory {
	
	private WsnbModelFactory model;
		
	// ################# EXPERITMENTAL SINGLETON PATTERN FORM ######################
	
	private RefinedWsnbFactory (/*WsaModelFactory modelFactory*/) {
		/*this.modelFactory = modelFactory;*/
	}	

	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	 * or the first access to SingletonHolder.INSTANCE, not before.
	 */
	private  static final class WsnbFactoryHolder { 		
		//private static WsaModelFactory modelFactory;
		private  static final RefinedWsnbFactory INSTANCE = new RefinedWsnbFactory(/*WsaFactoryHolder.modelFactory*/);		
	
		private WsnbFactoryHolder(){}
	}

	public static WsnbFactory getInstance() throws WsnbException {
		RefinedWsnbFactory singleton = WsnbFactoryHolder.INSTANCE;
		if (singleton.model == null){ 			
			throw new WsnbException("\n\t/!\\ WARNING /!\\\n" +
					"The WsnbModelFactory have not been initialized !!!\n" +
					"Please create a \"WsnbModelFactory\" instance and \n" +
					"call the \"getInstance(WsnbModelFactory)\" method instead." +
					"\n\t/!\\ WARNING /!\\\n");
		}
		return singleton;		
	}	
	
	public static WsnbFactory getInstance(WsnbModelFactory modelFactory) {		
		RefinedWsnbFactory singleton = WsnbFactoryHolder.INSTANCE;
		singleton.model = modelFactory;		 	
		return singleton;		
	}	 		
	//	#######################################################################
		
	//	public RefinedWsaFactory(WsaModelFactory modelFactory) {
	//		this.modelFactory = modelFactory;
	//	}
	
	public WsnbModelFactory getModel() {
		return this.model;
	}
	
	protected void  setModel(WsnbModelFactory model) {
		this.model = model;
	}		
	@Override
	public WsnbReader getWsnbReader() {		
		return this.model.getWsnbModelReader();
	}
	
	@Override
	public WsnbWriter getWsnbWriter() {
		return this.model.getWsnbModelWriter();
	}

	@Override
	public CreatePullPoint createCreatePullPoint() {
		return this.model.createWsnbModelCreatePullPoint();
	}

	@Override
	public CreatePullPointResponse createCreatePullPointResponse() {
		return this.model.createWsnbModelCreatePullPointResponse();
	}

	@Override
	public DestroyPullPoint createDestroyPullPoint() {		
		return this.model.createWsnbModelDestroyPullPoint();
	}

	@Override
	public DestroyPullPointResponse createDestroyPullPointResponse() {		
		return this.model.createWsnbModelDestroyPullPointResponse();
	}

	@Override
	public FilterType createFilterType() {		
		return this.model.createWsnbModelFilterType();
	}

	@Override
	public GetCurrentMessage createGetCurrentMessage(TopicExpressionType concreteTopic) {		
		return this.model.createWsnbModelGetCurrentMessage(concreteTopic);
	}

	@Override
	public GetCurrentMessageResponse createGetCurrentMessageResponse(Message notification) {		
		return this.model.createWsnbModelGetCurrentMessageResponse(notification);
	}
	
	@Override
	public GetMessages createGetMessages() {		
		return this.model.createWsnbModelGetMessages();
	}

	@Override
	public GetMessagesResponse createGetMessagesResponse() {		
		return this.model.createWsnbModelGetMessagesResponse();
	}
	
	@Override
	public InvalidFilterFaultType createInvalidFilterFaultType(Date timestamp,List<QName> unknownFilters) {		
		return this.model.createWsnbModelInvalidFilterFaultType(timestamp,unknownFilters);
	}

	@Override
	public InvalidMessageContentExpressionFaultType createInvalidMessageContentExpressionFaultType(Date timestamp) {		
		return this.model.createWsnbModelInvalidMessageContentExpressionFaultType(timestamp);
	}

	@Override
	public InvalidProducerPropertiesExpressionFaultType createInvalidProducerPropertiesExpressionFaultType(Date timestamp) {		
		return this.model.createWsnbModelInvalidProducerPropertiesExpressionFaultType(timestamp);
	}

	@Override
	public InvalidTopicExpressionFaultType createInvalidTopicExpressionFaultType(Date timestamp) {		
		return this.model.createWsnbModelInvalidTopicExpressionFaultType(timestamp);
	}

	@Override
	public MessageContentExpression createMessageContentExpression(URI dialect) {		
		
		return this.model.createWsnbModelMessageContentExpression(dialect);
	}
	
	@Override
	public MultipleTopicsSpecifiedFaultType createMultipleTopicsSpecifiedFaultType(Date timestamp) {		
		return this.model.createWsnbModelMultipleTopicsSpecifiedFaultType(timestamp);
	}

	@Override
	public NoCurrentMessageOnTopicFaultType createNoCurrentMessageOnTopicFaultType(Date timestamp) {		
		return this.model.createWsnbModelNoCurrentMessageOnTopicFaultType(timestamp);
	}

	@Override
	public NotificationMessageHolderType createNotificationMessageHolderType(Message message) {		
		return this.model.createWsnbModelNotificationMessageHolderType(message);
	}

	@Override
	public Message createNotificationMessageHolderTypeMessage(Element message) {
		return this.model.createWsnbModelNotificationMessageHolderTypeMessage(message);
	}
	
	@Override
	public NotificationProducerRP createNotificationProducerRP() {		
		return this.model.createWsnbModelNotificationProducerRP();
	}

	@Override
	public Notify createNotify(NotificationMessageHolderType notifMsg) {		
		return this.model.createWsnbModelNotify(notifMsg);
	}

	@Override
	public NotifyMessageNotSupportedFaultType createNotifyMessageNotSupportedFaultType(Date timestamp) {		
		return this.model.createWsnbModelNotifyMessageNotSupportedFaultType(timestamp);
	}

	@Override
	public PauseFailedFaultType createPauseFailedFaultType(Date timestamp) {		
		return this.model.createWsnbModelPauseFailedFaultType(timestamp);
	}

	@Override
	public PauseSubscription createPauseSubscription() {
		return this.model.createWsnbModelPauseSubscription();
	}

	@Override
	public PauseSubscriptionResponse createPauseSubscriptionResponse() {		
		return this.model.createWsnbModelPauseSubscriptionResponse();
	}

	@Override
	public ProducerPropertiesExpression createProducerPropertiesExpression(
			URI dialect) {
		return this.model.createWsnbModelProducerPropertiesExpression(dialect);
	}

	@Override
	public Renew createRenew() {		
		return this.model.createWsnbModelRenew();
	}

	@Override
	public RenewResponse createRenewResponse(Date termTime) {		
		return this.model.createWsnbModelRenewResponse(termTime);
	}

	@Override
	public ResumeFailedFaultType createResumeFailedFaultType(Date timestamp) {		
		return this.model.createWsnbModelResumeFailedFaultType(timestamp);
	}

	@Override
	public ResumeSubscription createResumeSubscription() {		
		return this.model.createWsnbModelResumeSubscription();
	}

	@Override
	public ResumeSubscriptionResponse createResumeSubscriptionResponse() {		
		return this.model.createWsnbModelResumeSubscriptionResponse();
	}

	@Override
	public Subscribe createSubscribe(EndpointReferenceType consumerRef) {		
		return this.model.createWsnbModelSubscribe(consumerRef);
	}

	@Override
	public SubscribeCreationFailedFaultType createSubscribeCreationFailedFaultType(Date timestamp) {		
		return this.model.createWsnbModelSubscribeCreationFailedFaultType(timestamp);
	}

	@Override
	public SubscribeResponse createSubscribeResponse(EndpointReferenceType subscriptionRef) {		
		return this.model.createWsnbModelSubscribeResponse(subscriptionRef);
	}

	@Override
	public SubscriptionManagerRP createSubscriptionManagerRP(EndpointReferenceType consumerRef) {		
		return this.model.createWsnbModelSubscriptionManagerRP(consumerRef);
	}

	@Override
	public SubscriptionPolicyType createSubscriptionPolicyType() {		
		return this.model.createWsnbModelSubscriptionPolicyType();
	}

	@Override
	public TopicExpressionType createTopicExpressionType(URI dialect) {		
		return this.model.createWsnbModelTopicExpressionType(dialect);
	}

	@Override
	public TopicExpressionDialectUnknownFaultType createTopicExpressionDialectUnknownFaultType(Date timestamp) {		
		return this.model.createWsnbModelTopicExpressionDialectUnknownFaultType(timestamp);
	}

	@Override
	public TopicNotSupportedFaultType createTopicNotSupportedFaultType(Date timestamp) {		
		return this.model.createWsnbModelTopicNotSupportedFaultType(timestamp);
	}

	@Override
	public UnableToCreatePullPointFaultType createUnableToCreatePullPointFaultType(Date timestamp) {		
		return this.model.createWsnbModelUnableToCreatePullPointFaultType(timestamp);
	}

	@Override
	public UnableToDestroyPullPointFaultType createUnableToDestroyPullPointFaultType(Date timestamp) {		
		return this.model.createWsnbModelUnableToDestroyPullPointFaultType(timestamp);
	}

	@Override
	public UnableToDestroySubscriptionFaultType createUnableToDestroySubscriptionFaultType(Date timestamp) {		
		return this.model.createWsnbModelUnableToDestroySubscriptionFaultType(timestamp);
	}

	@Override
	public UnableToGetMessagesFaultType createUnableToGetMessagesFaultType(Date timestamp) {		
		return this.model.createWsnbModelUnableToGetMessagesFaultType(timestamp);
	}

	@Override
	public UnacceptableInitialTerminationTimeFaultType createUnacceptableInitialTerminationTimeFaultType(Date timestamp,Date minTermTime) {		
		return this.model.createWsnbModelUnacceptableInitialTerminationTimeFaultType(timestamp,minTermTime);
	}

	@Override
	public UnacceptableTerminationTimeFaultType createUnacceptableTerminationTimeFaultType(Date timestamp,Date minTermTime) {		
		return this.model.createWsnbModelUnacceptableTerminationTimeFaultType(timestamp,minTermTime);
	}

	@Override
	public UnrecognizedPolicyRequestFaultType createUnrecognizedPolicyRequestFaultType(Date timestamp) {		
		return this.model.createWsnbModelUnrecognizedPolicyRequestFaultType(timestamp);
	}

	@Override
	public Unsubscribe createUnsubscribe() {		
		return this.model.createWsnbModelUnsubscribe(); 
	}

	@Override
	public UnsubscribeResponse createUnsubscribeResponse() {		
		return this.model.createWsnbModelUnsubscribeResponse();
	}
	
	@Override
	public UnsupportedPolicyRequestFaultType createUnsupportedPolicyRequestFaultType(
			Date timestamp) {
		return this.model.createWsnbModelUnsupportedPolicyRequestFaultType(timestamp);
	}

	@Override
	public UseRaw createUseRaw() {		
		return this.model.createWsnbModelUseRaw();
	}
	
}
