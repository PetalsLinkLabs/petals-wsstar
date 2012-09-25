/**
 * easySchema - easyWSDL toolbox Platform.
 * Copyright (c) 2008,  eBM Websourcing
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of California, Berkeley nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ebmwebsourcing.wsstar.wsnb.services.impl.test;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.ebmwebsourcing.easycommons.xml.XMLPrettyPrinter;
import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.element.EndpointReference;
import com.ebmwebsourcing.wsaddressing10.api.element.ReferenceParameters;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.WsrfbfModelFactoryImpl;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
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
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl.WsnbModelFactoryImpl;
import com.ebmwebsourcing.wsstar.resource.datatypes.impl.impl.WsrfrModelFactoryImpl;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl.WsrfrlModelFactoryImpl;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.WsrfrpConstants;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl.WsrfrpModelFactoryImpl;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.refinedabstraction.RefinedWstopFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl.WstopModelFactoryImpl;
import com.ebmwebsourcing.wsstar.wsnb.services.faults.TopicNotSupportedFault;
import com.ebmwebsourcing.wsstar.wsnb.services.impl.util.Wsnb4ServUtils;
import com.ebmwebsourcing.wsstar.wsnb.services.transport.ITransporterForWsnbRequestor;
import com.ebmwebsourcing.wsstar.wsnb.services.transport.ITransporterForWsnbSubscriber;
import com.ebmwebsourcing.wsstar.wsrfbf.services.faults.AbsWSStarFault;
import com.ebmwebsourcing.wsstar.wsrfr.services.faults.ResourceUnknownFault;

/**
 * @author tdejean - eBM WebSourcing
 */

public class TestBaseNotificationServicesTest extends TestCase {

	private final String MAIN_PERSISTENCE_FOLDER = "tmp_for_persistence";
	
	private final String TEST_NAME = "testName";
	
	private final String SUBS_REQUEST_AS_OBJ = "subsRequestAsObj";
	private final String SUBS_RESPONSE_AS_OBJ = "subsResponseAsObj";
	
	private final String CURRENT_MESSAGE_RESP = "getCurrentMessageResponse";
	
	private final String RENEW_REQUEST_AS_OBJ = "renewRequestAsObj";
	private final String RENEW_RESPONSE_AS_OBJ = "renewResponseAsObj";
	
	private final String WSNFAULT_EXPECTED = "wsnFaultExpected"; 
	private final String MUST_BE_STORED = "mustBeStored"; 
		
	private NotificationProducerService wsnbProducerService;
	private SubscriptionManagerService wsnbSubscriptionMgrService;
	private NotificationConsumerService wsnbConsumerService;
	
	private RequestorActor requestor;
	
	private Map<String, Object> properties = null;	
	
	boolean isPassed = false, isDebug = false;		
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// ----- /!\ call ModelFactories Init method ----- //

