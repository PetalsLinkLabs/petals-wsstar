package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.util.List;

public interface GetMessagesResponse {

	 List<NotificationMessageHolderType> getNotificationMessages();
	
	 void addNotificationMessage(NotificationMessageHolderType notification);
}
