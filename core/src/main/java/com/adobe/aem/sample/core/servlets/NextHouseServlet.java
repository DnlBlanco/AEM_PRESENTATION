package com.adobe.aem.sample.core.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.sample.core.service.impl.HouseComponentServiceImpl;

@Component(service = { Servlet.class }, property = { "sling.servlet.paths=/bin/nextHouseServlet",
		"sling.servlet.methods=GET" })
public class NextHouseServlet extends SlingAllMethodsServlet {
	
	@Reference							//Identifies the annotated member as a reference of a service component.
	HouseComponentServiceImpl service;
	
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException  {
		
		try {
			service.nextHouse(request);	//Calls the service class nextHouse() method and passes the request object.
		} catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
	}

}
