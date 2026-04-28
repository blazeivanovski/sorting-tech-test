package core;

import com.microsoft.playwright.*;

public class BrowserManager {

    private static Playwright playwright;

    public static void init() {
        if (playwright == null) {
            playwright = Playwright.create();
        }

        if (TestContext.browser == null) {
            TestContext.browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
            );
        }

        TestContext.browserContext = TestContext.browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1280, 720)
        );

        TestContext.page = TestContext.browserContext.newPage();
    }
}
