package com.techprimers.elastic.service;

import java.util.List;

import com.techprimers.elastic.model.Article;

public interface ArticleService {

	public Article findOne(long id);

	public List<Article> findAll();

	public Article save(Article article);

	public void delete(Article article);

	public Article update(Article article, long id);

}
