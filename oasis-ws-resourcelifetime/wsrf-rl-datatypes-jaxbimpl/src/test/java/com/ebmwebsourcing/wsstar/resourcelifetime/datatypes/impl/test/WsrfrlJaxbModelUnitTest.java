package com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.test;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.refinedabstraction.RefinedWsrfbfFactory;
import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.impl.WsrfbfModelFactoryImpl;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.api.test.AbsWsrfrlFaultsTypesAndPayloadsUnitTests;
import com.ebmwebsourcing.wsstar.resourcelifetime.datatypes.impl.impl.WsrfrlModelFactoryImpl;

public class WsrfrlJaxbModelUnitTest extends AbsWsrfrlFaultsTypesAndPayloadsUnitTests {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.resource.api.basefaults.test.AbstractWsrfrlApiUnitTests#setWsrfrlModelFactory()
	 */
	@Override
	protected void setWsrfrlModelFactory() {
		this.modelFactoryImpl = new WsrfrlModelFactoryImpl();		
//		this.isDebug = true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.resource.api.basefaults.test.AbstractWsrfrlApiUnitTests#initRequiredDependencyModelFactories()
	 */
	@Override
	protected void initRequiredDependencyModelFactories() {		
		//RefinedWsaFactory.getInstance(new WsaModelFactoryImpl());
		RefinedWsrfbfFactory.getInstance(new WsrfbfModelFactoryImpl());		
	}

}
