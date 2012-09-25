package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UpdateResourcePropertiesRequestFailedFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class UpdateResourcePropertiesRequestFailedFaultTypeImpl extends
		BaseFaultTypeImpl implements
		UpdateResourcePropertiesRequestFailedFaultType {

	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UpdateResourcePropertiesRequestFailedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 * @param failure change failure details as {@link ResourcePropertyChangeFailureType} object 
	 */
	protected UpdateResourcePropertiesRequestFailedFaultTypeImpl(Date timestamp, ResourcePropertyChangeFailureType failure){
		super(Logger.getLogger(UpdateResourcePropertiesRequestFailedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		jaxbTypeObj.setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(failure));

		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UpdateResourcePropertiesRequestFailedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UpdateResourcePropertiesRequestFailedFaultTypeImpl.class.getSimpleName()));
	}
	
	@Override
	public ResourcePropertyChangeFailureType getResourcePropertyChangeFailure() {
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType refinedTypeFault = 
			((com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType)
					this.getJaxbTypeObj());
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType rpChangeFailureFromMofdel =
			refinedTypeFault.getResourcePropertyChangeFailure();		
		
		return new ResourcePropertyChangeFailureTypeImpl(rpChangeFailureFromMofdel);
	}

	@Override
	public void setResourcePropertyChangeFailure(
			ResourcePropertyChangeFailureType failure) {
		((com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType)
				this.getJaxbTypeObj()).setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(failure));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType}
	 *  "Jaxb model type" object from a {@link UpdateResourcePropertiesRequestFailedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType toJaxbModel(UpdateResourcePropertiesRequestFailedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UpdateResourcePropertiesRequestFailedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType)
			((UpdateResourcePropertiesRequestFailedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UpdateResourcePropertiesRequestFailedFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUpdateResourcePropertiesRequestFailedFaultType());
			
			// ~~~~ Set ResourcePropertyChangeFailure value ~~~~			
			jaxbTypeObj.setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(
					apiTypeObj.getResourcePropertyChangeFailure()));			
		}
		return jaxbTypeObj;
	}

}
