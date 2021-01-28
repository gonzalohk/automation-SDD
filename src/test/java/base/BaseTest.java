package base;

import com.aventstack.extentreports.Status;
import helper.ScreenShotHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import report.ReportManager;
import utils.OSValidator;
import pages.BasePage;
import org.openqa.selenium.TakesScreenshot;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.OutputType;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;

public class BaseTest {
    protected WebDriver webDriver;
    protected BasePage basePage;
    protected final String URL = "http://192.241.152.251:3000/auth/login";
    private final String DEFAULT_BROWSER = "chrome";
    private ExtentTest extentTest;


    /**
     * Método para inicializar la ruta del reporte
     *
     */
    @BeforeSuite
    public static void setUpSuite() throws Exception {
        Properties prop = new Properties();
        InputStream is = null;

        try {
            is = new FileInputStream("resources/config.properties");
            prop.load(is);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        ReportManager.init(prop.getProperty("ruta.so"), "Report");
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional(DEFAULT_BROWSER) String browser, ITestResult iTestResult) throws Exception {
        String operatingSystem = OSValidator.isMac() ? "-mac" : OSValidator.isUnix() ? "-linux" : ".exe";
        // Report
        ReportManager.getInstance().startTest(iTestResult.getMethod().getMethodName());
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver" + operatingSystem);
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "resources/geckodriver" + operatingSystem);
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new Exception(browser + " Does not supported");
        }
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) {
        try {
            switch (iTestResult.getStatus()) {
                case ITestResult.FAILURE:
                    ReportManager.getInstance().getTest().log(Status.FAIL, "Test failed");
                    break;
                case ITestResult.SKIP:
                    ReportManager.getInstance().getTest().log(Status.SKIP, "Test skipped");
                    break;
                case ITestResult.SUCCESS:
                    ReportManager.getInstance().getTest().log(Status.PASS, "Test passed");
                    break;
                default:
                    ReportManager.getInstance().getTest().log(Status.FAIL, "Test incomplete");
            }

            if (iTestResult.getStatus() != ITestResult.FAILURE && iTestResult.getThrowable() != null) {
                ReportManager.getInstance().getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage());
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "Failure Image");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (webDriver != null)
                webDriver.quit();
        }

    }

    @AfterSuite
    public static void tearDownSuite() {
        ReportManager.getInstance().flush();

    }

}
