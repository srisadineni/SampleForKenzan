package com.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Dog;
import com.demo.service.DogService;

@RestController
public class DogController {

	@Autowired
	private DogService dogService;

	
	@RequestMapping(value = "/createdog", method = RequestMethod.POST)
	public @ResponseBody Dog createNewDog(Dog dog) {
		return dogService.create(dog);

	}

	@RequestMapping(value = "/getDogDetails/{id}", method = RequestMethod.GET)
	public @ResponseBody Dog getDogDetails(@PathVariable Long id) {
		return dogService.getDog(id);
	}

	@RequestMapping(value = "/getDogDetails", method = RequestMethod.GET)
	public @ResponseBody List<Dog> getDogDetails() {
		return dogService.findAllDogs();
	}
	
	@RequestMapping(value = "/removeDog/{id}", method = RequestMethod.DELETE)
	public void removeDog(@PathVariable Long id) {
		 dogService.removeDog(dogService.getDog(id));
	}

}
