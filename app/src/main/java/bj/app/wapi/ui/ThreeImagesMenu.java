package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ThreeImagesMenu extends AppCompatActivity {

    ImageView iv_e_learning, iv_mon_exploitation, iv_business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_images_menu);

        iv_e_learning = findViewById(R.id.iv_e_learning);
        iv_mon_exploitation = findViewById(R.id.iv_mon_exploitation);
        iv_business = findViewById(R.id.iv_business);

        iv_e_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThreeImagesMenu.this, MainActivity.class)
                    .putExtra("from_wapi_e_learning", "wapi_e_learning")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        iv_mon_exploitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThreeImagesMenu.this, MainActivity.class)
                        .putExtra("from_wapi_mon_exploitation", "wapi_mon_exploitation")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        iv_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThreeImagesMenu.this, MainActivity.class)
                        .putExtra("from_wapi_business", "wapi_business")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        startAudio();
    }

    private void startAudio() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ThreeImagesMenu.this, ChoixLangue.class));
    }
}
