package com.techprimers.elastic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.elastic.exceptions.UnexistingArticleException;
import com.techprimers.elastic.jparepository.ArticleRepository;
import com.techprimers.elastic.model.Article;
import com.techprimers.elastic.service.ArticleService;

@Service
public class ArticleServiceJpa implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article findOne(long id) {
		// TODO Auto-generated method stub
		Article article = articleRepository.findById(id);
		if (article == null) {
			throw new UnexistingArticleException(id);
		}
		return article;
	}

	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}

	@Override
	public Article save(Article article) {
		// TODO Auto-generated method stub
		return articleRepository.save(article);
	}

	@Override
	public void delete(Article article) {
		// TODO Auto-generated method stub
		articleRepository.delete(article);
	}

	@Override
	public Article update(Article article, long id) {
		// TODO Auto-generated method stub
		Article articleToUpdate = this.findOne(id);
		articleToUpdate.setTitle(article.getTitle());
		// articleToUpdate.setCoautors(article.getCoautors());
		// articleToUpdate.setAuthorUsername(article.getAuthorUsername());
		// articleToUpdate.setPdf(article.getPdf());
		// articleToUpdate.setScientificArea(article.getScientificArea());
		// articleToUpdate.setTerms(article.getTerms());
		// articleToUpdate.setVersion(article.getVersion());

		return articleRepository.save(articleToUpdate);
	}

}
