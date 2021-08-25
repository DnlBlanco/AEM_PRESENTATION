package com.adobe.aem.sample.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


/**
 * 
 * @author kathyhseol
 *	due to complications with the multifields in granite and coral, the content.xml structure for the dialog
 *will no longer contain a multifield.
 *
 * 08/19/21
 * the sling model below will be using a sling model exporter using Jackson exporter for the purpose of developing in AEM.
 * 
 * you can define your own selector by adding in the extra parameters "selector" in the annotation
 */
@Model(adaptables = Resource.class, 
		resourceType = Blade_Sample.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL) //declared the class a SLING MODEL
@Exporter(name = "jackson", extensions = "json", selector = "exportBlade",
		options={
				@ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true")
		}) 
@JsonRootName("Blade-Component")
public class Blade_Sample {
	protected final static String RESOURCE_TYPE = "sample/components/blade-sample"; //this defines the component or the resource
	

	@ValueMapValue
	private String bladetitle;
	@ValueMapValue
	private String content;
	
	@ValueMapValue
	private String fileReference;
	@ValueMapValue
	private String fileReference2;
	@ValueMapValue
	private String fileReference3;
	@ValueMapValue
	private int interval;
	
	
	@JsonProperty(value="blade-title")
	public String getBladetitle() {
		return bladetitle;
	}
	
	public String getContent() {
		return content;
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
	
	public int getInterval() {
		return interval;
	}
	
}
