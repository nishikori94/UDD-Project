package com.techprimers.elastic.dto;

import java.util.HashSet;
import java.util.Set;

import com.techprimers.elastic.model.Article;
import com.techprimers.elastic.model.Magazine;

public class CartDTO {
	private Set<Article> articles = new HashSet<Article>(0);
	private Set<Magazine> magazines = new HashSet<Magazine>(0);
	
	public CartDTO(){}
	
	public CartDTO(Set<Article> articles, Set<Magazine> magazines) {
		super();
		this.articles = articles;
		this.magazines = magazines;
	}
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public Set<Magazine> getMagazines() {
		return magazines;
	}
	public void setMagazines(Set<Magazine> magazines) {
		this.magazines = magazines;
	}
	
	
}
