package ru.opening.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.opening.ui.pages.GooglePage;
import ru.opening.ui.pages.GoogleSearchResultPage;
import ru.opening.ui.pages.OpeningPage;
import ru.opening.ui.services.CurrenciesTableService;

import static com.codeborne.selenide.Selenide.open;

public class OpeningTest {
    private GooglePage googlePage;
    private GoogleSearchResultPage resultPage;
    private OpeningPage openingPage;
    private CurrenciesTableService exchangeService;
    private String searchQuery = "Открытие";
    private String searchSite = "https://www.open.ru/";

    @BeforeClass
    public void setup() {
        Configuration.baseUrl = "http://google.com/";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        exchangeService = new CurrenciesTableService();
        googlePage = new GooglePage();
    }

    @Test
    public void searchOpeningSiteTest() {
        resultPage = googlePage.open().search(searchQuery);
        Assert.assertTrue(resultPage.isResultContain(searchSite), "Results don't contain specified string: " + searchQuery);
    }

    @Test
    public void openBankSiteTest() {
        googlePage.open().search(searchQuery).openLink(searchSite);
        Assert.assertEquals(searchSite, WebDriverRunner.url());
    }

    @Test
    public void currencyPriceCheckTest() {
        //In case of google site unavailable we will check bank's site independently if open the direct link
        openingPage = open(searchSite, OpeningPage.class);
        Assert.assertTrue(exchangeService.checkThatBuyLessThanSell("USD", openingPage.getCurrencyPrices()));
        Assert.assertTrue(exchangeService.checkThatBuyLessThanSell("EUR", openingPage.getCurrencyPrices()));
    }
}
