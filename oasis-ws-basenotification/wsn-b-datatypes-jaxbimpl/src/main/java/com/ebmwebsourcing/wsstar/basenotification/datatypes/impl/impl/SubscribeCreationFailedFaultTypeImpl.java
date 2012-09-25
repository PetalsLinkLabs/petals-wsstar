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
package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.Date;
import java.util.logging.Logger;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.BaseFaultTypeImpl;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfUtils;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeCreationFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

/**
 * @author Thierry DÉJEAN - eBM WebSourcing
 */
public class SubscribeCreationFailedFaultTypeImpl extends BaseFaultTypeImpl
		implements SubscribeCreationFailedFaultType {
	/**
	 * Default constructor
	 * 
	 * Note : According to the xml schema the "timestamp" field is mandatory
	 * 
	 * @param timestamp the timestamp of the {@link SubscribeCreationFailedFaultType) object that must
	 * 		  be {@link Date} representation of an Date xml type.
	 */
	protected SubscribeCreationFailedFaultTypeImpl(Date timestamp){
		super(Logger.getLogger(SubscribeCreationFailedFaultTypeImpl.class.getSimpleName()));
		
		com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType jaxbTypeObj = 
			new com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType();
		
		jaxbTypeObj.setTimestamp(WsrfbfUtils.toXMLGregorianCalendar(timestamp, this.getLogger()));
		
		this.setJaxbTypeObj(jaxbTypeObj);
	}
	
	protected SubscribeCreationFailedFaultTypeImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType jaxbTypeObj){
		super(jaxbTypeObj,Logger.getLogger(SubscribeCreationFailedFaultTypeImpl.class.getSimpleName()));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType}
	 *  "Jaxb model type" object from a {@link SubscribeCreationFailedFaultType} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType toJaxbModel(SubscribeCreationFailedFaultType apiTypeObj){

		com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType jaxbTypeObj = null;

		if (apiTypeObj instanceof SubscribeCreationFailedFaultTypeImpl) {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType)
			((SubscribeCreationFailedFaultTypeImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = (com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType) 
			BaseFaultTypeImpl.toJaxbModel(apiTypeObj,
					WsnbJAXBContext.WSNB_JAXB_FACTORY.createSubscribeCreationFailedFaultType());
		}
		return jaxbTypeObj;
	}
}
