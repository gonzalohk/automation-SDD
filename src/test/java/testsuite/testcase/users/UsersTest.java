package testsuite.testcase.users;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.UsersPage;
import utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsersTest extends BaseTest {
    private UsersPage usersPage;

    @BeforeMethod
    public void init(){
        webDriver.get(URL);
        LoginPage loginPage = new LoginPage(webDriver);
        DashboardPage dashboardPage = loginPage.loginAs("admin2@admin.com","admin123$");
        usersPage = dashboardPage.clickOnMenuUsers();
    }

    @Test
    public void testCreateUser() {
        usersPage.clickOnCreateUserButton();
        String currentDateTime= StringUtils.getCurrentTimeStr();
        usersPage.fillUserForm("user_"+currentDateTime, "user_lastname_"+currentDateTime,
                "user_"+currentDateTime+"@gmail.com.ss",new String[] {"Defensoria de la ni\u00f1ez y adolescencia-24 horas"},
                "profesional","Abogado");
        usersPage.clickOnSaveButton();
        Assert.assertTrue(usersPage.isUserPageDisplayed(),"User Page was displayed");
        Assert.assertTrue(usersPage.isNewUserCreated("user_"+currentDateTime),"New user was not created");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testCreateUser finished");
    }

    @Test
    public void testCreateUserEmptyName() {
        usersPage.clickOnCreateUserButton();
        String currentDateTime= StringUtils.getCurrentTimeStr();
        usersPage.fillUserForm("", "user_lastname_"+currentDateTime,
                "user_"+currentDateTime+"@gmail.com.ss",new String[] {"Defensoria de la ni\u00f1ez y adolescencia-24 horas"},
                "profesional","Abogado");
        usersPage.clickOnSaveButton();
        Assert.assertFalse(usersPage.isUserPageDisplayed(),"User Page was displayed");
        Assert.assertTrue(usersPage.isNameErrorMessageVisible(),"Error message was not displayed");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testCreateUserEmptyName finished");
    }

    @Test
    public void testCreateAndCancelUser() {
        usersPage.clickOnCreateUserButton();
        String currentDateTime= StringUtils.getCurrentTimeStr();
        usersPage.fillUserForm("", "user_lastname_"+currentDateTime,
                "user_"+currentDateTime+"@gmail.com.ss",new String[] {"Defensoria de la ni\u00f1ez y adolescencia-24 horas"},
                "profesional","Abogado");
        usersPage.clickOnCancelButton();
        Assert.assertTrue(usersPage.isUserPageDisplayed(),"User Page was displayed");
        Assert.assertFalse(usersPage.isNewUserCreated("user_"+currentDateTime),"New user was created");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testCreateAndCancelUser finished");
    }
}