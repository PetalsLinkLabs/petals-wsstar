package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class ResourcePropertyChangeFailureTypeImpl implements ResourcePropertyChangeFailureType {

	private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType jaxbTypeObj;
	private static Logger logger = Logger.getLogger(ResourcePropertyChangeFailureTypeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected ResourcePropertyChangeFailureTypeImpl(boolean value) {
		this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyChangeFailureType();
		this.jaxbTypeObj.setRestored(value);
	}
	
	protected ResourcePropertyChangeFailureTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public CurrentValue getCurrentValue() {
		CurrentValue result = null;
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.CurrentValue valueFromModel = 
			this.jaxbTypeObj.getCurrentValue();
		
		if (valueFromModel != null){
			result = new CurrentValueImpl(valueFromModel);
		}				
		return result;
	}

	@Override
	public void setCurrentValue(CurrentValue value) {
		this.jaxbTypeObj.setCurrentValue(CurrentValueImpl.toJaxbModel(value));
	}

	@Override
	public RequestedValue getRequestedValue() {
		
		RequestedValue result = null; 
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.RequestedValue valueFromModel = 
			this.jaxbTypeObj.getRequestedValue();
		
		if (valueFromModel != null){
			result = new RequestedValueImpl(valueFromModel);
		}		
		return result;
	}

	@Override
	public void setRequestedValue(RequestedValue values) {
		this.jaxbTypeObj.setRequestedValue(RequestedValueImpl.toJaxbModel(values));
	}

	@Override
	public boolean isRestored() {
		return this.jaxbTypeObj.isRestored().booleanValue();
	}

	@Override
	public void setRestored(boolean bool) {
		this.jaxbTypeObj.setRestored(bool);
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType}
	 *  "Jaxb model type" object from a {@link ResourcePropertyChangeFailureType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType toJaxbModel(
			ResourcePropertyChangeFailureType apiTypeObj){
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof ResourcePropertyChangeFailureTypeImpl) {
			jaxbTypeObj = ((ResourcePropertyChangeFailureTypeImpl)apiTypeObj).getJaxbTypeObj();
		}
		else {
			jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyChangeFailureType();			

			// ~~~ Set "isRestored" value ~~~
			jaxbTypeObj.setRestored(apiTypeObj.isRestored());
			
			// ~~~ Set "CurrentValue" value ~~~
			CurrentValue curVal = apiTypeObj.getCurrentValue();			
			if (curVal != null){
				jaxbTypeObj.setCurrentValue(CurrentValueImpl.toJaxbModel(curVal));
			}
			
			// ~~~ Set "RequestedValue ~~~
			RequestedValue reqVal = apiTypeObj.getRequestedValue();
			if (reqVal != null){
				jaxbTypeObj.setRequestedValue(RequestedValueImpl.toJaxbModel(reqVal));
			}
			
		}
		return jaxbTypeObj;
	}
	
	public static class CurrentValueImpl implements ResourcePropertyChangeFailureType.CurrentValue {
		
		private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.CurrentValue jaxbTypeObj;

		/**
		 * Default constructor
		 * 
		 */
		protected CurrentValueImpl(List<Element> values){
			this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyChangeFailureTypeCurrentValue();
			this.jaxbTypeObj.getAny().addAll(values);
		}
		
		protected CurrentValueImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.CurrentValue jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}
		
		protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.CurrentValue getJaxbTypeObj() {
			return jaxbTypeObj;
		}
		
		@Override
		public List<Element> getAny() {
			List<Element> values = new  ArrayList<Element>();
			
			List<Object> valueFromModel = this.jaxbTypeObj.getAny();
			for (Object valFromModelItem : valueFromModel) {
				if(valFromModelItem instanceof Element){
					values.add((Element) valFromModelItem);
				}else {
					logger.log(Level.WARNING, "An item of the list is not typed as \"org.w3c.Dom Element\" object" +
							" It will be ignored");
				}
			}		
			return values;
		}

		@Override
		public void setAny(List<Element> values) {
			List<Object> anyFromModel = this.jaxbTypeObj.getAny();
			if (anyFromModel.size()>0) {
				anyFromModel.clear();
			}
			this.jaxbTypeObj.getAny().addAll(values);			
		}
		
		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.CurrentValue}
		 *  "Jaxb model type" object from a {@link ResourcePropertyChangeFailureType.CurrentValue} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.CurrentValue toJaxbModel(
				ResourcePropertyChangeFailureType.CurrentValue apiTypeObj){
			
			com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.CurrentValue jaxbTypeObj = null;
			
			if (apiTypeObj instanceof ResourcePropertyChangeFailureTypeImpl.CurrentValueImpl) {
				jaxbTypeObj = ((ResourcePropertyChangeFailureTypeImpl.CurrentValueImpl)apiTypeObj).getJaxbTypeObj();
			}
			else {
				jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyChangeFailureTypeCurrentValue();			

				List<Element> content = apiTypeObj.getAny();				
				jaxbTypeObj.getAny().addAll(content);
				
			}
			return jaxbTypeObj;
		}
	}
	
	
	public static class RequestedValueImpl implements ResourcePropertyChangeFailureType.RequestedValue {
		
		private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.RequestedValue jaxbTypeObj;

		/**
		 * Default constructor
		 * 
		 */
		protected RequestedValueImpl(List<Element> values){
			this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyChangeFailureTypeRequestedValue();
			this.jaxbTypeObj.getAny().addAll(values);
		}
		
		protected RequestedValueImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.RequestedValue jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}
		
		protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.RequestedValue getJaxbTypeObj() {
			return jaxbTypeObj;
		}
		
		@Override
		public List<Element> getAny() {
			List<Element> values = new ArrayList<Element>();
			
			List<Object> valueFromModel = this.jaxbTypeObj.getAny();
			for (Object valFromModelItem : valueFromModel) {
				if(valFromModelItem instanceof Element){
					values.add((Element) valFromModelItem);
				}else {
					logger.log(Level.WARNING, "An item of the list is not typed as \"org.w3c.Dom Element\" object" +
							" It will be ignored");
				}
			}		
			return values;
		}
		
		@Override
		public void setAny(List<Element> values) {
			List<Object> anyFromModel = this.jaxbTypeObj.getAny();
			if (anyFromModel.size()>0) {
				anyFromModel.clear();
			}
			this.jaxbTypeObj.getAny().addAll(values);
		}
		
		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.RequestedValue}
		 *  "Jaxb model type" object from a {@link ResourcePropertyChangeFailureType.RequestedValue} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.RequestedValue toJaxbModel(
				ResourcePropertyChangeFailureType.RequestedValue apiTypeObj){
			
			com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType.RequestedValue jaxbTypeObj = null;
			
			if (apiTypeObj instanceof ResourcePropertyChangeFailureTypeImpl.RequestedValueImpl) {
				jaxbTypeObj = ((ResourcePropertyChangeFailureTypeImpl.RequestedValueImpl)apiTypeObj).getJaxbTypeObj();
			}
			else {
				jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyChangeFailureTypeRequestedValue();			

				List<Element> content = apiTypeObj.getAny();				
				jaxbTypeObj.getAny().addAll(content);
				
			}
			return jaxbTypeObj;
		}
	}

}
