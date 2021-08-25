package com.adobe.aem.sample.core.service;

import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;

/**
 * Service Class for the Review Component
 * @author Daniel
 *
 */

public interface ReviewService {
	//Constants
	final String JCR_NODE_PATH = "/apps/sample/golfequipment/sample/jcr:content";
	final String CONTENT_NODE_PATH = "/content/sample/us/en/products/jcr:content/root/responsivegrid/review_component";
	
	//Methods
	public void nextItem(SlingHttpServletRequest req) throws RepositoryException;
}
