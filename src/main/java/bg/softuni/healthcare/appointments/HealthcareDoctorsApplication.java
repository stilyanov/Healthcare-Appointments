package bg.softuni.healthcare.appointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class HealthcareDoctorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareDoctorsApplication.class, args);
	}

}
