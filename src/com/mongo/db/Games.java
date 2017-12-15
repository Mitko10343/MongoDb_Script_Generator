
/*
 *3rd Year MongoDb Assignment
 *Written By: Dimiter Dinkov
 *Student Number : C15334276
 */

package com.mongo.db;

//Class to store all the data being read from the .tsv file
//Game Object to store data in array list
public class Games {
	//Variables to store all the data from each column in the .tsv frile
	private int id;
	private String score_phrase;
	private String title;
	private String url;
	private String platform;
	private float score;
	private String genre;
	private String editors_choice;
	private int relese_year,release_month,release_day;
	
	
	//Class constructor
	public Games(int id, String score_phrase, String title, String url,float score, String platform, String genre,
			String editors_choice, int relese_year, int release_month, int release_day) {
		this.setId(id);
		this.setScore_phrase(score_phrase);
		this.setTitle(title);
		this.setUrl(url);
		this.setScore(score);
		this.setPlatform(platform);
		this.setGenre(genre);
		this.setEditors_choice(editors_choice);
		this.setRelese_year(relese_year);
		this.setRelease_month(release_month);
		this.setRelease_day(release_day);
	}
	
	//Getter and setter methods
	public int getId() {
		return id;
	}
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getScore_phrase() {
		return score_phrase;
	}
	public void setScore_phrase(String score_phrase) {
		this.score_phrase = score_phrase;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getEditors_choice() {
		return editors_choice;
	}
	public void setEditors_choice(String editors_choice) {
		this.editors_choice = editors_choice;
	}
	public int getRelese_year() {
		return relese_year;
	}
	public void setRelese_year(int relese_year) {
		this.relese_year = relese_year;
	}
	public int getRelease_month() {
		return release_month;
	}
	public void setRelease_month(int release_month) {
		this.release_month = release_month;
	}
	public int getRelease_day() {
		return release_day;
	}
	public void setRelease_day(int release_day) {
		this.release_day = release_day;
	}
	
	
}
