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
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;


/**
 * Interface related to "BaseFaultType" WS-BaseFaults type
 * Thought to be the most "user-friendly" as possible according to
 * WS-BaseFaults specification (part of WS-Resource specifications set)
 * content and especially its associated XML Schema 
 * 
 * @author Thierry Déjean - EBM Websourcing
 */
public interface BaseFaultType {

	/**
	 * Interface related to "BaseFaultType.Description" WS-BaseFaults sub-type
	 * Thought to be the most "user-friendly" as possible according to
	 * WS-BaseFaults specification (part of WS-Resource specifications set)
	 * content and especially its associated XML Schema 
	 * @author thierry
	 */
	interface Description {
		
		/**
		 * Get the language of the description (based on "ISO 639 code")
		 * 
		 * @return the language value as a {@link Local} object
		 */
		Locale getLang();
		
		/**
		 * Set the language of the description (based on "ISO 639 code")
		 *   
		 * @param value the {@link Local} object representing the description's language
		 */
		void setLang(Locale value);
		
		/**
		 * Get the description content  
		 * 
		 * @return a {@link String} representing the description content 
		 */
		String getValue();
		
		/**
		 * Set the description content
		 * 
		 * @param value the {@link String} representing the description content
		 */
		void setValue(String value);
	}
	
	/**
	 * Interface related to "BaseFaultType.ErrorCode" WS-BaseFaults sub-type
	 * Thought to be the most "user-friendly" as possible according to
	 * WS-BaseFaults specification (part of WS-Resource specifications set)
	 * content and especially its associated XML Schema 
	 * @author thierry
	 */
	interface ErrorCode {
		
		/**
		 * Get the dialect associated to the Error Code used 
		 * 
		 * @return the dialect as {@link URI} object 
		 */
		URI getDialect();
		
		/**
		 * Set the dialect associated to the Error Code used
		 * 
		 * @param dialect the URI of the currently used dialect
		 */
		void setDialect(URI dialect);
		
		/**
		 * Get the list of Error code
		 * 
		 * @return a list of error code according the used dialect 
		 */
		List<Object> getCodes(); 
		
		/**
		 * add a Error code the the list
		 * 
		 * @param code the error Code to add
		 */
		void addCode(Object code);
		
	}
	
	/**
	 * Interface related to "BaseFaultType.FaultCause" WS-BaseFaults sub-type
	 * Thought to be the most "user-friendly" as possible according to
	 * WS-BaseFaults specification (part of WS-Resource specifications set)
	 * content and especially its associated XML Schema 
	 * @author thierry
	 */
	interface FaultCause{
		/**
		 * Get the Fault cause value as an {@link BaseFaultType}
		 * Object if tha associated xml-schema is defined in the
		 * "WS-Resource" xmlSchemas. Or a {@link Element} representing
		 * an inherited {@link BaseFaultType} type Object
		 * 
		 * @return {@link Element} object representing an inherited type of {@link BaseFaultType}   
		 */
		Element getAny();
		
		/**
		 * Set {@link Element} representation of an inherited {@link BaseFaultType} type
		 *  
		 * @param value the {@link Element} representing an inherited {@link BaseFaultType} type
		 */
		void setAny(Element value);
		
	}
	
	/**
	 * Get the list of descriptions of the fault  
	 * 
	 * @return list of {@link Description} object
	 */
	List<BaseFaultType.Description> getDescriptions();
	
	/**
	 * Add a description to the fault	
	 * 
	 * @param value the description as {@link Description} object to add
	 */
	void addDescription(BaseFaultType.Description value);
	
	/**
	 * Get the error code of the fault
	 * 
	 * @return
	 */
	BaseFaultType.ErrorCode getErrorCode();
	
	/**
	 * Set the error code of the fault
	 * 
	 * @param value the ErrorCode as a {@link ErrorCode} object
	 */
	void setErrorCode(BaseFaultType.ErrorCode value);
	
	/**
	 * Get the Fault cause of the fault
	 * 
	 * @return the ErrorCode as a {@link ErrorCode} object
	 */
	BaseFaultType.FaultCause getFaultCause();
	
	/**
	 * Set the Fault cause of the fault
	 * 
	 * @param value the fault cause as {@link FaultCause} object
	 */
	void setFaultCause(BaseFaultType.FaultCause value);
	
	/**
	 * Get the "originator" - means endpoint that has reported the fault -
	 * of the fault 
	 * 
	 * @return the "originator endpoint as {@link EndpointReferenceType} object
	 */
	EndpointReferenceType getOriginator();
	
	/**
	 * Set the "originator" - means endpoint that has reported the fault -
	 * of the fault
	 * 
	 * @param value the originator {@link EndpointReferenceType} object
	 */
	void setOriginator(EndpointReferenceType value);
	
	/**
	 * Get the timestamp - creation date - of the fault 
	 * 
	 * @return the timestamp as a {@link Date} object
	 */
	Date getTimestamp();
	
	/**
	 *  Set the timestamp - creation date - of the fault 
	 *  
	 * @param value the timestamp as a {@link Date} object
	 */
	void setTimestamp(Date value);
		
}
