package bj.app.wapi.ui.annonce;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import adapter.AnnoncePagerAdapter;
import adapter.FormationPagerAdapter;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;
import bj.app.wapi.R;
import bj.app.wapi.ui.nouvelleAnnonce.NouvelleAnnonce;

public class AnnonceFragment extends Fragment {

    private AnnonceViewModel homeViewModel;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    FloatingActionButton fab_annonce;
    Boolean isFABOpen = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = ViewModelProviders.of(this).get(AnnonceViewModel.class);

        return inflater.inflate(R.layout.fragment_annonce, container, false);

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager2 = view.findViewById(R.id.vp_annonce);
        viewPager2.setAdapter(new AnnoncePagerAdapter(getActivity()));


        fab_annonce = view.findViewById(R.id.fab_annonce);
        fab_annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), NouvelleAnnonce.class));
            }
        });



        tabLayout = view.findViewById(R.id.tbl_annonce);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        tab.setText(R.string.achats);
                        tab.setIcon(R.drawable.ic_business_black_24dp);
                        break;
                    case 1:
                        tab.setText(R.string.ventes);
                        tab.setIcon(R.drawable.ic_business_black_24dp);
                        break;
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }

    /*private void showFABMenu(){
        isFABOpen=true;
        fab_help.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab_publier_annonce.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab_help.animate().translationY(0);
        fab_publier_annonce.animate().translationY(0);
    }*/


}