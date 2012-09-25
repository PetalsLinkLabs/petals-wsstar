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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.xml.namespace.QName;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.AbsWsrfbfTypesUnitTests;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidFilterFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidMessageContentExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidProducerPropertiesExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidTopicExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MultipleTopicsSpecifiedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NoCurrentMessageOnTopicFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotifyMessageNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeCreationFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionDialectUnknownFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToCreatePullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroyPullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroySubscriptionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToGetMessagesFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableInitialTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnrecognizedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsupportedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbReader;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbWriter;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.implementor.WsnbModelFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbFaultMessageContentConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.test.util.WsaUnitTestsUtils;

/**
 * @author Thierry DÉJEAN - eBM WebSourcing
 */
public abstract class AbsWsnbFaultsUnitTests extends TestCase {

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
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link InvalidFilterFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkInvalidFilterFaultType(InvalidFilterFaultType expectedFault,
			InvalidFilterFaultType toCheckFault, boolean isDebug){		
		
		// ~~~~~~~ Check UnknownFilter Lists ~~~~~~~
		List<QName> expectedUnknownFilters = expectedFault.getUnknwonFilters(),
		toCheckUnknownFilters = toCheckFault.getUnknwonFilters();
				
		int expectedUnknownFiltersCount = (expectedUnknownFilters != null)? expectedUnknownFilters.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"\"InvalidFilter\" faults have different \"UnknownFilter\" values\n" +
				"\t(-> toCheckFault unknownFilter count : " + ((toCheckUnknownFilters != null)?toCheckUnknownFilters.size():"none") +
				"\n\t-> expectedFault unknownFilter count : " + ((expectedUnknownFiltersCount==-1)?"none": expectedUnknownFiltersCount) + ")",
				( toCheckUnknownFilters== null && expectedUnknownFilters == null) ||
				toCheckUnknownFilters.size() == expectedUnknownFiltersCount);
		
		QName expectedUnknownFilter,toCheckUnknownFilter;
		
