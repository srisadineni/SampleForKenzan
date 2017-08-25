package com.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.Dog;
import com.demo.repositories.DogRepo;
import com.demo.service.DogServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class DogServiceTest {
	
	@Mock
	private DogRepo dogRepo;
	
	@InjectMocks
	private DogServiceImpl dogService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllDog(){
		List<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog(1,"Labrador",null));
		dogs.add(new Dog(2,"Pug",null));
		dogs.add(new Dog(3,"Yorkie",null));
		when(dogRepo.findAll()).thenReturn(dogs);
		
		List<Dog> result = dogService.findAllDogs();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetDogById(){
		Dog dog = new Dog(1,"Labrador","http://i.imgur.com/eE29vX4.png");
		when(dogRepo.findOne(1L)).thenReturn(dog);
		Dog result = dogService.getDog(1L);
		assertEquals(1, result.getId());
		assertEquals("Labrador", result.getBreed());
		assertEquals("http://i.imgur.com/eE29vX4.png", result.getDogPictureUrl());
	}
	
	@Test
	public void saveDog(){
		Dog dog = new Dog(8,"Pug",null);
		when(dogRepo.save(dog)).thenReturn(dog);
		Dog result = dogService.create(dog);
		assertEquals(8, result.getId());
		assertEquals("Pug", result.getBreed());
		assertEquals(null, result.getDogPictureUrl());
	}
	
	@Test
	public void removeDog(){
		Dog dog = new Dog(8,"Pug",null);
		dogService.removeDog(dog);
        verify(dogRepo, times(1)).delete(dog);
	}
	
	

}

