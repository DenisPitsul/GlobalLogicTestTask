package org.example.ui.pages;

import org.example.ui.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@class='listbox']//a[contains(text(), 'Computers')]")
    private WebElement computersLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ComputersPage openComputersPage() {
        waitForElementToAppear(computersLink).click();
        return new ComputersPage(driver);
    }
}
