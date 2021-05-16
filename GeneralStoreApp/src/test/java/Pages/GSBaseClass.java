package Pages;

import PageImpl.GSShoesPageImpl;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public interface GSBaseClass {

    public void setup();
    public void tearDown();
    public void switchContext();
}
