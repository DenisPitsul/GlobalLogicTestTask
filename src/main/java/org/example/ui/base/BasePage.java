package org.example.ui.base;

import org.example.ui.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public abstract class BasePage extends Base {
    protected HeaderComponent header;

    @FindBy(how = How.XPATH, using = "//div[@class='header']")
    private WebElement headerNode;

    public BasePage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver, headerNode);
    }

    public HeaderComponent getHeader() {
        return header;
    }
}
