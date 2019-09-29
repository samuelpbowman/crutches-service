package hack.crutches.crutchesservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.google")
@Data
public class GoogleAPIConfiguration {

	private String googleApiKey;
}
