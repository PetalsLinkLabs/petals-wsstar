package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.net.URI;

public interface QueryExpressionType {
	
	 String getContent();
	
	 void setContent(String content);
	
	 URI getDialect();
	
	 void setDialect(URI dialect);
	
}
