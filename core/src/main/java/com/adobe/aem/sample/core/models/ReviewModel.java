package com.adobe.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * Sling Model for the Review Component
 * @author Daniel
 *
 */
//Declares a Sling Model Class		//Allows optional injection of all the fields.
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReviewModel {

		//Fields
		@Inject					
		private String itemName;
		
		@Inject
		private String itemDescription;
		
		@Inject
		private String itemVideoUrl;
			
		@Inject
		private String itemPrice;

		//Get Methods
		public String getItemName() {
			return itemName;
		}

		public String getItemDescription() {
			return itemDescription;
		}

		public String getItemVideoUrl() {
			return itemVideoUrl;
		}

		public String getItemPrice() {
			return itemPrice;
		}

}
