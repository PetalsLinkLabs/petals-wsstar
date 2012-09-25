/**
 * Copyright (c) 2010 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * $id.java
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MessageContentExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType.Message;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ProducerPropertiesExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Renew;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.RenewResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionManagerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Unsubscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UseRaw;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbReader;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbWriter;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.implementor.WsnbModelFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.test.util.AbsWsaTypesUnitTests;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.test.util.WsaUnitTestsUtils;

/**
 * @author Thierry DÉJEAN - eBM WebSourcing
 */
public abstract class AbsWsnbTypesAndPayloadsUnitTests extends TestCase {

	protected boolean isDebug = false;
	
	private WsnbFactory factory;
	protected WsnbModelFactory modelFactoryImpl;
	private WsnbReader reader;
	private WsnbWriter writer;
	
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		this.initRefinedWsnbFactory();		
		this.initRequiredDependencyModelFactories();
	}
	
	/**
	 * provide an instance of your "WsnbModelFactory" model implementation class.
	 * The body must content something like :
	 * 
	 * 				"this.modelFactoryImpl = new WsnbModelFactoryImpl();"
	 * 
	 * where "WsnbModelFactoryImpl" is a class which implements "WsnbModelFactory".
	 * 
	 */		
	protected abstract void setWsnbModelFactory();
	
	/**
	 * init other model factories (mainly : WS-Addressing,WS-BaseFault,WS-Resource)
	 */
	protected abstract void initRequiredDependencyModelFactories();
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  Technical Methods (Init, ....) ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Initialization of factory and reader/writer attributes
	 */
	public void initRefinedWsnbFactory(){	
		if (this.modelFactoryImpl == null)
			this.setWsnbModelFactory();			
		
		assertNotNull(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"the \"WsnbModelFactory\" implementation Object has not been set.\n" +
				"Please provide an instance of \"WsnbModelFactory\" implementation class", modelFactoryImpl);
		
		if (this.factory == null)		
			this.factory = RefinedWsnbFactory.getInstance(this.modelFactoryImpl);			
		if (this.reader == null)
			this.reader = this.factory.getWsnbReader();
		if (this.writer == null)
			this.writer = this.factory.getWsnbWriter();	
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Common default objects creation" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //	
	/**
	 * create a default {@link FilterType} object
	 */
	protected FilterType createDefaultFilter() throws WsnbException {
		
		// ~~~~ create an default FilterType and write it to DOM document ~~~~ 
		FilterType filter = this.factory.createFilterType();
			
		// ~~~~ create and add a TopicExpression to the filter ~~~~
		TopicExpressionType topicExpression = 
			this.factory.createTopicExpressionType(WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI);
		topicExpression.setContent("tns1:rootTopic/*/grandChildTopic1");
		try {
			topicExpression.addTopicNamespace("tns1", new URI("http://default/namespace/for/topic"));
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		}
		filter.addTopicExpression(topicExpression);
		
		// ~~~~ create and add a MessageContentExpression to the filter ~~~~
		MessageContentExpression msgContentExpression = 
			this.factory.createMessageContentExpression(WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI);			
		msgContentExpression.setContent("/data/temperature[external>35]");
		filter.addMessageContentExpression(msgContentExpression);
				
		// ~~~~ create and add a ProducerPropertiesExpression to the filter ~~~~
		ProducerPropertiesExpression prodPropertiesExpression = 
			this.factory.createProducerPropertiesExpression(WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI);
		prodPropertiesExpression.setContent("*//identity[name=\"External Sensor\"]");		
		filter.addProducerPropertiesExpression(prodPropertiesExpression);
		
		return filter;
	}
	
	/**
	 * create a default DOM document to build Element obj (useful for Policy, 
	 * NotificationMessageHolder.Message , ....) 
	 * @param docUri
	 * @return
	 * @throws WsnbException 
	 */
	protected Document createDefaultDocument(String docUri) throws WsnbException{
		Document defaultDoc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			defaultDoc = factory.newDocumentBuilder().newDocument();
			//defaultDoc.setDocumentURI(docUri);
		} catch (ParserConfigurationException e) {
			throw new WsnbException(e);
		}
		return defaultDoc;
	}

	/**
	 * create a default subscription ({@link SubscriptionManagerRP}) object
	 * @return
	 * @throws WsnbException
	 */
	protected SubscriptionManagerRP createDefaultSubscriptionManagerRP() throws WsnbException {
		
		// ~~~~ create a default ConsumerReference  ~~~~~
		EndpointReferenceType consumerRef = null;
		try {
			consumerRef = WsnbUnitTestsUtils.createDefaultWsnConsumerOriginator();
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		}
		SubscriptionManagerRP subscription = this.factory.createSubscriptionManagerRP(consumerRef);

		// ~~~~ set filter ~~~~~
		subscription.setFilter(this.createDefaultFilter());

		// ~~~~ set creationTime ~~~~~		
		subscription.setCreationTime(new Date());

		// ~~~~ Set SubscriptionPolicy ~~~~~
		SubscriptionPolicyType policy = this.factory.createSubscriptionPolicyType();

		Document defaultDoc = this.createDefaultDocument("http://defauturi/for/policy");
		Element policyAsElt = defaultDoc.createElement("CustomPolicy"),child1;;
		child1 = defaultDoc.createElement("NotifFrequency");
		child1.setNodeValue("1 per minute max");
		policyAsElt.appendChild(child1);
		policy.addPolicy(policyAsElt);

		subscription.setSubscriptionPolicy(policy);		
		
		return subscription;
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Check a given (provided by the "WsnbReader") {@link TopicExpressionType} object
	 * 	 against a reference one
	 */
	public static boolean checkTopicExpressionType(TopicExpressionType expectedTopicExpression,
			TopicExpressionType toCheckTopicExpression, boolean isDebug){		
	
		// ~~~~~~~ check TopicExpressionType dialect equality ~~~~~~~~ 
		URI expectedDialect =  expectedTopicExpression.getDialect(),
		toCheckDialect = toCheckTopicExpression.getDialect();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckDialect value : " +  ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
					"\n[DEBUG] --> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"TopicExpression dialects are \n" +
				"\t(-> toCheckDialect value : " + ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
				"\n\t-> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null") + ")", 
				(toCheckDialect == expectedDialect) ||(toCheckDialect != null && toCheckDialect.equals(expectedDialect)));

		// ~~~~~~~ check TopicExpressionType content equality ~~~~~~~~ 
		String expectedContent =  expectedTopicExpression.getContent(),
		toCheckContent = toCheckTopicExpression.getContent();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckContent value : " +  toCheckContent +
					"\n[DEBUG] --> expectedContent value : " + expectedContent + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"TopicExpression contents are \n" +
				"\t(-> toCheckContent value : " + toCheckContent +
				"\n\t-> expectedContent value : " + expectedContent + ")", 
				(toCheckContent == expectedContent) ||(toCheckContent != null && toCheckContent.equals(expectedContent)));
		
		// ~~~~~~~ Check TopicNamespaces Lists ~~~~~~~
		List<QName> expectedTopicNamespaces = expectedTopicExpression.getTopicNamespaces(),
		toCheckTopicNamespaces = toCheckTopicExpression.getTopicNamespaces();
				
		int expectedTopicNamespacesCount = (expectedTopicNamespaces != null)? expectedTopicNamespaces.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"TopicExpression have different \"TopicNamespace\" values\n" +
				"\t(-> toCheckTopicExpression topicNamespace count : " + ((toCheckTopicNamespaces != null)?toCheckTopicNamespaces.size():"none") +
				"\n\t-> expectedTopicExpression topicNamespace count : " + ((expectedTopicNamespacesCount==-1)?"none": expectedTopicNamespacesCount) + ")",
				( toCheckTopicNamespaces== null && expectedTopicNamespaces == null) ||
				toCheckTopicNamespaces.size() == expectedTopicNamespacesCount);
		
		QName expectedTopicNamespace,toCheckTopicNamespace;
		
		for (int i = 0; i < expectedTopicNamespacesCount; i++) {
			
			toCheckTopicNamespace = toCheckTopicNamespaces.get(i);
			expectedTopicNamespace = expectedTopicNamespaces.get(i);
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckTopicExpression TopicNamespace #" + i +
						" : \"" + toCheckTopicNamespace + 
						"\n[DEBUG] --> expectedTopicExpression TopicNamespace #" + i +
						" : \"" + expectedTopicNamespace + "\"\n");			
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"faults  UnknownFilter values are different \n" +
					"\t(-> toCheckTopicExpression TopicNamespace #" + i + " : " + toCheckTopicNamespace +
					"\n\t-> expectedTopicExpression TopicNamespace #" + i + " : " + expectedTopicNamespace + ")",
					toCheckTopicNamespace.equals(expectedTopicNamespace));	
		}	
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link MessageContentExpression} object
	 * 	 against a reference one
	 */
	protected static boolean checkMessageContentExpression(MessageContentExpression expectedMessageContent,
			MessageContentExpression toCheckMessageContent, boolean isDebug){		
				
		// ~~~~~~~ check QueryExpressionType dialect equality ~~~~~~~~ 
		URI expectedDialect =  expectedMessageContent.getDialect(),
		toCheckDialect = toCheckMessageContent.getDialect();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckDialect value : " +  ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
					"\n[DEBUG] --> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"MessageContentExpression dialects are \n" +
				"\t(-> toCheckDialect value : " + ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
				"\n\t-> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null")+ ")" , 
				(toCheckDialect == expectedDialect) ||(toCheckDialect != null && toCheckDialect.equals(expectedDialect)));

		// ~~~~~~~ check MessageContentExpressionType content equality ~~~~~~~~ 
		String expectedContent =  expectedMessageContent.getContent(),
		toCheckContent = toCheckMessageContent.getContent();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckContent value : " +  toCheckContent +
					"\n[DEBUG] --> expectedContent value : " + expectedContent + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"MessageContentExpression contents are \n" +
				"\t(-> toCheckContent value : " + toCheckContent +
				"\n\t-> expectedContent value : " + expectedContent+ ")", 
				(toCheckContent == expectedContent) ||(toCheckContent != null && toCheckContent.equals(expectedContent)));
		
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link ProducerPropertiesExpression} object
	 * 	 against a reference one
	 */
	protected static boolean checkProducerPropertiesExpression(ProducerPropertiesExpression expectedProducerProperties,
			ProducerPropertiesExpression toCheckProducerProperties, boolean isDebug){		
				
		// ~~~~~~~ check ProducerPropertiesExpressionType dialect equality ~~~~~~~~ 
		URI expectedDialect =  expectedProducerProperties.getDialect(),
		toCheckDialect = toCheckProducerProperties.getDialect();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckDialect value : " +  ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
					"\n[DEBUG] --> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"ProducerPropertiesExpression dialects are \n" +
				"\t(-> toCheckDialect value : " + ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
				"\n\t-> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null")+ ")" , 
				(toCheckDialect == expectedDialect) ||(toCheckDialect != null && toCheckDialect.equals(expectedDialect)));

		// ~~~~~~~ check ProducerPropertiesExpressionType content equality ~~~~~~~~ 
		String expectedContent =  expectedProducerProperties.getContent(),
		toCheckContent = toCheckProducerProperties.getContent();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckContent value : " +  toCheckContent +
					"\n[DEBUG] --> expectedContent value : " + expectedContent + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"ProducerPropertiesExpression contents are \n" +
				"\t(-> toCheckContent value : " + toCheckContent +
				"\n\t-> expectedContent value : " + expectedContent+ ")", 
				(toCheckContent == expectedContent) ||(toCheckContent != null && toCheckContent.equals(expectedContent)));
		
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link FilterType} object against a reference one
	 */
	protected static boolean checkFilterType(FilterType expectedFilter,
			FilterType toCheckFilter, boolean isDebug){		
				
		// ~~~~~~~ check FilterType TopicExpressions equality ~~~~~~~~ 
		List<TopicExpressionType> expectedTopicExpressions =  expectedFilter.getTopicExpressions(),
		toCheckTopicExpressions = toCheckFilter.getTopicExpressions();	
		
		int expectedTopicExpressionsCount = (expectedTopicExpressions != null)? expectedTopicExpressions.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Filters have different TopicExpression list \n" +
				"\t(-> toCheckFilter TopicExpressions count : " + ((toCheckTopicExpressions != null)?toCheckTopicExpressions.size():"none") +
				"\n\t-> expectedFilter TopicExpressions count : " + ((expectedTopicExpressionsCount==-1)?"none": expectedTopicExpressionsCount) + ")",
				(expectedTopicExpressions == null && toCheckTopicExpressions == null) ||
				toCheckTopicExpressions.size() == expectedTopicExpressionsCount);
		
		TopicExpressionType expectedTopicExpression,toCheckTopicExpression;
		
		for (int i = 0; i < expectedTopicExpressionsCount; i++) {
			
			toCheckTopicExpression = toCheckTopicExpressions.get(i);
			expectedTopicExpression = expectedTopicExpressions.get(i);
			
			if (isDebug)
				System.out.println("[DEBUG] \t ~~~~ TopicExpression (# "+ i +") details : ~~~~\n");
			
			AbsWsnbTypesAndPayloadsUnitTests.checkTopicExpressionType(expectedTopicExpression, toCheckTopicExpression, isDebug);

			if (isDebug)
				System.out.println("[DEBUG] \t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}
		
		// ~~~~~~~ check FilterType MessageContents equality ~~~~~~~~ 
		List<MessageContentExpression> expectedMessageContentExpressions =  expectedFilter.getMessageContentExpressions(),
		toCheckMessageContentExpressions = toCheckFilter.getMessageContentExpressions();	
		
		int expectedMessageContentExpressionsCount = (expectedMessageContentExpressions != null)? expectedMessageContentExpressions.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Filters have different MessageContentExpression list \n" +
				"\t(-> toCheckFilter MessageContentExpressions count : " + ((toCheckMessageContentExpressions != null)?toCheckMessageContentExpressions.size():"none") +
				"\n\t-> expectedFilter MessageContentExpressions count : " + ((expectedMessageContentExpressionsCount==-1)?"none": expectedMessageContentExpressionsCount) + ")",
				(expectedMessageContentExpressions == null && toCheckMessageContentExpressions == null) ||
				toCheckMessageContentExpressions.size() == expectedMessageContentExpressionsCount);
		
		MessageContentExpression expectedMessageContentExpression,toCheckMessageContentExpression;
		
		for (int i = 0; i < expectedMessageContentExpressionsCount; i++) {
			
			toCheckMessageContentExpression = toCheckMessageContentExpressions.get(i);
			expectedMessageContentExpression = expectedMessageContentExpressions.get(i);
			
			if (isDebug)
				System.out.println("[DEBUG] \t~~~~ MessageContentExpression (# "+ i +") details : ~~~~\n");
			
			AbsWsnbTypesAndPayloadsUnitTests.checkMessageContentExpression(expectedMessageContentExpression, toCheckMessageContentExpression, isDebug);
			
			if (isDebug)
				System.out.println("[DEBUG] \t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}	
		
		// ~~~~~~~ check FilterType ProducerProperties equality ~~~~~~~~ 
		List<ProducerPropertiesExpression> expectedProducerPropertiesExpressions =  expectedFilter.getProducerPropertiesExpressions(),
		toCheckProducerPropertiesExpressions = toCheckFilter.getProducerPropertiesExpressions();	
		
		int expectedProducerPropertiesExpressionsCount = (expectedProducerPropertiesExpressions != null)? expectedProducerPropertiesExpressions.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Filters have different ProducerPropertiesExpression list \n" +
				"\t(-> toCheckFilter ProducerPropertiesExpressions count : " + ((toCheckProducerPropertiesExpressions != null)?toCheckProducerPropertiesExpressions.size():"none") +
				"\n\t-> expectedFilter ProducerPropertiesExpressions count : " + ((expectedProducerPropertiesExpressionsCount==-1)?"none": expectedProducerPropertiesExpressionsCount) + ")",
				(expectedProducerPropertiesExpressions == null && toCheckProducerPropertiesExpressions == null) ||
				toCheckProducerPropertiesExpressions.size() == expectedProducerPropertiesExpressionsCount);
		
		ProducerPropertiesExpression expectedProducerPropertiesExpression,toCheckProducerPropertiesExpression;
		
		for (int i = 0; i < expectedProducerPropertiesExpressionsCount; i++) {
			
			toCheckProducerPropertiesExpression = toCheckProducerPropertiesExpressions.get(i);
			expectedProducerPropertiesExpression = expectedProducerPropertiesExpressions.get(i);
			
			if (isDebug)
				System.out.println("[DEBUG] \t ~~~~ ProducerPropertiesExpression (# "+ i +") details : ~~~~\n");
			
			AbsWsnbTypesAndPayloadsUnitTests.checkProducerPropertiesExpression(expectedProducerPropertiesExpression, toCheckProducerPropertiesExpression, isDebug);
			
			if (isDebug)
				System.out.println("[DEBUG] \t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}
		
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link GetCurrentMessage} object against a reference one
	 */
	protected static boolean checkGetCurrentMessage(GetCurrentMessage expectedPayload,
			GetCurrentMessage toCheckPayload, boolean isDebug){		
				
		// ~~~~~~~ check GetCurrentMessage Topic equality ~~~~~~~~ 
		TopicExpressionType expectedTopic =  expectedPayload.getTopic(),
		toCheckTopic = toCheckPayload.getTopic();	
					
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Payloads \"GetCurrentMessage\" have different topic \n" +
				"\t(-> toCheckTopic is " + ((toCheckTopic != null)?"not null":"null") +
				"\n\t-> expectedTopic is " + ((expectedTopic != null)?"not null":"null") + ")",
				(toCheckTopic != null) && (expectedTopic != null));
			
		AbsWsnbTypesAndPayloadsUnitTests.checkTopicExpressionType(expectedTopic, toCheckTopic, isDebug);

		return true;		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link GetCurrentMessageResponse} object against a reference one
	 */
	protected static boolean checkGetCurrentMessageResponse(GetCurrentMessageResponse expectedPayload,
			GetCurrentMessageResponse toCheckPayload, boolean isDebug){		
				
		// ~~~~~~~ check GetCurrentMessageResponse Topic equality ~~~~~~~~ 
		List<Message> expectedNotifications =  expectedPayload.getNotifications(),
		toCheckNotifications = toCheckPayload.getNotifications();	
		
		int expectedNotificationsCount = (expectedNotifications != null)? expectedNotifications.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Filters have different ProducerPropertiesExpression list \n" +
				"\t(-> toCheckPayload Notifications count : " + ((toCheckNotifications != null)?toCheckNotifications.size():"none") +
				"\n\t-> expectedPayload Notifications count : " + ((expectedNotificationsCount==-1)?"none": expectedNotificationsCount) + ")",
				(expectedNotifications == null && toCheckNotifications == null) ||
				toCheckNotifications.size() == expectedNotificationsCount);
		
		Message expectedMessage,toCheckMessage;
		
		for (int i = 0; i < expectedNotificationsCount; i++) {
			
			toCheckMessage = toCheckNotifications.get(i);
			expectedMessage = expectedNotifications.get(i);
			
			if (isDebug)
				System.out.println("[DEBUG] \t ~~~~ Message (# "+ i +") details : ~~~~\n");
			
			AbsWsnbTypesAndPayloadsUnitTests.checkNotificationMessageHolderTypeMessage(expectedMessage, toCheckMessage, isDebug);
			
			if (isDebug)
				System.out.println("[DEBUG] \t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}
		
		
		return true;		
	}	
	/**
	 * Check a given (provided by the "WsnbReader") {@link NotificationMessageHolderType.Message} object
	 * 	 against a reference one
	 */
	protected static boolean checkNotificationMessageHolderTypeMessage(NotificationMessageHolderType.Message expectedMessage,
			NotificationMessageHolderType.Message toCheckMessage, boolean isDebug){		
				
		// ~~~~~~~ check Message content type equality ~~~~~~~~		
		Object toCheckAny = toCheckMessage.getAny() , expectedAny = expectedMessage.getAny();
		
		String toCheckAnyType = toCheckAny.getClass().getSimpleName() , 
			toCheckAnySuperType = toCheckAny.getClass().getSuperclass().getSimpleName() ,
			expectedAnyType = expectedAny.getClass().getSimpleName(), 
			expectedAnySuperType = expectedAny.getClass().getSuperclass().getSimpleName();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Message of NotificationMessageHolder have not supported typed content.\n" +
				"Expected content of message is \"org.w3c.dom.Element\" types." +
				"(Found types for messages are :\n" +
				"-> toCheck Message content type is : " + toCheckAnyType +"\n"+
				"-> expected Message content type is : " + expectedAnyType +")",
				(toCheckAnyType.equals(expectedAnyType) ||
						toCheckAnySuperType.equals(expectedAnyType) ||
						toCheckAnyType.equals(expectedAnySuperType) ||
						toCheckAnySuperType.equals(expectedAnySuperType)));			

		// ~~~~~~~ case "Element" : check Message content equality ~~~~~~
		if(toCheckAny instanceof Element){
		
			String toCheckAnyEltAsString = WsaUnitTestsUtils.formatToComparison((Element)toCheckAny), 
			expectedAnyEltAsString = WsaUnitTestsUtils.formatToComparison((Element)expectedAny);
			
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckMessage any value : " + toCheckAnyEltAsString +
						"\n[DEBUG] --> expectedMessage any value : " + expectedAnyEltAsString + "\n");
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"Message Element Content (formatted to comparison) are \n" +
					"\t(-> toCheckMessage any value : " + toCheckAnyEltAsString +
					"\n\t-> expectedMessage any value : " + expectedAnyEltAsString+ ")" , 
					toCheckAnyEltAsString.equals(expectedAnyEltAsString));
		} 
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link NotificationMessageHolderType} object
	 * 	 against a reference one
	 */
	protected static boolean checkNotificationMessageHolderType(NotificationMessageHolderType expectedNotifMsgHolder,
			NotificationMessageHolderType toCheckNotifMsgHolder, boolean isDebug){		
				
		// ~~~~~~~ check NotificationMessageHolderType message equality ~~~~~~~~ 
		Message toCheckMessage = toCheckNotifMsgHolder.getMessage(), expectedMessage = expectedNotifMsgHolder.getMessage();
		AbsWsnbTypesAndPayloadsUnitTests.checkNotificationMessageHolderTypeMessage(expectedMessage, toCheckMessage, isDebug);
		
		// ~~~~~~~ check NotificationMessageHolderType concretTopic equality ~~~~~~~~  
		TopicExpressionType toCheckTopic = toCheckNotifMsgHolder.getTopic() , expectedTopic = expectedNotifMsgHolder.getTopic();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"NotificationMessageHolder have different topic \n" +
				"\t(-> toCheckTopic is " + ((toCheckTopic != null)?"not null":"null") +
				"\n\t-> expectedTopic is " + ((expectedTopic != null)?"not null":"null") + ")",
				(toCheckTopic == null && expectedTopic == null) ||
				(toCheckTopic != null) && (expectedTopic != null));
		
		if (toCheckTopic!=null)
			AbsWsnbTypesAndPayloadsUnitTests.checkTopicExpressionType(expectedTopic, toCheckTopic, isDebug);
		
		// ~~~~~~~ check NotificationMessageHolderType ProducerReference equality ~~~~~~~~  
		EndpointReferenceType toCheckProducerRef= toCheckNotifMsgHolder.getProducerReference() , 
			expectedProducerRef = expectedNotifMsgHolder.getProducerReference();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"NotificationMessageHolder have different ProducerReference  \n" +
				"\t(-> toCheckProducerRef is " + ((toCheckProducerRef != null)?"not null":"null") +
				"\n\t-> expectedProducerRef is " + ((expectedProducerRef != null)?"not null":"null") + ")",
				(toCheckProducerRef == null && expectedProducerRef == null) ||
				(toCheckProducerRef != null) && (expectedProducerRef != null));
		
		if (toCheckProducerRef != null){		
			AbsWsaTypesUnitTests.checkEndpointReferenceType(expectedProducerRef, toCheckProducerRef, WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);
		}
		
		// ~~~~~~~ check NotificationMessageHolderType SubscriptionReference equality ~~~~~~~~  
		EndpointReferenceType toCheckSubscriptionRef = toCheckNotifMsgHolder.getSubscriptionReference() ,
			expectedSubscriptionRef = expectedNotifMsgHolder.getSubscriptionReference();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"NotificationMessageHolder have different SubscriptionReference  \n" +
				"\t(-> toCheckSubscriptionRef is " + ((toCheckSubscriptionRef != null)?"not null":"null") +
				"\n\t-> expectedSubscriptionRef is " + ((expectedSubscriptionRef != null)?"not null":"null") + ")",
				(toCheckSubscriptionRef == null && expectedSubscriptionRef == null) ||
				(toCheckSubscriptionRef != null) && (expectedSubscriptionRef != null));
		
		if (toCheckSubscriptionRef != null){
			AbsWsaTypesUnitTests.checkEndpointReferenceType(expectedSubscriptionRef, toCheckSubscriptionRef, WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);
		}
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link Notify} object
	 * 	 against a reference one
	 */
	protected static boolean checkNotify(Notify expectedNotify,
			Notify toCheckNotify, boolean isDebug){		
				
		// ~~~~~~~ check NotificationMessages equality ~~~~~~~~ 
		List<NotificationMessageHolderType> toCheckNotifMessages = toCheckNotify.getNotificationMessage(),
		expectedNotifMessages = expectedNotify.getNotificationMessage();
				
		int expectedNotifMessagesCount = (expectedNotifMessages != null)? expectedNotifMessages.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Notify payloads have different notificationMessages values\n" +
				"\t(-> toCheckNotify messages  count : " + ((toCheckNotifMessages != null)?toCheckNotifMessages.size():"none") +
				"\n\t-> expectedNotify messages count : " + ((expectedNotifMessagesCount==-1)?"none": expectedNotifMessagesCount) + ")",				
				(expectedNotifMessagesCount > 0) &&
				toCheckNotifMessages.size() == expectedNotifMessagesCount);
		
		for (int i = 0; i < expectedNotifMessagesCount; i++) {			
			AbsWsnbTypesAndPayloadsUnitTests.checkNotificationMessageHolderType(expectedNotifMessages.get(i), 
					toCheckNotifMessages.get(i), isDebug);				
		}				
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link Renew} object
	 * 	 against a reference one
	 */
	protected static boolean checkRenew(Renew expectedPayload, Renew toCheckPayload, boolean isDebug){		
		
		// ~~~~~~~ check TerminationTime equality ~~~~~
		Object toCheckTermTime = toCheckPayload.getTerminationTime(),
		expectedTermTime = expectedPayload.getTerminationTime();

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Renew payload have different TerminationTime \n" +
				"\t(-> toCheckTermTime is " + ((toCheckTermTime != null)?"not null":"null") +
				"\n\t-> expectedTermTime is " + ((expectedTermTime != null)?"not null":"null") + ")",
				(toCheckTermTime != null) && (expectedTermTime != null)||
				(toCheckTermTime == null) && (expectedTermTime == null));

		if (toCheckTermTime != null){
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"Renew payload have different TerminationTime type\n" +
					"\t(-> toCheckTermTime type is " + toCheckTermTime.getClass().getSimpleName() +
					"\n\t-> expectedTermTime type is " + expectedTermTime.getClass().getSimpleName() + ")",
					toCheckTermTime.getClass().isInstance(expectedTermTime));

			if (toCheckTermTime instanceof Duration){
				Duration toCheckDuration = (Duration)toCheckTermTime , expectedDuration = (Duration)expectedTermTime;

				if (isDebug)
					System.out.println("[DEBUG] --> toCheckTermTime value is : " + toCheckDuration.toString() +
							"\n[DEBUG] --> expectedTermTime value is : " + expectedDuration.toString() + "\n");

				Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
						"Renew payload have different TerminationTime value\n" +
						"\t(-> toCheckTermTime value is " + toCheckDuration.toString() +
						"\n\t-> expectedTermTime value is " + expectedDuration.toString() + ")",
						toCheckDuration.equals(expectedDuration));

			}else if (toCheckTermTime instanceof Date){
				Date toCheckGCal = (Date)toCheckTermTime , expectedGCal = (Date)expectedTermTime;
				if (isDebug)
					System.out.println("[DEBUG] --> toCheckTermTime value is : " + toCheckGCal.toString() +
							"\n[DEBUG] --> expectedTermTime value is : " + expectedGCal.toString() + "\n");

				Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
						"Renew payload have different TerminationTime value\n" +
						"\t(-> toCheckTermTime value is " + toCheckGCal.toString() +
						"\n\t-> expectedTermTime value is " + expectedGCal.toString() + ")",
						toCheckGCal.equals(expectedGCal));

			}else 
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
						"Renew payload have unknown TerminationTime type\n" +
						"\t(-> type is " + toCheckTermTime.getClass().getSimpleName()+")");
		}
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link RenewResponse} object
	 * 	 against a reference one
	 */
	protected static boolean checkRenewResponse(RenewResponse expectedPayload, RenewResponse toCheckPayload, boolean isDebug){		

		// ~~~~~~~ check TerminationTime equality ~~~~~
		Date toCheckTerminationTime = toCheckPayload.getTerminationTime(),
			expectedTerminationTime = expectedPayload.getTerminationTime();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscribe payload have different TerminationTime \n" +
				"\t(-> toCheckCreationTime is " + ((toCheckTerminationTime!= null)?"not null":"null") +
				"\n\t-> expectedCreationTime is " + ((expectedTerminationTime != null)?"not null":"null") + ")",
				(toCheckTerminationTime != null) && (expectedTerminationTime != null));

		if (isDebug)
			System.out.println("[DEBUG] --> toCheckTerminationTime value is : " + toCheckTerminationTime.toString() +
					"\n[DEBUG] --> expectedTerminationTime value is : " + expectedTerminationTime.toString() + "\n");

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscribe payload have different InitialTerminationTime value\n" +
				"\t(-> toCheckTerminationTime value is " + toCheckTerminationTime.toString() +
				"\n\t-> expectedTerminationTime value is " + expectedTerminationTime.toString() + ")",
				toCheckTerminationTime.equals(expectedTerminationTime));

		
		// ~~~~~~~ check CreationTerminationTime equality ~~~~~
		Date toCheckCurrentTime = toCheckPayload.getCurrentTime(),
		expectedCurrentTime = expectedPayload.getCurrentTime();
	
	Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
			"Subscribe payload have different CurrentTime \n" +
			"\t(-> toCheckCurrentTime is " + ((toCheckCurrentTime != null)?"not null":"null") +
			"\n\t-> expectedCurrentTime is " + ((expectedCurrentTime != null)?"not null":"null") + ")",
			(toCheckCurrentTime == null && expectedCurrentTime == null) ||
			(toCheckCurrentTime != null) && (expectedCurrentTime != null));

	if (toCheckCurrentTime != null){

		if (isDebug)
			System.out.println("[DEBUG] --> toCheckCurrentTime value is : " + toCheckCurrentTime.toString() +
					"\n[DEBUG] --> expectedCurrentTime value is : " + expectedCurrentTime.toString() + "\n");

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscribe payload have different InitialTerminationTime value\n" +
				"\t(-> toCheckCurrentTime value is " + toCheckCurrentTime.toString() +
				"\n\t-> expectedCurrentTime value is " + expectedCurrentTime.toString() + ")",
				toCheckCurrentTime.equals(expectedCurrentTime));

	}
					
		return true;		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link SubscriptionPolicyType} object
	 * 	 against a reference one
	 */
	protected static boolean checkSubscriptionPolicyType(SubscriptionPolicyType expectedSubsPolicy,
			SubscriptionPolicyType toCheckSubsPolicy, boolean isDebug){		
				
		// ~~~~~~~ check Policies equality ~~~~~~~~ 
		List<Element> toCheckPolicies = toCheckSubsPolicy.getPolicies(),
		expectedPolicies = expectedSubsPolicy.getPolicies();
				
		int expectedPoliciesCount = (expectedPolicies != null)? expectedPolicies.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"SubscriptionPolicy have different policies content\n" +
				"\t(-> toCheckSubsPolicy policies  count : " + ((toCheckPolicies != null)?toCheckPolicies.size():"none") +
				"\n\t-> expectedSubsPolicy policies count : " + ((expectedPoliciesCount==-1)?"none": expectedPoliciesCount) + ")",				
				(expectedPoliciesCount > 0) &&
				toCheckPolicies.size() == expectedPoliciesCount);
		
		String toCheckPolicyEltAsString, expectedPolicyEltAsString;
		for (int i = 0; i < expectedPoliciesCount; i++) {		
			toCheckPolicyEltAsString = WsaUnitTestsUtils.formatToComparison(toCheckPolicies.get(i)); 
			expectedPolicyEltAsString = WsaUnitTestsUtils.formatToComparison(expectedPolicies.get(i));
			
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckPolicy #"+ i +" (formattedToCompare) : " + toCheckPolicyEltAsString +
						"\n[DEBUG] --> expectedPolicy #"+ i +" (formattedToCompare) : " + expectedPolicyEltAsString + "\n");
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"Message Element Content (formatted to comparison) are \n" +
					"\t(-> toCheckPolicy #"+ i +" (formattedToCompare) : " + toCheckPolicyEltAsString +
					"\n\t-> expectedPolicy #"+ i +" (formattedToCompare) : " + expectedPolicyEltAsString+ ")" , 
					toCheckPolicyEltAsString.equals(expectedPolicyEltAsString));
				
		}				
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link Subscribe} object
	 * 	 against a reference one
	 */
	protected static boolean checkSubscribe(Subscribe expectedSubscribe,
			Subscribe toCheckSubscribe, boolean isDebug){		
				
		// ~~~~~~~ check ConsumerReference equality ~~~~~~~~ 
		EndpointReferenceType toCheckConsumerRef = toCheckSubscribe.getConsumerReference(),
		expectedConsumerRef = expectedSubscribe.getConsumerReference();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"ConsumerReference must not be null ! \n" +
				"\t(-> toCheckConsumerRef is " + ((toCheckConsumerRef != null)?"not null":"null") +
				"\n\t-> expectedConsumerRef is " + ((expectedConsumerRef != null)?"not null":"null") + ")",
				(toCheckConsumerRef != null) && (expectedConsumerRef != null));
		
		AbsWsaTypesUnitTests.checkEndpointReferenceType(expectedConsumerRef, toCheckConsumerRef, WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);
		
		// ~~~~~~~ check Filter equality ~~~~~~
		FilterType toCheckFilter = toCheckSubscribe.getFilter(), expectedFilter = expectedSubscribe.getFilter();
	
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscribe payload have different Filter \n" +
				"\t(-> toCheckFilter is " + ((toCheckFilter != null)?"not null":"null") +
				"\n\t-> expectedFilter is " + ((expectedFilter != null)?"not null":"null") + ")",
				(toCheckFilter == null && expectedFilter == null) ||
				(toCheckFilter != null) && (expectedFilter != null));

		if (toCheckFilter != null){
			AbsWsnbTypesAndPayloadsUnitTests.checkFilterType(expectedFilter, toCheckFilter, isDebug);		
		}

		// ~~~~~~~ check SubscriptionPolicy equality ~~~~~~
		SubscriptionPolicyType toCheckSubsPolicy = toCheckSubscribe.getSubscriptionPolicy(), 
			expectedSubsPolicy = expectedSubscribe.getSubscriptionPolicy();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscribe payload have different SubscriptionPolicy \n" +
				"\t(-> toCheckSubsPolicy is " + ((toCheckSubsPolicy != null)?"not null":"null") +
				"\n\t-> expectedSubsPolicy is " + ((expectedSubsPolicy != null)?"not null":"null") + ")",
				(toCheckSubsPolicy == null && expectedSubsPolicy == null) ||
				(toCheckSubsPolicy != null) && (expectedSubsPolicy != null));

		if (toCheckSubsPolicy != null){
			AbsWsnbTypesAndPayloadsUnitTests.checkSubscriptionPolicyType(expectedSubsPolicy, toCheckSubsPolicy, isDebug);
		}
		
		// ~~~~~~~ check InitialTerminationTime equality ~~~~~
		Object toCheckInitTermTime = toCheckSubscribe.getInitialTerminationTime(),
			expectedInitTermTime = expectedSubscribe.getInitialTerminationTime();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscribe payload have different InitialTerminationTime \n" +
				"\t(-> toCheckInitTermTime is " + ((toCheckInitTermTime != null)?"not null":"null") +
				"\n\t-> expectedInitTermTime is " + ((expectedInitTermTime != null)?"not null":"null") + ")",
				(toCheckInitTermTime == null && expectedInitTermTime == null) ||
				(toCheckInitTermTime != null) && (expectedInitTermTime != null));

		if (toCheckInitTermTime != null){
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"Subscribe payload have different InitialTerminationTime type\n" +
					"\t(-> toCheckInitTermTime type is " + toCheckInitTermTime.getClass().getSimpleName() +
					"\n\t-> expectedInitTermTime type is " + expectedInitTermTime.getClass().getSimpleName() + ")",
					toCheckInitTermTime.getClass().isInstance(expectedInitTermTime));
			
			if (toCheckInitTermTime instanceof Duration){
				Duration toCheckDuration = (Duration)toCheckInitTermTime , expectedDuration = (Duration)expectedInitTermTime;
				
				if (isDebug)
					System.out.println("[DEBUG] --> toCheckInitTermTime value is : " + toCheckDuration.toString() +
							"\n[DEBUG] --> expectedInitTermTime value is : " + expectedDuration.toString() + "\n");
					
				Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
						"Subscribe payload have different InitialTerminationTime value\n" +
						"\t(-> toCheckInitTermTime value is " + toCheckDuration.toString() +
						"\n\t-> expectedInitTermTime value is " + expectedDuration.toString() + ")",
						toCheckDuration.equals(expectedDuration));
			
			}else if (toCheckInitTermTime instanceof Date){
				Date toCheckGCal = (Date)toCheckInitTermTime , expectedGCal = (Date)expectedInitTermTime;
				if (isDebug)
					System.out.println("[DEBUG] --> toCheckInitTermTime value is : " + toCheckGCal.toString() +
							"\n[DEBUG] --> expectedInitTermTime value is : " + expectedGCal.toString() + "\n");
					
				Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
						"Subscribe payload have different InitialTerminationTime value\n" +
						"\t(-> toCheckInitTermTime value is " + toCheckGCal.toString() +
						"\n\t-> expectedInitTermTime value is " + expectedGCal.toString() + ")",
						toCheckGCal.equals(expectedGCal));
			
			}else 
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
						"Subscribe payload have unknown InitialTerminationTime type\n" +
						"\t(-> type is " + toCheckInitTermTime.getClass().getSimpleName()+")");

		}
					
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link SubscriptionManagerRP} object
	 * 	 against a reference one
	 */
	protected static boolean checkSubscriptionManagerRP(SubscriptionManagerRP expectedSubscription,
			SubscriptionManagerRP toCheckSubscription, boolean isDebug){		
				
		// ~~~~~~~ check ConsumerReference equality ~~~~~~~~ 
		EndpointReferenceType toCheckConsumerRef = toCheckSubscription.getConsumerReference(),
		expectedConsumerRef = expectedSubscription.getConsumerReference();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"ConsumerReference must not be null ! \n" +
				"\t(-> toCheckConsumerRef is " + ((toCheckConsumerRef != null)?"not null":"null") +
				"\n\t-> expectedConsumerRef is " + ((expectedConsumerRef != null)?"not null":"null") + ")",
				(toCheckConsumerRef != null) && (expectedConsumerRef != null));
		
		AbsWsaTypesUnitTests.checkEndpointReferenceType(expectedConsumerRef, toCheckConsumerRef, WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);
		
		// ~~~~~~~ check Filter equality ~~~~~~
		FilterType toCheckFilter = toCheckSubscription.getFilter(), expectedFilter = expectedSubscription.getFilter();
	
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscription have different Filter \n" +
				"\t(-> toCheckFilter is " + ((toCheckFilter != null)?"not null":"null") +
				"\n\t-> expectedFilter is " + ((expectedFilter != null)?"not null":"null") + ")",
				(toCheckFilter == null && expectedFilter == null) ||
				(toCheckFilter != null) && (expectedFilter != null));

		if (toCheckFilter != null){
			AbsWsnbTypesAndPayloadsUnitTests.checkFilterType(expectedFilter, toCheckFilter, isDebug);		
		}

		// ~~~~~~~ check SubscriptionPolicy equality ~~~~~~
		SubscriptionPolicyType toCheckSubsPolicy = toCheckSubscription.getSubscriptionPolicy(), 
			expectedSubsPolicy = expectedSubscription.getSubscriptionPolicy();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscription have different SubscriptionPolicy \n" +
				"\t(-> toCheckSubsPolicy is " + ((toCheckSubsPolicy != null)?"not null":"null") +
				"\n\t-> expectedSubsPolicy is " + ((expectedSubsPolicy != null)?"not null":"null") + ")",
				(toCheckSubsPolicy == null && expectedSubsPolicy == null) ||
				(toCheckSubsPolicy != null) && (expectedSubsPolicy != null));

		if (toCheckSubsPolicy != null){
			AbsWsnbTypesAndPayloadsUnitTests.checkSubscriptionPolicyType(expectedSubsPolicy, toCheckSubsPolicy, isDebug);
		}
		
		// ~~~~~~~ check CreationTerminationTime equality ~~~~~
		Date toCheckCreationTime = toCheckSubscription.getCreationTime(),
			expectedCreationTime = expectedSubscription.getCreationTime();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Subscription have different CreationTime \n" +
				"\t(-> toCheckCreationTime is " + ((toCheckCreationTime != null)?"not null":"null") +
				"\n\t-> expectedCreationTime is " + ((expectedCreationTime != null)?"not null":"null") + ")",
				(toCheckCreationTime == null && expectedCreationTime == null) ||
				(toCheckCreationTime != null) && (expectedCreationTime != null));

		if (toCheckCreationTime != null){

			if (isDebug)
				System.out.println("[DEBUG] --> toCheckCreationTime value is : " + toCheckCreationTime.toString() +
						"\n[DEBUG] --> expectedCreationTime value is : " + expectedCreationTime.toString() + "\n");

			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"Subscription have different CreationTime value\n" +
					"\t(-> toCheckCreationTime value is " + toCheckCreationTime.toString() +
					"\n\t-> expectedCreationTime value is " + expectedCreationTime.toString() + ")",
					toCheckCreationTime.equals(expectedCreationTime));

		}
					
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link SubscribeResponse} object
	 * 	 against a reference one
	 */
	protected static boolean checkSubscribeResponse(SubscribeResponse expectedSubscribeResponse,
			SubscribeResponse toCheckSubscribeResponse, boolean isDebug){		
				
		// ~~~~~~~ check SubscriptionReference equality ~~~~~~~~ 
		EndpointReferenceType toCheckSubscriptionRef = toCheckSubscribeResponse.getSubscriptionReference(),
		expectedSubscriptionRef = expectedSubscribeResponse.getSubscriptionReference();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"SubscriptionReference must not be null ! \n" +
				"\t(-> toCheckSubscriptionRef is " + ((toCheckSubscriptionRef != null)?"not null":"null") +
				"\n\t-> expectedSubscriptionRef is " + ((expectedSubscriptionRef != null)?"not null":"null") + ")",
				(toCheckSubscriptionRef != null) && (expectedSubscriptionRef != null));
		
		AbsWsaTypesUnitTests.checkEndpointReferenceType(expectedSubscriptionRef, toCheckSubscriptionRef, WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);
		
		// ~~~~~~~ check CurrentTime equality ~~~~~~
		Date toCheckGCal = toCheckSubscribeResponse.getCurrentTime() ,
			expectedGCal = expectedSubscribeResponse.getCurrentTime();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"SubscribeResponse payload have different CurrentTime \n" +
				"\t(-> toCheckCurrentTime is " + ((toCheckGCal != null)?"not null":"null") +
				"\n\t-> expectedCurrentTime is " + ((expectedGCal != null)?"not null":"null") + ")",
				(toCheckGCal == null && expectedGCal == null) ||
				(toCheckGCal != null) && (expectedGCal != null));

		if (toCheckGCal != null){
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckCurrentTime value is : " + toCheckGCal.toString() +
						"\n[DEBUG] --> expectedCurrentTime value is : " + expectedGCal.toString() + "\n");
				
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"SubscribeResponse payload have different CurrentTime value\n" +
					"\t(-> toCheckCurrentTime value is " + toCheckGCal.toString() +
					"\n\t-> expectedCurrentTime value is " + expectedGCal.toString() + ")",
					toCheckGCal.equals(expectedGCal));		
		}
		
		// ~~~~~~~ check TerminationTime equality ~~~~~~
		    toCheckGCal = toCheckSubscribeResponse.getTerminationTime();
			expectedGCal = expectedSubscribeResponse.getTerminationTime();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"SubscribeResponse payload have different TerminationTime \n" +
				"\t(-> toCheckTerminationTime is " + ((toCheckGCal != null)?"not null":"null") +
				"\n\t-> expectedTerminationTime is " + ((expectedGCal != null)?"not null":"null") + ")",
				(toCheckGCal == null && expectedGCal == null) ||
				(toCheckGCal != null) && (expectedGCal != null));

		if (toCheckGCal != null){
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckTerminationTime value is : " + toCheckGCal.toString() +
						"\n[DEBUG] --> expectedTerminationTime value is : " + expectedGCal.toString() + "\n");
				
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"SubscribeResponse payload have different TerminationTime value\n" +
					"\t(-> toCheckTerminationTime value is " + toCheckGCal.toString() +
					"\n\t-> expectedTerminationTime value is " + expectedGCal.toString() + ")",
					toCheckGCal.equals(expectedGCal));		
		}
					
		return true;		
	}	
		
	/**
	 * Check a given (provided by the "WsnbReader") {@link Unsubscribe} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnsubscribe(Unsubscribe expectedUnsubscribe,
			Unsubscribe toCheckUnsubscribe, boolean isDebug){		
				
		// ~~~~~~~ check Unsubscribe equality (~~~~~~~~ 			
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckUnsubscribe : " +  ((toCheckUnsubscribe !=null)?toCheckUnsubscribe.toString():"null") +
					"\n[DEBUG] --> expectedUnsubscribe : " + ((expectedUnsubscribe!=null)?expectedUnsubscribe.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"Unsubscribe are \n" +
				"\t(-> toCheckUnsubscribe : " + ((toCheckUnsubscribe!=null)?toCheckUnsubscribe.toString():"null") +
				"\n\t-> expectedUnsubscribe : " + ((expectedUnsubscribe!=null)?expectedUnsubscribe.toString():"null")+ ")" , 
				(toCheckUnsubscribe != null && expectedUnsubscribe != null));

		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnsubscribeResponse} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnsubscribeResponse(UnsubscribeResponse expectedUnsubscribeResponse,
			UnsubscribeResponse toCheckUnsubscribeResponse, boolean isDebug){		
				
		// ~~~~~~~ check UnsubscribeResponse equality (~~~~~~~~ 			
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckUnsubscribeResponse : " +  ((toCheckUnsubscribeResponse !=null)?toCheckUnsubscribeResponse.toString():"null") +
					"\n[DEBUG] --> expectedUnsubscribeResponse : " + ((expectedUnsubscribeResponse!=null)?expectedUnsubscribeResponse.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"UnsubscribeResponse are \n" +
				"\t(-> toCheckUnsubscribeResponse : " + ((toCheckUnsubscribeResponse!=null)?toCheckUnsubscribeResponse.toString():"null") +
				"\n\t-> expectedUnsubscribeResponse : " + ((expectedUnsubscribeResponse!=null)?expectedUnsubscribeResponse.toString():"null")+ ")" , 
				(toCheckUnsubscribeResponse != null && expectedUnsubscribeResponse != null));

		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UseRaw} object
	 * 	 against a reference one
	 */
	protected static boolean checkUseRaw(UseRaw expectedUseRaw,
			UseRaw toCheckUseRaw, boolean isDebug){		
				
		// ~~~~~~~ check UseRaw equality (~~~~~~~~ 			
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckUseRaw : " +  ((toCheckUseRaw !=null)?toCheckUseRaw.toString():"null") +
					"\n[DEBUG] --> expectedUseRaw : " + ((expectedUseRaw!=null)?expectedUseRaw.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"UseRaw are \n" +
				"\t(-> toCheckUseRaw : " + ((toCheckUseRaw!=null)?toCheckUseRaw.toString():"null") +
				"\n\t-> expectedUseRaw : " + ((expectedUseRaw!=null)?expectedUseRaw.toString():"null")+ ")" , 
				(toCheckUseRaw != null && expectedUseRaw != null));

		return true;		
	}	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/**
	 * Unit Test for WsnbFactory : create a {@link GetCurrentMessage} Object
	 * Unit Test for WsnbWriter : write the created {@link GetCurrentMessage} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link GetCurrentMessage} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadGetCurrentMessage() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM GetCurrentMessage\" ~~~~~~~ \n");				
		// ~~~~ create an default GetCurrentMessage and write it to DOM document ~~~~ 
		GetCurrentMessage payload = null;
		// ~~~~ set a concrete Topic ~~~~		
		try {
			TopicExpressionType concreteTopic = this.factory.createTopicExpressionType(WstopConstants.CONCRETE_TOPIC_EXPRESSION_DIALECT_URI);
			concreteTopic.addTopicNamespace("sensor", new URI("http://www.scneiderElectric/sensor"));
			concreteTopic.setContent("sensor:outside/thermal");
			payload = this.factory.createGetCurrentMessage(concreteTopic);
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		}
			
		Document payloadAsDOM = this.writer.writeGetCurrentMessageAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, GetCurrentMessage.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created GetCurrentMessage from its DOM Document representation
		GetCurrentMessage readGetCurrentMessage = this.reader.readGetCurrentMessage(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkGetCurrentMessage(payload, readGetCurrentMessage, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM GetCurrentMessage\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}	

	/**
	 * Unit Test for WsnbFactory : create a {@link GetCurrentMessageResponse} Object
	 * Unit Test for WsnbWriter : write the created {@link GetCurrentMessageResponse} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link GetCurrentMessageResponse} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadGetCurrentMessageResponse() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM GetCurrentMessageResponse\" ~~~~~~~ \n");				
		// ~~~~ create an default GetCurrentMessageResponse and write it to DOM document ~~~~ 
		
		// ~~~~ create Element message ~~~~~
		Document defaultDoc = this.createDefaultDocument("http://default/test/uri");

		Element msgElt = defaultDoc.createElementNS("http://www.schneider-electric.org/sensor","SensorCapture"),child1,child2;
		msgElt.setPrefix("npex");
		child1 = defaultDoc.createElement("Temperature");		
		child1.setNodeValue("38°C");
		msgElt.appendChild(child1);
		child2 = defaultDoc.createElement("Humidity");
		child2.setNodeValue("12%");
		msgElt.appendChild(child2);		
		
		// ~~~~~ Invoke test ~~~~~
		// ~~~~ create an default Notify and write it to DOM document ~~~~ 
		// ~~~~ first create a Message then a NotificationMessageHolder and then
		// ~~~~ the payload notify
		Message msg = this.factory.createNotificationMessageHolderTypeMessage(msgElt);
		// ~~~~ set a notification previously sent ~~~~		
		
		GetCurrentMessageResponse payload = this.factory.createGetCurrentMessageResponse(msg);
		
			
		Document payloadAsDOM = this.writer.writeGetCurrentMessageResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, GetCurrentMessageResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created GetCurrentMessageResponse from its DOM Document representation
		GetCurrentMessageResponse readGetCurrentMessageResponse = this.reader.readGetCurrentMessageResponse(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkGetCurrentMessageResponse(payload, readGetCurrentMessageResponse, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM GetCurrentMessageResponse\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}	
	/**
	 * Unit Test for WsnbFactory : create a {@link TopicExpressionType} Object
	 * Unit Test for WsnbWriter : write the created {@link TopicExpressionType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link TopicExpressionType} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadTopicExpressionType() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TopicExpressionType\" ~~~~~~~ \n");				
		// ~~~~ create an default TopicExpressionType and write it to DOM document ~~~~ 
		TopicExpressionType topicExpression = this.factory.createTopicExpressionType(WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI);
			
		// ~~~~ optional : add content ~~~~
		topicExpression.setContent("tns1:rootTopic/*/grandChildTopic1");
		
		// ~~~~ optional : add topicNamespace ~~~~
		try {
			topicExpression.addTopicNamespace("tns1", new URI("http://default/namespace/for/topic"));
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		}
		
		Document topicExpressionAsDOM = this.writer.writeTopicExpressionTypeAsDOM(topicExpression);

		try {
			WsaUnitTestsUtils.validateResult(topicExpressionAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, TopicExpressionType.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created TopicExpressionType from its DOM Document representation
		TopicExpressionType readTopicExpressionType = this.reader.readTopicExpressionType(topicExpressionAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkTopicExpressionType(topicExpression, readTopicExpressionType, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TopicExpressionType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}	

	/**
	 * Unit Test for WsnbFactory : create a {@link MessageContentExpression} Object
	 * Unit Test for WsnbWriter : write the created {@link MessageContentExpression} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link MessageContentExpression} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadMessageContentExpression() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM MessageContentExpression\" ~~~~~~~ \n");				
		
		// ~~~~ create an default MessageContentExpression and write it to DOM document ~~~~ 
		MessageContentExpression msgContentExpression = 
			this.factory.createMessageContentExpression(WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI);
			
		// ~~~~ optional : add content ~~~~
		msgContentExpression.setContent("/data/temperature[external>35]");
				
		Document msgContentExpressionAsDOM = this.writer.writeMessageContentExpressionAsDOM(msgContentExpression);

		try {
			WsaUnitTestsUtils.validateResult(msgContentExpressionAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, QueryExpressionType.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created MessageContentExpression from its DOM Document representation
		MessageContentExpression readMsgContentExpression = this.reader.readMessageContentExpression(msgContentExpressionAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkMessageContentExpression(msgContentExpression, readMsgContentExpression, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM MessageContentExpression\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link ProducerPropertiesExpression} Object
	 * Unit Test for WsnbWriter : write the created {@link ProducerPropertiesExpression} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link ProducerPropertiesExpression} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadProducerPropertiesExpression() throws WsnbException {

		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM ProducerPropertiesExpression\" ~~~~~~~ \n");				
		
		// ~~~~ create an default ProducerPropertiesExpression and write it to DOM document ~~~~ 
		ProducerPropertiesExpression prodPropertiesExpression = 
			this.factory.createProducerPropertiesExpression(WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI);
			
		// ~~~~ optional : add content ~~~~
		prodPropertiesExpression.setContent("*//identity[name=\"External Sensor\"]");
				
		Document prodPropertiesExpressionAsDOM = this.writer.writeProducerPropertiesExpressionAsDOM(prodPropertiesExpression);

		try {
			WsaUnitTestsUtils.validateResult(prodPropertiesExpressionAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, QueryExpressionType.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created ProducerPropertiesExpression from its DOM Document representation
		ProducerPropertiesExpression readMsgContentExpression = this.reader.readProducerPropertiesExpression(prodPropertiesExpressionAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkProducerPropertiesExpression(prodPropertiesExpression, readMsgContentExpression, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM ProducerPropertiesExpression\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	/**
	 * Unit Test for WsnbFactory : create a {@link FilterType} Object
	 * Unit Test for WsnbWriter :GetCurrentMessage created {@link FilterType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link FilterType} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadFilterType() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM FilterType\" ~~~~~~~ \n");				
		
		FilterType filter = this.createDefaultFilter();
		
		Document filterAsDOM = this.writer.writeFilterTypeAsDOM(filter);

		try {
			WsaUnitTestsUtils.validateResult(filterAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, FilterType.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created FilterType from its DOM Document representation
		FilterType readFilter = this.reader.readFilterType(filterAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkFilterType(filter, readFilter, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM FilterType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}			
	
	/**
	 * Unit Test for WsnbFactory : create a {@link Notify} Object
	 * Unit Test for WsnbWriter : write the created {@link Notify} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link Notify} object
	 * @throws WsnbException   
	 */
	@Test
	public final void testCreateWriteAsDOMNotify() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM Notify \" ~~~~~~~ \n");				
		// ~~~~ create Element message ~~~~~
		Document defaultDoc = this.createDefaultDocument("http://default/test/uri");
		
		Element msgElt = defaultDoc.createElement("SensorCapture"),child1,child2;
		child1 = defaultDoc.createElement("Temperature");
		
		child1.setNodeValue("38°C");
		msgElt.appendChild(child1);
		child2 = defaultDoc.createElement("Humidity");
		child2.setNodeValue("12%");
		msgElt.appendChild(child2);		
		
		// ~~~~~ Invoke test ~~~~~
		// ~~~~ create an default Notify and write it to DOM document ~~~~ 
		// ~~~~ first create a Message then a NotificationMessageHolder and then
		// ~~~~ the payload notify
		Message msg = this.factory.createNotificationMessageHolderTypeMessage(msgElt);
		
		NotificationMessageHolderType notifMsg = this.factory.createNotificationMessageHolderType(msg);
										
		// ~~~~ set a concrete Topic ~~~~		
		try {
			TopicExpressionType concreteTopic = this.factory.createTopicExpressionType(WstopConstants.CONCRETE_TOPIC_EXPRESSION_DIALECT_URI);
			concreteTopic.addTopicNamespace("sensor", new URI("http://www.scneiderElectric/sensor"));
			concreteTopic.setContent("sensor:outside/thermal");
			notifMsg.setTopic(concreteTopic);
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		}
				
		// ~~~~ set SubscriptionReference  ~~~~~
		try {
			EndpointReferenceType subscriptionRef = WsnbUnitTestsUtils.createDefaultWsnSubscriptionReference();
			notifMsg.setSubscriptionReference(subscriptionRef);
		} catch (Exception e1) {
			throw new WsnbException(e1);
		} 
		
		// ~~~~ set ProducerReference  ~~~~~
		try {
			EndpointReferenceType producerRef = WsnbUnitTestsUtils.createDefaultWsnProducerOriginator();
			notifMsg.setProducerReference(producerRef);
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		}
		
		Notify payload = this.factory.createNotify(notifMsg);
		
		Document payloadAsDOM = this.writer.writeNotifyAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, Notify.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created Notify from its DOM Document representation
		Notify readPayload = this.reader.readNotify(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkNotify(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM Notify \" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
	}
	
	/**
	 * 	Common "Renew" payload Unit Test
	 * 
	 * Unit Test for WsnbFactory : create a {@link Renew} Object
	 * Unit Test for WsnbWriter : write the created {@link Renew} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link Renew} object
	 * @throws WsnbException   
	 */	
	public final void genericCreateWriteAsDOMRenew(Object terminationTime) throws WsnbException {
			
		// ~~~~ create an default Renew and write it to DOM document ~~~~ 
		Renew payload =	this.factory.createRenew();
		
		// ~~~~ Set TerminationTime ~~~~~
		if (terminationTime instanceof Duration)
			payload.setTerminationTime((Duration)terminationTime);
		else
			payload.setTerminationTime((Date)terminationTime);
		
		Document payloadAsDOM = this.writer.writeRenewAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, Renew.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created Renew from its DOM Document representation
		Renew readRenew = this.reader.readRenew(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkRenew(payload, readRenew, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
	}
	
	/**
	 * 	"Renew" payload Unit Test with Duration
	 */	
	@Test
	public final void testCreateWriteAsDOMRenewWithDuration() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM Renew with Duration\" ~~~~~~~ \n");				
		
		try {
			this.genericCreateWriteAsDOMRenew(DatatypeFactory.newInstance().newDuration(7200*1000));
		} catch (DatatypeConfigurationException e) {
			throw new WsnbException(e);
		}
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM Renew with Duration\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * 	"Renew" payload Unit Test with Date
	 */	
	@Test
	public final void testCreateWriteAsDOMRenewWithDate() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM Renew with Date\" ~~~~~~~ \n");				
		
		GregorianCalendar gCal = new GregorianCalendar();
		gCal.add(GregorianCalendar.HOUR, 3);
		
		this.genericCreateWriteAsDOMRenew(gCal.getTime());
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM Renew with Date\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link RenewResponse} Object
	 * Unit Test for WsnbWriter : write the created {@link RenewResponse} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link RenewResponse} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMRenewResponse() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM RenewResponse\" ~~~~~~~ \n");				
		
		// ~~~~ create an default RenewResponse and write it to DOM document ~~~~ 
		
		GregorianCalendar gCal = new GregorianCalendar();
		Date currentTime = gCal.getTime(), termTime;		
		gCal.add(GregorianCalendar.HOUR, 2);		
		termTime = gCal.getTime();
		
		RenewResponse payload = this.factory.createRenewResponse(termTime);
		
		// ~~~~ Set Current Time		
		payload.setCurrentTime(currentTime);		
		Document payloadAsDOM = this.writer.writeRenewResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, RenewResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created RenewResponse from its DOM Document representation
		RenewResponse readPayload = this.reader.readRenewResponse(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkRenewResponse(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM RenewResponse\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * 		Commun part of "Subscribe" Unit test
	 * 
	 * Unit Test for WsnbFactory : create a {@link Subscribe} Object
	 * Unit Test for WsnbWriter : write the created {@link Subscribe} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link Subscribe} object
	 * @throws WsnbException   
	 */
	public final void genericCreateWriteAsDOMSubscribe(Object initTerminationTime) throws WsnbException {
		
		// ~~~~ create a default ConsumerReference  ~~~~~
		EndpointReferenceType consumerRef = null;
		try {
			consumerRef = WsnbUnitTestsUtils.createDefaultWsnConsumerOriginator();
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		}
		Subscribe payload = this.factory.createSubscribe(consumerRef);
		
		// ~~~~ set filter ~~~~~
		payload.setFilter(this.createDefaultFilter());
		
		// ~~~~ set InitialTerminationTime ~~~~~		
		if (initTerminationTime instanceof Duration)
			payload.setInitialTerminationTime((Duration)initTerminationTime);
		else
			payload.setInitialTerminationTime((Date)initTerminationTime);
		
		// ~~~~ Set SubscriptionPolicy ~~~~~
		SubscriptionPolicyType policy = this.factory.createSubscriptionPolicyType();
		
		Document defaultDoc = this.createDefaultDocument("http://defauturi/for/policy");
		Element policyAsElt = defaultDoc.createElement("CustomPolicy"),child1;;
		child1 = defaultDoc.createElement("NotifFrequency");
		child1.setNodeValue("1 per minute max");
		policyAsElt.appendChild(child1);
		policy.addPolicy(policyAsElt);
		
		payload.setSubscriptionPolicy(policy);		
			
		Document payloadAsDOM = this.writer.writeSubscribeAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, Subscribe.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created Subscribe from its DOM Document representation
		Subscribe readPayload = this.reader.readSubscribe(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkSubscribe(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");	
		
	}
	
	/**
	 * 	"Subscribe" Unit Test with Duration 
	 * 	
	 * @throws WsnbException
	 */
	@Test
	public final void testCreateWriteAsDOMSubscribeWithDuration() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM Subscribe with Duration \" ~~~~~~~ \n");				
		
		try {
			this.genericCreateWriteAsDOMSubscribe(DatatypeFactory.newInstance().newDuration(3600*1000));
		} catch (DatatypeConfigurationException e) {
			throw new WsnbException(e);
		}
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM Subscribe with Duration\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");		
	}
	
	/**
	 * 	"Subscribe" Unit Test with Date 
	 * 	
	 * @throws WsnbException
	 */
	@Test
	public final void testCreateWriteAsDOMSubscribeWithDate() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM Subscribe with Date \" ~~~~~~~ \n");				
		GregorianCalendar gCal = new GregorianCalendar();
		gCal.add(GregorianCalendar.HOUR, 2);
		
		this.genericCreateWriteAsDOMSubscribe(gCal.getTime());
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM Subscribe with Date\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");		
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link SubscriptionManagerRP} Object
	 * Unit Test for WsnbWriter : write the created {@link SubscriptionManagerRP} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link SubscriptionManagerRP} object
	 * @throws WsnbException   
	 */
	@Test
	public final void testCreateWriteAsDOMSubscriptionManagerRP() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM SubscriptionManagerRP\" ~~~~~~~ \n");				
	
		SubscriptionManagerRP subscription = this.createDefaultSubscriptionManagerRP();
		
		Document subscriptionAsDOM = this.writer.writeSubscriptionManagerRPAsDOM(subscription);

		try {
			WsaUnitTestsUtils.validateResult(subscriptionAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, SubscriptionManagerRP.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created SubscriptionManagerRP from its DOM Document representation
		SubscriptionManagerRP readSubscription = this.reader.readSubscriptionManagerRP(subscriptionAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkSubscriptionManagerRP(subscription, readSubscription, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");	
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM SubscriptionManagerRP \" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");			
	}
	
	/**	 
	 * Unit Test for WsaFactory : create an SubscriptionManagerRP Object
	 * Unit Test for WsaWriter : write the created SubscriptionManagerRP object to Filesystem
	 * Unit Test for WsaReader : read from Filesystem the previously stored SubscriptionManagerRP object
	 * @throws WsnbException 
	 */
	@Test
	public final void testCreateWriteToFSReadFromFSEnpointReferenceType() throws WsnbException {

		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-Filesystem and read-from-Filesystem SubscriptionManagerRP\" ~~~~~~~ \n");

		// ~~~ Create storage folder ~~~ 
		File persistenceRepository = WsaUnitTestsUtils.createTempTestDirectory("SaveSubscriptions");

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + 
				"the persistence directory has not been found on filesystem !"
				,(persistenceRepository.exists()) && persistenceRepository.isDirectory());			

		// ~~~~~~ Write subscription to Filesystem ~~~~
		final SubscriptionManagerRP defaultSubscription = this.createDefaultSubscriptionManagerRP();

		final String subscriptionFileName = "subscription-" + UUID.randomUUID().toString();

		this.writer.writeSubscriptionManagerRPToFilesystem(defaultSubscription,persistenceRepository.getAbsolutePath()+ 
				File.separatorChar + subscriptionFileName);

		File subscriptionAsFile = new File(persistenceRepository.getAbsolutePath()+ File.separatorChar + subscriptionFileName);

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"The subscription has not been written on Filesystem\n" +
				"\tNo file named \"" + subscriptionFileName +"\" has been found",
				subscriptionAsFile.exists() && subscriptionAsFile.length()>0);						

		System.out.println("\t Unit Test Step 1 (\"create\" and \"write to Filesystem\") passed !\n");			

		// ~~~ Read subscription from Filesystem  ~~~
		SubscriptionManagerRP readSubscription = this.reader.readSubscriptionManagerRP(subscriptionAsFile);

		AbsWsnbTypesAndPayloadsUnitTests.checkSubscriptionManagerRP(defaultSubscription,readSubscription, this.isDebug);

		System.out.println("\t Unit Test Step 2 (\"read from Filesystem\") passed !");

		// clean and remove storage folder
		if (!isDebug)
			WsaUnitTestsUtils.deleteExistingTestDirectory(persistenceRepository);

		System.out.println("\n\t OK, unit test \"Create, Write-to-Filesystem and read-from-Filesystem SubscriptionManagerRP\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");	

	}
	/**
	 * Unit Test for WsnbFactory : create a {@link SubscribeResponse} Object
	 * Unit Test for WsnbWriter : write the created {@link SubscribeResponse} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link SubscribeResponse} object
	 * @throws WsnbException   
	 */
	@Test
	public final void testCreateWriteAsDOMSubscribeResponse() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM SubscribeResponse\" ~~~~~~~ \n");				
	
		// ~~~~ create a default ConsumerReference  ~~~~~
		EndpointReferenceType subscriptionRef = null;
		try {
			subscriptionRef = WsnbUnitTestsUtils.createDefaultWsnSubscriptionReference();
		} catch (Exception e1) {
			throw new WsnbException(e1);
		} 
		SubscribeResponse payload = this.factory.createSubscribeResponse(subscriptionRef);
		
		GregorianCalendar gCal = new GregorianCalendar();
		// ~~~~ set CurrentTime ~~~~~
		Date currentTime = gCal.getTime(), termTime;
		payload.setCurrentTime(currentTime);
		
		// ~~~~ set TerminationTime ~~~~~	
		gCal.add(GregorianCalendar.HOUR, 2);
		termTime = gCal.getTime();
		payload.setTerminationTime(termTime);
		
		Document payloadAsDOM = this.writer.writeSubscribeResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, SubscribeResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created SubscribeResponse from its DOM Document representation
		SubscribeResponse readPayload = this.reader.readSubscribeResponse(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkSubscribeResponse(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");	
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM SubscribeResponse \" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");			
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link Unsubscribe} Object
	 * Unit Test for WsnbWriter : write the created {@link Unsubscribe} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link Unsubscribe} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMUnsubscribe() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM Unsubscribe\" ~~~~~~~ \n");				
		
		// ~~~~ create an default Unsubscribe and write it to DOM document ~~~~ 
		Unsubscribe payload = this.factory.createUnsubscribe();
						
		Document payloadAsDOM = this.writer.writeUnsubscribeAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, Unsubscribe.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created Unsubscribe from its DOM Document representation
		Unsubscribe readPayload = this.reader.readUnsubscribe(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkUnsubscribe(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM Unsubscribe\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnsubscribeResponse} Object
	 * Unit Test for WsnbWriter : write the created {@link UnsubscribeResponse} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnsubscribeResponse} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMUnsubscribeResponse() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnsubscribeResponse\" ~~~~~~~ \n");				
		
		// ~~~~ create an default UnsubscribeResponse and write it to DOM document ~~~~ 
		UnsubscribeResponse payload = this.factory.createUnsubscribeResponse();
						
		Document payloadAsDOM = this.writer.writeUnsubscribeResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnsubscribeResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created UnsubscribeResponse from its DOM Document representation
		UnsubscribeResponse readPayload = this.reader.readUnsubscribeResponse(payloadAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkUnsubscribeResponse(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnsubscribeResponse\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UseRaw} Object
	 * Unit Test for WsnbWriter : write the created {@link UseRaw} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UseRaw} object
	 * @throws WsnbException   
	 */	
	@Test
	public final void testCreateWriteAsDOMUseRaw() throws WsnbException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UseRaw\" ~~~~~~~ \n");				
		
		// ~~~~ create an default UseRaw and write it to DOM document ~~~~ 
		UseRaw useRaw = 
			this.factory.createUseRaw();
						
		Document useRawAsDOM = this.writer.writeUseRawAsDOM(useRaw);

		try {
			WsaUnitTestsUtils.validateResult(useRawAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS,
					WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UseRaw.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created UseRaw from its DOM Document representation
		UseRaw readUseRaw = this.reader.readUseRaw(useRawAsDOM);		

		AbsWsnbTypesAndPayloadsUnitTests.checkUseRaw(useRaw, readUseRaw, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UseRaw\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
}
