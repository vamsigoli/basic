package com.vamsi.spring.cdi;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class HelloJob implements Job {

	public HelloJob() {
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobKey key = context.getJobDetail().getKey();

		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		String jobSays = dataMap.getString("jobSays");
		float myFloatValue = dataMap.getFloat("myFloatValue");

		System.err.println("Instance " + key + " of DumbJob says: " + jobSays
				+ ", and val is: " + myFloatValue);

		System.err.println("Hello!  HelloJob is executing.");
	}
}