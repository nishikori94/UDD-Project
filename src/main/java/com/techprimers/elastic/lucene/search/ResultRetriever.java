package com.techprimers.elastic.lucene.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.techprimers.elastic.lucene.indexing.handlers.DocumentHandler;
import com.techprimers.elastic.lucene.indexing.handlers.PDFHandler;
import com.techprimers.elastic.lucene.indexing.handlers.TextDocHandler;
import com.techprimers.elastic.lucene.indexing.handlers.Word2007Handler;
import com.techprimers.elastic.lucene.indexing.handlers.WordHandler;
import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;
import com.techprimers.elastic.model.RequiredHighlight;
import com.techprimers.elastic.model.ResultData;
import com.techprimers.elastic.repository.MagazineERepository;

@Service
public class ResultRetriever {

	@Autowired
	private MagazineERepository repository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	public ResultRetriever() {
	}

	public List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query, RequiredHighlight rh) {
		if (query == null) {
			return null;
		}
		
		AggregatedPage<IndexUnitOfMagazine> indexUnitArticles = highLightResult(query, rh);

		List<ResultData> results = new ArrayList<ResultData>();

		for (IndexUnitOfMagazine indexUnit : indexUnitArticles) {
			results.add(new ResultData(indexUnit.getTitle(), indexUnit.getKeyWords(), indexUnit.getFilename(),
					indexUnit.getScName(), indexUnit.getAuthor(), indexUnit.getHighlight()));
		}

		return results;
	}

	
	//https://github.com/VanRoy/spring-data-jest/blob/master/spring-data-jest/src/test/java/com/github/vanroy/springdata/jest/JestElasticsearchTemplateTests.java
	
	private AggregatedPage<IndexUnitOfMagazine> highLightResult(org.elasticsearch.index.query.QueryBuilder query,
			RequiredHighlight rh) {
		SearchQuery searchQuery = createSearchQuery(query, rh);

		AggregatedPage<IndexUnitOfMagazine> results = elasticsearchTemplate.queryForPage(searchQuery, IndexUnitOfMagazine.class, new SearchResultMapper() {
			
			@Override
			public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				// TODO Auto-generated method stub
				List<IndexUnitOfMagazine> articles = new ArrayList();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }

                    IndexUnitOfMagazine indexUnitOfMagazine = createIndexUnitMagazine(searchHit);

                    if (searchHit.getHighlightFields() != null) {
                        StringBuilder highlights = new StringBuilder("***");
                        if (searchHit.getHighlightFields().get(rh.getFieldName()) != null) {
                            Text[] text = searchHit.getHighlightFields().get(rh.getFieldName()).fragments();
                            for (Text t : text) {
                                highlights.append(t.toString());
                                highlights.append("***");
                            }
                        }
                        indexUnitOfMagazine.setHighlight(highlights.toString());
                    }
                    articles.add(indexUnitOfMagazine);
                }
                if (articles.size() > 0) {
                    return new AggregatedPageImpl<T>((List<T>) articles);
                }
                return null;
			}
		});
		return results;
	}
	
    private IndexUnitOfMagazine createIndexUnitMagazine(SearchHit searchHit) {
        IndexUnitOfMagazine indexUnitOfMagazine = new IndexUnitOfMagazine();
        indexUnitOfMagazine.setFilename((String) searchHit.getSource().get("filename"));
        indexUnitOfMagazine.setTitle((String) searchHit.getSource().get("title"));
        indexUnitOfMagazine.setAuthor((String) searchHit.getSource().get("author"));
        indexUnitOfMagazine.setKeyWords((String) searchHit.getSource().get("keyWords"));
        indexUnitOfMagazine.setScName((String) searchHit.getSource().get("scName"));
        indexUnitOfMagazine.setText((String) searchHit.getSource().get("text"));
        return indexUnitOfMagazine;
    }

	private SearchQuery createSearchQuery(org.elasticsearch.index.query.QueryBuilder query, RequiredHighlight rh) {
		return new NativeSearchQueryBuilder().withQuery(query)
				.withHighlightFields(new HighlightBuilder.Field(rh.getFieldName())).build();
	}

	protected DocumentHandler getHandler(String fileName) {
		if (fileName.endsWith(".txt")) {
			return new TextDocHandler();
		} else if (fileName.endsWith(".pdf")) {
			return new PDFHandler();
		} else if (fileName.endsWith(".doc")) {
			return new WordHandler();
		} else if (fileName.endsWith(".docx")) {
			return new Word2007Handler();
		} else {
			return null;
		}
	}
}
