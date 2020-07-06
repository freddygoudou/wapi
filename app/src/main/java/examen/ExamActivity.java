package examen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.AudioBackgroundService;
import bj.app.wapi.ui.ChoixLangue;
import bj.app.wapi.ui.CvaActivity;
import bj.app.wapi.ui.NewChampsActivity;
import entity.AudioAndTextview;
import entity.AudioCarrousel;
import entity.ImageCarrousel;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity  implements StartDragListener{

    ArrayList<ImageCarrousel> imageCarrousels = new ArrayList<>();;
    ArrayList<AudioCarrousel> audioCarrousels = new ArrayList<>();;
    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ItemTouchHelper touchHelper;

    ImageView iv_exam_no, iv_exam_ok;
    CircularImageView iv_exam_repeat, iv_exam_valid;
    ArrayList<Integer> orders= null;
    Animation shake;
    int my_position;
    MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (getIntent().hasExtra("ImageForTest")) {
            imageCarrousels.clear();
            imageCarrousels.addAll(getIntent().getParcelableArrayListExtra("ImageForTest"));
            audioCarrousels.addAll(getIntent().getParcelableArrayListExtra("AudioForTest"));

            //LECTURE DES AUDIOS
            playAudio(audioCarrousels, 0);

            /*startService(new Intent(getApplicationContext(), AudioBackgroundService.class)
                    .putParcelableArrayListExtra("audiosFormation", audioCarrousels)
                    .putExtra("connexionState",false));*/

            //System.out.println("TAKE OT IS :: "+imageCarrousels.toString());

            //orders.clear();
            //orders.addAll(loadImageOrder(imageCarrousels));

            shake = AnimationUtils.loadAnimation(ExamActivity.this, R.anim.shakeanimation);

            iv_exam_repeat = findViewById(R.id.iv_exam_repeat);
            iv_exam_valid = findViewById(R.id.iv_exam_valid);
            iv_exam_ok = findViewById(R.id.iv_exam_ok);
            iv_exam_no = findViewById(R.id.iv_exam_no);
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

            populateRecyclerView(imageCarrousels);

            iv_exam_repeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ExamActivity.this, CvaActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            });
            iv_exam_valid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (orders !=null){
                        boolean examResult = validExam(orders);
                        if (examResult){
                            iv_exam_ok.startAnimation(shake);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    messageValidationFormation();
                                }
                            },2000);

                        }else {
                            Toast.makeText(ExamActivity.this, "FAILLED FORM EXAM", Toast.LENGTH_LONG).show();
                            iv_exam_no.startAnimation(shake);
                        }
                    }else {
                        iv_exam_no.startAnimation(shake);
                        Toast.makeText(ExamActivity.this, "FAILLED FORM EXAM", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    public boolean validExam(ArrayList<Integer> orders){
            for (int i=0; i<orders.size(); i++){
                if ((i<orders.size()-1)){
                    if (orders.get(i) >= orders.get(i+1)){
                        return false;
                    }
                }
            }
        return true;
    }

    public ArrayList<Integer> loadImageOrder(ArrayList<ImageCarrousel> imageCarrousels){
        ArrayList<Integer> orders = new ArrayList<>();
        orders.clear();
        for (int i=0; i<imageCarrousels.size(); i++){
            orders.add(imageCarrousels.get(i).getOrder());
        }
        return orders;
    }

    private void populateRecyclerView(ArrayList<ImageCarrousel> imageCarrousels) {
        /*imageCarrousels.add(new ImageCarrousel("id-1", "http://lorempixel.com/400/200",1));
        imageCarrousels.add(new ImageCarrousel("id-2","https://www.fillmurray.com/640/360",1));
        imageCarrousels.add(new ImageCarrousel("id-3","https://www.fillmurray.com/640/360",1));
        imageCarrousels.add(new ImageCarrousel("id-4","https://www.fillmurray.com/640/360",1));
        imageCarrousels.add(new ImageCarrousel("id-5","https://i.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U",1));
*/
        mAdapter = new RecyclerViewAdapter(imageCarrousels, this);

        mAdapter.setMyInterface(new RecyclerViewAdapter.MyInterface() {
            @Override
            public void onUpdate(ArrayList<ImageCarrousel> originalData, ArrayList<ImageCarrousel> newData) {
                //((TextView)findViewById(R.id.log)).setText(originalData.toString()+" => "+newData.toString());
                orders = new ArrayList<>();
                orders.addAll(loadImageOrder(imageCarrousels));
                ((TextView)findViewById(R.id.log)).setText(orders.toString());
            }
        });

        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        touchHelper  = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ExamActivity.this, CvaActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null){
            player.stop();
            player.release();
        }
        //stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null){
            player.stop();
            player.release();
        }
        //stopService(new Intent(getApplicationContext(), AudioBackgroundService.class));
    }

    public void playAudio(ArrayList<AudioCarrousel> audioCarrousels, int position){

        my_position = position;
        player = MediaPlayer.create(ExamActivity.this, Uri.parse(String.valueOf(Environment.getExternalStoragePublicDirectory(audioCarrousels.get(my_position).getBaseUri()))));
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.stop();
                player.release();
                my_position++;
                if (my_position < audioCarrousels.size()){
                    playAudio(audioCarrousels, my_position);
                }

            }
        });
    }

    private void messageValidationFormation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ExamActivity.this);
        builder.setTitle("Examen de fin de formation");
        builder.setMessage(R.string.message_validation_formation);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                startActivity(new Intent(ExamActivity.this, CvaActivity.class));
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
