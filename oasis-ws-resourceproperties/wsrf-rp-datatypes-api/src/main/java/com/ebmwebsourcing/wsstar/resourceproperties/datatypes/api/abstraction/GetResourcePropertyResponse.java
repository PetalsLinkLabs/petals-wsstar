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
 * Interface related to "GetResourcePropertyResponse" WS-ResourceProperties
 * message exchange payload. Thought to be the most "user-friendly" as possible
 * according to WS-ResourceProperties specification (part of WS-Resource
 * specifications set) content and especially its associated XML Schema
 * 
 * @author Thierry Déjean - EBM Websourcing
 */
public interface GetResourcePropertyResponse {

	/**
	 * Get the requested Ws-Resource property value(s) 
	 * 
	 * @return value(s) of the requested resource's property as a list of {@link Element} objects
	 */
	List<Element> getPropertyValue();
	
	/**
	 * Set the value(s) of the requested resource's property
	 * 
	 * @param value values of the requested resource's property as list of {@link Element} objects
	 */
	void setPropertyValue(List<Element> value);
}
