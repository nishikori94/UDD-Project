package com.techprimers.elastic.lucene.indexing.handlers;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import com.techprimers.elastic.lucene.filters.CyrillicLatinConverter;
import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;

public class PDFHandler extends DocumentHandler {
	
	//CyrillicLatinConverter convertor;

	@Override
	public IndexUnitOfMagazine getIndexUnit(File file) {
		System.out.println("!!!!");
		IndexUnitOfMagazine retVal = new IndexUnitOfMagazine();
		try {
			System.out.println("Ajdeeeee");
			org.apache.pdfbox.io.RandomAccessFile raf = new org.apache.pdfbox.io.RandomAccessFile(file,"r");
			PDFParser parser = new PDFParser(raf);
			System.out.println("Ovdee");
			parser.parse();
			String text = getText(parser);
			String txt=CyrillicLatinConverter.cir2lat(text);
			System.out.println("Latinica\n "+text);
			retVal.setText(txt);

			// metadata extraction
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();

			String title = "" + info.getTitle();
			retVal.setTitle(title);

			String keywords = "" + info.getKeywords();
			retVal.setKeyWords(keywords);

			retVal.setFilename(file.getCanonicalPath());

			pdf.close();
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}

		return retVal;
	}

	@Override
	public String getText(File file) {
		try {
			PDFParser parser = new PDFParser((RandomAccessRead) new RandomAccessFile(file, "r"));
			parser.parse();
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}

	public String getText(PDFParser parser) {
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}

}
