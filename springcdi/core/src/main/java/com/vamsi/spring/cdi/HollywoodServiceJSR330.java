package com.vamsi.spring.cdi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class HollywoodServiceJSR330 {

	private AgentFinder finder;

	public AgentFinder getFinder() {
		return this.finder;
	}

	@Inject
	public void setFinder(AgentFinder finder) {
		System.out.println("setting finder..");
		this.finder = finder;
	}

	public List<Agent> getFriendlyAgents() {
		List<Agent> agents = getFinder().findAllAgents();
		List<Agent> friendlyAgents = filterAgents(agents, "Java Developers");
		return friendlyAgents;
	}

	public static List<Agent> filterAgents(List<Agent> agents, String agentType) {
		List<Agent> filteredAgents = new ArrayList<>();
		for (Agent agent : agents) {
			if (agent.getType().equals(agentType)) {
				filteredAgents.add(agent);
			}
		}
		return filteredAgents;
	}
}
