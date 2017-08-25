package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.domain.Dog;

public interface DogService {
	
	List<String> findAllDogPictureUrlsByBreed(String breed); 
	
	List<Dog> findAllDogs();
	
	Map<String,List<String>> findAllDogPictureUrls(); 
	
	Dog create(Dog dog);
	
	Dog getDog(Long id);

	void removeDog(Dog dog);
}
