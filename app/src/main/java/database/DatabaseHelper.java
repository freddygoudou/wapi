package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import entityBackend.Carrousel;
import entityBackend.Video;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "wapi.db";

    final String SQL_GET_ALL_TABLE_CAROUSSEL = " SELECT * FROM "+DatabaseContent.DatabaseEntry.TABLE_CAROUSSEL+";";
    final String SQL_GET_ALL_TABLE_VIDEO = " SELECT * FROM "+DatabaseContent.DatabaseEntry.TABLE_VIDEO+" ;";



    private String DROP_TABLE_CAROUSSEL = "DROP TABLE IF EXISTS " + DatabaseContent.DatabaseEntry.TABLE_CAROUSSEL;
    private String DROP_TABLE_VIDEO = "DROP TABLE IF EXISTS " + DatabaseContent.DatabaseEntry.TABLE_VIDEO;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE_CAROUSSEL = "CREATE TABLE " + DatabaseContent.DatabaseEntry.TABLE_CAROUSSEL + " (" +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_FORMATION_NAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_AUDIOS_PATHS + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_DESCRIPTION + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_IMAGES_PATHS + " TEXT NOT NULL);";

        final String SQL_CREATE_TABLE_VIDEO = "CREATE TABLE " + DatabaseContent.DatabaseEntry.TABLE_VIDEO + " (" +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_PATH + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_FORMATION_NAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_DESCRIPTION + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_CAPTION_PATH + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_TABLE_CAROUSSEL);
        db.execSQL(SQL_CREATE_TABLE_VIDEO);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_TABLE_CAROUSSEL);
        db.execSQL(DROP_TABLE_VIDEO);

        // Create tables again
        onCreate(db);

    }

    private void closeDB(){
        db = this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }


    public boolean saveOneCaroussel(Carrousel carrousel){

        db = this.getWritableDatabase();

        ContentValues params = new ContentValues();
        //params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_FORMATION_NAME , carrousel.getName());
        //params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_AUDIOS_PATHS , carrousel.getAudiosPaths());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_DESCRIPTION , carrousel.getDescription());
        //params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_IMAGES_PATHS , carrousel.getImagesPaths());

        long l = db.insert(DatabaseContent.DatabaseEntry.TABLE_CAROUSSEL, null, params);
        return l != -1;
    }

    public boolean saveManyRessources(ArrayList<Carrousel> carrouselArrayList){
        boolean result = true;
        for (int i = 0; i< carrouselArrayList.size(); i++){
            result = saveOneCaroussel(carrouselArrayList.get(i)) && result;
        }
        return result;
    }

    public ArrayList<Carrousel> getAllCaroussels(){

        ArrayList<Carrousel> ressourceArrayList = new ArrayList<>();
        Carrousel carrousel;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_GET_ALL_TABLE_CAROUSSEL,null);

        while(cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_FORMATION_NAME));
            String audiosPaths = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_AUDIOS_PATHS));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_DESCRIPTION));
            String imagesPaths = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_IMAGES_PATHS));

            //carrousel = new Carrousel(name, description, audiosPaths, imagesPaths);
            // ok ressourceArrayList.add(carrousel);
        }

        cursor.close();

        return  ressourceArrayList;

    }

    public ArrayList<Video> getAllVideos(){

        ArrayList<Video> videoArrayList = new ArrayList<>();
        Video video;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_GET_ALL_TABLE_VIDEO,null);

        while(cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_ID));
            String path = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_PATH));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_FORMATION_NAME));
            String caption = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_CAPTION_PATH));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_DESCRIPTION));

            video = new Video(name,path,description,caption);
            videoArrayList.add(video);
        }

        cursor.close();

        return  videoArrayList;

    }

    public boolean saveOneVideo(Video video){

        db = this.getWritableDatabase();

        ContentValues params = new ContentValues();
        params.put(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_PATH , video.getVideosPaths());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_FORMATION_NAME , video.getName());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_DESCRIPTION, video.getDescription());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_VIDEO_CAPTION_PATH , video.getCaptionPath());

        long l = db.insert(DatabaseContent.DatabaseEntry.TABLE_VIDEO, null, params);
        return l != -1;
    }

    public boolean saveManyVideo(ArrayList<Video> videoArrayList){
        boolean result = true;
        for (int i=0; i<videoArrayList.size(); i++){
            result = saveOneVideo(videoArrayList.get(i)) && result;
        }
        return result;
    }



}
