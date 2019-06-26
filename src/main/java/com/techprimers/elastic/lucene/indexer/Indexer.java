package com.techprimers.elastic.lucene.indexer;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.elastic.lucene.indexing.handlers.DocumentHandler;
import com.techprimers.elastic.lucene.indexing.handlers.PDFHandler;
import com.techprimers.elastic.lucene.indexing.handlers.TextDocHandler;
import com.techprimers.elastic.lucene.indexing.handlers.Word2007Handler;
import com.techprimers.elastic.lucene.indexing.handlers.WordHandler;
import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;
import com.techprimers.elastic.repository.MagazineERepository;

@Service
public class Indexer {

	@Autowired
	private MagazineERepository repository;

	public Indexer() {
	}

	public boolean delete(String filename) {
		if (repository.equals(filename)) {
			repository.delete(filename);
			return true;
		} else
			return false;

	}

	public boolean update(IndexUnitOfMagazine unit) {
		unit = repository.save(unit);
		if (unit != null)
			return true;
		else
			return false;
	}

	public boolean add(IndexUnitOfMagazine unit) {
		unit = repository.index(unit);
		if (unit != null)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param file
	 *            Direktorijum u kojem se nalaze dokumenti koje treba indeksirati
	 */
	public int index(File file) {
		DocumentHandler handler = null;
		String fileName = null;
		int retVal = 0;
		try {
			File[] files;
			if (file.isDirectory()) {
				files = file.listFiles();
			} else {
				files = new File[1];
				files[0] = file;
			}
			for (File newFile : files) {
				if (newFile.isFile()) {
					fileName = newFile.getName();
					handler = getHandler(fileName);
					if (handler == null) {
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}
					if (add(handler.getIndexUnit(newFile)))
						retVal++;
				} else if (newFile.isDirectory()) {
					retVal += index(newFile);
				}
			}
			System.out.println("indexing done");
		} catch (Exception e) {
			System.out.println("indexing NOT done");
		}
		return retVal;
	}

	public DocumentHandler getHandler(String fileName) {
		if (fileName.endsWith(".txt")) {
			return new TextDocHandler();
		} else if (fileName.endsWith(".pdf")) {
			System.out.println("????");
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