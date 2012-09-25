package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.InvalidModificationFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class InvalidModificationFaultTypeImpl extends BaseFaultTypeImpl
	implements InvalidModificationFaultType {

	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link InvalidModificationFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 * @param failure change failure details as {@link ResourcePropertyChangeFailureType} object 
	 */
	protected InvalidModificationFaultTypeImpl(Date timestamp, ResourcePropertyChangeFailureType failure){
		super(Logger.getLogger(InvalidModificationFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		jaxbTypeObj.setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(failure));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected InvalidModificationFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(InvalidModificationFaultTypeImpl.class.getSimpleName()));
	}
	
	@Override
	public ResourcePropertyChangeFailureType getResourcePropertyChangeFailure() {		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType refinedTypeFault = 
			((com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType)
					this.getJaxbTypeObj());
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType rpChangeFailureFromMofdel =
			refinedTypeFault.getResourcePropertyChangeFailure();		
		
		return new ResourcePropertyChangeFailureTypeImpl(rpChangeFailureFromMofdel);
	}

	@Override
	public void setResourcePropertyChangeFailure(
			ResourcePropertyChangeFailureType failure) {
		((com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType)
				this.getJaxbTypeObj()).setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(failure));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType}
	 *  "Jaxb model type" object from a {@link InvalidModificationFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType toJaxbModel(InvalidModificationFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof InvalidModificationFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType)
			((InvalidModificationFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.InvalidModificationFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createInvalidModificationFaultType());
			
			// ~~~~ Set ResourcePropertyChangeFailure value ~~~~			
			jaxbTypeObj.setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(
					apiTypeObj.getResourcePropertyChangeFailure()));			
		}
		return jaxbTypeObj;
	}
	
}
