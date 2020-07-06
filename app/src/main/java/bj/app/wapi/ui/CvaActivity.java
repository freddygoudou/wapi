package bj.app.wapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.internal.AccountType;
import com.mikhaellopez.circularimageview.CircularImageView;

public class CvaActivity extends AppCompatActivity implements View.OnClickListener {

    CircularImageView iv_cva_mung_bean, iv_cva_2, iv_cva_3, iv_cva_4, iv_cva_5, iv_cva_6, iv_cva_7, iv_cva_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cva);

        iv_cva_mung_bean = findViewById(R.id.iv_cva_mung_bean);

        iv_cva_2 = findViewById(R.id.iv_cva_2);
        iv_cva_3 = findViewById(R.id.iv_cva_3);
        iv_cva_4 = findViewById(R.id.iv_cva_4);
        iv_cva_5 = findViewById(R.id.iv_cva_5);
        iv_cva_6 = findViewById(R.id.iv_cva_6);
        iv_cva_7 = findViewById(R.id.iv_cva_7);
        iv_cva_8 = findViewById(R.id.iv_cva_8);

        iv_cva_mung_bean.setOnClickListener(this);
        /*iv_cva_2.setOnClickListener(this);
        iv_cva_3.setOnClickListener(this);
        iv_cva_4.setOnClickListener(this);
        iv_cva_5.setOnClickListener(this);
        iv_cva_6.setOnClickListener(this);
        iv_cva_7.setOnClickListener(this);
        iv_cva_8.setOnClickListener(this);*/



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.iv_cva_mung_bean:
                startActivity(new Intent(CvaActivity.this, MainActivity.class)
                        .putExtra("from_wapi_e_learning", "wapi_e_learning")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            default:
                startActivity(new Intent(CvaActivity.this, MainActivity.class)
                        .putExtra("from_wapi_e_learning", "wapi_e_learning")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;

        }
    }
}
