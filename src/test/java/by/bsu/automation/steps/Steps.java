package by.bsu.automation.steps;

import java.util.concurrent.TimeUnit;

import by.bsu.automation.pages.BlogPage;
import by.bsu.automation.pages.DashboardPage;
import by.bsu.automation.pages.LoginPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Steps
{
    private WebDriver driver;

    private final Logger logger = Logger.getLogger(Steps.class);

    public void initBrowser()
    {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
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

    public void postLinkTumblr(String link){
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openPage();
        dashboardPage.postLink(link);
    }

    public boolean hasLink(String link)
    {
        BlogPage blogPage = new BlogPage(driver);
        openNewTab();
        blogPage.openPage();
        return (link.equals(blogPage.getLink()));
    }

    private void openNewTab() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        logger.info("Opened new tab");
    }
}
