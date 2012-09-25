/**
 * Copyright (c) 2010 EBM Websourcing, http://www.ebmwebsourcing.com/
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * -------------------------------------------------------------------------
 * $id.java
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction;

import java.net.URI;
import java.util.Date;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType.Description;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType.ErrorCode;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType.FaultCause;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfReader;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfWriter;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.implementor.WsrfbfModelFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WsrfbfException;

/**
 * Implementation, following the "Singleton" design pattern, of
 *  the {@link WsrfbfFactory} factory interface.
 * 
 * NOTE : "RefinedWsrfbfFactory" class is the "refined abstraction"
 * part of the "Bridge" design pattern.
 * 
 * @author Thierry Déjean - EBM Websourcing
 *
 */
public final class RefinedWsrfbfFactory implements WsrfbfFactory {
	
	private WsrfbfModelFactory model;
		
	// ################# EXPERITMENTAL SINGLETON PATTERN FORM ######################
	
	private RefinedWsrfbfFactory () {}	

	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	 * or the first access to SingletonHolder.INSTANCE, not before.
	 */
	private static final class WsrfbfFactoryHolder { 		

		private static final RefinedWsrfbfFactory INSTANCE = new RefinedWsrfbfFactory(/*WsaFactoryHolder.modelFactory*/);
		
		private WsrfbfFactoryHolder(){};
	}

	public static WsrfbfFactory getInstance() throws WsrfbfException {
		RefinedWsrfbfFactory singleton = WsrfbfFactoryHolder.INSTANCE;
		if (singleton.model == null){ 			
			throw new WsrfbfException("\n\t/!\\ WARNING /!\\\n" +
					"The WsrfbfModelFactory have not been initialized !!!\n" +
					"Please create a \"WsrfbfModelFactory\" instance and \n" +
					"call the \"getInstance(WsrfbfModelFactory)\" method instead." +
					"\n\t/!\\ WARNING /!\\\n");
		}
		return singleton;		
	}	
	
	public static WsrfbfFactory getInstance(WsrfbfModelFactory modelFactory) {		
		RefinedWsrfbfFactory singleton = WsrfbfFactoryHolder.INSTANCE;
		singleton.model = modelFactory;		 	
		return singleton;		
	}	 		
	//	#######################################################################
		
	/**
	 * Get the model's factory implementation used.
	 * In "Bridge" design pattern language it is the 
	 * "ConcreteImplementor" 
	 * 
	 * @return the model's factory implementation used
	 */
	public WsrfbfModelFactory getModel() {
		return this.model;
	}
	
	/**
	 * Set the model's factory implementation to used.
	 * In "Bridge" design pattern language it is the 
	 * "ConcreteImplementor" 
	 * @param modelFactory an instance of model's factory implementation
	 */
	protected void setModel(WsrfbfModelFactory model) {
		this.model = model;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resource.api.basefaults.abstraction.WsrfbfFactory#createBaseFaultType(java.util.Date)
	 */
	@Override
	public BaseFaultType createBaseFaultType(Date timestamp) {
		return this.model.createWsrfbfModelBaseFaultType(timestamp);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resource.api.basefaults.abstraction.WsrfbfFactory#createBaseFaultTypeDescription(java.lang.String)
	 */
	@Override
	public Description createBaseFaultTypeDescription(String value) {
		return this.model.createWsrfbfModelBaseFaultTypeDescription(value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resource.api.basefaults.abstraction.WsrfbfFactory#createBaseFaultTypeErrorCode(java.net.URI)
	 */
	@Override
	public ErrorCode createBaseFaultTypeErrorCode(URI dialect) {
		return this.model.createWsrfbfModelBaseFaultTypeErrorCode(dialect);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resource.api.basefaults.abstraction.WsrfbfFactory#createBaseFaultTypeFaultCause(org.w3c.dom.Element)
	 */
	@Override
	public FaultCause createBaseFaultTypeFaultCause(Element faultCause) {		
		return this.model.createWsrfbfModelBaseFaultTypeFaultCause(faultCause);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resource.api.basefaults.abstraction.WsrfbfFactory#getWsrfbfReader()
	 */
	@Override
	public WsrfbfReader getWsrfbfReader() {		
		return this.model.getWsrfbfModelReader();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.wsstar.resource.api.basefaults.abstraction.WsrfbfFactory#getWsrfbfWriter()
	 */
	@Override
	public WsrfbfWriter getWsrfbfWriter() {
		return this.model.getWsrfbfModelWriter();
	}
	
}
