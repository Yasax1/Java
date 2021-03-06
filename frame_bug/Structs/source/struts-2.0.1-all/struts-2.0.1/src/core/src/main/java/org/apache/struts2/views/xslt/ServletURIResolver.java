/*
 * $Id: ServletURIResolver.java 439747 2006-09-03 09:22:46Z mrdon $
 *
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts2.views.xslt;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ServletURIResolver is a URIResolver that can retrieve resources from the servlet context using the scheme "response".
 * e.g.
 *
 * A URI resolver is called when a stylesheet uses an xsl:include, xsl:import, or document() function to find the
 * resource (file).
 */
public class ServletURIResolver implements URIResolver {

    private Log log = LogFactory.getLog(getClass());
    static final String PROTOCOL = "response:";

    private ServletContext sc;

    public ServletURIResolver(ServletContext sc) {
        log.trace("ServletURIResolver: " + sc);
        this.sc = sc;
    }

    public Source resolve(String href, String base) throws TransformerException {
        log.debug("ServletURIResolver resolve(): href=" + href + ", base=" + base);
        if (href.startsWith(PROTOCOL)) {
            String res = href.substring(PROTOCOL.length());
            log.debug("Resolving resource <" + res + ">");

            InputStream is = sc.getResourceAsStream(res);

            if (is == null) {
                throw new TransformerException(
                        "Resource " + res + " not found in resources.");
            }

            return new StreamSource(is);
        }

        throw new TransformerException(
                "Cannot handle procotol of resource " + href);
    }
}
