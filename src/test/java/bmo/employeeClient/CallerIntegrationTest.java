package bmo.employeeClient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CallerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCallToProviderIsNotFound() {
        webTestClient.get()
                .uri("/api/v1/employee/3")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testCallToProviderIsOk() {
        webTestClient.get()
                .uri("/api/v1/employee/1")
                .exchange()
                .expectStatus().isOk();
    }
}
