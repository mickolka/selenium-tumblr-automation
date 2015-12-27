package by.bsu.automation.steps;

import java.util.concurrent.TimeUnit;

import by.bsu.automation.pages.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Steps
{
    private WebDriver driver;

    private final Logger logger = Logger.getLogger(Steps.class);

    public void initBrowser()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS)
                .implicitlyWait(25, TimeUnit.SECONDS);
        logger.info("Initialized browser");
    }

    public void closeDriver()
    {
        driver.quit();
        logger.info("Stopped browser");
    }

    public void loginTumblr(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isLoggedIn(String username)
    {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openPage();
        return (username.equals(dashboardPage.getLoggedInUserName()));
    }

    public boolean postTextTumblr(String text){
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openPage();
        int count = dashboardPage.getPostCount();
        dashboardPage.postText(text);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='knight-rider-container loading']")));
        return(count+1 == dashboardPage.getPostCount());
    }

    public boolean hasPost(String text)
    {
        BlogPage blogPage = new BlogPage(driver);
        openNewTab();
        blogPage.openPage();
        return (text.equals(blogPage.getFirstPostText()));
    }

    public void search(String searchTerm) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.searchFor(searchTerm);
    }

    public boolean isTagged(String searchTerm) {
        SearchPage searchPage = new SearchPage(driver);
        return(searchPage.isPresentInAllPosts(searchTerm));
    }

    public boolean deleteLatestPost() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openPage();
        int count = dashboardPage.getPostCount();
        dashboardPage.deletePost();
        driver.navigate().refresh();
        return(count-1 == dashboardPage.getPostCount());
    }

    public String likeFirstPost() {
        SearchPage searchPage = new SearchPage(driver);
        return searchPage.likeFirstPostAndGetId();
    }


    public boolean isLiked(String postId) {
        LikesPage likesPage = new LikesPage(driver);
        likesPage.openPage();
        return likesPage.isLiked(postId);
    }

    private void openNewTab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        logger.info("Opened new tab");
    }
}
