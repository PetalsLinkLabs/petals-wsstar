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
package com.ebmwebsourcing.wsstar.basenotification.datatypes.impl.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * This class provides a custom {Prefix/Namespace} mapping
 * which is substituted to the default one.   
 * 
 * @author Thierry DEJEAN - eBM Websourcing
 */
public class WsnbJAXBPrefixMapper extends NamespacePrefixMapper {

	/**
	 * Fixed custom mapping
	 *
	 *  "ebm-ids","http://org.ow2.petals/ebmwebsourcing/specific/resourceIds",
	 *	"ebm-top","http://org.ow2.petals/ebmwebsourcing/specific/topicExtension",
	 *	"jbi","http://www.ebmwebsourcing.com/mapping/WSNotifEndpointReference",
	 *
	 */
	private final String defaultsNs[] = {"xs","http://www.w3.org/2001/XMLSchema",
			"wsa","http://www.w3.org/2005/08/addressing",			
			"xsi","http://www.w3.org/2001/XMLSchema-instance",
			"wsrf-bf","http://docs.oasis-open.org/wsrf/bf-2",
			"wstop","http://docs.oasis-open.org/wsn/t-1",
			"wsnt","http://docs.oasis-open.org/wsn/b-2",
			"wsn-br","http://docs.oasis-open.org/wsn/br-2",
			"wsrf-r","http://docs.oasis-open.org/wsrf/r-2",
			"wsrf-rp","http://docs.oasis-open.org/wsrf/rp-2",
			"wsrf-rl","http://docs.oasis-open.org/wsrf/rl-2",
			};
	
	private String knownNs[] = {};
	
	private Map<String, String> predefinedNamespaces = new HashMap<String, String>();
	
	public WsnbJAXBPrefixMapper() {	
		this.initKnownNsMapping();	
	}
	
	public WsnbJAXBPrefixMapper(String[] customNamespaces) {	
		this.initKnownNsMapping();			
		for (int i = 0; i < customNamespaces.length; i++) {

			String prefix = customNamespaces[i++];
			String namespace = customNamespaces[i];

			this.predefinedNamespaces.put(namespace, prefix);
		}
	}

	private void initKnownNsMapping(){
		this.knownNs = this.defaultsNs;
	}
		
	@Override
	public final String getPreferredPrefix(String namespaceUri, String suggestion,
			boolean requirePrefix) {
		
		if (this.predefinedNamespaces.containsKey(namespaceUri)){
			return this.predefinedNamespaces.get(namespaceUri);
		}
		/*
		if (namespaceUri.equals("http://schemas.xmlsoap.org/wsdl/")) {
			return "w";
		} else if (namespaceUri
				.equals("http://schemas.xmlsoap.org/wsdl/soap12/")) {
			return "soap12";
		} else if (namespaceUri.equals("http://schemas.xmlsoap.org/wsdl/soap/")) {
			return "soap";
		} else if (namespaceUri.equals("http://schemas.xmlsoap.org/wsdl/http/")) {
			return "http";
		} else if (namespaceUri.equals("http://schemas.xmlsoap.org/wsdl/mime/")) {
			return "mime";
		} else if (namespaceUri.equals("http://www.w3.org/2001/XMLSchema")) {
			return "xs";
		} else if (this.predefinedNamespaces.containsKey(namespaceUri)) {
			return this.predefinedNamespaces.get(namespaceUri);
		}
		*/
		return suggestion;
	}
	
	@Override
	public final String[] getPreDeclaredNamespaceUris() {
		String[] custNS = new String[this.predefinedNamespaces.size() * 2];

		Iterator<String> it = this.predefinedNamespaces.keySet().iterator();

		int i = 0;
		while (it.hasNext()) {
			String ns = it.next();
			String prefix = this.predefinedNamespaces.get(ns);

			custNS[i++] = prefix;
			custNS[i++] = ns;
		}
		return custNS;
	}
	
	@Override
	public final String[] getContextualNamespaceDecls() {
		return this.defaultsNs;
	}
	
	public final void addContextualNamespaceDecls(String[] nss) {
		int length = nss.length;		
		String[] newDefaultsNs = new String[length+this.knownNs.length];		

		for (int i=0;i<newDefaultsNs.length;i++){
			if (i< this.knownNs.length) {
				newDefaultsNs[i] = this.knownNs[i];
			} else { 
				newDefaultsNs[i] = nss[i-this.knownNs.length];
			}
		}
		
	}
}
