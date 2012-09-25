package com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.WstopJAXBContext;

public class TopicTypeImpl implements TopicType {
	
	protected com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected TopicTypeImpl(String name) {		
		this.jaxbTypeObj = WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicType();	
		this.jaxbTypeObj.setName(name);
	}

	protected TopicTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	/**
	 * default, called by inheritance class {@link TopicNamespaceType.Topic} 
	 */
	protected TopicTypeImpl() {}
			
	protected com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public boolean isFinal() {
		Boolean result =  this.jaxbTypeObj.isFinal();
		return ((result != null) && result.booleanValue());
	}

	@Override
	public void setFinal(boolean value) {
		this.jaxbTypeObj.setFinal(value);
	}

	@Override
	public QueryExpressionType getMessagePattern() {
		com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType queryExprFromModel = 
			this.jaxbTypeObj.getMessagePattern();
		return (queryExprFromModel != null)?new QueryExpressionTypeImpl(queryExprFromModel):null;
	}

	@Override
	public void setMessagePattern(QueryExpressionType value) {
		this.jaxbTypeObj.setMessagePattern(QueryExpressionTypeImpl.toJaxbModel(value));
	}

	@Override
	public List<QName> getMessageTypes() {
		return this.jaxbTypeObj.getMessageTypes(); 
	}

	@Override
	public void addMessageType(QName value) {
		this.jaxbTypeObj.getMessageTypes().add(value);
	}

	@Override
	public String getName() {
		return this.jaxbTypeObj.getName();
	}

	@Override
	public void setName(String value) {
		this.jaxbTypeObj.setName(value);
	}

	@Override
	public List<TopicType> getTopics() {
		List<TopicType> children = new ArrayList<TopicType>();
		List<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType> childsFromModel = this.jaxbTypeObj.getTopic();
		if (childsFromModel != null && childsFromModel.size()>0){
			for (com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType topicItem : childsFromModel) {
				children.add(new TopicTypeImpl(topicItem));
			}
		}		
		return children;
	}

	@Override
	public void addTopic(TopicType value) {
		jaxbTypeObj.getTopic().add(TopicTypeImpl.toJaxbModel(value));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType}
	 * object from a {@link TopicType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType toJaxbModel(TopicType apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof TopicTypeImpl){
			jaxbTypeObj = ((TopicTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicType();

			jaxbTypeObj.setName(apiTypeObj.getName());
			
			jaxbTypeObj.setFinal(apiTypeObj.isFinal());
			
			QueryExpressionType msgPattern =  apiTypeObj.getMessagePattern();
			if (msgPattern != null){
				jaxbTypeObj.setMessagePattern(QueryExpressionTypeImpl.toJaxbModel(msgPattern));
			}
			
			List<QName> msgTypes = apiTypeObj.getMessageTypes();
			if (msgTypes != null && msgTypes.size() > 0){
				jaxbTypeObj.getMessageTypes().addAll(msgTypes);
			}
			
			List<TopicType> childs = apiTypeObj.getTopics();
			if (childs != null && childs.size()>0){
				for (TopicType topicItem : childs) {
					jaxbTypeObj.getTopic().add(TopicTypeImpl.toJaxbModel(topicItem));
				}
			}
			
		}
		return jaxbTypeObj;		
	}

}
