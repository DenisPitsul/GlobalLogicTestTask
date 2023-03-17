package org.example.ui.pages;

import org.example.ui.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ComputersPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@class='item-box']//a[contains(text(), 'Desktops')]")
    private WebElement desktopsLink;

    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    public DesktopsPage openDesktopsPage() {
        waitForElementToAppear(desktopsLink).click();
        return new DesktopsPage(driver);
    }
}
