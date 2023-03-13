package Framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass {
    public static void main (String args[]) {
    }

    private WebDriver driver;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
//    public void setUp(WebDriver driver) {
//        this.driver = driver;
//    }

    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_PATH = "C:\\\\Users\\\\upa01\\\\OneDrive\\\\Documents\\\\Automation\\\\SecondMavenAchetypeProject\\\\tools\\\\chromedriver1.exe";
    private static final String FIREFOX_DRIVER = "webdriver.firefox.driver";
    private static final String FIREFOX_DRIVER_PATH = "C:\\\\Users\\\\upa01\\\\OneDrive\\\\Documents\\\\Automation\\\\SecondMavenAchetypeProject\\\\tools\\\\geckodriver.exe";

    public void setupWebdriver() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.argos.co.uk");
    }

//    public void openBrowser (String browser) {
//        switch (browser) {
//            case "chrome" -> {
////                System.setProperty(CHROME_DRIVER, CHROME_DRIVER_PATH);
////                // ChromeOptions co = new ChromeOptions();
////                // co.addArguments("--remote-allow-origins=*");
////                WebDriver driver = new ChromeDriver();
//                WebDriverManager.chromedriver().setup();
//            }
//            case "firefox" -> {
////                System.setProperty(FIREFOX_DRIVER, FIREFOX_DRIVER_PATH);
////                WebDriver driver = new FirefoxDriver();
//                WebDriverManager.firefoxdriver().setup();
//            }
//        }
//    }
//
//    public void navigateToSite(String url) {
//        driver.navigate().to(url);
//    }
}
