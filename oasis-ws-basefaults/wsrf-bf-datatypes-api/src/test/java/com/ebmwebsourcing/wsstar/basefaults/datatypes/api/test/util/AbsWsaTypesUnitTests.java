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
package com.ebmwebsourcing.wsstar.basefaults.datatypes.api.test.util;

import java.net.URI;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.ebmwebsourcing.wsaddressing10.api.type.EndpointReferenceType;



/**
 * @author Thierry DÉJEAN - eBM WebSourcing
 */
public abstract class AbsWsaTypesUnitTests extends TestCase {

	protected boolean isDebug = false;
	

	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	//   ############  "Check Result" Methods  ################   //
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
	/**
	 * Check a given (provided by the "WsaReader") EndpointReferenceType object
	 * 	 against a reference one
	 * @param specificationRef TODO
	 * @param isDebug TODO
	 * @param isDebug TODO
	 * @param createdEdp
	 * @param readEdp
	 */
	public static final void checkEndpointReferenceType(EndpointReferenceType expectedEdp,EndpointReferenceType toCheckEdp,
			String specificationRef, boolean isDebug){
		
		// ~~~~~~~ Check EndpointReferenceType "address" ~~~~~~~~ //
		URI expectedURI = expectedEdp.getAddress().getValue(), toCheckURI = toCheckEdp.getAddress().getValue();
		if (isDebug)
			System.out.println("[DEBUG] --> toCheckEndpoint address : " + toCheckURI.toString()+
					"\n[DEBUG] --> expectedEndpoint address : " + expectedURI.toString() + "\n");
		
		Assert.assertTrue(WsaUnitTestsUtils.getFailedMessagePrefix(specificationRef) +
				"endpoints have different address values\n" +
				"\t(-> toCheckEdp address : " + toCheckURI.toString() +
				"\n\t-> expectedEdp address : " + expectedURI.toString() + ")", 
				toCheckURI.equals(expectedURI));
		
		Assert.assertTrue(expectedEdp.equals(toCheckEdp));
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//		####################	UNIT TESTS Methods   #########################
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	


}
