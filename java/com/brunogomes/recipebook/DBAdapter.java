package com.brunogomes.recipebook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLDataException;

/**
 * Created by Bru on 09/04/2016.
 */

public class DBAdapter extends SQLiteOpenHelper{

    private static String DB_Path="/data/data/com.brunogomes.recipebook/";
    private static String DB_Name="android_db.sqlite";
    private final Context myContext;
    private SQLiteDatabase myDatabase;

    public DBAdapter(Context context) {
        super(context, DB_Name, null, 1);
        this.myContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_Path+DB_Name;
            checkDB=SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e){

        }
        if(checkDB != null) checkDB.close();
        return  checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_Name);
        String outFileName = DB_Path+DB_Name;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer,0,length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLDataException{
        String myPath = DB_Path+DB_Name;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void ExeSQLData(String aq1) throws SQLDataException{
        myDatabase.execSQL(aq1);
    }

    public Cursor QueryData(String query) throws SQLDataException{
        return myDatabase.rawQuery(query,null);
    }

    @Override
    public synchronized void close(){
        if(myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }

    public void checkAndCopyDatabase(){
        boolean dbExist = checkDatabase();
        if(dbExist){
            Log.d("TAG","Database already exist");
        } else {
            this.getReadableDatabase();
            try{
                copyDataBase();
            } catch (IOException e){
                Log.d("TAG", "Error copying data");
            }
        }
    }
}
