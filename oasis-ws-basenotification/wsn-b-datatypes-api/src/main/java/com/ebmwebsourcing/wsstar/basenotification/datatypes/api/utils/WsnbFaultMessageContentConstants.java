/**
 * Copyright (c) 2009 EBM Websourcing, http://www.ebmwebsourcing.com/
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * -------------------------------------------------------------------------
 * $Id$
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils;

import java.util.Locale;

/**
 * 
 * @author tdejean - eBM WebSourcing
 *
 */
public class WsnbFaultMessageContentConstants {

	protected WsnbFaultMessageContentConstants(){
		// prevents calls from subclass
        throw new UnsupportedOperationException();
	}
	
	private static final String MESSAGE_NOT_KNOWN_TO_WEB_SERVICE = "message is not known to the Web service.";
	
	public static final Locale FAULT_DESCRIPTION_LANGUAGE = Locale.ENGLISH;
	
	/**
	 * 	~~~~~ WS-BaseNotification "Subscribe" fault messages descriptions ~~~~~
	 * 				~~~~~ (WS-BaseNotification - p.20-21) ~~~~~
	 * 
	 *  InvalidFilterFault
	 *	•   The Subscribe message contained a filter that was not understood or supported by the
   	 *		NotificationProducer.
	 *  InvalidMessageContentExpressionFault
	 *	•   The Subscribe message contained a MessageContent filter that did not represent a valid
	 *		boolean expression.
	 *  InvalidProducerPropertiesExpressionFault
	 *	•   The Subscribe message contained a ProducerProperties filter that did not represent a
   	 *		valid boolean expression.
	 *  InvalidTopicExpressionFault
	 *	•   The Subscribe message contained a TopicExpression filter where the contents of the
   	 *		filter did not match the dialect specified.
	 *  NotifyMessageNotSupportedFault
	 *	•   The NotificationProducer does not support the wsnt:Notify wrapper and was not able to
	 *		determine an alternative.
	 *  ResourceUnknownFault
	 *	•   The NotificationProducer is a WS-Resource, and the resource identified in the message
   	 *		is not known to the Web service. This fault is specified by the WS-Resource [WS-
   	 *		Resource] specification.
	 *  SubscribeCreationFailedFault
     *	•  	The NotificationProducer failed to process the Subscribe message. The
     *	 	NotificationProducer SHOULD use a more specific fault message if possible. The
     * 		NotificationProducer MAY include a hint in the fault message indicating why it failed to
     *  	process the Subscribe message.
	 *  TopicExpressionDialectUnknownFault
	 *	•   The Subscribe message contained a TopicExpression filter having a dialect that was not
   	 *		understood or supported by the NotificationProducer.
	 *  TopicNotSupportedFault
	 *	•   The Subscribe message contained a TopicExpression filter that referenced a topic that
   	 *		was not supported by the NotificationProducer.
	 *  UnacceptableInitialTerminationTimeFault
     *	•   The value of InitialTerminationTime specified in the Subscribe message was not
     *  	acceptable to the NotificationProducer. The NotificationProducer MAY include a hint in
     *  	the fault message indicating acceptable values for InitialTerminationTime.
     *  UnrecognizedPolicyRequestFault
	 *	•   The NotificationProducer does not recognize one or more policy requests carried in the
	 *		SubscriptionPolicy element. This fault SHOULD contain elements representing the
	 *		offending policy requests.
	 *	UnsupportedPolicyRequestFault
	 *	•   The NotificationProducer recognizes, but does not support one or more policy requests
	 *		carried in the SubscriptionPolicy element. This fault SHOULD contain elements
	 *		representing the offending policy requests.
	 */
	public static class WsnbSubcribeFaultDescriptions {
		
		protected WsnbSubcribeFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String INVALID_FILTER_FAULT_DESC = 
			"The Subscribe message contained a filter that was not understood or supported " +
			"by the NotificationProducer";
		
		public static final String INVALID_MESSAGE_CONTENT_EXPRESSION_FAULT_DESC = 
			"The Subscribe message contained a MessageContent filter that did not represent a " +
			"valid boolean expression.";
		
		public static final String INVALID_PRODUCER_PROPERTIES_EXPRESSION_FAULT_DESC = 
			"The Subscribe message contained a ProducerProperties filter that did not represent " +
			"a valid boolean expression.";
		
		public static final String INVALID_TOPIC_EXPRESSION_FAULT_DESC = 
			"The Subscribe message contained a TopicExpression filter where the contents of the filter " +
			"did not match the dialect specified.";
		
