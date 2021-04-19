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

        val balanceFirstCardBeforTransfer = dashboardPageOfCards.getFirstCardBalance();
        val balanceSecondCardBeforTransfer = dashboardPageOfCards.getSecondCardBalance();

        dashboardPageOfCardsRecharge.transferMoneyFromFirstCardToTheSecond
                    (DataHelper.getValidAmount(), DataHelper.getFirstCardNumber());

        val balanceFirstCardAfterTransfer = dashboardPageOfCards.getFirstCardBalance();
        val balanceSecondCardAfterTransfer = dashboardPageOfCards.getSecondCardBalance();
        }


    @Test
    void shouldNotTransferMoneyFromFirstCardToTheSecond2() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPageOfCardsRecharge = new DashboardPageOfCardsRecharge();

        val dashboardPageOfCards = new DashboardPageOfCards();

        val balanceFirstCardBeforTransfer = dashboardPageOfCards.getFirstCardBalance();
        val balanceSecondCardBeforTransfer = dashboardPageOfCards.getSecondCardBalance();

        dashboardPageOfCardsRecharge.transferMoneyFromFirstCardToTheSecond
                (DataHelper.getInvalidAmount(), DataHelper.getFirstCardNumber());

        val balanceFirstCardAfterTransfer = balanceFirstCardBeforTransfer;
        val balanceSecondCardAfterTransfer = balanceSecondCardBeforTransfer;

    }

}
