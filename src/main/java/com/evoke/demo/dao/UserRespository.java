package com.evoke.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evoke.demo.model.User;

public interface UserRespository extends JpaRepository<User, Integer> {

	



}