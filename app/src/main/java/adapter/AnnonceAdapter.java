package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import de.hdodenhof.circleimageview.CircleImageView;
import entity.Article;

public class AnnonceAdapter extends RecyclerView.Adapter <AnnonceAdapter.HomeViewHolder>{

    private Context mContext;
    private ArrayList<Article> mData;

    View view;
    public AnnonceAdapter(Context mContext, ArrayList<Article> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(mContext).inflate(R.layout.item_annonce, viewGroup, false);
        return new AnnonceAdapter.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {

        holder.rl_one_produit.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_qte.setText(mData.get(position).getQuantite());
        holder.tv_date.setText(mData.get(position).getDate());

        holder.rl_one_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mData.get(position).getName()+"/"+mData.get(position).getQuantite()+"/"+mData.get(position).getDate(), Toast.LENGTH_LONG).show();
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_qte, tv_date;
        CircleImageView civ_photo;
        RelativeLayout rl_one_produit;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_qte = itemView.findViewById(R.id.tv_qte);
            tv_date = itemView.findViewById(R.id.tv_date);
            civ_photo = itemView.findViewById(R.id.iv_operation_photo);
            rl_one_produit = itemView.findViewById(R.id.rl_one_produit);
        }
    }


}
