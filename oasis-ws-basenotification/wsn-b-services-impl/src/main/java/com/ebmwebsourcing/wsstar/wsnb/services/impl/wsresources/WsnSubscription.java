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
package com.ebmwebsourcing.wsstar.wsnb.services.impl.wsresources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;

import org.w3c.dom.Document;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.element.EndpointReference;
import com.ebmwebsourcing.wsaddressing10.api.element.ReferenceParameters;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionManagerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbFaultMessageContentConstants;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.utils.WsrfrException;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.WsrfrlConstants;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ScheduledResourceTerminationRP;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationNotification;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.refinedabstraction.RefinedWsrfrlFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;
import com.ebmwebsourcing.wsstar.wsnb.services.faults.UnacceptableInitialTerminationTimeFault;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.Wsnb4ServUtils;
import com.ebmwebsourcing.wsstar.wsnb.services.transport.ITransporterForWsnbPublisher;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;
import com.ebmwebsourcing.wsstar.wsrfr.services.faults.ResourceUnknownFault;
import com.ebmwebsourcing.wsstar.wsrfrl.services.IImmediateResourceTermination;
import com.ebmwebsourcing.wsstar.wsrfrl.services.IScheduledResourceTermination;

public class WsnSubscription implements IImmediateResourceTermination,IScheduledResourceTermination {

	private final static String IMMEDIAT_TERMINAISON_REASON = "The \"Subscription\" resource have been detroy for some reason (" +
	"Reloated Topic no longer supported, ...)";

	protected Logger logger;

	private SubscriptionManagerRP subscriptionResource = null;
	private EndpointReferenceType subscriptionReference = null;
	private ScheduledResourceTerminationRP terminationRP = null;

	protected String wsResourceEdpBase = "";//"http://www.ebmwebsourcing.com/subscriptionManager/default";
	protected QName wsResourceService = null;//new QName("http://www.ebmwebsourcing.com/default","SubscriptionManagerService");
	protected QName wsResourceInterface = null;//new QName("http://www.ebmwebsourcing.com/default","SubscriptionManager");	

	protected List<TopicExpressionType> concreteTopExprs = null;

	protected ITransporterForWsnbPublisher transporter = null;

