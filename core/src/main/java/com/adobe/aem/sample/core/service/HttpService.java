package com.adobe.aem.sample.core.service;

import java.io.IOException;

import org.apache.sling.api.SlingHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 *
 *This interface exposes the functionality of calling a JSON Web Service
 *
 */
public interface HttpService {

	/**
	 * This method makes the HTTP cal on the given URL
	 */
	public String makeHttpCall(SlingHttpServletRequest req);
	
	public void addPlayerStats(SlingHttpServletRequest req, String jsonResponse) throws JsonParseException, JsonMappingException, IOException;
}