		Wsnb4ServUtils.initModelFactories(
				new WsrfbfModelFactoryImpl(), 
				new WsrfrModelFactoryImpl(), 
				new WsrfrlModelFactoryImpl(), 
				new WsrfrpModelFactoryImpl(), 
				new WstopModelFactoryImpl(), new WsnbModelFactoryImpl());
		// ----------------------------------- //
	}
	
	public TestBaseNotificationServicesTest() {}
	
	protected void initUnitTest(String testName) throws Exception {
		this.properties = new HashMap<String, Object>();
		this.isPassed = false;
		
		this.properties.put(TEST_NAME, testName);
		
		System.out.println("\n =============================================== \n\t " +
				"\n\t ---> Start unit test named : "+ testName +"...\n"+
								"\n\t ... Init BaseNotification services ...");
		
		InputStream supportedTopicsConfig = TestBaseNotificationServicesTest.class.getResourceAsStream("/models/SupportedTopicsSet.xml");		
		
		this.wsnbConsumerService = new NotificationConsumerService(Logger.getLogger("TestBaseNotificationServices "),
				this.properties,this.isDebug);
		
		// ---- TOPIC NAMESPACE and PREFIX for resource
		String nsPrefix = "wsn-prod";
		TopicNamespaceType topicNS = RefinedWstopFactory.getInstance().createTopicNamespaceType(new URI("http://com.ebmwebsourcing.com/wsn/producer-sample"));
		topicNS.setFinal(true);
		topicNS.setName("WsnProducerTopicNamespace");
		
		TopicNamespaceType.Topic topicForTopicSetChange = RefinedWstopFactory.getInstance().createTopicNamespaceTypeTopic("TopicSet");
		topicForTopicSetChange.setFinal(true);
		topicForTopicSetChange.addMessageType(WsrfrpConstants.RESOURCE_PROPERTY_VALUE_CHANGE_NOTIFICATION_QNAME);
		
		topicNS.addTopic(topicForTopicSetChange);
				
		// ---- init Notification producer service 
		this.wsnbProducerService = new NotificationProducerService(Logger.getLogger("TestBaseNotificationServices "),
					supportedTopicsConfig,false,topicNS,nsPrefix,MAIN_PERSISTENCE_FOLDER, this.wsnbConsumerService);	
						
		this.wsnbSubscriptionMgrService = this.wsnbProducerService.getSubscriptionManagerService();
		
		this.requestor = new RequestorActor(wsnbProducerService,this.wsnbSubscriptionMgrService);
		
	}

	protected void endUnitTest() throws Exception {
		String resultMessage = "\n\t ---> ...test "+ this.properties.get(TEST_NAME) +" result : ";

		resultMessage += ((this.isPassed)?"SUCCESS":"FAILED")
			+ " !! \n\n ========================================== \n\t";
		
	//	Assert.assertTrue("ERROR : temp folder for Persistence not deleted !",this.removeTmpTestFolder());
						
		System.out.println(resultMessage);
		Assert.assertTrue(this.isPassed);
	}

	private boolean removeTmpTestFolder(){
				
		// Remove persistence folder :
		File main = new File(MAIN_PERSISTENCE_FOLDER);		
		
		File[] subFolders = main.listFiles();

		for (File subFolderItem : subFolders) {			 
			if (subFolderItem != null){
				File[] Files = subFolderItem.listFiles();
				if (Files != null)
				for (File fileItem : Files) 
					fileItem.delete();
				subFolderItem.delete();
			}
		}
		return	  !main.exists() || main.delete();					
	}
	
	/**
	 * Sample of subscriber actor implementation
	 * that is not necessary the "NotificationConsumer"
	 * actor 
	 * 
	 * @author tdejean
	 */
	private class RequestorActor implements ITransporterForWsnbSubscriber,ITransporterForWsnbRequestor{
		
		private NotificationProducerService wnsbProducer = null;
		private SubscriptionManagerService wnsbSubscriptionManager = null;
		
		public RequestorActor(NotificationProducerService producer,SubscriptionManagerService subscriptionMgr) {
			this.wnsbProducer = producer;
			this.wnsbSubscriptionManager = subscriptionMgr;
		}
		
		@Override
		public SubscribeResponse sendSubscribeRequest(EndpointReferenceType
				 knownProducerEdp, Subscribe payload)
				throws AbsWSStarFault {

			SubscribeResponse response = null;
			
			try {
				response = this.wnsbProducer.subscribe(payload);
			} catch (WsnbException e) {
				e.printStackTrace();
			}			
			return response;
		}

		@Override
		public UnsubscribeResponse sendUnsubscribeRequest(EndpointReferenceType subscriptionMgrEdp, Unsubscribe payload) throws AbsWSStarFault {
			
			UnsubscribeResponse response= null;

			try {
				response = this.wnsbSubscriptionManager.unsubscribe(subscriptionMgrEdp, payload);
			} catch (WsnbException e) {
				e.printStackTrace();
			}
			return response;
		}

		@Override
		public RenewResponse sendRenewRequest(EndpointReferenceType subscriptionMgrEdp,Renew payload)
				throws AbsWSStarFault {
			RenewResponse response= null;
			try {
				response = this.wnsbSubscriptionManager.renew(subscriptionMgrEdp, payload);
			} catch (WsnbException e) {
				e.printStackTrace();
			}
			return response;
		}

		@Override
		public PauseSubscriptionResponse sendPauseSubscriptionRequest(EndpointReferenceType subscriptionMgrEdp,
				PauseSubscription payload) throws AbsWSStarFault {
			// Operation not yet supported : PausableSubscription Not yet implemented 
			return null;
		}

		@Override
		public ResumeSubscriptionResponse sendResumeSubscriptionRequest(EndpointReferenceType subscriptionMgrEdp,
				ResumeSubscription payload) throws AbsWSStarFault {
			// Operation not yet supported : PausableSubscription Not yet implemented 
			return null;
		}

		@Override
		public GetCurrentMessageResponse sendGetCurrentMessageRequest(EndpointReferenceType knownProducerEdp, GetCurrentMessage payload)
				throws AbsWSStarFault {
			GetCurrentMessageResponse response= null;
			try {
				response = this.wnsbProducer.getCurrentMessage(payload);
			} catch (WsnbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}
		
	}
	
	/**
	 *		Perform a "Subscribe()" request
	 *	
	 * @throws AbsWSStarFault
	 * @throws WsnbException
	 */
	private void performSubscribeRequest(String payloadXmlFile) throws AbsWSStarFault, WsnbException {
		
		Object response = null;			
		String testName = (String) properties.get(TEST_NAME);
		
		Document requestAsDoc = WsnbServiceUnitTestUtils.fromStreamToDocument(TestBaseNotificationServicesTest.class.getResourceAsStream(payloadXmlFile));
		Assert.assertNotNull("\nUnit test named : \""+ properties.get(TEST_NAME) +"\" failed !\n Cause : Error on \"Susbcribe\" xml file payload of access",requestAsDoc);
		
		Subscribe requestAsWsnType = Wsnb4ServUtils.getWsnbReader().readSubscribe(requestAsDoc);
		properties.put(SUBS_REQUEST_AS_OBJ, requestAsWsnType);
		
		try{
			//response = this.wsnbProducerService.subscribe(requestAsWsnType);
			response = this.requestor.sendSubscribeRequest(null, requestAsWsnType);
			properties.put(SUBS_RESPONSE_AS_OBJ,response);
				
		} catch (AbsWSStarFault wsnFault) {
			if ((Boolean)properties.get(WSNFAULT_EXPECTED))
				throw wsnFault;
			else
				Assert.assertTrue("\nUnit test named : \""+ testName +"\" failed !\n Cause : Unexpected WSNotificationFault Returned",false);
		} 
		
		Assert.assertNotNull("Unit test named : \""+ testName +"\" failed !\n Cause :  \"Subscribe\" request's response is null !", response);		
		Assert.assertTrue("Unit test named : \""+ testName +"\" failed !\n Cause :  \"Subscribe \" request's response has wrong type.\n" +
				"Expected type is \"SubscribeResponse\" Type !",(response instanceof SubscribeResponse));
		
	}
			
	/**
	 * 	Perform an "Unsubscribe()" request
	 * 
	 * @throws WsnbException
	 * @throws AbsWSStarFault
	 */
	private void performUnsubscribeRequest() throws WsnbException, AbsWSStarFault{
		Object response = null;			
		String testName = (String) properties.get(TEST_NAME);
	
		SubscribeResponse respSubs = (SubscribeResponse) this.properties.get(SUBS_RESPONSE_AS_OBJ);
		//String subscriptionUuid =  (respSubs.getSubscriptionReference()).getAddress().toString().split("@")[1];
		
		EndpointReferenceType subscriptionRef = respSubs.getSubscriptionReference();
		
		Unsubscribe requestUnsubscribe = RefinedWsnbFactory.getInstance().createUnsubscribe();
		Assert.assertNotNull(requestUnsubscribe);
					
		try {
			//response = this.wsnbSubscriptionMgrService.unsubscribe(subscriptionRef/*subscriptionUuid*/, requestUnsubscribe);
			response = this.requestor.sendUnsubscribeRequest(subscriptionRef, requestUnsubscribe);
			
		} catch (AbsWSStarFault wsnFault) {
			if ((Boolean)this.properties.get(WSNFAULT_EXPECTED))
				throw wsnFault;
			else
				Assert.assertTrue("\nUnit test named : \""+ testName +"\" failed !\n Cause : Unexpected WSNotificationFault Returned",false);
		}
		
		Assert.assertNotNull("Unit test named : \""+ testName +"\" failed !\n Cause :  \"Unsubscribe\" request's response is null !", response);		
		Assert.assertTrue("Unit test named : \""+ testName +"\" failed !\n Cause :  \"Unsubscribe\" request's response has wrong type.\n" +
				"Expected type is \"UnsubscribeResponse\" Type !",(response instanceof UnsubscribeResponse));

		/*return (UnsubscribeResponse) response;*/
	}
	
	/**
	 * 	Perform an "Notify()" request
	 * 
	 * @throws WsnbException 
	 * @throws AbsWSStarFault 
	 * 
	 */
	private void performNotifyRequest(String payloadXmlFile) throws WsnbException, AbsWSStarFault{
	
		Document request = WsnbServiceUnitTestUtils.fromStreamToDocument(TestBaseNotificationServicesTest.class.getResourceAsStream("/models/Notify.xml"));
		Assert.assertNotNull("\nUnit test named : \""+ properties.get(TEST_NAME) +"\" failed !\n Cause : Error on Subscribe.xml file access",request);
		//System.out.println(" *** xml file (request) imported :\n" + WsnUtils.prettyPrint(request) + "\n");
		Notify reqObj = Wsnb4ServUtils.getWsnbReader().readNotify(request);
		//this.properties.put(this.NOTIFY_TO_FORWARD,this.wsnbConsumerService.notify(reqObj));
		this.wsnbProducerService.notifyNewSituation(reqObj);
	}
	
	/**
	 * 		Perform a "GetCurrentMessage()" request
	 * 
	 * @throws WsnbException
	 * @throws AbsWSStarFault 
	 */
	private void performGetCurrentMessageRequest(String payloadXmlFile) throws WsnbException, AbsWSStarFault{
		Object response = null;			
		String testName = (String) properties.get(TEST_NAME);
	
		// ------ GetCurrentMessage request ----		
		Document request = WsnbServiceUnitTestUtils.fromStreamToDocument(TestBaseNotificationServicesTest.class.getResourceAsStream(payloadXmlFile));
		Assert.assertNotNull("\nUnit test named : \""+ testName +"\" failed !\n Cause : Error on GetCurrentMessage.xml file access",request);

		GetCurrentMessage getCurMsgObj = Wsnb4ServUtils.getWsnbReader().readGetCurrentMessage(request);
		
		try {
		
			response = this.requestor.sendGetCurrentMessageRequest(null, getCurMsgObj);
			this.properties.put(CURRENT_MESSAGE_RESP, response);
	
		} catch (AbsWSStarFault wsnFault) {
			if ((Boolean)this.properties.get(WSNFAULT_EXPECTED))
				throw wsnFault;
			else {
				Assert.assertTrue("\nUnit test named : \""+ testName +"\" failed !\n Cause : Unexpected WSNotificationFault Returned",false);
			}
		}
		
		Assert.assertNotNull("Unit test named : \""+ testName +"\" failed !\n Cause :  \"GetCurrentMessage\" request's response is null !", response);		
		Assert.assertTrue("Unit test named : \""+ testName +"\" failed !\n Cause :  \"GetCurrentMessage\" request's response has wrong type.\n" +
				"Expected type is \"GetCurrentMessageResponse\" Type !(received : " + response.getClass().getSimpleName() + ")",(response instanceof GetCurrentMessageResponse));
		
	}
	
	/**
	 * 		Perform a "Renew()" request
	 * @throws AbsWSStarFault
	 * @throws WsnbException
	 */
	private void performRenewRequest(String payloadXmlFile) throws AbsWSStarFault, WsnbException {
		
		Object response = null;			
		String testName = (String) properties.get(TEST_NAME);
		
		Document requestAsDoc = WsnbServiceUnitTestUtils.fromStreamToDocument(TestBaseNotificationServicesTest.class.getResourceAsStream(payloadXmlFile));
		Assert.assertNotNull("\nUnit test named : \""+ properties.get(TEST_NAME) +"\" failed !\n Cause : Error on Renew.xml file access",requestAsDoc);
		
		Renew requestAsWsnType = Wsnb4ServUtils.getWsnbReader().readRenew(requestAsDoc);
		properties.put(RENEW_REQUEST_AS_OBJ, requestAsWsnType);
		
		EndpointReferenceType susbcriptionRef = (((SubscribeResponse)this.properties.get(SUBS_RESPONSE_AS_OBJ)).getSubscriptionReference());
		
		try{
			response = this.requestor.sendRenewRequest(susbcriptionRef, requestAsWsnType);
			properties.put(RENEW_RESPONSE_AS_OBJ,response);
					
		} catch (AbsWSStarFault wsnFault) {
			if ((Boolean)properties.get(WSNFAULT_EXPECTED))
				throw wsnFault;
			else
				Assert.assertTrue("\nUnit test named : \""+ testName +"\" failed !\n Cause : Unexpected WSNotificationFault Returned",false);
		} 
		
		Assert.assertNotNull("Unit test named : \""+ testName +"\" failed !\n Cause :  \"Renew\" request's response is null !", response);		
		Assert.assertTrue("Unit test named : \""+ testName +"\" failed !\n Cause :  \"Renew \" request's response has wrong type.\n" +
				"Expected type is \"RenewResponse\" Type !",(response instanceof RenewResponse));			 
	}
		
	/**
	 * @throws AbsWSStarFault 
	 * 
	 * @throws WsnbException
	 * @throws  
	 */
	private boolean checkSubscriptionUuidStorage() throws WsnbException, AbsWSStarFault {
		boolean result = false;
		SubscribeResponse reqRespObj = (SubscribeResponse) this.properties.get(SUBS_RESPONSE_AS_OBJ);
		
		String subscriptionUuid = Wsnb4ServUtils.getSubscriptionIdFromReferenceParams((reqRespObj.getSubscriptionReference()).getReferenceParameters());//.getAddress().toString().split("@")[1];
		

		// -----------------------------------------------------------------------
		// -- check current state of supported Topic set of WstopTopicManager ----

		List<String> subscriptionIdsList = new ArrayList<String>();			
		
		//List<TopicExpressionType> topics = (((Subscribe)properties.get(SUBS_REQUEST_AS_OBJ)).getFilter()).getTopicExpressions(),
		//currentPartialTopicList = null;		
		
		
		// ----
		//for (TopicExpressionType topicItem : topics){
			
			// ---- transform to "Concrete" TopicExpression List 
			// ---- that represents exact topics		
			//currentPartialTopicList = this.wsnbProducerService.getWstopTopicsMgr().getTopicsAsConcreteTopExpr(topicItem);			
			//for (TopicExpressionType concreteTopicExprItem : currentPartialTopicList) {
		subscriptionIdsList.addAll(this.wsnbSubscriptionMgrService.getSubsMgrEngine().getStoredSubscriptionUuids());
			//}
			
			//			try {
//			subscriptionIdsList.addAll(
//					this.wsnbProducerService.getWstopTopicsMgr().getSubscriptionIdsFromTopicsSet(topicItem,WsnbConstants.SUBSCRIBE_QNAME));
			
			
			//			} catch (WSNotificationFault e) {
			//				// TODO Auto-generated catch block
			//				throw new WsnbException(e);
			//			}
		//}
		
		if ((Boolean)this.properties.get(MUST_BE_STORED))
			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +
					"SubscriptionUuid \"" + subscriptionUuid + "\" not stored in Supported topic set tree",
					subscriptionIdsList.contains(subscriptionUuid));
		else
			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +
					"SubscriptionUuid \"" + subscriptionUuid + "\" not removed from Supported topic set tree",
					!subscriptionIdsList.contains(subscriptionUuid));
			
		result = true;
		
