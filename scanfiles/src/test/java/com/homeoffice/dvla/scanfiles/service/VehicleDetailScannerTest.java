package com.homeoffice.dvla.scanfiles.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleDetailScannerTest {

	private static String directoryPath = "/Users/Raj/Downloads/REST API/DataFolder";

	@Autowired
	private VehicleDetailScanner vehicleDetailScanner;

	@Test
	public void VehicleDetailScannerTest(){
		try {
			vehicleDetailScanner.getVehicleDetails(directoryPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
