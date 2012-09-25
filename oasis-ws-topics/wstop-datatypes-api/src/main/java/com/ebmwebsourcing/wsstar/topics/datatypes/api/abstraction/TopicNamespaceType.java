package com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction;

import java.net.URI;
import java.util.List;

public interface TopicNamespaceType {

	interface Topic extends TopicType {
		
		 String getParent();
		
		 void setParent(String parent);
		
	}		
	
	 String getName();
	
	 void setName(String value);
	
	 URI getNamespace();
	
	 void setNamespace(URI value);
	
	 List<Topic> getTopics();
	
	 void addTopic(Topic value);
	
	 List<Object> getAny();
	 
	 boolean isFinal();
	 
	 void setFinal(boolean value);
	
}
