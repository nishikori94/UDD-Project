package com.techprimers.elastic.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.elastic.jparepository.RoleRepository;
import com.techprimers.elastic.model.role.Role;
import com.techprimers.elastic.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findOne(Long id) {
		return roleRepository.findById(id);
	}

}
