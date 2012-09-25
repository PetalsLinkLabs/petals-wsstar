package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils.WsrfrpException;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class UpdateTypeImpl implements UpdateType {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType jaxbTypeObj;
	private static Logger logger = Logger.getLogger(UpdateTypeImpl.class.getSimpleName());

	/**
	 * Default constructor
	 * @throws WsrfrpException 
	 */
	protected UpdateTypeImpl(List<Element> content) throws WsrfrpException {		
		this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUpdateType();
		if (content.isEmpty()){
			throw new WsrfrpException("According to \"WS-ResourceProperties\" specification XML schema" +
					" \"UpdateType\" object must have at least one property");
		}
		this.jaxbTypeObj.getAny().addAll(content);
	}

	protected UpdateTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public List<Element> getUpdateContent() {
		List<Element> properties = new ArrayList<Element>();
		List<Object> propertiesFromModel = this.jaxbTypeObj.getAny();
		for (Object propertyItem : propertiesFromModel) {
			if (propertyItem instanceof Element){
				properties.add((Element) propertyItem);
			}else {
				logger.log(Level.WARNING, "An item of the list is not typed as \"org.w3c.Dom Element\" object"+
				" It will be ignored");
			}
		}		
		return properties;	
	}

	@Override
	public void setUpdateContent(List<Element> values) {
		this.jaxbTypeObj.getAny().clear();
		this.jaxbTypeObj.getAny().addAll(values);
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType}
	 *  "Jaxb model type" object from a {@link UpdateType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType toJaxbModel(UpdateType apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UpdateTypeImpl){
			jaxbTypeObj = ((UpdateTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUpdateType();
		
			List<Element> properties = apiTypeObj.getUpdateContent();
			if (!properties.isEmpty()){
				jaxbTypeObj.getAny().addAll(properties);
			}		
		}
						
		return jaxbTypeObj;
	}
}
