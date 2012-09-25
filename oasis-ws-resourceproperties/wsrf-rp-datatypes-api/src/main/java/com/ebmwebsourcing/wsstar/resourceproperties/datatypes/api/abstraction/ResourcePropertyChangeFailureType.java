/**
 * Copyright (c) 2011 EBM Websourcing, http://www.ebmwebsourcing.com/
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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.abstraction;

import java.util.List;

import org.w3c.dom.Element;

/**
 * Interface related to "ResourcePropertyChangeFailureType" WS-ResourceProperties
 * Type. Thought to be the most "user-friendly" as possible according to
 * WS-ResourceProperties specification (part of WS-Resource
 * specifications set) content and especially its associated XML Schema
 * 
 * @author Thierry Déjean - EBM Websourcing
 */
public interface ResourcePropertyChangeFailureType {
	
	/**
	 * Interface related to "ResourcePropertyChangeFailureType.CurrentValue"
	 * WS-ResourceProperties Type. Thought to be the most "user-friendly" as
	 * possible according to WS-ResourceProperties specification (part of WS-Resource
	 * specifications set) content and especially its associated XML Schema
	 * 
	 * @author Thierry Déjean - EBM Websourcing
	 */
	interface CurrentValue{
		
		/**
		 * Get the list of current values of requested resource properties 
		 * 
		 * @return value(s) of the requested resource's property as a list of {@link Element} objects
		 */
		List<Element> getAny();
		
		/**
		 * Set the list current values of requested resource properties 
		 * 
		 * @param values value(s) of the requested resource's property as a list of {@link Element} objects
		 */
		void setAny(List<Element> values);
	}
	
	/**
	 * Interface related to "ResourcePropertyChangeFailureType.RequestedValue"
	 * WS-ResourceProperties Type. Thought to be the most "user-friendly" as
	 * possible according to WS-ResourceProperties specification (part of WS-Resource
	 * specifications set) content and especially its associated XML Schema
	 * 
	 * @author Thierry Déjean - EBM Websourcing
	 */
	interface RequestedValue{
		
		/**
		 * Get requested values of requested resource properties 
		 * 
		 * @return value(s) of the requested resource's property as a list of {@link Element} objects
		 */
		List<Element> getAny();
		
		/**
		 * Set requested values of requested resource properties 
		 *
		 * @param values value(s) of the requested resource's property as a list of {@link Element} objects
		 */
		void setAny(List<Element> values);
	}
	
	/**
	 * Get the current values of requested resource properties 
	 * 
	 * @return value(s) of the requested resource's property as a list of {@link Element} objects
	 */
	CurrentValue getCurrentValue();
	
	/**
	 * Set the current values of requested resource properties 
	 * 
	 * @param value value of the requested resource's property as a {@link CurrentValue} object
	 */
	void setCurrentValue(CurrentValue value);
	
	/**
	 * Get the current values of requested resource properties 
	 * 
	 * @return value(s) of the requested resource's property as a list of {@link Element} objects
	 */
	RequestedValue getRequestedValue();
	
	/**
	 * Set the requested values of requested resource properties 
	 * 
	 * @param value of the requested resource's property as a {@link RequestedValue} objects
	 */
	void setRequestedValue(RequestedValue value);
	
	/**
	 * Get the value of the "restored" property.
	 * 
	 * @return true if the value have been restored. False if not.
	 */
	boolean isRestored();
	
	/**
	 * Set the value of the "restored" property 
	 * according to the value have been restored or not
	 * 
	 * @param bool set the boolean value of the "restored" property
	 */
	void setRestored(boolean bool);
	

}
