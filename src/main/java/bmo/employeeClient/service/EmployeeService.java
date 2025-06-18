package bmo.employeeClient.service;

import bmo.employeeClient.dto.Employee;
import bmo.employeeClient.exception.ClientErrorException;
import bmo.employeeClient.exception.EmployeeNotFoundException;
import bmo.employeeClient.exception.ProviderUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    private final WebClient webClient;

    @Autowired
    public EmployeeService(@Qualifier("employeeWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Employee> getEmployeeById(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, this::handle4xxError)
                .onStatus(HttpStatusCode::is5xxServerError, this::handle5xxError)
                .bodyToMono(Employee.class)
                .onErrorResume(WebClientRequestException.class,
                        ex -> Mono.error(new ProviderUnavailableException("Employee provider service is unreachable. Please try again later.")));
    }

    private Mono<? extends Throwable> handle4xxError(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(message -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new EmployeeNotFoundException("Employee adapter app response: " + message));
                    } else {
                        return Mono.error(new ClientErrorException("Client error: " + message));
                    }
                });
    }

    private Mono<? extends Throwable> handle5xxError(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(message -> Mono.error(new Exception("Server error: " + message)));
    }
}
