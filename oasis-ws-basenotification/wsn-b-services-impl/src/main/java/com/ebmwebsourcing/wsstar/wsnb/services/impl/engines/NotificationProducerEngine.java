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
package com.ebmwebsourcing.wsstar.wsnb.services.impl.engines;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationProducerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbFaultMessageContentConstants;
import com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.Message;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpFaultMessageContentConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.refinedabstraction.RefinedWstopFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopDatatypeUtils;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;
import com.ebmwebsourcing.wsstar.wsnb.services.INotificationProducer;
import com.ebmwebsourcing.wsstar.wsnb.services.INotificationProducerRP;
import com.ebmwebsourcing.wsstar.wsnb.services.faults.NoCurrentMessageOnTopicFault;
import com.ebmwebsourcing.wsstar.wsnb.services.faults.TopicNotSupportedFault;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.topic.TopicsManagerEngine;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.Wsnb4ServUtils;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.WsnbTopicManagerBadUsageException;
import com.ebmwebsourcing.wsstar.wsnb.services.transport.ITransporterForWsnbPublisher;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;
import com.ebmwebsourcing.wsstar.wsrfrp.services.faults.InvalidResourcePropertyQNameFault;
import com.ebmwebsourcing.wsstar.wsrfrp.services.faults.UnableToModifyResourcePropertyFault;

/**
 * @author tdejean - eBM WebSourcing
 *
 */
public class NotificationProducerEngine implements INotificationProducer,INotificationProducerRP {

	protected Logger logger;
	protected String wsnProducerEndpoint = "";
	protected QName wsnProducerService = null;//new QName("http://www.ebmwebsourcing.com/default","NotificationProducerService");
	protected QName wsnProducerInterface = null;//new QName("http://www.ebmwebsourcing.com/default","NotificationProducer");	

	protected NotificationProducerRP actorAsRP = null;
	protected TopicNamespaceType topicNSForRPChangeValueNotif = null;
	protected String associatedNSPrefix = null; 

	protected TopicsManagerEngine topicsManager;
	protected SubscriptionManagerEngine subscriptionManagerService;

	protected Map<QName, List<NotificationMessageHolderType.Message>> currentMessages = null;

	protected ITransporterForWsnbPublisher notificationSender = null;

	public NotificationProducerEngine(Logger logger, TopicsManagerEngine topicsMgr, SubscriptionManagerEngine subsMgr, 
			boolean isFixedTopicSet,TopicSetType supportedTopics, TopicNamespaceType topicNSForRPChangeNotif,String prefixForNs, 
			ITransporterForWsnbPublisher transporter) throws WsnbException {		
		this.logger = logger;
		this.topicsManager = topicsMgr;
		this.subscriptionManagerService = subsMgr;
		this.topicNSForRPChangeValueNotif = topicNSForRPChangeNotif;
		this.associatedNSPrefix = prefixForNs;
		this.notificationSender = transporter;

		this.initActorAsRPAndAttributes(isFixedTopicSet,supportedTopics);
	}

	public NotificationProducerEngine(Logger logger ,SubscriptionManagerEngine subsMgr, boolean isFixedTopicSet,
			TopicSetType supportedTopics, TopicNamespaceType topicNSForRPChangeNotif, String prefixForNs, 
			ITransporterForWsnbPublisher transporter) throws WsnbException {
		this.logger = logger;
		this.subscriptionManagerService = subsMgr;

		this.topicNSForRPChangeValueNotif = topicNSForRPChangeNotif;
		this.associatedNSPrefix = prefixForNs;

		this.notificationSender = transporter;
		this.initActorAsRPAndAttributes(isFixedTopicSet,supportedTopics);
	}

	public NotificationProducerEngine(boolean isFixedTopicSet,TopicSetType supportedTopics,
			TopicNamespaceType topicNSForRPChangeNotif, String prefixForNs,
			ITransporterForWsnbPublisher transporter) throws WsnbException{	

		this.topicNSForRPChangeValueNotif = topicNSForRPChangeNotif;
		this.associatedNSPrefix = prefixForNs;
		this.notificationSender = transporter;

		this.initActorAsRPAndAttributes(isFixedTopicSet,supportedTopics);
	}

