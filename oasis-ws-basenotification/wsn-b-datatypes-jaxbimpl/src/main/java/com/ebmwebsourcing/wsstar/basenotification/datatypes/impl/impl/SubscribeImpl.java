package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.element.ReferenceParameters;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;
import com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe.SubscriptionPolicy;

public class SubscribeImpl implements Subscribe {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(SubscribeImpl.class.getSimpleName());

	/**
	 * Default constructor
	 */
	protected SubscribeImpl(EndpointReferenceType consumerRef) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscribe();	

		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(consumerRef.getAddress().getValue().toString());
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(consumerRef.getReferenceParameters().getAny()));

		this.jaxbTypeObj.setConsumerReference(jaxbModelObj);
	}

	protected SubscribeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}

	public com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe getJaxbTypeObj() {
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

		if(this.jaxbTypeObj.getConsumerReference().getReferenceParameters() != null) {
			for(Object obj: this.jaxbTypeObj.getConsumerReference().getReferenceParameters().getAny()) {
				result.getReferenceParameters().addAny(obj);
			}
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
	public final SubscriptionPolicyType getSubscriptionPolicy() {		
		SubscriptionPolicyType policy = null;
		SubscriptionPolicy jaxbPolicy = this.jaxbTypeObj.getSubscriptionPolicy();
		if (jaxbPolicy != null) {
			policy = new SubscriptionPolicyTypeImpl();
			for (Object anyItem : jaxbPolicy.getAny()) {
				if (anyItem instanceof Element){
					policy.addPolicy((Element)anyItem);
				}
			}
		}		
		return policy;
	}

	@Override
	public final void setSubscriptionPolicy(SubscriptionPolicyType value) {
		this.jaxbTypeObj.setSubscriptionPolicy(SubscribeImpl.toSubscibeSubscriptionPolicy(value));
	}

	@Override
	public final Object getInitialTerminationTime() {
		Object initTermTime = null; 

		JAXBElement<String> jaxbInitTermTime = this.jaxbTypeObj.getInitialTerminationTime();
		if (jaxbInitTermTime != null){
			String jaxbEltValue = jaxbInitTermTime.getValue();
			try {
				if (jaxbEltValue.startsWith("P")){				
					initTermTime = DatatypeFactory.newInstance().newDuration(jaxbEltValue);
				} else if(jaxbEltValue.contains("T")) {
					initTermTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(jaxbEltValue).toGregorianCalendar().getTime();
				}
			} catch (DatatypeConfigurationException e) {
				SubscribeImpl.logger.log(Level.WARNING,"The \"InitalTerminaionTime\" field " +
						"value of the \"Subsrcibe\" payload does correspond to a string representation" +
						"of a \"Duration\" xml type nor \"DateTime\" xml type (received : " + jaxbEltValue+ ") \n",e);	
			}	
		}	

		return initTermTime;
	}

	@Override
	public final void setInitialTerminationTime(Date value) {
		this.jaxbTypeObj.setInitialTerminationTime(
				new JAXBElement<String>(WsnbConstants.INIT_TERMINATION_TIME_QNAME,
						String.class,
						WsrfbfUtils.toXMLGregorianCalendar(value, SubscribeImpl.logger).toString()));
	}

	@Override
	public final void setInitialTerminationTime(Duration value) {
		this.jaxbTypeObj.setInitialTerminationTime(
				new JAXBElement<String>(WsnbConstants.INIT_TERMINATION_TIME_QNAME,
						String.class,
						value.toString()));
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.Subscribe}
	 *  "Jaxb model type" object from a {@link Subscribe} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe toJaxbModel(Subscribe apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe jaxbTypeObj = null;

		if (apiTypeObj instanceof SubscribeImpl){
			jaxbTypeObj = ((SubscribeImpl)apiTypeObj).getJaxbTypeObj();
		} else { 
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscribe();

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
			//~~~~~ Set InitialTerminationTime ~~~~~
			Object apiInitTermTime = apiTypeObj.getInitialTerminationTime();
			if (apiInitTermTime != null){
				String initTermTimeAsString = null;

				if (apiInitTermTime instanceof Date) {
					initTermTimeAsString = WsrfbfUtils.toXMLGregorianCalendar((Date) apiInitTermTime,
							SubscribeImpl.logger).toString();
				} else if (apiInitTermTime instanceof Duration) {
					initTermTimeAsString = ((Duration) apiInitTermTime).toString();
				}

				if (initTermTimeAsString != null) {
					jaxbTypeObj.setInitialTerminationTime(
							new JAXBElement<String>(WsnbConstants.INIT_TERMINATION_TIME_QNAME,
									java.lang.String.class,
									initTermTimeAsString));
				}
			}

			// ~~~~~ Set SubscriptionPolicies ~~~~~
			SubscriptionPolicyType apiPolicy = apiTypeObj.getSubscriptionPolicy();
			if (apiPolicy != null) {
				jaxbTypeObj.setSubscriptionPolicy(SubscribeImpl.toSubscibeSubscriptionPolicy(apiPolicy));
			}
		}
		return jaxbTypeObj;
	}

	/**
	 * Convert from type {@link SubscriptionPolicyType} to type
	 * {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe.SubscriptionPolic}
	 * 
	 * (consequence of duplicated type in XML Schema of WS-BaseNotification) 
	 */
	private static com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe.SubscriptionPolicy toSubscibeSubscriptionPolicy(
			SubscriptionPolicyType apiSubsciptionPolicy){
		com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe.SubscriptionPolicy jaxbSubscibeSubscriptionPolicy =
			WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscribeSubscriptionPolicy();

		// ~~~~ Set Policies ~~~~
		List<Element> apiPolices = apiSubsciptionPolicy.getPolicies();
		if (apiPolices != null) {
			for (Element policy : apiPolices) {
				jaxbSubscibeSubscriptionPolicy.getAny().add(policy);
			}
		}
		return jaxbSubscibeSubscriptionPolicy;
	}
}
