package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;
    Locator englishLink;

    public HomePage(Page page) {
        this.page = page;
        englishLink = page.locator("a:has-text('english')");
    }

    public void open() {
        page.navigate("https://madrid.craigslist.org/");
    }

    public void selectEnglish() {
        englishLink.waitFor();
        englishLink.click();
    }

    public void goToHousing() {
        String housingLink = "#hhh a.hhh";
        page.locator(housingLink).click();
    }
}
