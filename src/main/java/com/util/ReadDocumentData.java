package com.util;

import com.models.Employee;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadDocumentData {

    public static void readWordFile(String filePath) {
        FileInputStream fis = null;
        HWPFDocument document = null;

        try {
            // Open the Word file
            fis = new FileInputStream(filePath);
            document = new HWPFDocument(fis);

            // Extract text from the document
            WordExtractor extractor = new WordExtractor(document);
            String text = extractor.getText();

            // Print out the text
            System.out.println(text);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the document and input stream
            try {
                if (document != null) {
                    document.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Employee> getTableData(String filePath) {
        Employee employee = new Employee();
        List<Employee> employeeList=new ArrayList<>();
        // Assuming you have a default constructor
        try {
            FileInputStream fis = new FileInputStream(filePath);
            HWPFDocument document = new HWPFDocument(fis);

            // TableIterator iterates over tables in the document
            TableIterator tableIterator = new TableIterator(document.getRange());
            while (tableIterator.hasNext()) {
                Table table = tableIterator.next();

                // Assuming the first row contains the headers and the subsequent rows contain data
                for (int rowIndex = 1; rowIndex < table.numRows(); rowIndex++) {
                    TableRow row = table.getRow(rowIndex);

                    // Assuming specific columns for employee data
                    String id = row.getCell(0).getParagraph(0).text().trim();
                    String name = row.getCell(1).getParagraph(0).text().trim();
                    String department = row.getCell(2).getParagraph(0).text().trim();

                    // Set data to employee object
                    employee.setId(id);
                    employee.setName(name);
                    employee.setDesignation(department);

                    System.out.println("Name: " + name);
                    System.out.println("ID: " +id );
                    System.out.println("Department: " +department );
                    employeeList.add(employee);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employeeList;
    }

}
