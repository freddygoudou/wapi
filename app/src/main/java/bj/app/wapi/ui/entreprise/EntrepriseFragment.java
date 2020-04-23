package bj.app.wapi.ui.entreprise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import bj.app.wapi.R;

public class EntrepriseFragment extends Fragment {

    private EntrepriseViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //notificationsViewModel = ViewModelProviders.of(this).get(EntrepriseViewModel.class);

        View root = inflater.inflate(R.layout.fragment_entreprise, container, false);

        /*final TextView textView = root.findViewById(R.id.text_entreprise);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}