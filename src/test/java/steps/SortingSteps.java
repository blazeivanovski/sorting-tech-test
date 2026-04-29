package steps;

import com.microsoft.playwright.Locator;
import core.TestContext;
import io.cucumber.java.en.*;
import model.SortOption;
import pages.HousingPage;

import org.junit.Assert;
import utils.SortOptionMapper;

import java.util.List;

import static utils.AllureUtils.attachText;

public class SortingSteps {

    private final HousingPage housingPage = new HousingPage(TestContext.page);

    @Then("Default sorting options should be available")
    public void defaultSorting() {
        Locator dropdown = housingPage.openSortDropdown();
        Assert.assertTrue(SortOption.PRICE_ASC.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.PRICE_ASC));
        Assert.assertTrue(SortOption.PRICE_DESC.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.PRICE_DESC));
        Assert.assertTrue(SortOption.NEWEST.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.NEWEST));
    }

    @When("User performs a search for {string}")
    public void search(String query) {
        housingPage.search(query);
    }

    @Then("Extended sorting options should be available")
    public void extendedSorting() {
        Locator dropdown = housingPage.openSortDropdown();
        Assert.assertTrue(SortOption.PRICE_ASC.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.PRICE_ASC));
        Assert.assertTrue(SortOption.PRICE_DESC.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.PRICE_DESC));
        Assert.assertTrue(SortOption.NEWEST.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.NEWEST));
        Assert.assertTrue(SortOption.UPCOMING.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.UPCOMING));
        Assert.assertTrue(SortOption.RELEVANCE.getValue() + " sorting option is not available!", housingPage.isSortOptionVisible(dropdown, SortOption.RELEVANCE));
    }

    @When("User sorts results by {string}")
    public void sortBy(String optionText) {
        SortOption option = SortOptionMapper.fromText(optionText);
        housingPage.selectSortOption(option);
        housingPage.waitForPricesToLoad();
    }

    @Then("Results should be sorted by price in ascending order")
    public void verifyAscending() {
        List<Integer> prices = housingPage.getPrices();
        attachText("Prices - ASC check", prices.toString());

        Assert.assertTrue(
                "Prices not sorted ascending: " + prices,
                housingPage.isSortedAscending(prices)
        );
    }

    @Then("Results should be sorted by price in descending order")
    public void verifyDescending() {
        List<Integer> prices = housingPage.getPrices();
        attachText("Prices - DESC check", prices.toString());

        Assert.assertTrue(
                "Prices not sorted descending: " + prices,
                housingPage.isSortedDescending(prices)
        );
    }
}
