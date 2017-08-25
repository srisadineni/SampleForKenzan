package com.demo.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DogControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllDogs() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getDogDetails").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	
	@Test
	public void verifyDogById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getDogDetails/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.breed").exists())
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.breed").value("Retriever"));
	}
	
	@Test
	public void verifyInvalidDogArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getDogDetails/f").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void verifyInvalidDogId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getDogDetails/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void verifyNullDog() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getDogDetails/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void verifySaveDog() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/createdog")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"dogPictureUrl\" : \"abc.png\", \"breed\" : \"Pug\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.id").value(5));
	}
	
	@Test
	public void verifyRemoveInvalidDog() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/removeDog/f").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	
	@Test
	public void verifyRemoveDog() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/removeDog/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	
	
	
}
