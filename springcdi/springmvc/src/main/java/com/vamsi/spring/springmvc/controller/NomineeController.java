package com.vamsi.spring.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vamsi.spring.beans.Member;


@Controller
@RequestMapping("/nominee")
public final class NomineeController {
	private static final Logger log = LoggerFactory.getLogger(Logger.class);
	private String thanksViewName;

	
	@Value("${thanksviewname}")
	public void setThanksViewName(String thanksViewName) {
		this.thanksViewName = thanksViewName;
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public Member form() {
		return new Member();
	}

	@RequestMapping(value="/new",method = RequestMethod.POST)
	public String processFormData(Member member, RedirectAttributes redirectAttr) {
		log.info("Processing nominee: " + member);
		
		redirectAttr.addFlashAttribute(member);
		
		log.info("the view name is " + thanksViewName);
		return "redirect:"+thanksViewName;
	}
}
