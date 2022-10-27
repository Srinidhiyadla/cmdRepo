package com.evoke.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoke.demo.dao.UserRespository;
import com.evoke.demo.model.User;
import com.evoke.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	Map<String, Object> map = new ConcurrentHashMap<String,Object>();
	
	@Autowired
	private UserService userService;

	public UserController(UserRespository userRespository) {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/users")
	public ResponseEntity<?> getUser() {
		
		List<User> userList = userService.getUser();
		if (!userList.isEmpty()) {
			map.put("status", 1);
			map.put("data", userList);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		userService.save(user);
		map.put("status", 1);
		map.put("message", "Record is Saved Successfully!");
		//map.put(generatedKey(user), user);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

//	private String generatedKey(User user) {
//		// TODO Auto-generated method stub
//		return String.format("%s-%s", user.getCity(),user.getUserName());
//	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id) {
		try {
			User user = userService.findById(id);
			map.put("status", 1);
			map.put("data", user);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		try {
			User user = userService.findById(id);
			userService.delete(user);
			map.put("status", 1);
			map.put("message", "Record is deleted successfully!");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User userDetail) {
		try {
			User user = userService.findById(id);
			user.setUserName(userDetail.getUserName());
			user.setMobileNo(userDetail.getMobileNo());
			user.setEmailId(userDetail.getEmailId());
			user.setCity(userDetail.getCity());
			user.setPassword(userDetail.getPassword());
			userService.save(user);
			map.put("status", 1);
			map.put("data", userService.findById(id));
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}

	}


}
