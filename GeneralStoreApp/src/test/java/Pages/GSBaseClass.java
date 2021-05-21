package Pages;

import PageImpl.GSShoesPageImpl;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.io.IOException;

public interface GSBaseClass {

    public void setup();
    public void tearDown();
    public void switchContext();
    public void startAppiumServer();
    public void stopAppiumServer();
    public boolean checkifServerIsRunning(int port);
    public void startEmulator() throws Exception;
    public void getScreenShot(String testName) throws IOException;

}
