package testBase;

import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.slf4j.Logger;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.Logger;     // Important imports for log4j2
import org.apache.logging.log4j.LogManager; // Important imports for log4j2
import org.testng.annotations.Parameters;

import javax.xml.transform.Source;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger; //Log4j
    public Properties pro;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String br) throws IOException {

        //Loading config.properties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        pro = new Properties();
        pro.load(file);

        logger = LogManager.getLogger(this.getClass());   //Log4j2 code

        if (pro.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();

            //os
            if (os.equalsIgnoreCase("windows")) {
                cap.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                cap.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                cap.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No Matching OS");
                return;
            }

            //browser
            switch (br.toLowerCase()) {
                case "chrome":
                    cap.setBrowserName("chrome");
                    break;
                case "edge":
                    cap.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    cap.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("No Matching browser");
                    return;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        }

        if (pro.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid Browser name");
                    return;
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(pro.getProperty("appURL"));  // reading url from proeprties file.
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Master", "Regression"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String captureScreen(String tname) {
//        try {
//            // Check if driver supports screenshots
//            if (driver instanceof TakesScreenshot) {
//                // Take screenshot
//                TakesScreenshot ts = (TakesScreenshot) driver;
//                File source = ts.getScreenshotAs(OutputType.FILE);
//
//                // Create directory if not exists
//                String directory = System.getProperty("user.dir") + "/Screenshots/";
//                File screenshotDir = new File(directory);
//                if (!screenshotDir.exists()) {
//                    screenshotDir.mkdirs();  // Create directory and parents if needed
//                }
//
//                // Add timestamp to avoid overwriting
//                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                File target = new File(directory + tname + "_" + timestamp + ".png");
//
//                // Copy file to target location
//                FileUtils.copyFile(source, target);
//
//                System.out.println("Screenshot Taken: " + target.getAbsolutePath());
//            } else {
//                System.out.println("Driver does not support screenshots.");
//            }
//        } catch (IOException e) {
//            System.out.println("Failed to capture screenshot: " + e.getMessage());
//        }
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }


    public String randomString() {
        String generatedstring = RandomStringUtils.randomAlphabetic(5);
        return generatedstring;
    }

    public String randomNumber() {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomPassword() {
        String generatedPassword = RandomStringUtils.randomAlphanumeric(10);
        return generatedPassword;
    }

}
