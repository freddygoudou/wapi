package bj.app.wapi.ui.menage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import bj.app.wapi.R;

public class MenageFragment extends Fragment {

    private MenageViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //notificationsViewModel = ViewModelProviders.of(this).get(MenageViewModel.class);

        View root = inflater.inflate(R.layout.fragment_menage, container, false);

        /*final TextView textView = root.findViewById(R.id.text_menage);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}