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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.AbsWsrfbfTypesUnitTests;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidModificationFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidResourcePropertyQNameFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType.CurrentValue;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType.RequestedValue;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UnableToModifyResourcePropertyFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesRequestFailedFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpReader;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.WsrfrpWriter;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.implementor.WsrfrpModelFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.refinedabstraction.RefinedWsrfrpFactory;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpFaultMessageContentConstants;

public abstract class AbsWsrfrpFaultsTypesUnitTests extends TestCase {

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
	 * Check a given (provided by the "WsrfrpReader") {@link InvalidResourcePropertyQNameFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkInvalidResourcePropertyQNameFaultType(
			InvalidResourcePropertyQNameFaultType expectedFault,
			InvalidResourcePropertyQNameFaultType toCheckFault,boolean isDebug){		

		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, isDebug);		
	}

	/**
	 * Check a given (provided by the "WsrfrpReader") {@link ResourcePropertyChangeFailureType} object
	 * 	 against a reference one (common to all WS-Resource Faults) 
	 */
	private static boolean checkChangeFailurePart(ResourcePropertyChangeFailureType expectedChangeFailure,
			ResourcePropertyChangeFailureType toCheckChangeFalure, boolean isDebug){

		boolean result = false;

		// ~~~~~~~ Check "isRestored" part ~~~~~
		boolean toCheckIsRestored = toCheckChangeFalure.isRestored(),
		expectedIsRestored = expectedChangeFailure.isRestored();
		if (isDebug) {		
			System.out.println("[DEBUG] --> toCheckIsRestore value : " + toCheckIsRestored + 
					"\n[DEBUG] --> expectedIsRestore value : " + expectedIsRestored + ")\n");
		}

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"ChangeFailure fields have different \"isRestored\" attributs values : \n" +
				"\t(-> toCheckIsRestore value : " + toCheckIsRestored +
				"\n\t-> expectedIsRestore value : " + expectedIsRestored + ")",
				(toCheckIsRestored && expectedIsRestored) || (!toCheckIsRestored && !expectedIsRestored));

		// ~~~~~~~ Check CurrentValue part ~~~~~~
		CurrentValue expectedCurrentValue = expectedChangeFailure.getCurrentValue(),
		toCheckCurrentValue = toCheckChangeFalure.getCurrentValue();

		List<Element> toCheckCurrentPropValues = toCheckCurrentValue.getAny(),	expectedCurrentPropValues = expectedCurrentValue.getAny();

