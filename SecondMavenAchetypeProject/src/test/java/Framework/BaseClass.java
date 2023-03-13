package Framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class BaseClass {
    public static void main(String args[]) {
    }

    private WebDriver driver;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
//    public void setUp(WebDriver driver) {
//        this.driver = driver;
//    }

//    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
//    private static final String CHROME_DRIVER_PATH = "C:\\\\Users\\\\upa01\\\\OneDrive\\\\Documents\\\\Automation\\\\SecondMavenAchetypeProject\\\\tools\\\\chromedriver1.exe";
//    private static final String FIREFOX_DRIVER = "webdriver.firefox.driver";
//    private static final String FIREFOX_DRIVER_PATH = "C:\\Users\\upa01\\OneDrive\\Documents\\Automation\\SecondMavenAchetypeProject\\tools\\geckodriver.exe";

    public void setupWebdriver() {
//        WebDriverManager.chromedriver().driverVersion("92.0.4515.43").setup();

//        driver = new ChromeDriver();
//        driver.manage().window().maximize();

//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        DesiredCapabilities cp=new DesiredCapabilities();
//        cp.setCapability(ChromeOptions.CAPABILITY, options);
//        options.merge(cp);
//        driver = new ChromeDriver(options);

        WebDriverManager.firefoxdriver().setup();

    }

    public void openBrowser(String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                DesiredCapabilities cp = new DesiredCapabilities();
                cp.setCapability(ChromeOptions.CAPABILITY, options);
                options.merge(cp);
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "internet explorer" -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
//            case "edge" -> {
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//            }
            case "remote" -> {
                //set up remote test envs
                DesiredCapabilities caps = new DesiredCapabilities();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-ssl-errors=yes");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-web-security");
                options.addArguments("--test-type");
                options.addArguments("allow-running-insecure-content");
                caps.setCapability(ChromeOptions.CAPABILITY, options);

                caps.setCapability("browser", "chrome");
                caps.setCapability("browser_version", "100");
                caps.setCapability("os", "windows");
                caps.setCapability("os_version", "11");
                caps.setCapability("seleniumVersion", "4.2.2");
                caps.setCapability("project", "BAT");
                driver = new RemoteWebDriver(new URL("https://batbsauto_IG7R8rDiiDf:vNhrigLoLtzizHEKhTti@hub-cloud.browserstack.com/wd/hub"), caps);
                driver.manage().window().maximize();
            }
        }
    }

//        private static ChromeOptions getChromeOptions() {
//            LoggingPreferences logs = new LoggingPreferences();
//            logs.enable(LogType.DRIVER, Level.OFF);
//
//            ChromeOptions chromeOptions = new ChromeOptions();
//            System.out.println("USING CHROME OPTIONS");
//            //chromeOptions.addArguments("--lang=en");
//            chromeOptions.addArguments("--ignore-ssl-errors=yes");
//            chromeOptions.addArguments("--ignore-certificate-errors");
//            chromeOptions.addArguments("--disable-web-security");
//            chromeOptions.addArguments("--test-type");
//            chromeOptions.addArguments("allow-running-insecure-content");
//            return chromeOptions;
//        }

    public void navigateToSite (String url){
        driver.navigate().to(url);
    }

    @After
    public void quit (){
        driver.quit();
    }
}