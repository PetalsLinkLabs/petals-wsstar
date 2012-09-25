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
package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.test;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction.RefinedWsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.WsrfbfModelFactoryImpl;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.test.AbsWsnbTypesAndPayloadsUnitTests;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl.WsnbModelFactoryImpl;

/**
 * @author Thierry DÉJEAN - eBM WebSourcing
 */
public class WsnbJaxbModelTypesAndPayloadsUnitTest extends
		AbsWsnbTypesAndPayloadsUnitTests {

	@Override
	protected void setWsnbModelFactory() {
		this.modelFactoryImpl = new WsnbModelFactoryImpl();		
//		this.isDebug = true;
	}

	@Override
	protected void initRequiredDependencyModelFactories() {
	//	RefinedWsaFactory.getInstance(new WsaModelFactoryImpl());
		RefinedWsrfbfFactory.getInstance(new WsrfbfModelFactoryImpl());
		//RefinedWstopFactory.getInstance(new WstopModelFactoryImpl());
	}

}
