package by.bsu.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by MiCkolka on 27.12.2015.
 */
public class LikesPage extends AbstractPage {
    private final String BASE_URL = "https://www.tumblr.com/likes";

    private final static Logger logger = Logger.getLogger(LikesPage.class);

    @FindBy(xpath = "//li[@class='post_container'][1]/div")
    private WebElement firstPost;

    public LikesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Opened likes page");
    }

    public boolean isLiked(String postId) {
        if (postId.equals(firstPost.getAttribute("data-post-id"))){
            firstPost.findElement(By.xpath("//div[contains(@class,'like')]")).click();
            return true;
        }
        return false;
    }
}
