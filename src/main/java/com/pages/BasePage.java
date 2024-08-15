package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage extends Page{
    public BasePage(WebDriver driver,WebDriverWait wait) {
        super(driver,wait);
    }
    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }
    @Override
    public String getPageHeader(By locator) {
        return getElement(locator).getText();
    }
    @Override
    public WebElement getElement(By locator) {
        try {
            return getWait().until(driver -> {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    return elements.get(0);
                } else {
                    return null;
                }
            });
        }catch (Exception e){
            System.out.println("Some error occured while creating element"+locator.toString());
            e.printStackTrace();
        }
        return null;
    }
    public WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait;
    }
    @Override
    public void waitForElementPresent(By locator) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        }catch (Exception e){
            System.out.println("Some Exception occured while waiting for the element"+locator.toString());
        }

    }
    @Override
    public void waitForPageTitle(String title) {
        try {
            wait.until(ExpectedConditions.titleContains(title));
        }catch (Exception e){
            System.out.println("Some Exception occured while waiting for the title"+ title);
        }
    }
    public void doClick(By locator){
        driver.findElement(locator).click();
    }
    public void doSendKeys(By locator,String text){
        driver.findElement(locator).sendKeys(text);
    }
    public String doGetText(By locator){
        return driver.findElement(locator).getText();
    }
}
