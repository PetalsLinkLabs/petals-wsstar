package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class TopicExpressionTypeImpl implements TopicExpressionType {
	
	private static final String CUSTOM_XMLNS_PREFIX = "xmlns:"; 
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(TopicExpressionTypeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected TopicExpressionTypeImpl(URI dialect) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicExpressionType();		
		this.jaxbTypeObj.setDialect(dialect.toString());					
	}

	protected TopicExpressionTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	public final com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final String getContent() {
		String res = null;
		if (this.jaxbTypeObj.getContent() != null && this.jaxbTypeObj.getContent().size() > 0) {
			res = this.jaxbTypeObj.getContent().get(0).toString().trim();
		}
		return res;
	}

	@Override
	public final void setContent(String value) {
		final List<Object> objFromModel = this.jaxbTypeObj.getContent();

		if (objFromModel != null) {
			if (objFromModel.size() > 0) {
				objFromModel.clear();
			}
			objFromModel.add(0, value.trim());
		}
	}

	@Override
	public final URI getDialect() {
		// ~~ Note : Not be null according to related xml schema
		URI result = null;
		String uriAsString = this.jaxbTypeObj.getDialect();
		try {
			result = new URI(uriAsString);
		} catch (URISyntaxException e) {
			/*throw new WsaException(*/
			TopicExpressionTypeImpl.logger.log(Level.WARNING,"The \"Dialect\" field " +
					"value of the \"TopicExpression\" does not respect the URI Syntax (according to" +
					" RFC-2396/RFC-2732).\nUri string value is :\n\t " + uriAsString + "\n");			
		}
		return result;
	}

	@Override
	public final void setDialect(URI value) {
		this.jaxbTypeObj.setDialect(value.toString());
	}

	@Override
	public final List<QName> getTopicNamespaces() {
		List<QName> topicNSAsQNames = null;
		final Map<QName, String> objFromModel = this.jaxbTypeObj.getOtherAttributes();
		if (objFromModel != null){	
			topicNSAsQNames = new ArrayList<QName>();
			QName currentReformattedQName = null;
			String currentUri = null;
			for (QName jaxbQNameItem : objFromModel.keySet()) {
				currentUri = objFromModel.get(jaxbQNameItem);
				currentReformattedQName = new QName(currentUri, jaxbQNameItem.getLocalPart().replaceFirst(CUSTOM_XMLNS_PREFIX, ""));				
				topicNSAsQNames.add(currentReformattedQName);
			}
			
		}
		return topicNSAsQNames;
	}

	@Override
	public final void addTopicNamespace(String prefix, URI uri) {
		final Map<QName, String> objFromModel = this.jaxbTypeObj.getOtherAttributes();
		if (objFromModel != null) {
			//this.jaxbTypeObj.getOtherAttributes().put(new QName(uri.toString(), prefix), "xmlns");
			this.jaxbTypeObj.getOtherAttributes().remove(new QName("http://www.w3.org/XML/1998/namespace", prefix, "xml"));
			this.jaxbTypeObj.getOtherAttributes().put(new QName(CUSTOM_XMLNS_PREFIX+prefix),uri.toString());
		}
	}

	/**
	 * remove additional topic namespace stored in "otherAttribute jaxb model field
	 * 
	 * @param model
	 */
	public static void removeTopicNamspacesFromJaxbModel(TopicExpressionTypeImpl wsnbModelTopicExp) {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbModelObj = wsnbModelTopicExp.getJaxbTypeObj();
		
		final Map<QName, String> objFromModel = (jaxbModelObj!= null)?jaxbModelObj.getOtherAttributes():null;
		if (objFromModel != null) {
			objFromModel.clear();
		}
	} 
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.TopicExpressionType}
	 *  "Jaxb model type" object from a {@link TopicExpressionType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType toJaxbModel(TopicExpressionType apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof TopicExpressionTypeImpl){
			jaxbTypeObj = ((TopicExpressionTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createTopicExpressionType();

			jaxbTypeObj.setDialect(apiTypeObj.getDialect().toString());

			jaxbTypeObj.getContent().add(apiTypeObj.getContent());

			List<QName> topicNamespaces = apiTypeObj.getTopicNamespaces();
			for (QName qName : topicNamespaces) {
				jaxbTypeObj.getOtherAttributes().put(new QName(CUSTOM_XMLNS_PREFIX+qName.getLocalPart()),qName.getNamespaceURI());
			}		
		}
		return jaxbTypeObj;
	}
	
}
