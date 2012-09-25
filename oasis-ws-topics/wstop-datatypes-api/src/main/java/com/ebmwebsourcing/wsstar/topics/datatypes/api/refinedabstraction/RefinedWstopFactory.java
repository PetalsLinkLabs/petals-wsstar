package com.ebmwebsourcing.wsstar.topics.datatypes.api.refinedabstraction;

import java.net.URI;

import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.QueryExpressionType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicNamespaceType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicSetType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.TopicType;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopReader;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.abstraction.WstopWriter;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.implementor.WstopModelFactory;
import com.ebmwebsourcing.wsstar.topics.datatypes.api.utils.WstopException;


public final class RefinedWstopFactory implements WstopFactory {

	private WstopModelFactory model;
	
	//	################# EXPERITMENTAL SINGLETON PATTERN FORM ######################
	
	private RefinedWstopFactory () {
	}	

	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	 * or the first access to SingletonHolder.INSTANCE, not before.
	 */
	private static final class WstopFactoryHolder { 		
		private  static final RefinedWstopFactory INSTANCE = new RefinedWstopFactory();	
		
		private WstopFactoryHolder(){}
	}

	public static WstopFactory getInstance() throws WstopException {
		RefinedWstopFactory singleton = WstopFactoryHolder.INSTANCE;
		if (singleton.model == null) {			
			throw new WstopException("\n\t/!\\ WARNING /!\\\n" +
					"The WstopModelFactory have not been initialized !!!\n" +
					"Please create a \"WstopModelFactory\" instance and \n" +
					"call the \"getInstance(WstopModelFactory)\" method instead." +
					"\n\t/!\\ WARNING /!\\\n");
		}
		return singleton;		
	}	
	
	public static WstopFactory getInstance(WstopModelFactory modelFactory) {		
		RefinedWstopFactory singleton = WstopFactoryHolder.INSTANCE;
		singleton.model = modelFactory;		 	
		return singleton;		
	}	 		
	//	#######################################################################
	
	public WstopModelFactory getModel() {
		return this.model;
	}
	
	protected void  setModel(WstopModelFactory model) {
		this.model = model;
	}

	@Override
	public QueryExpressionType createMessagePattern(URI dialect) {
		return this.model.createWstopModelMessagePattern(dialect);
	}

	@Override
	public TopicNamespaceType createTopicNamespaceType(URI targetNamespace) {
		return this.model.createWstopModelTopicNamespaceType(targetNamespace);
	}
	
	@Override
	public TopicNamespaceType.Topic createTopicNamespaceTypeTopic(String name) {
		return this.model.createWstopModelTopicNamespaceTypeTopic(name);
	}
	
	@Override
	public TopicSetType createTopicSetType() {
		return this.model.createWstopModelTopicSetType();
	}

	@Override
	public TopicType createTopicType(String name) {
		return this.model.createWstopModelTopicType(name);
	}	

	@Override
	public WstopReader getWstopReader() {
		return this.model.getWstopModelReader();
	}

	@Override
	public WstopWriter getWstopWriter() {
		return this.model.getWstopModelWriter();
	}
}
