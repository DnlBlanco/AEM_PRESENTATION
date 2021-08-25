package com.adobe.aem.sample.core.servlets;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.sample.core.service.HttpService;

/**
 * 
 *This method makes an HTTP call and read the value from the JSON Web service via an OSGi config
 *
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=HTTP servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/golfscores" })
public class HttpServlet extends SlingSafeMethodsServlet {
	
	/**
	 * Generated serialVersionUid
	 */
	private static final long serialVersionUID = -2014397651676211439L;
	
	private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);
	
	@Reference
	private HttpService httpService;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		
		try {
		
		String jsonResponse = httpService.makeHttpCall(request);
		//log.info(jsonResponse);
		httpService.addPlayerStats(request, jsonResponse);
	
		/**
		 * Printing the json response on the browser
		 */
		response.getWriter().println(jsonResponse);
		
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
		}
	}

}
