package pages;

import jdk.jfr.consumer.RecordedClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage{

    private By dashboardPageContainer = By.id("kt_wrapper");

    private By topbarButton = By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[1]");
    private By topbarLogoutButton = By.xpath("//*[@id=\"kt_header\"]/div[3]/div[2]/div[2]/div/div/a");

    private By menuMainPage = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[1]/a/span");
    private By menuSearchCases = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[2]/a/span");
    private By menuUsers = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[3]/a/span");
    private By menuRecords = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[4]/a/span");
    private By menuFormDNA = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[5]/a/span");
    private By menuRoles = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[6]/a/span");
    private By menuReports = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[7]/a/span");
    private By menuConfigurations = By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[8]/a/span");

    private  By menuDistrict = By.xpath("//*[@id=\"kt_header\"]/div[2]/button");


    public DashboardPage(WebDriver webDriver){
        super(webDriver);
    }

    public void clickOnTopBarButton(){
        WebElement element = webDriver.findElement(topbarButton);
        element.click();
    }
    public void clickOnTopBarLogoutButton(){
        WebElement element = webDriver.findElement(topbarLogoutButton);
        element.click();
    }
    public UsersPage clickOnMenuUsers(){
        WebElement element = webDriver.findElement(menuUsers);
        element.click();
        return new UsersPage(webDriver);
    }
    public RecordsPage clickOnMenuRecords(){
        WebElement element = webDriver.findElement(menuRecords);
        element.click();
        return new RecordsPage(webDriver);
    }

    public RolesPage clickOnMenuRoles(){
        WebElement element = webDriver.findElement(menuRoles);
        element.click();
        return new RolesPage(webDriver);
    }

    public void clickOnMenuRecordsItem(Integer id){
        By menuDistrictItem = By.xpath("//*[@id=\"simple-menu\"]/div[3]/ul/li["+id+"]");
        WebElement element = webDriver.findElement(menuDistrict);
        element.click();
    }

    public void clickOnMenuFormDNA(){
        WebElement element = webDriver.findElement(menuFormDNA);
        element.click();
    }

    public boolean isDashboardPageDisplayed(){
        try {
            return webDriver.findElement(dashboardPageContainer).isDisplayed();
        }catch (Exception e){
            return false;
        }

    }
}
