package com.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String dogPictureUrl;
	private String breed;

	public Dog(long id,  String breed,String dogPictureUrl) {
		super();
		this.id = id;
		this.dogPictureUrl = dogPictureUrl;
		this.breed = breed;
	}

	public Dog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dog( String breed,String dogPictureUrl) {
		super();
		this.dogPictureUrl = dogPictureUrl;
		this.breed = breed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDogPictureUrl() {
		return dogPictureUrl;
	}

	public void setDogPictureUrl(String dogPictureUrl) {
		this.dogPictureUrl = dogPictureUrl;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

}
