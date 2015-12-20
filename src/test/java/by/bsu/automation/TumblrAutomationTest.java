package by.bsu.automation;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.bsu.automation.steps.Steps;

public class TumblrAutomationTest {
    private Steps steps;
    private final String USERNAME = "getthatautomated";
    private final String EMAIL = "i295491@trbvm.com";
    private final String PASSWORD = "comeonselenium";
    private final String LINK = "seleniumhq.org";

    @BeforeMethod (description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test (description = "Login to Tumblr")
    public void oneCanLoginTumblr() {
        steps.loginTumblr(EMAIL, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn(USERNAME));
    }

    @Test (description = "Post a new link to Tumblr")
    public void oneCanPostLink() {
        steps.loginTumblr(EMAIL, PASSWORD);
        steps.postLinkTumblr(LINK);
        Assert.assertTrue(steps.hasLink(LINK));
    }

    @AfterMethod (description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }
}