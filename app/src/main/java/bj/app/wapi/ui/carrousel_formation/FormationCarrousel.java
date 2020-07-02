package bj.app.wapi.ui.carrousel_formation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import adapter.CarrouselFormationAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.AudioBackgroundService;
import bj.app.wapi.ui.WapiApplication;
import database.DatabaseHelper;
import entity.AudioCarrousel;
import entityBackend.CarrouselFormation;
import entity.ImageCarrousel;

public class FormationCarrousel extends AppCompatActivity implements View.OnClickListener {

    public static final String BACK = "BACK";
    public static final String REPEAT = "REPEAT";
    public static final String NEXT = "NEXT";
    CarrouselFormationAdapter adapter;
    RecyclerView rv_carrousel_formation_image;
    ImageButton ib_back_formation, ib_repeat_formation, ib_next_formation;
    TextView tv_formation_texte_content;
    ArrayList<ImageCarrousel> mData = new ArrayList<>();
    ArrayList<ImageCarrousel>imageCarrousels;
    ArrayList<AudioCarrousel> audioCarrousels;
    ArrayList<CarrouselFormation> carrouselFormations;
    ProgressDialog progressDialog;
    int formationPosition = 0;
    Intent serviceIntent;
    Button btn_show_texte;
    Boolean connexionState;

    @Override
    public void onStart() {
        super.onStart();
        formationPosition = 0;
        //WapiApplication wapiApplication = getApplicationContext();
        //System.out.println("In FormationCarrousel 1117 : "+wapiApplication.getCarrouselFormations().toString());
        //System.out.println("App data is : "+wapiApplication.getCarrouselFormations().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        formationPosition = 0;
        loadRessources();
    }
    @Override
    public void onStop() {
        super.onStop();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        formationPosition = 0;
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_carrousel_formation);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        tv_formation_texte_content = findViewById(R.id.tv_formation_texte_content);

        rv_carrousel_formation_image = findViewById(R.id.rv_carrousel_formation_image);

