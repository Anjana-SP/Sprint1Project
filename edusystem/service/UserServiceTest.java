package com.edusystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.edusystem.entity.User;
import com.edusystem.exception.UserNotFoundException;
import com.edusystem.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserRepository userRepository;

	@Test
	public void testUserById() {

		User user = new User();
		user.setUserId(12);
		user.setUserName("sham");
		user.setUserPassword(234);
		user.setUserRole("admin");

		Optional<User> optionalUser = Optional.of(user);

		when(userRepository.findById(12)).thenReturn(optionalUser);

		User myUser = userService.getUserById(12);

		assertEquals("sham", myUser.getUserName());
	}

	@Test
	public void testGetUserByIdWithException() {

		when(userRepository.findById(12)).thenThrow(UserNotFoundException.class);

		assertThrows(UserNotFoundException.class, () -> userService.getUserById(12));
	}

	@Test
	public void testGetAllUsers() {

		User user = new User();
		user.setUserId(105);
		user.setUserName("Ram");
		user.setUserPassword(1234);
		user.setUserRole("admin");

		User user1 = new User();
		user.setUserId(106);
		user.setUserName("Rama");
		user.setUserPassword(3234);
		user.setUserRole("admin");

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user);

		when(userRepository.findAll()).thenReturn(userList);

		List<User> users = userService.getAllUsers();

		assertEquals(2, users.size());

	}

	@Test
	public void testSaveUser() {

		User user = new User();
		user.setUserId(12);
		user.setUserName("sham");
		user.setUserPassword(234);
		user.setUserRole("admin");

		when(userRepository.save(user)).thenReturn(user);

		User newUser = userService.saveUser(user);

		assertEquals("sham", newUser.getUserName());

		verify(userRepository, times(1)).save(user);

	}

	@Test
	public void testDeleteUser() {

		User user = new User();
		user.setUserId(12);
		user.setUserName("sham");
		user.setUserPassword(234);
		user.setUserRole("admin");

		Optional<User> optionalUser = Optional.of(user);

		when(userRepository.findById(12)).thenReturn(optionalUser);
		;

		userService.deleteUser(12);

		verify(userRepository, times(1)).findById(12);
		verify(userRepository, times(1)).deleteById(12);

	}

	@Test
	void testUser() {

		User user = new User();
		user.setUserId(12);
		user.setUserName("ram");
		user.setUserRole("office admin");
		user.setUserPassword(1234);

		Optional<User> optionalUser = Optional.of(user);

		when(userRepository.findById(12)).thenReturn(optionalUser);

		userService.updateUser(user);

		verify(userRepository, times(1)).findById(12);
		verify(userRepository, times(1)).save(user);

	}

}