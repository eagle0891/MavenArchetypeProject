package stepDefinitions;

import Helpers.BaseClass;
import Helpers.ReadFrom;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;
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
    }

    @Then("^the search results should be displayed$")
    public void searchResults() throws InterruptedException {
        System.out.println("**** Search results are displayed");
        collectProductsThree();
    }

    @Test
    public void simplePropertiesFileTest(){
        assertEquals("cssFromPropertiesFile",ReadFrom.propertiesFile("css","test"));
    }

    @When("^I click on the product '(.*)'$")
    public void selectTheProduct(String productType) throws Exception {
        System.out.println("Product type provided by the test is: " + productType);
        findProductType(productType);
    }

    @Then("^the PDP should be displayed$")
    public void pdpShouldBeDisplayed() {
        confirmPdpPageIsDisplayed();
    }
}