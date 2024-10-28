package com.example.SpringBootCrud;

import com.example.SpringBootCrud.Entity.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootCrudApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	@Autowired
	private TestH2Repository repository;

	private static RestTemplate restTemplate;

	@BeforeAll
	public static void init(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/student");
	}

	@Test
	public void testAddStudent(){
		Student student = new Student(1, "Test1", 25);
		Student response = restTemplate.postForObject(baseUrl, student, Student.class);
		assertEquals(1, repository.findAll().size());
		assertEquals(25, response.getAge());
		repository.deleteAll();
	}

	@Test
	@Sql(statements = "INSERT INTO student (id, name, age) values (2, 'Test 2', 30)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM student WHERE id=2", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetProducts(){
		List<Student> studentList = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(1, studentList.size());
		assertEquals(1, repository.findAll().size());
	}

	@Test
	@Sql(statements = "INSERT INTO student (id, name, age) values (3, 'Test 3', 30)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM student WHERE id=3", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetProductById(){
		Student student = restTemplate.getForObject(baseUrl+"/{id}", Student.class, 3);
		assertAll(
				() -> assertNotNull(student),
				() -> assertEquals(3, student.getId()),
				() -> assertEquals("Test 3", student.getName())
		);
	}

	@Test
	@Sql(statements = "INSERT INTO student (id, name, age) values (4, 'Test 4', 30)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM student WHERE id=4", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateProductById(){
		Student updateStudent = new Student(4, "Test 4", 31);
		restTemplate.put(baseUrl+"/{id}", updateStudent, updateStudent.getId());
		Student updatedStudent = repository.findById(updateStudent.getId()).get();
		assertAll(
				() -> assertNotNull(updatedStudent),
				() -> assertEquals(4, updatedStudent.getId()),
				() -> assertEquals("Test 4", updatedStudent.getName()),
				() -> assertEquals(31, updatedStudent.getAge())
		);
	}

	@Test
	@Sql(statements = "INSERT INTO student (id, name, age) values (5, 'Test 5', 30)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM student WHERE id=5", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void DeleteProductById(){
		restTemplate.delete(baseUrl+"/{id}", 5);
		assertEquals(0, repository.findAll().size());
	}

}
