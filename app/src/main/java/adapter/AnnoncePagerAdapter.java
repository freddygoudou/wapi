package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import bj.app.wapi.ui.annonce.sousFragment.AchatFragment;
import bj.app.wapi.ui.annonce.sousFragment.VenteFragment;
import bj.app.wapi.ui.formation.sousFragment.VideFragment;

public class AnnoncePagerAdapter extends FragmentStateAdapter {


    public AnnoncePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new AchatFragment();
            default:
                return new VenteFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
