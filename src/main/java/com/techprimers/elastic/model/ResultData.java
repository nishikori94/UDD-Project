package com.techprimers.elastic.model;

public final class ResultData {

	private String title;
	private String keywords;
	private String location;
	private String scName;
	private String author;
	private String highlight;

	public ResultData() {
		super();
	}

	public ResultData(String title, String keywords, String location, String scName, String author, String highlight) {
		super();
		this.title = title;
		this.keywords = keywords;
		this.location = location;
		this.author = author;
		this.scName = scName;
		this.highlight = highlight;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
