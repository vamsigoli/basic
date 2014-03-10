package com.vamsi.spring.jobs;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class JobHelper {
	
	@Autowired Scheduler scheduler;
	
	@Autowired JobDetail alJobDetails;
	
	@Autowired Trigger alTrigger;
	
	public  void startJob(String user) {
		
		
		
		
		
	}

}