//		System.out.println( "DEBUG - current State of uuid storage : \n " +  
//				this.wsnbrService.getTopicsMgr().displayCurrentSupportedTopicList() + "\n");
//		
		return result;
	}
			
//	/**
//	 * 
//	 * @return
//	 * @throws WsnbException
//	 */
//	private boolean checkSubscribeResponseUuidStorage() throws WsnbException{
//		boolean result = false;
//	
//		String registrationUuid = (((RegisterPublisherResponse)this.properties.get(REGPUB_RESPONSE_AS_OBJ)).
//				getPublisherRegistrationReference()).getAddress().toString().split("@")[1];
//		
//		List<String> subscribeResponseUuids = this.wsnbProducerService.getTopicsMgr().getSubcribeResponseOfRegistration(registrationUuid);
//		
//		if ((Boolean)this.properties.get(MUST_BE_STORED))
//			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +"RegistrationUuid \"" + registrationUuid + "\" stored but no \"Subscribe\" request have been sent" +
//					"to producers, unlike it was expected",!subscribeResponseUuids.isEmpty());
//		else
//			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +"RegistrationUuid \"" + registrationUuid + "\" stored but some \"Subscribe\" request have been sent" +
//					"to producers, unlike it was expected",subscribeResponseUuids.isEmpty());
//			
//		result = true;
//		
//		return result;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 * @throws WsnbException
//	 */
//	private boolean checkSubscriptionOnProducerUuidToRemove() throws WsnbException{
//		boolean result = false;
//
////		System.out.println(" *** Supported topics set (from WstopTopicManager) :\n" + 
////				new org.jdom.output.XMLOutputter(org.jdom.output.Format.getPrettyFormat()).outputString(this.wsnbrService.getWstopTopicsMgr().getSupportedTopics()));			
//
//				
//		List<String> subscribeRespUuid = new ArrayList<String>();			
//		
//		Subscribe request = (Subscribe) this.properties.get(SUBS_REQUEST_AS_OBJ);
//		for (TopicExpressionType topicItem : (request.getFilter()).getTopicExpressions()){
//			
//			List<String> registrationsList = this.wsnbProducerService.getTopicsMgr().getRegistrationIdsFromTopicsSet(topicItem);
//			
//			for (String regItem : registrationsList) {			
//				subscribeRespUuid.addAll(this.wsnbProducerService.getTopicsMgr().getSubcribeResponseOfRegistration(regItem));
//			}
//		}
//				
//		String subscriptionUuid = (((SubscribeResponse) this.properties.get(SUBS_RESPONSE_AS_OBJ)).
//				getSubscriptionReference()).getAddress().toString().split("@")[1];
//				
//		if ((Boolean)this.properties.get(MUST_BE_STORED))
//				Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +
//				"SubscriptionUuid \"" + subscriptionUuid + "\" removed from Supported topic set tree but some unexpected " +
//						"\"Unsubscribe\" requests have been sent to producer",
//				!subscribeRespUuid.isEmpty());
//		else 
//			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +
//					"SubscriptionUuid \"" + subscriptionUuid + "\" removed from Supported topic set tree but no expected " +
//							"\"Unsubscribe\" requests have been sent to producer",
//					subscribeRespUuid.isEmpty());
//		result = true;
//			
//		return result;
//	}
	
	/**
	 * 
	 * @return
	 */
	private boolean checkReceivedNotification(boolean isRPValChangeNotifExpected, boolean isTerminationNotifExpected){
		boolean result = false;
		
		
			// --- check "RECEIVED_RP_VALUE_CHANGE_NOTIFICATION" notification reception
			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +
					"\"ResourcePropertyValueChangeNotification\" Notify requests not received (then stored) by the consumer ",
					(!isRPValChangeNotifExpected || (this.properties.get(NotificationConsumerService.RECEIVED_RP_VALUE_CHANGE_NOTIFICATION)) != null));

			// --- check "RECEIVED_TERMINATION_NOTIFICATION" notification reception
			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +
					"\"TerminationNotification\" Notify requests expected but not recived by the NotificationConsumer ",
					(!isTerminationNotifExpected || (this.properties.get(NotificationConsumerService.RECEIVED_TERMINATION_NOTIFICATION)) != null)); 
			
			if ((Boolean)this.properties.get(MUST_BE_STORED)){
				// --- check "Business logic" notification reception
				Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  " +
						"Notify requests not received (then stored) by the consumer ",
						this.properties.get(NotificationConsumerService.RECEIVED_BUSINESS_LOGIC_NOTIFICATION) != null);
			}
				
		result = true;
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean checkGetCurrentMessageStorage(){
		boolean result = false;
		
		if ((Boolean)this.properties.get(MUST_BE_STORED))
			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  "
					+ "No Notification Message stored on given topic !",
					((GetCurrentMessageResponse)this.properties.get(CURRENT_MESSAGE_RESP)).getNotifications().size() > 0);
				
		result = true;
		return result;
	
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean checkRenew(){
		boolean result = false;
		
		SubscribeResponse subsResp = ((SubscribeResponse)this.properties.get(SUBS_RESPONSE_AS_OBJ));
		RenewResponse renewResp = ((RenewResponse)this.properties.get(RENEW_RESPONSE_AS_OBJ));
		
		if ((Boolean)this.properties.get(MUST_BE_STORED))
			Assert.assertTrue("\nUnit test named : \""+ this.properties.get(TEST_NAME) +"\" failed !\n Cause :  "
					+ "No modification on Subscription TerminationTimeStamp !",
				(subsResp.getTerminationTime().before(renewResp.getTerminationTime())));
		
		result = true;
		return result;
	
	}
		
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ========== Unit Tests =============
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~				
	
	@Test
	public void testSubscribeRequest() throws Exception {
		
		// ----------------------
		// --- init part -----
		String testName = "Simple \"Subscribe\" request";		
		this.initUnitTest(testName);		
		
		// -----------------------------
		// ----- perform test part ------
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));		
		this.performSubscribeRequest("/models/Subscribe.xml");
		
		// ------------------------------
		// ----- check test result ------
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed = this.checkSubscriptionUuidStorage();

		this.endUnitTest();
		
	}
	
	@Test
	public void testSubscribeRequestOnBadTopic() throws Exception {

		// ----------------------
		// --- init part -----
		String testName = "\"Subscribe\" request on Unknown topic";		
		this.initUnitTest(testName);		
		
		// -----------------------------
		// ----- perform test part ------
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(true));		
		try {
			this.performSubscribeRequest("/models/SubscribeOnBadTopic.xml");
		}catch (AbsWSStarFault wsnFault) {		
			this.isPassed = (wsnFault instanceof TopicNotSupportedFault);
			// ------------------------------
			// ----- check test result ------
			Assert.assertTrue("\nUnit test named : \""+ testName +
					"\" failed !\n Cause :  Bad WSNotificationFault type received. Expected \"TopicNotSupportedFault\" type", 
					this.isPassed);
			
			if (this.isDebug){
				Document faultAsDoc = wsnFault.getDocumentFragment();
				System.out.println("[DEBUG ] wsnFaultAsDocument: \n\n" +Wsnb4ServUtils.prettyPrint(faultAsDoc) +"\n\n");
			}
			//WsnWriter.getInstance().writeTopicNotSupportedFaultType((TopicNotSupportedFault)); 
			
		} finally {	
			
//			// ------------------------------
//		// ----- check test result ------
//		this.properties.put(MUST_BE_STORED, new Boolean(false));
//		this.isPassed = this.checkSubscriptionUuidStorage();

			this.endUnitTest();		
		}
				
	}
	
