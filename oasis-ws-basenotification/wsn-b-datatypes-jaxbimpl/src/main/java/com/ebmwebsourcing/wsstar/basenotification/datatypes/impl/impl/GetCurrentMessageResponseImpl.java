package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType.Message;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class GetCurrentMessageResponseImpl implements GetCurrentMessageResponse {
	
	private com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse jaxbTypeObj;
//	private static Logger logger  = Logger.getLogger(GetCurrentMessageResponseImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected GetCurrentMessageResponseImpl(NotificationMessageHolderType.Message notification) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createGetCurrentMessageResponse();
		this.jaxbTypeObj.getAny().add(notification.getAny());
	}

	protected GetCurrentMessageResponseImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final List<NotificationMessageHolderType.Message> getNotifications() {
		List<NotificationMessageHolderType.Message> notifications = new ArrayList<NotificationMessageHolderType.Message>();
		List<Object> jaxbNotif = this.jaxbTypeObj.getAny();
		if (jaxbNotif != null){
			for (Object jaxbNotifItem : jaxbNotif) {
				if (jaxbNotifItem instanceof Element){
					notifications.add(new NotificationMessageHolderTypeImpl.MessageImpl((Element)jaxbNotifItem));
				}
			}	
		}
		return notifications;
	}

	@Override
	public final void addCurrentMessage(Message notification) {
		this.jaxbTypeObj.getAny().add(notification.getAny());
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.notification.base.GetCurrentMessageResponse}
	 *  "Jaxb model type" object from a {@link GetCurrentMessageResponse} "api type" one  
	 *    
	 * @param apiTypeObj
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse toJaxbModel(GetCurrentMessageResponse apiTypeObj) {

		com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse jaxbTypeObj = null;
		
		if (apiTypeObj instanceof GetCurrentMessageResponseImpl){
			jaxbTypeObj = ((GetCurrentMessageResponseImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createGetCurrentMessageResponse();

			// ~~~~ Set Notification(s) ~~~~
			List<NotificationMessageHolderType.Message> notifications = apiTypeObj.getNotifications();
			List<Object> jaxbAny = jaxbTypeObj.getAny();
			if (notifications != null){
				for (NotificationMessageHolderType.Message notificationItem : notifications) {
					jaxbAny.add(notificationItem.getAny());
				}
			}
		}

		return jaxbTypeObj;
	}
}
