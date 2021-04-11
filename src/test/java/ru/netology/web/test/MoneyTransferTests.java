package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPageOfCards;
import ru.netology.web.page.DashboardPageOfCardsRecharge;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTests {

    @Test
    void shouldTransferMoneyFromFirstCardToTheSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPageOfCardsRecharge = new DashboardPageOfCardsRecharge();

        val dashboardPageOfCards = new DashboardPageOfCards();
//        val dataHelper = new DataHelper();
//        val amountForTransfer = DataHelper.getValidAmount();
        if (dashboardPageOfCards.getFirstCardBalance() >= Integer.parseInt(DataHelper.getValidAmount())) {
            dashboardPageOfCardsRecharge.transferMoneyFromFirstCardToTheSecond
                    (DataHelper.getValidAmount(), DataHelper.getFirstCardNumber());
        }
        dashboardPageOfCards.getFirstCardBalance();
        dashboardPageOfCards.getSecondCardBalance();
        }

    @Test
    void shouldNotTransferMoneyFromFirstCardToTheSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPageOfCardsRecharge = new DashboardPageOfCardsRecharge();

        dashboardPageOfCardsRecharge.transferMoneyFromFirstCardToTheSecond
                (DataHelper.getInvalidAmount(), DataHelper.getFirstCardNumber());
        val dashboardPageOfCards = new DashboardPageOfCards();

        dashboardPageOfCards.getFirstCardBalance();
        dashboardPageOfCards.getSecondCardBalance();
    }

}
