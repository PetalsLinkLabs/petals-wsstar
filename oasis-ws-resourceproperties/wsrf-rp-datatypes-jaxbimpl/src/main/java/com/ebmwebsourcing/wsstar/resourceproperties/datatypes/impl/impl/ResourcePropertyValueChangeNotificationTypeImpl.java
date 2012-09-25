package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyValueChangeNotificationType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class ResourcePropertyValueChangeNotificationTypeImpl implements
		ResourcePropertyValueChangeNotificationType {
	
	private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType jaxbTypeObj;

	/**
	 * Default constructor
	 */
	protected ResourcePropertyValueChangeNotificationTypeImpl(NewValues values) {		
		this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationType();
		this.jaxbTypeObj.setNewValues(NewValuesImpl.toJaxbModel(values));
	}

	protected ResourcePropertyValueChangeNotificationTypeImpl(
			com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public NewValues getNewValues() {
		return new NewValuesImpl(this.jaxbTypeObj.getNewValues());
	}

	@Override
	public void setNewValues(NewValues newTopicSetValue) {
		this.jaxbTypeObj.setNewValues(NewValuesImpl.toJaxbModel(newTopicSetValue));
	}

	@Override
	public OldValues getOldValues() {		
		return new OldValuesImpl(this.jaxbTypeObj.getOldValues().getValue());
	}

	@Override
	public void setOldValues(OldValues oldTopicSetValue) {
		this.jaxbTypeObj.setOldValues(
				WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationTypeOldValues(
						OldValuesImpl.toJaxbModel(oldTopicSetValue)));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType}
	 *  "Jaxb model type" object from a {@link ResourcePropertyValueChangeNotificationType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType toJaxbModel(
			ResourcePropertyValueChangeNotificationType apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof ResourcePropertyValueChangeNotificationTypeImpl){
			jaxbTypeObj = ((ResourcePropertyValueChangeNotificationTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationType();
		
			jaxbTypeObj.setNewValues(NewValuesImpl.toJaxbModel(apiTypeObj.getNewValues()));	
			
			OldValues oldVals = apiTypeObj.getOldValues();
			if (oldVals != null){
				jaxbTypeObj.setOldValues(
						WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationTypeOldValues(
								(OldValuesImpl.toJaxbModel(apiTypeObj.getOldValues()))));	
			}			
		}
						
		return jaxbTypeObj;
	}
	
	
	public static class NewValuesImpl implements NewValues{
		
		private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.NewValues jaxbTypeObj;

		/**
		 * Default constructor
		 */
		protected NewValuesImpl(Element value) {		
			this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationTypeNewValues();
			this.jaxbTypeObj.getAny().add(value);
		}

		protected NewValuesImpl(
				com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.NewValues jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}
		
		protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.NewValues getJaxbTypeObj() {
			return jaxbTypeObj;
		}
		
		@Override
		public List<Element> getValues() {
			List<Element> result = new ArrayList<Element>();
			
			List<Object> valuesFromModel = this.jaxbTypeObj.getAny();
			for (Object item : valuesFromModel) {
				if (item instanceof Element){
					result.add((Element)item);
				}
			}			
			return result;
		}

		@Override
		public void addValue(Element valueAsElt) {
			this.jaxbTypeObj.getAny().add(valueAsElt);
		}
		
		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType}
		 *  "Jaxb model type" object from a {@link ResourcePropertyValueChangeNotificationType} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.NewValues toJaxbModel(
				ResourcePropertyValueChangeNotificationType.NewValues apiTypeObj) {

			com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.NewValues jaxbTypeObj = null;
			
			if (apiTypeObj instanceof UpdateResourcePropertiesImpl){
				jaxbTypeObj = ((ResourcePropertyValueChangeNotificationTypeImpl.NewValuesImpl)apiTypeObj).getJaxbTypeObj();
			} else { 
				jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationTypeNewValues();
				List<Element> valuesFromApi = apiTypeObj.getValues();
				
				jaxbTypeObj.getAny().addAll(valuesFromApi);								
			}
							
			return jaxbTypeObj;
		}
	}
	
	
	public static class OldValuesImpl implements OldValues{
		
		private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.OldValues jaxbTypeObj;

		/**
		 * Default constructor
		 */
		protected OldValuesImpl(Element value) {		
			this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationTypeOldValues();
			this.jaxbTypeObj.getAny().add(value);
		}

		protected OldValuesImpl(
				com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.OldValues jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}
		
		protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.OldValues getJaxbTypeObj() {
			return jaxbTypeObj;
		}
		
		@Override
		public List<Element> getValues() {			
			List<Element> result = new ArrayList<Element>();
			
			List<Object> valuesFromModel = this.jaxbTypeObj.getAny();
			for (Object item : valuesFromModel) {
				if (item instanceof Element){
					result.add((Element)item);
				}
			}			
			return result;
		}

		@Override
		public void addValue(Element valueAsElt) {
			this.jaxbTypeObj.getAny().add(valueAsElt);	
		}
		
		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.OldValues}
		 *  "Jaxb model type" object from a {@link ResourcePropertyValueChangeNotificationType.OldValues} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.OldValues toJaxbModel(
				ResourcePropertyValueChangeNotificationType.OldValues apiTypeObj) {

			com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyValueChangeNotificationType.OldValues jaxbTypeObj = null;
			
			if (apiTypeObj instanceof UpdateResourcePropertiesImpl){
				jaxbTypeObj = ((ResourcePropertyValueChangeNotificationTypeImpl.OldValuesImpl)apiTypeObj).getJaxbTypeObj();
			} else { 
				jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createResourcePropertyValueChangeNotificationTypeOldValues();			
				jaxbTypeObj.getAny().addAll(apiTypeObj.getValues());					
			}
							
			return jaxbTypeObj;
		}
		
	}

}
