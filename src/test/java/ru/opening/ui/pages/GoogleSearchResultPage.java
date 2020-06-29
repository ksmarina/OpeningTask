package ru.opening.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchResultPage {

    public boolean isResultContain(String query){
        boolean isContain = false;
        for (SelenideElement element:results()){
            if (element.attr("href").equals(query)) {
                isContain = true;
            }
        }
        return isContain;
    }

    public void openLink(String query){
        if (isResultContain(query)) {
            $(By.cssSelector(".r a[href=\"" + query + "\"]")).click();
        }
    }

    private ElementsCollection results() {
        return $$(".r a");
    }
}
