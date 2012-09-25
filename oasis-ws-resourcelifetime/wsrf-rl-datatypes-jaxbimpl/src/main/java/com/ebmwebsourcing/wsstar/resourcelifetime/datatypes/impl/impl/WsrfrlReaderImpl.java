package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

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
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlReader;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.WsrfrlJAXBContext;

public class WsrfrlReaderImpl implements WsrfrlReader {

	private WsrfrlJAXBContext resourceLifetimeJaxbContext = null;
	//private Logger logger = Logger.getLogger(WsrfrlReaderImpl.class.getName());
		
	/**
	 * Default constructor
	 */
	protected WsrfrlReaderImpl() {
		this.resourceLifetimeJaxbContext = WsrfrlJAXBContext.getInstance();
	}
	
	protected WsrfrlReaderImpl(String[] nsAndPrefixForMarshalling) {
		this.resourceLifetimeJaxbContext = WsrfrlJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}
	
	@Override
	public final CurrentTime readCurrentTime(Document document) throws WsrfrlException {
		CurrentTime result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.CurrentTime.class);
			if (schemaBinding.getValue() != null){
				result = new CurrentTimeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;		
	}

	@Override
	public final Destroy readDestroy(Document document) throws WsrfrlException {
		Destroy result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.Destroy.class);
			if (schemaBinding.getValue() != null){
				result = new DestroyImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final DestroyResponse readDestroyResponse(Document document) throws WsrfrlException{
		DestroyResponse result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.DestroyResponse.class);
			if (schemaBinding.getValue() != null) {
				result = new DestroyResponseImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final ResourceNotDestroyedFaultType readResourceNotDestroyedFaultType(
			Document document) throws WsrfrlException {
		ResourceNotDestroyedFaultType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ResourceNotDestroyedFaultType.class);
			if (schemaBinding.getValue() != null) {
				result = new ResourceNotDestroyedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final ScheduledResourceTerminationRP readScheduledResourceTerminationRP(
			Document document) throws WsrfrlException {
		ScheduledResourceTerminationRP result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.ScheduledResourceTerminationRP.class);
			if (schemaBinding.getValue() != null) {
				result = new ScheduledResourceTerminationRPImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final SetTerminationTime readSetTerminationTime(Document document)
			throws WsrfrlException {
		SetTerminationTime result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTime.class);
			if (schemaBinding.getValue() != null) {
				result = new SetTerminationTimeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final SetTerminationTimeResponse readSetTerminationTimeResponse(
			Document document) throws WsrfrlException {
		SetTerminationTimeResponse result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.SetTerminationTimeResponse.class);
			if (schemaBinding.getValue() != null) {
				result = new SetTerminationTimeResponseImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final TerminationNotification readTerminationNotification(Document document)
			throws WsrfrlException {
		TerminationNotification result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationNotification.class);
			if (schemaBinding.getValue() != null){
				result = new TerminationNotificationImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final TerminationTime readTerminationTime(Document document)
			throws WsrfrlException {
		TerminationTime result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTime.class);
			if (schemaBinding.getValue() != null){
				result = new TerminationTimeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final TerminationTimeChangeRejectedFaultType readTerminationTimeChangeRejectedFaultType(
			Document document) throws WsrfrlException {
		TerminationTimeChangeRejectedFaultType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.TerminationTimeChangeRejectedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new TerminationTimeChangeRejectedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

	@Override
	public final UnableToSetTerminationTimeFaultType readUnableToSetTerminationTimeFaultType(
			Document document) throws WsrfrlException {
		UnableToSetTerminationTimeFaultType result = null ;
		try {

			Unmarshaller unmarshaller = this.resourceLifetimeJaxbContext.createWSResourceLifetimeUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.resource.resourcelifetime.UnableToSetTerminationTimeFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnableToSetTerminationTimeFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsrfrlException(e);
		} 	
		return result;
	}

}
