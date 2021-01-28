package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage extends BasePage {

    private By usersTitleLabel= By.xpath("//*[@id=\"kt_subheader\"]/div/div/h3");
    private By createUserButton = By.xpath("//*[contains(text(), 'Crear nuevo Usuario')]");
    private By saveUserButton = By.xpath("//*[contains(text(), 'Guardar')]");
    private By cancelUserButton = By.xpath("//*[contains(text(), 'Cancelar')]");

    private By userNameInput = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/div[2]/div/div/div/form/div[1]/div[1]/input");
    private By userLastNameInput = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/div[2]/div/div/div/form/div[1]/div[2]/input");
    private By userEmailInput = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/div[2]/div/div/div/form/div[2]/div[1]/input");
    private By userDistrictInput = By.xpath("//*[@id=\"downshift-multiple-input\"]");
    private By userRolInput = By.xpath("//*[@id=\"downshift-0-input\"]");
    private By userPositionInput = By.xpath("//*[@id=\"downshift-1-input\"]");

    private By nameErrorMessage = By.xpath("//*[contains(text(), 'El nombre es requerido')]");

    public UsersPage(WebDriver webDriver){
        super(webDriver);
    }

    public void fillUserForm(String userName, String lastName, String email, String [] district, String rol, String position){
        typeUserName(userName);
        typeUserLastName(lastName);
        typeUserEmail(email);
        selectUserDistrict(district);
        selectUserRol(rol);
        selectUserPosition(position);
        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "Fill User Form");
    }

    public void typeUserName(String userName){
        WebElement element = webDriver.findElement(userNameInput);
        element.sendKeys(userName);
    }

    public void typeUserLastName(String userLastName){
        WebElement element = webDriver.findElement(userLastNameInput);
        element.sendKeys(userLastName);
    }

    public void typeUserEmail(String userEmail){
        WebElement element = webDriver.findElement(userEmailInput);
        element.sendKeys(userEmail);
    }

    public void selectUserDistrict(String [] userDistrict){
        WebElement element = webDriver.findElement(userDistrictInput);
        element.click();
        element.sendKeys("d");//workaround
        By districtItem = By.xpath("//*[contains(text(), '"+userDistrict[0]+"')]");
        WebElement itemElement = webDriver.findElement(districtItem);
        itemElement.click();
    }

    public void selectUserRol(String rolName){
        WebElement element = webDriver.findElement(userRolInput);
        element.click();
        By rolItem = By.xpath("//*[contains(text(), '"+rolName+"')]");
        WebElement itemElement = webDriver.findElement(rolItem);
        itemElement.click();
    }

    public void selectUserPosition(String userPosition){
        WebElement element = webDriver.findElement(userPositionInput);
        element.click();
        By positionItem = By.xpath("//*[contains(text(), '"+userPosition+"')]");
        WebElement itemElement = webDriver.findElement(positionItem);
        itemElement.click();
    }
    public void clickOnCreateUserButton(){
        WebElement element = webDriver.findElement(createUserButton);
        element.click();
    }

    public void clickOnSaveButton(){
        WebElement element = webDriver.findElement(saveUserButton);
        element.click();
        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "saveNewUser");
    }

    public void clickOnCancelButton(){
        WebElement element = webDriver.findElement(cancelUserButton);
        element.click();
    }

    public boolean isUserPageDisplayed(){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,3);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(usersTitleLabel)));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isNameErrorMessageVisible(){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,3);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(nameErrorMessage)));
            helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.WARNING, "nameEmpty");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isNewUserCreated(String userName){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,5);
            By newRoleName = By.xpath("//*[contains(text(), '"+ userName +"')]");
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(newRoleName)));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
