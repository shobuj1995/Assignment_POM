package com.util;

import com.models.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadJsonFile {
    public static List<Employee> jsonReader(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        // Use the correct file path for your JSON file
        FileReader fileReader = new FileReader(filePath);

        // Parse the JSON file
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        // Retrieve the "userlogins" array from the JSON object
        JSONArray array = (JSONArray) jsonObject.get("Employees");

        // Create an array to store usernames and passwords
        List<Employee> employeeList = new ArrayList<>();
        Employee employee=new Employee();

        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String id = (String) users.get("id");
            String name = (String) users.get("name");
            String department = (String) users.get("department");
            employee.setId(id);
            employee.setName(name);
            employee.setDesignation(department);
            employeeList.add(employee);
        }

        // Return the array for the data provider
        return employeeList;
    }

}
