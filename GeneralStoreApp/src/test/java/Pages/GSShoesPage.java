package Pages;

import org.openqa.selenium.By;

import java.io.FileNotFoundException;

public interface GSShoesPage {

    By letsShopButton = By.xpath("//android.widget.Button[contains(@resource-id,'com.androidsample.generalstore:id/btnLetsShop')]");
    By maleRadioButton = By.xpath("//android.widget.RadioButton[contains(@text,'Male')]");
    By nameTestBox = By.xpath("//android.widget.EditText[contains(@text,'Enter name here')]");
    By countryDropDown = By.xpath("//android.widget.TextView[contains(@resource-id,'android:id/text1')]");

    By cartIcon = By.xpath("//android.widget.ImageButton[contains(@resource-id,'com.androidsample.generalstore:id/appbar_btn_cart')]");
    By totalAmountText = By.xpath("//*[contains(@text,'Total Purchase Amount')]");
    By itemCheckoutText = By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productName')]");
    By finalAmountText = By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/totalAmountLbl')]");
    By emailAlertCheckbox = By.xpath("//*[contains(@text(),'Send me e-mails on discounts related to selected products in future')]");
    By visitWebButton = By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/btnProceed')]");
    By tncButton = By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/termsButton')]");



    public void FillForm();
    void reachedHomePage();
    void blankSubmit();
    void verifyErrorMsg();
    void searchAndAddToCart(String itemName) throws FileNotFoundException, InterruptedException;
    void verifyTotalAmount() throws InterruptedException;
    double getAmount(String input);
    void doGestures() throws InterruptedException;
}
