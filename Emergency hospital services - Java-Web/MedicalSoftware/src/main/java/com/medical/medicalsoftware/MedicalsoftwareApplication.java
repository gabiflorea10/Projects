package com.medical.medicalsoftware;

import com.medical.medicalsoftware.repository.PatientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class MedicalsoftwareApplication {
	public static void main(String[] args) {
		SpringApplication.run(MedicalsoftwareApplication.class, args);
	}
}
