package com.techprimers.elastic.lucene.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.elastic.lucene.indexing.handlers.DocumentHandler;
import com.techprimers.elastic.lucene.indexing.handlers.PDFHandler;
import com.techprimers.elastic.lucene.indexing.handlers.TextDocHandler;
import com.techprimers.elastic.lucene.indexing.handlers.Word2007Handler;
import com.techprimers.elastic.lucene.indexing.handlers.WordHandler;
import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;
import com.techprimers.elastic.model.ResultData;
import com.techprimers.elastic.repository.MagazineERepository;

@Service
public class ResultRetriever {

	@Autowired
	private MagazineERepository repository;

	public ResultRetriever() {
	}

	public List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query) {
		if (query == null) {
			return null;
		}

		List<ResultData> results = new ArrayList<ResultData>();

		for (IndexUnitOfMagazine indexUnit : repository.search(query)) {
			results.add(new ResultData(indexUnit.getTitle(), indexUnit.getKeyWords(), indexUnit.getFilename(),
					indexUnit.getScName(), indexUnit.getAuthor(), ""));
		}

		return results;
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
