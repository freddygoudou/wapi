package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.DetailsFormation;
import entityBackend.Caroussel;

public class DocumentAdapter extends RecyclerView.Adapter <DocumentAdapter.DocumentViewHolder>{

    private Context mContext;
    private ArrayList<Caroussel> mData;
    private boolean connexionState;
    View view;
    public DocumentAdapter(Context mContext, ArrayList<Caroussel> mData, boolean connexionState) {
        this.mContext = mContext;
        this.mData = mData;
        this.connexionState = connexionState;
    }


    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_document, viewGroup, false);
        return new DocumentAdapter.DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DocumentViewHolder holder, final int position) {

        holder.ll_one_document.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        holder.tv_title.setText(mData.get(position).getName());
        holder.tv_description.setText(mData.get(position).getDescription());
        holder.btn_telecharger_ouvrir.setImageResource(R.drawable.ic_file_download_black_24dp);

        if (this.connexionState){
            Picasso.get().load(firstImage(mData.get(position).getImagesPaths())).into(holder.iv_produit);
        }else {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile(),ajustFilePath(firstImage(mData.get(position).getImagesPaths())));
            Picasso.get().load(file).into(holder.iv_produit);
        }

        holder.btn_telecharger_ouvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Téléchargement
                holder.btn_telecharger_ouvrir.setImageResource(R.drawable.ic_insert_drive_file_white_24dp);
                //holder.btn_telecharger_ouvrir.setBackgroundColor(R.color.colorRed);
            }
        });
        holder.ll_one_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mContext.startActivity(new Intent(mContext, DetailsFormation.class)
                    .putExtra("caroussel", mData.get(position))
                        .putExtra("connexionState", connexionState));
                //Toast.makeText(mContext, mData.get(position).getName()+"/"+mData.get(position).getDescription()+"/"+mData.get(position).getImagesPaths(), Toast.LENGTH_LONG).show();
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private String firstImage(String concatedLinks){
        StringTokenizer stringTokenizer = new StringTokenizer(concatedLinks, ";");
        return stringTokenizer.nextToken();
    }

    public String ajustFilePath(String path){
        return path.substring(8); //Download
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_description;
        ImageView iv_produit;
        ImageButton btn_telecharger_ouvrir;
        LinearLayout ll_one_document;
        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            btn_telecharger_ouvrir = itemView.findViewById(R.id.btn_telecharger_ouvrir);
            iv_produit = itemView.findViewById(R.id.iv_produit);
            ll_one_document = itemView.findViewById(R.id.ll_one_document);
        }
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
        /*if (file.isFile())*//*{
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



}
