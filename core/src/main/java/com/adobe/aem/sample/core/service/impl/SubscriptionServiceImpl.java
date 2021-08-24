package com.adobe.aem.sample.core.service.impl;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.adobe.aem.sample.core.service.SubscriptionService;



@Component(
		service = SubscriptionService.class,
		property = {
				Constants.SERVICE_ID + "=Subscription Service",
				Constants.SERVICE_DESCRIPTION + "= This service is responsible for creating a jcr node with the information of the subscriber"
		}
	)
public class SubscriptionServiceImpl implements SubscriptionService{

	@Override
	public void addSubscriber(SlingHttpServletRequest request) throws RepositoryException {
		
		//Retrieving the Form parameters from the request object.
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		//Getting an instance of Resource Resolver
		ResourceResolver resolver = request.getResourceResolver();
		
		/*Adapting the resolver to the subscriber model
		SubscribeModel subscriber = resolver.adaptTo(SubscribeModel.class);*/
		
		//Getting the subscriber node at the path where the subscribe nodes will be saved.
		Resource pathResource = resolver.getResource(JCR_NODE_PATH);
		
		//Creating a subscriber node.
		Node subscriberNode = pathResource.adaptTo(Node.class);
		
		//Filling the values of the node.
		subscriberNode.setProperty("firstName", firstName);
		subscriberNode.setProperty("lastName", lastName);
		subscriberNode.setProperty("phone", phone);
		subscriberNode.setProperty("email", email);
		
		//Saving the changes.
		subscriberNode.getSession().save();
	
	}

}
