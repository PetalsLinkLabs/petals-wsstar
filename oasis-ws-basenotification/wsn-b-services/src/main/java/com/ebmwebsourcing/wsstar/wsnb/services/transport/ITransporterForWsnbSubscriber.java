package com.ebmwebsourcing.wsstar.wsnb.services.transport;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Renew;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.RenewResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Unsubscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsubscribeResponse;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;

public interface ITransporterForWsnbSubscriber {
	
	SubscribeResponse sendSubscribeRequest(final EndpointReferenceType producerServiceEdp, 
            final Subscribe payload) throws AbsWSStarFault;
	
	UnsubscribeResponse sendUnsubscribeRequest(final EndpointReferenceType producerServiceEdp, 
             final Unsubscribe payload) throws AbsWSStarFault;	
	
	RenewResponse sendRenewRequest(final EndpointReferenceType subscriptionsMgrServiceEdp, 
             final Renew payload) throws AbsWSStarFault;
		
	PauseSubscriptionResponse sendPauseSubscriptionRequest(final EndpointReferenceType subscriptionsMgrService,
			final PauseSubscription payload) throws AbsWSStarFault;
	
	ResumeSubscriptionResponse sendResumeSubscriptionRequest(final EndpointReferenceType subscriptionsMgrService,
			final ResumeSubscription payload) throws AbsWSStarFault;
}
