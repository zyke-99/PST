package com.zyke.docdiag;

import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.repository.DiagnosisRepository;
import com.zyke.docdiag.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class DocdiagApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocdiagApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DoctorRepository repository, DiagnosisRepository diag) {
		return (args) -> {
			Doctor dc = new Doctor("John Doe", "877777");
			repository.save(dc);
			repository.save(new Doctor("Mich Ore", "877777"));
			diag.save(new Diagnosis(4, "Vezis", new Date(), dc));
			System.out.println(repository.findAll());
		};
	}

}
