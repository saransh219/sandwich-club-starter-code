package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        Log.e("TAG",json);
        Log.e("TAG","inside");
        JSONObject jsonObject = new JSONObject(json);


        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> alsoKnownAs = new ArrayList<>();

        JSONObject name = jsonObject.getJSONObject("name");
        String mainName = name.getString("mainName");
        Log.e("TAG",mainName);

        String image = jsonObject.getString("image");
        String description = jsonObject.getString("description");
        String placeOfOrigin = jsonObject.getString("placeOfOrigin");
        Log.e("TAG",description + placeOfOrigin + image);

        JSONArray alsoKnownAsJSON = name.getJSONArray("alsoKnownAs");
        for (int a=0; a < alsoKnownAsJSON.length();a++)
        {

            Log.e("TAG","clicked json utils");
            alsoKnownAs.add(alsoKnownAsJSON.getString(a));
        }

        JSONArray ingredientsJSON = jsonObject.getJSONArray("ingredients");
        for (int b=0 ; b< ingredientsJSON.length();b++)
        {
            ingredients.add(ingredientsJSON.getString(b));
        }

        Sandwich sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
        Log.e("TAG","Sandwich ");

        return sandwich;
    }
}
