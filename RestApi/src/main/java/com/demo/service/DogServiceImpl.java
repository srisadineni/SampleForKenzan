package com.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Dog;
import com.demo.repositories.DogRepo;

@Service
public class DogServiceImpl implements DogService {

	@Autowired
	private DogRepo dogRepo;

	@Override
	public List<String> findAllDogPictureUrlsByBreed(String breed) {
		return dogRepo.findAllDogPictureUrlsByBreed(breed);
	}

	@Override
	public Dog create(Dog dog) {
		return dogRepo.save(dog);
	}

	@Override
	public Dog getDog(Long id) {

		return dogRepo.findOne(id);
	}

	@Override
	public Map<String, List<String>> findAllDogPictureUrls() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		dogRepo.findAll().forEach(d -> {
			if (map.get(d.getBreed()) != null) {
				map.get(d.getBreed()).add(d.getDogPictureUrl());
			} else {
				List<String> l = new ArrayList<String>();
				l.add(d.getDogPictureUrl());
				map.put(d.getBreed(), l);
			}
		});
		return map;
	}

	@Override
	public List<Dog> findAllDogs() {
		return (List<Dog>) dogRepo.findAll();
	}

	@Override
	public void removeDog(Dog dog) {
		dogRepo.delete(dog);
		
	}

}
