package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView also_known_tv,origin_tv,ingredients_tv,description_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        also_known_tv = findViewById(R.id.also_known_tv);
        origin_tv = findViewById(R.id.origin_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        description_tv = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            Log.e("TAG","intent");
            closeOnError();
        }

        assert intent != null;
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            Log.e("TAG","position ");
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (sandwich == null) {
            // Sandwich data unavailable
            Log.e("TAG","sandwich null");
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich)
    {
        description_tv.setText(sandwich.getDescription());
        origin_tv.setText(sandwich.getPlaceOfOrigin());
        List<String> alternativeName = sandwich.getAlsoKnownAs();
        for (String name : alternativeName)
        {
            also_known_tv.append(name);
        }

        List<String> iName = sandwich.getIngredients();
        for (String name: iName)
        {
            ingredients_tv.append(name+"\n");
        }


    }
}
