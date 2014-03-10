package com.vamsi.spring.cdi;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class SimpleQuartzJobInvoker {

	public static void main(String argv[]) {

		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		Scheduler sched;
		try {
			sched = schedFact.getScheduler();
			sched.start();

			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(HelloJob.class).withIdentity("myJob","group1") 
					.usingJobData("jobSays", "Hello World!")
				      .usingJobData("myFloatValue", 3.141f)// name "myJob", group "group1"
					.build();

			// Trigger the job to run now, and then every 40 seconds
			Trigger trigger = newTrigger()
					.withIdentity("myTrigger", "group1")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(40)
									.repeatForever()).build();

			// Tell quartz to schedule the job using our trigger
			sched.scheduleJob(job, trigger);
			//you cannot do this below as we already scheduled one job with identity myJob. 
			//we can schedule another with same HelloJob with different id
			//sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
