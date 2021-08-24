package com.adobe.aem.sample.core.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeType;

/**
 * 
 * @author Kathy
 * The OSGI configuration for a sling scheduler.
 * An interface that defines the configuration for the sling scheduler, which is used by the 
 * blade component,
 */
@ObjectClassDefinition(name = "Blade Scheduler", description = "Scheduler for the blade component")
public @interface Blade_Scheduler {

	/**
	 * provides the name of the scheduler being used 
	 *
	 */
	  @AttributeDefinition(
	            name = "Scheduler name",
	            description = "Name of the scheduler",
	            type = AttributeType.STRING)
	    public String schedulerName() default "Custom Sling Scheduler Configuration for Blade component";
	  
	  /**
	   * cron expression have five fields:
	   * <minute> <hour> <day-of-month> <month> <day-of-week> <command>
	   * example
	   * 0/20 * * * * ? --> firing every 10 seconds
	   * 
	   * and the example will be needing it to run:
	   * hourly, daily, or weekly <--these options will be configured for now
	   * 
	   * hourly --> 0 0 * ? * *
	   * daily, change at 00:00 --> 0 0 0 * * ?
	   * weekly changing on Monday, at 00:00 --> 0 0 0 ? * MON 
	   * 
	   * default is every minute
	   */
	  @AttributeDefinition(
			  name ="Cron Expression",
			  description = "Cron expression that specifies the schedule",
			  type = AttributeType.STRING)
	  public String cronExpression()default "0 * * ? * *"; //
}
