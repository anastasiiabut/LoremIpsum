package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//p[contains(text(),'это текст')]")
    private WebElement firstParagraphOnRussianHomePage;
    @FindBy(xpath = "//div[@id='lipsum']//p[1]")
    private WebElement firstParagraphOnGenerateLoremIpsum;
    @FindBy(xpath = "//div[@id='lipsum']//p[2]")
    private WebElement secondParagraphOnGenerateLoremIpsum;
    @FindBy(xpath = "//div[@id='lipsum']//p[3]")
    private WebElement thirdParagraphOnGenerateLoremIpsum;
    @FindBy(xpath = "//div[@id='lipsum']//p[4]")
    private WebElement fourthParagraphOnGenerateLoremIpsum;
    @FindBy(xpath = "//div[@id='lipsum']//p[5]")
    private WebElement fifthParagraphOnGenerateLoremIpsum;
    @FindBy (xpath = "//div[@id='lipsum']/p")
    private List<WebElement> paragraphList;
    @FindBy (xpath = "//div//p")
    private WebElement resultField;
    @FindBy (xpath = "//a[@title='Lorem Ipsum']")
    private WebElement loremIpsum;


    public SearchResultsPage (WebDriver driver) { super (driver);}

    public WebElement getFirstParagraphOnRussianHomePage() { return firstParagraphOnRussianHomePage;}
    public String getFish () {return firstParagraphOnRussianHomePage.getText();}

    public WebElement getFirstParagraphOnGenerateLoremIpsum() {return firstParagraphOnGenerateLoremIpsum;}
    public String getFirstParagraph() {return firstParagraphOnGenerateLoremIpsum.getText();}

    public String getResultField() {return resultField.getText(); }
    public String getAmountOfWordsInResultField() {
        String [] words = getResultField().split("\\s+");
        return String.valueOf(words.length); }
    public String getAmountOfBytesInResultField() { return String.valueOf(getResultField().length()); }

    public List<WebElement> getParagraphList() {return paragraphList;}
    public int getNumberOfParagraphs(final String word) {

        int numberOfParagraphs = 0;
        for (WebElement webElement: getParagraphList()) {
            if(webElement.getText().toLowerCase().contains(word)) {
                System.out.println(word);
                numberOfParagraphs++;
            }
        }
        System.out.println(numberOfParagraphs);
        return numberOfParagraphs;
    }
    public void userClicksOnLoremIpsum() { loremIpsum.click(); }












}
