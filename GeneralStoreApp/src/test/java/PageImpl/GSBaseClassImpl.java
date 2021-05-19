package PageImpl;

import Pages.GSBaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import okio.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GSBaseClassImpl implements GSBaseClass {

    public static AndroidDriver<MobileElement> driver;

    @Override
    public void setup() {

        try{

            FileInputStream fis = new FileInputStream("src/main/resources/global.properties");
            Properties prop = new Properties();
            prop.load(fis);

        DesiredCapabilities ds = new DesiredCapabilities();
        File ff = new File("src/main/resources");
        File appF = new File(ff,prop.getProperty("appName"));
        ds.setCapability("deviceName",prop.getProperty("deviceName"));
        ds.setCapability("platformName","Android");
        ds.setCapability("platformVersion","11.0");
        ds.setCapability("app",appF.getAbsolutePath());
        ds.setCapability("udid","emulator-5554");
        ds.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        ds.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),ds);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
    }

    @Override
    public void tearDown() {

        driver.quit();
    }

    @Override
    public void switchContext() {

        Set<String> modes = driver.getContextHandles();
        driver.context("WEBVIEW_com.androidsample.generalstore");
        //driver.findElement(By.name("q")).sendKeys("Hello");
        //driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        //driver.navigate().back();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");

    }


}
