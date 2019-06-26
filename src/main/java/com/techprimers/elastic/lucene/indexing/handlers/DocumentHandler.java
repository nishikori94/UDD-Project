package com.techprimers.elastic.lucene.indexing.handlers;

import java.io.File;

import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;

public abstract class DocumentHandler {
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
	public abstract IndexUnitOfMagazine getIndexUnit(File file);
	public abstract String getText(File file);

}
