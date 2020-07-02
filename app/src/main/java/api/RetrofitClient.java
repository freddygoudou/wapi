package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    /*
        ERROR : CLEARTEXT communication to host not permitted by network security policy
        SOLUTION : https://github.com/shadowsocks/shadowsocks-android/issues/1765
     */

    //192.168.43.5 ; 217.160.241.185 ; 63.250.35.104
    private static final String BASE_URL = "http://217.160.241.185:8080/wapibackend/"; // BASE URL DE LA OU EST LE BACKEND //http://217.160.241.185:8181/
    private static  RetrofitClient mInstance;
    private Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public RetrofitClient() {
        this.retrofit =  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClient getmInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
