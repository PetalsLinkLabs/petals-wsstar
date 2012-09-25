package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.net.URI;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ProducerPropertiesExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.QueryExpressionType;

public class ProducerPropertiesExpressionImpl extends QueryExpressionTypeImpl
		implements ProducerPropertiesExpression {
	private static Logger logger  = Logger.getLogger(MessageContentExpressionImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected ProducerPropertiesExpressionImpl(URI dialect) {
		super(dialect, ProducerPropertiesExpressionImpl.logger);		
	}

	protected ProducerPropertiesExpressionImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType jaxbTypeObj){
		super(jaxbTypeObj, ProducerPropertiesExpressionImpl.logger);
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType}
	 * object from a {@link ProducerPropertiesExpression} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType toJaxbModel(ProducerPropertiesExpression apiTypeObj){
		
		QueryExpressionType param = (apiTypeObj instanceof ProducerPropertiesExpressionImpl) ?
				(QueryExpressionTypeImpl)apiTypeObj :
					(QueryExpressionType)apiTypeObj;
		
		return QueryExpressionTypeImpl.toJaxbModel(param);
	}
}
