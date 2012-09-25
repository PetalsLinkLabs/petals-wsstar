/**
 * Copyright (c) 2009 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * $Id$
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.wsnb.services.impl.topic;

import java.net.URI;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;

/**
 * The Ws topic constants
 * 
 * @author chamerling - eBM WebSourcing
 * 
 */
public class WstConstants {
	
	protected WstConstants() {
        // prevents calls from subclass
        throw new UnsupportedOperationException();
    }

    /**
     * 
     */
    public static final String NAMESPACE_URI = "http://docs.oasis-open.org/wsn/t-1";

    /**
     * 
     */
    public static final String PREFIX = "wstop";
    
    /**
     * 
     */
    public static final QName TOPIC_ATTR_QNAME = new QName(NAMESPACE_URI,"topic",PREFIX);
    
    /**
     * 
     */
    public static final String XML_SCHEMA_PREFIX = "xsi";
    
    /**
     * 
     */
    public static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";     
    
    /**
     * 
     */
    public static final String CONCRETE_TOPIC_URI = NAMESPACE_URI
            + "/TopicExpression/Concrete";

    /**
     * 
     */
    public static final String DIALECT = "dialect";

    /**
     * 
     */
    public static final String FINAL = "final";

    /**
     * 
     */
    public static final String FULL_TOPIC_URI = NAMESPACE_URI
            + "/TopicExpression/Full";

    /**
     * 
     */
    public static final String MESSAGE_TYPES = "messageTypes";

    /**
     * 
     */
    public static final QName PATTERN_QNAME = new QName(NAMESPACE_URI,
            "MessagePattern", PREFIX);

    /**
     * 
     */
    public static final String SIMPLE_TOPIC_URI = NAMESPACE_URI
            + "/TopicExpression/Simple";

    /**
     * 
     */
    public static final QName TOPIC_NAMESPACE_QNAME = new QName(NAMESPACE_URI,
            "TopicNamespace", PREFIX);

    /**
     * 
     */
    public static final QName TOPIC_QNAME = new QName(NAMESPACE_URI, "Topic",
            PREFIX);

    /**
     * 
     */
    public static final QName TOPIC_SET_QNAME = new QName(NAMESPACE_URI,
            "TopicSet", PREFIX);
    
    /**
     * 
     */
    public static final URI[] SUPPORTED_DIALECTS = {
    	WstopConstants.XPATH_TOPIC_EXPRESSION_DIALECT_URI,
    	WstopConstants.FULL_TOPIC_EXPRESSION_DIALECT_URI,
    	WstopConstants.CONCRETE_TOPIC_EXPRESSION_DIALECT_URI,
    	WstopConstants.SIMPLE_TOPIC_EXPRESSION_DIALECT_URI};
    
    // ---------------- Move from WstopConstants.java because depend on implemntation arbitrary choices ------------
    // ------ Used by TopicManager component as node added to supported topic tree ---------------------------
    public static final String EBM_RESOURCEIDS_PREFIX = "ebm";
	
	public static final String EBM_RESOURCEIDS_NAMESPACE_URI = "http://petals.ow2.org/ebmwebsourcing/specific/ResourceIds";
	
	private static final String EBM_TOPICS_EXTENSION_NAMESPACE_URI = "http://org.ow2.petals/ebmwebsourcing/specific/topicExtension";
	
	public static final QName RESOURCE_IDS_QNAME = new QName(EBM_RESOURCEIDS_NAMESPACE_URI, "ResourceIds",EBM_RESOURCEIDS_PREFIX);
	
	public static final QName REGISTRATION_ID_QNAME = new QName(EBM_RESOURCEIDS_NAMESPACE_URI, "RegistrationId",EBM_RESOURCEIDS_PREFIX);

	public static final QName SUBSCRIPTION_ID_QNAME = new QName(EBM_RESOURCEIDS_NAMESPACE_URI, "SubscriptionId",EBM_RESOURCEIDS_PREFIX);
	
	public static final QName CURRENT_MESSAGE_ID_QNAME = new QName(EBM_RESOURCEIDS_NAMESPACE_URI, "CurrentMessageId",EBM_RESOURCEIDS_PREFIX);
	
	public static final QName SUPPORTED_QNAME_ATTR = new QName(EBM_TOPICS_EXTENSION_NAMESPACE_URI, "supported", EBM_RESOURCEIDS_PREFIX);
	
	public static final QName SUBSCRIBE_RESPONSE_UUID_QNAME = new QName(EBM_RESOURCEIDS_NAMESPACE_URI, "SubscribeResponseUuid",EBM_RESOURCEIDS_PREFIX);
	
	public static final String UUID_ATTR_NAME = "uuid";
	
	public static final QName NEW_TOPIC_ADDITION = new QName(EBM_RESOURCEIDS_NAMESPACE_URI,"NewTopicAddition",EBM_RESOURCEIDS_PREFIX);
	
	public static final String NEW_TOPIC_ADD_DIALECT_FAULT_MESSAGE = "Unsupported Topic Dialect !\nOnly \"Simple\" or \"Concrete\" " +
		"Dialect is allowed if new supported topic addition is expected.";
	public static final String NEW_TOPIC_ADD_TOPIC_EXPR_FAULT_MESSAGE = "Bad Topic EXpression !\nOnly \"Simple\" or \"Concrete\" " +
	"Dialect is allowed if new supported topic addition is expected.";
// ----------------------------------------------------
	
	public static final String WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE = "http://www.ebmwebsourcing.com/wsnotification/specificTypes";
	
	public static final String WSNOTIFICATION_EXTENDED_TYPES_PREFIX = "ebm";
		
	public static final QName SOA_PARAMETER_QNAME = new QName(WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE,"SOAParameter",WSNOTIFICATION_EXTENDED_TYPES_PREFIX);

	public static final String RESOURCES_UUID_ROOT_TAG = "ResourceUuidList";
	
	public static final QName RESOURCES_UUID_QNAME = new QName(WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE,"ResourceUuidList",WSNOTIFICATION_EXTENDED_TYPES_PREFIX);

	public static final String XPATH_EXPRESSION_ROOT_TAG = "Xpath";
	
	public static final QName XPATHS_QNAME = new QName(WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE,"Xpath",WSNOTIFICATION_EXTENDED_TYPES_PREFIX);

	public static final QName TRANSFORM_POLICY_QNAME = new QName(WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE,"TransformPolicy",WSNOTIFICATION_EXTENDED_TYPES_PREFIX);

	public static final QName PROCESS_POLICY_QNAME = new QName(WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE,"ProcessPolicy",WSNOTIFICATION_EXTENDED_TYPES_PREFIX);

	public static final QName CONTEXT_POLICY_QNAME = new QName(WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE,"ContextPolicy",WSNOTIFICATION_EXTENDED_TYPES_PREFIX);

	public static final QName TERMINATION_TIME_QNAME = new QName(WSNOTIFICATION_EXTENDED_TYPES_NAMESPACE,"TerminationTime",WSNOTIFICATION_EXTENDED_TYPES_PREFIX);

    
}
