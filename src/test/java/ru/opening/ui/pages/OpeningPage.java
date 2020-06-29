package ru.opening.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$$;

public class OpeningPage {
    public OpeningPage open() {
        Selenide.open("");
        return this;
    }

    public ElementsCollection getCurrencyPrices(){
        ElementsCollection exchanges = $$(".main-page-exchange__row");
        return exchanges;
    }

}
