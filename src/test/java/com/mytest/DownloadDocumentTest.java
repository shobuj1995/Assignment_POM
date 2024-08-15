package com.mytest;

import com.base.BaseTest;
import com.models.Employee;
import com.pages.DocumentDownloadPage;
import com.pages.GetDocFilePage;
import com.pages.Homepage;
import com.util.ReadDocumentData;
import com.util.ReadJsonFile;
import com.util.ReadJsonUsingDataProvier;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DownloadDocumentTest extends BaseTest {
    @Test()
    public void openDocumentFilePage() throws InterruptedException {
        page.getInstance(DocumentDownloadPage.class).downloadDocument();
        Thread.sleep(5000);
    }
    @Test
    public static void verifyDocumentDataWithJsonFile() throws IOException, ParseException {
        String docFilePath="src/test/resources/download/TestData.doc";
        String jsonFilePath="src/test/resources/base/TestJson.json";
        List<Employee> employeesFromDocFile= ReadDocumentData.getTableData(docFilePath);
        List<Employee> employeesFromJsonFile= ReadJsonFile.jsonReader(jsonFilePath);
        String nameFromJson=employeesFromJsonFile.get(0).getName().trim();
        String nameFromDoc=employeesFromDocFile.get(0).getName().trim();
        System.out.println("Name from Json: "+nameFromJson);
        System.out.println("Name from Doc: "+nameFromDoc);
        Assert.assertTrue(nameFromJson.equals(nameFromDoc));
//        System.out.println(employeesFromJsonFile.get(0).getName().equals(employeesFromDocFile.get(0).getName()));
    }
    @Test(dataProvider = "Json_Parsing",dataProviderClass = ReadJsonUsingDataProvier.class)
    public void verifyJsonDataUsingDataProvider(String data) {
        // Split the data into username and password
        String[] user = data.split(",");
        String docFilePath="src/test/resources/download/TestData.doc";
        List<Employee> employeesFromDocFile= ReadDocumentData.getTableData(docFilePath);
        System.out.println(employeesFromDocFile.get(0).getName().trim().equalsIgnoreCase(user[1].trim()));
        System.out.println("Username from JSON file is --> " + user[1]);
        System.out.println("Department from JSON file is --> " + user[2]);
    }
}
