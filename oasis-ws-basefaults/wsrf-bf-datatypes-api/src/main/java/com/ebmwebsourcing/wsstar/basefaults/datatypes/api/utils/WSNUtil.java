/*******************************************************************************
 * Copyright (c) 2011 EBM Websourcing.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     EBM Websourcing - initial API and implementation
 ******************************************************************************/
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils;

import com.ebmwebsourcing.easybox.api.XmlContext;
import com.ebmwebsourcing.easybox.api.XmlContextFactory;
import com.ebmwebsourcing.easybox.api.XmlObjectFactory;
import com.ebmwebsourcing.easybox.api.XmlObjectReader;
import com.ebmwebsourcing.easybox.api.XmlObjectWriter;

public class WSNUtil {
	
	
	private XmlContext xmlContext = null;

	private XmlObjectFactory xmlObjectFactory = null;
	
	private ThreadLocal<XmlObjectWriter> xmlwriter = null;

	private ThreadLocal<XmlObjectReader> xmlreader = null;
	
	private static WSNUtil INSTANCE = null;

	
	private WSNUtil() {
		xmlContext = new XmlContextFactory().newContext();
		xmlObjectFactory = xmlContext.getXmlObjectFactory();
		xmlwriter = new ThreadLocal<XmlObjectWriter>() {
			protected XmlObjectWriter initialValue() {
				return xmlContext.createWriter();
			}
		};
		xmlreader = new ThreadLocal<XmlObjectReader>() {
			protected XmlObjectReader initialValue() {
				return xmlContext.createReader();
			}
		};
		
	}
	
	public static WSNUtil getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new WSNUtil();
		}
		return INSTANCE;
	}
 	
	public ThreadLocal<XmlObjectWriter> getWriter() {
		return xmlwriter;
	}

	public ThreadLocal<XmlObjectReader> getReader() {
		return xmlreader;
	}

	public XmlContext getXmlContext() {
		return xmlContext;
	}

	public XmlObjectFactory getXmlObjectFactory() {
		return xmlObjectFactory;
	}	

}
