package com.techprimers.elastic.lucene.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = IndexUnitOfMagazine.INDEX_NAME, type = IndexUnitOfMagazine.INDEX_NAME, shards = 1)
public class IndexUnitOfMagazine {

	public static final String INDEX_NAME = "digitallibrary";
	public static final String TYPE_NAME = "book";

	@Id
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String filename;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String title;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String scName;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String keyWords;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String text;

	@Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String author;

	public IndexUnitOfMagazine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getScName() {
		return this.scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
