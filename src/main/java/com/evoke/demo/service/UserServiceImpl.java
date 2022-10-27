
  package com.evoke.demo.service;
  
  import java.util.List;
  

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;

import com.evoke.demo.dao.UserRespository;
import com.evoke.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


  @Service
  public class UserServiceImpl implements UserService {
	  
	  private static final Logger log = LoggerFactory
		      .getLogger(UserServiceImpl.class);

	//we will performing database interactions
  	@Autowired
  	private UserRespository userRepo;

  	@Override
  	public List<User> getUser() {
  		log.info("inside userRepo.getUsers");
  		return userRepo.findAll();
  	}

  	@Override
  	public void save(User user) {
  	  log.info("Adding Employee " + user);
  		userRepo.save(user);

  	}


	@Override
	public void delete(User user) {
		log.info("Deleting employee " + user);
  		userRepo.delete(user);

  	}

	@Override
	public User findById(Integer id) {
		log.info("Getting user details for ID " + id);
		return userRepo.findById(id).get();
	}


	}
	

 
