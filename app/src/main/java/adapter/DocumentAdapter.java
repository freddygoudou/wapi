package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.formation.DetailsFormation;
import entity.Document;

public class DocumentAdapter extends RecyclerView.Adapter <DocumentAdapter.DocumentViewHolder>{

    private Context mContext;
    private ArrayList<Document> mData;

    View view;
    public DocumentAdapter(Context mContext, ArrayList<Document> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_description.setText(mData.get(position).getDescription());
        holder.btn_telecharger_ouvrir.setImageResource(R.drawable.ic_file_download_black_24dp);
        holder.iv_produit.setImageResource(mData.get(position).getImage());
        holder.btn_telecharger_ouvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Téléchargement
                holder.btn_telecharger_ouvrir.setImageResource(R.drawable.ic_insert_drive_file_white_24dp);
            }
        });

        holder.ll_one_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mContext.startActivity(new Intent(mContext, DetailsFormation.class)
                    .putExtra("videoTitle", mData.get(position).getTitle())
                    .putExtra("videoDescription", mData.get(position).getDescription()));
                Toast.makeText(mContext, mData.get(position).getTitle()+"/"+mData.get(position).getDescription()+"/"+mData.get(position).getImage(), Toast.LENGTH_LONG).show();
            }
        });
        
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
