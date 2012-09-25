package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnrecognizedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnrecognizedPolicyRequestFaultTypeImpl extends BaseFaultTypeImpl
		implements UnrecognizedPolicyRequestFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnrecognizedPolicyRequestFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected UnrecognizedPolicyRequestFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(UnrecognizedPolicyRequestFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnrecognizedPolicyRequestFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnrecognizedPolicyRequestFaultTypeImpl.class.getSimpleName()));
	}
	
	@Override
	public final List<QName> getUnrecognizedPolicies() {
		return ((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType)this.getJaxbTypeObj()).getUnrecognizedPolicy();
	}
	
	@Override
	public final void addUnrecognizedPolicy(QName value) {
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType)this.getJaxbTypeObj()).getUnrecognizedPolicy().add(value);
	}
	
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType}
	 *  "Jaxb model type" object from a {@link UnrecognizedPolicyRequestFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType toJaxbModel(UnrecognizedPolicyRequestFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UnrecognizedPolicyRequestFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType)
				((UnrecognizedPolicyRequestFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnrecognizedPolicyRequestFaultType());
			
			// ~~~~ Set UnrecognizedPolicy QNames ~~~~~
			List<QName> unrecognizedPolicyQnames = apiTypeObj.getUnrecognizedPolicies();
			if (unrecognizedPolicyQnames != null && unrecognizedPolicyQnames.size() > 0){
				jaxbTypeObj.getUnrecognizedPolicy().addAll(unrecognizedPolicyQnames);
			}
		}
		return jaxbTypeObj;
	}
}
