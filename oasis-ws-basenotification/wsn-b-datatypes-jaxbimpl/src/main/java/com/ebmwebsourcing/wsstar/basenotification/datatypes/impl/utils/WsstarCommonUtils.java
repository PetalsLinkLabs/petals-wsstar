/**
 * Copyright (c) 2010 EBM Websourcing, http://www.ebmwebsourcing.com/
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


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class WsstarCommonUtils {
	
	
	private WsstarCommonUtils() {}
	
	private static Logger logger = Logger.getLogger(WsstarCommonUtils.class.getSimpleName());
	
	private static String transformerFactoryClass = "";

	
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
    

        
    /**
     * parse the xml String and return it pretty-printed (with correct
     * indentations, etc..)
     * 
     * @param xmlDocument
     *            the xml document to pretty print. Must be non null
     * @param encoding
     *            the encoding to use
     *   
     * @returnpretty printed string if no error occurs. If an error occurs,
     *               return an empty String
     */
    public static String prettyPrint(final Document xmlDocument,String encoding) {
        String result = "";
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            WsstarCommonUtils.prettify(xmlDocument, outStream,encoding);
            result = outStream.toString(encoding);
        } catch (final Exception e) {
        	WsstarCommonUtils.logger.log(Level.SEVERE, "write_dom failed:" + e);
            //System.err.println("write_dom failed:" + e);
            // if an error occurs, the result will be the original string
        }
        return result;

    }

    /**
     * parse the xml Document and return it pretty-printed (with correct
     * indentations, etc..).
     * Use the encoding defined at the parsing or in the document 
     * (utf8 is used if no encoding is defined)
     * @param xmlDocument
     *            the xml document to pretty print. Must be non null
     * @returnpretty printed string if no error occurs. If an error occurs,
     *               return an empty String
     */
    public static String prettyPrint(final Document xmlDocument) {
        
        return prettyPrint(xmlDocument,getEncoding(xmlDocument));
    }

    
    /**
     * Prettify the node into the output stream.
     *
     * @param node
     * @param out
     * @throws TransformerException 
     * @throws Exception
     */
    public static void prettify(Node node, OutputStream out,String encoding) throws TransformerException {
        Source source = new DOMSource(node);
        Source stylesheetSource = getStyleSheetSource();

        TransformerFactory tf = TRANSFORMER_FACTORY_THREAD_LOCAL.get();
        Templates templates = tf.newTemplates(stylesheetSource);
        Transformer transformer = templates.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,encoding);
        transformer.transform(source, new StreamResult(out));
    }

    /**
     * Return the encoding of the document.
     * @param xmlDocument
     * @return InputEncoding or the XmlEncoding of the document, UTF-8 if not found
     */
    public static String getEncoding(Document xmlDocument){
//        String encoding = xmlDocument.getInputEncoding();
//        if(encoding == null){
//                encoding = xmlDocument.getXmlEncoding();
//        }
//        if(encoding == null){
     String           encoding = "UTF-8";
        //}
        return encoding;
    }
    
    private static Source getStyleSheetSource() {
        return new StreamSource(WsstarCommonUtils.class.getResourceAsStream("/util/prettyPrint.xsl"));
    }

    /**
     * <p>
     * A thread safe TransformerFactory, based on 
     * com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl
     * </p>
     */
    private static final ThreadLocal<TransformerFactory> TRANSFORMER_FACTORY_THREAD_LOCAL = 
    	new ThreadLocal<TransformerFactory>() {

            @Override
            protected TransformerFactory initialValue() {
                    try {
                            
                            if (System.getProperty("java.vendor").indexOf("Sun") != -1){
                                    transformerFactoryClass = "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl";
                            }else if (System.getProperty("java.vendor").indexOf("IBM") != -1){
                                    transformerFactoryClass = "org.apache.xalan.processor.TransformerFactoryImpl";
                            }
                            
                            Thread.currentThread().getContextClassLoader().loadClass(transformerFactoryClass);
                            System.setProperty("javax.xml.transform.TransformerFactory", transformerFactoryClass);
                    } catch (ClassNotFoundException e) {
                    	 WsstarCommonUtils.logger.log(Level.SEVERE,
                    			 "Warning. TransformerFactory '"+transformerFactoryClass+"' not found, the standard TransformerFactory will be used.");
                           // System.err.println("Warning. TransformerFactory '"+transformerFactoryClass+"' not found, the standard TransformerFactory will be used.");
                    }
                    return TransformerFactory.newInstance();
            }
    };
    
    /**
     * <p>
     * {@link Transformer} as thread local.
     * </p>
     * <p>
     * The Transformer is the default one provided by the classpath (no property
     * set).
     * </p>
     */
    private static final ThreadLocal<Transformer> DEFAULT_TRANSFORMER_THREAD_LOCAL = 
    	new ThreadLocal<Transformer>() {

        @Override
        protected Transformer initialValue() {
            try {

                return TransformerFactory.newInstance().newTransformer();

            } catch (TransformerConfigurationException e) {
                throw new RuntimeException("Failed to create Transformer", e);
            }
        }
    };
    
    public static Transformer getDefaultTransformerFactoryThreadLocal() {
    	return DEFAULT_TRANSFORMER_THREAD_LOCAL.get();
    }
    
