package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MessageContentExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ProducerPropertiesExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class FilterTypeImpl implements FilterType {

    private com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType jaxbTypeObj;
    //	private static Logger logger  = Logger.getLogger(FilterTypeImpl.class.getSimpleName());

    /**
     * Default constructor
     */
    protected FilterTypeImpl() {		
        this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createFilterType();							
    }

    protected FilterTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType jaxbTypeObj){
        this.jaxbTypeObj = jaxbTypeObj;
    }

    protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType getJaxbTypeObj() {
        return jaxbTypeObj;
    }

    @Override
    public final List<TopicExpressionType> getTopicExpressions() {

        List<TopicExpressionType> topicExpressions = null;

        final List<Object> anyFromModel = this.jaxbTypeObj.getAny();
        if (anyFromModel != null) {

            topicExpressions = new ArrayList<TopicExpressionType>();

            for (final Object item : anyFromModel) {

                TopicExpressionType currentTopicExpression = null;

                if (item instanceof JAXBElement<?> && ((JAXBElement<?>) item).getValue() instanceof
                        com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType) {

                    com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbObj =	
                        (com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType)((JAXBElement<?>) item).getValue();

                    currentTopicExpression = new TopicExpressionTypeImpl(jaxbObj);

                    com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType topicJaxb = (com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType)((JAXBElement<?>) item).getValue() ;

                    topicExpressions.add(currentTopicExpression);		
                }					
            }
        }
        return topicExpressions;
    }

    @Override
    public final void addTopicExpression(TopicExpressionType value) {
        final List<Object> anyFromModel = this.jaxbTypeObj.getAny();		
        if (anyFromModel != null) {				
            anyFromModel.add(WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicExpression(TopicExpressionTypeImpl.toJaxbModel(value)));
        }
    }

    @Override
    public final List<MessageContentExpression> getMessageContentExpressions() {
        List<MessageContentExpression> messageContentExpressions = null;

        final List<Object> anyFromModel = this.jaxbTypeObj.getAny();
        if (anyFromModel != null) {

            messageContentExpressions = new ArrayList<MessageContentExpression>();

            for (final Object item : anyFromModel) {

                if (item instanceof JAXBElement<?> && 
                        (((JAXBElement<?>) item).getValue() instanceof 
                                com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType) &&
                                ((JAXBElement<?>)item).getName().equals(WsnbConstants.MESSAGE_CONTENT_QNAME)) {

                    messageContentExpressions.add(new MessageContentExpressionImpl(
                            (com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType)((JAXBElement<?>) item).getValue()));

                }						
            }
        }
        return messageContentExpressions;
    }

    @Override
    public final void addMessageContentExpression(MessageContentExpression value) {
        final List<Object> anyFromModel = this.jaxbTypeObj.getAny();		
        if (anyFromModel != null) {		
            anyFromModel.add(WsnbJAXBContext.WSNB_JAXB_FACTORY.createMessageContent(MessageContentExpressionImpl.toJaxbModel(value)));
        }
    }

    @Override
    public final List<ProducerPropertiesExpression> getProducerPropertiesExpressions() {
        List<ProducerPropertiesExpression> producerPropertiesExpressions = null;

        final List<Object> anyFromModel = this.jaxbTypeObj.getAny();
        if (anyFromModel != null) {

            producerPropertiesExpressions = new ArrayList<ProducerPropertiesExpression>();

            for (final Object item : anyFromModel) {

                if (item instanceof JAXBElement<?> && 
                        (((JAXBElement<?>) item).getValue() instanceof 
                                com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType) &&
                                ((JAXBElement<?>)item).getName().equals(WsnbConstants.PRODUCER_PROPERTIES_QNAME)) {

                    producerPropertiesExpressions.add(new ProducerPropertiesExpressionImpl(
                            (com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType)((JAXBElement<?>) item).getValue()));
                }						
            }
        }
        return producerPropertiesExpressions;
    }

    @Override
    public final void addProducerPropertiesExpression(ProducerPropertiesExpression value) {
        final List<Object> anyFromModel = this.jaxbTypeObj.getAny();		
        if (anyFromModel != null) {		
            anyFromModel.add(WsnbJAXBContext.WSNB_JAXB_FACTORY.createProducerProperties(ProducerPropertiesExpressionImpl.toJaxbModel(value)));
        }
    }

    /**
     * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.FilterType}
     *  "Jaxb model type" object from a {@link FilterType} "api type" one  
     *    
     * @param apiTypeObj
     */
    public static com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType toJaxbModel(FilterType apiTypeObj) {

        com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType jaxbTypeObj = null;

        if (apiTypeObj instanceof FilterTypeImpl){
            jaxbTypeObj = ((FilterTypeImpl)apiTypeObj).getJaxbTypeObj();
        } else {
            jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createFilterType();

            // ~~~~~ build "TopicExpressionType" list ~~~~~
            List<TopicExpressionType> topicExpressions = apiTypeObj.getTopicExpressions();
            if (topicExpressions != null){
                for (TopicExpressionType topicExpression : topicExpressions) {
                    jaxbTypeObj.getAny().add(WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicExpression(
                            TopicExpressionTypeImpl.toJaxbModel(topicExpression)));
                }
            }
            // ~~~~~ build "MessageContentExpression" list ~~~~~
            List<MessageContentExpression> messageContentExpressions = apiTypeObj.getMessageContentExpressions();
            if (messageContentExpressions != null){
                for (MessageContentExpression messageContentExpression : messageContentExpressions) {
                    jaxbTypeObj.getAny().add(WsnbJAXBContext.WSNB_JAXB_FACTORY.createMessageContent(
                            MessageContentExpressionImpl.toJaxbModel(messageContentExpression)));			
                }
            }
            // ~~~~~ build "ProducerPropertiesExpression" list ~~~~~
            List<ProducerPropertiesExpression> producerPropertiesExpressions = apiTypeObj.getProducerPropertiesExpressions();
            if (producerPropertiesExpressions != null){
                for (ProducerPropertiesExpression producerPropertiesExpression : producerPropertiesExpressions) {
                    jaxbTypeObj.getAny().add(WsnbJAXBContext.WSNB_JAXB_FACTORY.createProducerProperties(
                            ProducerPropertiesExpressionImpl.toJaxbModel(producerPropertiesExpression)));
                }
            }
        }
        return jaxbTypeObj;
    }
}
