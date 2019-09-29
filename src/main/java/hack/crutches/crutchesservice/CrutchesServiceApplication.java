package hack.crutches.crutchesservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main class of our web service application
 *
 * @author Samuel P. Bowman
 */
@SpringBootApplication
@EnableMongoRepositories
public class CrutchesServiceApplication {

	/**
	 * The main method which launches the Spring framework.
	 * @param args command line args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CrutchesServiceApplication.class, args);
	}
}
