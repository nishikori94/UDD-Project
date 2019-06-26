package com.techprimers.elastic.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techprimers.elastic.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

	Article findById(long id);


}
