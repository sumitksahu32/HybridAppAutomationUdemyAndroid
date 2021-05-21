package PageImpl;

import Pages.GSShoesPage;
import com.opencsv.CSVReader;
import com.sun.deploy.association.utility.AppUtility;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AppUtilities;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class GSShoesPageImpl extends GSBaseClassImpl implements GSShoesPage {

    Random rdm = new Random();
    String str="";

    public GSShoesPageImpl()
    {
        AndroidDriver<MobileElement> driver = GSBaseClassImpl.driver;
    }

    public void FillForm() {

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(countryDropDown).click();
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));").click();
        AppUtilities apu = new AppUtilities(driver);
        apu.scrollToTextAndClick("Austria");
        //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
        //driver.findElement(By.xpath("//*[@text='Argentina']")).click();

        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        str = "Robert" + rdm.nextInt(100000);
        driver.findElement(nameTestBox).sendKeys(str);

        driver.hideKeyboard();
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        driver.findElement(maleRadioButton).click();

        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        driver.findElement(letsShopButton).click();
    }

    @Override
    public void reachedHomePage() {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(cartIcon).isDisplayed(),"User can't reach Home Page");

    }

    @Override
    public void blankSubmit() {

        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        driver.findElement(letsShopButton).click();
    }

    @Override
    public void verifyErrorMsg() {

        str = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Msg is : " + str);
        Assert.assertEquals("Please enter your name",str,"Toast message is wrong");
    }

    @Override
    public void searchAndAddToCart(String itemName) throws Exception {

        List<MobileElement> ls = driver.findElements(By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productName')]"));
        List<MobileElement> lsAddLink = driver.findElements(By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productAddCart')]"));
        int loc = 0;


        if(itemName.equalsIgnoreCase("multiple"))
        {
            lsAddLink.get(0).click();
            lsAddLink.get(1).click();

//                File fs = new File("src/main/resources");
//                File csvFile = new File(fs, "TestData.csv");
//                String abPath = csvFile.getAbsolutePath();
//
//                CSVReader read = new CSVReader(new FileReader(abPath));
//                List<String[]> record = read.readAll();
//                Iterator<String[]> itr = record.iterator();
//
//                while(itr.hasNext())
//                {
//                    String[] sArr = itr.next();
//
//                        String prdName  = sArr[0];
//                        loc=0;
////                        //String temp="new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + prdName + "\"));";
////                        String temp = "new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"" + prdName + "\").instance(0));";
////                        driver.findElementByAndroidUIAutomator(temp).click();
//                    AppUtilities apu = new AppUtilities(driver);
//                    apu.scrollToTextAndClickInherited(prdName);
//                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//
//                    ls = driver.findElements(By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productName')]"));
//                    lsAddLink = driver.findElements(By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productAddCart')]"));
//
//                    for(int j=0;j<ls.size();j++)
//                        {
//                            if(ls.get(j).getText().equalsIgnoreCase(prdName))
//                            {
//                                lsAddLink.get(j).click();
//                                break;
//                            }
//                        }
//                }


            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(cartIcon).click();
        }
        else{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            AppUtilities apu = new AppUtilities(driver);
            //apu.scrollToTextAndClickInherited(itemName);
            apu.scrollToTextAndClick(itemName);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

            ls = driver.findElements(By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productName')]"));
            lsAddLink = driver.findElements(By.xpath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productAddCart')]"));
            loc = 0;

            //driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

            for (int i = 0; i < ls.size(); i++) {
                if (ls.get(i).getText().equalsIgnoreCase(itemName)) {
                    lsAddLink.get(i).click();
                    break;
                }
            }

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(cartIcon).click();

            Thread.sleep(4000);
            Assert.assertTrue(driver.findElement(totalAmountText).isDisplayed(), "Checkout Page did not open correctly");

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Assert.assertEquals(driver.findElement(itemCheckoutText).getText().toString(), itemName, "Product not added to cart");
        }
    }

    @Override
    public void verifyTotalAmount() throws InterruptedException {

        Thread.sleep(4000);
        double calSum=0;
        int len = driver.findElementsByXPath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productPrice')]").size();

        for(int i=0;i<len;i++) {
            String amt1 = driver.findElementsByXPath("//*[contains(@resource-id,'com.androidsample.generalstore:id/productPrice')]").get(i).getText();
            double v1 = getAmount(amt1);
            calSum+= v1;
        }

        String tot = driver.findElement(finalAmountText).getText();
        double totV = getAmount(tot);

        Assert.assertEquals(calSum,totV,"Mismatch in added products price");

    }

    @Override
    public double getAmount(String input) {

        input=input.substring(1);
        return Double.parseDouble(input);
    }

    @Override
    public void doGestures() throws InterruptedException {

        //Mobile Gestures
        //Mobile Gestures

        WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
        TouchAction t=new TouchAction(driver);
        t.tap(tapOptions().withElement(element(checkbox))).perform();

        WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
        t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

        driver.findElement(By.id("android:id/button1")).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(5000);
    }


}
