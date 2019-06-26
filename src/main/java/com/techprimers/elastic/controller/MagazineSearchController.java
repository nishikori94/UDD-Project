package com.techprimers.elastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.elastic.lucene.filters.CyrillicLatinConverter;
import com.techprimers.elastic.lucene.search.QueryBuilder;
import com.techprimers.elastic.lucene.search.ResultRetriever;
import com.techprimers.elastic.model.ResultData;
import com.techprimers.elastic.model.SearchType;
import com.techprimers.elastic.model.SimpleQuery;
import com.techprimers.elastic.repository.MagazineERepository;

@RestController
@RequestMapping(value = "/api/elastic")
@CrossOrigin(origins = "http://localhost:4200")
public class MagazineSearchController {

		@Autowired
		private ResultRetriever resultRetriever;
		
		@Autowired
		private MagazineERepository magazineRepo;

	
		@PostMapping(value="/search/term")
		public ResponseEntity<List<ResultData>> searchTermQuery(@RequestBody SimpleQuery simpleQuery) throws Exception {	
			System.err.println("kotroler: "+simpleQuery.getField()+" "+simpleQuery.getValue());
			org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.regular, simpleQuery.getField(), obradi(simpleQuery.getValue())/*simpleQuery.getValue()*/);
			List<ResultData> results = resultRetriever.getResults(query);			
			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
		}
		
//		@PostMapping(value="/search/fuzzy", consumes="application/json")
//		public ResponseEntity<List<ResultData>> searchFuzzy(@RequestBody SimpleQuery simpleQuery) throws Exception {
//			org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.fuzzy, simpleQuery.getField(), simpleQuery.getValue());
//			List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
//			rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
//			List<ResultData> results = resultRetriever.getResults(query, rh);			
//			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
//		}
//		
//		@PostMapping(value="/search/prefix", consumes="application/json")
//		public ResponseEntity<List<ResultData>> searchPrefix(@RequestBody SimpleQuery simpleQuery) throws Exception {
//			org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.prefix, simpleQuery.getField(), simpleQuery.getValue());
//			List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
//			rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
//			List<ResultData> results = resultRetriever.getResults(query, rh);			
//			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
//		}
//		
//		@PostMapping(value="/search/range", consumes="application/json")
//		public ResponseEntity<List<ResultData>> searchRange(@RequestBody SimpleQuery simpleQuery) throws Exception {
//			org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.range, simpleQuery.getField(), simpleQuery.getValue());
//			List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
//			rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
//			List<ResultData> results = resultRetriever.getResults(query, rh);			
//			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
//		}
//		
		@PostMapping(value="/search/phrase", consumes="application/json")
		public ResponseEntity<List<ResultData>> searchPhrase(@RequestBody SimpleQuery simpleQuery) throws Exception {
			org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, simpleQuery.getField(), simpleQuery.getValue());
			List<ResultData> results = resultRetriever.getResults(query);			
			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
		}
		
//		
//		@PostMapping(value="/search/boolean", consumes="application/json")
//		public ResponseEntity<List<ResultData>> searchBoolean(@RequestBody AdvancedQuery advancedQuery) throws Exception {
//			org.elasticsearch.index.query.QueryBuilder query1 = QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField1(), advancedQuery.getValue1());
//			org.elasticsearch.index.query.QueryBuilder query2 = QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField2(), advancedQuery.getValue2());
//			
//			BoolQueryBuilder builder = QueryBuilders.boolQuery();
//			if(advancedQuery.getOperation().equalsIgnoreCase("AND")){
//				builder.must(query1);
//				builder.must(query2);
//			}else if(advancedQuery.getOperation().equalsIgnoreCase("OR")){
//				builder.should(query1);
//				builder.should(query2);
//			}else if(advancedQuery.getOperation().equalsIgnoreCase("NOT")){
//				builder.must(query1);
//				builder.mustNot(query2);
//			}
//			
//			List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
//			rh.add(new RequiredHighlight(advancedQuery.getField1(), advancedQuery.getValue1()));
//			rh.add(new RequiredHighlight(advancedQuery.getField2(), advancedQuery.getValue2()));
//			List<ResultData> results = resultRetriever.getResults(builder, rh);			
//			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
//		}
//		
//		@PostMapping(value="/search/queryParser", consumes="application/json")
//		public ResponseEntity<List<ResultData>> search(@RequestBody SimpleQuery simpleQuery) throws Exception {
//			org.elasticsearch.index.query.QueryBuilder query=QueryBuilders.queryStringQuery(simpleQuery.getValue());			
//			List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
//			List<ResultData> results = resultRetriever.getResults(query, rh);
//			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
//		}
	
		private String obradi(String s) {
			String x=s.toLowerCase();
			x=CyrillicLatinConverter.cir2lat(x);
			return x;
		}
	
}
