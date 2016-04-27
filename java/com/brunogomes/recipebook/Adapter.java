package com.brunogomes.recipebook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Bru on 09/04/2016.
 */
public class Adapter extends ArrayAdapter<Recipe > {
    private Activity activity;
    int id;
    ArrayList<Recipe> recipes;
    public Adapter(Activity context, int resource, ArrayList<Recipe> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.id = resource;
        this.recipes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(id,null);
        }

        Recipe recipe = recipes.get(position);
        TextView recipe_id = (TextView) convertView.findViewById(R.id.recipe_id);
        TextView recipe_title = (TextView) convertView.findViewById(R.id.recipe_title);
        TextView recipe_description = (TextView) convertView.findViewById(R.id.recipe_description);

        recipe_id.setText(String.valueOf(recipe.getRecipeId()));
        recipe_title.setText(String.valueOf(recipe.getTitle()));
        recipe_description.setText(String.valueOf(recipe.getDescription()));

        return convertView;
    }
}
