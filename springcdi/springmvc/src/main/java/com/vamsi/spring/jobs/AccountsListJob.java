package com.vamsi.spring.jobs;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.vamsi.spring.springmvc.service.AccountService;

public class AccountsListJob extends QuartzJobBean {
	
	@Autowired AccountService accountService;
	
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
