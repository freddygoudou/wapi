package api;

import java.util.ArrayList;
import java.util.List;

import entityBackend.Caroussel;
import entityBackend.Champs;
import entityBackend.SaisonCulture;
import entityBackend.User;
import entityBackend.Video;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface  Api {

    //    @FormUrlEncoded : Ne marche pas avec ResponseBody au niveau de Spring

    // *********************** Authentification *********************

    @POST("api/auth")
    Call<User> auth(
            @Body User user
    );

    @POST("api/user/create")
    Call<User> createUser(
            @Body User user
    );

    //*********************** Caroussel *********************************

    @POST("api/caroussel")
    Call<Caroussel> saveCaroussel(
            @Body Caroussel caroussel
    );

    @GET("api/caroussel")
    Call<List<Caroussel>> getAllCaroussel();

    //*********************** Videos *********************************

    @GET("api/video")
    Call<Video> saveVideo(
            @Body Video video
    );

    @GET("api/video")
    Call<List<Video>> getAllVideo();

    //*********************  Exploitation ************************************

    @POST("api/champs/create")
    Call<Champs> saveChamps(
            @Body Champs champs
    );

    @GET("api/champs")
    Call<List<Champs>> getAllChamps();

    /* *********************** Gestion des chaînes : Fragment Chaînes ********************* *//*

    @POST("api/chaine/get-all")
    Call<ArrayList<Chaine>> getAllChaines(
            @Body User user
    ); // Ordonné par les chaines suivie

    @POST("api/chaine/get-one")
    Call<ArrayList<Informations>> getOneChainesInfos(
            @Body Chaine chaine
    );




    *//* *********************** Gestion des messages : Fragment Message ********************* *//*

    @POST("api/message/get-all")
    Call<ArrayList<Message>> getAllMessageCoUser(
    ); // Ordonné par le plus récent

    @POST("api/message/get-one")
    Call<ArrayList<Message>> getOneMessage(
    );

    @POST("api/message/send-message")
    Call<Boolean> sendMessage(
            @Body Message message,
            @Body User user,
            @Body User co_user
    );

    @POST("api/message/remove-all")
    Call<Boolean> removedAllMessage(
            @Body User user,
            @Body User co_user
    );//Desactiver tous les messgages d'avec quelqu'un : Pas de suppression physique

    @POST("api/message/remove-one")
    Call<Boolean> removedMessage(
            @Body Message message, // message à supprimer
            @Body User user,
            @Body User co_user
    );// Désactiver les message avec un champs statut : Pas de suppression physique


    *//* *********************** Gestion de compte utilisateur : Fragment Compte ********************* *//*

    @POST("api/compte/update-username")
    Call<User> updateUsername(
            @Body User user
    );

    @POST("api/compte/update-email")
    Call<User> updateEmail(
            @Body User user
    );

    @POST("api/compte/update-contact")
    Call<User> updateContact(
            @Body User user
    );

    @POST("api/compte/update-password")
    Call<User> updatePassword(
            @Body User user
    );

*/
}
