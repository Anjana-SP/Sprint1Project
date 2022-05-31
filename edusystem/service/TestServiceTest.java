package com.edusystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.edusystem.entity.TestEntity;
import com.edusystem.repository.TestRepository;

@SpringBootTest
public class TestServiceTest {

	@InjectMocks
	private TestService testService = new TestServiceImpl();
	
	@Mock
	private TestRepository testRepository;
	
	@Test
	public void TestById() {
		
		TestEntity test = new TestEntity();
		
		test.setTestId(1);
		test.setTestName("Java");	
		test.setTestType("MCQ");
		test.setMaxMarks(100);
		test.setDate(LocalDate.now());
		test.setPassPercentage("60");
		
		
	
      Optional<TestEntity> optionalTestEntity = Optional.of(test);
		
		when(testRepository.findById(1)).thenReturn(optionalTestEntity);
		
		TestEntity myTestEntity = testService.getTestById(1);
		
		assertEquals("Java", myTestEntity.getTestName());
	}
	
	@Test
	public void testGetAllTestEntity() {
		
		TestEntity testEntity = new TestEntity();
		testEntity.setTestId(1);
		testEntity.setTestName("Java");
		testEntity.setTestType("MCQ");
		testEntity.setMaxMarks(100);
		testEntity.setDate(LocalDate.now());
		testEntity.setPassPercentage("60");
		
		TestEntity testEntity1 = new TestEntity();
		testEntity1.setTestId(2);
		testEntity1.setTestName("Java");
		testEntity1.setTestType("Coding");
		testEntity1.setMaxMarks(100);
		testEntity1.setDate(LocalDate.now());
		testEntity1.setPassPercentage("60");

		
		List<TestEntity> testEntityList = new ArrayList<>();
		testEntityList.add(testEntity);
		testEntityList.add(testEntity1);
		
		when(testRepository.findAll()).thenReturn(testEntityList);
		
		List<TestEntity> testEntityList1 = testService.getAllTests();
		
		assertEquals(2,testEntityList.size());		
	
	}
	
	@Test
	public void testEntitySave() {
		
		TestEntity testEntity = new TestEntity();
		testEntity.setTestId(1);
		testEntity.setTestName("Java");
		testEntity.setTestType("MCQ");
		testEntity.setMaxMarks(100);
		testEntity.setDate(LocalDate.now());
		testEntity.setPassPercentage("60");
		
		
when(testRepository.save(testEntity)).thenReturn(testEntity);
		
TestEntity newTestEntity = testService.saveTest(testEntity);
		
		assertEquals(1,newTestEntity.getTestId());
		
		verify(testRepository,times(1)).save(testEntity);
		
	}
	
	@Test
	public void testDeleteTestEntity() {
		
		TestEntity testEntity = new TestEntity();
		testEntity.setTestId(1);
		testEntity.setTestName("Java");
		testEntity.setTestType("MCQ");
		testEntity.setMaxMarks(100);
		testEntity.setDate(LocalDate.now());
		testEntity.setPassPercentage("60");
		
		Optional<TestEntity> optionalTestEntity = Optional.of(testEntity);
		
		when(testRepository.findById(1)).thenReturn(optionalTestEntity);
;
		
testService.deleteTest(1);
		
		verify(testRepository,times(1)).findById(1);
		verify(testRepository,times(1)).deleteById(1);
		
	}
	
	@Test
	void testUpdateCourse() {
		TestEntity testEntity = new TestEntity();
		testEntity.setTestId(1);
		testEntity.setTestName("Java");
		testEntity.setTestType("MCQ");
		testEntity.setMaxMarks(100);
		testEntity.setDate(LocalDate.now());
		testEntity.setPassPercentage("80");

		Optional<TestEntity> optionalTestEntity = Optional.of(testEntity);

		when(testRepository.findById(1)).thenReturn(optionalTestEntity);

		testService.updateTest(testEntity);

		verify(testRepository, times(1)).findById(1);
		verify(testRepository, times(1)).save(testEntity);

	}
	

}
