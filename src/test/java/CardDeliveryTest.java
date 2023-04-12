import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {
    LocalDate currentDate = LocalDate.now();
    LocalDate meetingDate = currentDate.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @Test
    void shouldCheckPositive() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(formatter.format(meetingDate));
        $("[data-test-id=name] input").setValue("Ольга Александрова");
        $("[data-test-id=phone] input").setValue("+79001112233");
        $(" [data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).shouldBe(Condition.appear, Duration.ofSeconds(15));
    }
}
