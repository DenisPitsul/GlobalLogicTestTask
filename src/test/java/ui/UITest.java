package ui;

import org.example.ui.pages.AddToCartPage;
import org.example.ui.pages.DesktopsPage;
import org.example.ui.pages.HomePage;
import org.example.ui.pages.ShoppingCartPage;
import org.testng.annotations.Test;

import java.util.List;

public class UITest extends BaseTestRunnerUI {

    @Test
    public void addToCartTest() {
        driver.get("http://demowebshop.tricentis.com/");
        DesktopsPage desktopsPage = new HomePage(driver)
                .openComputersPage()
                .openDesktopsPage()
                .selectPageSize("4");

        softAssert.assertEquals(desktopsPage.getItemBoxesSize(), 4);

        AddToCartPage addToCartPage = desktopsPage.addToCartTheMostExpensiveItem();

        String productName = addToCartPage.getProductNameText();

        List<String> productNames = addToCartPage
                .clickAddToCart()
                .getHeader()
                .openShoppingCartPage()
                .getProductNamesText();

        softAssert.assertEquals(productNames.get(0), productName);
        softAssert.assertAll();
    }

    @Test
    public void addToCartTest2() {
        driver.get("https://demowebshop.tricentis.com/build-your-own-expensive-computer-2");

        AddToCartPage addToCartPage = new AddToCartPage(driver);
        String productName = addToCartPage.getProductNameText();

        int beforeCartQuantity = addToCartPage
                .getHeader()
                .getCartQuantity();

        double fullPrice = addToCartPage
                .clickFastProcessorRadioButton()
                .clickEightGBRadioButton()
                .clickImageViewerCheckBox()
                .clickOfficeSuiteCheckBox()
                .clickOtherOfficeSuiteCheckBox()
                .clickAddToCart()
                .getFullPrice();

        int afterCartQuantity = addToCartPage
                .getHeader()
                .getCartQuantity();

        softAssert.assertEquals(afterCartQuantity, beforeCartQuantity + 1);

        ShoppingCartPage shoppingCartPage = addToCartPage
                .clickAddToCart()
                .getHeader()
                .openShoppingCartPage();
        List<String> productNames = shoppingCartPage.getProductNamesText();
        double productPrice = shoppingCartPage.getProductPrice();

        softAssert.assertEquals(productNames.get(0), productName);
        softAssert.assertEquals(productPrice, fullPrice);
        softAssert.assertAll();

        shoppingCartPage.removeProduct();
    }
}
