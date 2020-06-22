package adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import net.lingala.zip4j.model.ZipModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import bj.app.wapi.R;
import bj.app.wapi.ui.ThreeImagesMenu;
import bj.app.wapi.ui.WapiApplication;
import bj.app.wapi.ui.carrousel_formation.FormationCarrousel;
import bj.app.wapi.ui.splash.SplashActivity;
import database.DatabaseHelper;
import entity.CarrouselDownloded;
import entity.FileAndExtention;
import entityBackend.Carrousel;
import entityBackend.CarrouselFormation;
import ir.mahdi.mzip.zip.ZipArchive;
import storage.SharedPrefManager;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DocumentAdapter extends RecyclerView.Adapter <DocumentAdapter.DocumentViewHolder>{

    private Context mContext;
    private List<Carrousel> mData;
    private boolean connexionState;
    Long downloadId;
    View view;
    BroadcastReceiver onStart, onComplete;
    String langue, formationName;
    DatabaseHelper databaseHelper;
    CarrouselDownloded carrouselDownloded;
    ArrayList<CarrouselDownloded> carrouselDownlodeds;

    public DocumentAdapter(Context mcntext, List<Carrousel> mData, boolean connexionState) {
        this.mContext = mcntext;
        this.databaseHelper = new DatabaseHelper(mContext);
        this.mData = mData;
        this.connexionState = connexionState;
        this.langue = SharedPrefManager.getmInstance(mContext).getUser().getLangue();
        this.carrouselDownlodeds = databaseHelper.getAllCarousselDownloaded();
        this.formationName = "";

        //MediaPlayer player = MediaPlayer.create(mContext, Uri.parse())
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_document, viewGroup, false);
        return new DocumentAdapter.DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DocumentViewHolder holder, final int position) {

        //holder.ll_one_document.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        holder.tv_title.setText(mData.get(position).getName());
        holder.tv_description.setText(mData.get(position).getDescription());

        if (this.connexionState){
            Picasso.get().load(mData.get(position).getCarrouselFormations().get(0).getImages().get(0).getUrl()).placeholder(R.drawable.mung_bean).into(holder.iv_produit);
        }
        /*else {
            File file = new File(String.valueOf(Uri.fromFile(Environment.getExternalStoragePublicDirectory(mData.get(position).getCarrouselFormations().get(0).getImages().get(0).getBaseUri()))));
            Picasso.get().load(file).into(holder.iv_produit);
        }*/

        if (checkIfDownloaded(mData.get(position),carrouselDownlodeds)){
            holder.btn_download_carrousel.setEnabled(false);
            holder.btn_download_carrousel.setActivated(false);
            holder.btn_download_carrousel.setVisibility(View.INVISIBLE);
            holder.tv_download_completed.setText(R.string.downloaded);
        }else {
            holder.btn_download_carrousel.setEnabled(true);
            holder.btn_download_carrousel.setActivated(true);
            holder.btn_download_carrousel.setVisibility(View.VISIBLE);
        }

        holder.ll_one_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(mContext,  FormationCarrousel.class);
                Bundle bundle = new Bundle();
                intent.putExtra("my_id", mData.get(position).get_id());
               // bundle.putParcelab("connexionState",connexionState);
                intent.putExtras(bundle);
               // intent.putExtra("connexionState",connexionState);

               //  mData.get(position).getCarrouselFormations()
               // JSONObject jsonObject = (JSONObject) new JsonParser().parse(your json string);
                if(connexionState){
               mContext. startActivity(new Intent(mContext, FormationCarrousel.class)
                    .putExtra("carrouselFormations",mData.get(position).getCarrouselFormations())
                        .putExtra("connexionState",connexionState));
                }else {
                    mContext.startActivity(intent);
                }


                //System.out.println("carrouselFormations is now :"+mData.get(position).getCarrouselFormations().toString());
//               mContext. startActivity(new Intent(mContext, FormationCarrousel.class)
//                    .putExtra("carrouselFormations",mData.get(position).getCarrouselFormations())
//                        .putExtra("connexionState",connexionState));
                //WapiApplication app = (WapiApplication) mContext.getApplicationContext();
                //app.setCarrouselFormations(mData.get(position).getCarrouselFormations());
                /*WapiApplication wapiApplication = new WapiApplication(mData.get(position).getCarrouselFormations());
                System.out.println("App data is : "+wapiApplication.getCarrouselFormations().toString());
                //mContext.getApplicationContext()
                mContext.startActivity(new Intent(mContext, FormationCarrousel.class)
                        .putExtra("connexionState",connexionState));*/
            }
        });
        holder.btn_download_carrousel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog downloadSart = new ProgressDialog(mContext);
                downloadSart.setMessage(mContext.getString(R.string.please_wait_for_download_and_unziping));
                downloadSart.setCanceledOnTouchOutside(false);
                downloadSart.show();
                //SharedPrefManager.getmInstance(mContext).saveFormationInDownloadingName(mData.get(position).getName());
                //System.out.println("Value of formationName : "+formationName);
                //System.out.println("Value of langue : "+langue);
                holder.btn_download_carrousel.setVisibility(View.INVISIBLE);
                holder.btn_download_carrousel.setActivated(false);
                holder.btn_download_carrousel.setEnabled(false);
                holder.tv_download_completed.setText(R.string.downloading);
                onComplete=new BroadcastReceiver() {
                    public void onReceive(Context ctxt, Intent intent) {
                //File file = new File(Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Caroussel/french/Mung Bean/Mung Bean.zip");
                //Toast.makeText(mContext, "The file name is  : "+file.getName(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(mContext, "DOWNLOAD FINISH ....", Toast.LENGTH_SHORT).show();*//*
                        ProgressDialog dezippageStart = new ProgressDialog(mContext);
                        dezippageStart.setMessage("Dézippage en cours ...");
                        dezippageStart.setCanceledOnTouchOutside(false);
                        holder.btn_download_carrousel.setVisibility(View.INVISIBLE);
                        holder.btn_download_carrousel.setActivated(false);
                        holder.btn_download_carrousel.setEnabled(false);
                        holder.tv_download_completed.setText(R.string.downloaded);
                        //Toast.makeText(mContext, "Formation downloaded is : "+mData.get(position).getName(), Toast.LENGTH_LONG).show();

                        String folder = createCarousselFolder(SharedPrefManager.getmInstance(mContext).getUser().getLangue());
                        ZipArchive zipArchive = new ZipArchive();
                        ZipArchive.unzip(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/"+mData.get(position).getName()+".zip",Environment.getExternalStoragePublicDirectory(folder).getAbsolutePath(),"");

                        //SAVE A LIST OF
                        databaseHelper = new DatabaseHelper(mContext);
                        databaseHelper.saveCaroussel(mData.get(position));
                        databaseHelper.saveCarousselDownloaded(new CarrouselDownloded(1L,mData.get(position).getName(),mData.get(position).getSubname(), langue));
                        downloadSart.hide();
                    }
                };
                mContext.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                startDownloadingCaroussel(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private boolean checkIfDownloaded(Carrousel carrousel, ArrayList<CarrouselDownloded> list){
        if (list.isEmpty() || list ==null)
            return false;
        else {
            for (int i=0; i< list.size(); i++){
                if (list.get(i).getLangue().equals(carrousel.getLangue()) && list.get(i).getName().equals(carrousel.getName()) && list.get(i).getSubname().equals(carrousel.getSubname()))
                    return true;
            }
        }
        return false;
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_description, tv_download_completed;
        ImageView iv_produit;
        LinearLayout ll_one_document;
        Button btn_download_carrousel;
        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            iv_produit = itemView.findViewById(R.id.iv_produit);
            ll_one_document = itemView.findViewById(R.id.ll_one_document);
            btn_download_carrousel = itemView.findViewById(R.id.btn_download_carrousel);
            tv_download_completed = itemView.findViewById(R.id.tv_download_completed);
        }
    }

    private void startDownloadingCaroussel(Carrousel carrousel) {

        //String folder = createCarousselFolder(SharedPrefManager.getmInstance(mContext).getUser().getLangue());

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(carrousel.getUrl()));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(carrousel.getName());
        request.setDescription(carrousel.getDescription());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, carrousel.getName() +".zip");
        DownloadManager manager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
        downloadId = manager.enqueue(request);
    }

    public String createCarousselFolder(String langue) {
        String path = Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Carrousel";
        //String path = Environment.DIRECTORY_DOWNLOADS + "/Wapi/Formation/Caroussel/"+langue+"/"+carrousel.getName();
        //String path = Environment.DIRECTORY_DOWNLOADS +"/"+carrousel.getName();
        File dir = new File(path);
        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated) {
            Log.d("Folder", "Already Created");
        }
        return path;
    }


    public ArrayList<String> getFilesPathBySubFiles(String path){
        ArrayList<String> list = new ArrayList<>();
        String newPath = path.substring(9);
        StringTokenizer stringTokenizer = new StringTokenizer(newPath, "/");
        while (stringTokenizer.hasMoreTokens()){
            list.add(stringTokenizer.nextToken());
        }
        list.remove(list.size()-1);
        return list;
    }

    public String getDownloadedFile(String fileName){
        File fileToReturn = null;
        File[] files = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).listFiles();
        for (File file : files){
            if (file.isDirectory()){
                files = file.listFiles();
            }else {
                if (file.getName().equals(fileName)){
                    fileToReturn = file;
                }
            }
        }
        return fileToReturn.getName();
    }

    public File getDownloadedFile(ArrayList<String> list, File files){

        File fileToReturn = null;
        //File files = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile();

        for (File file : files.listFiles()){
            for (int i=0; i<list.size(); i++){
                if (file.getName().equals(list.get(i))){
                    /*System.out.println("YOUR REAL FOLDER IS :"+file.getName());
                    for(File f: file.listFiles()){
                        System.out.println("CHILDREN:"+f.getName());
                    }*/
                    if(file.listFiles() != null)
                        System.out.println("YOUR FOLDER IS :"+file.getName());
                    System.out.println("YOUR FOLDER CHILDREN ARE :"+file.listFiles());
                    getDownloadedFile(list,file);
                }else{
                    System.out.println("YOUR FILE IS :"+file.getName());
                        /*for(File f: file.listFiles()){
                            System.out.println("CHILDREN:"+f.getName());
                        }*/
                    fileToReturn =file;
                }

            }
        }

        /*OUTER_LOOP:
        for (int i=0;i<list.size() ;i++ ) {

            for (File file : files) {
                if (file.getName().equals(list.get(i))) {

                    System.out.println("BEFORE DIRECTORY :"+file.getName());
                    if(file.isDirectory()) {
                        System.out.println("YOUR DIRECTORY :"+file.getName());
                        */
        /*System.out.println("THE DIRECTORY IS :"+file.getName());
                        for (File filee : file.listFiles()) {
                            System.out.println("AND HIS FILES IS :"+filee.getName());
                            if (filee.isFile()){
                                System.out.println("AND HIS FILES IS :"+ filee.getName()+" is "+filee.isFile()+" with child "+filee.listFiles());
                                fileToReturn = file;
                                break OUTER_LOOP;
                            }
                        }*/
        /*
                        getDownloadedFile(list, file.listFiles());
                    }else */
        /*if (file.isFile())*/
        /*{
                        System.out.println("YOUR FILE IS :"+file.getName());
                        */
        /*fileToReturn = file;
                        break OUTER_LOOP;*/
        /*
                    }
                }else {
                    System.out.println("AFTER DIRECTORY :"+file.getName());
                }
            }
        }*/
        return fileToReturn;
    }

    public String ajustFilePath(String path){
        return path.substring(8); //Download
    }


}
