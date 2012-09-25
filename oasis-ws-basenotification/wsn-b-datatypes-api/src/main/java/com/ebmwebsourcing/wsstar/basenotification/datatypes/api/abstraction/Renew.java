package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;


import java.util.Date;

import javax.xml.datatype.Duration;

public interface Renew {

	 Object getTerminationTime();
	
	 void setTerminationTime(Duration value);
	
	 void setTerminationTime(Date value);

}
