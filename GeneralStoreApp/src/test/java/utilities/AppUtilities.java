package utilities;

import PageImpl.GSBaseClassImpl;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppUtilities extends GSBaseClassImpl {

    AndroidDriver<MobileElement> driver;

    public AppUtilities(AndroidDriver<MobileElement> driver)
    {
        this.driver = driver;
    }

    public void scrollToTextAndClick(String input)
    {
        String temp="new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + input + "\"));";
        driver.findElementByAndroidUIAutomator(temp).click();
    }

    public void scrollToTextAndClickInherited(String input)
    {
        String temp = "new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"" + input + "\").instance(0));";
        driver.findElementByAndroidUIAutomator(temp).click();
    }

}
