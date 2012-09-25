package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.w3c.dom.Document;

import com.ebmwebsourcing.easycommons.research.util.dom.DOMUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.WsrfrlConstants;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ResourceNotDestroyedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ScheduledResourceTerminationRP;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationNotification;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTimeChangeRejectedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.UnableToSetTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlWriter;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class WsrfrlWriterImpl implements WsrfrlWriter {
	
	//private Logger logger = Logger.getLogger(WsrfrlWriterImpl.class.getName());
	private WsrfrlJAXBContext resourceLifetimeJaxbContext = null;
	
	/**
     * Default constructor
     */
    protected  WsrfrlWriterImpl(){
    	this.resourceLifetimeJaxbContext = WsrfrlJAXBContext.getInstance();
	} 
        
    protected  WsrfrlWriterImpl(String[] nsAndPrefixForMarshalling){
    	this.resourceLifetimeJaxbContext = WsrfrlJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}

	@Override
	public final Document writeCurrentTimeAsDOM(CurrentTime value)
			throws WsrfrlException {
		Document result = null;
    	if (value instanceof CurrentTimeImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime>(WsrfrlConstants.CURRENT_TIME_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime.class,
    						((CurrentTimeImpl)value).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeDestroyAsDOM(Destroy value) throws WsrfrlException {
		Document result = null;
    	if (value instanceof DestroyImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy>(WsrfrlConstants.DESTROY_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy.class,
    						((DestroyImpl)value).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeDestroyResponseAsDOM(DestroyResponse value)
			throws WsrfrlException {
		Document result = null;
    	if (value instanceof DestroyResponseImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse>(WsrfrlConstants.DESTROY_RESPONSE_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse.class,
    						((DestroyResponseImpl)value).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeResourceNotDestroyedFaultTypeAsDOM(
			ResourceNotDestroyedFaultType value) throws WsrfrlException {
		Document result = null;
    	if (value instanceof ResourceNotDestroyedFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType> element = 
    				WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createResourceNotDestroyedFault(ResourceNotDestroyedFaultTypeImpl.toJaxbModel(value));
    				
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeScheduledResourceTerminationRPAsDOM(
			ScheduledResourceTerminationRP value) throws WsrfrlException {
		Document result = null;
    	if (value instanceof ScheduledResourceTerminationRPImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP>(WsrfrlConstants.SCHEDULED_RESOURCE_TERMINATION_RP_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP.class,
    						((ScheduledResourceTerminationRPImpl)value).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeSetTerminationTimeAsDOM(SetTerminationTime value)
			throws WsrfrlException {
		Document result = null;
    	if (value instanceof SetTerminationTimeImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime>(WsrfrlConstants.SET_TERMINATION_TIME_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime.class,
    						((SetTerminationTimeImpl)value).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeSetTerminationTimeResponseAsDOM(
			SetTerminationTimeResponse value) throws WsrfrlException {
		Document result = null;
    	if (value instanceof SetTerminationTimeResponseImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse>(WsrfrlConstants.SET_TERMINATION_TIME_RESPONSE_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse.class,
    						((SetTerminationTimeResponseImpl)value).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeTerminationNotificationAsDOM(
			TerminationNotification value) throws WsrfrlException {
		Document result = null;
    	if (value instanceof TerminationNotificationImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification> element = 
    				new JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification>(
    						WsrfrlConstants.TERMINATION_NOTIFICATION_QNAME,
    						com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification.class,
    						((TerminationNotificationImpl)value).getJaxbTypeObj());

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeTerminationTimeAsDOM(TerminationTime value)
			throws WsrfrlException {
		Document result = null;
    	if (value instanceof TerminationTimeImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime> element = 
    			WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createTerminationTime(TerminationTimeImpl.toJaxbModel(value));

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeTerminationTimeChangeRejectedFaultTypeAsDOM(
			TerminationTimeChangeRejectedFaultType value) throws WsrfrlException {
		Document result = null;
    	if (value instanceof TerminationTimeChangeRejectedFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType> element = 
    				WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createTerminationTimeChangeRejectedFault(TerminationTimeChangeRejectedFaultTypeImpl.toJaxbModel(value));

    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;	
	}

	@Override
	public final Document writeUnableToSetTerminationTimeFaultTypeAsDOM(
			UnableToSetTerminationTimeFaultType value) throws WsrfrlException {
		Document result = null;
    	if (value instanceof UnableToSetTerminationTimeFaultTypeImpl){
    		try {

    			Marshaller marshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeMarshaller();

    			result = DOMUtil.getInstance().getDocumentBuilderFactory().newDocumentBuilder().newDocument();

    			// TODO : Check if it is a Thread safe method
    			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType> element = 
    				WsrfrlJAXBContext.WSRFRL_JAXB_FACTORY.createUnableToSetTerminationTimeFault(UnableToSetTerminationTimeFaultTypeImpl.toJaxbModel(value));
    			marshaller.marshal(element, result);            

    		} catch (final Exception ex) {
    			throw new WsrfrlException(WsrfbfUtils.getBindingExMessage(value), ex);
    		} 
    	}
    	return result;		
	}

}
