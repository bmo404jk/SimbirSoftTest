package Pages;
import Base.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CustomerLoginPage extends BaseSeleniumPage {
    @FindBy(xpath ="//button[@ng-click='customer()']")
    private WebElement buttonCustomerLogin;
    @FindBy(xpath ="//select[@ng-model='custId']")
    private  WebElement inputAreaName;
    @FindBy(xpath = "//option[@value='2']")
    private  WebElement currentUserInInputArea;
    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private  WebElement loginButton;
    private  String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    public void loginButtonClick()
    {
        loginButton.click();
    }
    public void currentUserInInputAreaClick()
    {
        currentUserInInputArea.click();
    }
    public void inputAreaNameClick()
    {
        inputAreaName.click();
    }
    public void buttonCustomerLoginClick()
    {
        buttonCustomerLogin.click();
    }
    public CustomerLoginPage() {
        driver.get(URL);
        PageFactory.initElements(driver,this);
    }
    public void harryPotterAuthorization()
    {
        buttonCustomerLoginClick();
        inputAreaNameClick();
        currentUserInInputAreaClick();
        loginButtonClick();
    }
}