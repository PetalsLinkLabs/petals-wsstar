package com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction;

import java.net.URI;

public interface QueryExpressionType {
	
	 URI getDialect();
	
	 void setDialect(URI value);

	 String getContent();
	
	 void setContent(String value);	
	
}
