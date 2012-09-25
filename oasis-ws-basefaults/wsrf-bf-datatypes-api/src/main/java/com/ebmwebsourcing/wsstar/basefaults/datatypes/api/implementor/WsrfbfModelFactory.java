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
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.implementor;

import java.net.URI;
import java.util.Date;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfReader;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.WsrfbfWriter;

/**
 * Interface of the "model's factory" that must provide creation methods
 * of WS-BaseFaults Types, called by the "RefinedAbstraction"(see "Bridge"
 * design pattern details for more details ).
 * Mainly expected methods are those which help to build {@link BaseFaultType},
 * {@link BaseFaultType.Description}, {@link BaseFaultType.ErrorCode} and 
 * {@link BaseFaultType.FaultCause} typed objects.
 *  Also provide getters for {@link WsrfbfReader} and {WsrfbfWriter}objects.
 * 
 * NOTE : the "model's factory" is implemented following the "Bridge" design pattern
 * and the "WsrfbfModelFatory" class represents the "implementor" part    
 * 
 * @author Thierry Déjean - EBM Websourcing
 *
 */
public interface WsrfbfModelFactory {
	
	/**
	 * create a minimal {@link BaseFaultType} object, respect to
	 * "model implementation"
	 * 
	 * @param timestamp the creation date of the fault
	 * @return 
	 */
	BaseFaultType createWsrfbfModelBaseFaultType(Date timestamp);
	
	/**
	 * create a minimal {@link BaseFaultType.Description} object, respect to
	 * "model implementation"
	 * 
	 * @param value the description content as {@link String} object
	 * @return
	 */
	BaseFaultType.Description createWsrfbfModelBaseFaultTypeDescription(String value);
	
	/**
	 * create a minimal {@link BaseFaultType.ErrorCode} object, respect to
	 * "model implementation"
	 * 
	 * @param dialect the dialect to use as {@link URI} object 
	 * @return 
	 */
	BaseFaultType.ErrorCode createWsrfbfModelBaseFaultTypeErrorCode(URI dialect);
	
	/**
	 * create a minimal {@link BaseFaultType.FaultCause} object,
	 * respect to "model implementation"
	 * @param faultCause the fault cause representation as {@link Element}  
	 * @return
	 */
	BaseFaultType.FaultCause createWsrfbfModelBaseFaultTypeFaultCause(Element faultCause);
	
	/**
	 * Get a instance of {@link WsrfbfReader} class ,
	 * respect to "model implementation"
	 * 
	 * @return an instance of {@link WsrfbfReader} class
	 */
	WsrfbfReader getWsrfbfModelReader();
	
	/**
	 * Get a instance of {@link WsrfbfaWriter} class,
	 * respect to "model implementation"
	 * 
	 * @return an instance of of {@link WsrfbfWriter} class
	 */
	WsrfbfWriter getWsrfbfModelWriter();
}
