package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourceProperties;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class UpdateResourcePropertiesImpl implements UpdateResourceProperties {

	private com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected UpdateResourcePropertiesImpl(UpdateType content) {		
		this.jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUpdateResourceProperties();
		this.setUpdate(content);
	}

	protected UpdateResourcePropertiesImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public UpdateType getUpdate() {
		return new UpdateTypeImpl(this.jaxbTypeObj.getUpdate());
	}

	@Override
	public void setUpdate(UpdateType value) {
		this.jaxbTypeObj.setUpdate(UpdateTypeImpl.toJaxbModel(value));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties}
	 *  "Jaxb model type" object from a {@link UpdateResourceProperties} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties toJaxbModel(UpdateResourceProperties apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourceProperties jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UpdateResourcePropertiesImpl){
			jaxbTypeObj = ((UpdateResourcePropertiesImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUpdateResourceProperties();
		
			jaxbTypeObj.setUpdate(UpdateTypeImpl.toJaxbModel(apiTypeObj.getUpdate()));	
		}
						
		return jaxbTypeObj;
	}
}
