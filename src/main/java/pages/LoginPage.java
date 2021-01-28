package pages;

import com.aventstack.extentreports.Status;
import helper.ScreenShotHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private By loginPageContainer= By.xpath("//*[@id=\"kt_login\"]");
    private By userInput = By.xpath("//*[@id=\"kt_login\"]/div/div[2]/div/div/form/div[1]/div/div/input");
    private By passwordInput = By.xpath("//*[@id=\"kt_login\"]/div/div[2]/div/div/form/div[2]/div/div/input");
    private By loginButton = By.xpath("//*[@id=\"kt_login_signin_submit\"]");
    private By errorMessageLogin = By.xpath("//*[contains(text(), 'Credenciales Incorrectos')]");
    private By errorMessageEmail = By.xpath("//*[contains(text(), 'Email invalido')]");


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public DashboardPage loginAs(String userName, String password){
        typeUserName(userName);
        typePassword(password);
        clickOnLoginButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "Login");
        return new DashboardPage(webDriver);
    }

    public void typeUserName(String userName){
        WebElement element = webDriver.findElement(userInput);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        element.sendKeys(userName);
    }

    public void typePassword(String password){
        WebElement element = webDriver.findElement(passwordInput);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        element.sendKeys(password);
    }

    public void clickOnLoginButton(){
        WebElement element = webDriver.findElement(loginButton);
        element.click();
    }

    public boolean isLoginErrorMessageVisible(){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,15);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(errorMessageLogin)));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isEmailErrorMessageVisible(){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,15);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(errorMessageEmail)));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isLoginPageDisplayed(){
        return webDriver.findElement(loginPageContainer).isDisplayed();
    }
}