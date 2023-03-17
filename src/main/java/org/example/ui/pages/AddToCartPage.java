package org.example.ui.pages;

import org.example.ui.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddToCartPage extends BasePage {

    private double additionalPrice = 0;

    @FindBy(how = How.XPATH, using = "//div[@class='product-name']/h1")
    private WebElement productName;

    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Fast')]//preceding-sibling::input")
    private WebElement fastProcessorRadioButton;

    @FindBy(how = How.XPATH, using = "//label[contains(text(), '8GB')]//preceding-sibling::input")
    private WebElement eightGBRadioButton;

    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Image Viewer')]//preceding-sibling::input")
    private WebElement imageViewerCheckBox;

    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Office Suite')]//preceding-sibling::input")
    private WebElement officeSuiteCheckBox;

    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Other Office Suite')]//preceding-sibling::input")
    private WebElement otherOfficeSuiteCheckBox;

    @FindBy(how = How.XPATH, using = "//div[@class='product-price']/span")
    private WebElement productPrice;

    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'add-to-cart-button')]")
    private WebElement addToCartButton;

    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    public double getFullPrice() {
        return getProductPrice() + additionalPrice;
    }

    public String getProductNameText() {
        return waitForElementToAppear(productName).getText();
    }

    public double getProductPrice() {
        return Double.parseDouble(waitForElementToAppear(productPrice).getText());
    }

    public AddToCartPage clickFastProcessorRadioButton() {
        waitForElementToAppear(fastProcessorRadioButton).click();
        additionalPrice += 100;
        return this;
    }

    public AddToCartPage clickEightGBRadioButton() {
        waitForElementToAppear(eightGBRadioButton).click();
        additionalPrice += 60;
        return this;
    }

    public AddToCartPage clickImageViewerCheckBox() {
        waitForElementToAppear(imageViewerCheckBox).click();
        additionalPrice += 5;
        return this;
    }

    public AddToCartPage clickOfficeSuiteCheckBox() {
        waitForElementToAppear(officeSuiteCheckBox).click();
        additionalPrice += 100;
        return this;
    }

    public AddToCartPage clickOtherOfficeSuiteCheckBox() {
        waitForElementToAppear(otherOfficeSuiteCheckBox).click();
        additionalPrice += 40;
        return this;
    }

    public AddToCartPage clickAddToCart() {
        waitForElementToAppear(addToCartButton).click();
        return this;
    }
}
