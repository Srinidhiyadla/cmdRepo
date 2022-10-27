//package com.evoke.demo.TestController;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.evoke.demo.controller.UserController;
//import com.evoke.demo.dao.UserRespository;
//import com.evoke.demo.model.User;
//
//@ExtendWith(MockitoExtension.class)
//public class SampleUserControllerTest {
//
//	@Mock
//	private UserRespository userRespository;
//
//	@InjectMocks
//	private UserController userController;
//
//	@BeforeAll
//	public static void SetupAll() {
//		System.out.println("Should Print before all tests");
//	}
//
//	@BeforeEach
//	public void setup() {
//		System.out.println("Instantiating User service");
//		this.userRespository = mock(UserRespository.class);
//		this.userController = new UserController(userRespository);
//		// userController = new UserController();
//	}
//
//	@Test
//	@DisplayName("Should save User Details")
//	public void testSaveUser() {
//		// supplies Request Context when code under test needs it
////		MockHttpServletRequest request = new MockHttpServletRequest();
////        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
////         
//
//		// given
//		User user = new User(06, "sai", "7536993907", "abc@gmail.com", "vizag", "dfjW95");
//
//		// when
//		when(userRespository.save(any(User.class))).thenReturn(user);
//
//		// User savedUser = userRespository.save(user);
//		User responseEntity = userRespository.save(user);
//		// assertThat(savedUser.getUserName().isEmpty());
//
//		// then
//		assertThat(responseEntity.getId()).isEqualTo(6);
//	}
//
//	
//}
