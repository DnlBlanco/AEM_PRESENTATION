package com.adobe.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

//Declares a Sling Model Class		//Allows optional injection of all the fields.
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SubscribeModel {
	
	//Fields
	@Inject					
	private String firstName;
	
	@Inject
	private String lastName;
	
	@Inject
	private String phone;
		
	@Inject
	private String email;
	
	//Getters for my defined fields.
	//When calling the values with sightly, the variables are retrieved by their getter methods.

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}
}
