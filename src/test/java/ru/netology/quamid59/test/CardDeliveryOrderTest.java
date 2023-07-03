package ru.netology.quamid59.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.StringUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.quamid59.data.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryOrderTest {
//    private String generateDate(int addDays, String pattern) {
//        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));

//    }
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var dateFormat = "dd.MM.yyyy";
        var addDaysFirstMeeting = 4;
        var meetingDate = DataGenerator.generateDate(addDaysFirstMeeting, dateFormat);
        var addDaysSecondMeeting = 5;
        var secondMeetingDate = DataGenerator.generateDate(addDaysSecondMeeting, dateFormat);
        var validCity = DataGenerator.generateCity();
        var validPhone = DataGenerator.generatePhone("ru");
        var validName = DataGenerator.generateName("ru");

        $("[data-test-id='city'] [placeholder='Город']").setValue(validCity);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(meetingDate);
        $("[data-test-id='name'] [type=text]").setValue(validName);
        $("[data-test-id='phone'] [type=tel]").setValue(validPhone);
        $x("//*[contains(@class,'checkbox__text')]").click();
        $x("//*[contains(text(),'Запланировать')]").click();
        $("[data-test-id='success-notification'] [class='notification__content']").should(Condition.visible, Duration.ofSeconds(10));
        $("[data-test-id='success-notification'] [class='notification__content']").shouldHave(Condition.exactText("Встреча успешно запланирована на " + meetingDate));
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").setValue(secondMeetingDate);
        $x("//*[contains(text(),'Запланировать')]").click();
        $x("//span[contains(@class,button__text) and contains(text(),'Перепланировать')]").click();
        $("[data-test-id='success-notification'] [class='notification__content']").should(Condition.visible, Duration.ofSeconds(10));
        $("[data-test-id='success-notification'] [class='notification__content']").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }


}