		for (int i = 0; i < expectedUnknownFiltersCount; i++) {
			
			toCheckUnknownFilter = toCheckUnknownFilters.get(i);
			expectedUnknownFilter = expectedUnknownFilters.get(i);
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckFault UnknownFilter #" + i +
						" : \"" + toCheckUnknownFilter + 
						"\n[DEBUG] --> expectedFault UnknownFilter #" + i +
						" : \"" + expectedUnknownFilter + "\"\n");			
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"faults  UnknownFilter values are different \n" +
					"\t(-> toCheckFault UnknownFilter #" + i + " : " + toCheckUnknownFilter +
					"\n\t-> expectedFault UnknownFilter #" + i + " : " + expectedUnknownFilter,
					toCheckUnknownFilter.equals(expectedUnknownFilter));	
		}
		
		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link InvalidMessageContentExpressionFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkInvalidMessageContentExpressionFaultType(InvalidMessageContentExpressionFaultType expectedFault,
			InvalidMessageContentExpressionFaultType toCheckFault, boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link InvalidProducerPropertiesExpressionFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkInvalidProducerPropertiesExpressionFaultType(
			InvalidProducerPropertiesExpressionFaultType expectedFault,
			InvalidProducerPropertiesExpressionFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link InvalidTopicExpressionFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkInvalidTopicExpressionFaultType(InvalidTopicExpressionFaultType expectedFault,
			InvalidTopicExpressionFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link MultipleTopicsSpecifiedFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkMultipleTopicsSpecifiedFaultType(MultipleTopicsSpecifiedFaultType expectedFault,
			MultipleTopicsSpecifiedFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link NoCurrentMessageOnTopicFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkNoCurrentMessageOnTopicFaultType(NoCurrentMessageOnTopicFaultType expectedFault,
			NoCurrentMessageOnTopicFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link NotifyMessageNotSupportedFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkNotifyMessageNotSupportedFaultType(NotifyMessageNotSupportedFaultType expectedFault,
			NotifyMessageNotSupportedFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link PauseFailedFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkPauseFailedFaultType(PauseFailedFaultType expectedFault,
			PauseFailedFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link ResumeFailedFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkResumeFailedFaultType(ResumeFailedFaultType expectedFault,
			ResumeFailedFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link SubscribeCreationFailedFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkSubscribeCreationFailedFaultType(SubscribeCreationFailedFaultType expectedFault,
			SubscribeCreationFailedFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link TopicExpressionDialectUnknownFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkTopicExpressionDialectUnknownFaultType(TopicExpressionDialectUnknownFaultType expectedFault,
			TopicExpressionDialectUnknownFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link TopicNotSupportedFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkTopicNotSupportedFaultType(TopicNotSupportedFaultType expectedFault,
			TopicNotSupportedFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnableToCreatePullPointFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnableToCreatePullPointFaultType(UnableToCreatePullPointFaultType expectedFault,
			UnableToCreatePullPointFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnableToDestroyPullPointFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnableToDestroyPullPointFaultType(UnableToDestroyPullPointFaultType expectedFault,
			UnableToDestroyPullPointFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnableToDestroySubscriptionFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnableToDestroySubscriptionFaultType(UnableToDestroySubscriptionFaultType expectedFault,
			UnableToDestroySubscriptionFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnableToGetMessagesFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnableToGetMessagesFaultType(UnableToGetMessagesFaultType expectedFault,
			UnableToGetMessagesFaultType toCheckFault,boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnacceptableInitialTerminationTimeFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnacceptableInitialTerminationTimeFaultType(UnacceptableInitialTerminationTimeFaultType expectedFault,
			UnacceptableInitialTerminationTimeFaultType toCheckFault,boolean isDebug){		
		// ~~~~~~~ Check MinimumTime field ~~~~~~~~ //
		Date expectedMinimumTime = expectedFault.getMinimumTime(),
			toCheckMinimumTime = toCheckFault.getMinimumTime();		
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckFault MinimumTime : " + 
					((toCheckMinimumTime!=null)?toCheckMinimumTime.toString() : "null") +
					"\n[DEBUG] --> expectedFault MinimumTime : " + 
					((expectedMinimumTime != null)?expectedMinimumTime.toString() : "null") + "\n");
				
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"faults  have different \"MinimumTime\" values\n" +
				"\t(-> toCheckFault MinimumTime : " + ((toCheckMinimumTime!=null)?toCheckMinimumTime.toString() : "null") +
				"\n\t-> expectedFault MinimumTime : " + ((expectedMinimumTime != null)?expectedMinimumTime.toString() : "null") + "\n", 
				(expectedMinimumTime != null) &&(toCheckMinimumTime!=null) &&
				toCheckMinimumTime.equals(expectedMinimumTime));

		// ~~~~~~~ Check MaximumTime field ~~~~~~~~ //
		Date expectedMaximumTime = expectedFault.getMaximumTime(),
		toCheckMaximumTime = toCheckFault.getMaximumTime();		

		if (isDebug)
			System.out.println("[DEBUG] --> toCheckFault MaximumTime : " + 
					((toCheckMaximumTime!=null)?toCheckMaximumTime.toString() : "null") +
					"\n[DEBUG] --> expectedFault MaximumTime : " + 
					((expectedMaximumTime != null)?expectedMaximumTime.toString() : "null") + "\n");

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"faults  have different \"MaximumTime\" values\n" +
				"\t(-> toCheckFault MaximumTime : " + ((toCheckMaximumTime!=null)?toCheckMaximumTime.toString() : "null") +
				"\n\t-> expectedFault MaximumTime : " + ((expectedMaximumTime != null)?expectedMaximumTime.toString() : "null") + "\n", 
				((expectedMaximumTime == null) && (toCheckMaximumTime==null))||
				toCheckMaximumTime.equals(expectedMaximumTime));	

		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}

	/**
	 * Check a given (provided by the "WsnbReader") {@link UnacceptableTerminationTimeFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnacceptableTerminationTimeFaultType(UnacceptableTerminationTimeFaultType expectedFault,
			UnacceptableTerminationTimeFaultType toCheckFault,boolean isDebug){		
		
		// ~~~~~~~ Check MinimumTime field ~~~~~~~~ //
		Date expectedMinimumTime = expectedFault.getMinimumTime(),
			toCheckMinimumTime = toCheckFault.getMinimumTime();		
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckFault MinimumTime : " + 
					((toCheckMinimumTime!=null)?toCheckMinimumTime.toString() : "null") +
					"\n[DEBUG] --> expectedFault MinimumTime : " + 
					((expectedMinimumTime != null)?expectedMinimumTime.toString() : "null") + "\n");
				
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"faults  have different \"MinimumTime\" values\n" +
				"\t(-> toCheckFault MinimumTime : " + ((toCheckMinimumTime!=null)?toCheckMinimumTime.toString() : "null") +
				"\n\t-> expectedFault MinimumTime : " + ((expectedMinimumTime != null)?expectedMinimumTime.toString() : "null") + "\n", 
				(expectedMinimumTime != null) &&(toCheckMinimumTime!=null) &&
				toCheckMinimumTime.equals(expectedMinimumTime));

		// ~~~~~~~ Check MaximumTime field ~~~~~~~~ //
		Date expectedMaximumTime = expectedFault.getMaximumTime(),
		toCheckMaximumTime = toCheckFault.getMaximumTime();		

		if (isDebug)
			System.out.println("[DEBUG] --> toCheckFault MaximumTime : " + 
					((toCheckMaximumTime!=null)?toCheckMaximumTime.toString() : "null") +
					"\n[DEBUG] --> expectedFault MaximumTime : " + 
					((expectedMaximumTime != null)?expectedMaximumTime.toString() : "null") + "\n");

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"faults  have different \"MaximumTime\" values\n" +
				"\t(-> toCheckFault MaximumTime : " + ((toCheckMaximumTime!=null)?toCheckMaximumTime.toString() : "null") +
				"\n\t-> expectedFault MaximumTime : " + ((expectedMaximumTime != null)?expectedMaximumTime.toString() : "null") + "\n", 
				((toCheckMaximumTime==null) && (expectedMaximumTime == null)) ||
				toCheckMaximumTime.equals(expectedMaximumTime));		

		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnrecognizedPolicyRequestFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnrecognizedPolicyRequestFaultType(UnrecognizedPolicyRequestFaultType expectedFault,
			UnrecognizedPolicyRequestFaultType toCheckFault,boolean isDebug){		
		
		// ~~~~~~~ Check UnrecognizedPolicy Lists ~~~~~~~
		List<QName> expectedUnrecognizedPolicies = expectedFault.getUnrecognizedPolicies(),
		toCheckUnrecognizedPolicies = toCheckFault.getUnrecognizedPolicies();
				
		int expectedUnrecognizedPoliciesCount = (expectedUnrecognizedPolicies != null)? expectedUnrecognizedPolicies.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"\"UnrecognizedPolicyRequest\" faults have different \"UnrecognizedPolicy\" values\n" +
				"\t(-> toCheckFault UnrecognizedPolicies count : " + ((toCheckUnrecognizedPolicies != null)?toCheckUnrecognizedPolicies.size():"none") +
				"\n\t-> expectedFault UnrecognizedPolicies count : " + ((expectedUnrecognizedPoliciesCount==-1)?"none": expectedUnrecognizedPoliciesCount) + ")",
				( toCheckUnrecognizedPolicies== null && expectedUnrecognizedPolicies == null) ||
				toCheckUnrecognizedPolicies.size() == expectedUnrecognizedPoliciesCount);
		
		QName expectedUnrecognizedPolicy,toCheckUnrecognizedPolicy;
		
		for (int i = 0; i < expectedUnrecognizedPoliciesCount; i++) {
			
			toCheckUnrecognizedPolicy = toCheckUnrecognizedPolicies.get(i);
			expectedUnrecognizedPolicy = expectedUnrecognizedPolicies.get(i);
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckFault UnrecognizedPolicy #" + i +
						" : \"" + toCheckUnrecognizedPolicy + 
						"\n[DEBUG] --> expectedFault UnrecognizedPolicy #" + i +
						" : \"" + expectedUnrecognizedPolicy + "\"\n");			
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"faults UnrecognizedPolicy values are different \n" +
					"\t(-> toCheckFault UnrecognizedPolicy #" + i + " : " + toCheckUnrecognizedPolicy +
					"\n\t-> expectedFault UnrecognizedPolicy #" + i + " : " + expectedUnrecognizedPolicy,
					toCheckUnrecognizedPolicy.equals(expectedUnrecognizedPolicy));	
		}
		
		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsnbReader") {@link UnsupportedPolicyRequestFaultType} object
	 * 	 against a reference one
	 */
	protected static boolean checkUnsupportedPolicyRequestFaultType(UnsupportedPolicyRequestFaultType expectedFault,
			UnsupportedPolicyRequestFaultType toCheckFault,boolean isDebug){		
		
		// ~~~~~~~ Check UnsupportedPolicy Lists ~~~~~~~
		List<QName> expectedUnsupportedPolicies = expectedFault.getUnsupportedPolicies(),
		toCheckUnsupportedPolicies = toCheckFault.getUnsupportedPolicies();
				
		int expectedUnsupportedPoliciesCount = (expectedUnsupportedPolicies != null)? expectedUnsupportedPolicies.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
				"\"UnsupportedPolicyRequest\" faults have different \"UnsupportedPolicy\" values\n" +
				"\t(-> toCheckFault UnsupportedPolicies count : " + ((toCheckUnsupportedPolicies != null)?toCheckUnsupportedPolicies.size():"none") +
				"\n\t-> expectedFault UnsupportedPolicies count : " + ((expectedUnsupportedPoliciesCount==-1)?"none": expectedUnsupportedPoliciesCount) + ")",
				( toCheckUnsupportedPolicies== null && expectedUnsupportedPolicies == null) ||
				toCheckUnsupportedPolicies.size() == expectedUnsupportedPoliciesCount);
		
		QName expectedUnsupportedPolicy,toCheckUnsupportedPolicy;
		
		for (int i = 0; i < expectedUnsupportedPoliciesCount; i++) {
			
			toCheckUnsupportedPolicy = toCheckUnsupportedPolicies.get(i);
			expectedUnsupportedPolicy = expectedUnsupportedPolicies.get(i);
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckFault UnsupportedPolicy #" + i +
						" : \"" + toCheckUnsupportedPolicy + 
						"\n[DEBUG] --> expectedFault UnsupportedPolicy #" + i +
						" : \"" + expectedUnsupportedPolicy + "\"\n");			
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) +
					"faults UnsupportedPolicy values are different \n" +
					"\t(-> toCheckFault UnsupportedPolicy #" + i + " : " + toCheckUnsupportedPolicy +
					"\n\t-> expectedFault UnsupportedPolicy #" + i + " : " + expectedUnsupportedPolicy,
					toCheckUnsupportedPolicy.equals(expectedUnsupportedPolicy));	
		}
		
		// ~~~~~~~ Check common BaseFault part ~~~~~~
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, 
				WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, isDebug);		
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/**
	 * Unit Test for WsnbFactory : create a {@link InvalidFilterFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link InvalidFilterFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link InvalidFilterFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadInvalidFilterFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM InvalidFilterFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default InvalidFilterFaultType fault and write it to DOM document ~~~~ 		
						
			QName unknownFilterQname = new QName("http://www.ebmwebsourcing.com/wsn/faults/filter", "UnknownFilterValue");			
			List<QName> unknownFilters = new ArrayList<QName>();
			unknownFilters.add(unknownFilterQname);
			
			InvalidFilterFaultType defaultInvalidFilterFault = 
				this.factory.createInvalidFilterFaultType(new Date(),unknownFilters);	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultInvalidFilterFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.INVALID_FILTER_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-1",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);
						
			Document invalidFilterFaultAsDOM = this.writer.writeInvalidFilterFaultTypeAsDOM(defaultInvalidFilterFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, InvalidFilterFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously InvalidFilterFaultType fault from its DOM Document representation
			InvalidFilterFaultType readInvalidFilterFault = this.reader.readInvalidFilterFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkInvalidFilterFaultType(defaultInvalidFilterFault, readInvalidFilterFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM InvalidFilterFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link InvalidMessageContentExpressionFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link InvalidMessageContentExpressionFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link InvalidMessageContentExpressionFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadInvalidMessageContentExpressionFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM InvalidMessageContentExpressionFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default InvalidMessageContentExpressionFaultType fault and write it to DOM document ~~~~ 		
			InvalidMessageContentExpressionFaultType defaultInvalidMessageContentExpressionFault = this.factory.createInvalidMessageContentExpressionFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultInvalidMessageContentExpressionFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.INVALID_MESSAGE_CONTENT_EXPRESSION_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-2",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeInvalidMessageContentExpressionFaultTypeAsDOM(defaultInvalidMessageContentExpressionFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, InvalidMessageContentExpressionFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously InvalidMessageContentExpressionFaultType fault from its DOM Document representation
			InvalidMessageContentExpressionFaultType readInvalidMessageContentExpressionFault = this.reader.readInvalidMessageContentExpressionFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkInvalidMessageContentExpressionFaultType(defaultInvalidMessageContentExpressionFault, readInvalidMessageContentExpressionFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM InvalidMessageContentExpressionFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link InvalidProducerPropertiesExpressionFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link InvalidProducerPropertiesExpressionFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link InvalidProducerPropertiesExpressionFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadInvalidProducerPropertiesExpressionFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM InvalidProducerPropertiesExpressionFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default InvalidProducerPropertiesExpressionFaultType fault and write it to DOM document ~~~~ 		
			InvalidProducerPropertiesExpressionFaultType defaultInvalidProducerPropertiesExpressionFault = this.factory.createInvalidProducerPropertiesExpressionFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultInvalidProducerPropertiesExpressionFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.INVALID_PRODUCER_PROPERTIES_EXPRESSION_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-3",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeInvalidProducerPropertiesExpressionFaultTypeAsDOM(defaultInvalidProducerPropertiesExpressionFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, InvalidProducerPropertiesExpressionFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously InvalidProducerPropertiesExpressionFaultType fault from its DOM Document representation
			InvalidProducerPropertiesExpressionFaultType readInvalidProducerPropertiesExpressionFault = this.reader.readInvalidProducerPropertiesExpressionFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkInvalidProducerPropertiesExpressionFaultType(defaultInvalidProducerPropertiesExpressionFault, readInvalidProducerPropertiesExpressionFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM InvalidProducerPropertiesExpressionFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link InvalidTopicExpressionFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link InvalidTopicExpressionFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link InvalidTopicExpressionFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadInvalidTopicExpressionFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM InvalidTopicExpressionFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default InvalidTopicExpressionFaultType fault and write it to DOM document ~~~~ 		
			InvalidTopicExpressionFaultType defaultInvalidTopicExpressionFault = this.factory.createInvalidTopicExpressionFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultInvalidTopicExpressionFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.INVALID_TOPIC_EXPRESSION_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-4",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeInvalidTopicExpressionFaultTypeAsDOM(defaultInvalidTopicExpressionFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, InvalidTopicExpressionFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously InvalidTopicExpressionFaultType fault from its DOM Document representation
			InvalidTopicExpressionFaultType readInvalidTopicExpressionFault = this.reader.readInvalidTopicExpressionFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkInvalidTopicExpressionFaultType(defaultInvalidTopicExpressionFault, readInvalidTopicExpressionFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM InvalidTopicExpressionFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link MultipleTopicsSpecifiedFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link MultipleTopicsSpecifiedFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link MultipleTopicsSpecifiedFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadMultipleTopicsSpecifiedFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM MultipleTopicsSpecifiedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default MultipleTopicsSpecifiedFaultType fault and write it to DOM document ~~~~ 		
			MultipleTopicsSpecifiedFaultType defaultMultipleTopicsSpecifiedFault = this.factory.createMultipleTopicsSpecifiedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultMultipleTopicsSpecifiedFault,
					WsnbUnitTestsUtils.createDefaultWsnPullPointOriginator(),
					WsnbFaultMessageContentConstants.WsnbGetCurrentMessageFaultDescriptions.MULTIPLE_TOPICS_SPECIFIED_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-5",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeMultipleTopicsSpecifiedFaultTypeAsDOM(defaultMultipleTopicsSpecifiedFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, MultipleTopicsSpecifiedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously MultipleTopicsSpecifiedFaultType fault from its DOM Document representation
			MultipleTopicsSpecifiedFaultType readMultipleTopicsSpecifiedFault = this.reader.readMultipleTopicsSpecifiedFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkMultipleTopicsSpecifiedFaultType(defaultMultipleTopicsSpecifiedFault, readMultipleTopicsSpecifiedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM MultipleTopicsSpecifiedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link NoCurrentMessageOnTopicFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link NoCurrentMessageOnTopicFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link NoCurrentMessageOnTopicFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadNoCurrentMessageOnTopicFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM NoCurrentMessageOnTopicFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default NoCurrentMessageOnTopicFaultType fault and write it to DOM document ~~~~ 		
			NoCurrentMessageOnTopicFaultType defaultNoCurrentMessageOnTopicFault = this.factory.createNoCurrentMessageOnTopicFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultNoCurrentMessageOnTopicFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbGetCurrentMessageFaultDescriptions.NO_CURRENT_MESSAGE_ON_TOPIC_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-6",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeNoCurrentMessageOnTopicFaultTypeAsDOM(defaultNoCurrentMessageOnTopicFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, NoCurrentMessageOnTopicFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously NoCurrentMessageOnTopicFaultType fault from its DOM Document representation
			NoCurrentMessageOnTopicFaultType readNoCurrentMessageOnTopicFault = this.reader.readNoCurrentMessageOnTopicFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkNoCurrentMessageOnTopicFaultType(defaultNoCurrentMessageOnTopicFault, readNoCurrentMessageOnTopicFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM NoCurrentMessageOnTopicFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link NotifyMessageNotSupportedFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link NotifyMessageNotSupportedFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link NotifyMessageNotSupportedFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadNotifyMessageNotSupportedFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM NotifyMessageNotSupportedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default NotifyMessageNotSupportedFaultType fault and write it to DOM document ~~~~ 		
			NotifyMessageNotSupportedFaultType defaultNotifyMessageNotSupportedFault = this.factory.createNotifyMessageNotSupportedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultNotifyMessageNotSupportedFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.NOTIFY_MESSAGE_NOT_SUPPORTED_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-7",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeNotifyMessageNotSupportedFaultTypeAsDOM(defaultNotifyMessageNotSupportedFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, NotifyMessageNotSupportedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously NotifyMessageNotSupportedFaultType fault from its DOM Document representation
			NotifyMessageNotSupportedFaultType readNotifyMessageNotSupportedFault = this.reader.readNotifyMessageNotSupportedFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkNotifyMessageNotSupportedFaultType(defaultNotifyMessageNotSupportedFault, readNotifyMessageNotSupportedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM NotifyMessageNotSupportedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link PauseFailedFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link PauseFailedFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link PauseFailedFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadPauseFailedFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM PauseFailedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default PauseFailedFaultType fault and write it to DOM document ~~~~ 		
			PauseFailedFaultType defaultPauseFailedFault = this.factory.createPauseFailedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultPauseFailedFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbPauseSubscriptonFaultDescriptions.PAUSE_FAILED_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-8",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writePauseFailedFaultTypeAsDOM(defaultPauseFailedFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, PauseFailedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously PauseFailedFaultType fault from its DOM Document representation
			PauseFailedFaultType readPauseFailedFault = this.reader.readPauseFailedFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkPauseFailedFaultType(defaultPauseFailedFault, readPauseFailedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM PauseFailedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link ResumeFailedFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link ResumeFailedFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link ResumeFailedFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadResumeFailedFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM ResumeFailedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default ResumeFailedFaultType fault and write it to DOM document ~~~~ 		
			ResumeFailedFaultType defaultResumeFailedFault = this.factory.createResumeFailedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultResumeFailedFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbResumeSubscriptonFaultDescriptions.RESUME_FAILED_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-9",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeResumeFailedFaultTypeAsDOM(defaultResumeFailedFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, ResumeFailedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously ResumeFailedFaultType fault from its DOM Document representation
			ResumeFailedFaultType readResumeFailedFault = this.reader.readResumeFailedFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkResumeFailedFaultType(defaultResumeFailedFault, readResumeFailedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM ResumeFailedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link SubscribeCreationFailedFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link SubscribeCreationFailedFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link SubscribeCreationFailedFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadSubscribeCreationFailedFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM SubscribeCreationFailedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default SubscribeCreationFailedFaultType fault and write it to DOM document ~~~~ 		
			SubscribeCreationFailedFaultType defaultSubscribeCreationFailedFault = this.factory.createSubscribeCreationFailedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultSubscribeCreationFailedFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.SUBSCRIBE_CREATION_FAILED_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-10",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeSubscribeCreationFailedFaultTypeAsDOM(defaultSubscribeCreationFailedFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, SubscribeCreationFailedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously SubscribeCreationFailedFaultType fault from its DOM Document representation
			SubscribeCreationFailedFaultType readSubscribeCreationFailedFault = this.reader.readSubscribeCreationFailedFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkSubscribeCreationFailedFaultType(defaultSubscribeCreationFailedFault, readSubscribeCreationFailedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM SubscribeCreationFailedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link TopicExpressionDialectUnknownFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link TopicExpressionDialectUnknownFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link TopicExpressionDialectUnknownFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadTopicExpressionDialectUnknownFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TopicExpressionDialectUnknownFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default TopicExpressionDialectUnknownFaultType fault and write it to DOM document ~~~~ 		
			TopicExpressionDialectUnknownFaultType defaultTopicExpressionDialectUnknownFault = this.factory.createTopicExpressionDialectUnknownFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultTopicExpressionDialectUnknownFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.TOPIC_EXPRESSION_DIALECT_UNKNOWN_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-11",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeTopicExpressionDialectUnknownFaultTypeAsDOM(defaultTopicExpressionDialectUnknownFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, TopicExpressionDialectUnknownFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously TopicExpressionDialectUnknownFaultType fault from its DOM Document representation
			TopicExpressionDialectUnknownFaultType readTopicExpressionDialectUnknownFault = this.reader.readTopicExpressionDialectUnknownFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkTopicExpressionDialectUnknownFaultType(defaultTopicExpressionDialectUnknownFault, readTopicExpressionDialectUnknownFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TopicExpressionDialectUnknownFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link TopicNotSupportedFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link TopicNotSupportedFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link TopicNotSupportedFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadTopicNotSupportedFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TopicNotSupportedFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default TopicNotSupportedFaultType fault and write it to DOM document ~~~~ 		
			TopicNotSupportedFaultType defaultTopicNotSupportedFault = this.factory.createTopicNotSupportedFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultTopicNotSupportedFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.TOPIC_NOT_SUPPORTED_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-12",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document topicNotSupportedFaultAsDOM = this.writer.writeTopicNotSupportedFaultTypeAsDOM(defaultTopicNotSupportedFault);
			try {
				WsaUnitTestsUtils.validateResult(topicNotSupportedFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, TopicNotSupportedFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously TopicNotSupportedFaultType fault from its DOM Document representation
			TopicNotSupportedFaultType readTopicNotSupportedFault = this.reader.readTopicNotSupportedFaultType(topicNotSupportedFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkTopicNotSupportedFaultType(defaultTopicNotSupportedFault, readTopicNotSupportedFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TopicNotSupportedFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnableToCreatePullPointFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnableToCreatePullPointFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnableToCreatePullPointFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnableToCreatePullPointFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnableToCreatePullPointFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnableToCreatePullPointFaultType fault and write it to DOM document ~~~~ 		
			UnableToCreatePullPointFaultType defaultUnableToCreatePullPointFault = this.factory.createUnableToCreatePullPointFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnableToCreatePullPointFault,
					WsnbUnitTestsUtils.createDefaultWsnConsumerOriginator(),
					WsnbFaultMessageContentConstants.WsnbCreatePullPointFaultDescriptions.UNABLE_TO_CREATE_PULLPOINT_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-13",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeUnableToCreatePullPointFaultTypeAsDOM(defaultUnableToCreatePullPointFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnableToCreatePullPointFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnableToCreatePullPointFaultType fault from its DOM Document representation
			UnableToCreatePullPointFaultType readUnableToCreatePullPointFault = this.reader.readUnableToCreatePullPointFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnableToCreatePullPointFaultType(defaultUnableToCreatePullPointFault, readUnableToCreatePullPointFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnableToCreatePullPointFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnableToDestroyPullPointFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnableToDestroyPullPointFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnableToDestroyPullPointFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnableToDestroyPullPointFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnableToDestroyPullPointFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnableToDestroyPullPointFaultType fault and write it to DOM document ~~~~ 		
			UnableToDestroyPullPointFaultType defaultUnableToDestroyPullPointFault = this.factory.createUnableToDestroyPullPointFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnableToDestroyPullPointFault,
					WsnbUnitTestsUtils.createDefaultWsnPullPointOriginator(),
					WsnbFaultMessageContentConstants.WsnbDestroyPullPointFaultDescriptions.UNABLE_TO_DESTROY_PULLPOINT_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-14",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeUnableToDestroyPullPointFaultTypeAsDOM(defaultUnableToDestroyPullPointFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnableToDestroyPullPointFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnableToDestroyPullPointFaultType fault from its DOM Document representation
			UnableToDestroyPullPointFaultType readUnableToDestroyPullPointFault = this.reader.readUnableToDestroyPullPointFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnableToDestroyPullPointFaultType(defaultUnableToDestroyPullPointFault, readUnableToDestroyPullPointFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnableToDestroyPullPointFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnableToDestroySubscriptionFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnableToDestroySubscriptionFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnableToDestroySubscriptionFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnableToDestroySubscriptionFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnableToDestroySubscriptionFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnableToDestroySubscriptionFaultType fault and write it to DOM document ~~~~ 		
			UnableToDestroySubscriptionFaultType defaultUnableToDestroySubscriptionFault = this.factory.createUnableToDestroySubscriptionFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnableToDestroySubscriptionFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbUnsubscribeFaultDescriptions.UNABLE_TO_DESTROY_SUBSCRIPTION_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-15",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeUnableToDestroySubscriptionFaultTypeAsDOM(defaultUnableToDestroySubscriptionFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnableToDestroySubscriptionFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnableToDestroySubscriptionFaultType fault from its DOM Document representation
			UnableToDestroySubscriptionFaultType readUnableToDestroySubscriptionFault = this.reader.readUnableToDestroySubscriptionFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnableToDestroySubscriptionFaultType(defaultUnableToDestroySubscriptionFault, readUnableToDestroySubscriptionFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnableToDestroySubscriptionFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnableToGetMessagesFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnableToGetMessagesFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnableToGetMessagesFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnableToGetMessagesFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnableToGetMessagesFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnableToGetMessagesFaultType fault and write it to DOM document ~~~~ 		
			UnableToGetMessagesFaultType defaultUnableToGetMessagesFault = this.factory.createUnableToGetMessagesFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnableToGetMessagesFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbGetMessagesFaultDescriptions.UNABLE_TO_GET_MESSAGES_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-16",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			Document invalidFilterFaultAsDOM = this.writer.writeUnableToGetMessagesFaultTypeAsDOM(defaultUnableToGetMessagesFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnableToGetMessagesFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnableToGetMessagesFaultType fault from its DOM Document representation
			UnableToGetMessagesFaultType readUnableToGetMessagesFault = this.reader.readUnableToGetMessagesFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnableToGetMessagesFaultType(defaultUnableToGetMessagesFault, readUnableToGetMessagesFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnableToGetMessagesFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnacceptableInitialTerminationTimeFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnacceptableInitialTerminationTimeFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnacceptableInitialTerminationTimeFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnacceptableInitialTerminationTimeFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnacceptableInitialTerminationTimeFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnacceptableInitialTerminationTimeFaultType fault and write it to DOM document ~~~~ 		
			GregorianCalendar gCal = new GregorianCalendar();
			gCal.add(GregorianCalendar.YEAR, 1);
			Date  minTerminationTime = gCal.getTime();
			
			UnacceptableInitialTerminationTimeFaultType defaultUnacceptableInitialTerminationTimeFault = 
				this.factory.createUnacceptableInitialTerminationTimeFaultType(new Date(),minTerminationTime);	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnacceptableInitialTerminationTimeFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.UNACCEPTABLE_INITIAL_TERMINATION_TIME_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-17",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);
			
			// ~~~~ Optional : add a Maximum Termination Time ~~~~
			gCal = new GregorianCalendar();
			gCal.add(GregorianCalendar.YEAR, 2);
			Date maxTermTime = gCal.getTime();
			
			defaultUnacceptableInitialTerminationTimeFault.setMaximumTime(maxTermTime);
			
			Document invalidFilterFaultAsDOM = this.writer.writeUnacceptableInitialTerminationTimeFaultTypeAsDOM(defaultUnacceptableInitialTerminationTimeFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnacceptableInitialTerminationTimeFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnacceptableInitialTerminationTimeFaultType fault from its DOM Document representation
			UnacceptableInitialTerminationTimeFaultType readUnacceptableInitialTerminationTimeFault = this.reader.readUnacceptableInitialTerminationTimeFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnacceptableInitialTerminationTimeFaultType(defaultUnacceptableInitialTerminationTimeFault, readUnacceptableInitialTerminationTimeFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnacceptableInitialTerminationTimeFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnacceptableTerminationTimeFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnacceptableTerminationTimeFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnacceptableTerminationTimeFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnacceptableTerminationTimeFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnacceptableTerminationTimeFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnacceptableTerminationTimeFaultType fault and write it to DOM document ~~~~ 		
			GregorianCalendar gCal  = new GregorianCalendar();
			gCal.add(GregorianCalendar.MONTH, 1);
			Date minTerminationTime = gCal.getTime();
			
			UnacceptableTerminationTimeFaultType defaultUnacceptableTerminationTimeFault = 
				this.factory.createUnacceptableTerminationTimeFaultType(new Date(),minTerminationTime);	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnacceptableTerminationTimeFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbRenewFaultDescriptions.UNACCAPTABLE_TERMINATION_TIME_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-18",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);

			// ~~~~ Optional : add a Maximum Termination Time ~~~~
			gCal = new GregorianCalendar();
			gCal.add(GregorianCalendar.YEAR, 2);
			Date maxTermTime = gCal.getTime();
			
			defaultUnacceptableTerminationTimeFault.setMaximumTime(maxTermTime);
			
			Document invalidFilterFaultAsDOM = this.writer.writeUnacceptableTerminationTimeFaultTypeAsDOM(defaultUnacceptableTerminationTimeFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnacceptableTerminationTimeFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnacceptableTerminationTimeFaultType fault from its DOM Document representation
			UnacceptableTerminationTimeFaultType readUnacceptableTerminationTimeFault = this.reader.readUnacceptableTerminationTimeFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnacceptableTerminationTimeFaultType(defaultUnacceptableTerminationTimeFault, readUnacceptableTerminationTimeFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnacceptableTerminationTimeFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnrecognizedPolicyRequestFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnrecognizedPolicyRequestFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnrecognizedPolicyRequestFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnrecognizedPolicyRequestFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnrecognizedPolicyRequestFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnrecognizedPolicyRequestFaultType fault and write it to DOM document ~~~~ 		
			UnrecognizedPolicyRequestFaultType defaultUnrecognizedPolicyRequestFault = this.factory.createUnrecognizedPolicyRequestFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnrecognizedPolicyRequestFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.UNRECOGNIZED_POLICY_REQUEST_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-19",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);
			
			// ~~~~ Optional : add a QName associated to a unrecognized policy	
			QName anUnrecognizedPolicy = new QName("http://uri/default", "customPolicy");
			defaultUnrecognizedPolicyRequestFault.getUnrecognizedPolicies().add(anUnrecognizedPolicy);
			
			Document invalidFilterFaultAsDOM = this.writer.writeUnrecognizedPolicyRequestFaultTypeAsDOM(defaultUnrecognizedPolicyRequestFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnrecognizedPolicyRequestFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnrecognizedPolicyRequestFaultType fault from its DOM Document representation
			UnrecognizedPolicyRequestFaultType readUnrecognizedPolicyRequestFault = this.reader.readUnrecognizedPolicyRequestFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnrecognizedPolicyRequestFaultType(defaultUnrecognizedPolicyRequestFault, readUnrecognizedPolicyRequestFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnrecognizedPolicyRequestFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsnbFactory : create a {@link UnsupportedPolicyRequestFaultType} Object
	 * Unit Test for WsnbWriter : write the created {@link UnsupportedPolicyRequestFaultType} Object to {@link Document} 
	 * Unit Test for WsnbReader : read from previously created {@link Document}, the {@link UnsupportedPolicyRequestFaultType} object
	 * @throws WsnbException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadUnsupportedPolicyRequestFaultType() throws WsnbException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM UnsupportedPolicyRequestFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default UnsupportedPolicyRequestFaultType fault and write it to DOM document ~~~~ 		
			UnsupportedPolicyRequestFaultType defaultUnsupportedPolicyRequestFault = this.factory.createUnsupportedPolicyRequestFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultUnsupportedPolicyRequestFault,
					WsnbUnitTestsUtils.createDefaultWsnProducerOriginator(),
					WsnbFaultMessageContentConstants.WsnbSubcribeFaultDescriptions.UNSUPPORTED_POLICY_REQUEST_FAULT_DESC,
					Locale.ENGLISH,
					"WSNB-FAULT-20",
					new URI("http://www.ebmwebsourcing.com/dialect/wsn/errors"),
					null);
			
			// ~~~~ Optional : add a QName associated to a unrecognized policy	
			QName anUnsupportedPolicy = new QName("http://uri/default", "customPolicy");
			defaultUnsupportedPolicyRequestFault.getUnsupportedPolicies().add(anUnsupportedPolicy);
			
			Document invalidFilterFaultAsDOM = this.writer.writeUnsupportedPolicyRequestFaultTypeAsDOM(defaultUnsupportedPolicyRequestFault);
			try {
				WsaUnitTestsUtils.validateResult(invalidFilterFaultAsDOM,WsnbUnitTestsUtils.WSN_XML_SCHEMAS_PATHS ,
						WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME, UnsupportedPolicyRequestFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsnbUnitTestsUtils.WSNB_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously UnsupportedPolicyRequestFaultType fault from its DOM Document representation
			UnsupportedPolicyRequestFaultType readUnsupportedPolicyRequestFault = this.reader.readUnsupportedPolicyRequestFaultType(invalidFilterFaultAsDOM);
			AbsWsnbFaultsUnitTests.checkUnsupportedPolicyRequestFaultType(defaultUnsupportedPolicyRequestFault, readUnsupportedPolicyRequestFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM UnsupportedPolicyRequestFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
}
