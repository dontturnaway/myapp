package com.test.myapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.myapp.models.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class MyappApplicationTestsIntegration {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;

	@Container  //create Postgres container with appropriate credentials
	private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:15.2")
			.withDatabaseName("dev")
			.withUsername("dev")
			.withPassword("dev")
			//.withInitScript("script.sql")
			;

	@BeforeAll //bootstrap container for all test before run the tests
	public static void setUp() {
		database.withReuse(true);
		database.start();
	}

	@DynamicPropertySource //fetch container's properties, because we don't know it before it start
	public static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", database::getJdbcUrl);
		registry.add("spring.datasource.username", database::getUsername);
		registry.add("spring.datasource.password", database::getPassword);
		registry.add("spring.datasource.driver-class-name", database::getDriverClassName);
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