//	@Test
//	public void testSubscribeRequestWithExistingRegistration() throws Exception {
//
//		// ----------------------
//		// --- init part -----
//		String testName = "\"Subscribe\" request on topic with existing registration";		
//		this.initUnitTest(testName);
//			
//		// ------------------------------
//		// ----- perform test part ------
//		
//		// ~~~~~~~ First Register a publisher ~~~~~~~		
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));			
//		this.performRegisterPublisherRequest("/models/RegisterPublisher.xml");
//				
//		// ~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 		
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed = this.checkRegistrationUuidStorage();  				
//		
//		//~~~~~~~~~~ Then Subscribe a consumer ~~~~~~~~~~ 
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));		
//		this.performSubscribeRequest("/models/Subscribe.xml");
//		// ------------------------------
//		// ----- check test result ------
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed = this.checkSubscriptionUuidStorage();
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed &= this.checkSubscribeResponseUuidStorage();
//
//		this.endUnitTest();
//		
//	}

	@Test
	public void testUnsubscribeToExistingSubscriptionRequest() throws Exception {
		
		// ----------------------
		// --- init part -----
		String testName = "\"Unsubscribe\" request to existing subscription";
		this.initUnitTest(testName);
		
		// -----------------------------
		// ----- perform test part ------
		
		//~~~~~~~~~~ First subscribe a consumer ~~~~~~~~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));		
		this.performSubscribeRequest("/models/Subscribe.xml");
		
		// ~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed = this.checkSubscriptionUuidStorage();

		// ~~~~~~~~ Then unsubscribe ~~~~~~~~~~~~~~
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));		
		this.performUnsubscribeRequest();
		
		// ------------------------------
		// ----- check test result ------
		
		this.properties.put(MUST_BE_STORED, new Boolean(false));
		this.isPassed &= this.checkSubscriptionUuidStorage();
		
		this.endUnitTest();
		
	}
	
