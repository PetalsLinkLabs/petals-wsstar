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

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;

/**
 * Interface related to "InvalidModificationFaultType" WS-ResourceProperties type.
 * Thought to be the most "user-friendly" as possible according to
 * WS-ResourceProperties specification (part of WS-ResourceProperies specifications set)
 * content and especially its associated XML Schema
 * 
 * @author Thierry Déjean - EBM Websourcing
 */
public interface InvalidModificationFaultType extends BaseFaultType {
	
	/**
	 * Get the failure details as {@link ResourcePropertyChangeFailureType} object
	 * 
	 * @return {@link ResourcePropertyChangeFailureType} object value
	 */
	ResourcePropertyChangeFailureType getResourcePropertyChangeFailure();
	
	/**
	 * Set {@link ResourcePropertyChangeFailureType} object value
	 * 
	 * @param failure the {@link ResourcePropertyChangeFailureType} object to set
	 */
	void setResourcePropertyChangeFailure(ResourcePropertyChangeFailureType failure);

}
