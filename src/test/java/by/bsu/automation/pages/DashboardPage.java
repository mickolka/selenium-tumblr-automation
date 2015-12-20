package by.bsu.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends AbstractPage
{
    private final String BASE_URL = "https://www.tumblr.com/dashboard";
    private final Logger logger = Logger.getLogger(DashboardPage.class);

    @FindBy(id = "account_button")
    private WebElement accountButton;

    @FindBy(xpath = "//span[@class='blog-list-item-info-name']")
    private WebElement username;

    @FindBy(className = "icon_post_link")
    private WebElement newLinkButton;

    @FindBy(xpath = "//div[@aria-label='Type or paste a URL']")
    private WebElement linkField;

    @FindBy(xpath = "//button[@class='flat-button blue caption create_post_button']")
    private WebElement postButton;

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

    public void postLink(String link){
        newLinkButton.click();
        linkField.sendKeys(link);
        postButton.click();
        logger.info("Posted link");
    }
}