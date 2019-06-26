package com.techprimers.elastic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.elastic.exceptions.UnexistingScientificAreaException;
import com.techprimers.elastic.jparepository.ScientificAreaRepository;
import com.techprimers.elastic.model.ScientificArea;
import com.techprimers.elastic.service.ScientificAreaService;

@Service
public class ScientificAreaJpa implements ScientificAreaService{
	
	@Autowired
	ScientificAreaRepository scientificAreaRepository; 

	@Override
	public ScientificArea findOne(long id) {
		// TODO Auto-generated method stub
		ScientificArea scientificArea=scientificAreaRepository.findById(id);
		if(scientificArea==null) {
			throw new UnexistingScientificAreaException(id);
		}
		
		return scientificArea;
	}

	@Override
	public List<ScientificArea> findAll() {
		// TODO Auto-generated method stub
		return scientificAreaRepository.findAll();
	}

	@Override
	public ScientificArea save(ScientificArea scientificArea) {
		// TODO Auto-generated method stub
		return scientificAreaRepository.save(scientificArea);
	}

	@Override
	public void delete(ScientificArea scientificArea) {
		// TODO Auto-generated method stub
		scientificAreaRepository.delete(scientificArea);
	}

	@Override
	public ScientificArea update(ScientificArea scientificArea, long id) {
		// TODO Auto-generated method stub
		ScientificArea scientificAreaToUpdate=scientificAreaRepository.getOne(id);
		scientificAreaToUpdate.setName(scientificArea.getName());
		scientificAreaToUpdate.setYear(scientificArea.getYear());
		return null;
	}

}
