package api;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    //    @FormUrlEncoded : Ne marche pas avec ResponseBody au niveau de Spring

    /* *********************** Authentification ********************* */

    /*// Register User
    @POST("api/auth/sign-up")
    Call<User> register(
            @Body User user
    );

    //Login User
    @POST("api/auth/sign-in")
    Call<User> login(
            @Body User user
    );



    *//* *********************** Fin Authentification ********************* *//*


    *//* *********************** Affichage des news santé : Fragment Home ********************* *//*

    @GET("api/news/get-all")
    Call<ArrayList<HomeNews>> getHealthNews(
    );

    @GET("api/news/get-one")
    Call<HomeNews> getOneHealthNews(
    );




    *//* *********************** Gestion des chaînes : Fragment Chaînes ********************* *//*

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













    @FormUrlEncoded
    @POST("savecommandeonline")
    Call<ResponseBody> saveCommandeOnLine(

            @Field("id_produit") int id_produit,
            @Field("id_client") int id_client,
            @Field("qte_commande ") int qte_commande

    );*/

    /*@POST("allwaitingCommandesByClient/{id_client}")
    Call<WcommandesResponse> getAllWaitingCommandesByClient(

            @Path("id_client") int this_id_client
    );

    @GET("allmathsbooks")
    Call<ProduitResponse> getAllMathsProduit();

    @GET("allphysiquesbooks")
    Call<ProduitResponse> getAllPhysiquesBooks();

    @GET("allspiritbooks")
    Call<ProduitResponse> getAllSpiritualiteBooks();

    @GET("allinformatiquebooks")
    Call<ProduitResponse> getAllInformatiqueBooks();

    @GET("allromansbooks")
    Call<ProduitResponse> getAllRomanBooks();

    @GET("allcuisinesbooks")
    Call<ProduitResponse> getAllCuisineBooks();

    @GET("alldroitbooks")
    Call<ProduitResponse> getAllDroitBooks();

    @GET("alleconomiebooks")
    Call<ProduitResponse> getAllEconomieBooks();*/

        /*@GET("/group/{id}/users")
    Call<ResponseBody> groupList(@Path("id") int groupId);*/
}