//	@Test
//	public void testUnsubscribeRequestAndForward() throws Exception {
//	
//		// ----------------------
//		// --- init part -----		
//		String testName = "\"Unsubscribe\" request to existing Subscription and remove producer subscription response";
//		this.initUnitTest(testName);
//		
//		// -----------------------------
//		// ----- perform test part ------
//		
//		//~~~~~~~~~~First subscribe consumer ~~~~~~~~~~ 
//		
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
//		this.performSubscribeRequest("/models/Subscribe.xml");
//	
//		// ~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed = this.checkSubscriptionUuidStorage();
//	
//		//~~~~~~~~~~ Then register a producer ~~~~~~~~~~ 		
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));									
//		this.performRegisterPublisherRequest("/models/RegisterPublisher.xml");
//		
//		// ~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed &= this.checkRegistrationUuidStorage();  
//					
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed &= this.checkSubscribeResponseUuidStorage();
//		
//		//~~~~~~~~~~And finally Unsubscribe  ~~~~~~~~~~ 
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
//		this.performUnsubscribeRequest();
//		
//		// ------------------------------
//		// ----- check test result ------
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(false));
//		this.isPassed &= this.checkSubscriptionUuidStorage();
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(false));
//		this.isPassed &= this.checkSubscriptionOnProducerUuidToRemove();
//		
//		this.endUnitTest();
//
//	}

