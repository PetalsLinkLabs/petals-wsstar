package com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.element.ReferenceParameters;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.WsrfbfJAXBContext;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;

public class BaseFaultTypeImpl implements BaseFaultType {

	private com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType jaxbTypeObj;
	private Logger logger = null;
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link BaseFaultType) object that must
	 * 		  be {@link GregorianCalendar} representation of an Date xml type.
	 */
	protected BaseFaultTypeImpl(Date timestamp, Logger logger){
		this.logger = (logger!=null)?logger:Logger.getLogger(this.getClass().getSimpleName());

		this.jaxbTypeObj = WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultType();		
		this.jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp,this.logger));		
	}

	protected BaseFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType jaxbTypeObj, Logger logger){
		this.logger = (logger!=null)?logger:Logger.getLogger(this.getClass().getSimpleName());		
		this.jaxbTypeObj = jaxbTypeObj;
	}

	/**
	 *  Constructor called only by inherited classes
	 *  /!\ Attribute "jaxbTypeObj" not yet initialized
	 */
	protected BaseFaultTypeImpl(Logger logger){	
		this.logger = (logger!=null)?logger:Logger.getLogger(this.getClass().getSimpleName());	
	}

	protected final com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType getJaxbTypeObj(){
		return this.jaxbTypeObj;
	}

	protected final void setJaxbTypeObj(com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType jaxbTypeObj) {
		this.jaxbTypeObj = jaxbTypeObj;
	}

	protected final Logger getLogger() {
		return logger;
	}

	protected final void setLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public final List<Description> getDescriptions() {

		List<Description> descriptions = new ArrayList<BaseFaultType.Description>();

		List<com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description> jaxbDescriptions =
			this.jaxbTypeObj.getDescription();
		if (jaxbDescriptions != null){
			for (com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description descrItem : jaxbDescriptions) {
				descriptions.add(new DescriptionImpl(descrItem));
			}		
		}
		return descriptions;
	}

	@Override
	public final void addDescription(Description value) {
		this.jaxbTypeObj.getDescription().add(DescriptionImpl.toJaxbModel(value));
	}

	@Override
	public final ErrorCode getErrorCode() {

		ErrorCode errorCode = null;

		com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.ErrorCode jaxbErrorCode =  
			this.jaxbTypeObj.getErrorCode();
		if (jaxbErrorCode != null){
			errorCode = new ErrorCodeImpl(jaxbErrorCode);
		}
		return errorCode;
	}

	@Override
	public final void setErrorCode(ErrorCode value) {
		this.jaxbTypeObj.setErrorCode(ErrorCodeImpl.toJaxbModel(value));
	}

	@Override
	public final FaultCause getFaultCause() {
		FaultCause faultCause = null;
		com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.FaultCause jaxbFaultCause = 
			this.jaxbTypeObj.getFaultCause();

		if (jaxbFaultCause != null){
			faultCause = new FaultCauseImpl(jaxbFaultCause);
		}
		return faultCause;		
	}

	@Override
	public final void setFaultCause(FaultCause value) {
		this.jaxbTypeObj.setFaultCause(FaultCauseImpl.toJaxbModel(value));
	}

	@Override
	public final EndpointReferenceType getOriginator() {

		EndpointReferenceType result = null;

		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = this.jaxbTypeObj.getOriginator();
		if (jaxbModelObj != null){
			result = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
			result.setAddress(WSNUtil.getInstance().getXmlObjectFactory().create(Address.class));
			result.getAddress().setValue(URI.create(jaxbModelObj.getAddress().getValue()));
		
			result.setReferenceParameters(WSNUtil.getInstance().getXmlObjectFactory().create(ReferenceParameters.class));
			for(Object obj: jaxbModelObj.getReferenceParameters().getAny()) {
				result.getReferenceParameters().addAny(obj);
			}
		}

		return result;
	}

	@Override
	public final void setOriginator(EndpointReferenceType value) {		
		easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
		jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
		jaxbModelObj.getAddress().setValue(value.getAddress().getValue().toString());
		// TODO: copy all attribute or easybox all spec..... :(
		jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
		jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(value.getReferenceParameters().getAny()));

		this.jaxbTypeObj.setOriginator(jaxbModelObj);
	}

	@Override
	public final Date getTimestamp() {
		return this.jaxbTypeObj.getTimestamp().toGregorianCalendar().getTime();
	}

	@Override
	public final void setTimestamp(Date value) {
		this.jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(value,this.logger));		
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType}
	 *  "Jaxb model type" object from a {@link BaseFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 * @param inheritJaxbTypeObj TODO
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType toJaxbModel(BaseFaultType apiTypeObj, 
			com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType inheritJaxbTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType jaxbTypeObj = null; 
		if ((apiTypeObj instanceof BaseFaultTypeImpl) && inheritJaxbTypeObj == null) {
			jaxbTypeObj = ((BaseFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		}
		else {
			jaxbTypeObj = (inheritJaxbTypeObj!=null)?
					inheritJaxbTypeObj : WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultType();

			Date timestamp = apiTypeObj.getTimestamp();
			jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp,Logger.getLogger(apiTypeObj.getClass().getSimpleName())));

			List<BaseFaultType.Description> descriptions = apiTypeObj.getDescriptions();
			if (descriptions != null){
				for (Description descrItem : descriptions){				
					jaxbTypeObj.getDescription().add(DescriptionImpl.toJaxbModel(descrItem));
				}
			}

			BaseFaultType.ErrorCode errorCode = apiTypeObj.getErrorCode();
			if (errorCode != null){
				jaxbTypeObj.setErrorCode(ErrorCodeImpl.toJaxbModel(errorCode));
			}

			BaseFaultType.FaultCause faultCause = apiTypeObj.getFaultCause();
			if (faultCause != null){
				jaxbTypeObj.setFaultCause(FaultCauseImpl.toJaxbModel(faultCause));
			}

			EndpointReferenceType originator = apiTypeObj.getOriginator();
			if (originator != null){
				easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType jaxbModelObj = new easybox.org.w3._2005._08.addressing.EJaxbEndpointReferenceType();
				jaxbModelObj.setAddress(new easybox.org.w3._2005._08.addressing.EJaxbAttributedURIType());
				jaxbModelObj.getAddress().setValue(originator.getAddress().getValue().toString());
				// TODO: copy all attribute or easybox all spec..... :(
				jaxbModelObj.setReferenceParameters(new easybox.org.w3._2005._08.addressing.EJaxbReferenceParametersType());
				jaxbModelObj.getReferenceParameters().getAny().addAll(Arrays.asList(originator.getReferenceParameters().getAny()));

				jaxbTypeObj.setOriginator(jaxbModelObj);
			}
		}	
		return jaxbTypeObj;
	}

	public static class DescriptionImpl implements BaseFaultType.Description{

		private com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description jaxbTypeObj;

		/**
		 * Default constructor
		 * 
		 */
		protected DescriptionImpl(String value){
			this.jaxbTypeObj = WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultTypeDescription();
			this.jaxbTypeObj.setValue(value);
		}

		protected DescriptionImpl(com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}

		protected final com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description getJaxbTypeObj() {
			return jaxbTypeObj;
		}

		@Override
		public final Locale getLang() {
			String language = this.jaxbTypeObj.getLang();			
			return (language != null)?new Locale(language):null;
		}

		@Override
		public final void setLang(Locale value) {
			this.jaxbTypeObj.setLang(value.getLanguage());
		}

		@Override
		public final String getValue() {
			return this.jaxbTypeObj.getValue();
		}

		@Override
		public final void setValue(String value) {
			this.jaxbTypeObj.setValue(value);
		}

		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description}
		 *  "Jaxb model type" object from a {@link BaseFaultType.Description} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description toJaxbModel(
				BaseFaultType.Description apiTypeObj){

			com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.Description jaxbTypeObj = null;

			if (apiTypeObj instanceof BaseFaultTypeImpl.DescriptionImpl) {
				jaxbTypeObj =((BaseFaultTypeImpl.DescriptionImpl)apiTypeObj).getJaxbTypeObj();
			}
			else { 
				jaxbTypeObj = WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultTypeDescription();	

				Locale lang = apiTypeObj.getLang();
				if (lang != null) {
					jaxbTypeObj.setLang(lang.getLanguage());			
				}
				jaxbTypeObj.setValue(apiTypeObj.getValue());			
			}
			return jaxbTypeObj;
		}

	}

	public static class ErrorCodeImpl implements BaseFaultType.ErrorCode{

		private com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.ErrorCode jaxbTypeObj;

		/**
		 * Default constructor
		 * 
		 */
		protected ErrorCodeImpl(URI dialect){
			this.jaxbTypeObj = WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultTypeErrorCode();
			this.jaxbTypeObj.setDialect(dialect.toString());
		}

		protected ErrorCodeImpl(com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.ErrorCode jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}

		protected final com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.ErrorCode getJaxbTypeObj() {
			return jaxbTypeObj;
		}

		@Override
		public final URI getDialect() {
			URI dialect = null;
			String dialectAsString = this.jaxbTypeObj.getDialect();
			try {
				dialect = new URI(dialectAsString);
			} catch (URISyntaxException e) {
				Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING,"The \"dialect\" field " +
						"value of the \"ErrorCode\" does not respect the URI Syntax (according to" +
						" RFC-2396/RFC-2732).\nUri string value is :\n\t " + dialectAsString + "\n" /*,e*/);			
			}
			return dialect;
		}

		@Override
		public final void setDialect(URI dialect) {
			this.jaxbTypeObj.setDialect(dialect.toString());			
		}

		@Override
		public final List<Object> getCodes() {
			return this.jaxbTypeObj.getContent();
		}

		@Override
		public void addCode(Object code) {
			this.jaxbTypeObj.getContent().add(code);
		}

		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.ErrorCode}
		 *  "Jaxb model type" object from a {@link BaseFaultType.ErrorCode} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.ErrorCode toJaxbModel(
				BaseFaultType.ErrorCode apiTypeObj){

			com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.ErrorCode jaxbTypeObj = null;

			if (apiTypeObj instanceof BaseFaultTypeImpl.ErrorCodeImpl) {
				jaxbTypeObj = ((BaseFaultTypeImpl.ErrorCodeImpl)apiTypeObj).getJaxbTypeObj();
			}
			else {
				jaxbTypeObj = WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultTypeErrorCode();			

				URI dialect = apiTypeObj.getDialect();
				jaxbTypeObj.setDialect(dialect.toString());			

				List<Object> content = apiTypeObj.getCodes();
				if (content != null){
					jaxbTypeObj.getContent().addAll(content);
				}
			}
			return jaxbTypeObj;
		}

	}

	public static class FaultCauseImpl implements BaseFaultType.FaultCause{

		private com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.FaultCause jaxbTypeObj;

		/**
		 * Default constructor
		 * 
		 */
		protected FaultCauseImpl(Element faultCauseAny){
			this.jaxbTypeObj = WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultTypeFaultCause();
			// /!\ Be careful : here is a recursive call !
			this.jaxbTypeObj.setAny(faultCauseAny);
		}

		protected FaultCauseImpl(com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.FaultCause jaxbTypeObj){
			this.jaxbTypeObj = jaxbTypeObj;
		}

		protected final com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.FaultCause getJaxbTypeObj() {
			return jaxbTypeObj;
		}

		@Override
		public final Element getAny() {
			Object anyFromModel = this.jaxbTypeObj.getAny();
			return (anyFromModel instanceof Element)? (Element)anyFromModel : null;
		}

		@Override
		public final void setAny(Element value) {			
			this.jaxbTypeObj.setAny(value);
		}

		/**
		 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.FaultCause}
		 *  "Jaxb model type" object from a {@link BaseFaultType.FaultCause} "api type" one  
		 *    
		 * @param apiTypeObj
		 */
		public static com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.FaultCause toJaxbModel(
				BaseFaultType.FaultCause apiTypeObj){

			com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.BaseFaultType.FaultCause jaxbTypeObj = null;

			if(apiTypeObj instanceof BaseFaultTypeImpl.FaultCauseImpl) {
				jaxbTypeObj = ((BaseFaultTypeImpl.FaultCauseImpl)apiTypeObj).getJaxbTypeObj();
			}
			else {
				jaxbTypeObj = WsrfbfJAXBContext.WSRFBF_JAXB_FACTORY.createBaseFaultTypeFaultCause();			

				Object faultCause = apiTypeObj.getAny();
				if (faultCause != null){
					jaxbTypeObj.setAny(faultCause);
				}
			}							
			return jaxbTypeObj;
		}		
	}

}
