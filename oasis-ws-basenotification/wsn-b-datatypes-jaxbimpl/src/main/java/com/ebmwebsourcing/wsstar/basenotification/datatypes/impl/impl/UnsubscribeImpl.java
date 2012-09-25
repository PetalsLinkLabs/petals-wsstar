package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.List;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Unsubscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class UnsubscribeImpl implements Unsubscribe {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe jaxbTypeObj;
	
	/**
	 * Default constructor
	 */
	protected UnsubscribeImpl() {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnsubscribe();		
	}

	protected UnsubscribeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.Unsubscribe}
	 *  "Jaxb model type" object from a {@link Unsubscribe} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe toJaxbModel(Unsubscribe apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe jaxbTypeObj = null;
		
		if (apiTypeObj instanceof UnsubscribeImpl){
			jaxbTypeObj = ((UnsubscribeImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createUnsubscribe();
		}
		return jaxbTypeObj;
	}

	@Override
	public void addAny(Element elmt) {
		this.jaxbTypeObj.getAny().add(elmt);
	}

	@Override
	public List<Object> getAny() {
		return this.jaxbTypeObj.getAny();
	}
}
