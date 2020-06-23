package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.Nullable;
import entity.AudioCarrousel;
import entity.CarrouselDownloded;
import entity.ImageCarrousel;
import entityBackend.Carrousel;
import entityBackend.CarrouselFormation;
import entityBackend.Video;

import static database.DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_ID;
import static database.DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_ID;
import static database.DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_ID_FK;
import static database.DatabaseContent.DatabaseEntry.TABLE_CAROUSSEL;
import static database.DatabaseContent.DatabaseEntry.TABLE_CARROUSEL_FORMATION;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "wapi.db";

    final String SQL_GET_ALL_TABLE_CAROUSSEL = " SELECT * FROM "+ TABLE_CAROUSSEL+";";
    final String SQL_GET_ALL_TABLE_CARROUSEL_FORMATION = " SELECT * FROM "+ TABLE_CARROUSEL_FORMATION+";";
    final String SQL_GET_ALL_TABLE_VIDEO = " SELECT * FROM "+DatabaseContent.DatabaseEntry.TABLE_VIDEO+" ;";
    final String SQL_GET_ALL_TABLE_CARROUSEL_DOWNLOADED = " SELECT * FROM "+DatabaseContent.DatabaseEntry.TABLE_CARROUSEL_DOWNLOADED+" ;";



    private String DROP_TABLE_CARROUSEL_FORMATION = "DROP TABLE IF EXISTS " + TABLE_CARROUSEL_FORMATION;
    private String DROP_TABLE_CAROUSSEL = "DROP TABLE IF EXISTS " + TABLE_CAROUSSEL;
    private String DROP_TABLE_VIDEO = "DROP TABLE IF EXISTS " + DatabaseContent.DatabaseEntry.TABLE_VIDEO;
    private String DROP_TABLE_CARROUSEL_DOWNLOADED = "DROP TABLE IF EXISTS " + DatabaseContent.DatabaseEntry.TABLE_CARROUSEL_DOWNLOADED;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE_CAROUSSEL = "CREATE TABLE " + TABLE_CAROUSSEL + " (" +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_NAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_SUBNAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_DESCRIPTION + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_URL + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_LANGUE + " TEXT NOT NULL);";

        final String SQL_CREATE_TABLE_CARROUSEL_FORMATION = "CREATE TABLE " + TABLE_CARROUSEL_FORMATION + " (" +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContent.DatabaseEntry.COLUMN_CCARROUSEL_FORMATION_TEXTE + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_IMAGES + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_AUDIOS + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_ID_FK + " TEXT NOT NULL , FOREIGN KEY ("+COLUMN_CARROUSEL_ID_FK+") REFERENCES "+TABLE_CAROUSSEL +"("+COLUMN_CAROUSSEL_ID+"));";

        final String SQL_CREATE_TABLE_VIDEO = "CREATE TABLE " + DatabaseContent.DatabaseEntry.TABLE_VIDEO + " (" +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_PATH + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_FORMATION_NAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_DESCRIPTION + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_VIDEO_CAPTION_PATH + " TEXT NOT NULL);";

        final String SQL_CREATE_TABLE_CARROUSEL_DOWNLOADED = "CREATE TABLE " + DatabaseContent.DatabaseEntry.TABLE_CARROUSEL_DOWNLOADED + " (" +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_NAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_SUBNAME + " TEXT NOT NULL," +
                DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_LANGUE + " TEXT NOT NULL);";




        db.execSQL(SQL_CREATE_TABLE_CARROUSEL_FORMATION);
        db.execSQL(SQL_CREATE_TABLE_CAROUSSEL);
        db.execSQL(SQL_CREATE_TABLE_VIDEO);
        db.execSQL(SQL_CREATE_TABLE_CARROUSEL_DOWNLOADED);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_TABLE_CAROUSSEL);
        db.execSQL(DROP_TABLE_CARROUSEL_FORMATION);
        db.execSQL(DROP_TABLE_VIDEO);
        db.execSQL(DROP_TABLE_CARROUSEL_DOWNLOADED);

        // Create tables again
        onCreate(db);

    }

    private void closeDB(){
        db = this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }

    public ArrayList<Carrousel> getAllCaroussels(){

        ArrayList<Carrousel> carrousels = new ArrayList<>();
        Carrousel carrousel;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_GET_ALL_TABLE_CAROUSSEL,null);

        while(cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndex(COLUMN_CAROUSSEL_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_NAME));
            String subname = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_SUBNAME));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_DESCRIPTION));
            String url = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_URL));
            String langue = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_LANGUE));
            carrousel = new Carrousel(String.valueOf(id), name, subname, url, description, langue, getAllCarousselFormationsById(id));
            carrousels.add(carrousel);
        }
        cursor.close();
        return  carrousels;
    }

    public int getAllCarousselsRowsCount(){
        db = this.getReadableDatabase();
        int count;
        Cursor cursor = db.rawQuery(SQL_GET_ALL_TABLE_CAROUSSEL,null); //SQL_GET_ALL_TABLE_CAROUSSEL
        count = cursor.getCount();
        cursor.close();
        return  count;
    }

    public boolean saveCaroussel(Carrousel carrousel){

        System.out.println("Insertion of : "+carrousel.toString());
        boolean result = true;
        db = this.getWritableDatabase();

        ContentValues params = new ContentValues();
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_NAME, carrousel.getName());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_SUBNAME, carrousel.getSubname());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_DESCRIPTION , carrousel.getDescription());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_URL , carrousel.getUrl());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CAROUSSEL_LANGUE , carrousel.getLangue());

        long l = db.insert(TABLE_CAROUSSEL, null, params);

        if (l != -1){
            int id_carrousel = getAllCarousselsRowsCount();
            result = saveManyCarousselFormation(carrousel.getCarrouselFormations(), id_carrousel);
        }
        return result;
    }

    public ArrayList<CarrouselFormation> getAllCarousselFormationsById(Long id){
        ArrayList<String> resources = new ArrayList<>();
        ArrayList<ImageCarrousel> images = new ArrayList<>();
        ArrayList<AudioCarrousel> audios = new ArrayList<>();
        ArrayList<CarrouselFormation> carrouselFormations = new ArrayList<>();
        CarrouselFormation carrouselFormation;

        db = this.getReadableDatabase();

        String SQL_GET_ALL_TABLE_CARROUSEL_FORMATION_BY_ID = " SELECT * FROM "+ TABLE_CARROUSEL_FORMATION+" WHERE "+COLUMN_CARROUSEL_ID_FK +"="+id+";";

        Cursor cursor = db.rawQuery(SQL_GET_ALL_TABLE_CARROUSEL_FORMATION_BY_ID,null);

        while(cursor.moveToNext()){
            String audio = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_AUDIOS));
            String image = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_IMAGES));
            String texte = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CCARROUSEL_FORMATION_TEXTE));

            System.out.println("Images en db :"+image);
            System.out.println("Audio en db :"+audio);

            //loading audios
            resources = exportLocalsResources(audio);
            for (int i=0;i<resources.size();i++){
                audios.add(new AudioCarrousel(resources.get(i),resources.get(i),i+1,1));
            }

            System.out.println(" ressources image en db :"+resources);


            //loading image
            resources.clear();
            resources = exportLocalsResources(image);
            for (int i=0;i<resources.size();i++){
                images.add(new ImageCarrousel(resources.get(i),resources.get(i),1));
            }

            System.out.println(" ressources Audio en db :"+resources);

            carrouselFormation = new CarrouselFormation(texte,String.valueOf(cursor.getCount()),images, audios);

            System.out.println(" ressources carrouselFormation en db :"+carrouselFormation.toString());

            carrouselFormations.add(carrouselFormation);
        }

        cursor.close();

        return  carrouselFormations;

    }

    public boolean saveCarousselFormation(CarrouselFormation carrouselFormation, int id_carrousel){

        db = this.getWritableDatabase();


        ContentValues params = new ContentValues();
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CCARROUSEL_FORMATION_TEXTE, carrouselFormation.getTexte());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_IMAGES , importLocalsResources(getBaseUrlListFromImages(carrouselFormation.getImages())));
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_FORMATION_AUDIOS , importLocalsResources(getBaseUrlListFromAudios(carrouselFormation.getAudios())));
        params.put(COLUMN_CARROUSEL_ID_FK , id_carrousel);

        long l = db.insert(TABLE_CARROUSEL_FORMATION, null, params);
        return l != -1;
    }

    public boolean saveManyCarousselFormation(ArrayList<CarrouselFormation> carrouselFormations, int id){
        boolean result = true;
        for (int i = 0; i< carrouselFormations.size(); i++){
            result = saveCarousselFormation(carrouselFormations.get(i), id) && result;
        }
        return result;
    }

    public boolean saveModuleFormation(){
        //Save carrousel
        //Recuperer son id
        //Save la liste des carrousel formation avec cet id
        return true;
    }



    private ArrayList<String> exportLocalsResources(String resources){
        ArrayList<String> localResources = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(resources, ";");
        while (stringTokenizer.hasMoreTokens()){
            localResources.add(stringTokenizer.nextToken());
        }
        return localResources;
    }

    private String importLocalsResources(ArrayList<String> list){
        String resources="";
        for (int i=0; i<list.size(); i++){
            if (i!=list.size()-1)
                resources = resources.concat(list.get(i)+";");
            else
                resources = resources.concat(list.get(i));
        }

        System.out.println("====================================================================== RESSUCE IPORT IS : "+resources);
        return resources;
    }

    private ArrayList<String> getBaseUrlListFromAudios(ArrayList<AudioCarrousel> list){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            stringArrayList.add(list.get(i).getBaseUri());
        }
        return stringArrayList;
    }

    private ArrayList<String> getBaseUrlListFromImages(ArrayList<ImageCarrousel> list){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            stringArrayList.add(list.get(i).getBaseUri());
        }
        return stringArrayList;
    }








    public ArrayList<CarrouselDownloded> getAllCarousselDownloaded(){

        ArrayList<CarrouselDownloded> carrouselDownlodeds = new ArrayList<>();
        CarrouselDownloded carrouselDownloded;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_GET_ALL_TABLE_CARROUSEL_DOWNLOADED,null);

        while(cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_NAME));
            String subname = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_SUBNAME));
            String langue = cursor.getString(cursor.getColumnIndex(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_LANGUE));

            carrouselDownloded = new CarrouselDownloded(id, name, subname, langue);
            carrouselDownlodeds.add(carrouselDownloded);
        }

        cursor.close();

        return  carrouselDownlodeds;

    }

    public boolean saveCarousselDownloaded(CarrouselDownloded carrouselDownloded){

        db = this.getWritableDatabase();

        ContentValues params = new ContentValues();
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_NAME , carrouselDownloded.getName());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_SUBNAME , carrouselDownloded.getSubname());
        params.put(DatabaseContent.DatabaseEntry.COLUMN_CARROUSEL_DOWNLOADED_LANGUE , carrouselDownloded.getLangue());

        long l = db.insert(DatabaseContent.DatabaseEntry.TABLE_CARROUSEL_DOWNLOADED, null, params);
        return l != -1;
    }


    public boolean saveManyRessources(ArrayList<Carrousel> carrouselArrayList){
        boolean result = true;
        for (int i = 0; i< carrouselArrayList.size(); i++){
            result = saveCaroussel(carrouselArrayList.get(i)) && result;
        }
        return result;
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
