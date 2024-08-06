import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;



public class Autotest {
    @Test
    public void testPageUrlAndTitle() {
        Selenide.open("https://www.lhv.ee/et/liising#kalkulaator");

        String url = WebDriverRunner.url();
        Assert.assertEquals(url, "https://www.lhv.ee/et/liising#kalkulaator");

        String title = Selenide.title();
        Assert.assertEquals(title, "Liising · LHV");
    }

    @Test
    public void testElemetsExist() {
        // Open page url
        Selenide.open("https://www.lhv.ee/et/liising");

        //passing cookie monster
        if (Selenide.$(By.xpath("//input[contains(@id, \"account_type-1\") and @value=\"C\"]")).exists()) {
            Selenide.$(By.xpath("//a[@id=\"acceptPirukas\"]")).click();
        }

        // navigate to calculator
        Selenide.$(By.xpath("//a[contains(@href, \"#kalkulaator\")]")).click();

        // check that the checkbox is visible
        Selenide.$(By.xpath("//label[@for='account_type-1']")).shouldHave(Condition.text("juriidilise isikuna")).shouldBe(Condition.visible);
        // click on "juriidiline isik" checkbox, so it would be checked
        Selenide.$(By.xpath("//input[contains(@id, \"account_type-1\") and @value=\"C\"]")).click();

        // verify row names by xpath
        Selenide.$(By.xpath("//label[@for='account_type']")).shouldHave(Condition.text("Soovin liisingut"));
        Selenide.$(By.xpath("//label[@for='lease_type']")).shouldHave(Condition.text("Liisingu tüüp"));
        Selenide.$(By.xpath("//label[@for='price']")).shouldHave(Condition.text("Sõiduki hind"));
        Selenide.$(By.xpath("//label[@for='initial_percentage']")).shouldHave(Condition.text("Sissemakse"));
        Selenide.$(By.xpath("//label[@for='period']")).shouldHave(Condition.text("Liisingu periood"));
        Selenide.$(By.xpath("//label[@for='interest_rate']")).shouldHave(Condition.text("Intress"));
        Selenide.$(By.xpath("//label[@for='reminder_percentage']")).shouldHave(Condition.text("Jääkväärtus"));

        // checking extra row that appears after checkbox is clicked
        Selenide.$(By.xpath("//label[@for='vat_included']")).shouldHave(Condition.text("Hind sisaldab käibemaksu"));

    }
}
