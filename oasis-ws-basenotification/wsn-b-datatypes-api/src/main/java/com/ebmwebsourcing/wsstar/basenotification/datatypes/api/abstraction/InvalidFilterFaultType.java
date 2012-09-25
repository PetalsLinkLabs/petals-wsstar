package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.util.List;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;

public interface InvalidFilterFaultType extends BaseFaultType{

	 List<QName> getUnknwonFilters();
	
	 void addUnknwonFilter(QName value);
	
}
