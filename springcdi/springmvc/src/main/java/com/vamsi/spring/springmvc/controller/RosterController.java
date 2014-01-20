package com.vamsi.spring.springmvc.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vamsi.spring.beans.Member;

@Controller
@RequestMapping("/roster")
//all the urls from this type are relative to /roster
public final class RosterController {
	private List<Member> members = new ArrayList<Member>();

	public RosterController() {
		members.add(new Member("John", "Lennon"));
		members.add(new Member("Paul", "McCartney"));
		members.add(new Member("George", "Harrison"));
		members.add(new Member("Ringo", "Starr"));
	}

	@RequestMapping("/list")
	//this mapping is mandatory in spring mvc 3.2 url is accessible as /list.*
	public void list(Model model) {
		model.addAttribute(members);
		//note that we have not given any view name. spring has identified
		//the jsp that needs to be forwarded to based on the name of the request
		//also the members is added to the model with out any name. spring identifies
		//the name by convention, that as it is a list, it's name should be memberList
		//in jsp, it is available as ${memberList}
	}

	@RequestMapping("/member")
	public void member(@RequestParam("id") Integer id, Model model) {
		model.addAttribute(members.get(id));
	}
}