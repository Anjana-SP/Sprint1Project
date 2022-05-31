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

import com.edusystem.entity.Trainer;
import com.edusystem.exception.TrainerNotFoundException;
import com.edusystem.repository.TrainerRepository;


@SpringBootTest
public class TrainerServiceTest {
	
	@InjectMocks
	private TrainerService trainerService = new TrainerServiceImpl(); 
	
	@Mock
	private TrainerRepository trainerRepository;
	
	@Test
	public void testTrainerById() {
		
		Trainer trainer = new Trainer();
		trainer.setTrainerId(100);
		trainer.setTrainerName("Akhila");
		trainer.setTrainerEmail("akhila@gmail.com");
		trainer.setTrainerExperience(4);
		trainer.setTrainerSkillset("Python");
		trainer.setTrainerMobile(987654321);
	
      Optional<Trainer> optionalTrainer = Optional.of(trainer);
		
		when(trainerRepository.findById(100)).thenReturn(optionalTrainer);
		
		Trainer myTrainer = trainerService.getTrainerById(100);
		
		assertEquals("Akhila", myTrainer.getTrainerName());
	}
	
	@Test
	public void testGetTrainerByIdWithException() {
		
		when(trainerRepository.findById(100)).thenThrow(TrainerNotFoundException.class);
		
		assertThrows(TrainerNotFoundException.class,()->trainerService.getTrainerById(100));
	}
	
	@Test
	public void testGetAllTrainers() {
		
		Trainer trainer = new Trainer();
		trainer.setTrainerId(105);
		trainer.setTrainerName("Ram");
		trainer.setTrainerEmail("ram@gmail.com");
		trainer.setTrainerExperience(4);
		trainer.setTrainerSkillset("Python");
		trainer.setTrainerMobile(987654377);
		
		Trainer trainer1 = new Trainer();
		trainer1.setTrainerId(106);
		trainer1.setTrainerName("Raman");
		trainer1.setTrainerEmail("raman@gmail.com");
		trainer1.setTrainerExperience(4);
		trainer1.setTrainerSkillset("Java");
		trainer1.setTrainerMobile(987654444);


		
		List<Trainer> trainerList = new ArrayList<>();
		trainerList.add(trainer1);
		trainerList.add(trainer);
		
		when(trainerRepository.findAll()).thenReturn(trainerList);
		
		List<Trainer> trainers = trainerService.getAllTrainers();
		
		assertEquals(2,trainers.size());		
	
	}
	
	@Test
	public void testSaveTrainer() {
		
		Trainer trainer = new Trainer();
		trainer.setTrainerId(100);
		trainer.setTrainerName("Akhila");
		trainer.setTrainerEmail("akhila@gmail.com");
		trainer.setTrainerExperience(4);
		trainer.setTrainerSkillset("Python");
		trainer.setTrainerMobile(987654551);
		
when(trainerRepository.save(trainer)).thenReturn(trainer);
		
Trainer newTrainer = trainerService.saveTrainer(trainer);
		
		assertEquals("Akhila",newTrainer.getTrainerName());
		
		verify(trainerRepository,times(1)).save(trainer);
		
	}
	
	@Test
	public void testDeleteTrainer() {
		
		Trainer trainer = new Trainer();
		trainer.setTrainerId(100);
		trainer.setTrainerName("Akhila");
		trainer.setTrainerEmail("akhila@gmail.com");
		trainer.setTrainerExperience(4);
		trainer.setTrainerSkillset("Python");
		trainer.setTrainerMobile(987654321);
		
		
		Optional<Trainer> optionalTrainer = Optional.of(trainer);
		
		when(trainerRepository.findById(100)).thenReturn(optionalTrainer);
;
		
		trainerService.deleteTrainer(100);
		
		verify(trainerRepository,times(1)).findById(100);
		verify(trainerRepository,times(1)).deleteById(100);
		
	}
	
	@Test
	void testUpdateTrainer() {

		Trainer trainer = new Trainer();
		trainer.setTrainerId(100);
	    trainer.setTrainerEmail("akhila@gmail.com");
	    trainer.setTrainerName("akhila");
	    trainer.setTrainerExperience(6);
	    trainer.setTrainerMobile(876543218);
	    trainer.setTrainerSkillset("java");

		Optional<Trainer> optionalTrainer = Optional.of(trainer);

		when(trainerRepository.findById(100)).thenReturn(optionalTrainer);

		trainerService.updateTrainer(trainer);

		verify(trainerRepository, times(1)).findById(100);
		verify(trainerRepository, times(1)).save(trainer);

	}

}