package com.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;

public class JsonProperty {

    @DataProvider(name = "Json_Parsing")
    public Object[] jsonReader() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        // Use the correct file path to your JSON file
        FileReader fileReader = new FileReader("src/test/resources/base/TestJson.json");

        // Parse the JSON file
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        // Retrieve the "userlogins" array from the JSON object
        JSONArray array = (JSONArray) jsonObject.get("userlogins");

        // Create an array to store the username and password pairs
        String[] arr = new String[array.size()];

        for (int i = 0; i < array.size(); i++) {
            JSONObject user = (JSONObject) array.get(i);
            String username = (String) user.get("username");
            String password = (String) user.get("password");
            arr[i] = username + "," + password;
        }

        // Return the array for the DataProvider
        return arr;
    }
}
