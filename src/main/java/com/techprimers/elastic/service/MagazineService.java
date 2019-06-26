package com.techprimers.elastic.service;

import java.util.List;

import com.techprimers.elastic.model.Magazine;

public interface MagazineService {
	
	public Magazine findOne(String issn);

	public List<Magazine> findAll();

	public Magazine save(Magazine magazine);

	public void delete(Magazine magazine);

	public Magazine update(Magazine magazine, String issn);

}
