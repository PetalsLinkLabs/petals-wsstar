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
 * $Id$
 * -------------------------------------------------------------------------
 */

package com.ebmwebsourcing.wsstar.wsnb.services.impl.persistence;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.abstraction.SubscriptionManagerRP;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.refinedabstraction.RefinedWsnbFactory;
import com.ebmwebsourcing.wsstar.basenotification.datatypes.api.utils.WsnbException;
/**
 * The persistence manager used to persist consumer subscription  so
 * that they can be reloaded when needed.
 * 
 * @author Thierry Déjean - eBM WebSourcing
 */
public class WsnbPersistence {

    /**
     * The persistence folders path
     */
    private File subscriptions_folder = null;
   
    /**
     * 
     * @param subscriptions_path
     * @param registrations_path
     */
    public WsnbPersistence(File subscriptions_path) {

    	if (subscriptions_path == null) {
    		throw new NullPointerException("Persistence folder must not be null");
    	}
    	
    	if (!subscriptions_path.exists()) {
    		subscriptions_path.mkdirs();
    	}
    	this.subscriptions_folder = subscriptions_path;    	    	
    	
    }

    /**
     * 
     * @param subscription
     * @throws Exception
     */
    public void persist(SubscriptionManagerRP subscriptionRP, String uuid) throws WsnbException {
    	
//    		File persist = new File(this.subscriptions_folder.getAbsoluteFile(),uuid);
//    		if (persist == null) {
//    			throw new WsnbException("The persistance file can not be created");
//    		}
//    		if (!persist.exists()) {
//    			try {
//    				persist.createNewFile();
//    			} catch (IOException e) {
//    				throw new WsnbException(e);
//    			}
//    		}        
    		RefinedWsnbFactory.getInstance().getWsnbWriter().writeSubscriptionManagerRPToFilesystem(subscriptionRP,
    				this.subscriptions_folder.getAbsoluteFile() +File.separator + uuid);    	
    }
    
    
    /**
     * 
     * @return
     * @throws WSNotificationException 
     */
    public Map<String,SubscriptionManagerRP> getSubscriptionsToRestore() throws WsnbException{

    	Map<String, SubscriptionManagerRP> result = new ConcurrentHashMap<String, SubscriptionManagerRP>();
    	if (this.subscriptions_folder.exists()){
    		File[] persistedSubsriptionFiles = this.subscriptions_folder.listFiles();
    		for (int i = 0; i < persistedSubsriptionFiles.length; i++) {
    			result.put(persistedSubsriptionFiles[i].getName(),
    					RefinedWsnbFactory.getInstance().getWsnbReader().readSubscriptionManagerRP(persistedSubsriptionFiles[i]));
    		}
    	}	    	
    	return result;
    }
     
    /**
     * 
     * @param uuid
     * @throws WSNotificationException
     */
    public void removeSubscription(String uuid) throws WsnbException{
    		File subscriptionToRemove = new File(this.subscriptions_folder,uuid);
    		if (subscriptionToRemove.exists())
    			subscriptionToRemove.delete();
    			//	throw new WsnbException("Deletion of subscription \"" + uuid + "\" failed !");
    	
    }
    
}