		public static final String NOTIFY_MESSAGE_NOT_SUPPORTED_FAULT_DESC = 
			"The NotificationProducer does not support the wsnt:Notify wrapper and was not able to " +
			"determine an alternative.";
		
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The NotificationProducer is a WS-Resource, and the resource identified in the " +
			MESSAGE_NOT_KNOWN_TO_WEB_SERVICE;		
		public static final String SUBSCRIBE_CREATION_FAILED_FAULT_DESC = 
			"The NotificationProducer failed to process the Subscribe message.";
		
		public static final String TOPIC_EXPRESSION_DIALECT_UNKNOWN_FAULT_DESC =
			"The Subscribe message contained a TopicExpression filter having a dialect that was not understood " +
			"or supported by the NotificationProducer.";
		
		public static final String TOPIC_NOT_SUPPORTED_FAULT_DESC =			
			"The Subscribe message contained a TopicExpression filter that referenced a topic that was not " +
			"supported by the NotificationProducer.";
		
		public static final String UNACCEPTABLE_INITIAL_TERMINATION_TIME_FAULT_DESC =
			"The value of InitialTerminationTime specified in the Subscribe message was not	acceptable to the " +
			"NotificationProducer.";
		
		public static final String UNRECOGNIZED_POLICY_REQUEST_FAULT_DESC =
			"The NotificationProducer does not recognize one or more policy requests carried in the" +
			" SubscriptionPolicy element.";
		
		public static final String UNSUPPORTED_POLICY_REQUEST_FAULT_DESC =
			"The NotificationProducer recognizes, but does not support one or more policy requests " +
			"carried in the SubscriptionPolicy element.";		
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "GetCurrentMessage" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.23) ~~~~~
	 *
	 *	InvalidTopicExpressionFault
     *  •   The Topic element of the GetCurrentMessage message had contents that did not match
     *		the dialect specified.
     *	MultipleTopicsSpecifiedFault
     *	•   The GetCurrentMessage message referenced more than one topic.
     *	NoCurrentMessageOnTopicFault
     *	•   The topic referenced in the GetCurrentMessage message does not have any pending
     *		messages.
	 *	ResourceUnknownFault
	 *	•   The NotificationProducer is acting as a WS-Resource, and the resource identified in the
     * 		message is not known to the Web service. This fault is specified by the WS-Resource
     * 		[WS-Resource] specification.
     *	TopicExpressionDialectUnknownFault
     *	•   The Topic element of the GetCurrentMessage message had a dialect that was not
     *		understood or supported by the NotificationProducer.
     *	TopicNotSupportedFault
     *  •   The Topic element of the GetCurrentMessage message referenced a topic that was not
     *		supported by the NotificationProducer.
     */
	public static class WsnbGetCurrentMessageFaultDescriptions {
		
		protected WsnbGetCurrentMessageFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String INVALID_TOPIC_EXPRESSION_FAULT_DESC = 
			"The Topic element of the GetCurrentMessage message had contents that did not match " +
			"the dialect specified.";
		
		public static final String MULTIPLE_TOPICS_SPECIFIED_FAULT_DESC = 
			"The GetCurrentMessage message referenced more than one topic.";
		
		public static final String NO_CURRENT_MESSAGE_ON_TOPIC_FAULT_DESC = 
			"The topic referenced in the GetCurrentMessage message does not have any pending messages.";
		
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The NotificationProducer is acting as a WS-Resource, and the resource identified in the " +
			MESSAGE_NOT_KNOWN_TO_WEB_SERVICE;
		
		public static final String TOPIC_EXPRESSION_DIALECT_UNKNOWN_FAULT_DESC = 
			"The Topic element of the GetCurrentMessage message had a dialect that was not " +
			"understood or supported by the NotificationProducer.";
		
		public static final String TOPIC_NOT_SUPPORTED_FAULT_DESC = 
			"The Topic element of the GetCurrentMessage message referenced a topic that was not " +
			"supported by the NotificationProducer.";				
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "GetMessages" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.27) ~~~~~
	 *	ResourceUnknownFault
	 *	•   The PullPoint is acting as a WS-Resource, and the resource identified in the request
	 * 		message is not known to the Web service. This fault is specified by the WS-Resource
	 *		[WS-Resource] specification.
	 *	UnableToGetMessagesFault
	 *	•   The PullPoint was unable to return messages for some reason.
	 */
	public static class WsnbGetMessagesFaultDescriptions {
		
		protected WsnbGetMessagesFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The PullPoint is acting as a WS-Resource, and the resource identified in the request " +
			MESSAGE_NOT_KNOWN_TO_WEB_SERVICE;
		
