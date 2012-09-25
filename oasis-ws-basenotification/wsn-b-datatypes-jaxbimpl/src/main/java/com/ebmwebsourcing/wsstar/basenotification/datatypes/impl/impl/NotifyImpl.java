package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.util.ArrayList;
import java.util.List;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class NotifyImpl implements Notify {

	private com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify jaxbTypeObj;
//	private static Logger logger  = Logger.getLogger(NotifyImpl.class.getSimpleName());
	
	/**
	 * Default constructor
	 */
	protected NotifyImpl(NotificationMessageHolderType notifyMsg) {		
		this.jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotify();
		this.jaxbTypeObj.getNotificationMessage().add(NotificationMessageHolderTypeImpl.toJaxbModel(notifyMsg));		
	}

	protected NotifyImpl(com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify jaxbTypeObj){
		this.jaxbTypeObj = jaxbTypeObj;
	}
	
	protected final com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify getJaxbTypeObj() {
		return jaxbTypeObj;
	}
	
	@Override
	public final List<NotificationMessageHolderType> getNotificationMessage() {
		List<NotificationMessageHolderType> notifMsgHolders = new ArrayList<NotificationMessageHolderType>();
		
		List<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType> jaxbNotifMsgHolders =
			this.jaxbTypeObj.getNotificationMessage();		
		
		for (com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType notifMsgItem : jaxbNotifMsgHolders) {
			notifMsgHolders.add(new NotificationMessageHolderTypeImpl(notifMsgItem));
		}		
		return notifMsgHolders;
	}

	@Override
	public final void addNotificationMessage(NotificationMessageHolderType msg) {
		this.jaxbTypeObj.getNotificationMessage().add(NotificationMessageHolderTypeImpl.toJaxbModel(msg));
	}
	
	/**
	 * A way to create a  {@link com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType}
	 *  "Jaxb model type" object from a {@link NotificationMessageHolderType} "api type" one  
	 * @param apiTypeObj
	 * @return
	 */
	public static com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify toJaxbModel(
			Notify apiTypeObj) {
		com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify jaxbTypeObj = null;
		
		if (apiTypeObj instanceof NotifyImpl){
			jaxbTypeObj = ((NotifyImpl)apiTypeObj).getJaxbTypeObj();
		} else {
			jaxbTypeObj = WsnbJAXBContext.WSNB_JAXB_FACTORY.createNotify();
			
			// ~~~~ Set NotificationMessageHolders ~~~~~
			List<NotificationMessageHolderType> notifmsgs = apiTypeObj.getNotificationMessage();
			for (NotificationMessageHolderType notifMsgHolderItem : notifmsgs) {
				jaxbTypeObj.getNotificationMessage().add(NotificationMessageHolderTypeImpl.toJaxbModel(notifMsgHolderItem));
			}			
		}
		return jaxbTypeObj;
	}
	
	@Override
	public List<Object> getAny() {
		return this.jaxbTypeObj.getAny();
	}
}
