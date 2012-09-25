package com.ebmwebsourcing.wsstar.resource.datatypes.impl.test;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction.RefinedWsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.WsrfbfModelFactoryImpl;
import com.ebmwebsourcing.wsstar.resource.datatypes.api.test.AbsWsrfrFaultsUnitTests;
import com.ebmwebsourcing.wsstar.resource.datatypes.impl.impl.WsrfrModelFactoryImpl;

public class WsrfrJaxbModelUnitTest extends AbsWsrfrFaultsUnitTests {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.resource.api.basefaults.test.AbstractWsrfrApiUnitTests#setWsrfbfModelFactory()
	 */
	@Override
	protected void setWsrfrModelFactory() {
		this.modelFactoryImpl = new WsrfrModelFactoryImpl();
//		this.isDebug = true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.resource.api.basefaults.test.AbstractWsrfrApiUnitTests#initRequiredDependencyModelFactories()
	 */
	@Override
	protected void initRequiredDependencyModelFactories() {
	//	RefinedWsaFactory.getInstance(new WsaModelFactoryImpl());
		RefinedWsrfbfFactory.getInstance(new WsrfbfModelFactoryImpl());
	}
	
}
