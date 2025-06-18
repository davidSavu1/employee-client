package bmo.employeeClient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "webclient.employee.adapter")
public class WebClientEmployeeAdapterProperties {
    private String host;
    private String basePath;
}
