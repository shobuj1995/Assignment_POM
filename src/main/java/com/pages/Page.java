package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
    WebDriver driver;
    WebDriverWait wait;

    public Page(WebDriver driver,WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
    }

    //AbstractMethod
    public abstract String getPageTitle();

    public abstract String getPageHeader(By locator);

    public abstract WebElement getElement(By locator);

    public abstract void waitForElementPresent(By locator);

    public abstract void waitForPageTitle(String title);

    public <T extends BasePage> T getInstance(Class<T> pageClass)
    {
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class,WebDriverWait.class).newInstance(this.driver,this.wait);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
