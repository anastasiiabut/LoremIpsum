package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.SearchResultsPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 60;

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @And("User switches to Russian language")
    public void userSwitchesToRussianLanguage() { homePage.userSwitchesToRussianLanguage(); }
    @Then("User checks the first paragraph contains the {string}")
    public void userChecksTheFirstParagraphContainsTheWord(final String word) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getFirstParagraphOnRussianHomePage());
        assertTrue(searchResultsPage.getFish().contains(word));
    }

    @And("User clicks on 'Generate Lorem Ipsum'")
    public void userClicksOnGenerateLoremIpsum() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getGenerateLoremIpsumButton());
        homePage.userClicksOnGenerateLoremIpsum(); }
    @Then("User checks the first paragraph starts with the {string}")
    public void userChecksTheFirstParagraphStartsWithTheSentence(final String sentence) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getFirstParagraphOnGenerateLoremIpsum());
        assertTrue(searchResultsPage.getFirstParagraph().startsWith(sentence));
    }

    @And("User clicks on 'Words' button")
    public void userClicksOnWordsButton() { homePage.userClicksOnWordsButton(); }
    @And("User inputs {string} into the number field")
    public void userInputsNumberIntoTheNumberField(final String number) {
        homePage.inputNumberIntoNumberField(number);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)", "");
    }
    @And("User checks the text contains expected {string} amount of words")
    public void userChecksTheTextContainsTheAmountOfWords(final String amountOfWords) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(searchResultsPage.getAmountOfWordsInResultField(), amountOfWords);
    }

    @And("User clicks on 'Bytes' button")
    public void userClicksOnBytesButton() { homePage.userClicksOnBytesButton(); }
    @Then("User checks the text contains expected {string} amount of words of bytes")
    public void userChecksTheTextContainsExpectedAmountOfBytesAmountOfWordsOfBytes(final String amountOfBytes) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(searchResultsPage.getAmountOfBytesInResultField(), amountOfBytes);
    }

    @And("User unchecks 'Start with Lorem Ipsum' checkbox")
    public void userUnchecksStartWithLoremIpsumCheckbox() { homePage.userUnchecksStartWithLoremIpsumCheckbox(); }
    @Then("User checks that the first paragraph does not contain {string}")
    public void userChecksThatTheFirstParagraphDoesNotContainSentence(final String sentence) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertFalse(searchResultsPage.getFirstParagraph().startsWith(sentence));
    }

    @And("User counts the number of paragraphs containing the {string}")
    public void userCountsTheNumberOfParagraphsContainingTheWord(final String word) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.getNumberOfParagraphs(word);

    }
    @And("User clicks on 'Lorem Ipsum'")
    public void userClicksOnLoremIpsum() { searchResultsPage.userClicksOnLoremIpsum();  }

    @And("User counts number of paragraphs containing the {string}")
    public void userCountsAverageNumberOfParagraphsContainingTheWord(final String word) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.getNumberOfParagraphs(word);
        System.out.println(searchResultsPage.getNumberOfParagraphs(word));
    }

    @Then("User generates text {int} times and checks the probability")
    public void userRepeatsTheTestTimes(int ten) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i<ten; i++) {
            searchResultsPage.userClicksOnLoremIpsum();
            homePage = pageFactoryManager.getHomePage();
            homePage.openHomePage("https://lipsum.com/");
            homePage.userClicksOnGenerateLoremIpsum();
            searchResultsPage = pageFactoryManager.getSearchResultsPage();
            searchResultsPage.getNumberOfParagraphs("lorem");
            list.add(searchResultsPage.getNumberOfParagraphs("lorem"));
        }
        Integer sum = list.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println(sum);
        Assert.assertTrue((double)sum/(double)50>(double)4/(double)10);
        Assert.assertFalse((double)sum/(double)50>(double)6/(double)10);
    }


    @After
    public void tearDown() { driver.close(); }



}
