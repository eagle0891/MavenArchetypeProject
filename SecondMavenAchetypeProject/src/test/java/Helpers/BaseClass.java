package Helpers;

import Models.Product;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BaseClass extends WaitHelper {
    public static void main(String args[]) {
    }

//    public BaseClass (WaitHelper waitHelper){
//        this.waitHelper = waitHelper;
//    }
protected WebDriver driver;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
    @Getter
    protected WebDriverWait wait;
//    @Getter
//    protected WebDriver webDriver;
    public Product product;

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

    private static final By ACCEPT_TERMS_AND_CONDITIONS = By.cssSelector(ReadFrom.propertiesFile("css","acceptTermsAndConditions"));

    public void acceptCookies() {
        driver.findElement(By.cssSelector("#sp-cc-accept")).click(); //.consent_prompt_footer #consent_prompt_submit
    }

    public void enterSearchTerm(String string){
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(string, Keys.RETURN); //#searchTerm
    }

    public void clickEnter(String selector){
        driver.findElement(By.cssSelector(selector)).click();
    }


    public void collectProducts(){
        isElementDisplayed(By.cssSelector(".styles__ProductList-sc-1rzb1sn-1 .styles__LazyHydrateCard-sc-1rzb1sn-0 .ProductCardstyles__Title-h52kot-12"));
        String css = driver.findElement(By.cssSelector(".s-result-list")).getAttribute("class");
        System.out.println("CSS is: " + css);
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

    private static final By SEARCH_RESULTS_LIST = By.cssSelector(ReadFrom.propertiesFile("css", "searchResultsList"));
    private static final By SEARCH_RESULT_ITEM = By.cssSelector(ReadFrom.propertiesFile("css", "searchResultItem"));
    public void collectProductsTwo() throws InterruptedException {
        String css = driver.findElement(SEARCH_RESULTS_LIST).getAttribute("class");
        System.out.println("Search result CSS is: " + css);
        waitForElementToDisplay( driver.findElement(SEARCH_RESULTS_LIST), 10);
        WebElement searchResultsList = driver.findElement(SEARCH_RESULTS_LIST);
        //(driver.findElement(SEARCH_RESULT_ITEM).getAttribute("data-asin") != null)
        List<WebElement> products = searchResultsList.findElements(SEARCH_RESULT_ITEM);
        System.out.println("Number of products is: " + products.size());
        for (WebElement product : products) {
            if (product.findElement(By.cssSelector(".a-price-whole")).getText().length()>1) {
                String productName = product.findElement(By.cssSelector("span.a-text-normal")).getText();
                String productWholePricePart = product.findElement(By.cssSelector(".a-price-whole")).getText();
                String productDecimalPricePart = product.findElement(By.cssSelector(".a-price-fraction")).getText();
//                String productBrandName = product.findElement(By.cssSelector("h5.s-line-clamp-1 .a-size-base-plus.a-color-base")).getText();
//                System.out.println("Brand is: " + productBrandName);
                System.out.println("Product title is: " + productName);
                System.out.println("Product Price is: £" + productWholePricePart + "." + productDecimalPricePart);
            }
//            throw new NoSuchElementException("Element does not contain a product");
        }
    }

    public ArrayList<Product> collectProductsThree() throws InterruptedException {
        Product.ProductCollection = new ArrayList<>();
        String css = driver.findElement(SEARCH_RESULTS_LIST).getAttribute("class");
        System.out.println("Search result CSS is: " + css);
        waitForElementToDisplay(driver.findElement(SEARCH_RESULTS_LIST), 10);
        WebElement searchResultsList = driver.findElement(SEARCH_RESULTS_LIST);
        List<WebElement> products = searchResultsList.findElements(SEARCH_RESULT_ITEM);
        System.out.println("Number of products is: " + products.size());
        for (WebElement product : products) {
            try {
                String productName = product.findElement(By.cssSelector("span.a-text-normal")).getText();
                String productWholePricePart = product.findElement(By.cssSelector(".a-price-whole")).getText();
                String productDecimalPricePart = product.findElement(By.cssSelector(".a-price-fraction")).getText();
                WebElement produtImageLink = product.findElement(By.cssSelector(".s-image"));
                // String productBrandName = product.findElement(By.cssSelector("h5.s-line-clamp-1 .a-size-base-plus.a-color-base")).getText();
                // System.out.println("Brand is: " + productBrandName);
                System.out.println("Product title is: " + productName);
                System.out.println("Product Price is: £" + productWholePricePart + "." + productDecimalPricePart);
                boolean isSamsung = productName.contains("Samsung");
                WebElement samsungSelection = null;
                if (isSamsung) {
                    samsungSelection = product.findElement(By.cssSelector("span.a-text-normal"));
                }
                boolean isCasio = productName.contains("Casio");
                WebElement casioSelection = null;
                if (isCasio) {
                    casioSelection = product.findElement(By.cssSelector("span.a-text-normal"));
                }
                Product.ProductCollection.add(new Product(productName, productWholePricePart, productDecimalPricePart, isSamsung, product, samsungSelection, isCasio, casioSelection, produtImageLink));
            } catch (NoSuchElementException e) {
                LOG.info("**** Element does not contain a product ****");
            }
        }
        return Product.ProductCollection;
    }

    public void findProductType(String productType) throws Exception {
        //clearProductCollectionIfPopulated();
        outer: for (Product product : Product.ProductCollection) {
            switch(productType){
                case "samsung":
                    if (product.isSamsung()){
                        System.out.println("*** Samsung switch statement being executed ***");
                        LOG.info("** SAMSUNG PRODUCT FOUND **");
                        product.display();
                        waitForElementToBeClickable(driver, product.getProductImageLink(), Duration.ofSeconds(10));
//                        Thread.sleep(3000);
//                        product.getProductImageLink().click();
                        LOG.info("** SAMSUNG PDP SHOULD BE DISPLAYED **");
                        break outer;
                    }
                    break;
                case "casio":
                    if (product.isCasio()){
                        System.out.println("*** Casio switch statement being executed ***");
                        LOG.info("** CASIO PRODUCT FOUND **");
                        product.display();
                        waitForElementToBeClickable(driver, product.getProductImageLink(), Duration.ofSeconds(10));
//                        Thread.sleep(3000);
//                        product.getProductImageLink().click();
                        customerAction("click", product.getProductImageLink());
                        LOG.info("** CASIO PDP SHOULD BE DISPLAYED **");
                        break outer;
                    }
                    break;
                default:
                    LOG.info("ERROR : Product type not recognised, please select a valid product type.");
            }
        }
    }

    public void confirmPdpPageIsDisplayed(){
        assertTrue(driver.findElement(By.cssSelector("#breadcrumb-back-link")).isDisplayed());
    }

//    @After
     //TO BE IMPLEMENTED TOGETHER
    public void customerAction(String action, WebElement element, String... requiredText) throws Exception {
        switch (action) {
            case "click" -> {
                element.click();
            }
            case "enterText" -> {
                element.sendKeys(requiredText);
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

    //@After
    public void quit (){
        driver.quit();
    }
}