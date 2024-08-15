package com.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadJsonUsingDataProvier {
    @DataProvider(name = "Json_Parsing")
    public Object[] jsonReader() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        // Use the correct file path for your JSON file
        FileReader fileReader = new FileReader("src/test/resources/base/TestJson.json");

        // Parse the JSON file
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        // Retrieve the "userlogins" array from the JSON object
        JSONArray array = (JSONArray) jsonObject.get("Employees");

        // Create an array to store usernames and passwords
        String[] arr = new String[array.size()];

        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String id = (String) users.get("id");
            String username = (String) users.get("name");
            String department=(String) users.get("department");
            arr[i] = id + "," + username+","+department;
        }

        // Return the array for the data provider
        return arr;
    }

}