        btn_show_texte = findViewById(R.id.btn_show_texte);
        btn_show_texte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_formation_texte_content.getVisibility() == View.INVISIBLE)
                    tv_formation_texte_content.setVisibility(View.VISIBLE);
                else
                    tv_formation_texte_content.setVisibility(View.INVISIBLE);
            }
        });
        ib_back_formation = findViewById(R.id.ib_back_formation);
        ib_repeat_formation = findViewById(R.id.ib_repeat_formation);
        ib_next_formation = findViewById(R.id.ib_next_formation);


        progressDialog = new ProgressDialog(this);
        ib_back_formation.setOnClickListener(this);
        ib_repeat_formation.setOnClickListener(this);
        ib_next_formation.setOnClickListener(this);

        imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        carrouselFormations = new ArrayList<>();

        loadRessources();



    }


    private void updateUiAboutFormation(int position, String direction){
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
        System.out.println("======================================================EXÉCUTION DE LA FORMATION N° "+0);
        if ((carrouselFormations != null) || (carrouselFormations.size()-1>=formationPosition) || (formationPosition >= 0)){
            System.out.println("======================================================EXÉCUTION DE LA FORMATION N° "+1);

            if (direction.equals(BACK)){

                rv_carrousel_formation_image.setLayoutManager(new LinearLayoutManager(FormationCarrousel.this));
                rv_carrousel_formation_image.requestLayout();

                mData.clear();
                System.out.println("========================================IMAGE NAME LIST FOR POSITION :"+position+" IS "+carrouselFormations.get(0).getImages().toString());
                mData.addAll(carrouselFormations.get(position).getImages());
                adapter.notifyDataSetChanged();
                adapter.setDiapo(position);
                //tv_formation_texte_content.setText(carrouselFormations.get(position).getAudios().get(0).getTexte());
                tv_formation_texte_content.setText(carrouselFormations.get(position).getTexte());
                //System.out.println("Adapter Item count ="+adapter.getItemCount()+" .En position "+formationPosition+" MDATA SIZE = "+mData.size()+" AND MDATA = "+mData.toString());
                //rv_carrousel_formation_image.setAdapter(adapter);
                //PLAY AUDIOS
                startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                        .putParcelableArrayListExtra("audiosFormation", carrouselFormations.get(position).getAudios())
                        .putExtra("connexionState",connexionState));

            }else if (direction.equals(REPEAT)){
                //REPEAT AUDIOS
                stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
                startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                        .putParcelableArrayListExtra("audiosFormation", carrouselFormations.get(position).getAudios())
                        .putExtra("connexionState",connexionState));

                //System.out.println("Adapter Item count ="+adapter.getItemCount()+" .En position "+formationPosition+" MDATA SIZE = "+mData.size()+" AND MDATA = "+mData.toString());

            }else if (direction.equals(NEXT)){
                if (formationPosition == 27){
                    rv_carrousel_formation_image.setLayoutManager(new GridLayoutManager(FormationCarrousel.this,3,LinearLayoutManager.VERTICAL,false));
                    rv_carrousel_formation_image.requestLayout();
                }else {
                    rv_carrousel_formation_image.setLayoutManager(new LinearLayoutManager(FormationCarrousel.this));
                    rv_carrousel_formation_image.requestLayout();
                }
                mData.clear();
                //System.out.println("IMAGE NAME LIST FOR POSITION :"+position+" IS "+carrouselFormations.get(position).getImages().toString());
                mData.addAll(carrouselFormations.get(position).getImages());
                adapter.notifyDataSetChanged();
                adapter.setDiapo(position);
                //tv_formation_texte_content.setText(carrouselFormations.get(position).getAudios().get(0).getTexte());
                tv_formation_texte_content.setText(carrouselFormations.get(position).getTexte());

                //System.out.println("Adapter Item count ="+adapter.getItemCount()+" .En position "+formationPosition+" MDATA SIZE = "+mData.size()+" AND MDATA = "+mData.toString());
                //PLAY AUDIOS
                startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                        .putParcelableArrayListExtra("audiosFormation", carrouselFormations.get(position).getAudios())
                        .putExtra("connexionState",connexionState));
            }
        }else if(carrouselFormations.size()-1>=formationPosition){
            Toast.makeText(FormationCarrousel.this, R.string.dernier_module_formation, Toast.LENGTH_SHORT).show();
        }else if(formationPosition == 0){
            Toast.makeText(FormationCarrousel.this, R.string.premier_module_formation, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ib_back_formation){
            if (formationPosition > 0){
                formationPosition--;
                updateUiAboutFormation(formationPosition, BACK);
                if (formationPosition == 0){
                    ib_back_formation.setActivated(false);
                }
                if (formationPosition < carrouselFormations.size()-1){
                    ib_next_formation.setActivated(true);
                }
            }else if (formationPosition == 0){
                Toast.makeText(FormationCarrousel.this, R.string.premier_module_formation, Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId() == R.id.ib_repeat_formation){
            updateUiAboutFormation(formationPosition, REPEAT);
        }else if (view.getId() == R.id.ib_next_formation){
            if (formationPosition < carrouselFormations.size()-1){
                formationPosition++;
                updateUiAboutFormation(formationPosition, NEXT);
                if (formationPosition >= carrouselFormations.size()-1){
                    ib_next_formation.setActivated(false);
                }
                if (formationPosition > 0){
                    ib_back_formation.setActivated(true);
                }
            }else if(formationPosition >= carrouselFormations.size()-1){
                Toast.makeText(FormationCarrousel.this, R.string.dernier_module_formation, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void loadRessources() {

//        if (getIntent().hasExtra("carrouselFormations") && (getIntent().getParcelableArrayListExtra("carrouselFormations") != null) && (getIntent().hasExtra("connexionState"))) {
//            carrouselFormations = new ArrayList<>();
//            carrouselFormations.clear();
//            carrouselFormations.addAll(getIntent().getParcelableArrayListExtra("carrouselFormations"));
//            connexionState = getIntent().getBooleanExtra("connexionState", false);
//
//            mData.clear();
//            mData.addAll(carrouselFormations.get(0).getImages());
//            adapter = new CarrouselFormationAdapter(FormationCarrousel.this, mData);
//            rv_carrousel_formation_image.setLayoutManager(new GridLayoutManager(FormationCarrousel.this, 2));
//            rv_carrousel_formation_image.setAdapter(adapter);
//
//            tv_formation_texte_content.setText(carrouselFormations.get(0).getTexte());
//
//            //JOUER LE PREMIER AUDIO
//            stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
//            startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
//                    .putExtra("connexionState",connexionState)
//                    .putParcelableArrayListExtra("audiosFormation", carrouselFormations.get(0).getAudios()));
//
//            System.out.println("LA VALEUR ENVOYÉE EST : "+carrouselFormations.get(0).getAudios().get(0));
//        }
        //   if() {

        if (getIntent().hasExtra("carrouselFormations") && (getIntent().hasExtra("connexionState"))) {
//                Long my_id = Long.parseLong(getIntent().getStringExtra("carrouselFormations"));
//                System.out.println("=============================================================my_id:" + my_id);
//
//                DatabaseHelper databaseHelper = new DatabaseHelper(this);
//
//                if (!connexionState) {

            carrouselFormations = new ArrayList<CarrouselFormation>();
            carrouselFormations.clear();
            //     carrouselFormations.addAll(databaseHelper.getAllCarousselFormationsById(my_id));
//                } else {
//
//                }
            carrouselFormations.addAll(getIntent().getParcelableArrayListExtra("carrouselFormations"));
            connexionState = getIntent().getBooleanExtra("connexionState", false);

            mData.clear();
            mData.addAll(carrouselFormations.get(0).getImages());
            adapter = new CarrouselFormationAdapter(FormationCarrousel.this, mData, connexionState, 0);
            rv_carrousel_formation_image.setLayoutManager(new LinearLayoutManager(FormationCarrousel.this));
            rv_carrousel_formation_image.setAdapter(adapter);

            tv_formation_texte_content.setText(carrouselFormations.get(0).getTexte());

            //JOUER LE PREMIER AUDIO
            stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
            startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                    .putExtra("connexionState", connexionState)
                    .putParcelableArrayListExtra("audiosFormation", carrouselFormations.get(0).getAudios()));

            System.out.println("LA VALEUR ENVOYÉE EST : " + carrouselFormations.get(0).getAudios().get(0));
        }

        //   }
        /*else{
            if (getIntent().hasExtra("my_id") && (getIntent().getStringExtra("my_id") != null)) {
                Long my_id = Long.parseLong(getIntent().getStringExtra("my_id"));
                System.out.println("=============================================================my_id:" + my_id);

                DatabaseHelper databaseHelper = new DatabaseHelper(this);



                carrouselFormations = new ArrayList<CarrouselFormation>();
                carrouselFormations.clear();
                carrouselFormations.addAll(databaseHelper.getAllCarousselFormationsById(my_id));

                //  carrouselFormations.addAll(getIntent().getParcelableArrayListExtra("carrouselFormations"));
                connexionState = getIntent().getBooleanExtra("connexionState", false);

                mData.clear();
                mData.addAll(carrouselFormations.get(0).getImages());
                System.out.println("ELEMENT "+databaseHelper.getAllCarousselFormationsById(my_id).size());
                System.out.println("AKAPELA  .... " + carrouselFormations.get(0).getImages().size());
                adapter = new CarrouselFormationAdapter(FormationCarrousel.this, mData, connexionState);
                rv_carrousel_formation_image.setLayoutManager(new LinearLayoutManager(FormationCarrousel.this));
                rv_carrousel_formation_image.setAdapter(adapter);

                tv_formation_texte_content.setText(carrouselFormations.get(0).getTexte());

                //JOUER LE PREMIER AUDIO
                stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
                startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                        .putExtra("connexionState", connexionState)
                        .putParcelableArrayListExtra("audiosFormation", carrouselFormations.get(0).getAudios()));

                System.out.println("LA VALEUR ENVOYÉE EST : " + carrouselFormations.get(0).getAudios().get(0));
            }
        }*/
        /*Call<ArrayList<CarrouselFormation>> call = RetrofitClient
                .getmInstance()
                .getApi()
                .getAllCarousselsFormation();
        call.enqueue(new Callback<ArrayList<CarrouselFormation>>() {
            @Override
            public void onResponse(Call<ArrayList<CarrouselFormation>> call, Response<ArrayList<CarrouselFormation>> response) {

                //Toast.makeText(FormationCarrousel.this, "Request response  : "+response.body().toString(), Toast.LENGTH_LONG).show();
                //System.out.println("Request response  : "+response.body().toString());

                    if (response.code() == 200){
                        try {
                            carrouselFormations.clear();
                            carrouselFormations.addAll(response.body());
                            mData.clear();
                            mData.addAll(carrouselFormations.get(0).getImages());
                            adapter = new CarrouselFormationAdapter(FormationCarrousel.this, mData);
                            rv_carrousel_formation_image = findViewById(R.id.rv_carrousel_formation_image);
                            rv_carrousel_formation_image.setLayoutManager(new GridLayoutManager(FormationCarrousel.this, 2));
                            rv_carrousel_formation_image.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            //tv_formation_texte_content.setText(carrouselFormations.get(0).getAudios().get(0).getTexte());



                            //JOUER LE PREMIER AUDIO
                            stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
                            startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                                    .putExtra("audiosFormation", carrouselFormations.get(formationPosition).getAudios()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("Request response message : "+response.message());
                    }

            }

            @Override
            public void onFailure(Call<ArrayList<CarrouselFormation>> call, Throwable t) {

            }
        });*/

    }


}