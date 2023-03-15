package stepDefinitions;

import Framework.BaseClass;
import Framework.ReadFrom;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SearchStepDefs extends BaseClass {
    private static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
    @Given("^the user is on the website '(.*)' using '(.*)'$")
    public void navigateToWebsite(String websiteURL, String browser) throws MalformedURLException {
        System.out.println("**** Navigating to site " + websiteURL);
        openBrowser(browser);
        navigateToSite(websiteURL);
        acceptCookies();
    }

    @When("^the user searches for a product '(.*)'$")
    public void searchForProduct(String product) {
        System.out.println("**** Searching for product");
        enterSearchTerm(product);
        clickEnter("#searchTerm");
    }

    @Then("^the search results should be displayed$")
    public void searchResults() {
        System.out.println("**** Search results are displayed");
        collectProducts();
    }

    @Test
    public void simplePropertiesFileTest(){
        assertEquals("informationFromPropertiesFile",ReadFrom.propertiesFile("data","test"));
    }
}