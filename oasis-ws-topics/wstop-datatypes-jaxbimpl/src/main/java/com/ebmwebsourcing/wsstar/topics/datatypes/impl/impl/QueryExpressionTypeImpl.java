package com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.impl.WstopJAXBContext;

public class QueryExpressionTypeImpl implements QueryExpressionType {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType jaxbTypeObj;
	private static Logger logger  = Logger.getLogger(QueryExpressionTypeImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected QueryExpressionTypeImpl(URI dialect) {		
		this.jaxbTypeObj = WstopJAXBContext.WSTOP_JAXB_FACTORY.createQueryExpressionType();	
		this.jaxbTypeObj.setDialect(dialect.toString());
		QueryExpressionTypeImpl.logger = Logger.getLogger(QueryExpressionTypeImpl.class.getSimpleName());
	}

	protected QueryExpressionTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
		QueryExpressionTypeImpl.logger = Logger.getLogger(QueryExpressionTypeImpl.class.getSimpleName());
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public URI getDialect() {
		// ~~ Note : Not be null according to related xml schema
		URI result = null;
		String uriAsString = this.jaxbTypeObj.getDialect();
		try {
			result = new URI(uriAsString);
		} catch (URISyntaxException e) {
			/*throw new WsaException(*/
			QueryExpressionTypeImpl.logger.log(Level.WARNING,"The \"Dialect\" field " +
					"value of the \"QueryExpression\" does not respect the URI Syntax (according to" +
					" RFC-2396/RFC-2732).\nUri string value is :\n\t " + uriAsString + "\n");			
		}
		return result;
	}

	@Override
	public void setDialect(URI value) {
		this.jaxbTypeObj.setDialect(value.toString());
	}

	@Override
	public String getContent() {
		String res = null;
		if (this.jaxbTypeObj.getContent() != null && this.jaxbTypeObj.getContent().size() > 0) {
			res = this.jaxbTypeObj.getContent().get(0).toString().trim();
		}
		return res;
	}

	@Override
	public void setContent(String value) {
		final List<Object> objFromModel = this.jaxbTypeObj.getContent();

		if (objFromModel != null) {
			if (objFromModel.size() > 0) {
				objFromModel.clear();
			}
			objFromModel.add(0, value.trim());
		}
	}

	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType}
	 * object from a {@link QueryExpressionType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType toJaxbModel(QueryExpressionType apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.topics.QueryExpressionType jaxbTypeObj = null;
		
		if (apiTypeObj instanceof QueryExpressionTypeImpl){
			jaxbTypeObj = ((QueryExpressionTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WstopJAXBContext.WSTOP_JAXB_FACTORY.createQueryExpressionType();

			jaxbTypeObj.setDialect(apiTypeObj.getDialect().toString());

			jaxbTypeObj.getContent().add(apiTypeObj.getContent());
		}
		return jaxbTypeObj;		
	}
	
}