		public static final String UNABLE_TO_GET_MESSAGES_FAULT_DESC = 
			"The PullPoint was unable to return messages for some reason.";
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "DestroyPullPoint" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.28) ~~~~~
	 *	ResourceUnknownFault
	 *	•   The PullPoint is acting as a WS-Resource, and the resource identified in the request
	 * 		message is not known to the Web service. This fault is specified by the WS-Resource
	 *		[WS-Resource] specification.
	 *	UnableToDestroyPullPointFault
	 *	•   The PullPoint was unable to destroy the PullPoint resource for some reason.
	 */
	public static class WsnbDestroyPullPointFaultDescriptions {
		
		protected WsnbDestroyPullPointFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The PullPoint is acting as a WS-Resource, and the resource identified in the request " +
			MESSAGE_NOT_KNOWN_TO_WEB_SERVICE;
		
		public static final String UNABLE_TO_DESTROY_PULLPOINT_FAULT_DESC = 
			"The PullPoint was unable to destroy the PullPoint resource for some reason";
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "CreatePullPoint" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.30) ~~~~~
	 *	UnableToCreatePullPointFault
	 *	•   no description !!!
	 */
	public static class WsnbCreatePullPointFaultDescriptions {
		
		protected WsnbCreatePullPointFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String UNABLE_TO_CREATE_PULLPOINT_FAULT_DESC = 
			"Unable to create PullPoint.";
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "Renew" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.32) ~~~~~
	 *	ResourceUnknownFault
	 *	•   The SubscriptionManager is acting as a WS-Resource, and the resource identified in the
	 *		message is not known to the Web service. This fault is specified by the WS-Resource
	 *		[WS-Resource] specification.
	 *	UnacceptableTerminationTimeFault
	 *	•   The value of TerminationTime specified in the Renew message was not acceptable to the
	 *      NotificationProducer. The NotificationProducer MAY include a hint in the fault message
	 *      indicating acceptable values for InitialTerminationTime.
	 */
	public static class WsnbRenewFaultDescriptions {
		
		protected WsnbRenewFaultDescriptions () {
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The SubscriptionManager is acting as a WS-Resource, and the resource identified in the " +
			MESSAGE_NOT_KNOWN_TO_WEB_SERVICE;
		
		public static final String UNACCAPTABLE_TERMINATION_TIME_FAULT_DESC = 
			"The value of TerminationTime specified in the Renew message was not acceptable to the " +
			"NotificationProducer.";
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "Unsubscribe" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.34) ~~~~~
	 *	ResourceUnknownFault
	 *  •   The SubscriptionManager is acting as a WS-Resource, and the resource identified in the
	 *		message is not known to the Web service. This fault is specified by the WS-Resource
	 *      [WS-Resource] specification.
	 *	UnableToDestroySubscriptionFault
	 *  •   The SubscriptionManager was unable to destroy the Subscription resource for some
	 *      reason.
	 */
	public static class WsnbUnsubscribeFaultDescriptions {
		
		protected WsnbUnsubscribeFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The SubscriptionManager is acting as a WS-Resource, and the resource identified in the " +
			MESSAGE_NOT_KNOWN_TO_WEB_SERVICE;
		
		public static final String UNABLE_TO_DESTROY_SUBSCRIPTION_FAULT_DESC = 
			"The SubscriptionManager was unable to destroy the Subscription resource for some reason.";
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "PauseSubscription" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.35-36) ~~~~~
	 *	PauseFailedFault
	 *	• 	The Pause operation could not be performed on the Subscription.
	 *	ResourceUnknownFault
	 *	•   The resource identified in the message is not known to the Web service. This fault is
	 *	    specified by the WS-Resource [WS-Resource] specification.
	 */
	public static class WsnbPauseSubscriptonFaultDescriptions {
		
		protected WsnbPauseSubscriptonFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String PAUSE_FAILED_FAULT_DESC = 
			"The Pause operation could not be performed on the Subscription.";
	
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The resource identified in the " + MESSAGE_NOT_KNOWN_TO_WEB_SERVICE;
	}
	
	/**
	 * 	~~~~~ WS-BaseNotification "ResumeSubscription" fault messages descriptions ~~~~~
	 *				~~~~~ (WS-BaseNotification - p.37) ~~~~~
	 *	ResourceUnknownFault
	 *	•   The resource identified in the message is not known to the Web service. This fault is
	 *	    specified by the WS-Resource [WS-Resource] specification.
	 *	ResumeFailedFault
	 *	• 	The Resume operation could not be performed on the Subscription.
	 */
	public static class WsnbResumeSubscriptonFaultDescriptions {
		
		protected WsnbResumeSubscriptonFaultDescriptions(){
			// prevents calls from subclass
	        throw new UnsupportedOperationException();
		}
		
		public static final String RESOURCE_UNKNOWN_FAULT_DESC = 
			"The resource identified in the message is not known to the Web service.";
		
		public static final String RESUME_FAILED_FAULT_DESC = 
			"The Resume operation could not be performed on the Subscription.";		
	}
		
}
