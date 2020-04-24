package bj.app.wapi.ui.formation.sousFragment;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import adapter.AnnonceAdapter;
import adapter.DocumentAdapter;
import adapter.SliderAdapter;
import adapter.VideoAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import bj.app.wapi.R;
import bj.app.wapi.ui.annonce.sousFragment.AchatFragment;
import entity.Article;
import entity.Document;
import entity.SlideItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentFragment extends Fragment {


    RecyclerView recyclerView;
    DocumentAdapter adapter;
    ArrayList<Document> mData;
    private CarouselView carouselView;
    ArrayList<SlideItem> slideItemList;

    //ViewPager2 viewPager2;
    //Handler slideHandler = new Handler();


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
        carouselView.pauseCarousel();
        //System.out.println("carouselView onPause paused");
    }

    @Override
    public void onResume() {
        super.onResume();
        carouselView.setCurrentItem(0, true);
        carouselView.playCarousel();
        //System.out.println("carouselView onResume playing");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //viewPager2 = view.findViewById(R.id.vp_imageSlider);

        //Liste à récupérée depuis l'api

        slideItemList = new ArrayList<>();
        slideItemList.add(new SlideItem(R.drawable.voiture1, "https://singemp3.com/telechargement-mp3/7192721/quand-je-taime"));
        slideItemList.add(new SlideItem(R.drawable.voiture2, "https://singemp3.com/telechargement-mp3/6598721/titanic"));
        slideItemList.add(new SlideItem(R.drawable.voiture3, "https://singemp3.com/telechargement-mp3/16591169/rossignol-singuila"));

        /*viewPager2.setAdapter(new SliderAdapter(getActivity(), slideItemList, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                Float r = 1 - Math.abs(position);
                page.setScaleY(0.85F+ r +  0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideRunable);
                slideHandler.postDelayed(slideRunable, 3000);
            }
        });*/


        //CarouselView
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(slideItemList.size());
        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(30000);
        carouselView.setCurrentItem(0, true);
        carouselView.playCarousel();
        //System.out.println("carouselView main playing");

        //RecycleerView
        recyclerView = view.findViewById(R.id.rv_document);
        mData = new ArrayList<>();
        mData.add(new Document("CAJOUX", "Le meilleur d'Afrique", "image url"));
        mData.add(new Document("RIZ", "Le meilleur d'Afrique", "image url"));
        mData.add(new Document("TOMATE", "Le meilleur d'Afrique", "image url"));
        mData.add(new Document("PIMENT", "Le meilleur d'Afrique", "image url"));
        mData.add(new Document("CAROTTE", "Le meilleur d'Afrique", "image url"));
        mData.add(new Document("SOJA", "Le meilleur d'Afrique", "image url"));


        adapter = new DocumentAdapter(DocumentFragment.this.getContext(), mData);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(DocumentFragment.this.getContext()));

    }



    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(slideItemList.get(position).getImage());
        }
    };

    /*private Runnable slideRunable  = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunable, 3000);
    }*/
}
