package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.GetResourcePropertyResponse;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class GetResourcePropertyResponseImpl implements GetResourcePropertyResponse {

	private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse jaxbTypeObj;
	private static Logger logger = Logger.getLogger(GetResourcePropertyResponseImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected GetResourcePropertyResponseImpl() {
		this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createGetResourcePropertyResponse();							
	}
	
	protected GetResourcePropertyResponseImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public List<Element> getPropertyValue() {
		List<Element> values = new  ArrayList<Element>();
		
		List<Object> valueFromModel = this.jaxbTypeObj.getAny();
		for (Object valFromModelItem : valueFromModel) {
			if(valFromModelItem instanceof Element){
				values.add((Element) valFromModelItem);
			}else {
				logger.log(Level.WARNING, "An item of the list is not typed as \"org.w3c.Dom Element\" object." +
						" It will be ignored");
			}
		}		
		return values;
	}

	@Override
	public void setPropertyValue(List<Element> value) {
		List<Object> anyFromModel = this.jaxbTypeObj.getAny();
		if (anyFromModel.size()>0) {
			anyFromModel.clear();
		}
		this.jaxbTypeObj.getAny().addAll(value);
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse}
	 *  "Jaxb model type" object from a {@link GetResourcePropertyResponse} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse toJaxbModel(GetResourcePropertyResponse apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.GetResourcePropertyResponse jaxbTypeObj = null;

		if (apiTypeObj instanceof GetResourcePropertyResponseImpl){
			jaxbTypeObj = ((GetResourcePropertyResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createGetResourcePropertyResponse();

			List<Element> values  = apiTypeObj.getPropertyValue();		
			if (values != null){
				jaxbTypeObj.getAny().addAll(values);
			}
		}
		return jaxbTypeObj;
	}
}
