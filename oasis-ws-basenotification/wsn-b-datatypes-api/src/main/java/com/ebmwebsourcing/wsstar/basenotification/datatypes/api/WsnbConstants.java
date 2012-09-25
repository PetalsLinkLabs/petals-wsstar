package com.ebmwebsourcing.wsstar.basenotification.datatypes.api;

import javax.xml.namespace.QName;

/**
 * Constants of WS-BaseFaults
 * 
 * @author Thierry Déjean - eBM WebSourcing
 */
public class WsnbConstants {
	
	protected WsnbConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	// ============== WS-BaseFaults "XML Schema" constants ===========================
	
	public static final String WS_BASE_NOTIFICATION_NAMESPACE_URI = "http://docs.oasis-open.org/wsn/b-2";

	public static final String WS_BASE_NOTIFICATION_PREFIX = "wsnt";
	
//	public static final String DIALECT = "Dialect";
	
	public static final QName CONSUMER_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "ConsumerReference", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName CREATION_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "CreationTime", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName CURRENT_TIME_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "CurrentTime", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName FILTER_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "Filter", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName FIXED_TOPIC_SET_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "FixedTopicSet", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName GET_CURRENT_MESSAGE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "GetCurrentMessage", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName GET_CURRENT_MESSAGE_RESPONSE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "GetCurrentMessageResponse", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName INIT_TERMINATION_TIME_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "InitialTerminationTime", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName INVALID_FILTER_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "InvalidFilterFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName INVALID_MESSAGE_CONTENT_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "InvalidMessageContentExpressionFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName INVALID_PRODUCER_PROPERTIES_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "InvalidProducerPropertiesExpressionFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName INVALID_TOPIC_EXPRESSION_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "InvalidTopicExpressionFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName MESSAGE_CONTENT_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "MessageContent", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName MESSAGE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "Message", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName MULTIPLE_TOPICS_SPECIFIED_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "MultipleTopicsSpecifiedFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName NO_CURRENT_MESSAGE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "NoCurrentMessageOnTopicFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName NOTIFICATION_MSG_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "NotificationMessage", WS_BASE_NOTIFICATION_PREFIX);
	
	public static final QName NOTIFICATION_PRODUCER_RP_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "NotificationProducerRP", WS_BASE_NOTIFICATION_PREFIX);
	
	public static final QName NOTIFY_MESSAGE_NOT_SUPPORTED_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "NotifyMessageNotSupportedFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName NOTIFY_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "Notify", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName NOTIFY_RESPONSE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "NotifyResponse", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName PAUSE_FAILED_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "PauseFailedFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName PAUSE_SUBSCRIPTION_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "PauseSubscription", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName PAUSE_SUBSCRIPTION_RESPONSE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "PauseSubscriptionResponse", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName POLICY_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "SubscriptionPolicy", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName PRODUCER_PROPERTIES_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "ProducerProperties", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName PRODUCER_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "ProducerReference", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName RENEW_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "Renew", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName RENEW_RESPONSE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "RenewResponse", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName RESUME_FAILED_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "ResumeFailedFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName RESUME_SUSBCRIPTION_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "ResumeSubscription", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName RESUME_SUBSCRIPTION_RESPONSE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "ResumeSubscriptionResponse", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName SUBSCRIBE_CREATION_FAILED_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "SubscribeCreationFailedFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName SUBSCRIBE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "Subscribe", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName SUBSCRIBE_RESPONSE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "SubscribeResponse", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName SUBSCRIPTION_EPR_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "SubscriptionReference", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName SUBSCRIPTION_MGR_RP_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "SubscriptionManagerRP", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName TERMINATION_TIME_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "TerminationTime", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName TOPIC_EXPRESSION_DIALECT_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "TopicExpressionDialect", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName TOPIC_DIALECT_UNKNOWN_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "TopicExpressionDialectUnknownFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName TOPIC_EXPRESSION_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "TopicExpression", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName TOPIC_NOT_SUPPORTED_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "TopicNotSupportedFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName TOPIC_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "Topic", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNABLE_TO_CREATE_PULL_POINT_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnableToCreatePullPointFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNABLE_TO_GET_MESSAGES_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnableToGetMessagesFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNABLE_TO_DESTROY_PULL_POINT_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnableToDestroyPullPointFault", WS_BASE_NOTIFICATION_PREFIX);
	
	public static final QName UNABLE_TO_DESTROY_SUBSCRIPTION_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnableToDestroySubscriptionFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNACCEPTABLE_INITIAL_TERMINATION_TIME_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnacceptableInitialTerminationTimeFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNACCEPTABLE_TERMINATION_TIME_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnacceptableTerminationTimeFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNRECOGNIZED_POLICY_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnrecognizedPolicyRequestFault", WS_BASE_NOTIFICATION_PREFIX);

	public static final String UNSUBSCRIBE_NAME = "Unsubscribe";
	public static final QName UNSUBSCRIBE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, UNSUBSCRIBE_NAME, WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNSUBSCRIBE_RESPONSE_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnsubscribeResponse", WS_BASE_NOTIFICATION_PREFIX);

	public static final QName UNSUPPORTED_POLICY_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI, "UnsupportedPolicyRequestFault", WS_BASE_NOTIFICATION_PREFIX);
	
	public static final QName USERAW_QNAME = new QName(WS_BASE_NOTIFICATION_NAMESPACE_URI,"UseRaw",WS_BASE_NOTIFICATION_PREFIX);
	
	// ============== WS-BaseFaults "WSDL" constants  =============
	
	public static final String WS_BASE_NOTIFICATION_WSDL_NAMESPACE_URI = "http://docs.oasis-open.org/wsn/bw-2";
	
	public static final String WS_BASE_NOTIFICATION_WSDL_PREFIX = "wsntw"; 
	
	/**
     * The QName of the subscription manager interface<br />
     * Imposed by the WS-BaseNotification specifications
     */
    public static final QName SUBSCRIPTION_MANAGER_INTERFACE = new QName(
            WS_BASE_NOTIFICATION_WSDL_NAMESPACE_URI, "SubscriptionManager");

    /**
     * The QName of the notification producer interface<br />
     * Imposed by the WS-BaseNotification specifications
     */
    public static final QName NOTIFICATION_PRODUCER_INTERFACE = new QName(
            WS_BASE_NOTIFICATION_WSDL_NAMESPACE_URI, "NotificationProducer");

    /**
     * The QName of the notification consumer interface<br />
     * Imposed by the WS-BaseNotification specifications
     */
    public static final QName NOTIFICATION_CONSUMER_INTERFACE = new QName(
            WS_BASE_NOTIFICATION_WSDL_NAMESPACE_URI, "NotificationConsumer");

    /**
	 * XML TAG node use to identified a subscription's Id in a WS-Addressing
	 * "EndpointReference", when the subscription resource is implemented
	 * as a WS-Resource.
	 * See {@linkplain http://docs.oasis-open.org/wsrf/wsrf-primer-1.2-primer-cd-02.pdf}
	 * (page 7/47 and following ones) for more details
	 */
	public static QName SUBSCRIPTION_ID_QNAME_TAG = new QName("http://www.ebmwebsourcing.com/wsstar/wsnb/ws-resource","SubscriptionId","rpimpl");

	
}
