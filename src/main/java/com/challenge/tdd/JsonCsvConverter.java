package com.challenge.tdd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonCsvConverter {

    public static String convert(String json) throws IllegalArgumentException{

        if(json == null || json.isEmpty()) {
            throw new IllegalArgumentException();
        }

        try {
            JSONArray jsonArray = new JSONArray(json);
            StringBuilder csv = new StringBuilder();
            int i = 0;
            while(!jsonArray.isNull(i)){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                csv.append(jsonObject.get("name")).append(",").append(jsonObject.get("age")).append("\n");
                i++;
            }
            return csv.toString();
        } catch (JSONException e) {
            System.out.println("Passed json is not an array!");
        }

        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.get("name") + "," + jsonObject.get("age");
    }

}