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

import com.edusystem.entity.StudentDetails;
import com.edusystem.exception.PaymentNotFoundException;
import com.edusystem.exception.StudentNotFoundException;
import com.edusystem.repository.StudentDetailsRepository;

@SpringBootTest
public class StudentDetailsServiceTest {
	@InjectMocks
	private StudentDetailsService studentDetailsService = new StudentDetailsServiceImpl();

	@Mock
	private StudentDetailsRepository studentDetailsRepository;

	@Test
	public void testStudentDetailsById() {
		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setStudentId(101);
		studentDetails.setStudentName("Ram");
		studentDetails.setStudentContact(990238766);
		studentDetails.setStudentEmail("ram@gmail");
		studentDetails.setStudentAddress("Hassan");
		studentDetails.setGender("M");
		studentDetails.setStudentPassword("harshi");

		Optional<StudentDetails> optionalStudentDetails = Optional.of(studentDetails);

		when(studentDetailsRepository.findById(101)).thenReturn(optionalStudentDetails);

		StudentDetails myStudentDetails = studentDetailsService.getStudentDetailsById(101);

		assertEquals(990238766, myStudentDetails.getStudentContact());
	}

	@Test
	public void testGetStudentDetailsByIdWithException() {

		when(studentDetailsRepository.findById(100)).thenThrow(StudentNotFoundException.class);

		assertThrows(StudentNotFoundException.class, () -> studentDetailsService.getStudentDetailsById(101));

	}

	@Test
	public void testGetAllStudentDetailses() {
		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setStudentId(101);
		studentDetails.setStudentName("Ram");
		studentDetails.setStudentContact(990238766);
		studentDetails.setStudentEmail("ram@gmail");
		studentDetails.setStudentAddress("Hassan");
		studentDetails.setGender("M");
		studentDetails.setStudentPassword("harshi");

		StudentDetails studentDetails1 = new StudentDetails();
		studentDetails1.setStudentId(102);
		studentDetails1.setStudentName("Ram");
		studentDetails1.setStudentContact(990256678);
		studentDetails1.setStudentEmail("ram@gmail");
		studentDetails1.setStudentAddress("BLR");
		studentDetails1.setGender("M");
		studentDetails1.setStudentPassword("harshi");

		StudentDetails studentDetails2 = new StudentDetails();
		studentDetails2.setStudentId(103);
		studentDetails2.setStudentName("Ram");
		studentDetails2.setStudentContact(990238766);
		studentDetails2.setStudentEmail("ram@gmail");
		studentDetails2.setStudentAddress("Hassan");
		studentDetails2.setGender("M");
		studentDetails2.setStudentPassword("harshi");

		List<StudentDetails> studentDetailsList = new ArrayList<>();
		studentDetailsList.add(studentDetails);
		studentDetailsList.add(studentDetails1);
		studentDetailsList.add(studentDetails2);

		when(studentDetailsRepository.findAll()).thenReturn(studentDetailsList);

		List<StudentDetails> StudentDetailses = studentDetailsService.getAllStudentDetails();

		assertEquals(3, StudentDetailses.size());

	}

	@Test
	public void testSaveStudentDetails() {
		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setStudentId(101);
		studentDetails.setStudentName("Ram");
		studentDetails.setStudentContact(990238766);
		studentDetails.setStudentEmail("ram@gmail");
		studentDetails.setStudentAddress("Hassan");
		studentDetails.setGender("M");
		studentDetails.setStudentPassword("harshi");

		when(studentDetailsRepository.save(studentDetails)).thenReturn(studentDetails);

		StudentDetails newStudentDetails = studentDetailsService.saveStudentDetails(studentDetails);

		assertEquals("ram@gmail", newStudentDetails.getStudentEmail());

		verify(studentDetailsRepository, times(1)).save(studentDetails);

	}

	@Test
	public void testDeleteStudentDetails() {

		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setStudentId(101);
		studentDetails.setStudentName("Ram");
		studentDetails.setStudentContact(990238766);
		studentDetails.setStudentEmail("ram@gmail");
		studentDetails.setStudentAddress("Hassan");
		studentDetails.setGender("M");
		studentDetails.setStudentPassword("harshi");

		Optional<StudentDetails> optionalStudentDetails = Optional.of(studentDetails);

		when(studentDetailsRepository.findById(101)).thenReturn(optionalStudentDetails);

		studentDetailsService.deleteStudentDetails(101);

		verify(studentDetailsRepository, times(1)).findById(101);
		verify(studentDetailsRepository, times(1)).deleteById(101);

	}
	@Test
	void testUpdateStudentDetails() {

		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setStudentId(101);
		studentDetails.setStudentName("Ram");
		studentDetails.setStudentContact(990238766);
		studentDetails.setStudentEmail("ram@gmail");
		studentDetails.setStudentAddress("Hassan");
		studentDetails.setGender("M");
		studentDetails.setStudentPassword("harshi");

		Optional<StudentDetails> optionalStudentDetails = Optional.of(studentDetails);

		when(studentDetailsRepository.findById(101)).thenReturn(optionalStudentDetails);

		studentDetailsService.updateStudentDetails(studentDetails);

		verify(studentDetailsRepository, times(1)).findById(101);
		verify(studentDetailsRepository, times(1)).save(studentDetails);

	}

}