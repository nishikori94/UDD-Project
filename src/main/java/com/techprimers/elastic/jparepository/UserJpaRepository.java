package com.techprimers.elastic.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.elastic.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
