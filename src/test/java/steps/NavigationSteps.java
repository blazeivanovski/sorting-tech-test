package steps;

import core.TestContext;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.HomePage;
import com.microsoft.playwright.Locator;

public class NavigationSteps {

    private HomePage homePage;

    @Given("User opens Craigslist Madrid")
    public void open_site() {
        homePage = new HomePage(TestContext.page);
        homePage.open();
    }

    @When("User selects English language")
    public void select_english() {
        homePage.selectEnglish();
    }

    @When("User navigates to Housing section")
    public void go_to_housing() {
        homePage.goToHousing();
    }

    @Given("User is on Housing page")
    public void user_on_housing() {
        Locator heading = TestContext.page.locator("h1");
        heading.waitFor();
        Assert.assertTrue(heading.innerText().toLowerCase().contains("housing"));
    }
}