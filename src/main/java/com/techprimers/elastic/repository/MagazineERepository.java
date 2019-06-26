package com.techprimers.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;

public interface MagazineERepository extends ElasticsearchRepository<IndexUnitOfMagazine, String> {
	List<IndexUnitOfMagazine> findByTitle(String title);

	List<IndexUnitOfMagazine> findByKeyWords(String key_words);

	List<IndexUnitOfMagazine> findByAuthor(String author);

	List<IndexUnitOfMagazine> findByText(String text);
	
	List<IndexUnitOfMagazine> findByScName(String name);

}
