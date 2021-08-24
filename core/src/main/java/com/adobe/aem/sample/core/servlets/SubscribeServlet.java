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

import com.adobe.aem.sample.core.service.SubscriptionService;

@Component(service = { Servlet.class }, property = { "sling.servlet.paths=/bin/subscribeServlet",
"sling.servlet.methods=GET" })
public class SubscribeServlet extends SlingAllMethodsServlet {

	@Reference
	SubscriptionService service;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			service.addSubscriber(request);	//Calls the service class addSubscriber() method and passes the request object.
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