//    /**
//     * <p>
//     * Document builder factories.
//     * </p>
//     * <p>
//     * To avoid several instance of the same DocumentBuilder (with same options) in
//     * the same Thread, each DocumentBulder must be created using these factories.
//     * </p>
//     * 
//     * @author Christophe DENEUX (Capgemini Sud)
//     * 
//     */

    /**
     * <p>
     * DocumentBuilder as thread local.
     * </p>
     * <p>
     * The document builder is the one provided by the JVM, even if an other DOM
     * implementation is in the classpath (ex: Xerces). The JVM implementation
     * is needed because the implementation of the DocumentFragment must be
     * deserializable on other Petals node, that have not a specific
     * implementation in its classpath.
     * </p>
     */
    private static final ThreadLocal<DocumentBuilder> JVM_DOCUMENT_BUILDER_THREAD_LOCAL = 
    	new ThreadLocal<DocumentBuilder>() {

    	@Override
    	protected DocumentBuilder initialValue() {
    		final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
    		try {
    			final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
    			Thread.currentThread().setContextClassLoader(systemClassLoader);

    			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
    			.newInstance();
    			documentBuilderFactory.setNamespaceAware(true);

    			return documentBuilderFactory.newDocumentBuilder();

    		} catch (ParserConfigurationException e) {
    			throw new RuntimeException("Failed to create DocumentBuilder",e);
    		} finally {
    			Thread.currentThread().setContextClassLoader(currentClassLoader);
    		}
    	}
    };

    /**
     * <p>
     * DocumentBuilder as thread local, supporting XML namespaces.
     * </p>
     * <p>
     * The document builder is the one provided by the classpath supporting
     * namespaces.
     * </p>
     */
    private static final ThreadLocal<DocumentBuilder> NAMESPACE_DOCUMENT_BUILDER_THREAD_LOCAL = 
    	new ThreadLocal<DocumentBuilder>() {

    	@Override
    	protected DocumentBuilder initialValue() {
    		try {
    			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
    			.newInstance();
    			documentBuilderFactory.setNamespaceAware(true);

    			return documentBuilderFactory.newDocumentBuilder();

    		} catch (ParserConfigurationException e) {
    			throw new RuntimeException("Failed to create DocumentBuilder", e);
    		}
    	}
    };

    /**
     * <p>
     * DocumentBuilder as thread local, supporting XML namespaces.
     * </p>
     * <p>
     * The document builder is the default (no property set) one provided by the
     * classpath.
     * </p>
     */
    private static final ThreadLocal<DocumentBuilder> DEFAULT_DOCUMENT_BUILDER_THREAD_LOCAL = 
    	new ThreadLocal<DocumentBuilder>() {

    	@Override
    	protected DocumentBuilder initialValue() {
    		try {
    			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
    			.newInstance();

    			return documentBuilderFactory.newDocumentBuilder();

    		} catch (ParserConfigurationException e) {
    			throw new RuntimeException("Failed to create DocumentBuilder", e);
    		}
    	}
    };

    /**
     * <p>
     * DocumentBuilder as thread local.
     * </p>
     * <p>
     * The document builder is the one provided by
     * {@link #JVM_DOCUMENT_BUILDER_THREAD_LOCAL}.
     * </p>
     */
    public static DocumentBuilder getJvmDocumentBuilder() {
    	return JVM_DOCUMENT_BUILDER_THREAD_LOCAL.get();
    }

    /**
     * <p>
     * DocumentBuilder as thread local, supporting XML namespaces.
     * </p>
     * <p>
     * The document builder is the one provided by
     * {@link #NAMESPACE_DOCUMENT_BUILDER_THREAD_LOCAL}.
     * </p>
     */
    public static DocumentBuilder getNamespaceDocumentBuilder() {
    	return NAMESPACE_DOCUMENT_BUILDER_THREAD_LOCAL.get();
    }

    /**
     * <p>
     * DocumentBuilder as thread local, supporting XML namespaces.
     * </p>
     * <p>
     * The document builder is the one provided by
     * {@link #DEFAULT_DOCUMENT_BUILDER_THREAD_LOCAL}.
     * </p>
     */
    public static DocumentBuilder getDefaultDocumentBuilder() {
    	return DEFAULT_DOCUMENT_BUILDER_THREAD_LOCAL.get();
    }

}
