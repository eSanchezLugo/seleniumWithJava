package StepDefinitions;

import Functions.CreateDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.net.MalformedURLException;

public class Hooks {
	
		public static WebDriver driver;
		Logger log = Logger.getLogger(Hooks.class);
		Scenario scenario = null;

		@Before
		public void before(Scenario scenario) {
			this.scenario = scenario;
		}

		@Before
	    /**
	     * Delete all cookies at the start of each scenario to avoid
	     * shared state between tests
	     */
	    public void initDriver() throws MalformedURLException {
			log.info("***********************************************************************************************************");
			log.info("[Configuración] - Inicializando la configuración del controlador");
			log.info("***********************************************************************************************************");
	    	driver = CreateDriver.initConfig();
	    	
	    	log.info("***********************************************************************************************************");
			log.info("[ Scenario ] - "+ scenario.getName());
			log.info("***********************************************************************************************************");
	    }	 
	     
	 	@After
	 	/**
	     * Embed a screenshot in test report if test is marked as failed
	     */
	    public void embedScreenshot(Scenario scenario) throws IOException {

	        if(scenario.isFailed()) {
		        try {
		        	scenario.write("El escenario falló.");
		        	scenario.write("La URL de la página actual es " + driver.getCurrentUrl());
		            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		            scenario.embed(screenshot, "src/test/resources/Data/Screenshots/Failed");
		        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
		            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		        }
	        }
	        
			log.info("***********************************************************************************************************");
			log.info("[Estado del controlador]: limpia y cierra la instancia del controlador");
			log.info("***********************************************************************************************************");
	        driver.quit();
	        
	    }
}
