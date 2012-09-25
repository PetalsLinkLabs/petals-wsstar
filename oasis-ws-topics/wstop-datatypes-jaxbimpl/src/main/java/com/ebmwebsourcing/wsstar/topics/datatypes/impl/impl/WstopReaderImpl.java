package com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopReader;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.WstopJAXBContext;

public class WstopReaderImpl implements WstopReader {

	private WstopJAXBContext topicsJaxbContext = null;
	//private Logger logger = Logger.getLogger(WsrfbfReaderImpl.class.getName());
		
	/**
	 * Default constructor
	 */
	protected WstopReaderImpl() {
		this.topicsJaxbContext = WstopJAXBContext.getInstance();
	}
	
	protected WstopReaderImpl(String[] nsAndPrefixForMarshalling) {
		this.topicsJaxbContext = WstopJAXBContext.getInstance(nsAndPrefixForMarshalling);
		this.topicsJaxbContext.addNsAndPrefixMapping(nsAndPrefixForMarshalling);
	}	
	
	@Override
	public final QueryExpressionType readMessagePattern(Document document)
			throws WstopException {
		QueryExpressionType result = null;
		
		try {
			Unmarshaller unmarshaller = this.topicsJaxbContext.createWSTopicsUnmarshaller();
			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType.class);
			if (schemaBinding.getValue() != null){
				result = new QueryExpressionTypeImpl(schemaBinding.getValue());
			}

		} catch (JAXBException e) {
			throw new WstopException(e);
		} 	
		return result;
	}

	@Override
	public final TopicNamespaceType readTopicNamespaceType(Document document)
			throws WstopException {
		TopicNamespaceType result = null;
		
		if(!document.getDocumentElement().getLocalName().equals("TopicNamespace")) {
		    throw new WstopException("This document is not a TopicNamespace");
		}
		
		try {
			Unmarshaller unmarshaller = this.topicsJaxbContext.createWSTopicsUnmarshaller();
			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicNamespaceType.class);
			if (schemaBinding.getValue() != null){
				result = new TopicNamespaceTypeImpl(schemaBinding.getValue());
			}

		} catch (JAXBException e) {
			throw new WstopException(e);
		} 	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopReader#readTopicSetType(org.w3c.dom.Document)
	 */
	@Override
	public final TopicSetType readTopicSetType(Document document) throws WstopException {
		TopicSetType result = null;
		
		try {
			Unmarshaller unmarshaller = this.topicsJaxbContext.createWSTopicsUnmarshaller();
			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType.class);
			if (schemaBinding.getValue() != null){
				result = new TopicSetTypeImpl(schemaBinding.getValue());
			}

		} catch (JAXBException e) {
			throw new WstopException(e);
		} 	
		return result;
	}
	
	/**
     * Read a TopicSetType XML definition from an {@link InputSource} object
     * into a WS-Topics JAXB model {@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType} instance.
     * 
     * @param inputSource
     *            an InputSource pointing to the SchemaImpl document, an XML
     *            document obeying the SchemaImpl parent.
     * @return the definition described in the document pointed to by the
     *         InputSource.
     *         
	 * @throws WstopException
     */
	@Override
	public final TopicSetType readTopicSetType(InputSource source) throws WstopException{
		TopicSetType topicSetObj=null;
		try {
			final Unmarshaller unmarshaller = this.topicsJaxbContext.createWSTopicsUnmarshaller();

			final SAXSource saxSource = new SAXSource(source);

			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType> eprBinding = unmarshaller.unmarshal(
					saxSource, com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType.class);

			topicSetObj = ((eprBinding!=null)? new TopicSetTypeImpl(eprBinding.getValue()):null);
			
		} catch (final JAXBException e) {
			throw new WstopException(e);
		} 
		return topicSetObj;
	}
	
	@Override
	public final TopicType readTopicType(Document document) throws WstopException {
		TopicType result = null;		
		try {
			Unmarshaller unmarshaller = this.topicsJaxbContext.createWSTopicsUnmarshaller();
			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicType.class);
			if (schemaBinding.getValue() != null){
				result = new TopicTypeImpl(schemaBinding.getValue());
			}

		} catch (JAXBException e) {
			throw new WstopException(e);
		} 	
		return result;
	}

}