//	@Test
//	public void testUnsubscribeRequestAndDontForward() throws Exception {
//
//
//		// ----------------------
//		// --- init part -----		
//		String testName = "\"Unsubscribe\" request to existing Subscription and keep producer subscription response";
//		this.initUnitTest(testName);
//		
//		// -----------------------------
//		// ----- perform test part ------
//		
//		//~~~~~~~~~~First subscribe consumer ~~~~~~~~~~ 
//		
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
//		this.performSubscribeRequest("/models/Subscribe.xml");
//	
//		// ~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed = this.checkSubscriptionUuidStorage();
//	
//		//~~~~~~~~~~ Then subscribe a second consumer ~~~~~~~~~~ 
//		
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
//		this.performSubscribeRequest("/models/Subscribe.xml");
//	
//		// ~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed = this.checkSubscriptionUuidStorage();
//		
//		//~~~~~~~~~~ Then register a producer ~~~~~~~~~~ 		
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));									
//		this.performRegisterPublisherRequest("/models/RegisterPublisher.xml");
//		
//		// ~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed &= this.checkRegistrationUuidStorage();  
//					
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed &= this.checkSubscribeResponseUuidStorage();
//		
//		//~~~~~~~~~~And finally Unsubscribe  ~~~~~~~~~~ 
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
//		this.performUnsubscribeRequest();
//		
//		// ------------------------------
//		// ----- check test result ------
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(false));
//		this.isPassed &= this.checkSubscriptionUuidStorage();
//		
//		this.properties.put(MUST_BE_STORED, new Boolean(true));
//		this.isPassed &= this.checkSubscriptionOnProducerUuidToRemove();
//		
//		this.endUnitTest();
//	
//	}
	
	@Test
	public void testUnsubscribeToUnknownSubscriptionRequest() throws Exception {
	
		// -------------------
		// --- init part -----
		String testName = "\"Unsubscribe \" request on unknown subscription";		
		this.initUnitTest(testName);
	
		// -----------------------------
		// ----- perform test part ------		
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(true));
		try {
			
			// --- create a random registration reference ---
			URI address = new URI(this.wsnbProducerService.getSUBSCRIPTION_MANAGER_SERVICE_QNAME().getNamespaceURI() +
					":"+ this.wsnbProducerService.getSUBSCRIPTION_MANAGER_SERVICE_QNAME().getLocalPart()+"@" +
					this.wsnbProducerService.getSUBSCRIPTION_MANAGER_SERVICE_EDP());

			EndpointReferenceType subscriptionRef = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReference.class);
			Address addr = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
			addr.setValue(address);
			subscriptionRef.setAddress(addr);
			
			Element subscriptionUuidAsElt = Wsnb4ServUtils.createSubscriptionIdAsReferenceParamElt(UUID.randomUUID().toString());			
			
			ReferenceParameters refParams = WSNUtil.getInstance().getXmlObjectFactory().create(ReferenceParameters.class);
			refParams.addAny(subscriptionUuidAsElt);
			
			subscriptionRef.setReferenceParameters(refParams);
			
			SubscribeResponse response = RefinedWsnbFactory.getInstance().createSubscribeResponse(subscriptionRef);
						
			this.properties.put(SUBS_RESPONSE_AS_OBJ, response);
			
			this.performUnsubscribeRequest();
			
		}catch (AbsWSStarFault wsnFault) {		
			this.isPassed = (wsnFault instanceof ResourceUnknownFault);
			// ------------------------------
			// ----- check test result ------
			Assert.assertTrue("\nUnit test named : \""+ testName +
					"\" failed !\n Cause :  Bad WSNotificationFault type received. Expected \"ResourceUnknownFault\" type", 
					this.isPassed);
			
		} finally {			
			this.endUnitTest();		
		}
		
	}
	
	@Test	
	public void testNotifyRequest() throws Exception{		

		// -------------------
		// --- init part -----
		String testName = "\"Notify\" request with existing consumer subscription";
		this.initUnitTest(testName);
	
		// -----------------------------
		// ----- perform test part ------		
		
		//~~~~~~~~~~First subscribe consumer ~~~~~~~~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performSubscribeRequest("/models/Subscribe.xml");
		
		//~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
		
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed = this.checkSubscriptionUuidStorage();
		
		//~~~~~~~~~~Then notify   ~~~~~~~~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performNotifyRequest("/models/Notify.xml");
				
		// ------------------------------
		// ----- check test result ------
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed &=this.checkReceivedNotification(false,false);
		
		this.endUnitTest();
	}
	
