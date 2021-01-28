package testsuite.testcase.record;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RecordsPage;
import pages.RolesPage;
import utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordTest extends BaseTest {
    private RecordsPage recordsPage;
    @BeforeMethod
    public void init(){
        webDriver.get(URL);
        LoginPage loginPage = new LoginPage(webDriver);
        DashboardPage dashboardPage = loginPage.loginAs("admin2@admin.com","admin123$");
        recordsPage = dashboardPage.clickOnMenuRecords();
    }

    @Test
    public void testAddChildToRecord() {
        recordsPage.clickOnOptionsRecordOneButton();
        recordsPage.clickOnOptionsRecordOneEditButton();
        recordsPage.clickOnAddChildrenButton();
        String cDateTime= StringUtils.getCurrentTimeStr();
        recordsPage.fillChildrenForm("child_" + cDateTime, "lastname_" + cDateTime,
                "02/01/2010","Primaria","Estudiante");
        recordsPage.clickOnSaveButton();
        Assert.assertTrue(recordsPage.isNewChildCreated("child_" + cDateTime),"Child was not added");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testAddChildToRecord finished");
    }

    @Test
    public void testAddChildWithoutLastNameData() {
        recordsPage.clickOnOptionsRecordOneButton();
        recordsPage.clickOnOptionsRecordOneEditButton();
        recordsPage.clickOnAddChildrenButton();
        String cDateTime= StringUtils.getCurrentTimeStr();
        recordsPage.fillChildrenForm("child_" + cDateTime, "","08/08/2005","Bachiller","Estudiante");
        recordsPage.clickOnSaveButton();
        Assert.assertFalse(recordsPage.isNewChildCreated("child_" + cDateTime),"Child was added");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testAddChildWithoutLastNameData finished");
    }

    @Test
    public void testCancelAddChildToRecord() {
        recordsPage.clickOnOptionsRecordOneButton();
        recordsPage.clickOnOptionsRecordOneEditButton();
        recordsPage.clickOnAddChildrenButton();
        String cDateTime= StringUtils.getCurrentTimeStr();
        recordsPage.fillChildrenForm("child_" + cDateTime, "lastname_" + cDateTime,
                "12/11/2005","Bachiller","Estudiante");
        recordsPage.clickOnCancelButton();
        Assert.assertFalse(recordsPage.isNewChildCreated("children_" + cDateTime),"Child was added");

        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "testCancelAddChildToRecord finished");
    }
}