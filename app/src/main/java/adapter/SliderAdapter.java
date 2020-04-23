package adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import bj.app.wapi.R;
import entity.SlideItem;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private ArrayList<SlideItem> slideItemArrayList;
    private ViewPager2 viewPager2;
    private Context mContext;

    public SliderAdapter(Context context, ArrayList<SlideItem> slideItemArrayList, ViewPager2 viewPager2) {
        this.slideItemArrayList = slideItemArrayList;
        this.viewPager2 = viewPager2;
        this.mContext = context;
    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slide, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(slideItemArrayList.get(position));
        try{
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(slideItemArrayList.get(position).getAudioUrl());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
            mediaPlayer.start();
            if(position == slideItemArrayList.size() - 2){
                viewPager2.post(runnable);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return slideItemArrayList.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView roundedImageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.imageSlide);

        }

        void setImage(SlideItem slideItem){
            //Si on a que le lin ici on utilidera Picasso
            roundedImageView.setImageResource(slideItem.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            slideItemArrayList.addAll(slideItemArrayList);
            notifyDataSetChanged();
        }
    };
}
