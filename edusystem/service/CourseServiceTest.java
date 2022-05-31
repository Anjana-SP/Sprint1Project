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

import com.edusystem.entity.Course;
import com.edusystem.exception.CourseNotFoundException;
import com.edusystem.repository.CourseRepository;


@SpringBootTest
public class CourseServiceTest {
	
	@InjectMocks
	private CourseService courseService = new CourseServiceImpl(); 
	
	@Mock
	private CourseRepository courseRepository;
	
	@Test
	public void testCourseById() {
		
		Course course = new Course();
		course.setCourseId(100);
		course.setCourseName("java");
		course.setCourseFee(16000.00);
		course.setCourseDuration(3);
	
      Optional<Course> optionalCourse = Optional.of(course);
		
		when(courseRepository.findById(100)).thenReturn(optionalCourse);
		
		Course myCourse = courseService.getCourseById(100);
		
		assertEquals("java", myCourse.getCourseName());
	}
	
	@Test
	public void testGetCourseByIdWithException() {
		
		when(courseRepository.findById(100)).thenThrow(CourseNotFoundException.class);
		
		assertThrows(CourseNotFoundException.class,()->courseService.getCourseById(100));
	}
	
	@Test
	public void testGetAllCourses() {
		
		Course course = new Course();
		course.setCourseId(100);
		course.setCourseName("java");
		course.setCourseFee(16000.00);
		course.setCourseDuration(3);
		
		Course course1 = new Course();
		course1.setCourseId(101);
		course1.setCourseName("phython");
		course1.setCourseFee(16000.00);
		course1.setCourseDuration(3);
		
		Course course2 = new Course();
		course2.setCourseId(102);
		course2.setCourseName("C");
		course2.setCourseFee(16000.00);
		course2.setCourseDuration(3);
		
		List<Course> courseList = new ArrayList<>();
		courseList.add(course);
		courseList.add(course1);
		courseList.add(course2);
		
		when(courseRepository.findAll()).thenReturn(courseList);
		
		List<Course> courses = courseService.getAllCourses();
		
		assertEquals(3,courses.size());		
	
	}
	
	@Test
	public void testSaveCourse() {
		
		Course course = new Course();
		course.setCourseId(100);
		course.setCourseName("java");
		course.setCourseFee(16000.00);
		course.setCourseDuration(3);
		
when(courseRepository.save(course)).thenReturn(course);
		
Course newCourse = courseService.saveCourse(course);
		
		assertEquals("java",newCourse.getCourseName());
		
		verify(courseRepository,times(1)).save(course);
		
	}
	
	@Test
	public void testDeleteCourse() {
		
		Course course = new Course();
		course.setCourseId(100);
		course.setCourseName("java");
		course.setCourseFee(16000.00);
		course.setCourseDuration(3);
		
		Optional<Course> optionalCourse = Optional.of(course);
		
		when(courseRepository.findById(100)).thenReturn(optionalCourse);
;
		
		courseService.deleteCourse(100);
		
		verify(courseRepository,times(1)).findById(100);
		verify(courseRepository,times(1)).deleteById(100);
		
	}
	
	@Test
	void testUpdateCourse() {

		Course course = new Course();
		course.setCourseId(100);
		course.setCourseName("java");
		course.setCourseFee(16000.00);
		course.setCourseDuration(3);

		Optional<Course> optionalCourse = Optional.of(course);

		when(courseRepository.findById(100)).thenReturn(optionalCourse);

		courseService.updateCourse(course);

		verify(courseRepository, times(1)).findById(100);
		verify(courseRepository, times(1)).save(course);

	}
	

}