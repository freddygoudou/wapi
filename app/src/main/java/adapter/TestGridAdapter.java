package adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entity.ImageCarrousel;

public class TestGridAdapter extends RecyclerView.Adapter <TestGridAdapter.CarrouselFormationViewHolder>{

    private Context mContext;
    private ArrayList<Integer> mData;
    View view;
    boolean connexionState;
    int diapo;
    ArrayList<Integer> examList = new ArrayList<>();
    public static final  int DIAPO_MAX_INDEX = 27;

    public TestGridAdapter(Context mContext, ArrayList<Integer> mData, boolean connexioState, int diapo) {
        this.mContext = mContext;
        this.mData = mData;
        this.connexionState = connexioState;
        this.diapo = diapo;
    }

    public int getDiapo() {
        return diapo;
    }

    public void setDiapo(int diapo) {
        this.diapo = diapo;
    }

    @NonNull
    @Override
    public CarrouselFormationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_carrousel_formation, viewGroup, false);
        return new TestGridAdapter.CarrouselFormationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarrouselFormationViewHolder holder, final int position) {
        holder.iv_formation_image.setImageResource(mData.get(position));
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
