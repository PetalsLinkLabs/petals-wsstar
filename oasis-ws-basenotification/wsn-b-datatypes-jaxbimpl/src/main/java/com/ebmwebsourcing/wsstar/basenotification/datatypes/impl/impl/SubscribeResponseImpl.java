package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.element.ReferenceParameters;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class SubscribeResponseImpl implements SubscribeResponse {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(SubscribeResponseImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected SubscribeResponseImpl(EndpointReferenceType subscriptionRef) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscribeResponse();	
		
		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(subscriptionRef.getAddress().getValue().toString());
		// TODO: copy all attribute or easybox all spec..... :(
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(subscriptionRef.getReferenceParameters().getAny()));

		this.jaxbTypeObj.setSubscriptionReference(jaxbModelObj);
	}

	public SubscribeResponseImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	public com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final Date getCurrentTime() {
		Date currentTime = null;
		XMLGregorianCalendar jaxbCurrTime = this.jaxbTypeObj.getCurrentTime();
		if (jaxbCurrTime != null){
			currentTime = jaxbCurrTime.toGregorianCalendar().getTime();
		}
		return currentTime;
	}

	@Override
	public final void setCurrentTime(Date value) {
		this.jaxbTypeObj.setCurrentTime(WsrfbfUtils.toXMLGregorianCalendar(value, SubscribeResponseImpl.logger));
	}

	@Override
	public final EndpointReferenceType getSubscriptionReference() {
		EndpointReferenceType result = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		result.setAddress(WSNUtil.getInstance().getXmlObjectFactory().create(Address.class));
		// TODO: set all attribute
		result.getAddress().setValue(URI.create(this.jaxbTypeObj.getSubscriptionReference().getAddress().getValue()));
		result.setReferenceParameters(WSNUtil.getInstance().getXmlObjectFactory().create(ReferenceParameters.class));
		for(Object obj: this.jaxbTypeObj.getSubscriptionReference().getReferenceParameters().getAny()) {
			result.getReferenceParameters().addAny(obj);
		}
		return result;		
	}

	@Override
	public final void setSubscriptionReference(EndpointReferenceType value) {
		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(value.getAddress().getValue().toString());
		// TODO: copy all attribute or easybox all spec..... :(
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(value.getReferenceParameters().getAny()));

		this.jaxbTypeObj.setSubscriptionReference(jaxbModelObj);
	}

	@Override
	public final Date getTerminationTime() {
		Date terminationTime = null;
		XMLGregorianCalendar jaxbTermTime = this.jaxbTypeObj.getTerminationTime();
		if (jaxbTermTime != null){
			terminationTime = jaxbTermTime.toGregorianCalendar().getTime();
		}
		return terminationTime;
	}

	@Override
	public final void setTerminationTime(Date termTime) {
		this.jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(termTime, SubscribeResponseImpl.logger));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.SubscribeResponse}
	 *  "Jaxb model type" object from a {@link SubscribeResponse} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse toJaxbModel(SubscribeResponse apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse jaxbTypeObj = null;
		
		if (apiTypeObj instanceof SubscribeResponseImpl){
			jaxbTypeObj = ((SubscribeResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscribeResponse();

			// ~~~~ set SubscriptionReference ~~~~
			easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
			jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
			jaxbModelObj.getAddress().setValue(apiTypeObj.getSubscriptionReference().getAddress().getValue().toString());
			// TODO: copy all attribute or easybox all spec..... :(
			jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
			jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(apiTypeObj.getSubscriptionReference().getReferenceParameters().getAny()));

			jaxbTypeObj.setSubscriptionReference(jaxbModelObj);
			
			// ~~~~ Set CurrentTime ~~~~~
			Date apiCurrentTime = apiTypeObj.getCurrentTime();
			if (apiCurrentTime != null){
				jaxbTypeObj.setCurrentTime(WsrfbfUtils.toXMLGregorianCalendar(apiCurrentTime,SubscribeResponseImpl.logger));
			}
			//~~~~~ Set TerminationTime ~~~~~
			Date apiTerminationTime = apiTypeObj.getTerminationTime();
			if (apiTerminationTime != null){
				jaxbTypeObj.setTerminationTime(WsrfbfUtils.toXMLGregorianCalendar(apiTerminationTime,SubscribeResponseImpl.logger));
			}
		}
		return jaxbTypeObj;
	}
}
