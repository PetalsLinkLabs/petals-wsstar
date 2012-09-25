package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class SubscriptionPolicyTypeImpl implements SubscriptionPolicyType {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected SubscriptionPolicyTypeImpl() {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscriptionPolicyType();		
	}

	protected SubscriptionPolicyTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final List<Element> getPolicies() {
		List<Element> polices = new ArrayList<Element>();
		List<Object> jaxbPolicies = this.jaxbTypeObj.getAny();
		if (jaxbPolicies != null){
			for (Object object : jaxbPolicies){ 
				if (object instanceof Element){
					polices.add((Element)object);
				}
			}
		}
		return polices;
	}

	@Override
	public final void addPolicy(Element policy) {
		this.jaxbTypeObj.getAny().add(policy);
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.SubscriptionPolicyType}
	 *  "Jaxb model type" object from a {@link SubscriptionPolicyType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType toJaxbModel(SubscriptionPolicyType apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof SubscriptionPolicyTypeImpl){
			jaxbTypeObj = ((SubscriptionPolicyTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscriptionPolicyType();

			// ~~~~ Set Policies ~~~~
			List<Element> apiPolices = apiTypeObj.getPolicies();
			if (apiPolices != null){
				for (Element policy : apiPolices) {
					jaxbTypeObj.getAny().add(policy);
				}
			}
		}
		return jaxbTypeObj;
	}
}
