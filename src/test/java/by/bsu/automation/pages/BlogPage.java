package by.bsu.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlogPage extends AbstractPage
{
    private final String BASE_URL = "http://getthatautomated.tumblr.com";
    private final String LINK = "seleniumhq.org";
    private final Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(className = "link-host")
    private WebElement link;


    public BlogPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened blog page");
    }

    public String getLink()
    {
        return link.getText();
    }

}
