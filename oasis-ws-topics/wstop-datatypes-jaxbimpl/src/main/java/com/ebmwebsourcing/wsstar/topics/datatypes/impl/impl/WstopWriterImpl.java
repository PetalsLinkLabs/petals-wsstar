package com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.w3c.dom.Document;

import com.ebmwebsourcing.easycommons.research.util.dom.DOMUtil;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.WstopConstants;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopWriter;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.WstopJAXBContext;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.utils.WstopUtils;

public class WstopWriterImpl implements WstopWriter {
	
	//private Logger logger = Logger.getLogger(WsrfbfWriterImpl.class.getName());
	private WstopJAXBContext topicsJaxbContext = null;
		
	/**
     * Default constructor
     * 
     * @throws WSNotificationException
     */
    protected  WstopWriterImpl(){
    	this.topicsJaxbContext = WstopJAXBContext.getInstance();
	} 
        
    protected  WstopWriterImpl(String[] nsAndPrefixForMarshalling){
    	this.topicsJaxbContext = WstopJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}
	@Override
	public final Document writeMessagePatternAsDOM(QueryExpressionType value) throws WstopException {
		
		Document result = null;
    	if (value instanceof QueryExpressionTypeImpl){
    		try {

    			Marshaller marshaller = this.topicsJaxbContext.createWSTopicsMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();
    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType> element =
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType>(WstopConstants.MESSAGE_PATTERN_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType.class,((QueryExpressionTypeImpl)value).getJaxbTypeObj());
    				
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WstopException(WstopUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;			
	}

	@Override
	public final Document writeTopicNamespaceTypeAsDOM(TopicNamespaceType value)
			throws WstopException {
		Document result = null;
    	
		if (value instanceof TopicNamespaceTypeImpl){
    		try {

    			Marshaller marshaller = this.topicsJaxbContext.createWSTopicsMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();
    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType> element =
    				WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicNamespace(TopicNamespaceTypeImpl.toJaxbModel(value));
    				
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WstopException(WstopUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeTopicSetTypeAsDOM(TopicSetType value)
			throws WstopException {

		Document result = null;
    	if (value instanceof TopicSetTypeImpl){
    		try {

    			Marshaller marshaller = this.topicsJaxbContext.createWSTopicsMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();
    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType> element =
    				WstopJAXBContext.WSTOP_JAXB_FACTORY.createTopicSet(TopicSetTypeImpl.toJaxbModel(value));
    				
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WstopException(WstopUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeTopicTypeAsDOM(TopicType value) throws WstopException {
		Document result = null;
    	if (value instanceof TopicTypeImpl){
    		try {

    			Marshaller marshaller = this.topicsJaxbContext.createWSTopicsMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();
    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType> element =
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType>(WstopConstants.TOPIC_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType.class,((TopicTypeImpl)value).getJaxbTypeObj());
    				
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WstopException(WstopUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;			
	}

}
