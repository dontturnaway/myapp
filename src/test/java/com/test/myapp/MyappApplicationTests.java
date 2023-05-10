package com.test.myapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.test.myapp.models.Person;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MyappApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllPersons() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/peoplerest"))
				.andExpect(status().isOk());
	}

	@Test
	void getPersonById() throws Exception {
		int existingId = 1;
		mockMvc.perform(MockMvcRequestBuilders.get("/peoplerest/"+existingId))
				.andExpect(status().isOk());
	}

	@Test
	void createPersonPositive() throws Exception {
		Person testPerson = new Person();
		testPerson.setUsername("MOCK_USER");
		testPerson.setPassword("123");
		testPerson.setRole("ROLE_USER");
		testPerson.setYearOfBirth(1901);
		mockMvc.perform(MockMvcRequestBuilders.post("/peoplerest")
				.content(mapper.writeValueAsString(testPerson))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}


	@Test
	void createPersonNegative() throws Exception {
		Person emptyTestPerson = new Person();
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/peoplerest")
				.content(mapper.writeValueAsString(emptyTestPerson))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@ParameterizedTest
	@ValueSource(strings = {"Vasya", "Petya"})
	void createPersonValues(String names) throws Exception {
		Person emptyTestPerson = new Person();
		emptyTestPerson.setUsername(names);
		mockMvc.perform(MockMvcRequestBuilders.post("/peoplerest")
						.content(mapper.writeValueAsString(emptyTestPerson))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}


}
