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
 * Interface related to "UpdateType" WS-ResourceProperties type
 * Thought to be the most "user-friendly" as possible according to
 * WS-ResourceProperties specification (part of WS-Resource specifications set)
 * content and especially its associated XML Schema
 * 
 * @author Thierry Déjean - EBM Websourcing
 */
public interface UpdateType {
	
	/**
	 * Get the content of the "UpdateType" typed object as a
	 * list of {@link Element} object
	 * 
	 * @return list of {@link Element} objects representing properties set
	 */
	List<Element> getUpdateContent();
	
	/**
	 * Set the content of the "UpdateType" typed object as a
	 * list of {@link Element} object
	 * 
	 * @param values list of properties represented as {@link Element} objects list
	 */
	void setUpdateContent(List<Element> values);

}
