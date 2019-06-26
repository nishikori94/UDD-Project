package com.techprimers.elastic.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techprimers.elastic.model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, String> {

	Magazine findByIssn(String issn);


}