/**
 * Copyright (c) 2009 EBM Websourcing, http://www.ebmwebsourcing.com/
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * -------------------------------------------------------------------------
 * $id.java
 * -------------------------------------------------------------------------
 */
package com.ebmwebsourcing.wsstar.basefaults.datatypes.impl;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ebmwebsourcing.wsstar.basefaults.datatypes.impl.utils.WsrfbfJAXBPrefixMapper;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


/**
 * This class is a "link" between JAXB generated classes and its upper API.
 * It provides the JAXB context object used to get Unmarshaller/Marshaller
 * Object. See EASYWSDL Library for more details since it follow the same
 * JAXB usage model.  
 *    
 * @author Thierry Déjean - eBM WebSourcing
 */
public final class WsrfbfJAXBContext {
	
	private static Logger log = Logger.getLogger(WsrfbfJAXBContext.class.getName());

    /**
     * The JAXB context
     */
    private JAXBContext jaxbContext;
    
    private String[] additionalsNsAndPrefixesMappings = null;

    /**
     * JAXB's ObjectFactories
     */
    public static final com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.ObjectFactory WSRFBF_JAXB_FACTORY = 
    	new com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.ObjectFactory();
        
    /**
     * Default constructor. Private object initializations
     */
    private WsrfbfJAXBContext() {
                 
        try {
             this.jaxbContext = JAXBContext
             	.newInstance(new Class[] {    		
             			com.ebmwebsourcing.wsstar.jaxb.resource.basefaults.ObjectFactory.class});                		               		                		
        } catch (final JAXBException e) {
        	log.log(Level.WARNING, "WsrfbfJAXBContext initialisation failed !", e);
        }
    }
   
    /**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	 * or the first access to SingletonHolder.INSTANCE, not before.
	 */
    private static final class WsrfbfJAXBContextHolder {     	
		private  static final WsrfbfJAXBContext INSTANCE = new WsrfbfJAXBContext();
		
		private WsrfbfJAXBContextHolder(){}
	}
    
    /**
	 * Return an unique - respect to the Singleton design pattern - instance  
	 * of the {@link WsrfbfJAXBContext} class.  
	 * 
	 * @return a unique {@link WsrfbfJAXBContext} instance. 
	 */
	public static WsrfbfJAXBContext getInstance() {
		return WsrfbfJAXBContextHolder.INSTANCE;
	}
	
	/**
	 * Return an unique - respect to the Singleton design pattern - instance  
	 * of the {@link WsrfbfJAXBContext} class.  
	 * 
	 * @return a unique {@link WsrfbfJAXBContext} instance. 
	 */
	public static WsrfbfJAXBContext getInstance(String[] nsAndPrefixForMarshalling) {
		WsrfbfJAXBContext context = WsrfbfJAXBContextHolder.INSTANCE;
		context.addNsAndPrefixMapping(nsAndPrefixForMarshalling);
		return context;
	}
    /**
     * jaxbContext attribute getter
	 *
     * @return the jaxbContext -instance of {@link JAXBContext}- attribute value
     */
    public JAXBContext getJaxbContext() {
        return this.jaxbContext;        
    }
    
    /**
     * add custom Prefix/Namesapce mapping Instead of default one which
     * used the default prefix notation : "ns[an_integer]"   
     * 
     * @param nsAndPref
     */
    public void addNsAndPrefixMapping(String[] nsAndPref) {
    	this.additionalsNsAndPrefixesMappings = nsAndPref.clone();
    }
    
    /**
     * Create an instance of {@link Marshaller {@code}} from the
     * current {@link JAXBContext} object 
     * 
     * @return a {@link Marshaller} object
     * @throws JAXBException
     */
    public Marshaller createWSBaseFaultsMarshaller() throws JAXBException{
    	NamespacePrefixMapper wsnMapper = new WsrfbfJAXBPrefixMapper();
    	
    	if (this.additionalsNsAndPrefixesMappings != null){ 
    		((WsrfbfJAXBPrefixMapper)wsnMapper).addContextualNamespaceDecls(this.additionalsNsAndPrefixesMappings);
    	}
    	Marshaller marshaller = this.jaxbContext.createMarshaller();
    	marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", wsnMapper);    	
    	return marshaller;
    }
  
    /**
     * Create an instance of {@link Unmarshaller {@code}} from the
     * current {@link JAXBContext} object 
     * 
     * @return a a {@link Unmarshaller} object
     * @throws JAXBException
     */
    public Unmarshaller createWSBaseFaultsUnmarshaller() throws JAXBException{    	 
    	return this.jaxbContext.createUnmarshaller();
    }
}
