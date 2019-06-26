package com.techprimers.elastic.jparepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.elastic.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	public User findByUsername(String username);
	public User findByEmail(String email);
	public Optional<User> findById(long id);

}
