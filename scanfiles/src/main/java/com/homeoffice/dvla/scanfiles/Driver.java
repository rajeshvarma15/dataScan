package com.homeoffice.dvla.scanfiles;

import com.homeoffice.dvla.scanfiles.service.VehicleDetailScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Driver implements CommandLineRunner {

	//private static String directoryPath = "/Users/Raj/Downloads/fwdbritishpassports";
	@Autowired
	private VehicleDetailScanner vehicleDetailScanner;

	public static void main(String[] args) {
		new SpringApplication(Driver.class).run(args);
	}

	@Override
	public void run(String... path) throws Exception {
	}

}
