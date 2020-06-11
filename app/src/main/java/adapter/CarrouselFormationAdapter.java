package adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entity.CarrouselFormation;
import entity.ImageCarrousel;

public class CarrouselFormationAdapter extends RecyclerView.Adapter <CarrouselFormationAdapter.CarrouselFormationViewHolder>{

    private Context mContext;
    private List<ImageCarrousel> mData;
    View view;

    public CarrouselFormationAdapter(Context mContext, List<ImageCarrousel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public CarrouselFormationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_carrousel_formation, viewGroup, false);
        return new CarrouselFormationAdapter.CarrouselFormationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarrouselFormationViewHolder holder, final int position) {
        Picasso.get().load(Uri.parse(mData.get(position).getUrl())).into(holder.iv_formation_image);
        //holder.iv_formation_image.setImageResource(mData.get(position).getImage());
        System.out.println("Image Data : "+mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CarrouselFormationViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_formation_image;
        public CarrouselFormationViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_formation_image = itemView.findViewById(R.id.iv_formation_image);
        }
    }
}
