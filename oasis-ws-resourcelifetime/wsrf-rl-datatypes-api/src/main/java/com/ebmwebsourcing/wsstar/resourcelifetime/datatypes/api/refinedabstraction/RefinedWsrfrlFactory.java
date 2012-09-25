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
package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.refinedabstraction;

import java.util.Date;

import javax.xml.datatype.Duration;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.CurrentTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.Destroy;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.DestroyResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ResourceNotDestroyedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.ScheduledResourceTerminationRP;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.SetTerminationTimeResponse;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationNotification;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTime;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.TerminationTimeChangeRejectedFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.UnableToSetTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlReader;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.abstraction.WsrfrlWriter;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.implementor.WsrfrlModelFactory;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;

public final class RefinedWsrfrlFactory implements WsrfrlFactory {

	private WsrfrlModelFactory model;
	
	//	################# EXPERITMENTAL SINGLETON PATTERN FORM ######################
	
	private RefinedWsrfrlFactory () {
	}	

	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	 * or the first access to SingletonHolder.INSTANCE, not before.
	 */
	private static final class WsrfrlFactoryHolder { 		
		private static final RefinedWsrfrlFactory INSTANCE = new RefinedWsrfrlFactory();
		
		private WsrfrlFactoryHolder(){}
	}

	public static WsrfrlFactory getInstance() throws WsrfrlException {
		RefinedWsrfrlFactory singleton = WsrfrlFactoryHolder.INSTANCE;
		if (singleton.model == null){ 			
			throw new WsrfrlException("\n\t/!\\ WARNING /!\\\n" +
					"The WsrfrlModelFactory have not been initialized !!!\n" +
					"Please create a \"WsrfrlModelFactory\" instance and \n" +
					"call the \"getInstance(WsrfrlModelFactory)\" method instead." +
					"\n\t/!\\ WARNING /!\\\n");
		}
		return singleton;		
	}	
	
	public static WsrfrlFactory getInstance(WsrfrlModelFactory modelFactory) {		
		RefinedWsrfrlFactory singleton = WsrfrlFactoryHolder.INSTANCE;
		singleton.model = modelFactory;		 	
		return singleton;		
	}	 		
	//	#######################################################################
	
	public WsrfrlModelFactory getModel() {
		return this.model;
	}
	
	protected void  setModel(WsrfrlModelFactory model) {
		this.model = model;
	}
	
	@Override
	public CurrentTime createCurrentTime(Date value) {
		return this.model.createWsrfrlModelCurrentTime(value);	
	}

	@Override
	public Destroy createDestroy() {
		return this.model.createWsrfrlModelDestroy();
	}

	@Override
	public DestroyResponse createDestroyResponse() {		
		return this.model.createWsrfrlModelDestroyResponse();
	}

	@Override
	public ResourceNotDestroyedFaultType createResourceNotDestroyedFaultType(
			Date timestamp) {
		return this.model.createWsrfrlModelResourceNotDestroyedFaultType(timestamp);
	}

	@Override
	public ScheduledResourceTerminationRP createScheduledResourceTerminationRP(
			CurrentTime curTime, TerminationTime termTime) {
		return this.model.createWsrfrlModelScheduledResourceTerminationRP(curTime, termTime);
	}

	@Override
	public SetTerminationTime createSetTerminationTime(Date value) {
		return this.model.createWsrfrlModelSetTerminationTime(value);
	}

	@Override
	public SetTerminationTime createSetTerminationTime(Duration value) {
		return this.model.createWsrfrlModelSetTerminationTime(value);
	}

	@Override
	public SetTerminationTimeResponse createSetTerminationTimeResponse(
			Date curTime, Date newTermTime) {
		return this.model.createWsrfrlModelSetTerminationTimeResponse(curTime, newTermTime);
	}

	@Override
	public TerminationNotification createTerminationNotification(Date termTime) {
		return this.model.createWsrfrlModelTerminationNotification(termTime);
	}

	@Override
	public TerminationTime createTerminationTime(Date value) {
		return this.model.createWsrfrlModelTerminationTime(value);
	}

	@Override
	public TerminationTimeChangeRejectedFaultType createTerminationTimeChangeRejectedFaultType(
			Date timestamp) {
		return this.model.createWsrfrlModelTerminationTimeChangeRejectedFaultType(timestamp);
	}

	@Override
	public UnableToSetTerminationTimeFaultType createUnableToSetTerminationTimeFaultType(
			Date timestamp) {
		return this.model.createWsrfrlModelUnableToSetTerminationTimeFaultType(timestamp);		
	}

	@Override
	public WsrfrlReader getWsrfrlReader() {
		return this.model.getWsrfrlModelReader();
	}

	@Override
	public WsrfrlWriter getWsrfrlWriter() {
		return this.model.getWsrfrlModelWriter();
	}

}
