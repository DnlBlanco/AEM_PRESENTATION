package com.adobe.aem.sample.core.service.impl;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;


import com.adobe.aem.sample.core.models.ReviewModel;
import com.adobe.aem.sample.core.service.ReviewService;

@Component(service = ReviewService.class, property = { Constants.SERVICE_ID + "=Product Review Service",
		Constants.SERVICE_DESCRIPTION
				+ "= This service is responsible for showing the various golf items saved in the JCR" })
public class ReviewServiceImpl implements ReviewService {

	@Override
	public void nextItem(SlingHttpServletRequest req) throws RepositoryException {

		//Displayed itemName
		String displayedItemName = req.getParameter("itemName");

		// Getting an instance of resource resolver.
		ResourceResolver resolver = req.getResourceResolver();

		// Getting the resource which has the data stored
		Resource resource = resolver.getResource(JCR_NODE_PATH); // This is the node that holds the golf item nodes.

		Node node = resource.adaptTo(Node.class); // Adapting the resource to a Node to iterate through the JCR.

		try {
			NodeIterator itr = node.getNodes(); // Creating a node iterator and retrieving the nodes inside node.
			while (itr.hasNext()) {
				// Creates a node instance of the node that follows.
				Node nextNode = itr.nextNode();

				// Retrieving the resource path of the node that was JUST created.
				resource = resolver.getResource(nextNode.getPath());

				// Adapting the resource to the Review model.
				ReviewModel reviewItem = resource.adaptTo(ReviewModel.class);

				// Checking if the itemName of the current node matches the itemName given on the
				// HTTP call.
				if (reviewItem.getItemName().equals(displayedItemName)) {

					// Checking if the node is the last node.
					if (!itr.hasNext()) { // Last case scenario, the next node is re adapted to the initial node.

						// Setting the resource back to the first node.
						resource = resolver.getResource(JCR_NODE_PATH);

						// setting nextNode back to being the container node.
						nextNode = resource.adaptTo(Node.class);

						// New iterator to iterate over a new node set and retrieve the first item
						// node.
						NodeIterator itr2 = nextNode.getNodes();

						// First Item Node
						nextNode = itr2.nextNode();

						// Setting resource to the first house node and giving it back to the user.
						resource = resolver.getResource(nextNode.getPath());
					} else {
						// Setting resource to the path of the node following the current node.
						resource = resolver.getResource(itr.nextNode().getPath());
					}
					break;
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		// Adapting the selected node from the JCR stored values into a Review model.
		ReviewModel reviewItem = resource.adaptTo(ReviewModel.class);

		// Storing the values of the item node properties to set them in the content component node path.
		String itemName = reviewItem.getItemName();
		String itemDescription = reviewItem.getItemDescription();
		String itemVideoUrl = reviewItem.getItemVideoUrl();
		String itemPrice = reviewItem.getItemPrice();

		resource = resolver.getResource(CONTENT_NODE_PATH); // Setting the resource address of the Review Component in
																	// the content folder.

		Node itemNode = resource.adaptTo(Node.class); // Adapting the resource into a node to replace the item's values.

		// Setting the new values for the Review Component properties.
		itemNode.setProperty("itemName", itemName);
		itemNode.setProperty("itemDescription", itemDescription);
		itemNode.setProperty("itemPrice", itemPrice);
		itemNode.setProperty("itemVideoUrl", itemVideoUrl);

		// Saving the changes.
		itemNode.getSession().save();
	}

}
