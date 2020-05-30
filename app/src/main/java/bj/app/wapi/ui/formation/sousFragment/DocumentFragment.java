package bj.app.wapi.ui.formation.sousFragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import adapter.DocumentAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import database.DatabaseHelper;
import entityBackend.Caroussel;
import entity.Document;
import entity.SlideItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentFragment extends Fragment {

    //Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Caroussel/BAOBAB/ et ses fichiers
    //Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Video/BAOBAB/ et ses fichiers

    RecyclerView recyclerView;
    DocumentAdapter adapter;
    ArrayList<Document> mData = new ArrayList<>();
    CarouselView carouselView;
    ArrayList<SlideItem> slideItemList;
    DatabaseHelper databaseHelper;
    ArrayList<Caroussel> ressourceArrayList = new ArrayList<>();

    //https://upload.wikimedia.org/wikipedia/commons/2/2d/Snake_River_%285mb%29.jpg
    //String test1="https://firebasestorage.googleapis.com/v0/b/wegoofirebase.appspot.com/o/images%2Fuser_profile%2FG8OOdW2PsffLjNuJ0iXGnywr2v43.jpg?alt=media&token=00339afd-4b57-4a5e-8e7c-5562b4ee68dd";
    //String text2="https://firebasestorage.googleapis.com/v0/b/wegoofirebase.appspot.com/o/images%2Fuser_profile%2F1obC08IjG7dj9SqPa8QNYBvGRek2.jpg?alt=media&token=5de96950-83a3-4c71-821f-6110f58a3875";

    String test1="https://i.postimg.cc/qMwgc348/Logo.png";
    String text2="https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg";


    public DocumentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_document, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDocuments();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadDocuments();

        recyclerView = view.findViewById(R.id.rv_document);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(DocumentFragment.this.getContext()));

    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void loadDocuments(){

        ressourceArrayList = new ArrayList<>();

        if (isNetworkConnected()){
            Toast.makeText(getActivity(),"CONNECTED ..... ", Toast.LENGTH_LONG).show();
            //GET RESSOURCES FROM API
            ressourceArrayList.add(new Caroussel("CAJOUX","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",text2+";"+text2));
            ressourceArrayList.add(new Caroussel("RIZ","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",text2+";"+text2));
            ressourceArrayList.add(new Caroussel("TOMATE","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",text2+";"+text2));
            ressourceArrayList.add(new Caroussel("PIMENT","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",text2+";"+text2));
            ressourceArrayList.add(new Caroussel("CAROTTE","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",text2+";"+text2));
            ressourceArrayList.add(new Caroussel("SOJA","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",text2+";"+text2));

            /*mData.add(new Document("CAJOUX", "Le meilleur d Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("RIZ", "Le meilleur d Afrique", R.drawable.wapibaobabpoudre));
            mData.add(new Document("TOMATE", "Le meilleur d Afrique", R.drawable.wapitransdetarium));
            mData.add(new Document("PIMENT", "Le meilleur d Afrique", R.drawable.wapihuilebaobab));
            mData.add(new Document("CAROTTE", "Le meilleur d Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("SOJA", "Le meilleur d Afrique", R.drawable.wapihuilebaobab));*/

            adapter = new DocumentAdapter(DocumentFragment.this.getContext(), ressourceArrayList, true);
            adapter.notifyDataSetChanged();

        }else {
            Toast.makeText(getActivity(),"NOT CONNECTED ..... ", Toast.LENGTH_LONG).show();

            databaseHelper = new DatabaseHelper(getActivity());

            ressourceArrayList = databaseHelper.getAllCaroussels();

            /*ressourceArrayList.add(new Caroussel("CAJOUX","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",test1+";"+text2));
            ressourceArrayList.add(new Caroussel("RIZ","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",test1+";"+text2));
            ressourceArrayList.add(new Caroussel("TOMATE","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",test1+";"+text2));
            ressourceArrayList.add(new Caroussel("PIMENT","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",test1+";"+text2));
            ressourceArrayList.add(new Caroussel("CAROTTE","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",test1+";"+text2));
            ressourceArrayList.add(new Caroussel("SOJA","Le meilleur d'Afrique","https://www.radiantmediaplayer.com/media/bbb-360p.mp4",test1+";"+text2));
*/

            /*mData.add(new Document("CAJOUX", "Le meilleur d Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("RIZ", "Le meilleur d Afrique", R.drawable.wapibaobabpoudre));
            mData.add(new Document("TOMATE", "Le meilleur d Afrique", R.drawable.wapitransdetarium));
            mData.add(new Document("PIMENT", "Le meilleur d Afrique", R.drawable.wapihuilebaobab));
            mData.add(new Document("CAROTTE", "Le meilleur d Afrique", R.drawable.wapipoudrefeuillebaobnab));
            mData.add(new Document("SOJA", "Le meilleur d Afrique", R.drawable.wapihuilebaobab));*/

            adapter = new DocumentAdapter(DocumentFragment.this.getContext(), ressourceArrayList, false);
            adapter.notifyDataSetChanged();
        }
    }
}
