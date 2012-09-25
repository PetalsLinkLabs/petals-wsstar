package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.util.Date;

public interface RenewResponse {

	 Date getTerminationTime();
	
	 void setTerminationTime(Date value);
	
	 Date getCurrentTime();
		
	 void setCurrentTime(Date currentTimeVar);

}
