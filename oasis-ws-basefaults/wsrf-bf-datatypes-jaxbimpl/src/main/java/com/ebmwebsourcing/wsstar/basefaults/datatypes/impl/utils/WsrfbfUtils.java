/**
 * Addressing Descriptor - SOA Tools Platform.
 * Copyright (c) 2008 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * XmlUtils.java
 * -------------------------------------------------------------------------
 */

package com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public final class WsrfbfUtils {

	private WsrfbfUtils(){}
	
	/**
	 * Convert to {@link XMLGregorianCalendar} type
	 * and also to avoid to duplicate piece of code
	 *    
	 * @param time The {@link GregorianCalendar} Object to convert to a {@link XMLGregorianCalendar} object
	 * @param logger The logger to use to log possible {@link DatatypeConfigurationException}
	 * @return the corresponding {@link XMLGregorianCalendar} object.
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(GregorianCalendar time, Logger logger) {		
		XMLGregorianCalendar xmlTime = null;
		
		try {
			xmlTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(time);
		} catch (DatatypeConfigurationException e) {
			logger.log(Level.WARNING, "a " + e.getClass().getSimpleName() +	"exception have been thrown." +
					" This is due to a problem during conversion of from type \"GregorianCalendar\"" +
					"to type \"XMLGregorianCalendar\"");
		}	
		return xmlTime;
	}
	
	/**
	 * Convert to {@link XMLGregorianCalendar} type
	 * and also to avoid to duplicate piece of code
	 *    
	 * @param time The {@link Date} Object to convert to a {@link XMLGregorianCalendar} object
	 * @param logger The logger to use to log possible {@link DatatypeConfigurationException}
	 * @return the corresponding {@link XMLGregorianCalendar} object.
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date time, Logger logger) {		
		GregorianCalendar timeAsCalendar = new GregorianCalendar();
		timeAsCalendar.setTime(time);
		return WsrfbfUtils.toXMLGregorianCalendar(timeAsCalendar, logger);
	}
	
	/**
	 * provide a "generic" message content for Binding Exception 
	 * 
	 * @param instance the Object that can not be marshalled
	 * @return a string that represent a generic Exception message
	 */
	public static String getBindingExMessage(Object instance) {
		return "Failed to build XML binding from "+ instance.getClass().getSimpleName() + " Api Java classes";	
	}
	
	
}
