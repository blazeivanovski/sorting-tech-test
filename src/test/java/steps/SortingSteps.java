package steps;

import com.microsoft.playwright.Locator;
import core.TestContext;
import io.cucumber.java.en.*;
import model.SortOption;
import pages.HousingPage;

import org.junit.Assert;

import java.util.List;

import static utils.AllureUtils.attachText;

public class SortingSteps {

    private final HousingPage housingPage = new HousingPage(TestContext.page);

    @Then("Default sorting options should be available")
    public void default_sorting() {
        TestContext.page.locator(".cl-search-sort-mode.bd-combo-box").click();
        Locator dropdown = TestContext.page.locator(".bd-list-box");
        dropdown.waitFor();

        Assert.assertTrue(dropdown.locator("text=£ → £££").isVisible());
        Assert.assertTrue(dropdown.locator("text=£££ → £").isVisible());
        Assert.assertTrue(dropdown.locator("text=newest").isVisible());
    }

    @When("User performs a search for {string}")
    public void search(String query) {
        housingPage.search(query);
    }

    @Then("Extended sorting options should be available")
    public void extended_sorting() {
        TestContext.page.locator(".cl-search-sort-mode.bd-combo-box").click();
        Locator dropdown = TestContext.page.locator(".bd-list-box");
        dropdown.waitFor();

        Assert.assertTrue(dropdown.locator("text=£ → £££").isVisible());
        Assert.assertTrue(dropdown.locator("text=£££ → £").isVisible());
        Assert.assertTrue(dropdown.locator("text=newest").isVisible());
        Assert.assertTrue(dropdown.locator("text=upcoming").isVisible());
        Assert.assertTrue(dropdown.locator("text=relevance").isVisible());
    }

    @When("User sorts results by {word}")
    public void sort_by(String optionText) {
        SortOption option = SortOption.valueOf(optionText);
        housingPage.selectSortOption(option);
        housingPage.waitForPricesToLoad();
    }

    @Then("Results should be sorted by price ascending")
    public void verifyAscending() {
        List<Integer> prices = housingPage.getPrices();
        attachText("Prices - ASC check", prices.toString());

        Assert.assertTrue(
                "Prices not sorted ascending: " + prices,
                housingPage.isSortedAscending(prices)
        );
    }

    @Then("Results should be sorted by price descending")
    public void verifyDescending() {
        List<Integer> prices = housingPage.getPrices();
        attachText("Prices - DESC check", prices.toString());

        Assert.assertTrue(
                "Prices not sorted descending: " + prices,
                housingPage.isSortedDescending(prices)
        );
    }
}
