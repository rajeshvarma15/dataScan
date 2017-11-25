package com.homeoffice.dvla.scanfiles.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = "pretty",
        tags = {"~@Ignore"},
        features = "src/main/resources/ScenarioFiles/testVehicleData"  //refer to Feature file
)
public class TestVehicleData {  }