package com.adobe.aem.sample.core.service.impl;


import java.util.List;
import org.osgi.service.component.annotations.Component;
import com.adobe.aem.sample.core.service.Serve_Background;

@Component(service= {Serve_Background.class})
public class Serve_Background_Impl implements Serve_Background {

	/**
	 * the method will most likely need to be changed to take in the 
	 * boolean value for the sling scheduler: true to serve up the next image to change false otherwise.
	 */
	@Override
	public int getNextImagePath(int currentPath, String[] fileReferences) {
		if(currentPath == fileReferences.length-1) {
			currentPath = 0;
		}else {
			currentPath +=1;
		}
		return currentPath;
	}

}
