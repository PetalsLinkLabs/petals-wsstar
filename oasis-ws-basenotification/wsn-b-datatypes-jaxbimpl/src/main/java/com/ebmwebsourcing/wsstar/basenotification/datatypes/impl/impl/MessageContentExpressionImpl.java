package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.net.URI;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MessageContentExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.QueryExpressionType;

public class MessageContentExpressionImpl extends QueryExpressionTypeImpl
		implements MessageContentExpression {
	
	private static Logger logger  = Logger.getLogger(MessageContentExpressionImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected MessageContentExpressionImpl(URI dialect) {
		super(dialect, MessageContentExpressionImpl.logger);		
	}

	protected MessageContentExpressionImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType jaxbTypeObj){
		super(jaxbTypeObj, MessageContentExpressionImpl.logger);
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType}
	 * object from a {@link MessageContentExpression} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType toJaxbModel(MessageContentExpression apiTypeObj)	{
		
		QueryExpressionType param = (apiTypeObj instanceof MessageContentExpressionImpl) ?
				(QueryExpressionTypeImpl)apiTypeObj :
					(QueryExpressionType)apiTypeObj;
		
		return QueryExpressionTypeImpl.toJaxbModel(param);
	}
}
