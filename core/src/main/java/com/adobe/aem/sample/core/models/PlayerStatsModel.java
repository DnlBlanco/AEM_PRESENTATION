package com.adobe.aem.sample.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PlayerStatsModel {
	
	String message = "This is the message variable";
	
	@Inject
	List<GolfScoresModel> playerStats;

	public List<GolfScoresModel> getPlayerStats() {
		return playerStats;
	}

	public String getMessage() {
		return message;
	}

	
	
}