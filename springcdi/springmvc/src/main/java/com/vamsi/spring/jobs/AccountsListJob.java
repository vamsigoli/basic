package com.vamsi.spring.jobs;


import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.springmvc.service.AccountService;

public class AccountsListJob extends QuartzJobBean {
	
	@Autowired AccountService accountService;
	
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		JobDataMap param = context.getMergedJobDataMap();
		
		String userName = param.getString("userDetails");
		
		Account userAccount = accountService.getAccountDetails(userName);
		
		System.out.println(userAccount);
		
	}

}
