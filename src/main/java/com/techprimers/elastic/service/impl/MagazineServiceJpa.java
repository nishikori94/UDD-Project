package com.techprimers.elastic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.elastic.exceptions.UnexistingMagazineException;
import com.techprimers.elastic.jparepository.MagazineRepository;
import com.techprimers.elastic.model.Magazine;
import com.techprimers.elastic.service.MagazineService;

@Service
public class MagazineServiceJpa implements MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;

	@Override
	public Magazine findOne(String issn) {
		// TODO Auto-generated method stub
		Magazine magazine=magazineRepository.findByIssn(issn);
		if(magazine==null) {
			throw new UnexistingMagazineException(issn);
		}
		
		return magazine;
	}

	@Override
	public List<Magazine> findAll() {
		// TODO Auto-generated method stub
		return magazineRepository.findAll();
	}

	@Override
	public Magazine save(Magazine magazine) {
		// TODO Auto-generated method stub
		return magazineRepository.save(magazine);
	}

	@Override
	public void delete(Magazine magazine) {
		// TODO Auto-generated method stub
		magazineRepository.delete(magazine);
	}

	@Override
	public Magazine update(Magazine magazine, String issn) {
		// TODO Auto-generated method stub
		Magazine magazineToUpdate = this.findOne(issn);
		magazineToUpdate.setTitle(magazine.getTitle());
		// magazineToUpdate.setArticles(magazine.getArticles());
		magazineToUpdate.setScientificArea(magazine.getScientificArea());

		return magazineRepository.save(magazineToUpdate);
	}

}
