package ru.opening.ui.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GooglePage {
    public GooglePage open() {
        Selenide.open("");
        return this;
    }

    public GoogleSearchResultPage search(String query) {
        $(By.name("q")).setValue(query);
        $(By.name("btnK")).click();
        return page(GoogleSearchResultPage.class);
    }
}
