package pages;

import utils.helpers.Action;


public class CommonPage extends Action{

    public void click(String key) {
        clickElement(findElement(key));
    }

    public void clearText(String key) {
        clearFilledInput(key);
    }

    public void fill(String key, String text) {
        clearAndFillInput(key, text);
    }

    public void fillTextBoxWithSpecificText(String text, String textbox){
        fillStringOfaTextIntoTextBox(text,textbox);
    }

    public void checkWithText(String key) {
        checkWithTextOnPage(key);
    }

    public void refreshPage() {
        super.refreshPage();
    }

    public void waitForTheElementDisplay(String key) {
        waitForVisibilityOfElement(findElement(key));
    }

    public void waitForTheElementClickable(String key) {
        waitForElementToClick(findElement(key));
    }

    public void verifyUrl(String url) {
        super.verifyUrl(url);
    }

    public void verifyPageTitle(String pageTitle) {
        super.verifyPageTitle(pageTitle);
    }

    public void waitUntilElementVisible(String key) {
        waitUntilElementIsVisible(findElement(key), 5);
    }

    public void clickEnter() {
        clickEnterButton();
    }

}
