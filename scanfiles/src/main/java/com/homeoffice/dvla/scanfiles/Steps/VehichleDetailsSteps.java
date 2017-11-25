package com.homeoffice.dvla.scanfiles.Steps;

import com.homeoffice.dvla.scanfiles.pojo.VehicleDetail;
import com.homeoffice.dvla.scanfiles.service.DefaultVehicleDetailScanner;
import com.homeoffice.dvla.scanfiles.service.VehicleDetailScanner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@ContextConfiguration("classpath:cucumber.xml")
public class VehichleDetailsSteps {

    Properties props = new Properties();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private final String DriverPath="webdriver.chrome.driver";
    private final String FileScanDir ="fileScanDir.path";
    private final String TestWebUrl = "testwebsite.url";

    WebDriver driver = new ChromeDriver();

    {
        try {
            props.load(new FileInputStream("application.properties"));
            System.setProperty(DriverPath, props.getProperty(DriverPath));
        }catch (Exception ex){

        }
    }

    @Autowired
    private VehicleDetailScanner vehicleDetailScanner = new DefaultVehicleDetailScanner();

    @Given("^I open vehicle details page$")
    public void iOpenVehicleDetailsPage() throws Throwable {
        driver.get(props.getProperty(TestWebUrl));
    }

    @When("^I am navigated to the vehicle details page$")
    public void iAmNavigatedToTheVehicleDetailsPage() throws Throwable {
        driver.findElement(By.xpath("//p[@id='get-started']/a")).click();
    }

    @Then("^the vehicle details are validated$")
    public void theVehicleDetailsAreValidated() throws Throwable {
        String directoryPath = props.getProperty(FileScanDir);
            List<VehicleDetail> vehicles = vehicleDetailScanner.getVehicleDetails(directoryPath);
            for (VehicleDetail vehicle : vehicles) {
                try {
                driver.findElement(By.id("Vrm")).sendKeys(vehicle.getVehicleRegNum());
                driver.findElement(By.name("Continue")).click();


                String make = driver.findElement(By.xpath("//span[text()='Make']//following::span[1]")).getText();
                String color = driver.findElement(By.xpath("//span[text()='Colour']//following::span[1]")).getText();

                logger.info("Vehicle Validated!" + vehicle + ", make:" + make);



                Assert.assertTrue(vehicle.getVehicleMake().equals(make) && vehicle.getVehicleColor().equals(color));

                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File("screenshots/Pass-" + vehicle.getVehicleRegNum() + ""));
                driver.navigate().back();

                } catch(Throwable ex){
                    ex.printStackTrace();
                    logger.warning(ex.getMessage());

                    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(src, new File("screenshots/Fail-" + vehicle.getVehicleRegNum() + ""));
                    driver.navigate().back();
                }
            }
            driver.quit();
    }
}
