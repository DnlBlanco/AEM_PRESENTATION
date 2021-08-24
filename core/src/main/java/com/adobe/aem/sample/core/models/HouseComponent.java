/*
 *  House Component for AEM Presentation Page
 */

package com.adobe.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;


//Declares a Sling Model Class		//Allows optional injection of all the fields.
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HouseComponent{
	
	//Fields
	@Inject					
	private String description;
	
	@Inject
	private String city;
	
	@Inject
	private String address;
		
	@Inject
	private String price;

	
	//Getters for my defined fields.
	//When calling the values with sightly, the variables are retrieved by their getter methods.
	
	public String getDescription() {
		return description;
	}
	
	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getPrice() {
		return price;
	}

}