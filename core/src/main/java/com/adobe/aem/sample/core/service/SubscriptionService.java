package com.adobe.aem.sample.core.service;

import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;

public interface SubscriptionService {

	//Constants
	final String JCR_NODE_PATH = "/apps/sample/subscribers/sample/jcr:content/items";
	
	//Methods
	public void addSubscriber(SlingHttpServletRequest request) throws RepositoryException;
}
