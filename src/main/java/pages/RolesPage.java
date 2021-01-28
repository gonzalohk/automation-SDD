package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RolesPage extends BasePage {

    private By rolesTitleLabel= By.xpath("//*[@id=\"kt_subheader\"]/div/div/h3");
    private By createRolButton = By.xpath("//*[contains(text(), 'Crear nuevo Rol')]");
    private By saveRolButton = By.xpath("//*[contains(text(), 'Guardar')]");
    private By cancelRolButton = By.xpath("//*[contains(text(), 'Cancelar')]");

    private By rolNameInput = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/div[2]/div/div/div/form/div[1]/div/input");
    private By nameErrorMessage = By.xpath("//*[contains(text(), 'El nombre es requerido')]");

    public RolesPage(WebDriver webDriver){
        super(webDriver);
    }

    public void fillRolForm(String rolName,Boolean [][] permissions, Boolean [] actions){
        typeRolName(rolName);
        setupPermissions(permissions);
        setupActions(actions);
    }

    public void typeRolName(String rolName){
        WebElement element = webDriver.findElement(rolNameInput);
        element.clear();
        element.sendKeys(rolName);
    }

    public void setupPermissions(Boolean [][] permissions){
        for (int i=1;i<=4;i++){
            for (int j=2;j<=5;j++){
                if (permissions[i-1][j-2]){
                    By permissionCheck = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/div[2]/div/div/div/form/div[2]/div/table/tbody/tr["+i+"]/td["+j+"]/div/input");
                    WebElement element = webDriver.findElement(permissionCheck);
                    element.click();
                }
            }
        }
    }

    public void setupActions(Boolean [] actions){
        for (int i=1;i<=16;i++){
            if (actions[i-1]){
                By permissionCheck = By.xpath("//*[@id=\"kt_content\"]/div[2]/div/div/div[2]/div/div/div/form/div[3]/div/table/tbody/tr["+i+"]/td[2]/div/input");
                WebElement element = webDriver.findElement(permissionCheck);
                element.click();
            }
        }
    }

    public void clickOnCreateRolButton(){
        WebElement element = webDriver.findElement(createRolButton);
        element.click();
    }

    public void clickOnSaveButton(){
        WebElement element = webDriver.findElement(saveRolButton);
        element.click();
        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "saveNewRol");
    }

    public void clickOnCancelButton(){
        WebElement element = webDriver.findElement(cancelRolButton);
        element.click();
    }

    public boolean isRolesPageDisplayed(){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,3);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(rolesTitleLabel)));
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
    public boolean isNewRolCreated(String rolName){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,5);
            By newRoleName = By.xpath("//*[contains(text(), '"+ rolName +"')]");
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(newRoleName)));
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
