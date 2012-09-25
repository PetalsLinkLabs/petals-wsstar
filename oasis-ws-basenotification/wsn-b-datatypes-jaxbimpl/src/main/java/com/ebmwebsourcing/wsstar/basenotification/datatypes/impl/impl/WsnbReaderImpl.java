package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.WsnbConstants;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPoint;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.CreatePullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPoint;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.DestroyPullPointResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.FilterType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessage;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetCurrentMessageResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessages;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.GetMessagesResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidFilterFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidMessageContentExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidProducerPropertiesExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.InvalidTopicExpressionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MessageContentExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.MultipleTopicsSpecifiedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NoCurrentMessageOnTopicFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationMessageHolderType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotificationProducerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Notify;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.NotifyMessageNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.PauseSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ProducerPropertiesExpression;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Renew;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.RenewResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscription;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.ResumeSubscriptionResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Subscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeCreationFailedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionManagerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionPolicyType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionDialectUnknownFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicExpressionType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.TopicNotSupportedFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToCreatePullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroyPullPointFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToDestroySubscriptionFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnableToGetMessagesFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableInitialTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnacceptableTerminationTimeFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnrecognizedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.Unsubscribe;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsubscribeResponse;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UnsupportedPolicyRequestFaultType;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.UseRaw;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.WsnbReader;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.WsnbJAXBContext;

public class WsnbReaderImpl implements WsnbReader {

	private WsnbJAXBContext wsnbJaxbContext = null;
	//private Logger logger = Logger.getLogger(WsnbReaderImpl.class.getName());
		
	/**
	 * Default constructor
	 */
	protected WsnbReaderImpl() {
		this.wsnbJaxbContext = WsnbJAXBContext.getInstance();
	}
	protected WsnbReaderImpl(String[] nsAndPrefixForMarshalling) {
		this.wsnbJaxbContext = WsnbJAXBContext.getInstance(nsAndPrefixForMarshalling);
	}
	
	@Override
	public final CreatePullPoint readCreatePullPoint(Document document)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final CreatePullPointResponse readCreatePullPointResponse(Document document)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final DestroyPullPoint readDestroyPullPoint(Document document)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final DestroyPullPointResponse readDestroyPullPointResponse(
			Document document) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final FilterType readFilterType(Document document) throws WsnbException {
		FilterType result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
			
			// Save additional namespaces present on TopicExpression nodes
            List<List<String[]>> additionalTopicNSs = new ArrayList<List<String[]>>();
            
            NodeList topicExpressionNodes = document.getDocumentElement().getElementsByTagNameNS(
            		WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
            		WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());
            
            Element currentTopicExpressionAsElt = null;
            for (int i = 0; i < topicExpressionNodes.getLength(); i++) {
            	currentTopicExpressionAsElt = (Element) topicExpressionNodes.item(i);            	
            	additionalTopicNSs.add(this.getAdditionalNamespacesFromDom(currentTopicExpressionAsElt));
            }

            // ------- unmarshall ------
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.FilterType.class);
			result = new FilterTypeImpl(schemaBinding.getValue());
			
