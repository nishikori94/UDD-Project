package com.techprimers.elastic.load;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techprimers.elastic.jparepository.MagazineRepository;
import com.techprimers.elastic.model.Magazine;
import com.techprimers.elastic.repository.MagazineERepository;

@Component
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    MagazineERepository magazineERepository;

    @PostConstruct
    @Transactional
    public void loadAll(){

//        operations.putMapping(IndexUnitOfMagazine.class);
//       
//        List<IndexUnitOfMagazine> magazines = magazineRepository.findAll(); 
//        magazineERepository.save(magazines);
//        System.out.printf("Ucitavanje iz baze u elastic repo");

    }

}
