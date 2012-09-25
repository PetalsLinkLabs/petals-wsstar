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
 * Interface related to "ResourcePropertyValueChangeNotificationType" WS-ResourceProperties
 * Type. Thought to be the most "user-friendly" as possible according to
 * WS-ResourceProperties specification (part of WS-Resource
 * specifications set) content and especially its associated XML Schema
 * 
 * @author Thierry Déjean - EBM Websourcing
 */
public interface ResourcePropertyValueChangeNotificationType {
	
	/**
	 * Interface related to "ResourcePropertyValueChangeNotificationType.NewValues"
	 * WS-ResourceProperties sub-type.
	 * Thought to be the most "user-friendly" as possible according to
	 * WS-ResourceProperties specification (part of WS-Resource specifications set)
	 * content and especially its associated XML Schema 
	 */
	interface NewValues {
		
		/**
		 * Get the list of values as {@link Element}
		 * 
		 * @return list of {@link Element} representing value
		 */
		List<Element> getValues();
		
		/**
		 * Add a value as {@link Element}
		 * 
		 * @param valueAsElt the value to add as {@link Element} 
		 */
		void addValue(Element valueAsElt);
	}
	
	/**
	 * Interface related to "ResourcePropertyValueChangeNotificationType.OldValues"
	 * WS-ResourceProperties sub-type.
	 * Thought to be the most "user-friendly" as possible according to
	 * WS-ResourceProperties specification (part of WS-Resource specifications set)
	 * content and especially its associated XML Schema 
	 */
	interface OldValues {
		
		/**
		 * Get the list of values as {@link Element}
		 * 
		 * @return list of {@link Element} representing value
		 */
		List<Element> getValues();
		
		/**
		 * Add a value as {@link Element}
		 * 
		 * @param valueAsElt the value to add as {@link Element} 
		 */
		void addValue(Element valueAsElt);
	}
	
	/**
	 * Get the list of property's new value(s) 
	 * 
	 * @return new values as list of {@link Element}
	 */
	NewValues getNewValues();
	
	/**
	 * Set list of property's new value(s)
	 * 
	 * @param newTopicSetValue
	 */
	void setNewValues(NewValues newTopicSetValue);
	
	/**
	 * Get the list of property's old value(s)
	 * 
	 * @return old values as list of {@link Element}
	 */
	OldValues getOldValues();

	/**
	 * Set list of property's old value(s)
	 * 
	 * @param oldTopicSetValue
	 */
	void setOldValues(OldValues oldTopicSetValue);
	
}
