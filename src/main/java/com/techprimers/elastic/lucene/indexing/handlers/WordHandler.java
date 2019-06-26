package com.techprimers.elastic.lucene.indexing.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;


import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;

public class WordHandler extends DocumentHandler {

	public IndexUnitOfMagazine getIndexUnit(File file) {
		IndexUnitOfMagazine retVal = new IndexUnitOfMagazine();
		InputStream is;

		try {
			is = new FileInputStream(file);
			// pomocu WordExtractor objekta izvuci tekst
			WordExtractor we = new WordExtractor(is);
			String text = we.getText();
			retVal.setText(text);

			// pomocu SummaryInformation objekta izvuci ostale metapodatke
			SummaryInformation si = we.getSummaryInformation();
			String title = si.getTitle();
			retVal.setTitle(title);

			String keywords = si.getKeywords();
			retVal.setKeyWords(keywords);

			retVal.setFilename(file.getCanonicalPath());

	//		we.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Dokument ne postoji");
		} catch (Exception e) {
			System.out.println("Problem pri parsiranju doc fajla");
		}

		return retVal;
	}

	@Override
	public String getText(File file) {
		String text = null;
		try {
			WordExtractor we = new WordExtractor(new FileInputStream(file));
			text = we.getText();
	//		we.
	//		we.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Dokument ne postoji");
		} catch (Exception e) {
			System.out.println("Problem pri parsiranju doc fajla");
		}
		return text;
	}

}
