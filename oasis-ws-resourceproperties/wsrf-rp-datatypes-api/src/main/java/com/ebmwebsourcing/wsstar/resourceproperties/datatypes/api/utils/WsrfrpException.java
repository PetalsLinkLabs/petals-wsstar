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
package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.utils;


/**
 * 
 * @author Nicolas Salatge - eBM WebSourcing
 * 
 */
public class WsrfrpException extends Exception {
   
	public static final long serialVersionUID = 1;

	private String faultCode;
	
    public WsrfrpException(final String faultCode, final String msg, final Throwable t) {
        super(msg,t);
    	this.faultCode = faultCode;
    }

    public final String getFaultCode(){
    	return this.faultCode;
    }
    
    public WsrfrpException(final String msg, final Throwable t) {
        super(msg, t);
    }

    public WsrfrpException(final Throwable t) {
        super(t);
    }


    public WsrfrpException(final String msg) {
        super(msg);
    }

    public WsrfrpException(final String faultCode, final String msg) {
        this(faultCode, msg, null);
    }
 
}
