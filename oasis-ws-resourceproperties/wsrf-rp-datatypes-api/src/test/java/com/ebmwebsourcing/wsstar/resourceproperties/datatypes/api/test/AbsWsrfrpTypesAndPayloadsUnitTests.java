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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.easycommons.xml.XMLPrettyPrinter;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpReader;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpWriter;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.implementor.WsrfrpModelFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;

public abstract class AbsWsrfrpTypesAndPayloadsUnitTests extends TestCase {
	
	protected boolean isDebug = false;
	
	private WsrfrpFactory factory;
	protected WsrfrpModelFactory modelFactoryImpl;
	private WsrfrpReader reader;
	private WsrfrpWriter writer;
	
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		this.initRefinedWsrfrpFactory();		
		this.initRequiredDependencyModelFactories();
	}
	
	/**
	 * provide an instance of your "WsrfrpModelFactory" model implementation class.
	 * The body must content something like :
	 * 
	 * 				"this.modelFactoryImpl = new WsrfrpModelFactoryImpl();"
	 * 
	 * where "WsrfrpModelFactoryImpl" is a class which implements "WsrfrpModelFactory".
	 * 
	 */	
	protected abstract void setWsrfrpModelFactory();
	
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
	public void initRefinedWsrfrpFactory(){	
		if (this.modelFactoryImpl == null)
			this.setWsrfrpModelFactory();			
		
		assertNotNull(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"the \"WsrfrpModelFactory\" implementation Object has not been set.\n" +
				"Please provide an instance of \"WsrfrpModelFactory\" implementation class", modelFactoryImpl);
		
		if (this.factory == null)		
			this.factory = RefinedWsrfrpFactory.getInstance(this.modelFactoryImpl);			
		if (this.reader == null)
			this.reader = this.factory.getWsrfrpReader();
		if (this.writer == null)
			this.writer = this.factory.getWsrfrpWriter();	
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
		
	/**
	 * Check a given (provided by the "WsrfrpReader") {@link QName}
	 * GetResourceProperty object against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkGetResourceProperty(QName expectedResourceName,
			QName toCheckResourceName, boolean isDebug){		
	
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckResourceName requested resource property : " + 
					toCheckResourceName +
					"\n[DEBUG] --> expectedResourceName requested resource property : " + 
					expectedResourceName + "\n");

		// ~~~~~~~~~ Check requested termination time ~~~~~~~~ 
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"payload have different requested resource property QName values\n" +
				"\t(-> toCheckResourceProperty requested QName : " + toCheckResourceName  +
				"\n\t-> expectedResourceProperty requested QName : " + expectedResourceName, 
				(toCheckResourceName != null && toCheckResourceName.equals(expectedResourceName)));
				
		return true;		
	}
	
		
	/**
	 * Check a given (provided by the "WsrfrpReader") {@link GetResourcePropertyResponse} object
	 * 	 against a reference one
	 * 
	 * @param expectedResponse
	 * @param toCheckResponse
	 */
	protected static boolean checkGetResourcePropertyResponse(GetResourcePropertyResponse expectedResponse,
			GetResourcePropertyResponse toCheckResponse, boolean isDebug){		
	
		// ~~~~~~~ check Resource Property value equality ~~~~~~~~ 
		
		List<Element> toCheckPropertyValues = toCheckResponse.getPropertyValue(),
		expectedPropertyValues = expectedResponse.getPropertyValue();
		
		int expectedRPValuesCount = (expectedPropertyValues != null)?expectedPropertyValues.size():-1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"ResourceProperty has different Element values\n" +
				"\t(-> toCheckRPValues count : " + ((toCheckPropertyValues != null)?toCheckPropertyValues.size():"none") +
				"\n\t-> expectedRPValues count : " + ((expectedRPValuesCount==-1)?"none": expectedRPValuesCount) + ")",
				(toCheckPropertyValues == null && expectedPropertyValues == null) ||
				toCheckPropertyValues.size() == expectedRPValuesCount);
		
		String toCheckPropertyValueItemAsString, expectedPropertyValueItemAsString;
		
		for (int i = 0; i < expectedRPValuesCount; i++) {
			
			toCheckPropertyValueItemAsString = (toCheckPropertyValues != null)?	WsaUnitTestsUtils.formatToComparison(toCheckPropertyValues.get(i)):null;
					
			expectedPropertyValueItemAsString = (expectedPropertyValues != null)? WsaUnitTestsUtils.formatToComparison(expectedPropertyValues.get(i)): null;	

			if (isDebug) {		
				System.out.println("[DEBUG] --> toCheckPropertyValue : " + toCheckPropertyValues +
						"\n\t(nodeAsString = " + toCheckPropertyValueItemAsString + ")"+
						"\n[DEBUG] --> expectedPropertyValue : " + expectedPropertyValues + 
						"\n\t(nodeAsString = " + expectedPropertyValueItemAsString + ")\n");
			}
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
					"ResourceProperty values are different \n" +
					"\t(-> toCheckResourceProperty value formatted to comparison is : " + 
					((toCheckPropertyValues != null)?toCheckPropertyValues:null)+
					"\n\t(nodesAsString = " + toCheckPropertyValueItemAsString + ")"+
					"\n\t-> expectedResourceProperty value formatted to comparison is : " +
					((expectedPropertyValues!= null)?expectedPropertyValues:null) + ")"+
					"\n\t(nodesAsString = " + expectedPropertyValueItemAsString + ")\n",
					((toCheckPropertyValueItemAsString == expectedPropertyValueItemAsString ))||
					((expectedPropertyValueItemAsString != null) && (toCheckPropertyValueItemAsString != null) && 
							toCheckPropertyValueItemAsString.equals(expectedPropertyValueItemAsString)));	
		}

		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsrfrpReader") {@link UpdateType} object
	 * 	 against a reference one
	 * 
	 * @param expectedUpdateObj
	 * @param toCheckUpdateObj
	 */
	protected static boolean checkUpdateType(UpdateType expectedUpdateObj,
			UpdateType toCheckUpdateObj, boolean isDebug){		
	
		// ~~~~~~~ check properties equality ~~~~~~~~ 
		
		List<Element> toCheckProperties = toCheckUpdateObj.getUpdateContent(),
		expectedProperties = expectedUpdateObj.getUpdateContent();
		
		int expectedPropertiesCount = (expectedProperties != null)?expectedProperties.size():-1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"UpdateType objects have different Element lists\n" +
				"\t(-> toCheckProperties count : " + ((toCheckProperties != null)?toCheckProperties.size():"none") +
				"\n\t-> expectedProperties count : " + ((expectedPropertiesCount==-1)?"none": expectedPropertiesCount) + ")",
				(toCheckProperties == null && expectedProperties == null) ||
				toCheckProperties.size() == expectedPropertiesCount);
		
		String toCheckPropertyItemAsString, expectedPropertyItemAsString;
		
		for (int i = 0; i < expectedPropertiesCount; i++) {
			
			toCheckPropertyItemAsString = (toCheckProperties != null)?	WsaUnitTestsUtils.formatToComparison(toCheckProperties.get(i)):null;
					
			expectedPropertyItemAsString = (expectedProperties != null)? WsaUnitTestsUtils.formatToComparison(expectedProperties.get(i)): null;	

			if (isDebug) {		
				System.out.println("[DEBUG] --> toCheckProperties : " + toCheckProperties +
						"\n\t(nodeAsString = " + toCheckPropertyItemAsString + ")"+
						"\n[DEBUG] --> expectedProperties : " + expectedProperties + 
						"\n\t(nodeAsString = " + expectedPropertyItemAsString + ")\n");
			}
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
					"Properties lists are different \n" +
					"\t(-> toCheckProperties formatted to comparison is : " + 
					((toCheckProperties != null)?toCheckProperties:null)+
					"\n\t(nodesAsString = " + toCheckPropertyItemAsString + ")"+
					"\n\t-> expectedProperties formatted to comparison is : " +
					((expectedProperties!= null)?expectedProperties:null) + ")"+
					"\n\t(nodesAsString = " + expectedPropertyItemAsString + ")\n",
					((toCheckPropertyItemAsString == expectedPropertyItemAsString ))||
					((expectedPropertyItemAsString != null) && (toCheckPropertyItemAsString != null) && 
							toCheckPropertyItemAsString.equals(expectedPropertyItemAsString)));	
		}

		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsrfrpReader") {@link UpdateResourceProperties} object
	 * 	 against a reference one
	 * 
	 * @param expectedPayload
	 * @param toCheckPayload
	 */
	protected static boolean checkUpdateResourceProperties(UpdateResourceProperties expectedPayload,
			UpdateResourceProperties toCheckPayload, boolean isDebug){		
	
		// ~~~~~~~ check properties equality ~~~~~~~~ 
		
		UpdateType toCheckProperties = toCheckPayload.getUpdate(),
		expectedProperties = expectedPayload.getUpdate();
		
		return AbsWsrfrpTypesAndPayloadsUnitTests.checkUpdateType(expectedProperties, toCheckProperties, isDebug);						
	}	
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UpdateResourcePropertiesResponse} object
	 * 	 against a reference one
	 */
	protected static boolean checkUpdateResourcePropertiesResponse(UpdateResourcePropertiesResponse expectedResponsePayload,
			UpdateResourcePropertiesResponse toCheckResponsePayload, boolean isDebug){		
				
		// ~~~~~~~ check UnsubscribeResponse equality (~~~~~~~~ 			
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckResponsePayload : " +  ((toCheckResponsePayload !=null)?toCheckResponsePayload.toString():"null") +
					"\n[DEBUG] --> expectedResponsePayload : " + ((expectedResponsePayload!=null)?expectedResponsePayload.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFBF_SPECIFICATION_NAME) +
				"UpdateResourcePropertiesResponse are \n" +
				"\t(-> toCheckResponsePayload : " + ((toCheckResponsePayload!=null)?toCheckResponsePayload.toString():"null") +
				"\n\t-> expectedResponsePayload : " + ((expectedResponsePayload!=null)?expectedResponsePayload.toString():"null")+ ")" , 
				(toCheckResponsePayload != null && expectedResponsePayload != null));

		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WsrfrpReader") {@link ResourcePropertyValueChangeNotificationType} object
	 * 	 against a reference one
	 * 
	 * @param expectedNotif
	 * @param toCheckNotif
	 */
	protected static boolean checkResourcePropertyValueChangeNotificationType(ResourcePropertyValueChangeNotificationType expectedNotif,
			ResourcePropertyValueChangeNotificationType toCheckNotif, boolean isDebug){		
	
		// ~~~~~~~ check Notification "NewValues" value equality ~~~~~~~~ 		
		ResourcePropertyValueChangeNotificationType.NewValues expectedNewValues = expectedNotif.getNewValues(),
			toCheckNewValues = toCheckNotif.getNewValues();
				
		List<Element> toCheckNewValueList = toCheckNewValues.getValues(),
		expectedNewValueList = expectedNewValues.getValues();
		
		int expectedNewValuesListCount = (expectedNewValueList != null)?expectedNewValueList.size():-1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"NewValues have different Element values\n" +
				"\t(-> toCheckNewValues count : " + ((toCheckNewValueList != null)?toCheckNewValueList.size():"none") +
				"\n\t-> expectedNewValues count : " + ((expectedNewValuesListCount==-1)?"none": expectedNewValuesListCount) + ")",
				(toCheckNewValueList != null && expectedNewValueList != null) ||
				toCheckNewValueList.size() == expectedNewValuesListCount);
		
		String toCheckNewValueItemAsString, expectedNewValueItemAsString;
		
		for (int i = 0; i < expectedNewValuesListCount; i++) {
			
			toCheckNewValueItemAsString = (toCheckNewValueList != null)?WsaUnitTestsUtils.formatToComparison(toCheckNewValueList.get(i)):null;
					
			expectedNewValueItemAsString = (expectedNewValueList != null)? WsaUnitTestsUtils.formatToComparison(expectedNewValueList.get(i)): null;	

			if (isDebug) {		
				System.out.println("[DEBUG] --> toCheckNewValue : " + toCheckNewValueList +
						"\n\t(nodeAsString = " + toCheckNewValueItemAsString + ")"+
						"\n[DEBUG] --> expectedNewValue : " + expectedNewValueList + 
						"\n\t(nodeAsString = " + expectedNewValueItemAsString + ")\n");
			}
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
					"NewValues values are different \n" +
					"\t(-> toCheckNewValue item formatted to comparison is : " + 
					((toCheckNewValueList != null)?toCheckNewValueList:null)+
					"\n\t(nodesAsString = " + toCheckNewValueItemAsString + ")"+
					"\n\t-> expectedNewValue item formatted to comparison is : " +
					((expectedNewValueList!= null)?expectedNewValueList:null) + ")"+
					"\n\t(nodesAsString = " + expectedNewValueItemAsString + ")\n",
					((toCheckNewValueItemAsString == expectedNewValueItemAsString ))||
					((expectedNewValueItemAsString != null) && (toCheckNewValueItemAsString != null) && 
							toCheckNewValueItemAsString.equals(expectedNewValueItemAsString)));	
		}
		// ~~~~~~~ check Notification "OldValues" value equality ~~~~~~~~ 		
		ResourcePropertyValueChangeNotificationType.OldValues expectedOldValues = expectedNotif.getOldValues(),
			toCheckOldValues = toCheckNotif.getOldValues();
		
		List<Element> toCheckOldValueList = toCheckOldValues.getValues(),
		expectedOldValueList = expectedOldValues.getValues();
		
		int expectedOldValuesListCount = (expectedOldValueList != null)?expectedOldValueList.size():-1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"NewValues have different Element values\n" +
				"\t(-> toCheckNewValues count : " + ((toCheckOldValueList != null)?toCheckOldValueList.size():"none") +
				"\n\t-> expectedNewValues count : " + ((expectedOldValuesListCount==-1)?"none": expectedOldValuesListCount) + ")",
				(toCheckOldValueList == null && expectedOldValueList == null) ||
				toCheckOldValueList.size() == expectedOldValuesListCount);
		
		String toCheckOldValueItemAsString, expectedOldValueItemAsString;
		
		for (int i = 0; i < expectedOldValuesListCount; i++) {
			
			toCheckOldValueItemAsString = (toCheckOldValueList != null)?WsaUnitTestsUtils.formatToComparison(toCheckOldValueList.get(i)):null;
					
			expectedOldValueItemAsString = (expectedOldValueList != null)? WsaUnitTestsUtils.formatToComparison(expectedOldValueList.get(i)): null;	

			if (isDebug) {		
				System.out.println("[DEBUG] --> toCheckOldValue : " + toCheckOldValueList +
						"\n\t(nodeAsString = " + toCheckOldValueItemAsString + ")"+
						"\n[DEBUG] --> expectedOldValue : " + expectedOldValueList + 
						"\n\t(nodeAsString = " + expectedOldValueItemAsString + ")\n");
			}
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
					"OldValues values are different \n" +
					"\t(-> toCheckOldValue item formatted to comparison is : " + 
					((toCheckOldValueList != null)?toCheckOldValueList:null)+
					"\n\t(nodesAsString = " + toCheckOldValueItemAsString + ")"+
					"\n\t-> expectedOldValue item formatted to comparison is : " +
					((expectedOldValueList!= null)?expectedOldValueList:null) + ")"+
					"\n\t(nodesAsString = " + expectedOldValueItemAsString + ")\n",
					((toCheckOldValueItemAsString == expectedOldValueItemAsString ))||
					((expectedOldValueItemAsString != null) && (toCheckOldValueItemAsString != null) && 
							toCheckOldValueItemAsString.equals(expectedOldValueItemAsString)));	
		}
		
		return true;		
	}	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/**
	 * Unit Test for WsrfrpWriter : write the created "GetResourceProperty" Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link QName} value of the  "GetResourceProperty"
	 * @throws WsrfrpException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadGetResourceProperty() throws WsrfrpException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Write-to-DOM and read-from-DOM GetResourceProperty\" ~~~~~~~ \n");				
		
		// ~~~~ create an default SetTerminationTime payload and write it to DOM document ~~~~
		QName propertyName = new QName("http://docs.oasis-open.org/wsn/t-1","TopicSet");
		
		Document payloadAsDOM = this.writer.writeGetResourcePropertyAsDOM(propertyName);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
					WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, this.getClass(), this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created payload from its DOM Document representation
		QName readPropertyName = this.reader.readGetResourceProperty(payloadAsDOM);		
		
		AbsWsrfrpTypesAndPayloadsUnitTests.checkGetResourceProperty(propertyName, readPropertyName,this.isDebug );		
		
		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
				
		System.out.println("\n\t OK, unit test \"Write-to-DOM and read-from-DOM GetResourceProperty\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}		
	
	/**
	 * Unit Test for WsrfrpFactory : create a {@link GetResourcePropertyResponse} Object
	 * Unit Test for WsrfrpWriter : write the created {@link GetResourcePropertyResponse} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link GetResourcePropertyResponse} object
	 * @throws WsrfrpException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadGetResourcePropertyResponse() throws WsrfrpException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM GetResourcePropertyResponse\" ~~~~~~~ \n");
		
		// ~~~~ create an default GetResourcePropertyResponse and write it to DOM document ~~~~ 	
		GetResourcePropertyResponse payload = this.factory.createGetResourcePropertyResponse();
		
		List<Element> propertyValues = new ArrayList<Element>();
		Element valAsElement = WsrfrpUnitTestsUtilsTests.createTopicSetPropertyValue("/SupportedTopicsSet.xml");
		if (isDebug){
			System.out.println("[DEBUG] propertyValues values : \n" + XMLPrettyPrinter.prettyPrint(valAsElement.getOwnerDocument()));
		}			
		propertyValues.add(valAsElement);
		
		payload.setPropertyValue(propertyValues);
		
		Document payloadAsDOM = this.writer.writeGetResourcePropertyResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
					WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, GetResourcePropertyResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created GetResourcePropertyResponse from its DOM Document representation
		GetResourcePropertyResponse readPayload = this.reader.readGetResourcePropertyResponse(payloadAsDOM);		

		AbsWsrfrpTypesAndPayloadsUnitTests.checkGetResourcePropertyResponse(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM GetResourcePropertyResponse\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}		
	
	/**
	 * Unit Test for WsrfrpFactory : create a {@link UpdateResourceProperties} Object
	 * Unit Test for WsrfrpWriter : write the created {@link UpdateResourceProperties} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link UpdateResourceProperties} object
	 * @throws WsrfrpException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUpdateResourceProperties() throws WsrfrpException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UpdateResourceProperties\" ~~~~~~~ \n");
		
		// ~~~~ create an default UpdateResourceProperties and write it to DOM document ~~~~ 	
		List<Element> content = WsrfrpUnitTestsUtilsTests.createDefaultProperties();		
		UpdateType properties = this.factory.createUpdateType(content);
		
		UpdateResourceProperties payload = this.factory.createUpdateResourceProperties(properties);				
		Document payloadAsDOM = this.writer.writeUpdateResourcePropertiesAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
					WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, UpdateResourceProperties.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created UpdateResourceProperties object from its DOM Document representation
		UpdateResourceProperties readPayload = this.reader.readUpdateResourceProperties(payloadAsDOM);		

		AbsWsrfrpTypesAndPayloadsUnitTests.checkUpdateResourceProperties(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UpdateResourceProperties\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsrfrpFactory : create a {@link UpdateResourcePropertiesResponse} Object
	 * Unit Test for WsrfrpWriter : write the created {@link UpdateResourcePropertiesResponse} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link UpdateResourcePropertiesResponse} object
	 * @throws WsrfrpException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUpdateResourcePropertiesResponse() throws WsrfrpException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ Unit test \"Create, Write-to-DOM and read-from-DOM UpdateResourcePropertiesResponse\" ~~~~~~~ \n");
		
		// ~~~~ create an default UpdateResourceProperties and write it to DOM document ~~~~ 	
		UpdateResourcePropertiesResponse payload = this.factory.createUpdateResourcePropertiesResponse();				
		Document payloadAsDOM = this.writer.writeUpdateResourcePropertiesResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
					WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, UpdateResourcePropertiesResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created UpdateResourcePropertiesResponse object from its DOM Document representation
		UpdateResourcePropertiesResponse readPayload = this.reader.readUpdateResourcePropertiesResponse(payloadAsDOM);		

		AbsWsrfrpTypesAndPayloadsUnitTests.checkUpdateResourcePropertiesResponse(payload, readPayload, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UpdateResourcePropertiesResponse\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsrfrpFactory : create a {@link ResourcePropertyValueChangeNotificationType} Object
	 * Unit Test for WsrfrpWriter : write the created {@link ResourcePropertyValueChangeNotificationType} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link ResourcePropertyValueChangeNotificationType} object
	 * @throws WsrfrpException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadResourcePropertyValueChangeNotificationType() throws WsrfrpException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ Unit test \"Create, Write-to-DOM and read-from-DOM ResourcePropertyValueChangeNotificationType\" ~~~~~~~ \n");
		
		// ~~~~ create an default ResourcePropertyValueChangeNotificationType and write it to DOM document ~~~~ 			
		
		Element newValAsElt = WsrfrpUnitTestsUtilsTests.createTopicSetPropertyValue("/NewSupportedTopicsSet.xml");		
		if (isDebug){
			System.out.println("[DEBUG] NewValues values : \n" + XMLPrettyPrinter.prettyPrint(newValAsElt.getOwnerDocument()));
		}
		ResourcePropertyValueChangeNotificationType.NewValues newValues = 
			this.factory.createResourcePropertyValueChangeNotificationTypeNewValues(newValAsElt);
		
		ResourcePropertyValueChangeNotificationType notifMsg = this.factory.createResourcePropertyValueChangeNotificationType(newValues);				
		
		Element oldValuesAsElt = WsrfrpUnitTestsUtilsTests.createTopicSetPropertyValue("/SupportedTopicsSet.xml");
		
		if (isDebug){
			System.out.println("[DEBUG] OldValues values : \n" + XMLPrettyPrinter.prettyPrint(oldValuesAsElt.getOwnerDocument()));
		}
		ResourcePropertyValueChangeNotificationType.OldValues oldValues = 
			this.factory.createResourcePropertyValueChangeNotificationTypeOldValues((Element)oldValuesAsElt.cloneNode(true));
		
		notifMsg.setOldValues(oldValues);		
		
		Document notifMsgAsDOM = this.writer.writeResourcePropertyValueChangeNotificationTypeAsDOM(notifMsg);		
		if (isDebug){
			System.out.println("[DEBUG] notifMessage as DOM values : \n" + XMLPrettyPrinter.prettyPrint(notifMsgAsDOM));
		}
		try {
			WsaUnitTestsUtils.validateResult(notifMsgAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
					WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, ResourcePropertyValueChangeNotificationType.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created ResourcePropertyValueChangeNotificationType object from its DOM Document representation
		ResourcePropertyValueChangeNotificationType readNotifMsg = this.reader.readResourcePropertyValueChangeNotificationType(notifMsgAsDOM);		

		AbsWsrfrpTypesAndPayloadsUnitTests.checkResourcePropertyValueChangeNotificationType(notifMsg, readNotifMsg, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM ResourcePropertyValueChangeNotificationType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
}
