package com.evoke.demo.TestController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.evoke.demo.controller.UserController;
import com.evoke.demo.dao.UserRespository;
import com.evoke.demo.model.User;
import com.evoke.demo.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	@MockBean
	UserServiceImpl userservice;

	@MockBean
	UserRespository userrepo;

	@Mock
	User user;

	private static MockMvc mockMVC;

	@Autowired
	private WebApplicationContext context;

	static ObjectMapper om = new ObjectMapper();

	List<User> userList;

	@BeforeEach
	public void setup() {
		System.out.println("Instantiating User service");
		om.registerModule(new JavaTimeModule());
		mockMVC = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	@DisplayName("Should save User Details")
	public void testSaveUser() throws Exception {
		String uri = "/api/save";
		User user = new User(3, "sai", "7536993907", "abc@gmail.com", "vizag", "dfjW95");
		String jsonres = om.writeValueAsString(user);

		when(userrepo.save(Mockito.any(User.class))).thenReturn(user);
		// when(userservice.save(Mockito.any(User.class))).thenReturn(user);
		MvcResult result = mockMVC
				.perform(
						MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonres))
				.andReturn();
		assertEquals(201, result.getResponse().getStatus());

	}

	@Test
	void testGetUser() throws Exception {
		User userOne = new User(3, "sai", "7536993907", "abc@gmail.com", "vizag", "dfjW95");
		User userTwo = new User(4, "sai", "7536993907", "abc@gmail.com", "vizag", "dfjW95");
		// User userTwo = new User(19, "Nidhi", "2934568987", "evc@gmail.com", "hyd",
		// "dgQ245");

		List<User> userList = new ArrayList<>();
		userList.add(userOne);
		userList.add(userTwo);
		// Mockito.when(userrepo.findById(userId)).thenReturn(user);
		Mockito.when(userservice.getUser()).thenReturn(userList);
		MvcResult result = mockMVC.perform(get("/api/users")).andExpect(status().isOk()).andReturn();
//		assertEquals(200, result.getResponse().getStatus());
//		assertEquals(result.getResponse().getContentAsString(), om.writeValueAsString(userList));
		  verify(userservice, times(1)).getUser();
	}

	
	@Test
	void testGetUserById() throws Exception {
		// String uri= "/api/user";
		User user = new User(8, "Srija", "458697877", "xyz@gmail.com", "vijayawada", "bue@84");
		Integer userid = 8;
		// user.setId(6);
		// Mockito.when(userservice.findById(user.setId(6))).thenReturn(user);

		Mockito.when(userservice.findById(userid)).thenReturn(user);
		MvcResult result = mockMVC.perform(get("/api/user/8")).andExpect(status().isOk()).andReturn();
		assertNotNull(user);
		assertEquals("Srija", user.getUserName());

	}

	@Test
	void testdeleteUser() throws Exception {
		// String uri= "/api/delete";
		User user = new User(8, "Srija", "458697877", "xyz@gmail.com", "vijayawada", "bue@84");
		Integer id = 8;
//		user.setId(6);
		when(userservice.findById(user.getId())).thenReturn(user);
		doNothing().when(userservice).delete(user);
		MvcResult result = mockMVC.perform(delete("/api/delete/8")).andExpect(status().isOk()).andReturn();
		verify(userservice, times(1)).delete(user);
	}
//	
//	@Test
//	void testdeleteUser()throws Exception  {
//		String uri= "/api/delete/" ;
//		User user = new User();
//		Integer Userid=3;
//		Mockito.doNothing().when(userservice).delete(user);
//		mockMVC.perform(get(uri)).andExpect(status().isOk());
//		Mockito.verify(userservice, times(1)).delete(user);
//
//
//	}
}