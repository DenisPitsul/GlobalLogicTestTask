package org.example.ui.pages;

import org.example.ui.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DesktopsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//select[@id='products-pagesize']")
    private WebElement productsPageSizeSelect;

    @FindBy(how = How.XPATH, using = "//select[@id='products-orderby']")
    private WebElement productsOrderBySelect;

    @FindBy(how = How.XPATH, using = "//div[@class='item-box']")
    private List<WebElement> itemBoxes;

    @FindBy(how = How.XPATH, using = "//input[@value='Add to cart']")
    private List<WebElement> addToCartButtons;

    public DesktopsPage(WebDriver driver) {
        super(driver);
    }

    public DesktopsPage selectPageSize(String visibleText) {
        waitForElementToAppear(productsPageSizeSelect);
        Select selectPageSize = new Select(productsPageSizeSelect);
        selectPageSize.selectByVisibleText(visibleText);
        return this;
    }

    public DesktopsPage selectOrderBy(String orderByVisibleText) {
        waitForElementToAppear(productsOrderBySelect);
        Select selectPageSize = new Select(productsOrderBySelect);
        selectPageSize.selectByVisibleText(orderByVisibleText);
        return this;
    }

    public AddToCartPage addToCartTheMostExpensiveItem() {
        selectOrderBy("Price: High to Low");
        waitForElementsToAppear(addToCartButtons);
        addToCartButtons.get(0).click();
        return new AddToCartPage(driver);
    }

    public int getItemBoxesSize() {
        waitForElementsToAppear(itemBoxes);
        return itemBoxes.size();
    }
}
