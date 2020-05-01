package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import entity.Ressource;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "wapi.db";

    final String SQL_GET_ALL_RESSOURCES = " SELECT * FROM "+DatabaseContent.DatabaseEntry.TABLE_RESSOURCES_DOWNLODED+";";
    final String SQL_GET_ALL_CAROUSSEL_RESSOURCES = " SELECT * FROM "+DatabaseContent.DatabaseEntry.TABLE_RESSOURCES_DOWNLODED+" WHERE "+DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_TYPE+"="+" '"+ DatabaseContent.DatabaseEntry.RESSOURCES_TYPE_CAROUSSEL+"' "+" ;";
    final String SQL_GET_ALL_VIDEO_RESSOURCES  = " SELECT * FROM "+DatabaseContent.DatabaseEntry.TABLE_RESSOURCES_DOWNLODED+" WHERE "+DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_TYPE+"="+" '"+ DatabaseContent.DatabaseEntry.RESSOURCES_TYPE_VIDEO+"' "+" ;";



    private String DROP_TABLE_RESSOURCES_DOWNLODED = "DROP TABLE IF EXISTS " + DatabaseContent.DatabaseEntry.TABLE_RESSOURCES_DOWNLODED;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE_RESSOURCES_DOWNLODED = "CREATE TABLE " + DatabaseContent.DatabaseEntry.TABLE_RESSOURCES_DOWNLODED + " (" +
                DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_PATH + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_NAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_TYPE + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FORMATION + " TEXT NOT NULL,"+
                DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FIRST_IMAGE_PATH + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_TABLE_RESSOURCES_DOWNLODED);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_TABLE_RESSOURCES_DOWNLODED);

        // Create tables again
        onCreate(db);

    }

    private void closeDB(){
        db = this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }


    public boolean saveOneRessource(Ressource ressource){

        db = this.getWritableDatabase();

        ContentValues params = new ContentValues();
        params.put(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_PATH , ressource.getPath());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_NAME , ressource.getName());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_TYPE , ressource.getType());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FORMATION , ressource.getFormation());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FIRST_IMAGE_PATH , ressource.getFirstImagePath());

        long l = db.insert(DatabaseContent.DatabaseEntry.TABLE_RESSOURCES_DOWNLODED, null, params);

        return l != -1;
    }

    public boolean saveManyRessources(ArrayList<Ressource> ressourceArrayList){
        boolean result = true;
        for (int i=0; i<ressourceArrayList.size(); i++){
            result = saveOneRessource(ressourceArrayList.get(i)) && result;
        }
        return result;
    }

    public ArrayList<Ressource> getAllCaroussels(){

        ArrayList<Ressource> ressourceArrayList = new ArrayList<>();
        Ressource ressource;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_GET_ALL_CAROUSSEL_RESSOURCES,null);

        while(cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_ID));
            String path = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_PATH));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_NAME));
            String type = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_TYPE));
            String formation = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FORMATION));
            String firstImagePath = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FIRST_IMAGE_PATH));

            ressource = new Ressource(id,path,name,type,formation, firstImagePath);
            ressourceArrayList.add(ressource);
        }

        cursor.close();

        return  ressourceArrayList;

    }

    public ArrayList<Ressource> getAllVideos(){

        ArrayList<Ressource> ressourceArrayList = new ArrayList<>();
        Ressource ressource;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_GET_ALL_VIDEO_RESSOURCES,null);

        while(cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_ID));
            String path = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_PATH));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_NAME));
            String type = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_TYPE));
            String formation = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FORMATION));
            String firstImagePath = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FIRST_IMAGE_PATH));

            ressource = new Ressource(id,path,name,type,formation, firstImagePath);
            ressourceArrayList.add(ressource);
        }

        cursor.close();

        return  ressourceArrayList;

    }

    public ArrayList<Ressource> getAll(){
        ArrayList<Ressource> ressourceArrayList = new ArrayList<>();
        Ressource ressource;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_GET_ALL_RESSOURCES,null);

        while(cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_ID));
            String path = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_PATH));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_NAME));
            String type = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_TYPE));
            String formation = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FORMATION));
            String firstImagePath = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_RESSOURCES_FIRST_IMAGE_PATH));

            ressource = new Ressource(id,path,name,type,formation, firstImagePath);
            ressourceArrayList.add(ressource);
        }

        cursor.close();

        return  ressourceArrayList;
    }

}
