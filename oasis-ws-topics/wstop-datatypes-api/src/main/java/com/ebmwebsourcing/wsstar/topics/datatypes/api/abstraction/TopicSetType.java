package com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction;

import java.util.List;

import org.w3c.dom.Element;

public interface TopicSetType {

	List<Element> getTopicsTrees();
	
	void addTopicsTree(Element topicTree);
	
}
