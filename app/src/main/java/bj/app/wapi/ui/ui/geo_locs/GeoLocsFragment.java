package bj.app.wapi.ui.ui.geo_locs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapter.EmployeeAdapter;
import adapter.LocationAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.NewChampsActivity;
import bj.app.wapi.ui.ui.employee.EmployeeFragment;
import entityBackend.ChampsLocation;

public class GeoLocsFragment extends Fragment {

    RecyclerView rv_geo_locs;
    FloatingActionButton fab_geo_locs;
    LocationAdapter adapter;
    List<ChampsLocation> mData = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geo_locs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_geo_locs = view.findViewById(R.id.rv_geo_locs);


        fab_geo_locs = view.findViewById(R.id.fab_geo_locs);

        fab_geo_locs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addChampsPosition();
            }
        });

        loadResources();
    }

    private void loadResources() {

        mData.add(new ChampsLocation(1.02365, 7.1556));
        mData.add(new ChampsLocation(9.02365, 4.1556));
        mData.add(new ChampsLocation(7.02365, 5.1556));

        adapter = new LocationAdapter(GeoLocsFragment.this.getActivity(), mData);
        rv_geo_locs.setAdapter(adapter);
        rv_geo_locs.setLayoutManager(new LinearLayoutManager(GeoLocsFragment.this.getContext()));
        adapter.notifyDataSetChanged();
    }

    private void addChampsPosition() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GeoLocsFragment.this.getContext());
        builder.setTitle("Ajout d'une coordonnée");
        builder.setMessage(R.string.add_new_champs_position_message);
        builder.setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                saveLocation(1.0, 1.2);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void saveLocation(double v, double v1) {
    }
}
