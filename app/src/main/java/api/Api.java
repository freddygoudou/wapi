package api;

import java.util.ArrayList;
import java.util.List;

import entityBackend.CarrouselFormation;
import entityBackend.Carrousel;
import entityBackend.Champs;
import entityBackend.Farmer;
import entityBackend.User;
import entityBackend.Video;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface  Api {

    //    @FormUrlEncoded : Ne marche pas avec ResponseBody au niveau de Spring

    // *********************** Authentification *********************

    @POST("api/auth")
    Call<User> auth(
            @Body User user
    );

    @POST("api/farmers/create")
    Call<Farmer> createUser(
            @Body Farmer farmer
    );

    //*********************** Caroussel *********************************

    @POST("api/caroussel")
    Call<Carrousel> saveCaroussel(
            @Body Carrousel carrousel
    );

    @GET("api/carrousels/read_all")
    Call<List<Carrousel>> getAllCaroussels();

    @GET("api/carrousels/read_all/{langue}")
    Call<List<Carrousel>> getAllCarousselsByLangue(
            @Path("langue") String langue
    );

    @GET("api/carrousels_formation/read_all")
    Call<ArrayList<CarrouselFormation>> getAllCarousselsFormation();

    //*********************** Videos *********************************

    @GET("api/video")
    Call<Video> saveVideo(
            @Body Video video
    );

    @GET("api/video")
    Call<List<Video>> getAllVideo();

    //*********************  Exploitation ************************************

    @POST("api/farmers/{firebaseId}/create-champs")
    Call<Farmer> saveChamps(
            @Path("firebaseId") String firebaseId,
            @Body Champs champs
    );

    @POST("api/farmers/{firebaseId}/update-champs")
    Call<Farmer> updateChamps(
            @Path("firebaseId") String firebaseId,
            @Body Champs champs
    );

    @GET("api/farmers/read_one/{firebaseId}")
    Call<Farmer> getAllChamps(@Path("firebaseId") String firebaseId);

    @GET("api/farmers/read_one/{firebaseId}")
    Call<Farmer> readOneFarmer(@Path("firebaseId") String firebaseId);

    /* *********************** Gestion des formations Audio et Images ********************* */

    @GET("api/carrousels_formation/read_all")
    Call<ArrayList<CarrouselFormation>> getAllImagesAudiosFormations();


    /* *********************** Gestion des messages : Fragment Message ********************* *//*

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
