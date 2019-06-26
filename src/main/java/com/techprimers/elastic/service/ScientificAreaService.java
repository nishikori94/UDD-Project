package com.techprimers.elastic.service;

import java.util.List;

import com.techprimers.elastic.model.ScientificArea;

public interface ScientificAreaService {
	
	public ScientificArea findOne(long id);

	public List<ScientificArea> findAll();

	public ScientificArea save(ScientificArea scientificArea);

	public void delete(ScientificArea scientificArea);

	public ScientificArea update(ScientificArea scientificArea, long id);

}
