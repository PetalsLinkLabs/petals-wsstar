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
package com.ebmwebsourcing.wsstar.resource.datatypes.api.refinedabstraction;

import java.util.Date;

import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnavailableFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.ResourceUnknownFaultType;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrFactory;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrReader;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.abstraction.WsrfrWriter;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.implementor.WsrfrModelFactory;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.utils.WsrfrException;

public final class RefinedWsrfrFactory implements WsrfrFactory {
	
	private WsrfrModelFactory model;
	
	//	################# EXPERITMENTAL SINGLETON PATTERN FORM ######################
	
	private RefinedWsrfrFactory () {
	}	

	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	 * or the first access to SingletonHolder.INSTANCE, not before.
	 */
	private static final class WsrfrFactoryHolder { 		
		private static final RefinedWsrfrFactory INSTANCE = new RefinedWsrfrFactory();		
		
		private WsrfrFactoryHolder(){}
	}

	public static WsrfrFactory getInstance() throws WsrfrException {
		RefinedWsrfrFactory singleton = WsrfrFactoryHolder.INSTANCE;
		if (singleton.model == null){ 			
			throw new WsrfrException("\n\t/!\\ WARNING /!\\\n" +
					"The WsrfrModelFactory have not been initialized !!!\n" +
					"Please create a \"WsrfrModelFactory\" instance and \n" +
					"call the \"getInstance(WsrfrModelFactory)\" method instead." +
					"\n\t/!\\ WARNING /!\\\n");
		}
		return singleton;		
	}	
	
	public static WsrfrFactory getInstance(WsrfrModelFactory modelFactory) {		
		RefinedWsrfrFactory singleton = WsrfrFactoryHolder.INSTANCE;
		singleton.model = modelFactory;		 	
		return singleton;		
	}	 		
	//	#######################################################################
	
	public WsrfrModelFactory getModel() {
		return this.model;
	}
	
	protected void setModel(WsrfrModelFactory model) {
		this.model = model;
	}
	
	@Override
	public ResourceUnknownFaultType createResourceUnknownFaultType(
			Date timestamp) {
		return this.model.createWsrfrModelResourceUnknownFaultType(timestamp);
	}

	@Override
	public ResourceUnavailableFaultType createResourceUnavailableFaultType(
			Date timestamp) {
		return this.model.createWsrfrModelResourceUnavailableFaultType(timestamp);
	}

	@Override
	public WsrfrReader getWsrfrReader() {
		return this.model.getWsrfrModelReader();
	}

	@Override
	public WsrfrWriter getWsrfrWriter() {
		return this.model.getWsrfrModelWriter();
	}
	
	

}
