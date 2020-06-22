package bj.app.wapi.ui.splash;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import bj.app.wapi.R;
import bj.app.wapi.ui.ChoixLangue;
import bj.app.wapi.ui.NewChampsActivity;
import bj.app.wapi.ui.ThreeImagesMenu;
import bj.app.wapi.ui.login.LoginActivity;
import bj.app.wapi.ui.main.MainActivity;
import database.DatabaseHelper;
import de.hdodenhof.circleimageview.CircleImageView;
import entity.AudioCarrousel;
import entity.ImageCarrousel;
import entity.ZipManager;
import entityBackend.Carrousel;
import entityBackend.CarrouselFormation;
import entityBackend.Farmer;
import entityBackend.User;
import ir.mahdi.mzip.zip.ZipArchive;
import storage.SharedPrefManager;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SplashActivity extends AppCompatActivity {

    CircleImageView appIcon;
    ArrayList<AudioCarrousel> audioCarrousels;
    MediaPlayer player;
    Farmer farmer;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference mUserDatabase;
    public static final int PERMISSION_STORAGE_CODE = 1000;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<CarrouselFormation> carrouselFormations = new ArrayList<>();
        ArrayList<AudioCarrousel> audioCarrousels = new ArrayList<>();
        ArrayList<ImageCarrousel> imageCarrousels = new ArrayList<>();

        audioCarrousels.add(new AudioCarrousel("urlaudio1","mybaseurla", 1,1));
        audioCarrousels.add(new AudioCarrousel("urlaudio2","mybaseurla", 2,1));
        audioCarrousels.add(new AudioCarrousel("urlaudio3","mybaseurla", 3,1));
        audioCarrousels.add(new AudioCarrousel("urlaudio4","mybaseurla", 4,1));

        imageCarrousels.add(new ImageCarrousel("urlimage1","mybaseurli", 1));
        imageCarrousels.add(new ImageCarrousel("urlimage2","mybaseurli", 2));
        imageCarrousels.add(new ImageCarrousel("urlimage3","mybaseurli", 3));
        imageCarrousels.add(new ImageCarrousel("urlimage4","mybaseurli", 4));

        carrouselFormations.add(new CarrouselFormation("Le résumé","1",imageCarrousels, audioCarrousels));
        Carrousel carrousel = new Carrousel("","Mung Bean","url","Production de Mung Bean", "English", carrouselFormations);
        //databaseHelper.saveCaroussel(carrousel);

        //System.out.println("SQLITE RESULT : "+databaseHelper.getAllCarousselsRowsCount());
        //System.out.println("SQLITE RESULT FORMATION : "+databaseHelper.getAllCarousselFormationsById(1L).toString());
        //System.out.println("SQLITE RESULT NOW  : "+databaseHelper.getAllCaroussels().toString());

        /*if (getIntent().hasExtra("farmer")){
            farmer = getIntent().getParcelableExtra("farmer");
            System.out.println("Voici farmer : "+farmer.toString());
        }*/

        appIcon = findViewById(R.id.app_icon);
        Animation welcome_animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.welcome_animation);
        appIcon.startAnimation(welcome_animation);

        /*
        MediaPlayer player = MediaPlayer.create(this, Uri.fromFile(Environment.getExternalStoragePublicDirectory("Download/Wapi/Formation/Carrousel/french/mungbean/production/1/audio1.mp3")));
        player.start();*/

        System.out.println("Unzip Start ..."+Environment.DIRECTORY_DOWNLOADS+"/Wapi/FormationCaroussel/Biali/Mung Been/Mung Been.zip");

        //try {
            /*File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/mung_bean_french.zip");

            if (file.exists()){
                Toast.makeText(this, "File existe", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "File read : "+file.canRead(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, "File write : "+file.canRead(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, "File execute "+file.canExecute(), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "File not existe", Toast.LENGTH_LONG).show();
            }*/

            /*ZipManager.*/ //unzip(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/Mung Bean.zip", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/check");
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/

        /*try {
            ZipFile zipFile = new ZipFile(Environment.DIRECTORY_DOWNLOADS + "/mung_bean_french.zip");
            zipFile.extractAll(Environment.DIRECTORY_DOWNLOADS+"/");
        } catch (ZipException e) {
            //A message is already provided
            e.printStackTrace();
        }*/

        //requestForpermission();

        /*DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://217.160.241.185:8181/audios_files/french/mungbean/mungbean.zip"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Bean");
        request.setDescription("Bean en cours ...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,  "mungbean.zip");
        DownloadManager manager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
        manager.enqueue(request);*/

        //ZipArchive zipArchive = new ZipArchive();
        //ZipArchive.unzip(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/mungbean.zip",Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(),"");
        //System.out.println("Unzip finish ...");

        //File file = new File(Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Caroussel/french/Mung Bean/Mung Bean.zip");
        //Toast.makeText(this, "Storage 1  : "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "The file name is  : "+file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "The parent file name is  : "+file.getParent(), Toast.LENGTH_LONG).show();
        //ZipArchive.unzip(Environment.getExternalStoragePublicDirectory(file.getAbsolutePath()).getAbsolutePath(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(),"");
        /*try {
            unzipFile(Environment.getExternalStoragePublicDirectory(file.getAbsolutePath()).getAbsolutePath(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        startAudios();
    }

    public void startAudios(){

        User user = SharedPrefManager.getmInstance(this).getUser();
        player = MediaPlayer.create(getApplicationContext(), R.raw.audio_french_d_1_1);
        player.setLooping(false);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.stop();
                player.release();
                player = null;
                if (user != null){
                    if (user.getLangue() != null){
                        //Toast.makeText(SplashActivity.this, "Langue is  :"+user.getLangue(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, ThreeImagesMenu.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                /*.putExtra("farmer", farmer)*/);
                    }else {
                        startActivity(new Intent(SplashActivity.this, ChoixLangue.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                /*.putExtra("farmer", farmer)*/);
                    }
                }else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }

            }
        });
    }

    public  void unzip(String zipFile, String location) throws IOException {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle("Mung Bean");
        builder.setMessage("Dézippage en cours");
        AlertDialog alert = builder.create();
        alert.show();
        try {
            File f = new File(location);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));

            try {
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {
                    String path = location + File.separator + ze.getName();

                    if (ze.isDirectory()) {
                        File unzipFile = new File(path);
                        if (!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    } else {
                        System.out.println("The location is : "+ location);
                        System.out.println("The File separator  is : "+ File.separator);
                        System.out.println("The ze name  is : "+ ze.getName());
                        System.out.println("The paht 1 : "+ location + File.separator + ze.getName());
                        System.out.println("The paht 1 : "+ path);
                        FileOutputStream fout = new FileOutputStream(path, false);

                        try {
                            for (int c = zin.read(); c != -1; c = zin.read()) {
                                fout.write(c);
                            }
                            zin.closeEntry();
                        } finally {
                            fout.close();
                        }
                    }
                }
            } finally {
                zin.close();
                alert.dismiss();
                Toast.makeText(SplashActivity.this, "Dezippage fini ..", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Unzip exception", e);
        }
    }




    public void unzipFile(String fileZip, String destionation) throws IOException {
        byte[] buffer = new byte[1024];
        File destDir = new File(destionation);
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Voir si l'utilisateur est dejà connecté ou pas pour savoir si on doit le redirigér vers le login ou dans l'application  en même temps
        // Ce listerner est responsable certainement du jeu d'activité entre le login et le RegisterUerActivity

        /*mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        if (mCurrentUser != null) {

            User user = SharedPrefManager.getmInstance(this).getUser();
            System.out.println("User is :"+ user.toString());
            if (user.getLangue() == null){
                startActivity(new Intent(SplashActivity.this, RegisterUserFormActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }else {
                startActivity(new Intent(SplashActivity.this, ChoixLangue.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        }
        else {
            Thread timer = new Thread(){

                public void  run(){
                    try{
                        sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }
            };
            timer.start();
        }*/

    /*mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    startActivity(new Intent(SplashActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    */
    /*
        */
    /*if (SharedPrefManager.getmInstance(SplashActivity.this).getUser().getId().equals("NO_FOUND")){

                        //Toast.makeText(SplashActivity.this,"TO LOGIN", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    }else {
                        //dToast.makeText(SplashActivity.this,"CONNECTÉÉÉÉÉ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }*/
    /*
        */
    /*

                }
                else {

                    //Toast.makeText(SplashActivity.this,"NON CONNECTÉÉÉÉÉ", Toast.LENGTH_LONG).show();

                    Thread timer = new Thread(){

                        public void  run(){
                            try{
                                sleep(3000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            finally {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }
                        }
                    };
                    timer.start();
                }
            }
        });*/

    }


}


