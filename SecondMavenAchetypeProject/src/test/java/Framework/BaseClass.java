package Framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;

public class BaseClass {
    public static void main(String args[]) {
    }

    private WebDriver driver;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);

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
//                FirefoxOptions options = new FirefoxOptions();
//                options.setBinary(new FirefoxBinary(new File("C:\\Users\\uagwo\\AppData\\Local\\Mozilla Firefox\\firefox.exe")));
//                WebDriver driver = new FirefoxDriver(options);
            }
            case "internet explorer" -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
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

    public void navigateToSite (String string){
        driver.navigate().to(string);
    }

    public void acceptCookies() {
        driver.findElement(By.cssSelector(".consent_prompt_footer #consent_prompt_submit")).click();
    }

    public void enterSearchTerm(String string){
        driver.findElement(By.cssSelector("#searchTerm")).sendKeys(string, Keys.RETURN);
    }

    public void clickEnter(String string) {
        driver.findElement(By.cssSelector(string)).sendKeys(Keys.RETURN);
    }

    public void parseSearchResults(){
        WebElement resultsGrid = driver.findElement(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1"));
        List<WebElement> products = resultsGrid.findElements(By.cssSelector("ProductCardstyles__Wrapper-h52kot-1"));
        System.out.println("The number of products is: " + products.size());
        for (WebElement product : products ) {
            WebElement chooseButton = driver.findElement(By.cssSelector(".ProductCardstyles__ButtonContainer-h52kot-8 .Buttonstyles__Button-sc-42scm2-2"));
            String chooseButtonText = chooseButton.getText();
            System.out.println(chooseButtonText);
        }
    }

    @After
    public void quit (){
        driver.quit();
    }
}