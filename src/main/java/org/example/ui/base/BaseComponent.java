package org.example.ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class BaseComponent extends Base {

    protected WebElement componentRoot;

    public BaseComponent(WebDriver driver, WebElement node) {
        super(driver);
        componentRoot = node;
        PageFactory.initElements(new DefaultElementLocatorFactory(componentRoot), this);
    }
}
