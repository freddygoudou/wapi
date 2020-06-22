package adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entity.ImageCarrousel;

public class CarrouselFormationAdapter extends RecyclerView.Adapter <CarrouselFormationAdapter.CarrouselFormationViewHolder>{

    private Context mContext;
    private ArrayList<ImageCarrousel> mData;
    boolean connexionState;
    View view;

    public CarrouselFormationAdapter(Context mContext, ArrayList<ImageCarrousel> mData, boolean connexionState) {
        this.mContext = mContext;
        this.mData = mData;
        this.connexionState = connexionState;
    }


    @NonNull
    @Override
    public CarrouselFormationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_carrousel_formation, viewGroup, false);
        return new CarrouselFormationAdapter.CarrouselFormationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarrouselFormationViewHolder holder, final int position) {

        if (this.connexionState) {
            Picasso.get().load(Uri.parse(mData.get(position).getUrl())).into(holder.iv_formation_image);
        }else {
            File file = new File(String.valueOf(Uri.fromFile(Environment.getExternalStoragePublicDirectory(mData.get(position).getUrl()))));
            Picasso.get().load(file).into(holder.iv_formation_image);
        }

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
