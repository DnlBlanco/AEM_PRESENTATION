package com.adobe.aem.sample.core.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.sample.core.schedulers.Blade_SchedulerJob;
import com.adobe.aem.sample.core.service.Serve_Background;
import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

/**
 * 
 * @author kathyhseol
 *	Servlets are a form of a SERVICE COMPONENT
 *	I will need a Service interface to point to the service
 *
 *	it is bad practice to use sling.servlet.paths right now but this will be later
 * changed to be using the resource Type
 * 
 * idea is to get the scheduler 'job' when the scheduler job has occured.
 * currently testing the short-term intervals (10 seconds and a minute) to get see the results.
 */
@Component(service = {Servlet.class},property = { "sling.servlet.paths=/bin/backgroundServlet",
"sling.servlet.methods=GET" })
public class Background_Servlet extends  SlingSafeMethodsServlet {
	
	@Reference 
	Serve_Background getCurrentPath;
	
	Blade_SchedulerJob interval = new Blade_SchedulerJob();
	
	
	
	/**
	 * the get method below will later be edited so that the image changing are controlled through the back-end, rather than the front end
	 * as the only way to break the code in the front end if if the current index is not set to any integer below 0 or higher than the length-1.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
		//after running the bundle, it was a sucess.
		String check = request.getParameter("check"); //this is returning null for some reason...
		String[] listOfImages = null;
		int currentIndex = 0;
		if(check.equals("switch")) {
			//this line causes problem as this cannot be 'casted'
			//this is returning null because the split is not coming though for one item.
			listOfImages = request.getParameterValues("images");
			//currentIndex = Integer.parseInt(request.getParameter("currentIndex"));
			//boolean test = true;
			if(interval.rotate() == true) { //i need it some that the call for this method is not going to send to an infinite loop....
				currentIndex = getCurrentPath.getNextImagePath(currentIndex, listOfImages);
				response.getWriter().write(listOfImages[currentIndex]);
			}
			else {
				response.getWriter().write(listOfImages[currentIndex]);
			}
		}
	}

}
