package com.vamsi.spring.cdi;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

/**
 * Code for listing 3_1
 */
@Named
public class WebServiceAgentFinder implements AgentFinder {

	/**
	 * This method returns an empty list of agents for compilation sake
	 * 
	 * @return An empty list of Agents
	 */

	@PostConstruct
	public void postConstruct() {
		System.out.println("in post construct of webservicegentfinder..");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("in pre destroy of webserviceagentfinder..");
	}

	@Override
	public List<Agent> findAllAgents() {
		System.out.println("webservice agent finder finding agents..");
		List<Agent> agents = new ArrayList<>();
		// Lots of RESTFul based implementation would go here
		return agents;
	}
}
