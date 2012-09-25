package com.ebmwebsourcing.wsstar.topics.datatypes.impl.impl;

import java.net.URI;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopReader;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopWriter;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.implementor.WstopModelFactory;

public class WstopModelFactoryImpl implements WstopModelFactory {

	private WstopReaderImpl wstopModelReader = null;
	private WstopWriterImpl wstopModelWriter = null;
	
	public WstopModelFactoryImpl() {	
		this.wstopModelReader = new WstopReaderImpl();
		this.wstopModelWriter = new WstopWriterImpl();		
	}
		
	@Override
	public final QueryExpressionType createWstopModelMessagePattern(URI dialect) {
		return new QueryExpressionTypeImpl(dialect);
	}

	@Override
	public final TopicNamespaceType createWstopModelTopicNamespaceType(URI targetNamespace) {
		return new TopicNamespaceTypeImpl(targetNamespace);
	}
	
	@Override
	public final TopicNamespaceType.Topic createWstopModelTopicNamespaceTypeTopic(String name) {
		return new TopicNamespaceTypeImpl.TopicImpl(name);
	}

	@Override
	public final TopicSetType createWstopModelTopicSetType() {		
		return new TopicSetTypeImpl();
	}

	public final TopicSetType createWstopModelTopicSetType(
			com.ebmwebsourcing.wsstar.jaxb.notification.topics.TopicSetType jaxbModelTopicSet) {		
		return new TopicSetTypeImpl(jaxbModelTopicSet);
	}

	@Override
	public final TopicType createWstopModelTopicType(String name) {
		return new TopicTypeImpl(name);
	}

	@Override
	public final WstopReader getWstopModelReader() {
		return this.wstopModelReader;
	}

	@Override
	public final WstopWriter getWstopModelWriter() {
		return this.wstopModelWriter;
	}

}
