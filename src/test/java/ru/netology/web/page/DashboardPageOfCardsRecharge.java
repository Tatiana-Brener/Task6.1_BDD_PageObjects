package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPageOfCardsRecharge {

    public DashboardPageOfCards transferMoneyFromFirstCardToTheSecond(String amount, DataHelper.CardNumber cardNumber) {

        val dashboradPageOfCards = new DashboardPageOfCards();
        if(dashboradPageOfCards.getFirstCardBalance() >= Integer.parseInt(amount)) {
            $$("[data-test-id=action-deposit]").first().click();
            $(withText("Пополнение карты")).shouldBe(Condition.visible);
            $("[data-test-id=amount] .input__control").setValue(amount);
            $("[data-test-id=from] .input__control").setValue(cardNumber.getNumber());
            $("[data-test-id=action-transfer]").click();
        }
        return new DashboardPageOfCards();
    }

    public DashboardPageOfCards transferMoneyFromSecondCardToTheFirst(String amount, DataHelper.CardNumber cardNumber) {

        val dashboradPageOfCards = new DashboardPageOfCards();
        if(dashboradPageOfCards.getSecondCardBalance() >= Integer.parseInt(amount)) {
            $$("[data-test-id=action-deposit]").last().click();
            $(withText("Пополнение карты")).shouldBe(Condition.visible);
            $("[data-test-id=amount] .input__control").setValue(amount);
            $("[data-test-id=from] .input__control").setValue(cardNumber.getNumber());
            $("[data-test-id=action-transfer]").click();
        }
        return new DashboardPageOfCards();
    }
}
