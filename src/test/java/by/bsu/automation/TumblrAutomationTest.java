package by.bsu.automation;

import java.util.UUID;
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
    private final String SEARCH_TERM = "tokyo";

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

    @Test (description = "Post text to Tumblr")
    public void oneCanPostText() {
        steps.loginTumblr(EMAIL, PASSWORD);
        String text = UUID.randomUUID().toString();
        Assert.assertTrue(steps.postTextTumblr(text));
        Assert.assertTrue(steps.hasPost(text));
    }

    @Test (description = "Search at Tumblr")
    public void oneCanSearch() {
        steps.search(SEARCH_TERM);
        Assert.assertTrue(steps.isTagged(SEARCH_TERM));
    }

    @Test (description = "Delete latest post")
    public void oneCanDeletePost() {
        steps.loginTumblr(EMAIL, PASSWORD);
        Assert.assertTrue(steps.deleteLatestPost());
    }

    @Test (description = "Like a post")
    public void oneCanLikePost(){
        steps.loginTumblr(EMAIL, PASSWORD);
        steps.search(SEARCH_TERM);
        String postId = steps.likeFirstPost();
        Assert.assertTrue(steps.isLiked(postId));
    }

    @AfterMethod (description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }
}