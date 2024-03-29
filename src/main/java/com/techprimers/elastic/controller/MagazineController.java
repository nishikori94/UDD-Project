package com.techprimers.elastic.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techprimers.elastic.lucene.indexer.Indexer;
import com.techprimers.elastic.lucene.model.IndexUnitOfMagazine;
import com.techprimers.elastic.model.Magazine;
import com.techprimers.elastic.model.ScientificArea;
import com.techprimers.elastic.repository.MagazineERepository;
import com.techprimers.elastic.service.MagazineService;
import com.techprimers.elastic.service.ScientificAreaService;

@RestController
@RequestMapping(value = "/api/magazines")
@CrossOrigin(origins = "http://localhost:4200")
public class MagazineController {

	@Autowired
	private Indexer indexer;

	@Autowired
	private MagazineERepository magazineRepo;

	@Autowired
	private MagazineService magazineService;

	@Autowired
	private ScientificAreaService scService;

	
	@PostMapping(path = "/download", produces = "application/json")
	public @ResponseBody ResponseEntity<byte[]> download(@RequestBody String location) throws IOException {
		Path path = Paths.get(location);
		File downloadFile = new File(location);

		byte[] content = Files.readAllBytes(path);

		HttpHeaders headers = new HttpHeaders();

		// set header fields
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentLength((int) downloadFile.length());

		// disposition = attachment - for download
		headers.setContentDispositionFormData("attachment; filename=\"%s\"",
				location);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(content, headers, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Magazine>> getMagazines() {

		return new ResponseEntity<Collection<Magazine>>(magazineService.findAll(), HttpStatus.OK);

	}

	@GetMapping(value = "/title={title}")
	private ResponseEntity<List<IndexUnitOfMagazine>> getMagazineByTitle(@PathVariable final String title) {
		List<IndexUnitOfMagazine> magazine = magazineRepo.findByTitle(title);
		return new ResponseEntity<List<IndexUnitOfMagazine>>(magazine, HttpStatus.OK);
	}

	@GetMapping(value = "/key+words={keyWords}")
	private ResponseEntity<List<IndexUnitOfMagazine>> getMagazineByKeyWords(@PathVariable final String keyWords) {
		List<IndexUnitOfMagazine> magazine = magazineRepo.findByKeyWords(keyWords);
		return new ResponseEntity<List<IndexUnitOfMagazine>>(magazine, HttpStatus.OK);
	}

	@GetMapping(value = "/author={author}")
	private ResponseEntity<List<IndexUnitOfMagazine>> getMagazineByAuthor(@PathVariable final String author) {
		List<IndexUnitOfMagazine> magazine = magazineRepo.findByAuthor(author);
		return new ResponseEntity<List<IndexUnitOfMagazine>>(magazine, HttpStatus.OK);
	}

	@GetMapping(value = "/text={text}")
	private ResponseEntity<List<IndexUnitOfMagazine>> getMagazineByText(@PathVariable final String text) {
		List<IndexUnitOfMagazine> magazine = magazineRepo.findByText(text);
		return new ResponseEntity<List<IndexUnitOfMagazine>>(magazine, HttpStatus.OK);
	}

	@GetMapping(value = "/scientific+area={scName}")
	private ResponseEntity<List<IndexUnitOfMagazine>> getMagazineByScName(@PathVariable final String scName) {
		List<IndexUnitOfMagazine> magazine = magazineRepo.findByTitle(scName);
		return new ResponseEntity<List<IndexUnitOfMagazine>>(magazine, HttpStatus.OK);
	}

	@RequestMapping(value = "/add={issn}/title={title}/author={author}/sc={scId}/keyWords={keyWords}", method = RequestMethod.POST)
	private ResponseEntity<String> addMagazine(@PathVariable String issn, @PathVariable String title,
			@PathVariable String author, @PathVariable long scId, @PathVariable String keyWords,
			@RequestParam("file") MultipartFile file) {
		String message = "";

		try {
			Magazine newMag = new Magazine();
			newMag.setIssn(issn);
			newMag.setAuthor(author.replace("+", " "));
			newMag.setTitle(title.replace("+", " "));
			newMag.setKeyWords(keyWords.replace("+", " "));
			ScientificArea sc = scService.findOne(scId);
			newMag.setScientificArea(sc);

			System.err.println("indeksiranje . . . ");
			
			indexUploadedFile(newMag, file);

			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	private void indexUploadedFile(Magazine newMag, MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			System.err.println("fajl je prazan");
			return; // next please
		}

		String fileName = saveUploadedFile(file);
		System.out.println(file);
        System.out.println(fileName); 
		System.err.println("sacuvan u h2!");
		newMag.setFilePath(fileName);
		magazineService.save(newMag);// cuvanje u h2
		
		
        
		if (fileName != null) {
			IndexUnitOfMagazine indexUnit = indexer.getHandler(fileName).getIndexUnit(new File(fileName));
			indexUnit.setAuthor(newMag.getAuthor());
			indexUnit.setKeyWords(newMag.getKeyWords());
			indexUnit.setScName(newMag.getScientificArea().getName() + " " + newMag.getScientificArea().getYear());
			indexUnit.setTitle(newMag.getTitle());

			System.err.println(fileName + " izindeksiran " + " - keyWords: " + indexUnit.getKeyWords() + " autor: "
					+ indexUnit.getAuthor());

			indexer.add(indexUnit); // cuvanje u indekser
		}

	}

	private String saveUploadedFile(MultipartFile file) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File filePath = new File(classLoader.getResource("application.properties").getFile());
		String retVal = null;
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath.getAbsolutePath().replace("application.properties", "files/")
					+ file.getOriginalFilename());
			Files.write(path, bytes);
			retVal = path.toString();
		}
		return retVal;
	}

}
