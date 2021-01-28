package testsuite.testcase.roles;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RolesPage;
import utils.StringUtils;

public class RolesTest extends BaseTest {
    private RolesPage rolesPage;

    @BeforeMethod
    public void init(){
        webDriver.get(URL);
        LoginPage loginPage = new LoginPage(webDriver);
        DashboardPage dashboardPage = loginPage.loginAs("admin2@admin.com","admin123$");
        rolesPage = dashboardPage.clickOnMenuRoles();
    }

    @Test
    public void testCreateAndSaveRol() {
        rolesPage.clickOnCreateRolButton();
        Boolean permissions[][] = new Boolean[][]{
                {true,false,false,false},
                {true,false,false,false},
                {true,false,false,false},
                {true,false,false,false}};
        Boolean actions[] = new Boolean[]{true,true,true,true,true,true,false,false,
                false,false,true,true,true,true,true,true};
        String currentDateTime= StringUtils.getCurrentTimeStr();
        rolesPage.fillRolForm("rol_"+currentDateTime,permissions,actions);
        rolesPage.clickOnSaveButton();
        Assert.assertTrue(rolesPage.isRolesPageDisplayed(),"Roles is not displayed");
        Assert.assertTrue(rolesPage.isNewRolCreated("rol_"+currentDateTime),"Rol was not created");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testCreateAndSaveRol finished");
    }

    @Test
    public void testCreateRolWithOutData() {
        rolesPage.clickOnCreateRolButton();
        Boolean permissions[][] = new Boolean[][]{
                {false,false,false,false},
                {false,false,false,false},
                {false,false,false,false},
                {false,false,false,false}};
        Boolean actions[] = new Boolean[]{false,false,false,false,false,false,false,false,
                false,false,false,false,false,false,false,false};
        rolesPage.fillRolForm("",permissions,actions);
        rolesPage.clickOnSaveButton();
        Assert.assertFalse(rolesPage.isRolesPageDisplayed(),"Roles was displayed");
        Assert.assertTrue(rolesPage.isNameErrorMessageVisible(),"Error message was not displayed");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testCreateRolWithOutData finished");
    }

    @Test
    public void testCreateAndCancelRol() {
        rolesPage.clickOnCreateRolButton();
        Boolean permissions[][] = new Boolean[][]{
                {true,false,false,false},
                {true,false,false,false},
                {true,false,false,false},
                {true,false,false,false}};
        Boolean actions[] = new Boolean[]{true,true,true,true,true,true,false,false,
                false,false,true,true,true,true,true,true};
        String currentDateTime= StringUtils.getCurrentTimeStr();
        rolesPage.fillRolForm("rol_"+currentDateTime,permissions,actions);
        rolesPage.clickOnCancelButton();
        Assert.assertTrue(rolesPage.isRolesPageDisplayed(),"Roles is not displayed");
        Assert.assertFalse(rolesPage.isNewRolCreated("rol_"+currentDateTime),"Rol was created");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testCreateAndCancelRol finished");
    }
}