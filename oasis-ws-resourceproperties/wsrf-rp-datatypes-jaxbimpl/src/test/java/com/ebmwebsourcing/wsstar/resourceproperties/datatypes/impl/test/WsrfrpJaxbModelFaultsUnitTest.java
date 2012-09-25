package com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.test;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction.RefinedWsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.WsrfbfModelFactoryImpl;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.api.test.AbsWsrfrpFaultsTypesUnitTests;
import com.ebmwebsourcing.wsstar.resourceproperties.datatypes.impl.impl.WsrfrpModelFactoryImpl;

public class WsrfrpJaxbModelFaultsUnitTest extends AbsWsrfrpFaultsTypesUnitTests {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.resource.api.basefaults.test.AbstractWsrfrlApiUnitTests#setWsrfrlModelFactory()
	 */

	@Override
	protected void setWsrfrpModelFactory() {
		this.modelFactoryImpl = new WsrfrpModelFactoryImpl();		
//		this.isDebug = true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.resource.api.basefaults.test.AbstractWsrfrlApiUnitTests#initRequiredDependencyModelFactories()
	 */

	@Override
	protected void initRequiredDependencyModelFactories() {		
	//	RefinedWsaFactory.getInstance(new WsaModelFactoryImpl());
		RefinedWsrfbfFactory.getInstance(new WsrfbfModelFactoryImpl());
	}

}
