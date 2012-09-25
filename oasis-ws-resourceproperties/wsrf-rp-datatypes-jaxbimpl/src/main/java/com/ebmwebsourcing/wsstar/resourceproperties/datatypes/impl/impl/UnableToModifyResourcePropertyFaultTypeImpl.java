package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.ResourcePropertyChangeFailureType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction.UnableToModifyResourcePropertyFaultType;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.WsrfrpJAXBContext;

public class UnableToModifyResourcePropertyFaultTypeImpl extends
		BaseFaultTypeImpl implements UnableToModifyResourcePropertyFaultType {

	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnableToModifyResourcePropertyFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 * @param failure change failure details as {@link ResourcePropertyChangeFailureType} object 
	 */
	protected UnableToModifyResourcePropertyFaultTypeImpl(Date timestamp, ResourcePropertyChangeFailureType failure){
		super(Logger.getLogger(UnableToModifyResourcePropertyFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType jaxbTypeObj =
			new com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		jaxbTypeObj.setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(failure));

		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnableToModifyResourcePropertyFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnableToModifyResourcePropertyFaultTypeImpl.class.getSimpleName()));
	}
		
	@Override
	public ResourcePropertyChangeFailureType getResourcePropertyChangeFailure() {
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType refinedTypeFault = 
			((com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType)
					this.getJaxbTypeObj());
		
		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.ResourcePropertyChangeFailureType rpChangeFailureFromMofdel =
			refinedTypeFault.getResourcePropertyChangeFailure();		
		
		return new ResourcePropertyChangeFailureTypeImpl(rpChangeFailureFromMofdel);
	}

	@Override
	public void setResourcePropertyChangeFailure(
			ResourcePropertyChangeFailureType failure) {
		((com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType)
				this.getJaxbTypeObj()).setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(failure));
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultTypeImpl}
	 *  "Jaxb model type" object from a {@link UnableToModifyResourcePropertyFaultType} api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType toJaxbModel(UnableToModifyResourcePropertyFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof UnableToModifyResourcePropertyFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType)
			((UnableToModifyResourcePropertyFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.resource.resourceproperties.UnableToModifyResourcePropertyFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsrfrpJAXBContext.WSRFRP_JAXB_FACTORY.createUnableToModifyResourcePropertyFaultType());
			
			// ~~~~ Set ResourcePropertyChangeFailure value ~~~~			
			jaxbTypeObj.setResourcePropertyChangeFailure(ResourcePropertyChangeFailureTypeImpl.toJaxbModel(
					apiTypeObj.getResourcePropertyChangeFailure()));			
		}
		return jaxbTypeObj;
	}
}
