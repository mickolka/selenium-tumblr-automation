package by.bsu.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage
{
    private final String BASE_URL = "https://www.tumblr.com/login";
    private final Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(id = "signup_email")
    private WebElement inputEmail;

    @FindBy(id = "signup_password")
    private WebElement inputPassword;

    @FindBy (id = "signup_forms_submit")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@class='blog-list-item-info-name']")
    private WebElement username;

    @FindBy(id = "search_query")
    private WebElement searchField;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened login page");
    }

    public void login(String email, String password)
    {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        submitButton.click();
        logger.info("Logged in");
    }

    public void searchFor(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        logger.info("Started search for term '" + searchTerm + "'");
    }
}