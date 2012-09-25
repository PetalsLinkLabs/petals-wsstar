/**
 * Addressing Descriptor - SOA Tools Platform.
 * Copyright (c) 2008 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * XmlUtils.java
 * -------------------------------------------------------------------------
 */

package com.ebmwebsourcing.wsstar.topics.datatypes.impl.utils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType;
import com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.refinedabstraction.RefinedWstopFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.WstopJAXBContext;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl.WstopModelFactoryImpl;

public final class WstopUtils {

	public static final String PERSISTENCE_EXCEPETION_MSG = "Problem occurs during \"persistency\" process";

	private WstopUtils(){}
	
    /**
     * a very useful method that convert an "TopicSetType" Object from 
     * 	{@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType} 
     * to {@link TopicSetType}
     *  
     * @param jaxbModelTopicSet the object to convert
     * @param clazz class call originator
     * @return
     */
    public static TopicSetType fromJaxbModelTopicSetToApiTopicSet(
    		com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType jaxbModelTopicSet, Class<?> clazz){
    	TopicSetType apiTopicSet = null;    	
    	try {		
			if (jaxbModelTopicSet != null){
				apiTopicSet = ((WstopModelFactoryImpl)
						((RefinedWstopFactory)
								RefinedWstopFactory.getInstance()).getModel()).createWstopModelTopicSetType(jaxbModelTopicSet);
			}
		} catch (WstopException e) {
			Logger.getLogger(clazz.getSimpleName()).log(Level.WARNING, getConvertExceptionMsg(e,TopicSetType.class));
		}		
		return apiTopicSet;		
    }
  
    /**
     * a very useful method that convert a {@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType}
     * to a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic} one
     *  
     * @param jaxbModelTopicSet the object to convert
     * @param clazz class call originator
     * @return
     */
    public static com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic
     fromJaxbModelTopicTypeToJaxbModelTopicNamespaceTypeTopic(
    		com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType jaxbModelTopicType, Class<?> clazz){
    	
    	com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic jaxbTopicNamespaceTypeTopic =     	    	
    		WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicNamespaceTypeTopic();
    	
    	jaxbTopicNamespaceTypeTopic.setName(jaxbModelTopicType.getName());
    	    	
    	QueryExpressionType msgPattern = jaxbModelTopicType.getMessagePattern();
    	if (msgPattern != null){
    		jaxbTopicNamespaceTypeTopic.setMessagePattern(msgPattern);
    	}
    	
    	List<QName> msgTypes = jaxbModelTopicType.getMessageTypes();
    	if (msgTypes != null && msgTypes.size()>0){
    		jaxbTopicNamespaceTypeTopic.getMessageTypes().addAll(msgTypes);
    	}
    	
    	List<TopicType> children = jaxbModelTopicType.getTopic();
    	if(children != null && children.size()>0){
    		jaxbTopicNamespaceTypeTopic.getTopic().addAll(children);
    	}
    	
		return jaxbTopicNamespaceTypeTopic;		
    }
         
//    /**
//     * a very useful method that convert an "TopicExpressionType" Object from 
//     * 	{@link com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType} 
//     * to {@link TopicExpressionType}
//     *  
//     * @param jaxbModelTopicSet the object to convert
//     * @param clazz class call originator
//     * @return
//     */
//    public static TopicExpressionType fromJaxbModelTopicExpressionToApiTopicExpression(
//    		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbModelTopicExpression, Class<?> clazz){
//    	TopicExpressionType apiTopicExpression = null;    	
//    	try {		
//			if (jaxbModelTopicExpression != null){
//				apiTopicExpression = ((WsnbModelFactoryImpl)
//						((RefinedWsnbFactory)
//								RefinedWsnbFactory.getInstance()).getModel()).createWstopModelTopicExpression(jaxbModelTopicExpression);
//			}
//		} catch (WsnException e) {
//			Logger.getLogger(clazz.getSimpleName()).log(Level.WARNING, getConvertExceptionMsg(e,TopicExpressionType.class));
//		}		
//		return apiTopicExpression;		
//    }
    
    /**
     * provide default message for "Convert from jaxb model to api" Exception 
     * 
     * @param e the exception thrown
     * @param clazz the Type converted
     * @return generic Exception message as String
     */
    private static String getConvertExceptionMsg(WstopException e, Class<?> clazz){
    	return "a " + e.getClass().getSimpleName() +
		"exception have been thrown. This is due to a problem during conversion of the " +  clazz.getSimpleName() +
		" from type \"Jaxb model type\" to type \" api type\"";
    }
    
    /**
	 * provide a "generic" message content for Binding Exception 
	 * 
	 * @param instance the Object that can not be marshalled
	 * @return a string that represent a generic Exception message
	 */
	public static String getBindingExMessage(Object instance) {
		return "Failed to build XML binding from "+ instance.getClass().getSimpleName() + " Api Java classes";	
	}
}
