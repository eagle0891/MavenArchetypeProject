package stepDefinitions;

import Framework.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;


public class SearchStepDefs extends BaseClass {
    private static final Logger LOG = LoggerFactory.getLogger(BaseClass.class);
    @Given("^the user is on the website '(.*)' using '(.*)'$")
    public void navigateToWebsite(String websiteURL, String browser) throws MalformedURLException {
        System.out.println("**** Navigating to site " + websiteURL);
        openBrowser(browser);
        navigateToSite(websiteURL);
    }

    @When("^the user searches for a product$")
    public void searchForProduct() {
        System.out.println("**** Searching for product");
    }

    @Then("^the search results should be displayed$")
    public void searchResults() {
        System.out.println("**** Search results are displayed");
    }
}
