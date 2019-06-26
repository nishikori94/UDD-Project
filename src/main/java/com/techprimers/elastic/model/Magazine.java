package com.techprimers.elastic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Magazine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String title;

	@Column(nullable = false, unique = true)
	private String issn;

	@ManyToOne
	private ScientificArea scientific_area;

	@Column
	private String key_words;

	@Column
	private String text;

	@Column
	private String author;

	@Column
	@Id
	private String file_path;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "magazine_file_path")
	private Set<Article> articles;

	public Magazine() {
		
		super();
		this.articles = new HashSet<Article>(0);
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public ScientificArea getScientificArea() {
		return scientific_area;
	}

	public void setScientificArea(ScientificArea scientificArea) {
		this.scientific_area = scientificArea;
	}

	public String getKeyWords() {
		return key_words;
	}

	public void setKeyWords(String keyWords) {
		this.key_words = keyWords;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFilePath() {
		return file_path;
	}
	

	public void setFilePath(String filePath) {
		this.file_path = filePath;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getScName() {
		return this.getScientificArea().getName() + " " + this.getScientificArea().getYear();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
