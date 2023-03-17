package org.example.ui.components;

import org.example.ui.base.BaseComponent;
import org.example.ui.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderComponent extends BaseComponent {

    @FindBy(how = How.XPATH, using = ".//span[text()='Shopping cart']")
    private WebElement shoppingCartLink;

    @FindBy(how = How.XPATH, using = ".//span[@class='cart-qty']")
    private WebElement cartQuantity;

    public HeaderComponent(WebDriver driver, WebElement node) {
        super(driver, node);
    }

    public ShoppingCartPage openShoppingCartPage() {
        waitForElementToAppear(shoppingCartLink);
        actions.moveToElement(shoppingCartLink).click().build().perform();
        return new ShoppingCartPage(driver);
    }

    public int getCartQuantity() {
        waitForElementToAppear(cartQuantity);
        return Integer.parseInt(cartQuantity.getText().replace("(", "").replace(")", ""));
    }
}
