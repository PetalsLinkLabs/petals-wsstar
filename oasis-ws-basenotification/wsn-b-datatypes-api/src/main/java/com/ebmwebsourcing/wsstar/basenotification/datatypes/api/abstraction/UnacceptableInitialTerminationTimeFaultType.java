package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;


import java.util.Date;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.api.abstraction.BaseFaultType;

public interface UnacceptableInitialTerminationTimeFaultType extends
		BaseFaultType {

	 Date getMinimumTime();
	
	 void setMinimumTime(Date value);
	
	 Date getMaximumTime();
	
	 void setMaximumTime(Date value);

}
