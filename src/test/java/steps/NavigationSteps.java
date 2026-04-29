package steps;

import core.TestContext;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.HomePage;
import com.microsoft.playwright.Locator;

public class NavigationSteps {

    private final HomePage homePage = new HomePage(TestContext.page);

    @Given("User visits the Craigslist home page")
    public void navigateHomePage() {
        homePage.navigateCraigslist();
    }

    @When("User selects English language")
    public void selectLanguage() {
        homePage.selectEnglish();
    }

    @When("User navigates to Housing section")
    public void selectHousing() {
        homePage.clickHousing();
    }

    @Given("User is on Housing page")
    public void userOnHousing() {
        Locator heading = TestContext.page.locator("h1");
        heading.waitFor();
        Assert.assertTrue(heading.innerText().toLowerCase().contains("housing"));
    }
}