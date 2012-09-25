package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsupportedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnsupportedPolicyRequestFaultTypeImpl extends BaseFaultTypeImpl
		implements UnsupportedPolicyRequestFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link UnsupportedPolicyRequestFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected UnsupportedPolicyRequestFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(UnsupportedPolicyRequestFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected UnsupportedPolicyRequestFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(UnsupportedPolicyRequestFaultTypeImpl.class.getSimpleName()));
	}
	
	@Override
	public final List<QName> getUnsupportedPolicies() {
		return ((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType)this.getJaxbTypeObj()).getUnsupportedPolicy();

	}
	
	@Override
	public final void addUnsupportedPolicy(QName value) {
		((com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType)this.getJaxbTypeObj()).getUnsupportedPolicy().add(value);
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType}
	 *  "Jaxb model type" object from a {@link UnsupportedPolicyRequestFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType toJaxbModel(UnsupportedPolicyRequestFaultType apiTypeObj){

com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UnsupportedPolicyRequestFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType)
				((UnsupportedPolicyRequestFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnsupportedPolicyRequestFaultType());
			
			// ~~~~ Set UnsupportedPolicy QNames ~~~~~
			List<QName> unsupportedPolicyQnames = apiTypeObj.getUnsupportedPolicies();
			if (unsupportedPolicyQnames != null && unsupportedPolicyQnames.size() > 0){
				jaxbTypeObj.getUnsupportedPolicy().addAll(unsupportedPolicyQnames);
			}
		}
		return jaxbTypeObj;
		
	}
}