			// Trick : add previously saved namespaces to the created JaxB model
            // (will be used on the write process)         
			List<TopicExpressionType> topicExpressions = result.getTopicExpressions();
			if (topicExpressions!= null){
				for (TopicExpressionType topicExpressionItem : topicExpressions) {
					for (String[] currentNs : additionalTopicNSs.get(topicExpressions.indexOf(topicExpressionItem))) {
						topicExpressionItem.addTopicNamespace(currentNs[0], new URI(currentNs[1]));
					}
				}				
			}
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		} 		
		return result;
	}

	@Override
	public final GetCurrentMessage readGetCurrentMessage(Document document)
	throws WsnbException {
		GetCurrentMessage result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// Save additional namespaces present on Topic nodes
			List<String[]> additionalTopicNs = new ArrayList<String[]>();
			NodeList topicNodes = document.getDocumentElement().getElementsByTagNameNS(
					WsnbConstants.TOPIC_QNAME.getNamespaceURI(),
					WsnbConstants.TOPIC_QNAME.getLocalPart());

			if (topicNodes != null && topicNodes.getLength() ==1){
				Element topicNode = (Element)topicNodes.item(0);
				additionalTopicNs.addAll(this.getAdditionalNamespacesFromDom(topicNode));
			}

			// Unmarshall to Java Object
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessage.class);
			result = new GetCurrentMessageImpl(schemaBinding.getValue());

			// Trick : add previously saved namespaces to the created JaxB model
			// (will be used on the write process)     			
			if (additionalTopicNs != null){
				for (String[] currentNs : additionalTopicNs) {
					result.getTopic().addTopicNamespace(currentNs[0], new URI(currentNs[1]));
				}
			}
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		}                   		
		return result;
	}

	@Override
	public final GetCurrentMessageResponse readGetCurrentMessageResponse(
			Document document) throws WsnbException {
		GetCurrentMessageResponse result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
			// Unmarshall to Java Object
			final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.GetCurrentMessageResponse.class);
			result = new GetCurrentMessageResponseImpl(schemaBinding.getValue());

		} catch (JAXBException e) {			
			throw new WsnbException(e);
		}                   		
		return result;
	}

	@Override
	public final GetMessages readGetMessages(Document document) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final GetMessagesResponse readGetMessagesResponse(Document document)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final InvalidFilterFaultType readInvalidFilterFaultType(Document document)
			throws WsnbException {
		InvalidFilterFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidFilterFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new InvalidFilterFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final InvalidMessageContentExpressionFaultType readInvalidMessageContentExpressionFaultType(
			Document document) throws WsnbException {
		InvalidMessageContentExpressionFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidMessageContentExpressionFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new InvalidMessageContentExpressionFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final InvalidProducerPropertiesExpressionFaultType readInvalidProducerPropertiesExpressionFaultType(
			Document document) throws WsnbException {
		InvalidProducerPropertiesExpressionFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidProducerPropertiesExpressionFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new InvalidProducerPropertiesExpressionFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final InvalidTopicExpressionFaultType readInvalidTopicExpressionFaultType(
			Document document) throws WsnbException {
		InvalidTopicExpressionFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.InvalidTopicExpressionFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new InvalidTopicExpressionFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final MessageContentExpression readMessageContentExpression(Document document)
			throws WsnbException {
		MessageContentExpression result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
						
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType.class);
			result = new MessageContentExpressionImpl(schemaBinding.getValue());

		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} 		
		return result;
	}
	
	@Override
	public final MultipleTopicsSpecifiedFaultType readMultipleTopicsSpecifiedFaultType(
			Document document) throws WsnbException {
		MultipleTopicsSpecifiedFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.MultipleTopicsSpecifiedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new MultipleTopicsSpecifiedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final NoCurrentMessageOnTopicFaultType readNoCurrentMessageOnTopicFaultType(
			Document document) throws WsnbException {
		NoCurrentMessageOnTopicFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.NoCurrentMessageOnTopicFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new NoCurrentMessageOnTopicFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final NotificationMessageHolderType readNotificationMessageHolderType(
			Document document) throws WsnbException {
		NotificationMessageHolderType result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
		
			// Save additional namespaces present on Topic nodes
            List<String[]> additionalTopicNS = new ArrayList<String[]>();
            
            NodeList topicNodes = document.getDocumentElement().getElementsByTagNameNS(
            		WsnbConstants.TOPIC_QNAME.getNamespaceURI(),
            		WsnbConstants.TOPIC_QNAME.getLocalPart());
                       
            if (topicNodes != null && topicNodes.getLength() == 1) {        	
            	additionalTopicNS.addAll(this.getAdditionalNamespacesFromDom((Element)topicNodes.item(0)));
            }
            // Unmarshall to Java Object
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationMessageHolderType.class);
			result = new NotificationMessageHolderTypeImpl(schemaBinding.getValue());

			// Trick : add previously saved namespaces to the created JaxB model
            // (will be used on the write process)     
			TopicExpressionType concreteTopic  = result.getTopic();
			if (concreteTopic != null){
				for (String[] currentNs : additionalTopicNS) {
					concreteTopic.addTopicNamespace(currentNs[0], new URI(currentNs[1]));
				}
			}
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		}                   		

		return result;
	}

	@Override
	public final NotificationProducerRP readNotificationProducerRP(Document document)
			throws WsnbException {
		NotificationProducerRP result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
			
			// Save additional namespaces present on Topic nodes
			Map<Integer,List<String[]>> additionalTopicNSPerMsg = new HashMap<Integer,List<String[]>>();
            NodeList topicExprNodes = document.getDocumentElement().getElementsByTagNameNS(
                    WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
                    WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());
            
            Element currentTopicNode = null;
            for (int i = 0; i < topicExprNodes.getLength(); i++) {            	
            		currentTopicNode = (Element) topicExprNodes.item(0);
            		additionalTopicNSPerMsg.put(i,this.getAdditionalNamespacesFromDom(currentTopicNode));
            }
            
            // Unmarshall to Java Object
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.NotificationProducerRP.class);
			result = new NotificationProducerRPImpl(schemaBinding.getValue());

			// Trick : add previously saved namespaces to the created JaxB model
			// (will be used on the write process)     			
			List<TopicExpressionType> topicExpressions = result.getTopicExpressions();
			for (TopicExpressionType topExprItem : topicExpressions) {
				List<String[]> topicNSToAdd = additionalTopicNSPerMsg.get(topicExpressions.indexOf(topExprItem));
				if (topicNSToAdd != null){
					for (String[] currentNs : topicNSToAdd) {
						topExprItem.addTopicNamespace(currentNs[0], new URI(currentNs[1]));
					}
				}
			}

		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		}                   		

		return result;
	}

	@Override
	public final Notify readNotify(Document document) throws WsnbException {
		Notify result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
			
			// Save additional namespaces present on Topic nodes
			Map<Integer,List<String[]>> additionalTopicNSPerMsg = new HashMap<Integer,List<String[]>>();
            NodeList notifMsgNodes = document.getDocumentElement().getElementsByTagNameNS(
                    WsnbConstants.NOTIFICATION_MSG_QNAME.getNamespaceURI(),
                    WsnbConstants.NOTIFICATION_MSG_QNAME.getLocalPart());
            
            Element currentTopicNode = null;
            NodeList topicNodes = null;
            for (int i = 0; i < notifMsgNodes.getLength(); i++) {
            	topicNodes = ((Element) notifMsgNodes.item(i)).getElementsByTagNameNS(
            			WsnbConstants.TOPIC_QNAME.getNamespaceURI(),
            			WsnbConstants.TOPIC_QNAME.getLocalPart());
            	if (topicNodes.getLength() > 0) {
            		currentTopicNode = (Element) topicNodes.item(0);
            		additionalTopicNSPerMsg.put(i,this.getAdditionalNamespacesFromDom(currentTopicNode));
            	}
            }
            
            // Unmarshall to Java Object
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.Notify.class);
			result = new NotifyImpl(schemaBinding.getValue());

			// Trick : add previously saved namespaces to the created JaxB model
            // (will be used on the write process)     			
			List<NotificationMessageHolderType> notifMessages = result.getNotificationMessage();
			for (NotificationMessageHolderType notifMsgItem : notifMessages) {
				List<String[]> topicNSToAdd = additionalTopicNSPerMsg.get(notifMessages.indexOf(notifMsgItem));
				if (topicNSToAdd != null){
					for (String[] currentNs : topicNSToAdd) {
						notifMsgItem.getTopic().addTopicNamespace(currentNs[0], new URI(currentNs[1]));
					}
				}
			}

		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		}                   		

		return result;
	}

	@Override
	public final NotifyMessageNotSupportedFaultType readNotifyMessageNotSupportedFaultType(
			Document document) throws WsnbException {
		NotifyMessageNotSupportedFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.NotifyMessageNotSupportedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new NotifyMessageNotSupportedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final PauseFailedFaultType readPauseFailedFaultType(Document document)
			throws WsnbException {
		PauseFailedFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.PauseFailedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new PauseFailedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final PauseSubscription readPauseSubscription(Document document)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final PauseSubscriptionResponse readPauseSubscriptionResponse(
			Document document) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final ProducerPropertiesExpression readProducerPropertiesExpression(Document document)
			throws WsnbException {
		ProducerPropertiesExpression result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
						
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.QueryExpressionType.class);
			            
            result = new ProducerPropertiesExpressionImpl(schemaBinding.getValue());

		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} 
		
		return result;
	}
	

	@Override
	public final Renew readRenew(Document document) throws WsnbException {
		Renew result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
						           
            // ------- unmarshall ------
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.Renew.class);
			result = new RenewImpl(schemaBinding.getValue());
			
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		}		
		return result;
	}

	@Override
	public final RenewResponse readRenewResponse(Document document)
			throws WsnbException {
		RenewResponse result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
						           
            // ------- unmarshall ------
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.RenewResponse.class);
			result = new RenewResponseImpl(schemaBinding.getValue());
			
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		}		
		return result;
	}

	@Override
	public final ResumeFailedFaultType readResumeFailedFaultType(Document document)
			throws WsnbException {
		ResumeFailedFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.ResumeFailedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new ResumeFailedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final ResumeSubscription readResumeSubscription(Document document)
			throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final ResumeSubscriptionResponse readResumeSubscriptionResponse(
			Document document) throws WsnbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Subscribe readSubscribe(Document document) throws WsnbException {
		Subscribe result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
			
			// Save additional namespaces present on TopicExpression nodes
            List<List<String[]>> additionalTopicNSs = new ArrayList<List<String[]>>();
            
            NodeList filterNode = document.getDocumentElement().getElementsByTagNameNS(
            		WsnbConstants.FILTER_QNAME.getNamespaceURI(),
            		WsnbConstants.FILTER_QNAME.getLocalPart());
           
            if (filterNode.getLength() == 1){
            	NodeList topicExpressionNodes = ((Element)filterNode.item(0)).getElementsByTagNameNS(
            			WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
            			WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());

            	Element currentTopicExpressionAsElt = null;
            	for (int i = 0; i < topicExpressionNodes.getLength(); i++) {
            		currentTopicExpressionAsElt = (Element) topicExpressionNodes.item(i);            	
            		additionalTopicNSs.add(this.getAdditionalNamespacesFromDom(currentTopicExpressionAsElt));
            	}
            }
            
            // ------- unmarshall ------
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.Subscribe.class);
			result = new SubscribeImpl(schemaBinding.getValue());
			
			// Trick : add previously saved namespaces to the created JaxB model
            // (will be used on the write process)       
			FilterType filter = result.getFilter();
			if (filter != null) {
				List<TopicExpressionType> topicExpressions = filter.getTopicExpressions();
				if (topicExpressions!= null){
					for (TopicExpressionType topicExpressionItem : topicExpressions) {
						for (String[] currentNs : additionalTopicNSs.get(topicExpressions.indexOf(topicExpressionItem))) {
							topicExpressionItem.addTopicNamespace(currentNs[0], new URI(currentNs[1]));
						}
					}				
				}
			}
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		} 		
		return result;
	}

	@Override
	public final SubscribeCreationFailedFaultType readSubscribeCreationFailedFaultType(
			Document document) throws WsnbException {
		SubscribeCreationFailedFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeCreationFailedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new SubscribeCreationFailedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final SubscribeResponse readSubscribeResponse(Document document)
			throws WsnbException {
		SubscribeResponse result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
						           
            // ------- unmarshall ------
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscribeResponse.class);
			result = new SubscribeResponseImpl(schemaBinding.getValue());
			
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		}		
		return result;
	}

	@Override
	public final SubscriptionManagerRP readSubscriptionManagerRP(Document document)
			throws WsnbException {
		SubscriptionManagerRP result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
			
			// Save additional namespaces present on TopicExpression nodes
            List<List<String[]>> additionalTopicNSs = new ArrayList<List<String[]>>();
            
            NodeList filterNode = document.getDocumentElement().getElementsByTagNameNS(
            		WsnbConstants.FILTER_QNAME.getNamespaceURI(),
            		WsnbConstants.FILTER_QNAME.getLocalPart());
           
            if (filterNode.getLength() == 1){
            	NodeList topicExpressionNodes = ((Element)filterNode.item(0)).getElementsByTagNameNS(
            			WsnbConstants.TOPIC_EXPRESSION_QNAME.getNamespaceURI(),
            			WsnbConstants.TOPIC_EXPRESSION_QNAME.getLocalPart());

            	Element currentTopicExpressionAsElt = null;
            	for (int i = 0; i < topicExpressionNodes.getLength(); i++) {
            		currentTopicExpressionAsElt = (Element) topicExpressionNodes.item(i);            	
            		additionalTopicNSs.add(this.getAdditionalNamespacesFromDom(currentTopicExpressionAsElt));
            	}
            }
            
            // ------- unmarshall ------
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionManagerRP.class);
			result = new SubscriptionManagerRPImpl(schemaBinding.getValue());
			
			// Trick : add previously saved namespaces to the created JaxB model
            // (will be used on the write process)       
			FilterType filter = result.getFilter();
			if (filter != null) {
				List<TopicExpressionType> topicExpressions = filter.getTopicExpressions();
				if (topicExpressions!= null){
					for (TopicExpressionType topicExpressionItem : topicExpressions) {
						for (String[] currentNs : additionalTopicNSs.get(topicExpressions.indexOf(topicExpressionItem))) {
							topicExpressionItem.addTopicNamespace(currentNs[0], new URI(currentNs[1]));
						}
					}				
				}
			}
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		} 		
		return result;
	}

	@Override
	public final SubscriptionManagerRP readSubscriptionManagerRP(File file)
			throws WsnbException {
		SubscriptionManagerRP subscriptionObj=null;
		try {
			Document subscriptionAsDoc = convertFromFiletoDocument(file);
			subscriptionObj = this.readSubscriptionManagerRP(subscriptionAsDoc);
		} catch (Exception e) {
			throw new WsnbException(e);
		}
		return subscriptionObj;
	}
	
	/**
	 * convert xml File loaded as {@link File} Java object to {@link Document} Java object 
	 * 
	 * @param file {@lin File} Java object to convert
	 * @return Document Representation 
	 */
	public static Document convertFromFiletoDocument(File file) throws Exception {
		Document result = null;		
		try{
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			dbfac.setNamespaceAware(true);
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			result = docBuilder.parse(file);
		} catch (SAXException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		} catch (ParserConfigurationException e) {
			throw new Exception(e);
		}

		return result;
	}

	@Override
	public final SubscriptionPolicyType readSubscriptionPolicyType(Document document)
			throws WsnbException {
		SubscriptionPolicyType result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
					
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.SubscriptionPolicyType.class);
			result = new SubscriptionPolicyTypeImpl(schemaBinding.getValue());
			
		} catch (JAXBException e) {			
			throw new WsnbException(e);	
		}                   		

		return result;
	}

	@Override
	public final TopicExpressionType readTopicExpressionType(Document document)
			throws WsnbException {
		TopicExpressionType result = null;
		try {

			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();
		
			// Save additional namespaces present on Topic nodes
            List<String[]> additionalTopicNS = new ArrayList<String[]>();
            
            Element topicExpressionAsElt = document.getDocumentElement();
            if (topicExpressionAsElt!= null) {            	
            	additionalTopicNS.addAll(this.getAdditionalNamespacesFromDom(topicExpressionAsElt));
            }
            final JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionType.class);
			result = new TopicExpressionTypeImpl(schemaBinding.getValue());

			// Trick : add previously saved namespaces to the created JaxB model
            // (will be used on the write process)            
			for (String[] currentNs : additionalTopicNS) {
                    result.addTopicNamespace(currentNs[0], new URI(currentNs[1]));
			}
           
		} catch (JAXBException e) {			
			throw new WsnbException(e);
		} catch (URISyntaxException e) {
			throw new WsnbException(e);
		}                   		

		return result;
	}

	@Override
	public final TopicExpressionDialectUnknownFaultType readTopicExpressionDialectUnknownFaultType(
			Document document) throws WsnbException {
		TopicExpressionDialectUnknownFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicExpressionDialectUnknownFaultType.class);
			if (schemaBinding.getValue() != null) {
				result = new TopicExpressionDialectUnknownFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final TopicNotSupportedFaultType readTopicNotSupportedFaultType(
			Document document) throws WsnbException {
		TopicNotSupportedFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.TopicNotSupportedFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new TopicNotSupportedFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnableToCreatePullPointFaultType readUnableToCreatePullPointFaultType(
			Document document) throws WsnbException {
		UnableToCreatePullPointFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToCreatePullPointFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnableToCreatePullPointFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnableToDestroyPullPointFaultType readUnableToDestroyPullPointFaultType(
			Document document) throws WsnbException {
		UnableToDestroyPullPointFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroyPullPointFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnableToDestroyPullPointFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnableToDestroySubscriptionFaultType readUnableToDestroySubscriptionFaultType(
			Document document) throws WsnbException {
		UnableToDestroySubscriptionFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToDestroySubscriptionFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnableToDestroySubscriptionFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnableToGetMessagesFaultType readUnableToGetMessagesFaultType(
			Document document) throws WsnbException {
		UnableToGetMessagesFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnableToGetMessagesFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnableToGetMessagesFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnacceptableInitialTerminationTimeFaultType readUnacceptableInitialTerminationTimeFaultType(
			Document document) throws WsnbException {
		UnacceptableInitialTerminationTimeFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableInitialTerminationTimeFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnacceptableInitialTerminationTimeFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnacceptableTerminationTimeFaultType readUnacceptableTerminationTimeFaultType(
			Document document) throws WsnbException {
		UnacceptableTerminationTimeFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnacceptableTerminationTimeFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnacceptableTerminationTimeFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnrecognizedPolicyRequestFaultType readUnrecognizedPolicyRequestFaultType(
			Document document) throws WsnbException {
		UnrecognizedPolicyRequestFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnrecognizedPolicyRequestFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnrecognizedPolicyRequestFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final Unsubscribe readUnsubscribe(Document document) throws WsnbException {
		Unsubscribe result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.Unsubscribe.class);
			if (schemaBinding.getValue() != null){
				result = new UnsubscribeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnsubscribeResponse readUnsubscribeResponse(Document document)
			throws WsnbException {
		UnsubscribeResponse result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsubscribeResponse.class);
			if (schemaBinding.getValue() != null){
				result = new UnsubscribeResponseImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UnsupportedPolicyRequestFaultType readUnsupportedPolicyRequestFaultType(
			Document document) throws WsnbException {
		UnsupportedPolicyRequestFaultType result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UnsupportedPolicyRequestFaultType.class);
			if (schemaBinding.getValue() != null){
				result = new UnsupportedPolicyRequestFaultTypeImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}

	@Override
	public final UseRaw readUseRaw(Document document) throws WsnbException {
		UseRaw result = null ;
		try {
			Unmarshaller unmarshaller = this.wsnbJaxbContext.createWSNotificationUnmarshaller();

			// TODO : Check if it is a Thread safe method
			JAXBElement<com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw> schemaBinding = 
				unmarshaller.unmarshal(new DOMSource(document),com.ebmwebsourcing.wsstar.jaxb.notification.base.UseRaw.class);
			if (schemaBinding.getValue() != null){
				result = new UseRawImpl(schemaBinding.getValue());
			}
		} catch (JAXBException e) {
			throw new WsnbException(e);
		} 	
		return result;
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~~~~~~~~~ Technical methods section ~~~~~~~~~~~~~~~~~~~~~
	
	
	/**
	 * Private method used to extract AdditionalNamespaces
	 * 
	 * @param topicExpressionNode
	 * @return
	 */
	private List<String[]> getAdditionalNamespacesFromDom(org.w3c.dom.Element topicExpressionNode) {
		List<String[]> additionalNamespaces = new ArrayList<String[]>();
		NamedNodeMap nnm = topicExpressionNode.getAttributes();
		for (int i = 0; i < nnm.getLength(); ++i) {
			Node attribute = nnm.item(i);
			if (XMLConstants.XMLNS_ATTRIBUTE.equals(attribute.getPrefix()) || XMLConstants.XML_NS_PREFIX.equals(attribute.getPrefix())) {
				// It's an namespace declaration attribute
				additionalNamespaces.add(new String[] { attribute.getLocalName(),
						attribute.getNodeValue() });
			}
		}
		return additionalNamespaces;
	}
}
