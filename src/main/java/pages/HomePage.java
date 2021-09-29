package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy (xpath = "//*[contains(text(), 'Pyccкий')]")
    private WebElement russianLanguageButton;
    @FindBy (xpath = "//input[@value='Generate Lorem Ipsum']")
    private WebElement generateLoremIpsum;
    @FindBy (xpath = "//label[@for='words']")
    private WebElement wordsButton;
    @FindBy (xpath = "//input[@name='amount']")
    private WebElement numberField;
    @FindBy (xpath = "//label[@for='bytes']")
    private WebElement bytesButton;
    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement startWithLoremIpsumCheckbox;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void openHomePage (String url) {driver.get(url);}

    public void userSwitchesToRussianLanguage() {  russianLanguageButton.click(); }
    public void userClicksOnGenerateLoremIpsum() { generateLoremIpsum.click(); }
    public WebElement getGenerateLoremIpsumButton() {return generateLoremIpsum;}

    public void userClicksOnWordsButton() { wordsButton.click(); }
    public void inputNumberIntoNumberField(final String number) { numberField.clear(); numberField.sendKeys(number); }
    public void userClicksOnBytesButton() { bytesButton.click(); }

    public void userUnchecksStartWithLoremIpsumCheckbox() { startWithLoremIpsumCheckbox.click();}

}