	protected void initActorAsRPAndAttributes(boolean isFixedTopicSet,TopicSetType supportedTopics) throws WsnbException{
		// ----- resource properties -----
		this.actorAsRP = RefinedWsnbFactory.getInstance().createNotificationProducerRP();		
		this.actorAsRP.setFixedTopicSet(isFixedTopicSet);

		// TODO : add "{http://docs.oasis-open.org/wsn/b-2}TopicSet" topic
		// TODO : associated to "ResourcePropertyValueChangeNotificatin" notification
		TopicSetType mergedTopicSet = supportedTopics;
		if(!isFixedTopicSet) {
			try {
				mergedTopicSet =
					WstopDatatypeUtils.addTopicsFromTopicNamespaceToTopicSet(supportedTopics,this.topicNSForRPChangeValueNotif,
							this.associatedNSPrefix);
			} catch (WstopException e) {
				throw new WsnbException(e);
			}
		}
		
		try {
			org.w3c.dom.Document topicSetDom = RefinedWstopFactory.getInstance().getWstopWriter().writeTopicSetTypeAsDOM(mergedTopicSet);
			
		} catch (WstopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// ~ Set the "TopicSet" property  
		this.actorAsRP.setTopicSet(mergedTopicSet);
		// ------- other attributes ------	
		this.currentMessages = new ConcurrentHashMap<QName, List<NotificationMessageHolderType.Message>>();
	}

	public void setNotificationProducerEdp(String notificationProducerEdp) {
		this.wsnProducerEndpoint = notificationProducerEdp;		
	}

	public String getNotificationProducerEdp() {
		return wsnProducerEndpoint;
	}

	public void setNotificationProducerService(
			QName notificationProducerService) {
		this.wsnProducerService = notificationProducerService;		
	}

	public QName getNotificationProducerService() {
		return this.wsnProducerService;
	}

	public void setNotificationProducerInterface(
			QName notificationProducerInterface) {
		this.wsnProducerInterface = notificationProducerInterface;
	}

	public QName getNotificationProducerInterfaceQName() {
		return (this.wsnProducerInterface !=null)?
				this.wsnProducerInterface : WsnbConstants.NOTIFICATION_PRODUCER_INTERFACE;
	}	

	public NotificationProducerRP getActorAsRP() {
		return actorAsRP;
	}

	public TopicsManagerEngine getTopicsMgr() {
		return topicsManager;
	}

	public void setTopicsMgr(TopicsManagerEngine topicsMgr) {
		this.topicsManager = topicsMgr;
	}

	public SubscriptionManagerEngine getSubsMgr() {
		return subscriptionManagerService;
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.notification.service.basenotification.WsnbNotificationProducer#getCurrentMessage(com.ebmwebsourcing.wsstar.notification.service.test.wsnotification.base.GetCurrentMessage)
	 */
	public GetCurrentMessageResponse getCurrentMessage(GetCurrentMessage request) throws WsnbException, AbsWSStarFault {
		logger.log(Level.FINE, "performs a \"GetCurrentMessage\" request ...");
		//TODO : implement this method !!!
		//throw new WSNotificationNotImplementedException(this.getClass().getName(),"GetCurrentMessage");

		if (this.topicsManager == null)
			throw new WsnbTopicManagerBadUsageException(this.getClass().getSimpleName(),"subscribe()");

		GetCurrentMessageResponse response = null;
		// TODO:
		// 	1 get CurrentMessage uuid from TopicManager according to the given Topic expression
		//	2 build the GetCurrentMessageResponse and return it

		TopicExpressionType topic = request.getTopic();

		//String curMsgUuid = this.topicsManager.getNotifContentUuid(topic,false);
		int indexEndPrefix = topic.getContent().indexOf(":");

		QName concreteTopicKey = new QName(topic.getTopicNamespaces().get(0).getNamespaceURI(),
				topic.getContent().substring(indexEndPrefix)+1);

		List<NotificationMessageHolderType.Message> notifs = this.currentMessages.get(concreteTopicKey/*curMsgUuid*/);

		if (notifs != null && notifs.size()> 0) {

			response = RefinedWsnbFactory.getInstance().createGetCurrentMessageResponse(notifs.get(0));	
			for (int i = 1 ; i < notifs.size(); i++) {
				response.addCurrentMessage(notifs.get(i));
			}

		} else
			try {
				throw new NoCurrentMessageOnTopicFault(WsnbFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE,
						WsnbFaultMessageContentConstants.WsnbGetCurrentMessageFaultDescriptions.NO_CURRENT_MESSAGE_ON_TOPIC_FAULT_DESC);
			} catch (WsrfbfException e) {
				throw new WsnbException(e);
			}

			return response;
	}

	/**
	 * Method call to store the last notification ({@link Message} object)
	 * sent by the NotificationProducer service, according to WS-BaseNotification
	 * specification content ("GetCurrentMessage" operation in "NotificationProducer"
	 * interface description part)
	 *  
	 * @param topic 
	 * @param notification
	 * @param isSameNotifyRequest
	 * @throws WsnbException
	 * @throws AbsWSStarFault
	 */
	public void setCurrentMessage(TopicExpressionType topic, NotificationMessageHolderType.Message notification, boolean isSameNotifyRequest) throws WsnbException, AbsWSStarFault {

		// 1/ ask topicManager if a previous currentMessage exists for this topic :
		//	 getCurrentMessageUuid() from TopicManagerMgr and check if it is null or not
		// 2/ if currentMessageUuid is null then 
		//						- generate new one
		//	   else 
		//						- used the existing one
		// 3/ store(replace) the pair <uuid,Message> in hasMap
		// 4/ store/(replace) the currentMessageUuid in the topicManager's "supportedTopicSet base" 

		//		String currentMessageUuid = null;

		//QName currentMessageKey = new QName(topic.getTopicNamespaces().get(0).getNamespaceURI(),topic.getContent());
		int indexEndPrefix = topic.getContent().indexOf(":");

		QName currentMessageKey = new QName(topic.getTopicNamespaces().get(0).getNamespaceURI(),
				topic.getContent().substring(indexEndPrefix)+1);

		//		try {
		//			currentMessageUuid = this.topicsManager.getNotifContentUuid(topic,true);
		//
		//			if ((currentMessageUuid == null) || (currentMessageUuid.length()==0)){
		//				currentMessageUuid = UUID.randomUUID().toString();	
		//				this.topicsManager.storeNewResourceUuid(topic, currentMessageUuid,WstConstants.CURRENT_MESSAGE_ID_QNAME);
		//			}

		List<NotificationMessageHolderType.Message> lastNotifications = this.currentMessages.get(currentMessageKey);
		if (lastNotifications == null) {
			lastNotifications = new ArrayList<NotificationMessageHolderType.Message>();
			this.currentMessages.put(currentMessageKey, lastNotifications);
		} else if (!isSameNotifyRequest)
			lastNotifications.clear();

		lastNotifications.add(notification);

		//		} catch (WSNotificationFault e) {
		//			if ((e instanceof TopicNotSupportedFault) ||
		//					(e instanceof TopicExpressionDialectUnknownFault) ||
		//					(e instanceof MultipleTopicsSpecifiedFault)) {
		//				throw e;
		//			} else 
		//			throw new WsnbException(e);
		//		}
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.notification.service.basenotification.WsnbNotificationProducer#subscribe(com.ebmwebsourcing.wsstar.notification.service.test.wsnotification.base.Subscribe)
	 */
	public SubscribeResponse subscribe(Subscribe request) throws WsnbException, AbsWSStarFault{
		logger.log(Level.FINE, "performs a \"Subscribe\" request ...");

	
		
		if (this.topicsManager == null)
			throw new WsnbTopicManagerBadUsageException(this.getClass().getSimpleName(),"subscribe()");

		// ---- temporary code :
		SubscribeResponse reponse = null;

		// ---- create new subscription :
		List<TopicExpressionType> topics = request.getFilter().getTopicExpressions();
		
		
		
		
		try {			
			String subscriptionId = UUID.randomUUID().toString();

			List<TopicExpressionType> concreteTopics = new ArrayList<TopicExpressionType>();

			// convert TopicSetType as DOM Document 
			// write to DOM Document the given TopicSetType object
			// TODO : Is it efficient enough (subscribe request are
			// TODO : not those which generate to more message exchange!)  
			Document supportedTopicsAsDOM = Wsnb4ServUtils.getWstopWriter().writeTopicSetTypeAsDOM(this.actorAsRP.getTopicSet());
			
			for (TopicExpressionType topExprItem : topics) {
			    
			    
				List<TopicExpressionType> foundTopics = this.topicsManager.getTopicsAsConcreteTopExpr(topExprItem,supportedTopicsAsDOM);
				if (foundTopics.isEmpty()) {
					throw new TopicNotSupportedFault(WsnbFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE,
							WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.TOPIC_NOT_SUPPORTED_FAULT_DESC);	
				}
				concreteTopics.addAll(foundTopics);
			}

			EndpointReferenceType subsRef = this.subscriptionManagerService.createAndStoreSubscriptionResource(subscriptionId,concreteTopics,request);

			reponse = RefinedWsnbFactory.getInstance().createSubscribeResponse(subsRef);
			reponse.setCurrentTime(new Date());
			Date termTime  = this.subscriptionManagerService.getTerminationTimeOfSubscription(subscriptionId);

			if (termTime!= null)
				reponse.setTerminationTime(termTime);			

		} catch (WstopException e) {
			throw new WsnbException(e);
		} catch (WsrfbfException e) {
			throw new WsnbException(e);
		} 

		return reponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.wsnb.services.INotificationProducerRP#getResourceProperty(javax.xml.namespace.QName)
	 */
	@Override
	public GetResourcePropertyResponse getResourceProperty(QName property)
	throws WsnbException, AbsWSStarFault {
		GetResourcePropertyResponse response = null;
		List<Element> values = null;

		try {
			if (property.equals(WstopConstants.TOPIC_SET_QNAME)){

				TopicSetType topicSetRP = this.actorAsRP.getTopicSet();

				if (topicSetRP != null){
					values = new ArrayList<Element>();											
					values.add(Wsnb4ServUtils.getWstopWriter().writeTopicSetTypeAsDOM(topicSetRP).getDocumentElement());
				} else {
					throw this.createInvalidResourcePropertyQNameFault();				
				}

			} else if(property.equals(WsnbConstants.FIXED_TOPIC_SET_QNAME)){

				Boolean isFixedTopicSetRP = this.actorAsRP.isFixedTopicSet();

				if (isFixedTopicSetRP != null){
					values = new ArrayList<Element>();				
					values.add(Wsnb4ServUtils.createPropertyAsElement(property,isFixedTopicSetRP.toString()));
				} else {
					throw createInvalidResourcePropertyQNameFault();				
				}

			} else if (property.equals(WsnbConstants.TOPIC_EXPRESSION_QNAME)) {

				List<TopicExpressionType> topicExprList = this.actorAsRP.getTopicExpressions();
				if (topicExprList != null){
					values = new ArrayList<Element>();				
					for (TopicExpressionType topExprItem : topicExprList) {
						values.add(Wsnb4ServUtils.getWsnbWriter().writeTopicExpressionTypeAsDOM(topExprItem).getDocumentElement());
					}
				} else {
					throw createInvalidResourcePropertyQNameFault();
				}

			} else if (property.equals(WsnbConstants.TOPIC_EXPRESSION_DIALECT_QNAME)){
				values = new ArrayList<Element>();
				List<URI> topicExprDialectList = this.actorAsRP.getTopicExpressionDialects();
				if (topicExprDialectList != null){
					values = new ArrayList<Element>();				
					for (URI dialectItem : topicExprDialectList) {						
						values.add(Wsnb4ServUtils.createPropertyAsElement(property,dialectItem.toString()));
					}
				} else {
					throw createInvalidResourcePropertyQNameFault();
				}				
			}

			// --- build response ---
			if (values == null){
				throw createInvalidResourcePropertyQNameFault();
			} else {
				response = RefinedWsrfrpFactory.getInstance().createGetResourcePropertyResponse();			
				response.setPropertyValue(values);
			}
		} catch (WsrfrpException e) {
			throw new WsnbException(e);
		} catch (WstopException e) {
			throw new WsnbException(e);
		}
		return response;
	}

	/**
	 * Factorize  {@link InvalidResourcePropertyQNameFault} instance creation
	 * 
	 * @return {@link InvalidResourcePropertyQNameFault} fault instance
	 * @throws WsnbException 
	 * @throws InvalidResourcePropertyQNameFault 
	 */
	private InvalidResourcePropertyQNameFault createInvalidResourcePropertyQNameFault() throws WsnbException{
		try {
			return new InvalidResourcePropertyQNameFault(WsrfrpFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE, 
					WsrfrpFaultMessageContentConstants.WsrfrpGetResourcePropertyFaultDescriptions.INVALID_RESOURCE_PROPERTY_QNAME_FAULT_DESC);
		} catch (WsrfrpException e) {
			throw new WsnbException(e);
		} catch (WsrfbfException e) {
			throw new WsnbException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.wsnb.services.INotificationProducerRP#updateResourceProperties(com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties)
	 */
	@Override
	public UpdateResourcePropertiesResponse updateResourceProperties(UpdateResourceProperties request) throws WsnbException,
	AbsWSStarFault {
		UpdateResourcePropertiesResponse response = null;		

		try {

			UpdateType content = request.getUpdate();
			List<Element> properties = content.getUpdateContent();

			for (Element propItem : properties) {

				if (propItem.getLocalName().equals(WsnbConstants.FIXED_TOPIC_SET_QNAME.getLocalPart()) &&
						propItem.getNamespaceURI().equals(WsnbConstants.FIXED_TOPIC_SET_QNAME.getNamespaceURI())){
					this.actorAsRP.setFixedTopicSet(new Boolean(propItem.getTextContent()));					

				} else if (propItem.getLocalName().equals(WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart()) &&
						propItem.getNamespaceURI().equals(WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI())) {

					// TODO : actually not managed so these property is not modifiable
					ResourcePropertyChangeFailureType failure = 
						RefinedWsrfrpFactory.getInstance().createResourcePropertyChangeFailureType(true);

					throw new UnableToModifyResourcePropertyFault(WsrfrpFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE, 
							WsrfrpFaultMessageContentConstants.WsrfrpGetResourcePropertyFaultDescriptions.INVALID_RESOURCE_PROPERTY_QNAME_FAULT_DESC,
							failure);

				} else if (propItem.getLocalName().equals(WsnbConstants.TOPIC_EXPRESSION_DIALECT_QNAME.getLocalPart()) &&
						propItem.getNamespaceURI().equals(WsnbConstants.TOPIC_EXPRESSION_DIALECT_QNAME.getNamespaceURI())) {

					// TODO : actually not managed so these property is not modifiable
					ResourcePropertyChangeFailureType failure = 
						RefinedWsrfrpFactory.getInstance().createResourcePropertyChangeFailureType(true);

					throw new UnableToModifyResourcePropertyFault(WsrfrpFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE, 
							WsrfrpFaultMessageContentConstants.WsrfrpGetResourcePropertyFaultDescriptions.INVALID_RESOURCE_PROPERTY_QNAME_FAULT_DESC,
							failure);

				} else if (propItem.getLocalName().equals(WstopConstants.TOPIC_SET_QNAME.getLocalPart()) &&
						propItem.getNamespaceURI().equals(WstopConstants.TOPIC_SET_QNAME.getNamespaceURI())) {

					if (!this.actorAsRP.isFixedTopicSet()){

						Document topicSetAsDOM = propItem.getOwnerDocument();
						TopicSetType newTopicSet = Wsnb4ServUtils.getWstopReader().readTopicSetType(topicSetAsDOM);					
						this.actorAsRP.setTopicSet(newTopicSet);

						// Notify "ResourceProperty changes" :
						//   => need to improve more datatype implementation 
						//   => can be made as a second iteration ?
						this.notifyTopicSetResourceModification(newTopicSet,this.actorAsRP.getTopicSet());

						//  Check Stored currentMessage respect to new TopicSet.
						//    => remove stored "currentMessage" which associated topic is no longer supported.
						
						// HACK : Don't care about that. Need to check the old topic and new topic...
						// this.updateStoredCurrentMsgList(topicSetAsDOM);

						// --- Check current subscription respect to new TopicSet :
						//  	=> "terminate" ones which associated topics is no longer supported.
						//      => "Notify" termination of these subscriptions to related subscribers
						List<String> uuidsOfToTerminateSubscription =
							this.subscriptionManagerService.lookForSubscriptionToTerminate(topicSetAsDOM, this.topicsManager);

						for (String uuidItem : uuidsOfToTerminateSubscription) {
							this.subscriptionManagerService.terminateSubscription(uuidItem);
						}



					} else {

						// --- Set "isRestored" to true since not modified
						ResourcePropertyChangeFailureType failure = 
							RefinedWsrfrpFactory.getInstance().createResourcePropertyChangeFailureType(true);

						throw new UnableToModifyResourcePropertyFault(WsrfrpFaultMessageContentConstants.FAULT_DESCRIPTION_LANGUAGE, 
								WsrfrpFaultMessageContentConstants.WsrfrpGetResourcePropertyFaultDescriptions.INVALID_RESOURCE_PROPERTY_QNAME_FAULT_DESC,
								failure);
					}

				} else {
					throw createInvalidResourcePropertyQNameFault();
				}
			}

			response = RefinedWsrfrpFactory.getInstance().createUpdateResourcePropertiesResponse();

		} catch (WsrfrpException e) {
			throw new WsnbException(e);
		} catch (WstopException e) {
			throw new WsnbException(e);
		} catch (WsrfbfException e) {
			throw new WsnbException(e);
		}
		return response; 
	}

	/**
	 * Build a "ResourcePropertyValueChangeNotificatin" notification
	 * 	and send it to all related consumers that have subscribed on
	 * "TopicSet" topic.
	 * 
	 * @param newTopicSet
	 * @param topicSet
	 * @throws WsnbException 
	 */
	private void notifyTopicSetResourceModification(TopicSetType newTopicSet,
			TopicSetType topicSet) throws WsnbException {

		// --- notify TopicSet RP change --- 
		if (this.notificationSender != null) {
			// TODO : Implement "ResourcePropertyValueChangeNotification" for TopicSet RP 
			try {
				// --- build notification message --- 
				Element newTopicSetAsElt = Wsnb4ServUtils.getWstopWriter().writeTopicSetTypeAsDOM(newTopicSet).getDocumentElement(),
				oldTopicSetAsElt = Wsnb4ServUtils.getWstopWriter().writeTopicSetTypeAsDOM(topicSet).getDocumentElement();

				ResourcePropertyValueChangeNotificationType.NewValues newTopicSetValue =
					RefinedWsrfrpFactory.getInstance().createResourcePropertyValueChangeNotificationTypeNewValues(newTopicSetAsElt);

				ResourcePropertyValueChangeNotificationType.OldValues oldTopicSetValue = 
					RefinedWsrfrpFactory.getInstance().createResourcePropertyValueChangeNotificationTypeOldValues(oldTopicSetAsElt);

				ResourcePropertyValueChangeNotificationType notifMessage = 
					RefinedWsrfrpFactory.getInstance().createResourcePropertyValueChangeNotificationType(newTopicSetValue);				
				notifMessage.setOldValues(oldTopicSetValue);

				// --- build notify payload ---

				Document notifAsDoc = Wsnb4ServUtils.getWsrfrpWriter().writeResourcePropertyValueChangeNotificationTypeAsDOM(notifMessage);
				NotificationMessageHolderType.Message message;
				message = RefinedWsnbFactory.getInstance().createNotificationMessageHolderTypeMessage(notifAsDoc.getDocumentElement());
				NotificationMessageHolderType formattedNotif = RefinedWsnbFactory.getInstance().createNotificationMessageHolderType(message);				

				// --- Set related topic : {http://docs.oasis-open.org/wsrf/rl-2}ResourceTermination ---
				TopicExpressionType topic = RefinedWsnbFactory.getInstance().createTopicExpressionType(WstopConstants.SIMPLE_TOPIC_EXPRESSION_DIALECT_URI);
				topic.addTopicNamespace(this.associatedNSPrefix, this.topicNSForRPChangeValueNotif.getNamespace());
				topic.setContent(this.associatedNSPrefix + ":" +"TopicSet");
				formattedNotif.setTopic(topic);

				// --- Build payload ---
				Notify notifPayload = RefinedWsnbFactory.getInstance().createNotify(formattedNotif);				

				// --- Notify all known subscribed consumer exactly one time ---				
				List<String> subscriptionUuids = this.subscriptionManagerService.getStoredSubscriptionUuids();		
				List<EndpointReferenceType> consumerAlreadyNotified = new ArrayList<EndpointReferenceType>();
				EndpointReferenceType currentConsumerEdp = null;
				for (String uuidItem : subscriptionUuids) {																
					currentConsumerEdp = this.subscriptionManagerService.getConsumerEdpRefOfSubscription(uuidItem);
					if (this.mustBeNotified(currentConsumerEdp, consumerAlreadyNotified)){
						this.notificationSender.sendNotifyRequest(currentConsumerEdp,notifPayload);	
						consumerAlreadyNotified.add(currentConsumerEdp);
					}
				}

			} catch (WsrfrpException e) {
				throw new WsnbException(e);
			} catch (WstopException e) {
				throw new WsnbException(e);
			}
		}
	}


	/**
	 * check if a notification has already been
	 * sent to the consumer 
	 *   
	 * @param currentConsumerEdp
	 */
	private boolean mustBeNotified(EndpointReferenceType currentConsumerEdp,List<EndpointReferenceType> edpList) {
		boolean result = true;

		return result;
	}

	/**
	 * Check stored CurrentMessage list respect to new "TopicSet"
	 * and remove those associated to topics no longer supported
	 * @throws WsnbException 
	 */
	private void updateStoredCurrentMsgList(Document topicSetAsDOM) throws WsnbException{
		List<QName> currentMessageToRemoveKey = new ArrayList<QName>();

		TopicExpressionType currentConcreteTopExpr = null;
		int currentPrefixIndex = -1;
		String currentLocalPart = null;

		// --- look for "currentMessage" to remove
		for (QName keyItem : this.currentMessages.keySet()) {

			currentLocalPart = keyItem.getLocalPart();
			currentPrefixIndex = currentLocalPart.indexOf(":");

			currentConcreteTopExpr = 
				RefinedWsnbFactory.getInstance().createTopicExpressionType(WstopConstants.CONCRETE_TOPIC_EXPRESSION_DIALECT_URI);

			try {
				currentConcreteTopExpr.addTopicNamespace(currentLocalPart.substring(0, currentPrefixIndex),
						new URI(keyItem.getNamespaceURI()));
			} catch (URISyntaxException e) {
				throw new WsnbException(e);
			}
			currentConcreteTopExpr.setContent(currentLocalPart);

			if (!this.topicsManager.isSupportedTopic(currentConcreteTopExpr, topicSetAsDOM)){
				currentMessageToRemoveKey.add(keyItem);
			}
		}

		// --- remove currentMessages 
		for (QName keyitem : currentMessageToRemoveKey) {
			this.currentMessages.remove(keyitem);
		}

	}

}
