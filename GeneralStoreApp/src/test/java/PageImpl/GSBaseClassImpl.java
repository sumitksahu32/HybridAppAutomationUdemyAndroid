package PageImpl;

import Pages.GSBaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import okio.Timeout;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GSBaseClassImpl implements GSBaseClass {

    public static AndroidDriver<MobileElement> driver;
    public static AppiumDriverLocalService ser;

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

    @Before
    public void startAppiumServer() {

        boolean status=checkifServerIsRunning(4723);

    if(!status) {
        ser = AppiumDriverLocalService.buildDefaultService();
        ser.start();
    }

    }

    @After
    public void stopAppiumServer() {

        ser.stop();
    }

    @Override
    public boolean checkifServerIsRunning(int port) {

        boolean flag=false;
        ServerSocket sk;
        try{
            sk=new ServerSocket(port);
            sk.close();
        }
        catch(Exception e)
        {
            flag=true;
            e.printStackTrace();
        }
        finally {
            sk=null;
        }

        return flag;
    }

    public void startEmulator() throws Exception {

        //Emulator Name = Nexus5XTestAVD
        String path = System.getProperty("user.dir") + "src/main/resources/startEmulator.bat";
        Runtime.getRuntime().exec(path);
        Thread.sleep(7000);
    }

    @Override
    public void getScreenShot(String testName) throws IOException {

        String path = System.getProperty("user.dir") + "target" + testName + ".jpg";
        File snap = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(snap,new File(path));
    }


}
