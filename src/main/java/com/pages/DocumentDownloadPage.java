package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DocumentDownloadPage extends BasePage {
    public DocumentDownloadPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public DocumentDownloadPage downloadDocument() {
        try {
            Thread.sleep(2000);
            getElement(By.xpath("//a[@class='download_doc']")).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Download Button has been clicked");
        return this;

    }
}
