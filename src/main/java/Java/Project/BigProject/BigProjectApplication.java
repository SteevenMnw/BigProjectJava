package Java.Project.BigProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BigProjectApplication {

	private final static Logger log = LoggerFactory.getLogger(BigProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BigProjectApplication.class, args);
	}

}
