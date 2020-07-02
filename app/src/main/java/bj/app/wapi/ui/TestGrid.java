package bj.app.wapi.ui;

import adapter.CarrouselFormationAdapter;
import adapter.TestGridAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.carrousel_formation.FormationCarrousel;

import android.os.Bundle;

import java.util.ArrayList;

public class TestGrid extends AppCompatActivity {

    ArrayList<Integer> mData;
    RecyclerView rv_rv;
    TestGridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grid);

        rv_rv = findViewById(R.id.rv_rv);
        mData = new ArrayList<>();
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        mData.add(R.drawable.tomato);
        adapter = new TestGridAdapter(TestGrid.this, mData, true, 0);
        rv_rv.setLayoutManager(new GridLayoutManager(TestGrid.this,3, LinearLayoutManager.VERTICAL,false));
        rv_rv.setAdapter(adapter);
    }
}
