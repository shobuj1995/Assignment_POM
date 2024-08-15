package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage extends BasePage{
    public Homepage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
//    private static String xpathForMenuItem="(//a[normalize-space()='Sample documents'])[1]";
    public GetDocFilePage clickOnDocumentFileMenu(){
        getElement(By.xpath("//span[normalize-space()='Document']")).click();
        return getInstance(GetDocFilePage.class);
    }

}
