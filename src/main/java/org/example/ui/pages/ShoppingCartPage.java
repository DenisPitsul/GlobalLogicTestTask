package org.example.ui.pages;

import org.example.ui.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[@class='product-name']")
    private List<WebElement> productNames;

    @FindBy(how = How.XPATH, using = "//span[@class='product-unit-price']")
    private WebElement productUnitPrice;

    @FindBy(how = How.XPATH, using = "//input[contains(@class, 'qty-input')]")
    private WebElement quantityInput;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductNamesText() {
        waitForElementsToAppear(productNames);
        return productNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public double getProductPrice() {
        waitForElementToAppear(productUnitPrice);
        return Double.parseDouble(productUnitPrice.getText());
    }

    public void removeProduct() {
        waitForElementToAppear(quantityInput).clear();
        quantityInput.sendKeys("0", Keys.RETURN);
    }
}
