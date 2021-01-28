package testsuite.testcase.login;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @BeforeMethod
    public void init(){
        webDriver.get(URL + "login");
    }

   @Test
    public void testInvalidCredentials() {
        LoginPage loginPage = new LoginPage(webDriver);
        DashboardPage dashboardPage = loginPage.loginAs("smith.admin@dnya.com","123456");
        Assert.assertFalse(dashboardPage.isDashboardPageDisplayed(),"Dashboard is not displayed");
        Assert.assertTrue(loginPage.isLoginErrorMessageVisible(),"Login was not successful, check credentials");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testInvalidCredentials finished");
    }

    @Test
    public void testInvalidEmail() {
        LoginPage loginPage = new LoginPage(webDriver);
        DashboardPage dashboardPage = loginPage.loginAs("admin2@admin@com","admin123$");
        Assert.assertFalse(dashboardPage.isDashboardPageDisplayed(),"Dashboard is not displayed");
        Assert.assertTrue(loginPage.isEmailErrorMessageVisible(),"Login was successful, check email format");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testInvalidEmail finished");
    }

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(webDriver);
        DashboardPage dashboardPage = loginPage.loginAs("admin2@admin.com","admin123$");
        Assert.assertTrue(dashboardPage.isDashboardPageDisplayed(),"Dashboard is not displayed");
        Assert.assertFalse(loginPage.isLoginErrorMessageVisible(),"Login was not successful, check credentials");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testSuccessfulLogin finished");
    }

    @Test
    public void testLogOut() {
        LoginPage loginPage = new LoginPage(webDriver);
        DashboardPage dashboardPage = loginPage.loginAs("admin2@admin.com","admin123$");
        dashboardPage.clickOnTopBarButton();
        dashboardPage.clickOnTopBarLogoutButton();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),"Login page was not displayed");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testLogOut finished");
    }
}