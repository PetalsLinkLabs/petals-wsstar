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
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.wsaddressing10.api.element.Address;
import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util.WsaUnitTestsUtils;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.utils.WSNUtil;



///** 
// * /!\ "WS-Addressing" Jaxb model unit test classes dependency !! 
// */

public class WsrfbfUnitTestsUtils {
		
	// ---------------------------------------------------------
	public static final String WSRFBF_SPECIFICATION_NAME = "OASIS \"WS-BaseFaults\"";
	
	public static final String WSRFBF_XML_SCHEMA_PATH = "/schemas/bf-2.xsd";
	
	/*
	public static final String WSRFR_SPECIFICATION_NAME = "OASIS \"WS-Resource\"";
	
	public static final String WSRFR_XML_SCHEMA_PATH = "/schemas/r-2.xsd";
	
	public static final String WSRFRL_SPECIFICATION_NAME = "OASIS \"WS-ResourceLifetime\"";
	
	public static final String WSRFRL_XML_SCHEMA_PATH = "/schemas/rl-2.xsd";
	
	public static final String WSRFRP_SPECIFICATION_NAME = "OASIS \"WS-ResourceProperties\"";
	
	public static final String WSRFRP_XML_SCHEMA_PATH = "/schemas/rp-2.xsd";
	*/	
	public static final String XML_XML_SCHEMA_PATH = "/schemas/xml.xsd";
	
	public static final String[] WSRFBF_XML_SCHEMAS_PATHS = {
		WsaUnitTestsUtils.WSA_XML_SCHEMA_PATH,
		WsrfbfUnitTestsUtils.XML_XML_SCHEMA_PATH,
		WsrfbfUnitTestsUtils.WSRFBF_XML_SCHEMA_PATH};
	// --------------------------------------------------------

	/**
	 * Create a Default DOM Element object to used as FaultCauseType content.
	 * It should be a DOM Element representation of a BaseFaultType - or inherited - Type. 
	 *  		See WS-BaseFaults OASIS specification for details
	 */
	public static Element createDefaultFaultCauseContent(String faultCausePath){
		
		Document faultCauseContentAsDocument = 
			WsaUnitTestsUtils.fromStreamToDocument(WsrfbfUnitTestsUtils.class.getResourceAsStream(faultCausePath));
		return faultCauseContentAsDocument.getDocumentElement();
		
	}

	public static EndpointReferenceType createDefaultBaseFaultOriginator() throws URISyntaxException {
		EndpointReferenceType defaultMinimalEdp = WSNUtil.getInstance().getXmlObjectFactory().create(EndpointReferenceType.class);
		URI address = new URI(WsaUnitTestsUtils.DEFAULT_ENDPOINT_URI_VALUE); 	
		Address defaultAddr = WSNUtil.getInstance().getXmlObjectFactory().create(Address.class);
		defaultAddr.setValue(address);
		defaultMinimalEdp.setAddress(defaultAddr);		
		return defaultMinimalEdp;
	}

}
