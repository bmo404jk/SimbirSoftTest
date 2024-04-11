package Pages;
import FibonacciNumber.FibonacciNumberGenerator;
import Base.BaseSeleniumPage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
public class PersonalAreaPage extends BaseSeleniumPage {
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    @FindBy(xpath = "//button[@ng-click='deposit()']")
    private WebElement mainDepositButton;
    @FindBy(xpath = "//input[@ng-model='amount']")
    private WebElement depositInputArea;
    @FindBy(xpath = "//button[contains(text(),'Deposit') and not(contains(@ng-click,'deposit()'))]")
    private WebElement floorDepositButton;
    @FindBy(xpath = "//button[@ng-click='withdrawl()']")
    private WebElement mainWithdrawlButton;
    @FindBy(xpath = "//input[@ng-model='amount']")
    private WebElement withdrawInputArea;
    @FindBy(xpath = "//button[contains(text(),'Withdraw') and not(contains(@ng-click,'withdrawl()'))]")
    private WebElement floorWithdrawlButton;
    @FindBy(xpath = "//strong[@class='ng-binding'][2]")
    private WebElement balance;
    @FindBy(xpath = "//tr[@id='anchor1']/td[1]")
    private WebElement debitDataTimeCell;
    @FindBy(xpath = "//tr[@id='anchor1']/td[2]")
    private WebElement debitAmountCell;
    @FindBy(xpath = "//tr[@id='anchor1']/td[3]")
    private WebElement debitTransactionTypeCell;
    @FindBy(xpath = "//tr[@id='anchor0']/td[1]")
    private WebElement creditDataTimeCell;
    @FindBy(xpath = "//tr[@id='anchor0']/td[2]")
    private WebElement creditAmountCell;
    @FindBy(xpath = "//tr[@id='anchor0']/td[3]")
    private WebElement creditTransactionTypeCell;
    @FindBy(xpath = "//button[@ng-click='transactions()']")
    private WebElement transactionsButton;
    @FindBy(xpath = "//input[@ng-model='startDate']")
    private WebElement calendarInput;
    @FindBy(xpath = "//button[@ng-click='back()']")
    private WebElement backButton;
    public String getTextDebitDataTimeCell() {
        return debitDataTimeCell.getText();
    }
    public String getTextDebitAmountCell() {
        return debitAmountCell.getText();
    }
    public String getTextDebitTransactionTypeCell() {
        return debitTransactionTypeCell.getText();
    }
    public String getTextCreditDataTimeCell() {
        return creditDataTimeCell.getText();
    }
    public String getTextCreditAmountCell() {
        return creditAmountCell.getText();
    }
    public String getTextCreditTransactionTypeCell() {
        return creditTransactionTypeCell.getText();
    }
    public PersonalAreaPage() {
        PageFactory.initElements(driver,this);
    }
    public void topUpDeposit()
    {
        FibonacciNumberGenerator fng = new FibonacciNumberGenerator();
        int moneyCount = fng.GetFibonacciNumber();
        mainDepositButton.click();
        depositInputArea.click();
        depositInputArea.sendKeys(Integer.toString(moneyCount));
        floorDepositButton.click();
    }
    public void writeOffMoney()
    {
        FibonacciNumberGenerator fng = new FibonacciNumberGenerator();
        int moneyCount = fng.GetFibonacciNumber();
        mainWithdrawlButton.click();
        wait.until(d -> floorWithdrawlButton.isEnabled());
        depositInputArea.click();
        withdrawInputArea.sendKeys(Integer.toString(moneyCount));
        floorWithdrawlButton.click();
    }
    public void transactionsButtonClick()
    {
        transactionsButton.click();
    }
    public void calendarInputClick()
    {
        wait.until(d -> calendarInput.isEnabled());
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);
        backButton.click();
        transactionsButton.click();
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);

        wait.until(d -> calendarInput.isEnabled());
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);
        backButton.click();
        transactionsButton.click();
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);

        wait.until(d -> calendarInput.isEnabled());
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);
        backButton.click();
        transactionsButton.click();
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);

        wait.until(d -> calendarInput.isEnabled());
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);
        backButton.click();
        transactionsButton.click();
        calendarInput.click();
        calendarInput.sendKeys(Keys.BACK_SPACE);
    }
    public String getBalanceText() {
        return balance.getText();
    }
    public void createReportExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet newSheet = workbook.createSheet("report");

        Row row = newSheet.createRow(0);
        row.createCell(0).setCellValue("Дата и время операции: ");
        row.createCell(1).setCellValue("Сумма транзакции: ");
        row.createCell(2).setCellValue("Тип транзакции: ");

        Row rowCredit = newSheet.createRow(1);
        rowCredit.createCell(0).setCellValue(getTextCreditDataTimeCell());
        rowCredit.createCell(1).setCellValue(getTextCreditAmountCell());
        rowCredit.createCell(2).setCellValue(getTextCreditTransactionTypeCell());

        Row rowDebit = newSheet.createRow(3);
        rowDebit.createCell(0).setCellValue(getTextDebitDataTimeCell());
        rowDebit.createCell(1).setCellValue(getTextDebitAmountCell());
        rowDebit.createCell(2).setCellValue(getTextDebitTransactionTypeCell());

        FileOutputStream fileOut = new FileOutputStream("src/test/java/FileReport/report.csv");
        workbook.write(fileOut);
        fileOut.close();
    }
}

