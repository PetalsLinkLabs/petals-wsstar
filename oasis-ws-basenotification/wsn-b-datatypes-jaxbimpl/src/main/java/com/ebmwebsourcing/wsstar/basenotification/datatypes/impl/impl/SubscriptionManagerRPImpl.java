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
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionManagerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class SubscriptionManagerRPImpl implements SubscriptionManagerRP {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(SubscriptionManagerRPImpl.class.getSimpleName());

	/**
	 * Default constructor
	 */
	protected SubscriptionManagerRPImpl(EndpointReferenceType consumerRef) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscriptionManagerRP();		

		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(consumerRef.getAddress().getValue().toString());
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(consumerRef.getReferenceParameters().getAny()));

		this.jaxbTypeObj.setConsumerReference(jaxbModelObj);
	}

	protected SubscriptionManagerRPImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}

	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	@Override
	public final FilterType getFilter() {
		FilterType filter = null;
		com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType jaxbFilter = this.jaxbTypeObj.getFilter();				
		if (jaxbFilter != null){
			filter = new FilterTypeImpl(jaxbFilter);
		}
		return filter;
	}

	@Override
	public final void setFilter(FilterType filter) {
		this.jaxbTypeObj.setFilter(FilterTypeImpl.toJaxbModel(filter));
	}

	@Override
	public final EndpointReferenceType getConsumerReference() {
		EndpointReferenceType result = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		result.setAddress(WSNUtil.getInstance().getXmlObjectFactory().create(Address.class));
		// TODO: set all attribute
		result.getAddress().setValue(URI.create(this.jaxbTypeObj.getConsumerReference().getAddress().getValue()));
		result.setReferenceParameters(WSNUtil.getInstance().getXmlObjectFactory().create(ReferenceParameters.class));
		for(Object obj: this.jaxbTypeObj.getConsumerReference().getReferenceParameters().getAny()) {
			result.getReferenceParameters().addAny(obj);
		}
		return result;
	}

	@Override
	public final void setConsumerReference(EndpointReferenceType value) {
		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(value.getAddress().getValue().toString());
		// TODO: copy all attribute or easybox all spec..... :(
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(value.getReferenceParameters().getAny()));

		this.jaxbTypeObj.setConsumerReference(jaxbModelObj);
	}

	@Override
	public final Date getCreationTime() {
		Date creationTime = null;
		XMLGregorianCalendar jaxbCreationTime = this.jaxbTypeObj.getCreationTime();
		if (jaxbCreationTime != null){
			creationTime = jaxbCreationTime.toGregorianCalendar().getTime();
		}
		return creationTime;
	}

	@Override
	public final void setCreationTime(Date value) {
		this.jaxbTypeObj.setCreationTime(WsrfbfUtils.toXMLGregorianCalendar(value, SubscriptionManagerRPImpl.logger));
	}

	@Override
	public final SubscriptionPolicyType getSubscriptionPolicy() {
		SubscriptionPolicyType subsPolicy = null;
		com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType jaxbPolicy = this.jaxbTypeObj.getSubscriptionPolicy();
		if (jaxbPolicy != null){
			subsPolicy = new SubscriptionPolicyTypeImpl(jaxbPolicy);
		}
		return subsPolicy;
	}

	@Override
	public final void setSubscriptionPolicy(SubscriptionPolicyType value) {
		this.jaxbTypeObj.setSubscriptionPolicy(SubscriptionPolicyTypeImpl.toJaxbModel(value));
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.SubscriptionManagerRP}
	 *  "Jaxb model type" object from a {@link SubscriptionManagerRP} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP toJaxbModel(SubscriptionManagerRP apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP jaxbTypeObj = null;

		if (apiTypeObj instanceof SubscriptionManagerRPImpl){
			jaxbTypeObj = ((SubscriptionManagerRPImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscriptionManagerRP();

			// ~~~~ set ConsumerReference ~~~~
			easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
			jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
			jaxbModelObj.getAddress().setValue(apiTypeObj.getConsumerReference().getAddress().getValue().toString());
			// TODO: copy all attribute or easybox all spec..... :(
			jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
			jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(apiTypeObj.getConsumerReference().getReferenceParameters().getAny()));

			jaxbTypeObj.setConsumerReference(jaxbModelObj);
			
			// ~~~~ Set Filter ~~~~~
			FilterType apiFilter = apiTypeObj.getFilter();
			if (apiFilter != null){
				jaxbTypeObj.setFilter(FilterTypeImpl.toJaxbModel(apiFilter));
			}
			//~~~~~ Set CreationTerminationTime ~~~~~
			Date apiCreationTime = apiTypeObj.getCreationTime();
			if (apiCreationTime != null){
				jaxbTypeObj.setCreationTime(WsrfbfUtils.toXMLGregorianCalendar((Date) apiCreationTime,
						SubscriptionManagerRPImpl.logger));
			}
			// ~~~~~ Set SubscriptionPolicies ~~~~~
			SubscriptionPolicyType apiPolicy = apiTypeObj.getSubscriptionPolicy();
			if (apiPolicy != null){
				jaxbTypeObj.setSubscriptionPolicy(SubscriptionPolicyTypeImpl.toJaxbModel(apiPolicy));
			}
		}
		return jaxbTypeObj;
	}
}
