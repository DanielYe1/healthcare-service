package nl.gerimedica.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AssignmentApplicationTests {
	private RestTemplate restTemplate;

	@BeforeEach
	void setUp() {
		restTemplate = new RestTemplate();
	}


}
