package pl.wnuk.demoapp;

import org.apache.coyote.Response;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoappApplicationTests {

	@Autowired
	TestRestTemplate httpClient;

	@LocalServerPort
	int port;

	@Test
	public void shouldReturnGreetings() {
		final String url = "http://localhost:" + port + "/hello";

		// when
		httpClient.getForEntity(url, String.class);
		ResponseEntity<String> response = httpClient.getForEntity(url, String.class);
		// wykonać request http na localhost:8080/hello

		// then
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		Assertions.assertThat(response.getBody()).isEqualTo("Hello heroku World!");
		// odpowiedz bedzie zawierala napis "hello world" i kod 200
	}

}
