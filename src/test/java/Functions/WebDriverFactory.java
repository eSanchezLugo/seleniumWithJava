package Functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class WebDriverFactory {
	static String resourceFolder="src/test/java/Software/";
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(WebDriverFactory.class);
    
	private static WebDriverFactory instance = null;
	    
    private WebDriverFactory() {    
    }
    
    /**
     * Singleton pattern
     * @return a single instance
     */
    public static WebDriverFactory getInstance() {
        if (instance == null) {
            instance = new WebDriverFactory();
        }
        return instance;
    }    
	
		
	 public static WebDriver createNewWebDriver(String browser, String os){
		 WebDriver driver;

		 /******** The driver selected is Local: Firefox  ********/
		 if ("FIREFOX".equalsIgnoreCase(browser)) {
			 if("WINDOWS".equalsIgnoreCase(os)){
				 //System.setProperty("webdriver.gecko.driver", resourceFolder+os+"/geckodriver.exe");
				 WebDriverManager.firefoxdriver().setup();
			 }
			 else{
				 System.setProperty("webdriver.gecko.driver", resourceFolder+os+"/geckodriver");
			 }
		     driver = new FirefoxDriver();
		 }

		 /******** The driver selected is Chrome  ********/

	     else if ("CHROME".equalsIgnoreCase(browser)) {
	    	 if("WINDOWS".equalsIgnoreCase(os)){
	    		 //System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver.exe");
				 WebDriverManager.chromedriver().setup();
	    	 }
	    	 else{
	    		 System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver");
	    	 }
	         driver = new ChromeDriver();

	     }

		 /******** The driver selected is Opera  ********/

		 else if ("OPERA".equalsIgnoreCase(browser)) {
			 if("WINDOWS".equalsIgnoreCase(os)){
				 //System.setProperty("webdriver.operadriver.driver", resourceFolder+os+"/operadriver.exe");
				 WebDriverManager.operadriver().setup();
			 }
			 else{
				 System.setProperty("webdriver.operadriver.driver", resourceFolder+os+"/operadriver");
			 }
			 driver = new ChromeDriver();

		 }


		 /******** The driver is not selected  ********/
	     else {
	    	 log.error("The Driver is not selected properly, invalid name: " + browser + ", " + os);
			 return null;
		 }
		 driver.manage().window().maximize();
	     return driver;

        }
	}
