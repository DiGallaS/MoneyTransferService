package ru.netology.moneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoneyTransferServiceApplicationTests {
	private final static int PORT = 5500;

	@Autowired
	private TestRestTemplate restTemplate;


	public static GenericContainer<?> app = new GenericContainer<>(new ImageFromDockerfile()
			.withFileFromPath(".", Paths.get("build/libs"))
			.withDockerfileFromBuilder(builder -> builder
					.from("adoptopenjdk/openjdk11:jre-11.0.13_8-alpine")
							.expose(PORT)
									.add("MoneyTransferService-0.0.1-SNAPSHOT.jar", "mts.jar")
											.entryPoint("java", "-jar", "/mts.jar")
													.build())).withExposedPorts(PORT);

	@BeforeAll
	public static void setUp() {
		app.start();
	}

	@Test
	void testSave() {
		String cardFromNumber = "1234567812345678";
		String cardFromValidTill = "11/24";
		String cardFromCVV = "123";
		String cardToNumber = "5678123456781234";
		int value = 1000;
		String currency = "RUB";

		String jsonString = String.format("{\"cardFromNumber\": \"%s\", " +
						"\"cardFromValidTill\": \"%s\", " +
						"\"cardFromCVV\": \"%s\", " +
						"\"cardToNumber\":  \"%s\", " +
						"\"amount\": {\"value\": \"%d\", \"currency\": \"%s\" }}",
				cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, value, currency);
		final String baseUrl = String.format("http://localhost:%d/transfer", app.getMappedPort(PORT));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
		ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
		assertEquals(response.getStatusCode(), (HttpStatus.OK));
		String operationId = response.getBody();

		Assertions.assertNotNull(operationId);
		Assertions.assertNotEquals(operationId, "");
	}

	@Test
	void testConfirm() {
		String id = "12345678";
		String code = "0000";
		String jsonString = String.format("{\"operationId\": \"%s\", \"code\": \"%s\"}",
				id, code);
		final String baseUrl = String.format("http://localhost:%d/confirmOperation", app.getMappedPort(PORT));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
		ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);

		assertEquals((HttpStatus.OK), response.getStatusCode());
		String operationId = response.getBody();

		Assertions.assertNotNull(operationId);
		Assertions.assertNotEquals(operationId, "");
	}
}
