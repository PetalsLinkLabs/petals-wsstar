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
package com.ebmwebsourcing.wsstar.topics.datatypes.api.test;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.namespace.QName;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopReader;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopWriter;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.implementor.WstopModelFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.refinedabstraction.RefinedWstopFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;

/**
 * @author Thierry DÉJEAN - eBM WebSourcing
 */
public abstract class AbsWstopTypesUnitTests extends TestCase {

	protected boolean isDebug = false;
	
	private WstopFactory factory;
	protected WstopModelFactory modelFactoryImpl;
	private WstopReader reader;
	private WstopWriter writer;
	
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		this.initRefinedWstopFactory();		
	}
	
	private static String getFailedMessagePrefix(String specification){
		return "\nUnit Tests of "+ specification + " Model implementation Failed !\n Failure Cause :\n";
	}
	
	/**
	 * provide an instance of your "WstopModelFactory" model implementation class.
	 * The body must content something like :
	 * 
	 * 				"this.modelFactoryImpl = new WsnbModelFactoryImpl();"
	 * 
	 * where "WstopModelFactoryImpl" is a class which implements "WstopModelFactory".
	 * 
	 */		
	protected abstract void setWstopModelFactory();

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  Technical Methods (Init, ....) ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Initialization of factory and reader/writer attributes
	 */
	public void initRefinedWstopFactory(){	
		if (this.modelFactoryImpl == null)
			this.setWstopModelFactory();			
		
		assertNotNull(AbsWstopTypesUnitTests.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"the \"WstopModelFactory\" implementation Object has not been set.\n" +
				"Please provide an instance of \"WstopModelFactory\" implementation class", modelFactoryImpl);
		
		if (this.factory == null)		
			this.factory = RefinedWstopFactory.getInstance(this.modelFactoryImpl);			
		if (this.reader == null)
			this.reader = this.factory.getWstopReader();
		if (this.writer == null)
			this.writer = this.factory.getWstopWriter();	
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Common default objects creation" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //	

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	
	/**
	 * Check a given (provided by the "WstopReader") {@link MessagePattern} object
	 * 	 against a reference one
	 */
	protected static boolean checkMessagePattern(QueryExpressionType expectedMessagePattern,
			QueryExpressionType toCheckMessagePattern, boolean isDebug){		
				
		// ~~~~~~~ check QueryExpressionType dialect equality ~~~~~~~~ 
		URI expectedDialect =  expectedMessagePattern.getDialect(),
		toCheckDialect = toCheckMessagePattern.getDialect();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckDialect value : " +  ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
					"\n[DEBUG] --> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null") + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"MessageContentPattern dialects are \n" +
				"\t(-> toCheckDialect value : " + ((toCheckDialect!=null)?toCheckDialect.toString():"null") +
				"\n\t-> expectedDialect value : " + ((expectedDialect!=null)?expectedDialect.toString():"null")+ ")" , 
				(toCheckDialect == expectedDialect) ||(toCheckDialect != null && toCheckDialect.equals(expectedDialect)));

		// ~~~~~~~ check MessagePattern content equality ~~~~~~~~ 
		String expectedContent =  expectedMessagePattern.getContent(),
		toCheckContent = toCheckMessagePattern.getContent();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckContent value : " +  toCheckContent +
					"\n[DEBUG] --> expectedContent value : " + expectedContent + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"MessageContentPattern contents are \n" +
				"\t(-> toCheckContent value : " + toCheckContent +
				"\n\t-> expectedContent value : " + expectedContent+ ")", 
				(toCheckContent == expectedContent) ||(toCheckContent != null && toCheckContent.equals(expectedContent)));
		
		return true;		
	}	
	
	/**
	 * Check a given (provided by the "WstopReader") {@link TopicType} object
	 * 	 against a reference one
	 */
	protected static boolean checkTopicType(TopicType expectedTopic,TopicType toCheckTopic, boolean isDebug){				
		
		// ~~~~~~~ check topic "name" attribute equality ~~~~~~~~
		String expectedAttrName = expectedTopic.getName(), toCheckAttrName = toCheckTopic.getName();
		
		if (isDebug) {
			System.out.println("[DEBUG] --> toCheckName attribute : " +  toCheckAttrName +
					"\n[DEBUG] --> expectedName attribute : " + expectedAttrName + "\n");
		}
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"Topic names are differents\n" +
				"\t(-> toCheckName attribute : " + toCheckAttrName +
				"\n\t-> expectedName attribute : " + expectedAttrName + ")" , 
				(toCheckAttrName.equals(expectedAttrName)));
		
		// ~~~~~~~ check topic "final" attribute equality ~~~~~~~~ 
		boolean expectedFinalAttr =  expectedTopic.isFinal(),
		toCheckFinalAttr = toCheckTopic.isFinal();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckFinal attribute value : " +  toCheckFinalAttr +
					"\n[DEBUG] --> expectedFinal attribute value : " + expectedFinalAttr + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"Topic \"final\" attribute values are \n" +
				"\t(-> toCheckFinal attribute value : " + toCheckFinalAttr +
				"\n\t-> expectedFinal attribute value : " +  expectedFinalAttr + ")" , 
				toCheckFinalAttr == expectedFinalAttr);

		// ~~~~~~~ check "MessagePattern" attribute equality ~~~~~~~~ 
		QueryExpressionType expectedMsgPattern = expectedTopic.getMessagePattern(),
			toCheckMsgPattern = toCheckTopic.getMessagePattern();
		
		AbsWstopTypesUnitTests.checkMessagePattern(expectedMsgPattern, toCheckMsgPattern, isDebug);
		
		// ~~~~~~~ check "MessageTypes" attribute equality ~~~~~~~
		List<QName> expectedMsgTypes = expectedTopic.getMessageTypes(),
			toCheckMsgTypes = toCheckTopic.getMessageTypes();
		
		int expectedMsgTypesCount = (expectedMsgTypes != null)? expectedMsgTypes.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"Topics have different messageTypes list content\n" +
				"\t(-> toCheckMessageTypes attribute list size : " + ((toCheckMsgTypes != null)?toCheckMsgTypes.size():"none") +
				"\n\t-> expectedMessageTypes attribute list size : " + ((expectedMsgTypesCount==-1)?"none": expectedMsgTypesCount) + ")",				
				(expectedMsgTypesCount > 0) &&
				toCheckMsgTypes.size() == expectedMsgTypesCount);

		QName expectedMsgTypesItem = null, toCheckMsgTypesItem = null;		
		for (int i = 0; i < expectedMsgTypesCount; i++) {		
			toCheckMsgTypesItem = toCheckMsgTypes.get(i); 
			expectedMsgTypesItem = expectedMsgTypes.get(i);
			
			if (isDebug)
				System.out.println("[DEBUG] --> toCheckMsgType #"+ i +" (QName) : " + toCheckMsgTypesItem +
						"\n[DEBUG] --> expectedMsgType #"+ i +" (QName) : " + expectedMsgTypesItem + "\n");
			
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
					"MessageType QName are \n" +
					"\t(-> toCheckMsgType #"+ i +" : " + toCheckMsgTypesItem +
					"\n\t-> expectedMsgType #"+ i +" : " + expectedMsgTypesItem+ ")" , 
					toCheckMsgTypesItem.equals(expectedMsgTypesItem));				
		}
		
		// ~~~~~~ Check Topic "children" (recursive call) ~~~~
		List<TopicType> expectedChildren = expectedTopic.getTopics(),
			toCheckChildren = toCheckTopic.getTopics();
		
		int expectedChildrenCount = (expectedChildren != null)? expectedChildren.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"Topics have different children list content\n" +
				"\t(-> toCheckChildren list size : " + ((toCheckChildren != null)?toCheckChildren.size():"none") +
				"\n\t-> expectedChildren list size : " + ((expectedChildrenCount == -1)?"none": expectedChildrenCount) + ")",
				toCheckChildren.size() == expectedChildrenCount);
		
		boolean result = true;
		
		for (int i = 0; i < expectedChildrenCount; i++) {
			result &= AbsWstopTypesUnitTests.checkTopicType(expectedChildren.get(i), toCheckChildren.get(i), isDebug);
		}
		return result;		
	}	
	
	/**
	 * Check a given (provided by the "WstopReader") {@link TopicNamespaceType} object
	 * 	 against a reference one
	 */
	protected static boolean checkTopicNamespeceType(TopicNamespaceType expectedTopicNs,TopicNamespaceType toCheckTopicNs, boolean isDebug){		
				
		// ~~~~~~~ check topicNamespace "targetNamespace" attribute equality ~~~~~~~~
		URI expectedNamespaceAttr = expectedTopicNs.getNamespace(), toCheckNamespaceAttr = toCheckTopicNs.getNamespace();
		
		if (isDebug) {
			System.out.println("[DEBUG] --> toCheckNamespace : " +  toCheckNamespaceAttr +
					"\n[DEBUG] --> expectedNamespace  : " + expectedNamespaceAttr + "\n");
		}
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"TopicNamespace \"targetNamespace\" attributes are differents\n" +
				"\t(-> toCheckNamespace attribute : " + toCheckNamespaceAttr +
				"\n\t-> expectedNamespace attribute : " + expectedNamespaceAttr + ")" , 
				toCheckNamespaceAttr.equals(expectedNamespaceAttr));
		
		// ~~~~~~~ check topicNamespace "Name" attribute equality ~~~~~~~~
		String expectedNameAttr = expectedTopicNs.getName(), toCheckNameAttr = toCheckTopicNs.getName();
		
		if (isDebug) {
			System.out.println("[DEBUG] --> toCheckNameAttr : " +  toCheckNameAttr +
					"\n[DEBUG] --> expectedNameAttr  : " + expectedNameAttr + "\n");
		}
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"TopicNamespace \"name\" attributes are differents\n" +
				"\t(-> toCheckName attribute : " + toCheckNamespaceAttr +
				"\n\t-> expectedName attribute : " + expectedNamespaceAttr + ")" , 
				toCheckNameAttr.equals(expectedNameAttr));
		
		// ~~~~~~~ check topic name "final" attribute equality ~~~~~~~~ 
		boolean expectedFinalAttr =  expectedTopicNs.isFinal(),
		toCheckFinalAttr = toCheckTopicNs.isFinal();	
		
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckFinal attribute value : " +  toCheckFinalAttr +
					"\n[DEBUG] --> expectedFinal attribute value : " + expectedFinalAttr + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"Topic \"final\" attribute values are \n" +
				"\t(-> toCheckFinal attribute value : " + toCheckFinalAttr +
				"\n\t-> expectedFinal attribute value : " +  expectedFinalAttr + ")" , 
				toCheckFinalAttr == expectedFinalAttr);

		// ~~~~~~~ check "Topics" equality ~~~~~~~~ 
	
		List<TopicNamespaceType.Topic> expectedTopics = expectedTopicNs.getTopics(),
			toCheckTopics = toCheckTopicNs.getTopics();
		
		int expectedTopicsCount = (expectedTopics != null)? expectedTopics.size() : -1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"Topics have different topics list content\n" +
				"\t(-> toCheckTopics list size : " + ((toCheckTopics != null)?toCheckTopics.size():"none") +
				"\n\t-> expectedTopics list size : " + ((expectedTopicsCount == -1)?"none": expectedTopicsCount) + ")",
				toCheckTopics.size() == expectedTopicsCount);
		
		boolean result = true;
		String expectedParent = null, toCheckParent = null;
		TopicNamespaceType.Topic expectedTopicItem = null, toCheckTopicItem = null;
		
		for (int i = 0; i < expectedTopicsCount; i++) {
			expectedTopicItem = expectedTopics.get(i);			
			toCheckTopicItem = toCheckTopics.get(i);
	
			// ~~~~ check "parent" attribute ~~~~
			expectedParent = expectedTopicItem.getParent();
			toCheckParent = toCheckTopicItem.getParent();
			
			if (isDebug) {
				System.out.println("[DEBUG] --> toCheckParentAttr : " +  toCheckParent +
						"\n[DEBUG] --> expectedParentAttr  : " + expectedParent + "\n");
			}
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
					"Topic \"Parent\" attributes are differents\n" +
					"\t(-> toCheckParent attribute : " + toCheckParent +
					"\n\t-> expectedParent attribute : " + expectedParent + ")" , 
					toCheckParent.equals(expectedParent));
			
			// ~~~~ check "TopicType" fields ~~~~			
			result &= AbsWstopTypesUnitTests.checkTopicType(expectedTopicItem, toCheckTopicItem, isDebug);			
		}
		return result;
		
	}	
	/**
	 * Check a given (provided by the "WstopReader") {@link TopicSetType} object
	 * 	 against a reference one
	 * 
	 * @param expectedTopicSet
	 * @param toCheckTopicSet
	 */
	protected static boolean checkTopicSetType(TopicSetType expectedTopicSet,
			TopicSetType toCheckTopicSet, boolean isDebug){		
	
		// ~~~~~~~ check TopicSet sub-tree (xml fragments) value equality ~~~~~~~~ 		
		List<org.w3c.dom.Element> toCheckTopicSetTrees = toCheckTopicSet.getTopicsTrees(),
		expectedTopicSetTrees = expectedTopicSet.getTopicsTrees();
		
		int expectedRPValuesCount = (expectedTopicSetTrees != null)?expectedTopicSetTrees.size():-1;
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
				"TopicSets have different topics trees values\n" +
				"\t(-> toCheckTrees count : " + ((toCheckTopicSetTrees != null)?toCheckTopicSetTrees.size():"none") +
				"\n\t-> expectedTrees count : " + ((expectedRPValuesCount==-1)?"none": expectedRPValuesCount) + ")",
				(toCheckTopicSetTrees == null && expectedTopicSetTrees == null) ||
				toCheckTopicSetTrees.size() == expectedRPValuesCount);
		
		String toCheckTopicsTreesItemAsString, expectedTopicsTreesItemAsString;
		
		for (int i = 0; i < expectedRPValuesCount; i++) {
			
			toCheckTopicsTreesItemAsString = (toCheckTopicSetTrees != null)?	WsaUnitTestsUtils.formatToComparison(toCheckTopicSetTrees.get(i)):null;
					
			expectedTopicsTreesItemAsString = (expectedTopicSetTrees != null)? WsaUnitTestsUtils.formatToComparison(expectedTopicSetTrees.get(i)): null;	

			if (isDebug) {		
				System.out.println("[DEBUG] --> toCheckTopicsTrees : " + toCheckTopicSetTrees +
						"\n\t(treeAsString = " + toCheckTopicsTreesItemAsString + ")"+
						"\n[DEBUG] --> expectedTopicsTrees : " + expectedTopicSetTrees + 
						"\n\t(treeAsString = " + expectedTopicsTreesItemAsString + ")\n");
			}
			
			Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) +
					"TopicsTrees are different \n" +
					"\t(-> toCheckTopicTree value formatted to comparison is : " + 
					((toCheckTopicSetTrees != null)?toCheckTopicSetTrees:null)+
					"\n\t(treesAsString = " + toCheckTopicsTreesItemAsString + ")"+
					"\n\t-> expectedTopicTree value formatted to comparison is : " +
					((expectedTopicSetTrees!= null)?expectedTopicSetTrees:null) + ")"+
					"\n\t(treesAsString = " + expectedTopicsTreesItemAsString + ")\n",
					((toCheckTopicsTreesItemAsString == expectedTopicsTreesItemAsString ))||
					((expectedTopicsTreesItemAsString != null) && (toCheckTopicsTreesItemAsString != null) && 
							toCheckTopicsTreesItemAsString.equals(expectedTopicsTreesItemAsString)));	
		}

		return true;		
	}	
		
	//	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		
	/**
	 * Unit Test for WstopFactory : create a {@link TopicNamespeceType} Object without children
	 * Unit Test for WstopWriter : write the created {@link TopicNamespaceType} Object to {@link Document} 
	 * Unit Test for WstopReader : read from previously created {@link Document}, the {@link TopicNamespaceType} object
	 * @throws WstopException   
	 * @throws URISyntaxException 
	 */	
	@Test
	public final void testCreateWriteAsDOMReadTopicNamespaceType() throws WstopException, URISyntaxException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TopicNamespaceType\" ~~~~~~~ \n");				
		
		// ~~~~ create an default TopicNamespaceType object and write it to DOM document ~~~~ 
		TopicNamespaceType topicNamespace = this.factory.createTopicNamespaceType(new URI("http://www.ebmwebsourcing.com/wsstar/wsn-producer"));
		topicNamespace.setFinal(true);
		topicNamespace.setName("SampleTopicSpace");
		
		// ~~~~ create and add topic ~~~~
		TopicNamespaceType.Topic topicToAdd = this.factory.createTopicNamespaceTypeTopic("TopicSet");
		
		// ~~~~ set "parent" value ~~~~
		topicToAdd.setParent("tns:ResourcePropertiesChanges");
		
		// ~~~~ Set "MessageType" attribute ~~~~
		QName rpValueChangeMsg = new QName("http://docs.oasis-open.org/wrfr/rp-2","ResourcePropertyValueChangeNotification");			
		topicToAdd.addMessageType(rpValueChangeMsg);
		
		// ~~~~ Set "final" attribute ~~~~
		topicToAdd.setFinal(true);
		
		// ~~~~ Set MessagePattern attribute ~~~~ 
		QueryExpressionType msgPattern = 
			this.factory.createMessagePattern(WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI);			
		msgPattern.setContent("/Notify/NotificationMessage[Message/ResourcePropertyValueChangeNotification]");		
		topicToAdd.setMessagePattern(msgPattern);
		
		
		topicNamespace.addTopic(topicToAdd);
		
		Document topicNamespaceAsDOM = this.writer.writeTopicNamespaceTypeAsDOM(topicNamespace);

		try {
			WsaUnitTestsUtils.validateResult(topicNamespaceAsDOM,WstopUnitTestsUtilsTests.WSTOP_XML_SCHEMAS_PATHS,
					WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME, TopicNamespaceType.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created MessagePattern from its DOM Document representation
		TopicNamespaceType readTopicNamespace = this.reader.readTopicNamespaceType(topicNamespaceAsDOM);		

		AbsWstopTypesUnitTests.checkTopicNamespeceType(topicNamespace, readTopicNamespace, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
	
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TopicNamespaceType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	
	/**
	 * Unit Test for WsrfrpWriter : write the created {@link TopicSetType} Object to {@link Document} 
	 * Unit Test for WsrfrpReader : read from previously created {@link Document}, the {@link TopicSetType} object
	 * @throws WstopException   
	 */	
	public final void genericWriteAsDOMReadTopicSetType(TopicSetType topicSet) throws WstopException {
		
		Document topicSetAsDOM = this.writer.writeTopicSetTypeAsDOM(topicSet);

		try {
			WsaUnitTestsUtils.validateResult(topicSetAsDOM,WstopUnitTestsUtilsTests.WSTOP_XML_SCHEMAS_PATHS ,
					WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME, TopicSetType.class, this.isDebug);
			System.out.println("\t Unit Test Step 1 (\"create\" and \"write to DOM\") : Passed !\n");
		} catch (SAXException e) {
			Assert.fail(WsaUnitTestsUtils.getFailedMessagePrefix(WstopUnitTestsUtilsTests.WSTOP_SPECIFICATION_NAME) + e.getMessage());
		}

		// ~~~~~~~~ read the previously created GetResourcePropertyResponse from its DOM Document representation
		TopicSetType readTopicSet = this.reader.readTopicSetType(topicSetAsDOM);		

		AbsWstopTypesUnitTests.checkTopicSetType(topicSet, readTopicSet, this.isDebug);		

		System.out.println("\t Unit Test Step 2 (\"read from DOM\") : Passed !");
		
	}	
	
	/**
	 * Unit Test for  : create a {@link TopicSetType} Object
	 * and call "genericWriteAsDOMReadTopicSetType()" common test (read/write)
	 */ 
	@Test
	public final void testCreateWriteAsDOMReadTopicSetType() throws WstopException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Create, Write-to-DOM and read-from-DOM TopicSetType\" ~~~~~~~ \n");
		
		// ~~~~ create an default TopicSetType and write it to DOM document ~~~~ 	
		TopicSetType topicSet = this.factory.createTopicSetType();
		List<Element> topicsTrees = WstopUnitTestsUtilsTests.createDefaultTopicTrees();
		for (Element treeItem : topicsTrees) {
			topicSet.addTopicsTree(treeItem);
		}
						
		this.genericWriteAsDOMReadTopicSetType(topicSet);
		
		System.out.println("\n\t OK, unit test \"Create, Write-to-DOM and read-from-DOM TopicSetType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Unit Test for WsrfrpReader : read from {@link InputStream}, the {@link TopicSetType} object
	 * and call "genericWriteAsDOMReadTopicSetType()" common test (read/write)
	 */ 
	@Test
	public final void testReadFromFSWriteAsDOMReadTopicSetType() throws WstopException {
		System.out.println("\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
		"\n\t ~~~~~~~ unit test \"Read from file, Write-to-DOM and read-from-DOM TopicSetType\" ~~~~~~~ \n");
		
		InputStream topicSetFromAsStream = AbsWstopTypesUnitTests.class.getResourceAsStream("/SupportedTopicsSet.xml");
		TopicSetType topicSet = this.reader.readTopicSetType(new InputSource(topicSetFromAsStream));			
		
		this.genericWriteAsDOMReadTopicSetType(topicSet);
		
		System.out.println("\n\t OK, unit test \"Read from file, Write-to-DOM and read-from-DOM TopicSetType\" passed !\n" +
		"\n\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	
}
