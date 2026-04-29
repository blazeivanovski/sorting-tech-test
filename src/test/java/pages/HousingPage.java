package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import model.SortOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HousingPage {

    private final Page page;
    private final String priceElements = "span.priceinfo";

    public HousingPage(Page page) {
        this.page = page;
    }

    public void search(String query) {
        Locator search = page.getByPlaceholder("Search housing");
        search.fill(query);
        search.press("Enter");
    }

    public void clickSortDropdown() {
        page.locator(".cl-search-sort-mode.bd-combo-box").click();
    }

    public Locator openSortDropdown() {
        clickSortDropdown();
        Locator dropdown = page.locator(".bd-list-box");
        dropdown.waitFor();
        return dropdown;
    }

    public void selectSortOption(SortOption option) {
        clickSortDropdown();
        String dropdownOptions = ".bd-list-box .label";
        Locator item = page.locator(dropdownOptions + ":has-text('" + option.getValue() + "')");

        item.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        item.click();
    }

    public void assertSortOptionsVisible(List<SortOption> options) {
        Locator dropdown = openSortDropdown();

        for (SortOption option : options) {
            if (!dropdown.locator("text=" + option.getValue()).isVisible()) {
                throw new AssertionError("Missing " + option.getValue() + " sorting option!");
            }
        }
    }

    public void waitForPricesToLoad() {
        page.waitForSelector(priceElements);
    }

    public List<Integer> getPrices() {
        return page.locator(priceElements)
                .allTextContents()
                .stream()
                .map(this::parsePrice)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Integer parsePrice(String priceText) {
        if (priceText == null || priceText.isBlank()) {
            return null;
        }

        String cleaned = priceText
                .replaceAll("[^0-9]", "");

        if (cleaned.isEmpty()) {
            return null;
        }

        return Integer.parseInt(cleaned);
    }

    public boolean isSortedAscending(List<Integer> prices) {
        List<Integer> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);

        return prices.equals(sorted);
    }

    public boolean isSortedDescending(List<Integer> prices) {
        List<Integer> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());

        return prices.equals(sorted);
    }
}
