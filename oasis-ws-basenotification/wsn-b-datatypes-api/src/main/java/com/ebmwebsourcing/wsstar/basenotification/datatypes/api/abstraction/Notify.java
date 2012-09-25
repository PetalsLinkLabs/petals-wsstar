package com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction;

import java.util.List;

public interface Notify {

	 List<NotificationMessageHolderType> getNotificationMessage();

	 void addNotificationMessage(NotificationMessageHolderType msg);

	List<Object> getAny();
}
