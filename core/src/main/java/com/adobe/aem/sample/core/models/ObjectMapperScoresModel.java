package com.adobe.aem.sample.core.models;

public class ObjectMapperScoresModel {
	public String rankthisweek;
	public String ranklastweek;
	public String playername;
	public String rounds;
	public String average;
	public String totalstrokes;
	public String totaladjustment;
	public String totalrounds;
	


	@Override
	public String toString() {
		return "ObjectMapperScoresModel [rankthisweek=" + rankthisweek + ", ranklastweek=" + ranklastweek
				+ ", playername=" + playername + ", rounds=" + rounds + ", average=" + average + ", totalstrokes="
				+ totalstrokes + ", totaladjustment=" + totaladjustment + ", totalrounds=" + totalrounds + "]";
	}

	public String getRankthisweek() {
		return rankthisweek;
	}

	public void setRankthisweek(String rankthisweek) {
		this.rankthisweek = rankthisweek;
	}

	public String getRanklastweek() {
		return ranklastweek;
	}

	public void setRanklastweek(String ranklastweek) {
		this.ranklastweek = ranklastweek;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public String getRounds() {
		return rounds;
	}

	public void setRounds(String rounds) {
		this.rounds = rounds;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public String getTotalstrokes() {
		return totalstrokes;
	}

	public void setTotalstrokes(String totalstrokes) {
		this.totalstrokes = totalstrokes;
	}

	public String getTotaladjustment() {
		return totaladjustment;
	}

	public void setTotaladjustment(String totaladjustment) {
		this.totaladjustment = totaladjustment;
	}

	public String getTotalrounds() {
		return totalrounds;
	}

	public void setTotalrounds(String totalrounds) {
		this.totalrounds = totalrounds;
	}
	
	
	
}
