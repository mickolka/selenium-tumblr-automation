package by.bsu.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends AbstractPage
{
    private final String BASE_URL = "https://www.tumblr.com/blog/getthatautomated";
    private final Logger logger = Logger.getLogger(DashboardPage.class);

    @FindBy(id = "account_button")
    private WebElement accountButton;

    @FindBy(xpath = "//span[@class='blog-list-item-info-name']")
    private WebElement username;

    @FindBy(className = "icon_post_text")
    private WebElement newTextButton;

    @FindBy(xpath = "//button[@class='flat-button blue caption create_post_button']")
    private WebElement postButton;

    @FindBy(xpath = "//li[@id='posts_control']//span[@class='count']")
    private WebElement postCount;

    @FindBy(xpath = "//li[@class='post_container'][1]//div[contains(@class,'creator')]")
    private WebElement settingsButton;

    @FindBy(xpath = "//li[@class='post_container'][1]//div[contains(@class,'creator')]//div[contains(@class,'delete')]")
    private WebElement deleteButton;

    public DashboardPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened dashboard page");
    }

    public String getLoggedInUserName()
    {
        accountButton.click();
        return username.getText();
    }

    public void postText(String text){
        newTextButton.click();
        driver.switchTo().activeElement().sendKeys(text);
        driver.switchTo().activeElement().sendKeys(Keys.CONTROL, Keys.ENTER);
        logger.info("Posted text: " + text);
    }

    public int getPostCount(){
        return Integer.parseInt(postCount.getText());
    }

    public void deletePost() {
        settingsButton.click();
        deleteButton.click();
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        logger.info("Deleted post");
    }
}