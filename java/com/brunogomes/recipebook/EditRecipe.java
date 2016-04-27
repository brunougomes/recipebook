package com.brunogomes.recipebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class EditRecipe extends AppCompatActivity {

    private TextView recipeId;
    private TextView recipeTitle;
    private TextView recipeDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        Bundle extras = getIntent().getExtras();

        //recipeId = (TextView) findViewById(R.id.recipe_id);
        //recipeId.setText(extras.getString("id"));

        recipeTitle = (TextView) findViewById(R.id.recipe_title);
        recipeTitle.setText(extras.getString("title"));

        recipeDescription = (TextView) findViewById(R.id.recipe_description);
        recipeDescription.setText(extras.getString("description"));

    }

}
