package hooks;

import core.BrowserManager;
import core.TestContext;
import io.cucumber.java.*;
import com.microsoft.playwright.*;
import utils.AllureUtils;

public class TestHooks {

    @Before
    public void setup() {
        BrowserManager.init();
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            AllureUtils.attachScreenshot(TestContext.page, "Failure Screenshot");
            AllureUtils.attachPageSource(TestContext.page, "Page Source");
        }

        if (TestContext.page != null) {
            TestContext.page.close();
        }

        if (TestContext.browserContext != null) {
            TestContext.browserContext.close();
        }

        if (TestContext.browser != null) {
            TestContext.browser.close();
        }
    }
}
