package utils;

import io.qameta.allure.Allure;
import com.microsoft.playwright.Page;

import java.io.ByteArrayInputStream;

public class AllureUtils {

    private AllureUtils() {}

    public static void attachScreenshot(Page page, String name) {
        byte[] screenshot = page.screenshot();
        Allure.addAttachment(name, "image/png",
                new ByteArrayInputStream(screenshot), ".png");
    }

    public static void attachPageSource(Page page, String name) {
        String content = page.content();
        Allure.addAttachment(name, "text/html", content);
    }

    public static void attachText(String name, String message) {
        Allure.addAttachment(name, message);
    }
}