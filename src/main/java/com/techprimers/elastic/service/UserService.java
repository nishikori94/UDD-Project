package com.techprimers.elastic.service;

import java.util.List;
import java.util.Optional;

import com.techprimers.elastic.model.User;

public interface UserService {

	Optional<User> findOne(long id);

	public List<User> findAll();

	public User save(User user);

	public void delete(User user);

	public User findByUsername(String username);

	public User findByEmail(String email);

}
