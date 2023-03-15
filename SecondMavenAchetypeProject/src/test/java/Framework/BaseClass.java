package Framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BaseClass extends WaitHelper {
    public static void main(String args[]) {
    }

//    public BaseClass (WaitHelper waitHelper){
//        this.waitHelper = waitHelper;
//    }
    private WebDriver driver;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
    @Getter
    protected WebDriverWait wait;
    @Getter
    protected WebDriver webDriver;

//    private WaitHelper waitHelper;
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

    public void navigateToSite (String string){
        driver.navigate().to(string);
    }

    public void acceptCookies() {
        driver.findElement(By.cssSelector(".consent_prompt_footer #consent_prompt_submit")).click();
    }

    public void enterSearchTerm(String string){
        driver.findElement(By.cssSelector("#searchTerm")).sendKeys(string, Keys.RETURN);
    }

    public void clickEnter(String selector){
        driver.findElement(By.cssSelector(selector)).click();
    }


    public void collectProducts(){
        isElementDisplayed(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1 .styles__LazyHydrateCard-sc-1rzb1sn-0 .ProductCardstyles__Title-h52kot-12"));
        List<WebElement> products = driver.findElements(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1 .styles__LazyHydrateCard-sc-1rzb1sn-0"));
        System.out.println("Number of products is: " + products.size());
        for (WebElement product : products) {
            WebElement link = product.findElement(By.cssSelector(".ProductCardstyles__Title-h52kot-12"));
            WebElement button = product.findElement(By.cssSelector(".Buttonstyles__Button-sc-42scm2-2"));

            String linkTextUrl = link.getAttribute("href");
            String buttonText = button.getText();

            System.out.println(linkTextUrl);
            System.out.println(buttonText);
        }
    }

    // TO BE IMPLEMENTED TOGETHER
    public static void customerAction(String action,By by, String... requiredText) throws Exception {
        switch (action) {
            case "click" -> {

            }
            case "enterText" -> {
            }

            case "SelectFromDropDown" -> {
                //Select select = new Select(find(by));
                //select.selectByVisibleText(requiredText[0]);
            }
            case "getText" -> {
                //find(by).getText();
            }
            case "getValue" -> {
                //find(by).getAttribute("value");
            }
        }
    }

    @After
    public void quit (){
        driver.quit();
    }
}