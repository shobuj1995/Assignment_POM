package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetDocFilePage extends BasePage{
    public GetDocFilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public DocumentDownloadPage openDocumentFilePage(){
        getElement(By.xpath("//a[normalize-space()='Get .doc samples']")).click();
        return getInstance(DocumentDownloadPage.class);
    }
}
