package Tests;
import Base.BaseSeleniumTest;
import Pages.CustomerLoginPage;
import Pages.PersonalAreaPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class MainTests extends BaseSeleniumTest {
    @Attachment(value = "Csv report file",fileExtension = ".csv")
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/test/java/FileReport", resourceName));
    }
    @DisplayName("SimbirSoftTest")
    @Description("Открываем главную страницу ZXY Bank, производим авторизацию за пользователя 'Harry Potter', " +
                 "вносим депозит, затем списываем ту же сумму, проверяем, что баланс = 0. После этого, проверяем во вкладке Transactions, " +
                 "что операции по пополнению и списанию отражены в отчете.")
   @Test
    public void mainTests(){
       CustomerLoginPage customerLoginPage = new CustomerLoginPage();
       PersonalAreaPage personalAreaPage = new PersonalAreaPage();

       Allure.step("Открыввем главную страницу и производим авторизацию за пользователя 'Harry Potter' ", step -> {
           customerLoginPage.harryPotterAuthorization();
       });
       Allure.step("Вносим депозит", step -> {
           personalAreaPage.topUpDeposit();
       });
       Allure.step("Списываем депозит", step -> {
           personalAreaPage.writeOffMoney();
       });
       Allure.step("Проверяем, что на балансе остаток = 0", step -> {
        String balanceText = personalAreaPage.getBalanceText();
           Assertions.assertEquals(balanceText,"0");
       });
       Allure.step("Нажимаем на кнопку вкладки Transactions", step -> {
           personalAreaPage.transactionsButtonClick();
           personalAreaPage.calendarInputClick();
       });
       Allure.step("Проверяем, что транзакции отражены в таблице", step -> {
          String debitText = personalAreaPage.getTextDebitAmountCell();
          String creditText = personalAreaPage.getTextCreditAmountCell();
          Assertions.assertEquals(debitText,creditText);
       });
        Allure.step("Формируем отчёт: ", step -> {
           personalAreaPage.createReportExcel();
            getBytes("report.csv");
        });
   }
}
