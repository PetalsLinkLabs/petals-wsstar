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
package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.AbsWsrfbfTypesUnitTests;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ResourceNotDestroyedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ScheduledResourceTerminationRP;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationNotification;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTimeChangeRejectedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.UnableToSetTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlReader;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlWriter;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.implementor.WsrfrlModelFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.refinedabstraction.RefinedWsrfrlFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;

public abstract class AbsWsrfrlFaultsTypesAndPayloadsUnitTests extends TestCase {
	
	protected boolean isDebug = false;
	
	private WsrfrlFactory factory;
	protected WsrfrlModelFactory modelFactoryImpl;
	private WsrfrlReader reader;
	private WsrfrlWriter writer;
	
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		this.initRefinedWsrfrlFactory();		
		this.initRequiredDependencyModelFactories();
	}
	
	/**
	 * provide an instance of your "WsrfrlModelFactory" model implementation class.
	 * The body must content something like :
	 * 
	 * 				"this.modelFactoryImpl = new WsrfrlModelFactoryImpl();"
	 * 
	 * where "WsrfrlModelFactoryImpl" is a class which implements "WsrfrlModelFactory".
	 * 
	 */	
	protected abstract void setWsrfrlModelFactory();
	
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
	public void initRefinedWsrfrlFactory(){	
		if (this.modelFactoryImpl == null)
			this.setWsrfrlModelFactory();			
		
		assertNotNull(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"the \"WsrfrlModelFactory\" implementation Object has not been set.\n" +
				"Please provide an instance of \"WsrfrlModelFactory\" implementation class", modelFactoryImpl);
		
		if (this.factory == null)		
			this.factory = RefinedWsrfrlFactory.getInstance(this.modelFactoryImpl);			
		if (this.reader == null)
			this.reader = this.factory.getWsrfrlReader();
		if (this.writer == null)
			this.writer = this.factory.getWsrfrlWriter();	
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link ResourceNotDestroyedFaultType} object
	 * 	 against a reference one
	 * @param isDebug TODO
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkResourceNotDestroyedFaultType(ResourceNotDestroyedFaultType expectedFault,
			ResourceNotDestroyedFaultType toCheckFault, boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link TerminationTimeChangeRejectedFaultType} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkTerminationTimeChangeRejectedFaultType(TerminationTimeChangeRejectedFaultType expectedFault,
			TerminationTimeChangeRejectedFaultType toCheckFault, boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link UnableToSetTerminationTimeFaultType} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkUnableToSetTerminationTimeFaultType(UnableToSetTerminationTimeFaultType expectedFault,
			UnableToSetTerminationTimeFaultType toCheckFault, boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link SetTerminationTime} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkSetTerminationTime(SetTerminationTime	expectedPayload,
			SetTerminationTime toCheckPayload, boolean isDebug){		
	
		Date expectedTerminationTime =  expectedPayload.getRequestedTerminationTime(),
			toCheckTerminationTime = toCheckPayload.getRequestedTerminationTime();
		
		Duration expectedDuration = expectedPayload.getRequestedLifetimeDuration(),
			toCheckDuration = toCheckPayload.getRequestedLifetimeDuration();
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckPayload requested termination time : " + 
					((toCheckTerminationTime != null)?toCheckTerminationTime.toString() : null) +
					"\n[DEBUG] --> toCheckPayload requested lifetime duration : " + 
					((toCheckDuration != null)?toCheckDuration.toString() : null) +
					"\n\n[DEBUG] --> expectedPayload requested termination time : " + 
					((expectedTerminationTime != null)?expectedTerminationTime.toString() : null) +
					"\n[DEBUG] --> expectedPayload requested lifetime duration : " + 
					((expectedDuration != null)?expectedDuration.toString() : null) + "\n");

		// ~~~~~~~~~ Check requested termination time ~~~~~~~~ 
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"payload have different requested termination time values\n" +
				"\t(-> toCheckPayload requested termination time: " + ((toCheckTerminationTime != null)?toCheckTerminationTime.toString() : null) +
				"\n\t-> expectedPayload  requested termination time: " + ((expectedTerminationTime != null)?expectedTerminationTime.toString() : null), 
				(toCheckTerminationTime == expectedTerminationTime) ||
				(toCheckTerminationTime != null && toCheckTerminationTime.equals(expectedTerminationTime)));
		
		// ~~~~~~~~~ Check requested lifetime duration ~~~~~~~~
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"payload have different requested termination time values\n" +
				"\t(-> toCheckPayload lifetime duration : " + ((toCheckDuration != null)?toCheckDuration.toString() : null) +
				"\n\t-> expectedPayload lifetime duration : " + ((expectedDuration != null)?expectedDuration.toString(): null), 
				(toCheckDuration == expectedDuration) ||
				(toCheckDuration != null && toCheckDuration.equals(expectedDuration)));
		
		return true;		
	}
	
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link SetTerminationTimeResponse} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkSetTerminationTimeResponse(SetTerminationTimeResponse	expectedPayload,
			SetTerminationTimeResponse toCheckPayload, boolean isDebug){		
	
		Date expectedCurrentTime =  expectedPayload.getCurrentTime(),
			toCheckCurrentTime = toCheckPayload.getCurrentTime(),		
			expectedNewTerminationTime = expectedPayload.getNewTerminationTime(),
			toCheckNewTerminationTime = toCheckPayload.getNewTerminationTime();
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckPayload current time : " + 
					((toCheckCurrentTime != null)?toCheckCurrentTime.toString() : null) +
					"\n[DEBUG] --> toCheckPayload new termination time : " + 
					((toCheckNewTerminationTime != null)?toCheckNewTerminationTime.toString() : null) +
					"\n\n[DEBUG] --> expectedPayload current time : " + 
					((expectedCurrentTime != null)?expectedCurrentTime.toString() : null) +
					"\n[DEBUG] --> expectedPayload new termination time : " + 
					((expectedNewTerminationTime != null)?expectedNewTerminationTime.toString() : null) + "\n");
	
		// ~~~~~~~~~ Check both current time and new termination time ~~~~~~~~
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"payload have different content values\n" +
				"\t(-> toCheckPayload current time : " + ((toCheckCurrentTime != null)?toCheckCurrentTime.toString() : null) +
				"\n\t-> toCheckPayload new termination time : " + ((toCheckNewTerminationTime != null)?toCheckNewTerminationTime.toString() : null)+
				"\n\n\t-> expectedPayload current time : " + ((expectedCurrentTime != null)?expectedCurrentTime.toString() : null)+
				"\n\t-> expectedPayload new termination time : " + ((expectedNewTerminationTime != null)?expectedNewTerminationTime.toString() : null), 
				((toCheckCurrentTime == expectedCurrentTime) || toCheckCurrentTime.equals(expectedCurrentTime) &&
				(toCheckNewTerminationTime == expectedNewTerminationTime) || toCheckNewTerminationTime.equals(expectedNewTerminationTime)));
		
		return true;		
	}
	
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link Destroy} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkDestroy(Destroy expectedPayload,Destroy toCheckPayload, boolean isDebug){		
				
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckPayload payload : " +  toCheckPayload +
					"\n[DEBUG] --> expectedPayload payload : " + expectedPayload + "\n");

		// ~~~~~~~ trivial check : no null payloads ~~~~~~~~  
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"payload are different \n" +
				"\t(-> toCheckPayload : " + toCheckPayload +
				"\n\t-> expectedPayload : " + expectedPayload , 
				(toCheckPayload == expectedPayload) ||(toCheckPayload != null && expectedPayload != null));
		
		return true;		
	}
	
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link DestroyResponse} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkDestroyResponse(DestroyResponse expectedPayload,DestroyResponse toCheckPayload, boolean isDebug){		
				
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckPayload payload : " +  toCheckPayload +
					"\n[DEBUG] --> expectedPayload payload : " + expectedPayload + "\n");

		// ~~~~~~~ trivial check : no null payloads ~~~~~~~~  
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"payload are \n" +
				"\t(-> toCheckPayload : " + toCheckPayload +
				"\n\t-> expectedPayload : " + expectedPayload , 
				(toCheckPayload == expectedPayload) ||(toCheckPayload != null && expectedPayload != null));
		
		return true;		
	}
	
	
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link TerminationNotification} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkTerminationNotification(TerminationNotification expectedNotification,
			TerminationNotification toCheckNotification, boolean isDebug){		
	
		// ~~~~~~~ check TerminationTime equality ~~~~~~~~ 
		Date expectedTerminationTime =  expectedNotification.getTerminationTime(),
		toCheckTerminationTime = expectedNotification.getTerminationTime();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckNotification termination time : " +  ((toCheckTerminationTime!=null)?toCheckTerminationTime.toString():"null") +
					"\n[DEBUG] --> expectedNotification termination time : " + ((expectedTerminationTime!=null)?expectedTerminationTime.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"TerminationNotification termination times are \n" +
				"\t(-> toCheckNotification termination time : " + ((toCheckTerminationTime!=null)?toCheckTerminationTime.toString():"null") +
				"\n\t-> expectedNotification termination time : " + ((expectedTerminationTime!=null)?expectedTerminationTime.toString():"null") +")", 
				(expectedTerminationTime == toCheckTerminationTime) ||
				(toCheckTerminationTime != null && toCheckTerminationTime.equals(expectedTerminationTime)));

		// ~~~~~~~ check TerminationReason equality ~~~~~~~~ 
		
		Object toCheckTermReason = toCheckNotification.getTerminationReason(),
			expectedTermReason = expectedNotification.getTerminationReason();
		
		String toCheckReasonAsString = (toCheckTermReason != null && toCheckTermReason instanceof Element)?
				WsaUnitTestsUtils.formatToComparison((Element) toCheckTermReason):null;	
		String expectedReasonAsString = (expectedTermReason != null && expectedTermReason instanceof Element)?
				WsaUnitTestsUtils.formatToComparison((Element) expectedTermReason): null;	
				
		if (isDebug)		
			System.out.println("[DEBUG] --> toCheckTerminationReason : " + toCheckTermReason +
					"\n\t(nodesAsString = " + toCheckReasonAsString + ")"+
					"\n[DEBUG] --> expectedTerminationReason : " + expectedTermReason + 
					"\n\t(nodesAsString = " + expectedReasonAsString + ")\n");

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"TerminationNotification reason values are different \n" +
				"\t(-> toCheckNotification reason formatted to comparison is : " + 
				((toCheckTermReason != null)?toCheckTermReason:null)+
				"\n\t(nodesAsString = " + toCheckReasonAsString + ")"+
				"\n\t-> expectedNotification reason formatted to comparison is : " +
				((expectedTermReason!= null)?expectedTermReason:null) + ")"+
				"\n\t(nodesAsString = " + expectedReasonAsString + ")\n",
				((toCheckReasonAsString == expectedReasonAsString ))||
				((expectedReasonAsString != null) && (toCheckReasonAsString != null) && 
						toCheckReasonAsString.equals(expectedReasonAsString)));	
		return true;		
	}	

	/**
	 * Check a given (provided by the "WsrfrlReader") {@link CurrentTime} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkCurrentTime(CurrentTime expectedCurrentTime,
			CurrentTime toCheckCurrentTime, boolean isDebug){		
	
		// ~~~~~~~ check CurrentTime equality ~~~~~~~~ 
		Date expectedValue =  expectedCurrentTime.getValue(),
		toCheckValue = toCheckCurrentTime.getValue();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckCurrentTime value : " +  ((toCheckValue!=null)?toCheckValue.toString():"null") +
					"\n[DEBUG] --> expectedCurrentTime value : " + ((expectedValue!=null)?expectedValue.toString():"null") + "\n");
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"CurrentTime value are \n" +
				"\t(-> toCheckCurrentTime value : " + ((toCheckValue!=null)?toCheckValue.toString():"null") +
				"\n\t-> expectedCurrentTime value : " + ((expectedValue!=null)?expectedValue.toString():"null") , 
				(toCheckValue == expectedValue) ||(toCheckValue != null && toCheckValue.equals(expectedValue)));

	
		return true;		
	}	

	/**
	 * Check a given (provided by the "WsrfrlReader") {@link TerminationTime} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkTerminationTime(TerminationTime expectedTerminationTime,
			TerminationTime toCheckTerminationTime, boolean isDebug){		
	
		// ~~~~~~~ check TerminationTime equality ~~~~~~~~ 
		Date expectedValue =  expectedTerminationTime.getValue(),
		toCheckValue = toCheckTerminationTime.getValue();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckTerminationTime value : " +  ((toCheckValue!=null)?toCheckValue.toString():"null") +
					"\n[DEBUG] --> expectedTerminationTime value : " + ((expectedValue!=null)?expectedValue.toString():"null") + "\n");
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
				"TerminationTime value are \n" +
				"\t(-> toCheckTerminationTime value : " + ((toCheckValue!=null)?toCheckValue.toString():"null") +
				"\n\t-> expectedTerminationTime value : " + ((expectedValue!=null)?expectedValue.toString():"null") , 
				(toCheckValue == expectedValue) ||(toCheckValue != null && toCheckValue.equals(expectedValue)));

		return true;		
	}
	/**
	 * Check a given (provided by the "WsrfrlReader") {@link ScheduledResourceTerminationRP} object
	 * 	 against a reference one
	 * 
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkScheduledResourceTerminationRP(ScheduledResourceTerminationRP expectedSchResTemRP,
			ScheduledResourceTerminationRP toCheckSchResTemRP, boolean isDebug){		
	
		// ~~~~~~~ check CurrentTime equality ~~~~~~~~ 
		CurrentTime expectedCurrentTime =  expectedSchResTemRP.getCurrentTime(),
		toCheckCurrentTime = toCheckSchResTemRP.getCurrentTime();	
		
		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkCurrentTime(expectedCurrentTime, toCheckCurrentTime,isDebug);
		
//		if (WsrfUnitTestsUtils.isDebug)
//			System.out.println("[DEBUG] --> toCheckTerminationTime value : " +  ((toCheckValue!=null)?toCheckValue.toString():"null") +
//					"\n[DEBUG] --> expectedTerminationTime value : " + ((expectedValue!=null)?expectedValue.toString():"null") + "\n");
//		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) +
//				"TerminationTime value are \n" +
//				"\t(-> toCheckTerminationTime value : " + ((toCheckValue!=null)?toCheckValue.toString():"null") +
//				"\n\t-> expectedTerminationTime value : " + ((expectedValue!=null)?expectedValue.toString():"null") , 
//				(toCheckValue == expectedValue) ||(toCheckValue != null && toCheckValue.equals(expectedValue)));

		// ~~~~~~~ check TerminationTime equality ~~~~~~~~ 
		TerminationTime expectedTerminationTime =  expectedSchResTemRP.getTerminationTime(),
		toCheckTerminationTime = toCheckSchResTemRP.getTerminationTime();	
		
		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkTerminationTime(expectedTerminationTime, toCheckTerminationTime,isDebug);
		
		return true;		
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link ResourceNotDestroyedFaultType} Object
	 * Unit Test for WsrfrlWriter : write the created {@link ResourceNotDestroyedFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link ResourceNotDestroyedFaultType} object
	 * @throws WsrfrlException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadResourceNotDestroyedFaultType() throws WsrfrlException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM ResourceNotDestroyedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default ResourceNotDestroyed fault and write it to DOM document ~~~~ 		
			ResourceNotDestroyedFaultType defaultResourceNotDestroyedFault = this.factory.createResourceNotDestroyedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultResourceNotDestroyedFault,
					WsrfrlUnitTestsUtils.createDefaultBaseFaultOriginator(),
					"Resource not destroyed because locked",
					Locale.ENGLISH,
					"468",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrf/errors"),
					null);

			Document resourceNotDestroyedFaultAsDOM = this.writer.writeResourceNotDestroyedFaultTypeAsDOM(defaultResourceNotDestroyedFault);
			try {
				WsaUnitTestsUtils.validateResult(resourceNotDestroyedFaultAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
						WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, ResourceNotDestroyedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously created fault from its DOM Document representation
			ResourceNotDestroyedFaultType readResourceNotDestroyedFault = this.reader.readResourceNotDestroyedFaultType(resourceNotDestroyedFaultAsDOM);
			AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkResourceNotDestroyedFaultType(defaultResourceNotDestroyedFault, readResourceNotDestroyedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM ResourceNotDestroyedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link TerminationTimeChangeRejectedFaultType} Object
	 * Unit Test for WsrfrlWriter : write the created {@link TerminationTimeChangeRejectedFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link TerminationTimeChangeRejectedFaultType} object
	 * @throws WsrfrlException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadTerminationTimeChangeRejectedFaultType() throws WsrfrlException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TerminationTimeChangeRejectedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default TerminationTimeChangeRejected fault and write it to DOM document ~~~~ 		
			TerminationTimeChangeRejectedFaultType defaultTerminationTimeChangeRejectedFault = this.factory.createTerminationTimeChangeRejectedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultTerminationTimeChangeRejectedFault,
					WsrfrlUnitTestsUtils.createDefaultBaseFaultOriginator(),
					"New TerminationTime value must be not in the past !!",
					Locale.ENGLISH,
					"744",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrf/errors"),
					null);

			Document terminationTimeChangeRejectedFaultAsDOM = this.writer.writeTerminationTimeChangeRejectedFaultTypeAsDOM(defaultTerminationTimeChangeRejectedFault);
			try {
				WsaUnitTestsUtils.validateResult(terminationTimeChangeRejectedFaultAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
						WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, TerminationTimeChangeRejectedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously created fault from its DOM Document representation
			TerminationTimeChangeRejectedFaultType readTerminationTimeChangeRejectedFault = this.reader.readTerminationTimeChangeRejectedFaultType(terminationTimeChangeRejectedFaultAsDOM);
			AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkTerminationTimeChangeRejectedFaultType(defaultTerminationTimeChangeRejectedFault, readTerminationTimeChangeRejectedFault,this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TerminationTimeChangeRejectedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link UnableToSetTerminationTimeFaultType} Object
	 * Unit Test for WsrfrlWriter : write the created {@link UnableToSetTerminationTimeFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link UnableToSetTerminationTimeFaultType} object
	 * @throws WsrfrlException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnableToSetTerminationTimeFaultType() throws WsrfrlException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnableToSetTerminationTimeFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnableToSetTerminationTime fault and write it to DOM document ~~~~ 		
			UnableToSetTerminationTimeFaultType defaultUnableToSetTerminationTimeFault = this.factory.createUnableToSetTerminationTimeFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnableToSetTerminationTimeFault,
					WsrfrlUnitTestsUtils.createDefaultBaseFaultOriginator(),
					"TerminationTime value must not be after the current time !!",
					Locale.ENGLISH,
					"745",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrf/errors"),
					null);

			Document unableToSetTerminationTimeFaultAsDOM = this.writer.writeUnableToSetTerminationTimeFaultTypeAsDOM(defaultUnableToSetTerminationTimeFault);
			try {
				WsaUnitTestsUtils.validateResult(unableToSetTerminationTimeFaultAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
						WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, UnableToSetTerminationTimeFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously created fault from its DOM Document representation
			UnableToSetTerminationTimeFaultType readUnableToSetTerminationTimeFault = this.reader.readUnableToSetTerminationTimeFaultType(unableToSetTerminationTimeFaultAsDOM);
			AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkUnableToSetTerminationTimeFaultType(defaultUnableToSetTerminationTimeFault, readUnableToSetTerminationTimeFault,this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnableToSetTerminationTimeFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * 					SetTerminationTime with RequestedTerminationTime
	 * 
	 * Unit Test for WsrfrlFactory : create a {@link SetTerminationTime} Object
	 * Unit Test for WsrfrlWriter : write the created {@link SetTerminationTime} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link SetTerminationTime} object
	 * @throws WsrfrlException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadSetTerminationTimeWithReqTermTime() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM SetTerminationTime with RequestedTerminationTime\" ~~~~~~~ \n");				
		
		// ~~~~ create an default SetTerminationTime payload and write it to DOM document ~~~~ 		
		SetTerminationTime payload = this.factory.createSetTerminationTime(new Date());
		
		Document payloadAsDOM = this.writer.writeSetTerminationTimeAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, SetTerminationTime.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created payload from its DOM Document representation
		SetTerminationTime readSetTerminationTime = this.reader.readSetTerminationTime(payloadAsDOM);		
		
		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkSetTerminationTime(payload, readSetTerminationTime,this.isDebug );		
		
		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
				
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM SetTerminationTime with RequestedTerminationTime\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * 					SetTerminationTime with RequestedTerminationTime
	 * 
	 * Unit Test for WsrfrlFactory : create a {@link SetTerminationTime} Object
	 * Unit Test for WsrfrlWriter : write the created {@link SetTerminationTime} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link SetTerminationTime} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadSetTerminationTimeWithReqLifetimeDuration() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM SetTerminationTime with RequestedLifetimeDuration\" ~~~~~~~ \n");				
		try {
			// ~~~~ create an default SetTerminationTime payload and write it to DOM document ~~~~ 		
			SetTerminationTime payload = this.factory.createSetTerminationTime(DatatypeFactory.newInstance().newDuration(1800*1000));

			Document payloadAsDOM = this.writer.writeSetTerminationTimeAsDOM(payload);

			try {
				WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
						WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, SetTerminationTime.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously created payload from its DOM Document representation
			SetTerminationTime readSetTerminationTime = this.reader.readSetTerminationTime(payloadAsDOM);		

			AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkSetTerminationTime(payload, readSetTerminationTime,this.isDebug);		

			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM SetTerminationTime with RequestedLifetimeDuration\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link SetTerminationTimeResponse} Object
	 * Unit Test for WsrfrlWriter : write the created {@link SetTerminationTimeResponse} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link SetTerminationTimeResponse} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadSetTerminationTimeResponse() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM SetTerminationTimeResponse\" ~~~~~~~ \n");				
		// ~~~~ create an default SetTerminationTimeResponse payload and write it to DOM document ~~~~ 		
		GregorianCalendar gCal = new GregorianCalendar();		
		
		Date currTime = gCal.getTime(), termTime/*=(Date) currTime.clone()*/;		
		
		gCal.add(GregorianCalendar.HOUR, 1);			
		termTime = gCal.getTime();
		
		SetTerminationTimeResponse payload = this.factory.createSetTerminationTimeResponse(currTime,termTime);

		Document payloadAsDOM = this.writer.writeSetTerminationTimeResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, SetTerminationTimeResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created payload from its DOM Document representation
		SetTerminationTimeResponse readSetTerminationTimeResponse = this.reader.readSetTerminationTimeResponse(payloadAsDOM);		

		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkSetTerminationTimeResponse(payload, readSetTerminationTimeResponse,this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM SetTerminationTimeResponse\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link Destroy} Object
	 * Unit Test for WsrfrlWriter : write the created {@link Destroy} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link Destroy} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadDestroy() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM Destroy\" ~~~~~~~ \n");				
		// ~~~~ create an default Destroy payload and write it to DOM document ~~~~ 				
		Destroy payload = this.factory.createDestroy();

		Document payloadAsDOM = this.writer.writeDestroyAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, Destroy.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created payload from its DOM Document representation
		Destroy readDestroy = this.reader.readDestroy(payloadAsDOM);		

		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkDestroy(payload, readDestroy,this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM Destroy\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link DestroyResponse} Object
	 * Unit Test for WsrfrlWriter : write the created {@link DestroyResponse} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link DestroyResponse} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadDestroyResponse() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM DestroyResponse\" ~~~~~~~ \n");				
		// ~~~~ create an default DestroyResponse payload and write it to DOM document ~~~~ 				
		DestroyResponse payload = this.factory.createDestroyResponse();

		Document payloadAsDOM = this.writer.writeDestroyResponseAsDOM(payload);

		try {
			WsaUnitTestsUtils.validateResult(payloadAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, DestroyResponse.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created payload from its DOM Document representation
		DestroyResponse readDestroyResponse = this.reader.readDestroyResponse(payloadAsDOM);		

		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkDestroyResponse(payload, readDestroyResponse,this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM DestroyResponse\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link TerminationNotification} Object
	 * Unit Test for WsrfrlWriter : write the created {@link TerminationNotification} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link TerminationNotification} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadTerminationNotification() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TerminationNotification\" ~~~~~~~ \n");				
		// ~~~~ create an default TerminationNotification and write it to DOM document ~~~~ 				
		TerminationNotification notification = this.factory.createTerminationNotification(new Date());
		
		notification.setTerminationReason(WsrfrlUnitTestsUtils.createTerminationReasonElt("Resource Lifetime elapsed !"));
		
		Document notificationAsDOM = this.writer.writeTerminationNotificationAsDOM(notification);

		try {
			WsaUnitTestsUtils.validateResult(notificationAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, TerminationNotification.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created TerminationNotification from its DOM Document representation
		TerminationNotification readTerminationNotification = this.reader.readTerminationNotification(notificationAsDOM);		

		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkTerminationNotification(notification, readTerminationNotification,this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TerminationNotification\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}		
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link CurrentTime} Object
	 * Unit Test for WsrfrlWriter : write the created {@link CurrentTimeCurrentTime} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link CurrentTime} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadCurrentTime() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM CurrentTime\" ~~~~~~~ \n");				
		// ~~~~ create an default CurrentTime and write it to DOM document ~~~~ 				
		CurrentTime currentTime = this.factory.createCurrentTime(new Date());
			
		Document currentTimeAsDOM = this.writer.writeCurrentTimeAsDOM(currentTime);

		try {
			WsaUnitTestsUtils.validateResult(currentTimeAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, CurrentTime.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created CurrentTime from its DOM Document representation
		CurrentTime readCurrentTime = this.reader.readCurrentTime(currentTimeAsDOM);		

		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkCurrentTime(currentTime, readCurrentTime,this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM CurrentTime\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}			
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link TerminationTime} Object
	 * Unit Test for WsrfrlWriter : write the created {@link TerminationTime} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link TerminationTime} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadTerminationTime() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TerminationTime\" ~~~~~~~ \n");				
		// ~~~~ create an default TerminationTime and write it to DOM document ~~~~ 				
		TerminationTime terminationTime = this.factory.createTerminationTime(new Date());
			
		Document terminationTimeAsDOM = this.writer.writeTerminationTimeAsDOM(terminationTime);

		try {
			WsaUnitTestsUtils.validateResult(terminationTimeAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, TerminationTime.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created TerminationTime from its DOM Document representation
		TerminationTime readTerminationTime = this.reader.readTerminationTime(terminationTimeAsDOM);		

		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkTerminationTime(terminationTime, readTerminationTime,this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TerminationTime\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}			
	
	/**
	 * Unit Test for WsrfrlFactory : create a {@link ScheduledResourceTerminationRP} Object
	 * Unit Test for WsrfrlWriter : write the created {@link ScheduledResourceTerminationRP} Object to {@link Document} 
	 * Unit Test for WsrfrlReader : read from previously created {@link Document}, the {@link ScheduledResourceTerminationRP} object
	 * @throws WsrfrlException   
	 */	
	@Test
	public final void testCreateWriteAsDOMReadScheduledResourceTerminationRP() throws WsrfrlException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM ScheduledResourceTerminationRP\" ~~~~~~~ \n");				
		// ~~~~ create an default ScheduledResourceTerminationTRP and write it to DOM document ~~~~
		GregorianCalendar gCal = new GregorianCalendar();
		Date currTimeVal = gCal.getTime(), termTimeVal/*=(Date) currTimeVal.clone()*/;
		
		gCal.add(GregorianCalendar.HOUR, 1);
		termTimeVal = gCal.getTime();
		
		CurrentTime currTime = this.factory.createCurrentTime(currTimeVal);
		TerminationTime termTime = this.factory.createTerminationTime(termTimeVal);
		
		ScheduledResourceTerminationRP schedResTermRP = this.factory.createScheduledResourceTerminationRP(currTime,termTime);
			
		Document schedResTermRPAsDOM = this.writer.writeScheduledResourceTerminationRPAsDOM(schedResTermRP);

		try {
			WsaUnitTestsUtils.validateResult(schedResTermRPAsDOM,WsrfrlUnitTestsUtils.WSRFRL_XML_SCHEMAS_PATHS ,
					WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME, ScheduledResourceTerminationRP.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrlUnitTestsUtils.WSRFRL_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created ScheduledResourceTerminationTRP from its DOM Document representation
		ScheduledResourceTerminationRP readSchedResTermRP = this.reader.readScheduledResourceTerminationRP(schedResTermRPAsDOM);		

		AbsWsrfrlFaultsTypesAndPayloadsUnitTests.checkScheduledResourceTerminationRP(schedResTermRP, readSchedResTermRP,this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM ScheduledResourceTerminationRP\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}			
}
