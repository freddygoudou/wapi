package adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.collection.LLRBNode;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.DetailsFormation;
import entity.Caroussel;
import entity.Document;

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
            File file = new File("/storage/self/primary/"+firstImage(mData.get(position).getImagesPaths())); //firstImage(mData.get(position).getImagesPaths())
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
                    .putExtra("caroussel", mData.get(position)));
                //Toast.makeText(mContext, mData.get(position).getName()+"/"+mData.get(position).getDescription()+"/"+mData.get(position).getImagesPaths(), Toast.LENGTH_LONG).show();
            }
        });
        
    }

    private String firstImage(String concatedLinks){
        StringTokenizer stringTokenizer = new StringTokenizer(concatedLinks, ";");
        return stringTokenizer.nextToken();
    }

    @Override
    public int getItemCount() {
        return mData.size();
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


}
