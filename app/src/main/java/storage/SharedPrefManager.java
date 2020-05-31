package storage;

import android.content.Context;
import android.content.SharedPreferences;

import entityBackend.User;


public class SharedPrefManager {

    private static  final String SHARED_PREF_NAME = "APP_SHARED_PREFERENCES";

    private static SharedPrefManager mInstance;
    private Context context;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public static  synchronized SharedPrefManager getmInstance(Context context){
        if (mInstance == null ){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveUser(User user){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("firebasUid",user.getFirebasUid());
        editor.putString("name",user.getName());
        editor.putString("phoneNumber",user.getPhoneNumber());
        editor.putString("langue",user.getLangue());
        editor.apply();
    }

    public void saveUserWithId(User user){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong("id",user.getId());
        editor.putString("firebasUid",user.getFirebasUid());
        editor.putString("name",user.getName());
        editor.putString("phoneNumber",user.getPhoneNumber());
        editor.putString("langue",user.getLangue());
        editor.apply();
    }


    public  boolean isLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if(sharedPreferences.getLong("id",-33L) != -1){
            return true; // Si la valeur est différente de -1, l'utilisateur est connecté
        }
        return false;
    }

    public  User getUser(){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        User user = null;
        user = new User(
                sharedPreferences.getLong("id",-1L),
                sharedPreferences.getString("firebasUid","NO_FOUND"),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("phoneNumber", null),
                sharedPreferences.getString("langue", null)
        );
        return user;
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