		int expectedCurrentPropValuesCount = (expectedCurrentPropValues != null)?expectedCurrentPropValues.size():-1;

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"Current ResourceProperty have different Element values\n" +
				"\t(-> toCheckCurrentPropValues count : " + ((toCheckCurrentPropValues != null)?toCheckCurrentPropValues.size():"none") +
				"\n\t-> expectedCurrentPropValues count : " + ((expectedCurrentPropValuesCount==-1)?"none": expectedCurrentPropValuesCount) + ")",
				(toCheckCurrentPropValues == null && expectedCurrentPropValues == null) ||
				toCheckCurrentPropValues.size() == expectedCurrentPropValuesCount);

		String toCheckCurrentPropValueItemAsString, expectedCurrentPropValueItemAsString;

		for (int i = 0; i < expectedCurrentPropValuesCount; i++) {

			toCheckCurrentPropValueItemAsString = (toCheckCurrentPropValues != null)?	WsaUnitTestsUtils.formatToComparison(toCheckCurrentPropValues.get(i)):null;

			expectedCurrentPropValueItemAsString = (expectedCurrentPropValues != null)? WsaUnitTestsUtils.formatToComparison(expectedCurrentPropValues.get(i)): null;	

			if (isDebug) {		
				System.out.println("[DEBUG] --> toCheckCurrentValue : " + toCheckCurrentPropValues +
						"\n\t(nodeAsString = " + toCheckCurrentPropValueItemAsString + ")"+
						"\n[DEBUG] --> expectedCurrentValue : " + expectedCurrentPropValues + 
						"\n\t(nodeAsString = " + expectedCurrentPropValueItemAsString + ")\n");
			}

			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
					"Current values are different \n" +
					"\t(-> toCheckCurrentValue formatted to comparison is : " + 
					((toCheckCurrentPropValues != null)?toCheckCurrentPropValues:null)+
					"\n\t(nodesAsString = " + toCheckCurrentPropValueItemAsString + ")"+
					"\n\t-> expectedCurrentValue formatted to comparison is : " +
					((expectedCurrentPropValues!= null)?expectedCurrentPropValues:null) + ")"+
					"\n\t(nodesAsString = " + expectedCurrentPropValueItemAsString + ")\n",
					((toCheckCurrentPropValueItemAsString == expectedCurrentPropValueItemAsString ))||
					((expectedCurrentPropValueItemAsString != null) && (toCheckCurrentPropValueItemAsString != null) && 
							toCheckCurrentPropValueItemAsString.equals(expectedCurrentPropValueItemAsString)));	
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// ~~~~~~~ Check RequestedValue part ~~~~~
		RequestedValue expectedRequestedValue = expectedChangeFailure.getRequestedValue(),
		toCheckRequestedValue = toCheckChangeFalure.getRequestedValue();

		List<Element> toCheckRequestedPropValues = toCheckRequestedValue.getAny(), expectedRequestedPropValues = expectedRequestedValue.getAny();

		int expectedRequestedPropValuesCount = (expectedRequestedPropValues != null)?expectedRequestedPropValues.size():-1;

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
				"Requested ResourceProperty have different Element values\n" +
				"\t(-> toCheckRequestedPropValues count : " + ((toCheckRequestedPropValues != null)?toCheckRequestedPropValues.size():"none") +
				"\n\t-> expectedRequestedPropValues count : " + ((expectedRequestedPropValuesCount==-1)?"none": expectedRequestedPropValuesCount) + ")",
				(toCheckRequestedPropValues == null && expectedRequestedPropValues == null) ||
				toCheckRequestedPropValues.size() == expectedRequestedPropValuesCount);

		String toCheckRequestedPropValueItemAsString, expectedRequestedPropValueItemAsString;

		for (int i = 0; i < expectedRequestedPropValuesCount; i++) {

			toCheckRequestedPropValueItemAsString = (toCheckRequestedPropValues != null)?	WsaUnitTestsUtils.formatToComparison(toCheckRequestedPropValues.get(i)):null;

			expectedRequestedPropValueItemAsString = (expectedRequestedPropValues != null)? WsaUnitTestsUtils.formatToComparison(expectedRequestedPropValues.get(i)): null;	

			if (isDebug) {		
				System.out.println("[DEBUG] --> toCheckRequestedValue : " + toCheckRequestedPropValues +
						"\n\t(nodeAsString = " + toCheckRequestedPropValueItemAsString + ")"+
						"\n[DEBUG] --> expectedRequestedValue : " + expectedRequestedPropValues + 
						"\n\t(nodeAsString = " + expectedRequestedPropValueItemAsString + ")\n");
			}

			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) +
					"Requested values are different \n" +
					"\t(-> toCheckRequestedValue formatted to comparison is : " + 
					((toCheckRequestedPropValues != null)?toCheckRequestedPropValues:null)+
					"\n\t(nodesAsString = " + toCheckRequestedPropValueItemAsString + ")"+
					"\n\t-> expectedRequestedValue formatted to comparison is : " +
					((expectedRequestedPropValues!= null)?expectedRequestedPropValues:null) + ")"+
					"\n\t(nodesAsString = " + expectedRequestedPropValueItemAsString + ")\n",
					((toCheckRequestedPropValueItemAsString == expectedRequestedPropValueItemAsString ))||
					((expectedRequestedPropValueItemAsString != null) && (toCheckRequestedPropValueItemAsString != null) && 
							toCheckRequestedPropValueItemAsString.equals(expectedRequestedPropValueItemAsString)));	
		}

		result = true;
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~		

		return result;
	}


	/**
	 * Check a given (provided by the "WsrfrpReader") {@link InvalidModificationFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkInvalidModificationFaultType(
			InvalidModificationFaultType expectedFault,
			InvalidModificationFaultType toCheckFault,boolean isDebug){		

		boolean interRes = false ;

		// ~~~~~~~ check ResourcePropertiesChangeFailure part ~~~~~
		ResourcePropertyChangeFailureType expectedChangeFailure = expectedFault.getResourcePropertyChangeFailure(),
		toCheckChangeFailure = toCheckFault.getResourcePropertyChangeFailure();

		interRes = AbsWsrfrpFaultsTypesUnitTests.checkChangeFailurePart(expectedChangeFailure, toCheckChangeFailure, isDebug);

		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return interRes && AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, isDebug);		
	}

	/**
	 * Check a given (provided by the "WsrfrpReader") {@link UnableToModifyResourcePropertyFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnableToModifyResourcePropertyFaultType(
			UnableToModifyResourcePropertyFaultType expectedFault,
			UnableToModifyResourcePropertyFaultType toCheckFault,boolean isDebug){		

		boolean interRes = false ;

		// ~~~~~~~ check ResourcePropertiesChangeFailure part ~~~~~
		ResourcePropertyChangeFailureType expectedChangeFailure = expectedFault.getResourcePropertyChangeFailure(),
		toCheckChangeFailure = toCheckFault.getResourcePropertyChangeFailure();

		interRes = AbsWsrfrpFaultsTypesUnitTests.checkChangeFailurePart(expectedChangeFailure, toCheckChangeFailure, isDebug);

		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return interRes && AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, isDebug);		
	}

	/**
	 * Check a given (provided by the "WsrfrpReader") {@link UpdateResourcePropertiesRequestFailedFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUpdateResourcePropertiesRequestFailedFaultType(
			UpdateResourcePropertiesRequestFailedFaultType expectedFault,
			UpdateResourcePropertiesRequestFailedFaultType toCheckFault,boolean isDebug){		

		boolean interRes = false ;

		// ~~~~~~~ check ResourcePropertiesChangeFailure part ~~~~~
		ResourcePropertyChangeFailureType expectedChangeFailure = expectedFault.getResourcePropertyChangeFailure(),
		toCheckChangeFailure = toCheckFault.getResourcePropertyChangeFailure();

		interRes = AbsWsrfrpFaultsTypesUnitTests.checkChangeFailurePart(expectedChangeFailure, toCheckChangeFailure, isDebug);

		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return interRes && AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, isDebug);		
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//	

	/**
	 * Unit Test for WsrfrpFactory : create a {@link InvalidResourcePropertyQNameFaultType} Object
	 * Unit Test for WsrfrpWriter : write the created {@link InvalidResourcePropertyQNameFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link InvalidResourcePropertyQNameFaultType} object
	 * @throws WsrfrpException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadInvalidResourcePropertyQNameFaultType() throws WsrfrpException {

		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM InvalidResourcePropertyQNameFaultType\" ~~~~~~~ \n");				

		try {
			// ~~~~ create an default InvalidResourcePropertyQNameFaultType fault and write it to DOM document ~~~~ 		
			InvalidResourcePropertyQNameFaultType defaultInvalidResourcePropertyQNameFaultType = 
				this.factory.createInvalidResourcePropertyQNameFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultInvalidResourcePropertyQNameFaultType,
					WsrfrpUnitTestsUtilsTests.createDefaultBaseFaultOriginator(),
					WsrfrpFaultMessageContentConstants.WsrfrpGetResourcePropertyFaultDescriptions.INVALID_RESOURCE_PROPERTY_QNAME_FAULT_DESC,
					Locale.ENGLISH,
					"WSRFRP-FAULT-1",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrfrp/errors"),
					null);

			Document invalidRPQNameFaultAsDOM = this.writer.writeInvalidResourcePropertyQNameFaultTypeAsDOM(defaultInvalidResourcePropertyQNameFaultType);
			try {
				WsaUnitTestsUtils.validateResult(invalidRPQNameFaultAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
						WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, InvalidResourcePropertyQNameFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously InvalidProducerPropertiesExpressionFaultType fault from its DOM Document representation
			InvalidResourcePropertyQNameFaultType readInvalidResourcePropertyQNameFault = 
				this.reader.readInvalidResourcePropertyQNameFaultType(invalidRPQNameFaultAsDOM);
			AbsWsrfrpFaultsTypesUnitTests.checkInvalidResourcePropertyQNameFaultType(defaultInvalidResourcePropertyQNameFaultType,
					readInvalidResourcePropertyQNameFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM InvalidResourcePropertyQNameFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}	

	/**
	 * Unit Test for WsrfrpFactory : create a {@link InvalidModificationFaultType} Object
	 * Unit Test for WsrfrpWriter : write the created {@link InvalidModificationFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link InvalidModificationFaultType} object
	 * @throws WsrfrpException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadInvalidModificationFaultType() throws WsrfrpException {

		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM InvalidModificationFaultType\" ~~~~~~~ \n");				

		try {
			// ~~~~ create an default InvalidModificationFaultType fault and write it to DOM document ~~~~ 		

			ResourcePropertyChangeFailureType defaultChangeFailure = this.factory.createResourcePropertyChangeFailureType(false);
			defaultChangeFailure.setCurrentValue(WsrfrpUnitTestsUtilsTests.createCurrentValues());
			defaultChangeFailure.setRequestedValue(WsrfrpUnitTestsUtilsTests.createRequestedValues());

			InvalidModificationFaultType defaultInvalidModificationFaultType = 
				this.factory.createInvalidModificationFaultType(new Date(),defaultChangeFailure);	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultInvalidModificationFaultType,
					WsrfrpUnitTestsUtilsTests.createDefaultBaseFaultOriginator(),
					WsrfrpFaultMessageContentConstants.WsrfrpUpdateResourcePropertiesFaultDescriptions.INVALID_MODIFICATION_FAULT_DESC,
					Locale.ENGLISH,
					"WSRFRP-FAULT-2",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrfrp/errors"),
					null);

			Document invalidModificationFaultAsDOM = this.writer.writeInvalidModificationFaultTypeAsDOM(defaultInvalidModificationFaultType);
			try {
				WsaUnitTestsUtils.validateResult(invalidModificationFaultAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
						WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, InvalidModificationFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously InvalidProducerPropertiesExpressionFaultType fault from its DOM Document representation
			InvalidModificationFaultType readInvalidModificationFault = 
				this.reader.readInvalidModificationFaultType(invalidModificationFaultAsDOM);

			AbsWsrfrpFaultsTypesUnitTests.checkInvalidModificationFaultType(defaultInvalidModificationFaultType,
					readInvalidModificationFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM InvalidModificationFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}

	/**
	 * Unit Test for WsrfrpFactory : create a {@link UnableToModifyResourcePropertyFaultType} Object
	 * Unit Test for WsrfrpWriter : write the created {@link UnableToModifyResourcePropertyFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link UnableToModifyResourcePropertyFaultType} object
	 * @throws WsrfrpException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnableToModifyResourcePropertyFaultType() throws WsrfrpException {

		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnableToModifyResourcePropertyFaultType\" ~~~~~~~ \n");				

		try {
			// ~~~~ create an default UnableToModifyResourcePropertyFaultType fault and write it to DOM document ~~~~ 		

			ResourcePropertyChangeFailureType defaultChangeFailure = this.factory.createResourcePropertyChangeFailureType(false);
			defaultChangeFailure.setCurrentValue(WsrfrpUnitTestsUtilsTests.createCurrentValues());
			defaultChangeFailure.setRequestedValue(WsrfrpUnitTestsUtilsTests.createRequestedValues());

			UnableToModifyResourcePropertyFaultType defaultUnableToModifyRPFaultType = 
				this.factory.createUnableToModifyResourcePropertyFaultType(new Date(),defaultChangeFailure);	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnableToModifyRPFaultType,
					WsrfrpUnitTestsUtilsTests.createDefaultBaseFaultOriginator(),
					WsrfrpFaultMessageContentConstants.WsrfrpUpdateResourcePropertiesFaultDescriptions.UNABLE_TO_MODIFY_RESOURCE_PROPERTY_FAULT_DESC,
					Locale.ENGLISH,
					"WSRFRP-FAULT-3",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrfrp/errors"),
					null);

			Document unableToModifyRPFaultAsDOM = this.writer.writeUnableToModifyResourcePropertyFaultTypeAsDOM(defaultUnableToModifyRPFaultType);
			try {
				WsaUnitTestsUtils.validateResult(unableToModifyRPFaultAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
						WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, UnableToModifyResourcePropertyFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnableToModifyResourcePropertyFaultType fault from its DOM Document representation
			UnableToModifyResourcePropertyFaultType readUnableToModifyRPFault = 
				this.reader.readUnableToModifyResourcePropertyFaultType(unableToModifyRPFaultAsDOM);

			AbsWsrfrpFaultsTypesUnitTests.checkUnableToModifyResourcePropertyFaultType(defaultUnableToModifyRPFaultType,
					readUnableToModifyRPFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnableToModifyResourcePropertyFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}

	/**
	 * Unit Test for WsrfrpFactory : create a {@link UpdateResourcePropertiesRequestFailedFaultType} Object
	 * Unit Test for WsrfrpWriter : write the created {@link UpdateResourcePropertiesRequestFailedFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link UpdateResourcePropertiesRequestFailedFaultType} object
	 * @throws WsrfrpException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUpdateResourcePropertiesRequestFailedFaultType() throws WsrfrpException {

		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UpdateResourcePropertiesRequestFailedFaultType\" ~~~~~~~ \n");				

		try {
			// ~~~~ create an default UpdateResourcePropertiesRequestFailedFaultType fault and write it to DOM document ~~~~ 					
			ResourcePropertyChangeFailureType defaultChangeFailure = this.factory.createResourcePropertyChangeFailureType(false);
			defaultChangeFailure.setCurrentValue(WsrfrpUnitTestsUtilsTests.createCurrentValues());
			defaultChangeFailure.setRequestedValue(WsrfrpUnitTestsUtilsTests.createRequestedValues());

			UpdateResourcePropertiesRequestFailedFaultType defaultUpdateRPRequestFailedFaultType = 
				this.factory.createUpdateResourcePropertiesRequestFailedFaultType(new Date(),defaultChangeFailure);	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUpdateRPRequestFailedFaultType,
					WsrfrpUnitTestsUtilsTests.createDefaultBaseFaultOriginator(),
					WsrfrpFaultMessageContentConstants.WsrfrpUpdateResourcePropertiesFaultDescriptions.UPDATE_RESOURCE_PROPERTIES_REQUEST_FAILED_FAULT_DESC,
					Locale.ENGLISH,
					"WSRFRP-FAULT-4",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrfrp/errors"),
					null);

			Document updateRPRequestFailedFaultAsDOM = this.writer.writeUpdateResourcePropertiesRequestFailedFaultTypeAsDOM(defaultUpdateRPRequestFailedFaultType);
			try {
				WsaUnitTestsUtils.validateResult(updateRPRequestFailedFaultAsDOM,WsrfrpUnitTestsUtilsTests.WSRFRP_XML_SCHEMAS_PATHS ,
						WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME, UnableToModifyResourcePropertyFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrpUnitTestsUtilsTests.WSRFRP_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UpdateResourcePropertiesRequestFailedFaultType fault from its DOM Document representation
			UpdateResourcePropertiesRequestFailedFaultType readUpdateRPRequestFailedFault = 
				this.reader.readUpdateResourcePropertiesRequestFailedFaultType(updateRPRequestFailedFaultAsDOM);

			AbsWsrfrpFaultsTypesUnitTests.checkUpdateResourcePropertiesRequestFailedFaultType(defaultUpdateRPRequestFailedFaultType,
					readUpdateRPRequestFailedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UpdateResourcePropertiesRequestFailedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
}
