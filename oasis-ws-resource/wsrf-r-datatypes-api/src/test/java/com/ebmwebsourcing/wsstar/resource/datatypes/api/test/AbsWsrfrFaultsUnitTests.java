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
package com.ebmwebsourcing.wsstar.resource.datatypes.api.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.AbsWsrfbfTypesUnitTests;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnavailableFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnknownFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrFactory;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrReader;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrWriter;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.implementor.WsrfrModelFactory;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.refinedabstraction.RefinedWsrfrFactory;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.utils.WsrfrException;

public abstract class AbsWsrfrFaultsUnitTests extends TestCase {
	
	protected boolean isDebug = false;
	
	private WsrfrFactory factory;
	protected WsrfrModelFactory modelFactoryImpl;
	private WsrfrReader reader;
	private WsrfrWriter writer;
	
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		this.initRefinedWsrfrFactory();		
		this.initRequiredDependencyModelFactories();
	}

	/**
	 * provide an instance of your "WsrfrModelFactory" model implementation class.
	 * The body must content something like :
	 * 
	 * 				"this.modelFactoryImpl = new WsrfrModelFactoryImpl();"
	 * 
	 * where "WsrfrModelFactoryImpl" is a class which implements "WsrfrModelFactory".
	 * 
	 */	
	protected abstract void setWsrfrModelFactory();
	
	/**
	 * init other model factories (mainly : WS-Addressing,WS-BaseFaults)
	 */
	protected abstract void initRequiredDependencyModelFactories();
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  Technical Methods (Init, ....) ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Initialization of factory and reader/writer attributes
	 */
	public void initRefinedWsrfrFactory(){	
		if (this.modelFactoryImpl == null)
			this.setWsrfrModelFactory();			
		
		assertNotNull(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrUnitTestsUtils.WSRFR_SPECIFICATION_NAME) +
				"the \"WsrfrModelFactory\" implementation Object has not been set.\n" +
				"Please provide an instance of \"WsrfrModelFactory\" implementation class", modelFactoryImpl);
		
		if (this.factory == null)		
			this.factory = RefinedWsrfrFactory.getInstance(this.modelFactoryImpl);			
		if (this.reader == null)
			this.reader = this.factory.getWsrfrReader();
		if (this.writer == null)
			this.writer = this.factory.getWsrfrWriter();	
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Check a given (provided by the "WsrfrReader") {@link ResourceUnknownFaultType} object
	 * 	 against a reference one
	 * @param isDebug TODO
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkResourceUnknownFaultType(ResourceUnknownFaultType expectedFault,ResourceUnknownFaultType toCheckFault, boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, WsrfrUnitTestsUtils.WSRFR_SPECIFICATION_NAME, isDebug);		
	}
	
	/**
	 * Check a given (provided by the "WsrfrReader") {@link ResourceUnavailableFaultType} object
	 * 	 against a reference one
	 * @param isDebug TODO
	 * @param createdEdp
	 * @param readEdp
	 */
	protected static boolean checkResourceUnavailableFaultType(ResourceUnavailableFaultType expectedFault,ResourceUnavailableFaultType toCheckFault, boolean isDebug){		
		
		return AbsWsrfbfTypesUnitTests.checkBaseFaultType(expectedFault, toCheckFault, WsrfrUnitTestsUtils.WSRFR_SPECIFICATION_NAME, isDebug);		
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/**
	 * Unit Test for WsrfrFactory : create a {@link ResourceUnknownFaultType} Object
	 * Unit Test for WsrfrWriter : write the created {@link ResourceUnknownFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrReader : read from previously created {@link Document}, the {@link ResourceUnknownFaultType} object
	 * @throws WsrfrException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadResourceUnknwonFaultType() throws WsrfrException {
		
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM ResourceUnknownFaultType\" ~~~~~~~ \n");				
		
		try {
			// ~~~~ create an default ResourceUnknown fault and write it to DOM document ~~~~ 		
			ResourceUnknownFaultType defaultResourceUnknownFault = this.factory.createResourceUnknownFaultType(new Date());	

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultResourceUnknownFault,
					WsrfrUnitTestsUtils.createDefaultBaseFaultOriginator(),
					"Resource not found on current topology",
					Locale.ENGLISH,
					"404",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrf/errors"),
					null);

			Document resourceUnknownFaultAsDOM = this.writer.writeResourceUnknwonFaultTypeAsDOM(defaultResourceUnknownFault);
			try {
				WsaUnitTestsUtils.validateResult(resourceUnknownFaultAsDOM,WsrfrUnitTestsUtils.WSRFR_XML_SCHEMAS_PATHS ,
						WsrfrUnitTestsUtils.WSRFR_SPECIFICATION_NAME, ResourceUnknownFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrUnitTestsUtils.WSRFR_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously created ResourceUnknown fault from its DOM Document representation
			ResourceUnknownFaultType readResourceUnknownFault = this.reader.readResourceUnknownFaultType(resourceUnknownFaultAsDOM);
			AbsWsrfrFaultsUnitTests.checkResourceUnknownFaultType(defaultResourceUnknownFault, readResourceUnknownFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM ResourceUnknownFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
	
	/**
	 * Unit Test for WsrfrFactory : create a {@link ResourceUnavailableFaultType} Object
	 * Unit Test for WsrfrWriter : write the created {@link ResourceUnavailableFaultType} Object to {@link Document} 
	 * Unit Test for WsrfrReader : read from previously created {@link Document}, the {@link ResourceUnavailableFaultType} object
	 * @throws WsrfrException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadResourceUnavailableFaultType() throws WsrfrException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM ResourceUnavailableFaultType\" ~~~~~~~ \n");				
		try {
			// ~~~~ create an default ResourceUnavailable fault and write it to DOM document ~~~~ 
			ResourceUnavailableFaultType defaultResourceUnavailableFault = this.factory.createResourceUnavailableFaultType(new Date());				

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultResourceUnavailableFault,
					WsrfrUnitTestsUtils.createDefaultBaseFaultOriginator(),
					"Resource temporarly busy !",
					Locale.ENGLISH,
					"450",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrf/errors"),
					null);

			Document resourceUnknownFaultAsDOM = this.writer.writeResourceUnavailableFaultTypeAsDOM(defaultResourceUnavailableFault);
			try {
				WsaUnitTestsUtils.validateResult(resourceUnknownFaultAsDOM,WsrfrUnitTestsUtils.WSRFR_XML_SCHEMAS_PATHS ,
						WsrfrUnitTestsUtils.WSRFR_SPECIFICATION_NAME, ResourceUnavailableFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfrUnitTestsUtils.WSRFR_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously created ResourceUnavailable fault from its DOM Document representation
			ResourceUnavailableFaultType readResourceUnknownFault = this.reader.readResourceUnavailableFaultType(resourceUnknownFaultAsDOM);
			AbsWsrfrFaultsUnitTests.checkResourceUnavailableFaultType(defaultResourceUnavailableFault, readResourceUnknownFault, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");

			System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM ResourceUnavailableFaultType\" passed !\n" +
			"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} 
	}
}
