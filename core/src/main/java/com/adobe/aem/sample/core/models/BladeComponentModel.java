/*Blade Component for Golf World Page*/

package com.adobe.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


//Declares a Sling Model Class		//Allows optional injection of all the fields.
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BladeComponentModel {

	//Class Fields
	@Inject
	private String title;
	
	@Inject
	private String description;
	
	@Inject
	private String fileReference;
	
	@Inject
	private String fileReference2;
	
	@Inject
	private String fileReference3;
	
	@Inject
	private String rotationFrequency;

	//Getters
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getFileReference() {
		return fileReference;
	}
	
	public String getFileReference2() {
		return fileReference2;
	}
	
	public String getFileReference3() {
		return fileReference3;
	}
	
	public String getRotationFrequency(){
		return rotationFrequency;
	}
}
