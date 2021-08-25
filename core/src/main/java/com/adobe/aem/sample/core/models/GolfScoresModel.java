package com.adobe.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GolfScoresModel {
	
	@ValueMapValue
	String playername;

	@ValueMapValue
	String rankthisweek;
	
	@ValueMapValue
	String ranklastweek;
	
	@ValueMapValue
	String rounds;

	@ValueMapValue
	String average;
	
	@ValueMapValue
	String totalstrokes;
	
	@ValueMapValue
	String totaladjustment;
	
	@ValueMapValue
	String totalrounds;

	public String getPlayername() {
		return playername;
	}

	public String getRankthisweek() {
		return rankthisweek;
	}

	public String getRanklastweek() {
		return ranklastweek;
	}

	public String getRounds() {
		return rounds;
	}

	public String getAverage() {
		return average;
	}

	public String getTotalstrokes() {
		return totalstrokes;
	}

	public String getTotaladjustment() {
		return totaladjustment;
	}

	public String getTotalrounds() {
		return totalrounds;
	}
}
