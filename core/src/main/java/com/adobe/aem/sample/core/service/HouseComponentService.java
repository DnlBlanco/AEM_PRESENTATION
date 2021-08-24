package com.adobe.aem.sample.core.service;



import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;

//Interface for the HouseComponentServiceImpl.
public interface HouseComponentService {
	//Constants
	final String JCR_NODE_PATH = "/apps/sample/houses/sample/jcr:content";
	final String CONTENT_NODE_PATH = "/content/sample/us/en/presentation-page/jcr:content/root/responsivegrid_26921014/housecomponent";
	
	//Methods
	public void nextHouse(SlingHttpServletRequest req) throws RepositoryException;
}
