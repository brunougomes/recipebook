package com.brunogomes.recipebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.io.Serializable;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private DBAdapter dbHelper;
    Adapter adapter;
    ArrayList<Recipe> arrayList = new ArrayList<Recipe>();
    Recipe item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        for(int i = 1; i <= 100; i++){
            Recipe recipeN = new Recipe();
            recipeN.setRecipeId(i);
            recipeN.setTitle("Titulo " + i);
            recipeN.setDescription("Descrição " + i);
            arrayList.add(recipeN);
        }

        //dbHelper = new DBAdapter(this);
        //try{
        //    dbHelper.checkAndCopyDatabase();
        //    dbHelper.openDatabase();
        //} catch (SQLException e){
        //
        //}
        //try{
        //    Cursor cursor = dbHelper.QueryData("select * from recipe");
        //    if(cursor != null){
        //        if(cursor.moveToFirst()){
        //            do{
        //                Recipe recipe = new Recipe();
        //                recipe.setRecipeId(cursor.getInt(0));
        //                recipe.setTitle(cursor.getString(1));
        //                recipe.setDescription(cursor.getString(2));
        //                recipe.setPreparation(cursor.getString(3));
        //                recipe.setPreparationTime(cursor.getDouble(4));
        //                recipe.setServing(cursor.getInt(5));
        //                arrayList.add(recipe);
        //            } while (cursor.moveToNext());
        //        }
        //    }
        //} catch (SQLException e) {
        //
        //}

        adapter = new Adapter(this, R.layout.custom_list, arrayList);
        listView = (ListView) findViewById(R.id.listRecipe);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditRecipe.class);
                //intent.putExtra("id", arrayList.get(position).getRecipeId());
                intent.putExtra("title", arrayList.get(position).getTitle());
                intent.putExtra("description", arrayList.get(position).getDescription());
                startActivity(intent);
            }
        });

        adapter.notifyDataSetChanged();
    }

}
