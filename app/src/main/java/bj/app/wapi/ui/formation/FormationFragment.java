package bj.app.wapi.ui.formation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import adapter.FormationPagerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.sousFragment.CarousselBackgroundAudioService;
import bj.app.wapi.ui.formation.sousFragment.VideFragment;
import bj.app.wapi.ui.main.MainActivity;

public class FormationFragment extends Fragment {

    private FormationViewModel dashboardViewModel;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //dashboardViewModel = ViewModelProviders.of(this).get(FormationViewModel.class);

        return inflater.inflate(R.layout.fragment_formation, container, false);

        /*final TextView textView = root.findViewById(R.id.text_formation);

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager2 = view.findViewById(R.id.vp_formation);
        viewPager2.setAdapter(new FormationPagerAdapter(getActivity()));

        tabLayout = view.findViewById(R.id.tbl_formation);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getActivity().stopService(new Intent(getActivity(), CarousselBackgroundAudioService.class));
                    getActivity().startService(new Intent(getActivity(), CarousselBackgroundAudioService.class));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getActivity().stopService(new Intent(getActivity(), CarousselBackgroundAudioService.class));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getActivity().stopService(new Intent(getActivity(), CarousselBackgroundAudioService.class));
                    getActivity().startService(new Intent(getActivity(), CarousselBackgroundAudioService.class));
                }
            }
        });

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        //tab.setText(R.string.documents);
                        tab.setIcon(R.drawable.ic_insert_drive_file_black_24dp);
                        break;
                    case 1:
                        //tab.setText(R.string.videos);
                        tab.setIcon(R.drawable.ic_video_library_black_24dp);
                        break;
                    case 2:
                        //tab.setText(R.string.vide);
                        tab.setIcon(R.drawable.ic_directions_walk_black_24dp);
                        break;
                }
            }
        }
        );
        tabLayoutMediator.attach();

    }
}