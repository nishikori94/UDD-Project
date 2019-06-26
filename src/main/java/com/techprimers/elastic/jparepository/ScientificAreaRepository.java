package com.techprimers.elastic.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techprimers.elastic.model.ScientificArea;

@Repository
public interface ScientificAreaRepository  extends JpaRepository<ScientificArea, Long>{

	ScientificArea findById(long id);

}
 