//	@Test
//	public void testNotifyOnEmptySubscriptionSetTopicRequest() throws Exception{		
//		
//		// -------------------
//		// --- init part -----
//		String testName = "\"Notify\" request without consumer subscription";
//		this.initUnitTest(testName);
//	
//		// -----------------------------
//		// ----- perform test part ------		
//		
//		//~~~~~~~~~~ Notify   ~~~~~~~~~~ 
//		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
//		this.performNotifyRequest("/models/Notify.xml");
//				
//		// ------------------------------
//		// ----- check test result ------
//		this.properties.put(MUST_BE_STORED, new Boolean(false));
//		this.isPassed = this.checkNotifyToForward();
//		
//		this.endUnitTest();
//		}
	
	@Test
	public void testGetCurrentMessageRequest() throws Exception {		

		// -------------------
		// --- init part -----
		String testName = "\"GetCurrentMessage \" request";
		this.initUnitTest(testName);
	
		// -----------------------------
		// ----- perform test part ------	
		
		//~~~~~~~~~~ First Notify   ~~~~~~~~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performNotifyRequest("/models/Notify.xml");	
		
		// ~~~~~~~ Then GetCurrentMessage ~~~~~~
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performGetCurrentMessageRequest("/models/GetCurrentMessage.xml");
		
		// ------------------------------
		// ----- check test result ------
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed = this.checkGetCurrentMessageStorage();

		this.endUnitTest();
	}
	
	@Test
	public void testRenewRequest() throws Exception {
		
		// -------------------
		// --- init part -----
		String testName = "\"Renew\" request";
		this.initUnitTest(testName);
		
		// -----------------------------
		// ----- perform test part ------	
		
		//~~~~~~~~~~First subscribe consumer ~~~~~~~~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performSubscribeRequest("/models/Subscribe.xml");
		
		//~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 
		
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed = this.checkSubscriptionUuidStorage();
			
		//~~~~~~~~~~ Then Renew ~~~~~~~~~~
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performRenewRequest("/models/Renew.xml");
		
		// ------------------------------
		// ----- check test result ------
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed &=this.checkRenew();
		
		this.endUnitTest();
		
	}
		
	@Test
	public void testExtensionOfSupportedTopic() throws Exception {
		
		// ----------------------
		// --- init part -----
		String testName = "Addition of new supported topics during runtine";		
		this.initUnitTest(testName);
		
		// -----------------------------
		// ----- perform test part ------
		
		// ~~~ topic not added yet ~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(true));
		try {
			this.performSubscribeRequest("/models/SubscribeOnTopicNotYetSupported.xml");
		} catch (AbsWSStarFault wsnFault) {		
			this.isPassed = (wsnFault instanceof TopicNotSupportedFault);
			// ------------------------------
			// ----- check test result ------
			Assert.assertTrue("\nUnit test named : \""+ testName +
					"\" failed !\n Cause :  Bad WSNotificationFault type received. Expected \"TopicNotSupportedFault\" type", 
					this.isPassed);
			if (isDebug){
				Document faultAsDoc = wsnFault.getDocumentFragment();
				System.out.println("[DEBUG ] wsnFaultAsDocument: \n\n" +Wsnb4ServUtils.prettyPrint(faultAsDoc) +"\n\n");
			}
		}			

		// ~~~ Update TopicSet property ~~~ 
		InputStream newTopicSet = TestBaseNotificationServicesTest.class.getResourceAsStream("/models/UpdateToExtendTopicsSet.xml");
		TopicSetType newTopicSetRP = Wsnb4ServUtils.getWstopReader().readTopicSetType(new InputSource(newTopicSet)); 
		Document topicSetAsDOM = RefinedWstopFactory.getInstance().getWstopWriter().writeTopicSetTypeAsDOM(newTopicSetRP);
		
		List<Element>  properties= new ArrayList<Element>();
		properties.add(topicSetAsDOM.getDocumentElement());

		UpdateType content = RefinedWsrfrpFactory.getInstance().createUpdateType(properties);
		UpdateResourceProperties request = RefinedWsrfrpFactory.getInstance().createUpdateResourceProperties(content);
				
		UpdateResourcePropertiesResponse response = this.wsnbProducerService.updateResourceProperties(request);
		
		if (response != null) {		
			Document doc = RefinedWsrfrpFactory.getInstance().getWsrfrpWriter().writeUpdateResourcePropertiesResponseAsDOM(response);
			System.out.println("doc = \n" + XMLPrettyPrinter.prettyPrint(doc));
			
			GetResourcePropertyResponse resp = this.wsnbProducerService.getResourceProperty(WstopConstants.TOPIC_SET_QNAME);
			Document doc2 = RefinedWsrfrpFactory.getInstance().getWsrfrpWriter().writeGetResourcePropertyResponseAsDOM(resp);
			
			System.out.println("doc2 = \n" + XMLPrettyPrinter.prettyPrint(doc2));
			
			System.out.println("\n\t ---> [Intermediate result] \"TopicSet\" resourceProperty updated !");
		}
		
		// ~~~ then try to subscribe again ~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performSubscribeRequest("/models/SubscribeOnTopicNotYetSupported.xml");
		
		// ------------------------------
		// ----- check test result ------
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed &= this.checkSubscriptionUuidStorage();

		this.endUnitTest();

	}
	
	@Test
	public void testReductionOfSupportedTopicWithoutTerminationNotif() throws Exception {
		String testName = "Suppression of some existing supported topics whitout subscription destruction during runtine";	
		this.reductionOfSupportedTopicWithNotif(testName,"/models/Subscribe.xml",false);
	}
	
	@Test
	public void testReductionOfSupportedTopicWithTerminationNotif() throws Exception {
		String testName = "Suppression of some existing supported topics with subscription destruction during runtine";	
		this.reductionOfSupportedTopicWithNotif(testName,"/models/SubscribeOnTopicToBeRemoved.xml",true);
	}
	
	private void reductionOfSupportedTopicWithNotif(String testName, String subscribeRequestPath,
			boolean isTerminationNotificationExpected) throws Exception {
		
		// ----------------------
		// --- init part -----			
		this.initUnitTest(testName);
		
		// -----------------------------
		// ----- perform test part ------
		
		//~~~~~~~~~~First subscribe consumer ~~~~~~~~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(false));
		this.performSubscribeRequest(subscribeRequestPath);
		
		//~~~~~~~~~~~~ check Unit Test intermediary step ~~~~~~~~~~~~~~ 		
		this.properties.put(MUST_BE_STORED, new Boolean(true));
		this.isPassed = this.checkSubscriptionUuidStorage();
				
		// ~~~ Update TopicSet property : remove a topic which a consumer is subscribed on ~~~ 
		// ~~~ As a consequence, related subscription resource must "immediately terminate" ~~~
		// ~~~ TODO : Termination must be "notify ... work in progress
		InputStream newTopicSet = TestBaseNotificationServicesTest.class.getResourceAsStream("/models/UpdateToReduceTopicsSet.xml");
		TopicSetType newTopicSetRP = Wsnb4ServUtils.getWstopReader().readTopicSetType(new InputSource(newTopicSet)); 
		Document topicSetAsDOM = RefinedWstopFactory.getInstance().getWstopWriter().writeTopicSetTypeAsDOM(newTopicSetRP);
		
		List<Element>  properties= new ArrayList<Element>();
		properties.add(topicSetAsDOM.getDocumentElement());

		UpdateType content = RefinedWsrfrpFactory.getInstance().createUpdateType(properties);
		UpdateResourceProperties request = RefinedWsrfrpFactory.getInstance().createUpdateResourceProperties(content);
				
		UpdateResourcePropertiesResponse response = this.wsnbProducerService.updateResourceProperties(request);
		
		if (response != null) {
			System.out.println("\n\t ---> [Intermediate result] \"TopicSet\" resourceProperty updated !");
		}
		
		// --- check if resource's "TerminationNotification" notification have been sent		
		this.properties.put(MUST_BE_STORED, new Boolean(false));
		this.isPassed &= this.checkReceivedNotification(true,isTerminationNotificationExpected);
		
		// ~~~ then try to subscribe again ~~~ 
		this.properties.put(WSNFAULT_EXPECTED, new Boolean(true));
		try {
			this.performSubscribeRequest("/models/SubscribeOnTopicToBeRemoved.xml");
				
		} catch (AbsWSStarFault wsnFault) {		
			this.isPassed &= (wsnFault instanceof TopicNotSupportedFault);
			// ------------------------------
			// ----- check test result ------
			Assert.assertTrue("\nUnit test named : \""+ testName +
					"\" failed !\n Cause :  Bad WSNotificationFault type received. Expected \"TopicNotSupportedFault\" type", 
					this.isPassed);
			if (isDebug){
				Document faultAsDoc = wsnFault.getDocumentFragment();
				System.out.println("[DEBUG ] wsnFaultAsDocument: \n\n" + Wsnb4ServUtils.prettyPrint(faultAsDoc) +"\n\n");
			}
		}			
	
		// ------------------------------
		// ----- check test result ------
		this.properties.put(MUST_BE_STORED, new Boolean(!isTerminationNotificationExpected));
		this.isPassed &= this.checkSubscriptionUuidStorage();

		this.endUnitTest();

	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	

}
