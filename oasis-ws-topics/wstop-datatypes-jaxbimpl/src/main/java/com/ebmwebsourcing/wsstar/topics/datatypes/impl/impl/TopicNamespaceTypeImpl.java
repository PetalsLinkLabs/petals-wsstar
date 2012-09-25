package com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.WstopJAXBContext;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.utils.WstopUtils;

public class TopicNamespaceTypeImpl implements TopicNamespaceType {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(TopicNamespaceTypeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected TopicNamespaceTypeImpl(URI dialect) {		
		this.jaxbTypeObj = WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicNamespaceType();	
		this.jaxbTypeObj.setTargetNamespace(dialect.toString());
		TopicNamespaceTypeImpl.logger = Logger.getLogger(TopicNamespaceTypeImpl.class.getSimpleName());
	}

	protected TopicNamespaceTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
		TopicNamespaceTypeImpl.logger = Logger.getLogger(QueryExpressionTypeImpl.class.getSimpleName());
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType getJaxbTypeObj() {
		return jaxbTypeObj;
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
	public URI getNamespace() {
		// ~~ Note : Not be null according to related xml schema
		URI result = null;
		String uriAsString = this.jaxbTypeObj.getTargetNamespace();
		try {
			result = new URI(uriAsString);
		} catch (URISyntaxException e) {
			/*throw new WsaException(*/
			TopicNamespaceTypeImpl.logger.log(Level.WARNING,"The \"Dialect\" field " +
					"value of the \"TopicNamespace\" does not respect the URI Syntax (according to" +
					" RFC-2396/RFC-2732).\nUri string value is :\n\t " + uriAsString + "\n");			
		}
		return result;
	}

	@Override
	public void setNamespace(URI value) {
		this.jaxbTypeObj.setTargetNamespace(value.toString());
	}

	@Override
	public List<Topic> getTopics() {
		List<Topic> topics = new ArrayList<TopicNamespaceType.Topic>();
		List<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic> topicsFromModel =
			this.jaxbTypeObj.getTopic();
		if (topicsFromModel != null){
			for (com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic topicItem : topicsFromModel) {
				topics.add(new TopicImpl(topicItem));
			}
		}		
		return topics;
	}

	@Override
	public void addTopic(Topic value) {
		this.jaxbTypeObj.getTopic().add(TopicImpl.toJaxbModel(value));
	}

	@Override
	public boolean isFinal() {
		return this.jaxbTypeObj.isFinal();
	}
	
	@Override
	public void setFinal(boolean value) {
		this.jaxbTypeObj.setFinal(value);
	}
	
	@Override
	public List<Object> getAny() {
		return this.jaxbTypeObj.getAny();
	}
		
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType}
	 *  "Jaxb model type" object from a {@link TopicNamespaceType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType toJaxbModel(
			TopicNamespaceType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType jaxbTypeObj = null;

		if (apiTypeObj instanceof TopicNamespaceTypeImpl) {
			jaxbTypeObj = ((TopicNamespaceTypeImpl)apiTypeObj).getJaxbTypeObj();
		}
		else {
			jaxbTypeObj = WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicNamespaceType();
			
			URI targetNS = apiTypeObj.getNamespace();
			if (targetNS != null){
				jaxbTypeObj.setTargetNamespace(targetNS.toString());
			}
			
			String name = apiTypeObj.getName();
			if (name != null){
				jaxbTypeObj.setName(name);
			}
			
			List<Topic> topics = apiTypeObj.getTopics();
			if (topics != null && topics.size() >0){
				for (Topic topicItem : topics) {
					jaxbTypeObj.getTopic().add(TopicImpl.toJaxbModel(topicItem));
				}
			}
			
			jaxbTypeObj.setFinal(apiTypeObj.isFinal());			
		}

		return jaxbTypeObj;
	}

	public static class TopicImpl extends TopicTypeImpl implements Topic {
				
		/**
		 * Default constructor
		 * 
		 */
		protected TopicImpl(String value){
			super();			
			this.jaxbTypeObj = WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicNamespaceTypeTopic();						
			this.jaxbTypeObj.setName(value);
		}
		
		protected TopicImpl(com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic jaxbTypeObj){
			super(jaxbTypeObj);
		}

		protected final com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic getJaxbTypeObj() {
			return (com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic) this.jaxbTypeObj;
		}		
		
		@Override
		public String getParent() {
			return ((com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic)this.jaxbTypeObj).getParent();			
		}

		@Override
		public void setParent(String parent) {
			((com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic)this.jaxbTypeObj).setParent(parent);
		}
		
		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic}
		 *  "Jaxb model type" object from a {@link TopicNamespaceType.Topic} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic toJaxbModel(
				TopicNamespaceType.Topic apiTypeObj){
			
			com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.Topic jaxbTypeObj = null;
			
			if (apiTypeObj instanceof TopicNamespaceTypeImpl.TopicImpl) {
				jaxbTypeObj =((TopicNamespaceTypeImpl.TopicImpl)apiTypeObj).getJaxbTypeObj();
			}
			else {
				com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType partialJaxbTypeObj =
					TopicTypeImpl.toJaxbModel(apiTypeObj);
			
				jaxbTypeObj = WstopUtils.fromJaxbModelTopicTypeToJaxbModelTopicNamespaceTypeTopic(partialJaxbTypeObj, TopicImpl.class);	
				
				String parentAtt = apiTypeObj.getParent();
				if (parentAtt != null){
					jaxbTypeObj.setParent(parentAtt);
				}
			}
			return jaxbTypeObj;
		}
	}
	
}
