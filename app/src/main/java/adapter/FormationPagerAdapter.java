package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import bj.app.wapi.ui.formation.sousFragment.DocumentFragment;
import bj.app.wapi.ui.formation.sousFragment.VideFragment;
import bj.app.wapi.ui.formation.sousFragment.VideoFragment;

public class FormationPagerAdapter extends FragmentStateAdapter {


    public FormationPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

            if (position == 0) {
                return new DocumentFragment();
            }else if (position == 0) {
                return  new VideoFragment();
            }
        return  null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
