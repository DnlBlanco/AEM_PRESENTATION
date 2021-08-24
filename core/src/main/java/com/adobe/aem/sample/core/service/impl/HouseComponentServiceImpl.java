package com.adobe.aem.sample.core.service.impl;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import com.adobe.aem.sample.core.models.HouseComponent;
import com.adobe.aem.sample.core.service.HouseComponentService;

@Component(service = HouseComponentServiceImpl.class) //Annotate as a Service Class.
public class HouseComponentServiceImpl implements HouseComponentService {

	@Override
	public void nextHouse(SlingHttpServletRequest req) throws RepositoryException {
		
		//Address passed on the HTTP GET Call
		String requestAddress = req.getParameter("address");
		
		//Getting an instance of resource resolver.
		ResourceResolver resolver = req.getResourceResolver();

		//Getting the resource which has the data stored
		Resource addressResource = resolver.getResource(JCR_NODE_PATH); //This is the node that holds the house nodes.

		Node node = addressResource.adaptTo(Node.class); //Adapting the resource to a Node to iterate through the JCR.

		try {
			NodeIterator itr = node.getNodes(); //Creating a node iterator and retrieving the nodes inside node.
			while (itr.hasNext()) {
				//Creates a node instance of the node that follows.
				Node nextNode = itr.nextNode();
				
				//Retrieving the resource path of the node that was just created.
				addressResource = resolver.getResource(nextNode.getPath());
				
				//Adapting the resource to the House model.
				HouseComponent house = addressResource.adaptTo(HouseComponent.class);
				
				//Checking if the address of the current node matches the address given on the HTTP call.
				if (house.getAddress().equals(requestAddress)) {
					
					//Checking if the node is the last node.
					if(!itr.hasNext()) {					//Last case scenario, the next node is re adapted to the initial node.
						
						//Setting the resource back to the first node.
						addressResource = resolver.getResource(JCR_NODE_PATH);
						
						//setting nextNode back to being the container node.
						nextNode = addressResource.adaptTo(Node.class);
						
						//New iterator to iterate over a new node set and retrieve the first house node.
						NodeIterator itr2 = nextNode.getNodes();
						
						//First House Node
						nextNode = itr2.nextNode();
						
						//Setting resource to the first house node and giving it back to the user.
						addressResource = resolver.getResource(nextNode.getPath());	
					}else {
						//Setting resource to the path of the node following the current node.
						addressResource = resolver.getResource(itr.nextNode().getPath()); 
					}
					break;
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		//Adapting the selected node from the JCR stored values into a House model.
		HouseComponent house = addressResource.adaptTo(HouseComponent.class);

		//Storing the values of the house properties to set them in the content house node.
		String city = house.getCity();
		String address = house.getAddress();
		String description = house.getDescription();
		String price = house.getPrice();

		addressResource = resolver.getResource(CONTENT_NODE_PATH); // Setting the resource address of the House node in the content folder.

		Node houseNode = addressResource.adaptTo(Node.class); //Adapting the resource into a node to replace the house values.

		//Setting new properties for the house node.
		houseNode.setProperty("city", city);
		houseNode.setProperty("address", address);
		houseNode.setProperty("price", price);
		houseNode.setProperty("description", description);
		
		//Saving the changes.
		houseNode.getSession().save();
	}

}
