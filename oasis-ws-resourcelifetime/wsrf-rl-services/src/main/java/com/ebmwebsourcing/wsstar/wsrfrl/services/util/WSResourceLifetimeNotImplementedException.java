/**
 * Copyright (c) 2009 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * $Id$
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.wsrfrl.services.util;

import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.utils.WsrfrlException;

public class WSResourceLifetimeNotImplementedException extends
		WsrfrlException {

	private static final long serialVersionUID = 1L;

	public WSResourceLifetimeNotImplementedException(String className,String methodName) {
		super("The code of the method \"" + methodName + "\", in the class \"" + className +"\" is not yet implemented !");
	}

}
