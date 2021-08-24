package com.adobe.aem.sample.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;

import com.adobe.aem.sample.core.configurations.Blade_Scheduler;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true) //most implementations for a configuration will be through creating a Service Component
@Designate(ocd = Blade_Scheduler.class)//designates that configurations to be implemented through this service component.
public class Blade_SchedulerJob implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(Blade_SchedulerJob.class); //logging the events for this class.
	
	private String scheduleTime; 
	private int schedulerId;
	private boolean canRotate = false; //the default setting
	
	@Reference
	private Scheduler scheduler; //used to inject the scheduler instance and is referenced
	
	
	@Override
	public void run() {
		
		log.info("Blade Scheduler is running."); //from log observation, the run method is called every time the scheduler job is met.
		rotate(); //method is called every time the job is 'done' and becomes a new job...
		canRotate = false; //this 'resets' the can rotate variable after the can rotate is triggered. this is a fail-safe against an infinite loop.
	}
	
	
	//success in activating the configuration.
	@Activate
	protected void activate(Blade_Scheduler bladeConfig) {
		schedulerId = bladeConfig.schedulerName().hashCode();
		scheduleTime = bladeConfig.cronExpression();
		
		addScheduler(bladeConfig);
	}
	
	
	/**
	 * method that registers the scheduler and runs the scheduler given the cron expression.
	 * @param bladeConfig
	 */
	private void addScheduler(Blade_Scheduler bladeConfig) {
		ScheduleOptions options = scheduler.EXPR(bladeConfig.cronExpression());
		options.name(bladeConfig.schedulerName());
		options.canRunConcurrently(true);
		
		//schedule the 'job'
		scheduler.schedule(this, options);
		
		log.info("blade scheduler is added and running");
	}
	
	private void removeScheduler() {
		log.info("Removing scheduler: {}", schedulerId);
		scheduler.unschedule(String.valueOf(schedulerId));
		
	}
	
	/**
	 * boolean method that indicates the 'start' of the schedule job
	 * 
	 * at default, it's false
	 * when run is called every time the sling scheduler has it's job done and added again for the next incremented time; method called and returns true
	 * after the rotate method is called, the canRotate variable is set back to false; 
	 * @return
	 */
	public boolean rotate() {
		if(canRotate == false) {
			canRotate = true;
			return canRotate;
		}else {
			return false;
		}
		
		
	}
	
}
