package bj.app.wapi.ui.carrousel_formation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import adapter.CarrouselFormationAdapter;
import adapter.ExploitationAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import api.RetrofitClient;
import bj.app.wapi.R;
import bj.app.wapi.ui.AudioBackgroundService;
import bj.app.wapi.ui.entreprise.EntrepriseFragment;
import entity.AudioCarrousel;
import entity.CarrouselFormation;
import entity.ImageCarrousel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    public void onStart() {
        super.onStart();
        formationPosition = 0;
    }

    @Override
    public void onResume() {
        super.onResume();
        formationPosition = 0;
        loadRessources();
        /*mData.clear();
        mData.addAll(carrouselFormations.get(0).getImages());
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
        startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                .putExtra("audiosFormation", carrouselFormations.get(0).getAudios()));*/
    }

    @Override
    public void onStop() {
        super.onStop();
        formationPosition = 0;
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
        /*loadRessources();
        mData.clear();
        adapter.notifyDataSetChanged();
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));*/
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_carrousel_formation);

        rv_carrousel_formation_image = findViewById(R.id.rv_carrousel_formation_image);

        ib_back_formation = findViewById(R.id.ib_back_formation);
        ib_repeat_formation = findViewById(R.id.ib_repeat_formation);
        ib_next_formation = findViewById(R.id.ib_next_formation);

        tv_formation_texte_content = findViewById(R.id.tv_formation_texte_content);

        progressDialog = new ProgressDialog(this);
        ib_back_formation.setOnClickListener(this);
        ib_repeat_formation.setOnClickListener(this);
        ib_next_formation.setOnClickListener(this);


        loadRessources();
    }

    private void updateUiAboutFormation(int position, String direction){
        stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
        //System.out.println("EXÉCUTION DE LA FORMATION N° "+position);
        if ((carrouselFormations != null) || (carrouselFormations.size()-1>=formationPosition) || (formationPosition >= 0)){
            if (direction.equals(BACK)){
                mData.clear();
                //System.out.println("IMAGE NAME LIST FOR POSITION :"+position+" IS "+carrouselFormations.get(position).getImages().toString());
                mData.addAll(carrouselFormations.get(position).getImages());
                adapter.notifyDataSetChanged();
                tv_formation_texte_content.setText(carrouselFormations.get(position).getAudios().get(0).getTexte());
                //System.out.println("Adapter Item count ="+adapter.getItemCount()+" .En position "+formationPosition+" MDATA SIZE = "+mData.size()+" AND MDATA = "+mData.toString());
                //rv_carrousel_formation_image.setAdapter(adapter);
                //PLAY AUDIOS
                startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                    .putExtra("audiosFormation", carrouselFormations.get(position).getAudios()));

            }else if (direction.equals(REPEAT)){
                //REPEAT AUDIOS
                startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                        .putExtra("audiosFormation", carrouselFormations.get(position).getAudios()));

                //System.out.println("Adapter Item count ="+adapter.getItemCount()+" .En position "+formationPosition+" MDATA SIZE = "+mData.size()+" AND MDATA = "+mData.toString());

            }else if (direction.equals(NEXT)){
                mData.clear();
                //System.out.println("IMAGE NAME LIST FOR POSITION :"+position+" IS "+carrouselFormations.get(position).getImages().toString());
                mData.addAll(carrouselFormations.get(position).getImages());
                adapter.notifyDataSetChanged();
                tv_formation_texte_content.setText(carrouselFormations.get(position).getAudios().get(0).getTexte());

                //System.out.println("Adapter Item count ="+adapter.getItemCount()+" .En position "+formationPosition+" MDATA SIZE = "+mData.size()+" AND MDATA = "+mData.toString());
                //PLAY AUDIOS
                startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                        .putExtra("audiosFormation", carrouselFormations.get(position).getAudios()));
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

        //1
        imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        carrouselFormations = new ArrayList<>();
        /*imageCarrousels.add(new ImageCarrousel("","Image 0-0",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","Image 0-1",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","Image 0-2",R.drawable.index));
        audioCarrousels.add(new AudioCarrousel("",R.raw.grace_a_cette_application, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.tu_sauras_comment_bien_gerer_tes_champs_tes_recoltes_et_ton_elevage_comme_grand_patron, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.tu_pourras_trouver_ou_acheter, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.si_tu_souhaite_faire_former_femme_apprend, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.si_tu_souhaite_savoir_comment_bien_gerer_tes_champs, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.si_tu_souhaite_acheter_maque_champs_vendre_produit, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        //2
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","Image 1-0",R.drawable.soja));
        imageCarrousels.add(new ImageCarrousel("","Image 1-1",R.drawable.tomato));
        audioCarrousels.add(new AudioCarrousel("",R.raw.merci_davoir_choisi_dapprendre_a_devenir, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.si_tu_souhaite_te_former_classe_maitre, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.si_tu_souhaites_apprendre_par_video_television, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 3","", imageCarrousels, audioCarrousels));*/

        //3
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","Image 2-0",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","Image 2-1",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","Image 2-2",R.drawable.index));
        imageCarrousels.add(new ImageCarrousel("","Image 2-3",R.drawable.mais));
        audioCarrousels.add(new AudioCarrousel("",R.raw.merci_davoir_choisi_te_former, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.appui_image_ceque_souhaite_apprendre, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/


        //4
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        audioCarrousels.add(new AudioCarrousel("",R.raw.bienvenue_formation_comment_bien_produire_lentille_verte_mung_bean_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        //5
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.mais));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.soja));
        audioCarrousels.add(new AudioCarrousel("",R.raw.merci_avoir_choisi_produire_mung_bean, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.le_mung_bean_est_un_haricot_en_50_jours, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.pour_produire_lentille_verte_mung_bean_il_faut_semer_bonne_graines_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.comment_obenir_bonne_graine_mung_bean_semer_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        //6
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        audioCarrousels.add(new AudioCarrousel("",R.raw.pour_obtenir_de_bonne_graine_semer_mung_bean_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.comment_reconnaitre_semence_bonne_qualite, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/


        //7
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        audioCarrousels.add(new AudioCarrousel("",R.raw.une_bonne_semence_doit_graine_bien_mur, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.maintenant_obtenu_bonne_graine_semer_etape_suivant, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/



        //8
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.mais));
        audioCarrousels.add(new AudioCarrousel("",R.raw.il_te_faut_choisir_un_bon_terrain, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.le_mung_bean_pousse_bien_sur_sol_leger_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.maintenant_que_tu_sais_type_sol_convient_mung_bean_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        //9
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        audioCarrousels.add(new AudioCarrousel("",R.raw.pour_cultiver_mung_bean_il_faut_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.ton_sol_laboure_comment_semmer_lentille_verte_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        //10
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.mais));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.mais));
        audioCarrousels.add(new AudioCarrousel("",R.raw.il_faut_semer_mung_bean_sur_sol_mouille_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.une_fois_graine_lentille_verte_germe_que_faire_pour_bonne_production_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        //11
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.carrot));
        audioCarrousels.add(new AudioCarrousel("",R.raw.comme_toute_autre_culture_mung_bean_besoin_entretient_fr, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.comment_sera_ton_champs_apres_operation, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        //12
        /*imageCarrousels = new ArrayList<>();
        audioCarrousels = new ArrayList<>();
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.baobab));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.carrot));
        imageCarrousels.add(new ImageCarrousel("","",R.drawable.index));
        audioCarrousels.add(new AudioCarrousel("",R.raw.deux_semaines_premier_sarclage, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.il_te_faudra_poursuivre_entrerient_mais_quand_comment, ""));
        audioCarrousels.add(new AudioCarrousel("",R.raw.next_back_repeat_formation_fr, ""));
        carrouselFormations.add(new CarrouselFormation("Ceci est le texte du module 2","", imageCarrousels, audioCarrousels));*/

        Call<ArrayList<CarrouselFormation>> call = RetrofitClient
                .getmInstance()
                .getApi()
                .getAllImagesAudiosFormations();

        call.enqueue(new Callback<ArrayList<CarrouselFormation>>() {
            @Override
            public void onResponse(Call<ArrayList<CarrouselFormation>> call, Response<ArrayList<CarrouselFormation>> response) {

                try{

                    if (response.code() == 200){

                        System.out.println("DATA IS  :"+response.body().toString());
                        System.out.println("AUDIO IS  :"+response.body().get(0).getAudios().toString());
                        if( (response.body().get(0) != null) && (response.body().size() > 0)){
                            carrouselFormations.addAll(response.body());
                            mData.clear();
                            mData.addAll(carrouselFormations.get(0).getImages());
                            adapter = new CarrouselFormationAdapter(FormationCarrousel.this, mData);
                            rv_carrousel_formation_image = findViewById(R.id.rv_carrousel_formation_image);
                            rv_carrousel_formation_image.setLayoutManager(new GridLayoutManager(FormationCarrousel.this, 2));
                            rv_carrousel_formation_image.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            tv_formation_texte_content.setText(carrouselFormations.get(0).getAudios().get(0).getTexte());

                            //JOUER LE PREMIER AUDIO
                            stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
                            startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                                    .putExtra("audiosFormation", carrouselFormations.get(formationPosition).getAudios()));
                        }

                        /*for (int i=0; i<list.size(); i++){
                        System.out.println("Result Formation : "+response.body().toString());
                        }*/
                    }else {
                        Toast.makeText(FormationCarrousel.this, "Response code is :"+response.code()+"\n"+" S_Response message "+response.message(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CarrouselFormation>> call, Throwable t) {

            }
        });                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
        
        /*mData.addAll(carrouselFormations.get(0).getImages());
        System.out.println("MDATA SIZE IS :"+mData.size());
        System.out.println("MDATA  :"+mData.toString());*/


    }


}
