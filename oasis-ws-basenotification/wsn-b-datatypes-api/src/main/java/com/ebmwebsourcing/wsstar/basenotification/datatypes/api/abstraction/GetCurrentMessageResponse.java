package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.util.List;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType.Message;

public interface GetCurrentMessageResponse {

	 List<Message> getNotifications();
	
	 void addCurrentMessage(Message notification);
	
}
