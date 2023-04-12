import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {
    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldCheckPositive() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(planningDate);
        $("[data-test-id=name] input").setValue("Ольга Александрова");
        $("[data-test-id=phone] input").setValue("+79001112233");
        $(" [data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
