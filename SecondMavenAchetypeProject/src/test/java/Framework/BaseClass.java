package Framework;

import io.cucumber.messages.types.Product;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class BaseClass {
    public static void main(String args[]) {
    }

    private WebDriver driver;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
    @Getter
    protected WebDriverWait wait;
    @Getter
    protected WebDriver webDriver;

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

    public void clickEnter(String string) {
        driver.findElement(By.cssSelector(string)).sendKeys(Keys.RETURN);
    }

    public WebElement waitForExpectedElement(final By by, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeout);
        try {
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        } catch (StaleElementReferenceException e) {
            LOG.info(e.getMessage());
            return wait.until(visibilityOfElementLocated(by));
        }
    }

    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOG.error("Error Message : " + e.getMessage());
            }
            WebElement element = getWebDriver().findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }

    public void isElementDisplayed(By bySelector) {
        boolean expectedElementDisplayed = false;
        try {
            //highlightElement(bySelector,1);
            expectedElementDisplayed = waitForExpectedElement(bySelector, Duration.ofMillis(50)).isDisplayed();
        } catch (Exception e) {
            LOG.info("\nPresence of Element : " + expectedElementDisplayed);
        }

        assertTrue("\n\n ******** ERROR *********** \n" +
                        "\n EXPECTED ELEMENT NOT DISPLAYED!! " +
                        "\n Element NOT Found : " + bySelector +"\n",
                expectedElementDisplayed);
    }

    public void collectProducts(){
          isElementDisplayed(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1 .styles__LazyHydrateCard-sc-1rzb1sn-0 .ProductCardstyles__Title-h52kot-12"));
//        List<WebElement> products = driver.findElements(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1 .styles__LazyHydrateCard-sc-1rzb1sn-0"));
//        System.out.println("Number of products is: " + products.size());
//        for (WebElement product : products) {
//            WebElement link = product.findElement(By.cssSelector(".ProductCardstyles__Title-h52kot-12"));
//            WebElement button = product.findElement(By.cssSelector(".Buttonstyles__Button-sc-42scm2-2"));
//
//            String linkTextUrl = link.getAttribute("href");
//            String buttonText = button.getText();
//
//            System.out.println(linkTextUrl);
//            System.out.println(buttonText);
//        }
    }

    @After
    public void quit (){
        driver.quit();
    }
}