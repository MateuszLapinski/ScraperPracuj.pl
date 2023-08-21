package com.example.Job.Offer.Analyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

@SpringBootApplication
public class JobOfferAnalystApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JobOfferAnalystApplication.class, args);

		SaveAndOpen saveAndOpen= new SaveAndOpen();
		String results=saveAndOpen.connectWithProvidedPages();
		saveAndOpen.appendToExcel(results);



	}
}
