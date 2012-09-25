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
package com.ebmwebsourcing.wsstar.resource.datatypes.api.utils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestrictedAccessList<E> extends ArrayList<E> {
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger; 	
	
	public RestrictedAccessList(Logger logger) {
		this.logger = logger;
	}
	@Override
	public final boolean add(E newObj){
		this.logger.log(Level.WARNING, "\n\t/!\\ WARNING /!\\\n" +
				"The \"add(...)\" method can not be called directly" +
				" to add new object to the List. Please use the provided" +
				"\"add[ObjectType](...)\" method instead in order to not" +
				"break the model implementation logic. \n\t/!\\ WARNING /!\\\n");
		return false;	
	}
	
}