	public WsnSubscription(Logger logger, URI wsaAddress, String subscriptionId ,Subscribe payload, ITransporterForWsnbPublisher transport) throws WsnbException, AbsWSStarFault{
		super();
		this.logger = logger;
		try {

			// --- transporter to use to notify termination
			this.transporter = transport;

			// ------- Set endpoint reference for Subscription : ------
			this.subscriptionReference = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReference.class);
			Address address = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
			address.setValue(wsaAddress);
			this.subscriptionReference.setAddress(address);

			ReferenceParameters refParams = WSNUtil.getInstance().getXmlObjectFactory().create(ReferenceParameters.class);
			refParams.addAny(Wsnb4ServUtils.createSubscriptionIdAsReferenceParamElt(subscriptionId));

			this.concreteTopExprs = new ArrayList<TopicExpressionType>();

			this.subscriptionReference.setReferenceParameters(refParams);

			// -- Create Subscription resource to manage 
			this.subscriptionResource = RefinedWsnbFactory.getInstance().createSubscriptionManagerRP(payload.getConsumerReference());
			//this.subscriptionResource.setConsumerReference(payload.getConsumerReference());

			// --- to test ---
			FilterType ft = payload.getFilter();
			// ---------------

			if (ft != null)
				this.subscriptionResource.setFilter(ft);

			this.subscriptionResource.setCreationTime(new Date());
			SubscriptionPolicyType subsPolicy = payload.getSubscriptionPolicy();
			if (subsPolicy != null)
				this.subscriptionResource.setSubscriptionPolicy(subsPolicy);

			// -------- Manage Termination time ------------												

			Object initTermTime = payload.getInitialTerminationTime();

			if (initTermTime != null){

				Date termTimeValue = null;

				if (initTermTime instanceof Duration){
					Duration duration = (Duration) initTermTime;
					termTimeValue = new Date();					
					duration.addTo(termTimeValue);										
				} else {				
					termTimeValue = (Date) initTermTime; 												
				}

				if (!termTimeValue.after(new Date()))
					try {
						throw new UnacceptableInitialTerminationTimeFault(WsnbFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE,
								WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.UNACCEPTABLE_INITIAL_TERMINATION_TIME_FAULT_DESC);
					} catch (WsrfbfException e) {
						new WsnbException(e);
					}

					CurrentTime currentTime = RefinedWsrfrlFactory.getInstance().createCurrentTime(new Date());
					TerminationTime terminationTime = RefinedWsrfrlFactory.getInstance().createTerminationTime(termTimeValue);				

					this.terminationRP = RefinedWsrfrlFactory.getInstance().createScheduledResourceTerminationRP(currentTime, terminationTime);

			}
		} catch (WsrfrlException e) {
			throw new WsnbException(e);
		} 

	}

	public WsnSubscription(Logger logger, URI wsaAddress, String subscriptionId, SubscriptionManagerRP subscriptionRP) throws WsnbException{		

		this.concreteTopExprs = new ArrayList<TopicExpressionType>();

		this.subscriptionResource=subscriptionRP;		

		this.subscriptionReference = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReference.class);
		Address address = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
		address.setValue(wsaAddress);
		this.subscriptionReference.setAddress(address);

		ReferenceParameters refParams = WSNUtil.getInstance().getXmlObjectFactory().create(ReferenceParameters.class);

		refParams.addAny(Wsnb4ServUtils.createSubscriptionIdAsReferenceParamElt(subscriptionId));
		this.subscriptionReference.setReferenceParameters(refParams);

		this.logger = logger;
		// ------- Set endpoint reference for Subscription :
		//WsaHelper.setAddressOfEndpointReference(this.subscriptionReference,wsaAddress);

	}

	// ##################################################
	//  		----- {Getter,Setter} methods ------
	// ##################################################

	/**
	 * 
	 * @return
	 */
	public String getWsResourceEdpBase() {
		return this.wsResourceEdpBase;
	}

	public void setWsResourceEdpBase(String subscriptionEdp) {
		this.wsResourceEdpBase = subscriptionEdp;
	}

	public QName getWsrfSubscriptionService() {
		return this.wsResourceService;
	}

	public void setWsrfSubscriptionService(QName subscriptionService) {
		this.wsResourceService = subscriptionService;
	}

	public QName getWsrfSubscriptionInterface() {
		return (this.wsResourceInterface!=null)?
				this.wsResourceInterface : WsnbConstants.SUBSCRIPTION_MANAGER_INTERFACE;
	}

	public void setWsrfSubscriptionInterface(QName subscriptionsMgrInterface) {
		this.wsResourceInterface = subscriptionsMgrInterface;
	}

	public EndpointReferenceType getSubscriptionReference() {
		return subscriptionReference;
	}

	public TerminationTime getTerminationTime() {
		if(this.terminationRP != null) {
			return this.terminationRP.getTerminationTime();
		} else {
			return null;
		}
	}

	//	public void setTerminationTime(TerminationTime terminationTime) {
	//		this.terminationRP.setTerminationTime(terminationTime);
	//	}

	public SubscriptionManagerRP getSubscriptionResource() {
		return subscriptionResource;
	}

	// ##########################################################################################
	// 	----- Methods' implementation of WS-ResourceLifetime/WS-ResourceProperties Interfaces ----
	// ##########################################################################################

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.wsrfrl.services.IScheduledResourceTermination#setTerminationTime(com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime)
	 */
	public SetTerminationTimeResponse setTerminationTime(SetTerminationTime request) throws WsrfrlException, AbsWSStarFault {	
		logger.log(Level.FINE, "performs a \"SetTerminationTime\" request ...");

		SetTerminationTimeResponse response = null;

		if ( this.subscriptionResource == null)
			try {
				throw new ResourceUnknownFault(WsnbFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE,
						WsnbFaultMessageContentConstants.WsnbRenewFaultDescriptions.RESOURCE_UNKNOWN_FAULT_DESC);
			} catch (WsrfrException e1) {
				throw new WsrfrlException(e1);
			} catch (WsrfbfException e1) {
				throw new WsrfrlException(e1);
			}

			Object newTerminationTimeVal = (request.getRequestedTerminationTime() == null)?
					request.getRequestedLifetimeDuration():request.getRequestedTerminationTime();

					Date currentTimeVar = new Date(), newTerminationTime = null; 

					if (newTerminationTimeVal instanceof Duration){
						((Duration)newTerminationTimeVal).addTo(currentTimeVar);	
					} else { 
						newTerminationTime = ((Date)newTerminationTimeVal);
					}

					(this.terminationRP.getTerminationTime()).setValue(newTerminationTime);		
					response = RefinedWsrfrlFactory.getInstance().createSetTerminationTimeResponse(currentTimeVar, newTerminationTime);			

					return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.wsrfrl.services.IImmediateResourceTermination#destroy(com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy)
	 */
	public DestroyResponse destroy(Destroy payload) throws WsrfrlException, AbsWSStarFault{
		logger.log(Level.FINE, "performs a \"Destroy\" request ...");


		// ---- perform a subscription destruction ---
		// --- Get the reference of the consumer to notify
		EndpointReferenceType consumerRef = this.subscriptionResource.getConsumerReference();

		// TODO : Remove persisted version

		// --- Remove resources
		this.subscriptionResource = null;		
		this.subscriptionReference = null;
		this.terminationRP = null;

		// notify consumer sending "TerminationNotification" notification
		if (this.transporter != null) {
			// --- build notification message --- 
			TerminationNotification terminationNotification = 
				RefinedWsrfrlFactory.getInstance().createTerminationNotification(new Date());
			terminationNotification.setTerminationReason(Wsnb4ServUtils.createSimpleReasonElement(WsnSubscription.IMMEDIAT_TERMINAISON_REASON));

			// --- build notify payload ---
			try {
				Document notifAsDoc = Wsnb4ServUtils.getWsrfrlWriter().writeTerminationNotificationAsDOM(terminationNotification);
				NotificationMessageHolderType.Message message;
				message = RefinedWsnbFactory.getInstance().createNotificationMessageHolderTypeMessage(notifAsDoc.getDocumentElement());
				NotificationMessageHolderType formattedNotif;
				formattedNotif = RefinedWsnbFactory.getInstance().createNotificationMessageHolderType(message);				

				// --- Set related topic : {http://docs.oasis-open.org/wsrf/rl-2}ResourceTermination ---
				TopicExpressionType topic = RefinedWsnbFactory.getInstance().createTopicExpressionType(WstopConstants.SIMPLE_TOPIC_EXPRESSION_DIALECT_URI);
				topic.addTopicNamespace(WsrfrlConstants.WS_RESOURCE_LIFETIME_PREFIX, new URI(WsrfrlConstants.WS_RESOURCE_LIFETIME_NAMESPACE_URI));
				topic.setContent(WsrfrlConstants.RESOURCE_TERMINATION_TOPIC_EXPRESSION_CONTENT);
				formattedNotif.setTopic(topic);

				// --- Build payload
				Notify notifPayload = RefinedWsnbFactory.getInstance().createNotify(formattedNotif);				

				this.transporter.sendNotifyRequest(consumerRef, notifPayload);
			} catch (WsnbException e) {
				new WsnbException(e);
			} catch (URISyntaxException e) {
				new WsnbException(e);
			}
		}

		return RefinedWsrfrlFactory.getInstance().createDestroyResponse();

	}

	// ###############################################
	// 			---- Others methods ----
	// ###############################################

	public EndpointReferenceType getConsumerEdpRef() throws WsnbException{
		return this.subscriptionResource.getConsumerReference();
	}

	public List<TopicExpressionType> getTopicExpressionOfSubscription() throws WsnbException{		
		FilterType filter = this.subscriptionResource.getFilter();
		return (filter != null)? filter.getTopicExpressions() : null;				
	}

	public FilterType getFilterOfSubscription() throws WsnbException{
		return this.subscriptionResource.getFilter();
	}

	public SubscriptionPolicyType getPolicyOfSubscription() throws WsnbException {
		return this.subscriptionResource.getSubscriptionPolicy();		
	}

	// ######################################################
	//        ---- Added part related to TopiExpre ----
	//  #####################################################

	public List<TopicExpressionType> getAssociatedTopicExprs(){
		return this.concreteTopExprs;
	}

	public void setAssociatedTopicExprs(List<TopicExpressionType> concreteTopics){
		this.concreteTopExprs.clear();
		this.concreteTopExprs.addAll(concreteTopics);
	}

	public int removeAssociatedTopicExpr(TopicExpressionType concreteTopic){

		int remainingTopicExprs = this.concreteTopExprs.size();

		List<TopicExpressionType> topExprstoRemove = new ArrayList<TopicExpressionType>();

		String concreteTopicContent = concreteTopic.getContent();
		QName concreteTopicNamespace = concreteTopic.getTopicNamespaces().get(0);

		for (TopicExpressionType topicItem : this.concreteTopExprs) {
			if((concreteTopicNamespace.equals(topicItem.getTopicNamespaces().get(0))) &&
					(topicItem.getContent().startsWith(concreteTopicContent))){
				topExprstoRemove.add(topicItem);								
			}
		}	

		for (TopicExpressionType topicItem : topExprstoRemove) {
			this.concreteTopExprs.remove(topicItem);
		}

		remainingTopicExprs = this.concreteTopExprs.size();

		return remainingTopicExprs;
	}

	/*
	public void removeExpiredSubscription(String subscriptionId) throws WsnbException{
		if (this.topicsMgr == null)
			throw new WSNotificationTopicManagerBadUsageException(this.getClass().getSimpleName(),"removeExpiredSubscription()");

		if  (subscriptions.containsKey(subscriptionId)){

			subscriptions.remove(subscriptionId);							
			try {
				this.topicsMgr.removeExistingSubscription(subscriptionId);
			} catch (WSNotificationFault e) {
				throw new WsnbException(e);
			}
			if (this.persistenceMgr != null)
				this.persistenceMgr.removeSubscription(subscriptionId);
		} 
	}
	 */

	/*public Map<String,Subscribe> getSubscriptionsRPAsSubscribePayoad() throws WsnbException{
		Map<String,Subscribe> result = new ConcurrentHashMap<String, Subscribe>();


		SubscriptionManagerRP currentSubscriptionManagerRP = null;

		for (Map.Entry<String, SubscriptionManagerRP> mapPairItem : this.subscriptions.entrySet()) {			

			currentSubscriptionManagerRP = mapPairItem.getValue();

			Subscribe currentSubscribePayload = RefinedWsnbFactory.getInstance().createSubscribe();

			currentSubscribePayload.setConsumerReference(currentSubscriptionManagerRP.getConsumerReference());
			currentSubscribePayload.setFilter(currentSubscriptionManagerRP.getFilter());
			currentSubscribePayload.setSubscriptionPolicy(currentSubscriptionManagerRP.getSubscriptionPolicy());

			// TODO : made followin code available when life time will be implemented (in ver. 1.0-SNAPSHOT)
			//currentSubscribePayload.setInitialTerminationTime(currentSubscriptionManagerRP.getTerminationTime());

			result.put(mapPairItem.getKey(), currentSubscribePayload);	
		}

		return result;
	}*/

}


