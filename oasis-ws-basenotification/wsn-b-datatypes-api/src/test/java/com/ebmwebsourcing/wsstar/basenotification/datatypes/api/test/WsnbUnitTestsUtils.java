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
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.element.ReferenceParameters;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.WsrfbfUnitTestsUtils;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.test.util.WsaUnitTestsUtils;

public class WsnbUnitTestsUtils {
		
	// ---------------------------------------------------------
	public static final String WSTOP_SPECIFICATION_NAME = "OASIS \"WS-Topics\"";
	
	public static final String WSTOP_XML_SCHEMA_PATH = "/schemas/t-1.xsd";	
	
	public static final String WSNB_SPECIFICATION_NAME = "OASIS \"WS-BaseNotification\"";
	
	public static final String WSNB_XML_SCHEMA_PATH = "/schemas/b-2.xsd";
	
		
	public static final String[] WSN_XML_SCHEMAS_PATHS = {
		WsaUnitTestsUtils.WSA_XML_SCHEMA_PATH,
		WsrfbfUnitTestsUtils.XML_XML_SCHEMA_PATH,	
		WsrfbfUnitTestsUtils.WSRFBF_XML_SCHEMA_PATH,		
		WsnbUnitTestsUtils.WSTOP_XML_SCHEMA_PATH,
		WsnbUnitTestsUtils.WSNB_XML_SCHEMA_PATH};
	// --------------------------------------------------------

	public static EndpointReferenceType createDefaultWsnProducerOriginator() throws URISyntaxException {
		EndpointReferenceType defaultMinimalEdp = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		URI address = new URI("http://www.anonymous.com/wsn/default::NotificationProducer@wsn-producer-edp"); 	
		Address defaultAddr = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
		defaultAddr.setValue(address);
		defaultMinimalEdp.setAddress(defaultAddr);		
		return defaultMinimalEdp;
	}
	
	public static EndpointReferenceType createDefaultWsnConsumerOriginator() throws URISyntaxException {
		EndpointReferenceType defaultMinimalEdp = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		URI address = new URI("http://www.anonymous.com/wsn/default::NotificationConsumer@wsn-consumer-edp"); 	
		Address defaultAddr = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
		defaultAddr.setValue(address);
		defaultMinimalEdp.setAddress(defaultAddr);		
		return defaultMinimalEdp;
	}
	
	
	public static EndpointReferenceType createDefaultWsnSubscriptionReference() throws Exception {
		EndpointReferenceType defaultMinimalEdp = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		URI address = new URI("http://www.anonymous.com/wsn/resource/subscription::SubscriptionManagerService@SubscriptionManagerServiceEndpoint"); 	
		Address defaultAddr = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
		defaultAddr.setValue(address);
		defaultMinimalEdp.setAddress(defaultAddr);	
		ReferenceParameters refparams = WSNUtil.getInstance().getXmlObjectFactory().create(ReferenceParameters.class);

		Element subscriptionUuidElt = null;
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		Document document;
		try {
			document = dbFactory.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			throw new Exception(e);
		}
		
		subscriptionUuidElt = document.createElementNS(WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getNamespaceURI(),
				WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getLocalPart());
		subscriptionUuidElt.setPrefix(WsnbConstants.SUBSCRIPTION_ID_QNAME_TAG.getPrefix());			
		subscriptionUuidElt.setNodeValue("12ae2225-fbeb-1400-aeae-12ac36bd6942");
		
		refparams.addAny(subscriptionUuidElt);
		defaultMinimalEdp.setReferenceParameters(refparams);
		
		return defaultMinimalEdp;
	}
		
	public static EndpointReferenceType createDefaultWsnPullPointOriginator() throws URISyntaxException {
		EndpointReferenceType defaultMinimalEdp = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		URI address = new URI("http://www.anonymous.com/wsn/default::NotificationPullPoint@wsn-pullpoint-edp"); 	
		Address defaultAddr = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
		defaultAddr.setValue(address);
		defaultMinimalEdp.setAddress(defaultAddr);	
		return defaultMinimalEdp;
	}
	
	public static TopicExpressionType createDefaultTopicExpression(URI dialect, String Content, List<QName> topicNss) throws WsnbException{
		TopicExpressionType topicExpression = null;
		try {
			topicExpression = RefinedWsnbFactory.getInstance().createTopicExpressionType(dialect);
			topicExpression.setContent("tns1:rootTopic/*/grandChildTopic1");
		
			for (QName tnsItem : topicNss) 
				topicExpression.addTopicNamespace(tnsItem.getLocalPart(), new URI(tnsItem.getNamespaceURI()));
			
		} catch (URISyntaxException e1) {
			throw new WsnbException(e1);
		} catch (WsnbException e) {
			throw new WsnbException(e);
		}
		return topicExpression;
	}
}
