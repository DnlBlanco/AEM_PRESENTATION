package com.adobe.aem.sample.core.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.sample.core.service.ReviewService;

@Component(service = { Servlet.class }, property = { "sling.servlet.paths=/bin/nextItemServlet",
"sling.servlet.methods=GET" })

public class nextItemServlet extends SlingSafeMethodsServlet{
	
	@Reference
	ReviewService service;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			service.nextItem(request);	//Calls the service class nextItem() method and passes the request object.
		} catch (RepositoryException e) {
			System.out.println(e.getMessage());
		}
	}

}
