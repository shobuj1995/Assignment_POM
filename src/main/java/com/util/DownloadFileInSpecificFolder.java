package com.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DownloadFileInSpecificFolder {

    // Define the download location
    String downloadFilepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"+ File.separator + "resources" + File.separator + "download";
    String expectedFileName = "Samplefile";

    @Test
    public void verifyDownloadFile() throws InterruptedException {
        // Set ChromeOptions to specify the download directory
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        // Set the download directory
        prefs.put("download.default_directory", downloadFilepath);

        // Other options you might need
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", "true");

        options.setExperimentalOption("prefs", prefs);

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Navigate to the sample file page
        driver.get("https://samplelib.com/sample-jpeg.html");

        // Click the download link
        driver.findElement(By.xpath("(//a[contains(text(),'Download')])[1]")).click();

        // Wait for the file to be downloaded (could be replaced by a more robust wait method)
        Thread.sleep(5000); // 5 seconds wait

        // Verify the file has been downloaded
        File folder = new File(downloadFilepath);
        Assert.assertTrue(folder.exists() && folder.isDirectory(), "Download directory does not exist");

        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File downloadedFile = null;

        if (listOfFiles != null) {
            // Check each file in the directory
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().contains(expectedFileName)) {
                    found = true;
                    downloadedFile = file;
                    break;
                }
            }
        }

        // Assert that the file was found
        Assert.assertTrue(found, "Downloaded document is not found");
        System.out.println("Downloaded file: " + (downloadedFile != null ? downloadedFile.getAbsolutePath() : "File not found"));

        // Clean up
        driver.quit();
    }
}
