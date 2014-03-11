package com.vamsi.spring.jobs;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.*;

@Component
public class JobHelperService {
	
	@Autowired Scheduler scheduler;
	
	
	@Autowired CronTrigger alTrigger;
	@Autowired JobDetail alJobDetail;
	
	
	
	public  void startJob(String user) {
		
	 TriggerBuilder<CronTrigger> a = 	alTrigger.getTriggerBuilder();
	 a.withSchedule(cronSchedule("0 0/10 * * * ?"));
	 
	 Trigger toSchedule = a.build();
	 
		
	JobBuilder b = alJobDetail.getJobBuilder();
	b.usingJobData("userDetails", user);
	b.withDescription("updated description");
	
	JobDetail forJob = b.build();
	
	try {
		scheduler.scheduleJob(forJob, toSchedule);
	} catch (SchedulerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
		
		
	}

}
