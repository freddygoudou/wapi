package database;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import entityBackend.AllCarrousels;
import entityBackend.Carrousel;

//https://www.youtube.com/watch?v=eYQSkadfa4k
public class IOHelper {

    public static String stringFromStream(InputStream is){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null)
                stringBuilder.append(line).append("\n");
            reader.close();
            return  stringBuilder.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String stringFromFile(File file)throws IOException{
        FileInputStream fileInputStream = new FileInputStream(file);
        String str = stringFromStream(fileInputStream);
        fileInputStream.close();
        return str;
    }

    public static void writeToFile(File file, String str) throws  IOException{
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(str.getBytes());
    }

   /* public static void writeToFileAdditional(Context context, File file, Carrousel carrousel) throws  IOException{
        ArrayList<Carrousel> carrousels = readCarrouselJson(context);
        carrousels.add(carrousel);
        String jsonString = new Gson().toJson(carrousels);

        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("carrousels.json");

        inputStream.close();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(jsonString.getBytes());
    }
*/

    public static void writeToFile(Context context, String fileName, String str) throws  IOException{
        try{
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(str.getBytes(),0, str.length());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String stringFromAsset(Context context, String assetFileName){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(assetFileName);
            String result = IOHelper.stringFromStream(inputStream);
            inputStream.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String stringFromLocalFile(String fileUri){
        try {
            InputStream inputStream = new FileInputStream(fileUri);
            String result = IOHelper.stringFromStream(inputStream);
            inputStream.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Carrousel> readCarrouselJson(Context context){
        ArrayList<Carrousel> carrousels = new ArrayList<>();
        //Carrousel carrousels;
        String jsonString = IOHelper.stringFromAsset(context, "carrousels.json");
        System.out.println("CARROUSSEL JSON IS :"+jsonString);
        try{
            JSONArray carrouselsJson = new JSONArray(jsonString);
            //System.out.println("CARROUSSEL JSON length:"+carrouselsJson.length());
            for (int i=0; i<carrouselsJson.length();i++){
                JSONObject carrouselJsonO = carrouselsJson.getJSONObject(i);

                Carrousel carrousel = new Gson().fromJson(String.valueOf(carrouselJsonO), (Type) Carrousel.class);
                //System.out.println("CARROUSSEL JSON OBJECT:"+carrousel.toString());
                //System.out.println("CARROUSSEL FORMATION JSON SIZE:"+carrousel.getCarrouselFormations().size());
                carrousels.add(carrousel);
            }
            return  carrousels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Carrousel readCarrouselJsonFromLocal(String fileUri){
        ArrayList<Carrousel> carrousels = new ArrayList<>();
        String jsonString = IOHelper.stringFromLocalFile(fileUri);
        System.out.println("CARROUSSEL JSON STRING IS :"+jsonString);
        try{

            Carrousel carrousel = new Gson().fromJson(String.valueOf(jsonString), Carrousel.class);

            //JSONObject carrouselsJson =  new JSONObject(jsonString);

            //System.out.println("CARROUSSEL JSON ARRAY LENGHT IS :"+carrouselsJson);
            /*for (int i=0; i<carrouselsJson.length();i++){
                JSONObject carrouselJsonO = carrouselsJson.getJSONObject(i);

                //Carrousel carrousel = new Gson().fromJson(String.valueOf(carrouselJsonO), (Type) Carrousel.class);
                //System.out.println("CARROUSSEL JSON OBJECT:"+carrousel.toString());
                //System.out.println("CARROUSSEL FORMATION JSON SIZE:"+carrousel.getCarrouselFormations().size());
                //carrousels.add(carrousel);

                System.out.println("CARROUSSEL JSON IS :"+carrouselsJson);
                System.out.println("CARROUSSEL JSON ARRAY LENGHT IS :"+carrouselsJson.length());

                //Carrousel carrousel = new Gson().fromJson(String.valueOf(carrouselsJson.getJSONObject(0)), Carrousel.class);
                //System.out.println("CARROUSSEL OBJECT ATTR COUNT IS :"+carrousel.getCarrouselFormations().size());

            }*/
            return carrousels.get(0);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("GSON FOR CARROUSELS CONVERTION MESSAGE ERROR :"+ e.getMessage());
        }
        return null;
    }

    /*public static ArrayList<Carrousel> readCarrouselJson(Context context){
        ArrayList<Carrousel> carrousels = new ArrayList<>();
        //Carrousel carrousels;
        String jsonString = IOHelper.stringFromAsset(context, "carrousels.json");
        System.out.println("CARROUSSEL JSON IS :"+jsonString);
        try{
            JSONArray carrouselsJson = new JSONArray(jsonString);
            //System.out.println("CARROUSSEL JSON length:"+carrouselsJson.length());
            for (int i=0; i<carrouselsJson.getJSONObject(i).length();i++){
                JSONObject carrouselJsonO = carrouselsJson.getJSONObject(i);

                AllCarrousels carrousel = new Gson().fromJson(jsonString, (Type) AllCarrousels.class);
                System.out.println("CARROUSSEL JSON OBJECT:"+carrousel.toString() + "AND SIZE IS : "+carrousel.getCarrousels().size());
                //carrousels.add(carrousel);
            }
            return  carrousels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/

    public static void writeJson(Carrousel carrousel) throws IOException {
        IOHelper.writeToFile(new File("file:///android_asset/carrousels.json"), new Gson().toJson(carrousel));
    }



}
