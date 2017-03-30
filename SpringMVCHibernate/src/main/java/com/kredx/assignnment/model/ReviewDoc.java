package com.kredx.assignnment.model;

public class ReviewDoc {

	String text;
	String summary;
	int score;
	int matches;
	public ReviewDoc(String text,String summary,int score) {
		this.text = text;
		this.summary = summary;
		this.score = score;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
	}
}
