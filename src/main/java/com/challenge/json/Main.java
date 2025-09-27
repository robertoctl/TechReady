package com.challenge.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static JSONObject jsonObject;
    private static JSONArray jsonArray;
    private static JSONArray otherJsonArray;

    public static void main(String[] args) {

        // Creating JSON Directly From JSONObject
        jsonObject = new JSONObject();
        jsonObject.put("name", "jon doe");
        jsonObject.put("age", "22");
        jsonObject.put("city", "chicago");
        System.out.println(jsonObject);

        // Creating JSON From Map
        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");
        jsonObject = new JSONObject(map);
        System.out.println(jsonObject);

        // Creating JSONObject From JSON String
        jsonObject = new JSONObject("{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}");
        System.out.println(jsonObject.get("city"));

        // JSONArray
        jsonArray = new JSONArray();
        jsonArray.put(Boolean.TRUE);
        jsonArray.put("lorem ipsum");

        otherJsonArray = new JSONArray();
        otherJsonArray.put(Boolean.FALSE);

        jsonObject = new JSONObject();
        jsonObject.put("name", "jon doe");
        jsonObject.put("age", "22");
        jsonObject.put("otherArray", otherJsonArray);

        jsonArray.put(jsonObject);
        System.out.println(jsonArray);

    }

}
