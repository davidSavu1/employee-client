package bmo.employeeClient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final WebClientEmployeeAdapterProperties props;

    @Autowired
    public WebClientConfig(WebClientEmployeeAdapterProperties props) {
        this.props = props;
    }

    @Bean
    @Qualifier("employeeWebClient")
    public WebClient employeeWebClient() {
        return WebClient.builder()
                .baseUrl(props.getHost() + props.getBasePath())
                .build();
    }
}
