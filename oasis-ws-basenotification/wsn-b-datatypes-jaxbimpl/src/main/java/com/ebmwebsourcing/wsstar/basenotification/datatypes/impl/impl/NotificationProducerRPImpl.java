package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationProducerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl.TopicSetTypeImpl;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.utils.WstopUtils;

public class NotificationProducerRPImpl implements NotificationProducerRP {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(NotificationProducerRPImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected NotificationProducerRPImpl() {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotificationProducerRP();		
	}

	protected NotificationProducerRPImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	protected final void setJaxbTypeObj(
			com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}
	@Override
	public final Boolean isFixedTopicSet() {
		return this.jaxbTypeObj.isFixedTopicSet();
	}

	@Override
	public final void setFixedTopicSet(Boolean value) {
		this.jaxbTypeObj.setFixedTopicSet(value);
	}

	@Override
	public final List<TopicExpressionType> getTopicExpressions() {
		List<TopicExpressionType> topicExpressions = new ArrayList<TopicExpressionType>();
		List<com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType> jaxbTopicExpressions = 
			this.jaxbTypeObj.getTopicExpression();
		if (jaxbTopicExpressions != null){
			for (com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbTopExprItem : jaxbTopicExpressions) {
				topicExpressions.add(new TopicExpressionTypeImpl(jaxbTopExprItem));
			}		
		}
		return topicExpressions;
	}

	@Override
	public final void addTopicExpression(TopicExpressionType value) {
		this.jaxbTypeObj.getTopicExpression().add(TopicExpressionTypeImpl.toJaxbModel(value));
	}

	@Override
	public final List<URI> getTopicExpressionDialects() {
		List<URI> dialects = new ArrayList<URI>();
		List<String> jaxbDialects = this.jaxbTypeObj.getTopicExpressionDialect();
		if (jaxbDialects != null){
			for (String uriItem : jaxbDialects) {
				try {
					dialects.add(new URI(uriItem));

				} catch (URISyntaxException e) {
					/*throw new WsaException(*/
					NotificationProducerRPImpl.logger.log(Level.WARNING,"The \"dialect\" value" +
							"value of the \"TopicExpression\" does not respect the URI Syntax (according to" +
							" RFC-2396/RFC-2732).\nUri string value is :\n\t " +  uriItem + "\n");			
				}
			}
		}
		return dialects;
	}

	@Override
	public final void addTopicExpressionDialect(URI dialect) {
		this.jaxbTypeObj.getTopicExpressionDialect().add(dialect.toString());
	}

	@Override
	public final TopicSetType getTopicSet() {
		return WstopUtils.fromJaxbModelTopicSetToApiTopicSet(this.jaxbTypeObj.getTopicSet(),this.getClass());
	}

	@Override
	public final void setTopicSet(TopicSetType topicSet) {
		this.getJaxbTypeObj().setTopicSet(TopicSetTypeImpl.toJaxbModel(topicSet));
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP}
	 *  "Jaxb model type" object from a {@link NotificationProducerRP} "api type" one  
	 * @param apiTypeObj
	 * @return
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP toJaxbModel(
			NotificationProducerRP apiTypeObj,
			com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP inheritJaxbTypeObj) {
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP jaxbTypeObj = null;

		if (apiTypeObj instanceof NotificationProducerRPImpl && inheritJaxbTypeObj == null){
			jaxbTypeObj = ((NotificationProducerRPImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (inheritJaxbTypeObj!=null)?
					inheritJaxbTypeObj : WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotificationProducerRP();

			// ~~~~ Set isFixedTopicSet ~~~~~
			jaxbTypeObj.setFixedTopicSet(apiTypeObj.isFixedTopicSet());

			// ~~~~ Set TopicSet ~~~~
			TopicSetType apiTopicSet = apiTypeObj.getTopicSet();
			if (apiTopicSet != null) {
				jaxbTypeObj.setTopicSet(TopicSetTypeImpl.toJaxbModel(apiTopicSet));
			}
			// ~~~~ Set TopicExpressionDialects ~~~~	
			List<URI> apiDialects = apiTypeObj.getTopicExpressionDialects();
			if (apiDialects != null){
				for (URI dialect : apiDialects) {
					jaxbTypeObj.getTopicExpressionDialect().add(dialect.toString());
				}
			}
			// ~~~~ Set TopicExpressionDialects ~~~~	
			List<TopicExpressionType> apiTopicExpressions = apiTypeObj.getTopicExpressions();
			if (apiTopicExpressions != null){
				for (TopicExpressionType topicExpression : apiTopicExpressions) {
					jaxbTypeObj.getTopicExpression().add(TopicExpressionTypeImpl.toJaxbModel(topicExpression));
				}
			}
		}
		return jaxbTypeObj;
	}
}
