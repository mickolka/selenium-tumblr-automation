package by.bsu.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by MiCkolka on 25.12.2015.
 */
public class SearchPage extends AbstractPage {
    private final String BASE_URL = "https://www.tumblr.com/search/";
    private final Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy (id = "search_posts")
    private WebElement searchResults;

    public SearchPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Opened search page");
    }

    public boolean isPresentInAllPosts(String searchTerm) {
        boolean flag;
        for(WebElement post: searchResults.findElements(By.tagName("article"))){
            flag = false;
            for(WebElement tag: post.findElements(By.className("post_tag"))){
                if(searchTerm.equals(tag.getAttribute("title").toLowerCase())){
                    flag = true;
                }
            }
            if(!flag){
                return false;
            }
        }
        return true;
    }


    public String likeFirstPostAndGetId() {
        WebElement firstPost = searchResults.findElements(By.tagName("article")).get(0);
        firstPost.findElement(By.xpath("//div[contains(@class, 'like')]")).click();
        return firstPost.getAttribute("data-post-id");
    }
}
