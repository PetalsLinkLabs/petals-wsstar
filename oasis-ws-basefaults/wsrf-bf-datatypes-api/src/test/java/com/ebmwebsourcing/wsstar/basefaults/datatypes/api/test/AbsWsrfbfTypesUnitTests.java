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
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test;

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

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfReader;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfWriter;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.implementor.WsrfbfModelFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction.RefinedWsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.AbsWsaTypesUnitTests;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;

/**
 * @author Thierry DÉJEAN - eBM WebSourcing
 */
public abstract class AbsWsrfbfTypesUnitTests extends TestCase {
	
	protected boolean isDebug = false;
	
	private WsrfbfFactory factory;
	protected WsrfbfModelFactory modelFactoryImpl;
	private WsrfbfReader reader;
	private WsrfbfWriter writer;
	
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		this.initRefinedWsrfbfFactory();
		this.initRequiredDependencyModelFactories();
	}
	
	/**
	 * provide an instance of your "WsrfbfModelFactory" model implementation class.
	 * The body must content something like :
	 * 
	 * 				"this.modelFactoryImpl = new WsrfbfModelFactoryImpl();"
	 * 
	 * where "WsrfbfModelFactoryImpl" is a class which implements "WsrfbfModelFactory".
	 * 
	 */	
	protected abstract void setWsrfbfModelFactory();
	
	/**
	 * init other model factories (mainly : WS-Addressing)
	 */
	protected abstract void initRequiredDependencyModelFactories();
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  Technical Methods (Init, ....) ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Initialization of factory and reader/writer attributes
	 */
	public void initRefinedWsrfbfFactory(){	
		if (this.modelFactoryImpl == null)
			this.setWsrfbfModelFactory();			
		
		assertNotNull(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfbfUnitTestsUtils.WSRFBF_SPECIFICATION_NAME) +
				"the \"WsrfbfModelFactory\" implementation Object has not been set.\n" +
				"Please provide an instance of \"WsrfbfModelFactory\" implementation class", modelFactoryImpl);
		
		if (this.factory == null)		
			this.factory = RefinedWsrfbfFactory.getInstance(this.modelFactoryImpl);			
		if (this.reader == null)
			this.reader = this.factory.getWsrfbfReader();
		if (this.writer == null)
			this.writer = this.factory.getWsrfbfWriter();	
	}
		
	/**
	 * create a default {@link BaseFaultType} Object
	 * 	using the {@link WsrfbfFactoryFactory} attribute
	 * @param minimalFault TODO
	 * @param originator TODO
	 * @param descriptionValue TODO
	 * @param descriptionLang TODO
	 * @param errorCodeValue TODO
	 * @param errorCodeDialect TODO
	 * 
	 * @return a default {@link BaseFaultType} Object
	 */
	public static void setBaseFaultContentType(BaseFaultType minimalFault, EndpointReferenceType originator, 
			String descriptionValue, Locale descriptionLang, String errorCodeValue, URI errorCodeDialect, String faultCausePath){
		
		try {
			WsrfbfFactory wsrfbfFactory = RefinedWsrfbfFactory.getInstance();

			// ~~~ set a default orginator ~~~
			minimalFault.setOriginator(originator);	
			
			// ~~~ set a default description ~~~
			BaseFaultType.Description description = 
				wsrfbfFactory.createBaseFaultTypeDescription(descriptionValue);
			description.setLang(descriptionLang);		
			minimalFault.addDescription(description);

			// ~~~ set a default errorCode ~~~
			BaseFaultType.ErrorCode errorCode = 
				wsrfbfFactory.createBaseFaultTypeErrorCode(errorCodeDialect);			
			errorCode.addCode(errorCodeValue);
			minimalFault.setErrorCode(errorCode);

			// ~~~ set a default faultCause ~~~
			if (faultCausePath != null && faultCausePath.length() > 0){
				Element faultCauseContent = WsrfbfUnitTestsUtils.createDefaultFaultCauseContent(faultCausePath);
				BaseFaultType.FaultCause faultCause = wsrfbfFactory.createBaseFaultTypeFaultCause(faultCauseContent);
				minimalFault.setFaultCause(faultCause);
			}

		} catch (WsrfbfException e) {
			e.printStackTrace();
		}		
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Check a given (provided by the "WsrfbfReader") {@link BaseFaultType} object
	 * 	 against a reference one
	 * @param relatedSpecification TODO
	 * @param isDebug TODO
	 * @param createdEdp
	 * @param readEdp
	 */
	public static boolean checkBaseFaultType(BaseFaultType expectedFault,BaseFaultType toCheckFault, String relatedSpecification, boolean isDebug){
		
		// ~~~~~~~ Check BaseFaultType "timestamp" ~~~~~~~~ //
		Date expectedTimestamp = expectedFault.getTimestamp(), toCheckTimestamp = toCheckFault.getTimestamp();
	
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckFault timstamp : " + toCheckTimestamp.toString()+
					"\n[DEBUG] --> expectedFault timstamp : " + expectedTimestamp.toString() + "\n");

		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
				"faults have different timestamp values\n" +
				"\t(-> toCheckFault timestamp : " + toCheckTimestamp.toString() +
				"\n\t-> expectedFault timestamp : " + expectedTimestamp.toString() + ")", 
				toCheckTimestamp.equals(toCheckTimestamp));
		
		// ~~~~~~~ Check BaseFaultType "originator"(address field only) ~~~~~~~~ //
		EndpointReferenceType expectedOriginator = expectedFault.getOriginator(), toCheckOriginator = toCheckFault.getOriginator();		
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
				"faults have different Originators :  \n" +
				"\t(-> toCheckFault  originator is " + ((toCheckOriginator != null)?"not null":"null") +
				"\n\t-> expectedFault originator is " + ((expectedOriginator != null)?"not null":"null") + ")",
				(toCheckOriginator == null && expectedOriginator == null) ||
				(toCheckOriginator != null) && (expectedOriginator != null));
		
		if (toCheckOriginator != null) {
		
			AbsWsaTypesUnitTests.checkEndpointReferenceType(expectedOriginator, toCheckOriginator, relatedSpecification, isDebug);
		}
		
		// ~~~~~~~ Check BaseFaultType "Description" ~~~~~~~~ //		
		List<BaseFaultType.Description> expectedDescriptions = expectedFault.getDescriptions(),
			toCheckDescriptions = toCheckFault.getDescriptions();
		
		int expectedDescriptionCount = (expectedDescriptions != null)? expectedDescriptions.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
				"faults have different description values\n" +
				"\t(-> toCheckFault descriptions count : " + ((toCheckDescriptions != null)?toCheckDescriptions.size():"none") +
				"\n\t-> expectedFault descriptions count : " + ((expectedDescriptionCount==-1)?"none": expectedDescriptionCount) + ")",
				(toCheckDescriptions == null && expectedDescriptions == null) ||
				toCheckDescriptions.size() == expectedDescriptionCount);
		
		BaseFaultType.Description expectedDescription,toCheckDescription;
		
		for (int i = 0; i < expectedDescriptionCount; i++) {
			
			toCheckDescription = toCheckDescriptions.get(i);
			expectedDescription = expectedDescriptions.get(i);
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckFault description #" + i +
						" : lang = \"" + toCheckDescription.getLang().getLanguage() + 
						"\" - value = \"" + toCheckDescription.getValue()+"\""+
						"\n[DEBUG] --> expectedFault description #" + i +
						" : lang = \"" + expectedDescription.getLang().getLanguage() + 
						"\" - value = \"" + expectedDescription.getValue() + "\"\n");			
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsaUnitTestsUtils.WSA_SPECIFICATION_NAME) +
					"faults description contents are different \n" +
					"\t(-> toCheckFault description #" + i + " : lang = " + toCheckDescription.getLang().getLanguage() + 
					";value = " + toCheckDescription.getValue() +
					"\n\t-> expectedFault description #" + i + " : lang = " + expectedDescription.getLang().getLanguage() + 
					";value = " + expectedDescription.getValue(),
					toCheckDescription.getLang().equals(expectedDescription.getLang()) &&
					toCheckDescription.getValue().equals(expectedDescription.getValue()));	
		}
				
		// ~~~~~~~ Check BaseFaultType "ErrorCode" ~~~~~~~~ //
		
		BaseFaultType.ErrorCode expectedErrorCode = expectedFault.getErrorCode(), toCheckErrorCode = toCheckFault.getErrorCode();
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
				"faults have different ErrorCode \n" +
				"\t(-> toCheckFault  errorCode is " + ((toCheckErrorCode	!= null)?"not null":"null") +
				"\n\t-> expectedFault errorCode is " + ((expectedErrorCode != null)?"not null":"null") + ")",
				(toCheckErrorCode == null && expectedErrorCode == null) ||
				(toCheckErrorCode != null) && (expectedErrorCode != null));
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
				"faults have different errorCode dialects\n" +
				"\t(-> toCheckFault  errorCode dialect : " + toCheckErrorCode.getDialect().toString() +
				"\n\t-> expectedFault errorCode dialect : " + expectedErrorCode.getDialect().toString() + ")",
				toCheckErrorCode.getDialect().equals(expectedErrorCode.getDialect()));
		
		List<Object> toCheckErrorCodeContents = toCheckErrorCode.getCodes(), expectedErrorCodeContents = expectedErrorCode.getCodes();
		
		int expectedErrCodeContentCount = (expectedErrorCodeContents != null)? expectedErrorCodeContents.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
				"faults have different error code contents\n" +
				"\t(-> toCheckFault errorCode count : " + ((toCheckErrorCodeContents != null)?toCheckErrorCodeContents.size():"none") +
				"\n\t-> expectedFault errorCode count : " + ((expectedErrCodeContentCount==-1)?"none": expectedErrCodeContentCount) + ")",
				(toCheckDescriptions == null && expectedDescriptions == null) ||
				toCheckDescriptions.size() == expectedDescriptionCount);
		
		Object expectedErrorCodeContent,toCheckErrorCodeContent;
		
		for (int i = 0; i < expectedErrCodeContentCount; i++) {
			
			toCheckErrorCodeContent = toCheckErrorCodeContents.get(i);
			expectedErrorCodeContent = expectedErrorCodeContents.get(i);
			
			if (isDebug)
			System.out.println("[DEBUG] --> toCheckFault errorCode #" + i +
					" : dialect = \"" + toCheckErrorCode.getDialect().toString() + 
					"\" - content  = \"" + toCheckErrorCodeContent.toString()+"\""+
					"\n[DEBUG] --> expectedFault errorCode #" + i +
					" : dialect = \"" + expectedErrorCode.getDialect().toString() + 
					"\" - content = \"" + expectedErrorCodeContent.toString() + "\"\n");

			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WsaUnitTestsUtils.WSA_SPECIFICATION_NAME) +
					"fault errorCode content are different \n" +
					"\t(-> toCheckFault errorCode  #" + i + " : content = " + toCheckErrorCodeContent.toString() + 
					"\n\t-> expectedFault errorCode  #" + i + " : content = " + expectedErrorCodeContent.toString(),
					toCheckErrorCodeContent.toString().equals(expectedErrorCodeContent.toString()));	
		}
		
		
		// ~~~~~~~ Check BaseFaultType "FaultCause" ~~~~~~~~ //
		
		BaseFaultType.FaultCause expectedFaultCause = expectedFault.getFaultCause(), toCheckFaultCause = toCheckFault.getFaultCause();		
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
				"faults have different faultCause \n" +
				"\t(-> toCheckFault  faultCause is " + ((toCheckFaultCause!= null)?"not null":"null") +
				"\n\t-> expectedFault faultCause is " + ((expectedFaultCause != null)?"not null":"null") + ")",
				(toCheckFaultCause == null && expectedFaultCause == null) ||
				(toCheckFaultCause != null) && (expectedFaultCause != null));
		
		if(toCheckFaultCause != null) { 
			
			Object toCheckFaultCauseAny = toCheckFaultCause.getAny(), expectedFaultCauseAny = expectedFaultCause.getAny();
			
			String toCheckAnyAsString = (toCheckFaultCauseAny != null && toCheckFaultCauseAny instanceof Element)?
					WsaUnitTestsUtils.formatToComparison((Element) toCheckFaultCauseAny):null;	
			String expectedAnyAsString = (expectedFaultCauseAny != null && expectedFaultCauseAny instanceof Element)?
					WsaUnitTestsUtils.formatToComparison((Element) expectedFaultCauseAny): null;					

			if (isDebug)		
				System.out.println("[DEBUG] --> toCheckFaultCauseAny : " + toCheckFaultCauseAny +
						"\n\t(nodesAsString = " + toCheckAnyAsString + ")"+
						"\n[DEBUG] --> expectedFaultCauseAny : " + expectedFaultCauseAny + 
						"\n\t(nodesAsString = " + toCheckAnyAsString + ")\n");

			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(relatedSpecification) +
					"endpoints metadata content are different \n" +
					"\t(-> toCheckFault formatted to comparison faultCause value is : " + 
					((toCheckFaultCauseAny != null)?toCheckFaultCauseAny:null)+
					"\n\t-> expectedFault formatted to comparison faultCause value is : " +
					((expectedFaultCauseAny!= null)?expectedFaultCauseAny:null) + ")",
					((toCheckAnyAsString == null) && (expectedAnyAsString == null))||
					((expectedAnyAsString != null) && (toCheckAnyAsString != null) && 
							toCheckAnyAsString.equals(expectedAnyAsString)));	
						
		}
		 return true;
	}
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	
	/**
	 * Unit Test for WsrfbfFactory : create a {@link BaseFaultType} Object
	 * Unit Test for WsrfbfWriter : write the created {@link BaseFaultType} Object to {@link Document} 
	 * Unit Test for WsrfbfReader : read from previously created {@link Document}, the {@link BaseFaultType} object
	 * @throws WsrfbfException 
	 */	
	public final void genericCreateWriteAsDOMReadBaseFaultType(String faultCausePath) throws WsrfbfException {
		try {
			// ~~~~ create an default baseFault and write it to DOM document ~~~~ 
			BaseFaultType defaultCreatedBaseFault = this.factory.createBaseFaultType(new Date());			

			AbsWsrfbfTypesUnitTests.setBaseFaultContentType(defaultCreatedBaseFault,
					WsrfbfUnitTestsUtils.createDefaultBaseFaultOriginator(),
					"Ceci est une decription en français de la faute",
					Locale.FRENCH,
					"6",
					new URI("http://www.ebmwebsourcing.com/dialect/wsrf/errors"),
					faultCausePath);

			Document baseFaultAsDOM = this.writer.writeBaseFaultTypeAsDOM(defaultCreatedBaseFault);
			try {
				WsaUnitTestsUtils.validateResult(baseFaultAsDOM,WsrfbfUnitTestsUtils.WSRFBF_XML_SCHEMAS_PATHS ,
						WsrfbfUnitTestsUtils.WSRFBF_SPECIFICATION_NAME, BaseFaultType.class, this.isDebug);
				System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
			} catch (SAXException e) {
				Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WsrfbfUnitTestsUtils.WSRFBF_SPECIFICATION_NAME) + e.getMessage());
			}

			// ~~~~~~~~ read the previously created basefault from its DOM Document representation
			BaseFaultType readBaseFault = this.reader.readBaseFaultType(baseFaultAsDOM);
			AbsWsrfbfTypesUnitTests.checkBaseFaultType(defaultCreatedBaseFault, readBaseFault, WsrfbfUnitTestsUtils.WSRFBF_SPECIFICATION_NAME, this.isDebug);
			System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
		} catch (URISyntaxException e1) {
			throw new WsrfbfException(e1);
		}
	}

	/**
	 * Test with FaultCause not defined in "WS-Resource" xml schemas set (use "WS-BaseNotification" one)
	 */ 
	@Test
	public final void testCreateWriteAsDOMReadBaseFaultTypeWithWsnFaultCause() throws WsrfbfException {
		System.out.println("trasnform Properties = " + System.getProperty("javax.xml.transform.TransformerFactory"));
		System.out.println("doc Properties = " + System.getProperty("javax.xml.parsers.DocumentBuilderFactory"));
	       
	    System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
				"~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM BaseFaultType with WS-Notification FaultCause\" ~~~~~~~ \n");				
		
		this.genericCreateWriteAsDOMReadBaseFaultType("/defaultWsnFaultCause.xml");
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM BaseFaultType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");	
	}
	
	/**
	 * Test with FaultCause defined in "WS-Resource" xml schemas set (use a "ResourceUnknownFaultType" fault)
	 */ 
	@Test
	public final void testCreateWriteAsDOMReadBaseFaultTypeWithWsrfFaultCause() throws WsrfbfException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
				"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM BaseFaultType with WS-Resource FaultCause \" ~~~~~~~ \n");				
		
		this.genericCreateWriteAsDOMReadBaseFaultType("/defaultWsrfFaultCause.xml");
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM BaseFaultType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");	
	}


}
