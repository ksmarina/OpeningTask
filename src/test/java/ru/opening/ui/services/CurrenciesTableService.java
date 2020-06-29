package ru.opening.ui.services;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.opening.ui.businessObjects.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesTableService {
    private List<Currency> currenciesTable;

    private List<Currency> getCurrenciesTable (ElementsCollection currenciesElements){
        currenciesTable= new ArrayList<Currency>();
        for (SelenideElement element:currenciesElements){
            Currency currency = new Currency();
            currency.setName(element.find(".main-page-exchange__currency-name").getText());
            currency.setBuyPrice(Double.parseDouble(element.findAll(".main-page-exchange__rate").get(0).getText().replace(',', '.')));
            currency.setSellPrice(Double.parseDouble(element.findAll(".main-page-exchange__rate").get(1).getText().replace(',', '.')));
            currenciesTable.add(currency);
        }

        return currenciesTable;
    }

    public boolean checkThatBuyLessThanSell(String curName, ElementsCollection currenciesElements){
        boolean isBuyLessThanSell = false;
        getCurrenciesTable(currenciesElements);
        for (Currency cur:currenciesTable) {
            if (cur.getName().equals(curName)){
                isBuyLessThanSell = (cur.getBuyPrice()<cur.getSellPrice());
            }
        }
        return isBuyLessThanSell;
    }
}
