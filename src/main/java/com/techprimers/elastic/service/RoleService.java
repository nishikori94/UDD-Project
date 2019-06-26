package com.techprimers.elastic.service;

import java.util.Optional;

import com.techprimers.elastic.model.role.Role;

public interface RoleService {
	Optional<Role> findOne(Long id);
}
