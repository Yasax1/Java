/*
 * $Id: MultiPartRequestWrapper.java 474787 2006-11-14 13:51:15Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.dispatcher.multipart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;


/**
 * Parse a multipart request and provide a wrapper around the request. The parsing implementation used
 * depends on the <tt>struts.multipart.parser</tt> setting. It should be set to a class which
 * extends {@link org.apache.struts2.dispatcher.multipart.MultiPartRequest}. 
 * <p/>
 * The <tt>struts.multipart.parser</tt> property should be set to <tt>jakarta</tt> for the
 * Jakarta implementation, <tt>pell</tt> for the Pell implementation and <tt>cos</tt> for the Jason Hunter
 * implementation.
 * <p/>
 * The files are uploaded when the object is instantiated. If there are any errors they are logged using
 * {@link #addError(String)}. An action handling a multipart form should first check {@link #hasErrors()}
 * before doing any other processing.
 * <p/>
 * An alternate implementation, PellMultiPartRequest, is provided as a plugin.
 *
 */
public class MultiPartRequestWrapper extends StrutsRequestWrapper {
    protected static final Log log = LogFactory.getLog(MultiPartRequestWrapper.class);

    Collection<String> errors;
    MultiPartRequest multi;

    /**
     * Process file downloads and log any errors.
     *
     * @param request Our HttpServletRequest object
     * @param saveDir Target directory for any files that we save
     * @param multiPartRequest Our MultiPartRequest object
     */
    public MultiPartRequestWrapper(MultiPartRequest multiPartRequest, HttpServletRequest request, String saveDir) {
        super(request);
        
        multi = multiPartRequest;
        try {
            multi.parse(request, saveDir);
            for (Object o : multi.getErrors()) {
                String error = (String) o;
                addError(error);
            }
        } catch (IOException e) {
            addError("Cannot parse request: "+e.toString());
        } 
    }

    /**
     * Get an enumeration of the parameter names for uploaded files
     *
     * @return enumeration of parameter names for uploaded files
     */
    public Enumeration<String> getFileParameterNames() {
        if (multi == null) {
            return null;
        }

        return multi.getFileParameterNames();
    }

    /**
     * Get an array of content encoding for the specified input field name or <tt>null</tt> if
     * no content type was specified.
     *
     * @param name input field name
     * @return an array of content encoding for the specified input field name
     */
    public String[] getContentTypes(String name) {
        if (multi == null) {
            return null;
        }

        return multi.getContentType(name);
    }

    /**
     * Get a {@link java.io.File[]} for the given input field name.
     *
     * @param fieldName input field name
     * @return a File[] object for files associated with the specified input field name
     */
    public File[] getFiles(String fieldName) {
        if (multi == null) {
            return null;
        }

        return multi.getFile(fieldName);
    }

    /**
     * Get a String array of the file names for uploaded files
     *
     * @param fieldName Field to check for file names.
     * @return a String[] of file names for uploaded files
     */
    public String[] getFileNames(String fieldName) {
        if (multi == null) {
            return null;
        }

        return multi.getFileNames(fieldName);
    }

    /**
     * Get the filename(s) of the file(s) uploaded for the given input field name.
     * Returns <tt>null</tt> if the file is not found.
     *
     * @param fieldName input field name
     * @return the filename(s) of the file(s) uploaded for the given input field name or
     *         <tt>null</tt> if name not found.
     */
    public String[] getFileSystemNames(String fieldName) {
        if (multi == null) {
            return null;
        }

        return multi.getFilesystemName(fieldName);
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getParameter(String)
     */
    public String getParameter(String name) {
        return ((multi == null) || (multi.getParameter(name) == null)) ? super.getParameter(name) : multi.getParameter(name);
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getParameterMap()
     */
    public Map getParameterMap() {
        Map<String, String[]> map = new HashMap<String, String[]>();
        Enumeration enumeration = getParameterNames();

        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            map.put(name, this.getParameterValues(name));
        }

        return map;
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getParameterNames()
     */
    public Enumeration getParameterNames() {
        if (multi == null) {
            return super.getParameterNames();
        } else {
            return mergeParams(multi.getParameterNames(), super.getParameterNames());
        }
    }

    /**
     * @see javax.servlet.http.HttpServletRequest#getParameterValues(String)
     */
    public String[] getParameterValues(String name) {
        return ((multi == null) || (multi.getParameterValues(name) == null)) ? super.getParameterValues(name) : multi.getParameterValues(name);
    }

    /**
     * Returns <tt>true</tt> if any errors occured when parsing the HTTP multipart request, <tt>false</tt> otherwise.
     *
     * @return <tt>true</tt> if any errors occured when parsing the HTTP multipart request, <tt>false</tt> otherwise.
     */
    public boolean hasErrors() {
        return !((errors == null) || errors.isEmpty());
    }

    /**
     * Returns a collection of any errors generated when parsing the multipart request.
     *
     * @return the error Collection.
     */
    public Collection<String> getErrors() {
        return errors;
    }

    /**
     * Adds an error message.
     *
     * @param anErrorMessage the error message to report.
     */
    protected void addError(String anErrorMessage) {
        if (errors == null) {
            errors = new ArrayList<String>();
        }

        errors.add(anErrorMessage);
    }

    /**
     * Merges 2 enumeration of parameters as one.
     *
     * @param params1 the first enumeration.
     * @param params2 the second enumeration.
     * @return a single Enumeration of all elements from both Enumerations.
     */
    protected Enumeration mergeParams(Enumeration params1, Enumeration params2) {
        Vector temp = new Vector();

        while (params1.hasMoreElements()) {
            temp.add(params1.nextElement());
        }

        while (params2.hasMoreElements()) {
            temp.add(params2.nextElement());
        }

        return temp.elements();
    }
}
