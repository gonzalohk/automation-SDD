package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecordsPage extends BasePage {

    private By recordsDNATitleLabel = By.xpath("//*[@id=\"kt_subheader\"]/div/div/h3");

    private By optionsRecordOneButton = By.xpath("//*[@id=\"kt_content\"]/div[2]/div[2]/div/div[2]/div/div/div[2]/div/div/div/table/tbody/tr[1]/td[8]");
    private By optionsRecordOneEditButton = By.xpath("//*[@id=\"menu-age\"]/div[3]/ul/li[4]");

    private By addChildrenButton = By.xpath("//*[@id=\"kt_content\"]/div[2]/div[4]/div[2]/div/div[1]/div/div[2]/div/div[2]/button");
    private By childNameInput = By.xpath("//*[@id=\"name\"]");
    private By childLastNameInput = By.xpath("//*[@id=\"last_name\"]");
    private By genderOption = By.xpath("//*[@id=\"inline-Masculino-1\"]");
    private By addressAreaUrbanOption = By.xpath("//*[@id=\"inline-Urbano-1\"]");
    private By birthdayDateInput = By.xpath("//*[@id=\"kt_content\"]/div[2]/div[4]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/form/div[3]/div[1]/input");
    private By educationInput = By.xpath("//*[@id=\"downshift-9-input\"]");
    private By occupationInput = By.xpath("//*[@id=\"downshift-10-input\"]");

    private By saveChildButton = By.xpath("//*[contains(text(), 'Guardar')]");
    private By cancelChildButton = By.xpath("//*[contains(text(), 'Cancelar')]");

    public RecordsPage(WebDriver webDriver){
        super(webDriver);
    }

    public void fillChildrenForm(String name, String lastName, String birthday, String education,
                                 String occupation){
        typeChildName(name);
        typeChildLastName(lastName);
        typeBirthday(birthday);
        selectChildEducation(education);
        selectChildOccupation(occupation);
        selectUrbanAddressOption();
        selectMaleGenderOption();
    }

    public void typeChildName(String userName){
        WebElement element = webDriver.findElement(childNameInput);
        element.sendKeys(userName);
    }

    public void typeChildLastName(String userLastName){
        WebElement element = webDriver.findElement(childLastNameInput);
        element.sendKeys(userLastName);
    }

    public void typeBirthday(String userEmail){
        WebElement element = webDriver.findElement(birthdayDateInput);
        element.sendKeys(userEmail);
    }

    public void selectMaleGenderOption(){
        WebElement element = webDriver.findElement(genderOption);
        element.click();
    }
    public void selectUrbanAddressOption(){
        WebElement element = webDriver.findElement(addressAreaUrbanOption);
        element.click();
    }

    public void selectChildEducation(String education){
        WebElement element = webDriver.findElement(educationInput);
        element.click();
        By districtItem = By.xpath("//*[contains(text(), '"+education+"')]");
        WebElement itemElement = webDriver.findElement(districtItem);
        itemElement.click();
    }

    public void selectChildOccupation(String occupation){
        WebElement element = webDriver.findElement(occupationInput);
        element.click();
        By rolItem = By.xpath("//*[contains(text(), '"+occupation+"')]");
        WebElement itemElement = webDriver.findElement(rolItem);
        itemElement.click();
    }

    public void clickOnSaveButton(){
        WebElement element = webDriver.findElement(saveChildButton);
        element.click();
        helper.ScreenShotHelper.takeScreenShotAndAdToHTMLReport(webDriver, Status.INFO, "addNewChild");
    }

    public void clickOnCancelButton(){
        WebElement element = webDriver.findElement(cancelChildButton);
        element.click();
    }

    public void clickOnOptionsRecordOneButton(){
        WebElement element = webDriver.findElement(optionsRecordOneButton);
        element.click();
    }

    public void clickOnOptionsRecordOneEditButton(){
        WebElement element = webDriver.findElement(optionsRecordOneEditButton);
        element.click();
    }

    public void clickOnAddChildrenButton(){
        WebElement element = webDriver.findElement(addChildrenButton);
        element.click();
    }

    public boolean isRecordsPageDisplayed(){
        return webDriver.findElement(recordsDNATitleLabel).isDisplayed();
    }

    public boolean isNewChildCreated(String childName){
        try {
            WebDriverWait wait = new WebDriverWait(webDriver,5);
            By newChildName = By.xpath("//*[contains(text(), '"+ childName +"')]");
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(newChildName)));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
