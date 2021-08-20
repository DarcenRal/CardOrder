package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;



public class CardOrderTest {
    @Test
    void shouldEnteringValidValues() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Борис Бритва");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldWithoutPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Борис Бритва");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldWithoutName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyCheckBox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Борис Бритва");
        $("[data-test-id=phone] input").setValue("+79270000000");
        //$("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='agreement']").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldNotValidPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Борис");
        $("[data-test-id=phone] input").setValue("89270000000");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}