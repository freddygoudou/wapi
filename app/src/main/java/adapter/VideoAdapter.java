package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.videoplayer.VideoPlayerActivity;
import entity.Video;
import entity.Videooo;

public class VideoAdapter extends RecyclerView.Adapter <VideoAdapter.VideoViewHolder>{

    private Context mContext;
    private ArrayList<Video> mData;
    private boolean connexionState;

    public VideoAdapter(Context mContext, ArrayList<Video> mData, boolean connexionState) {
        this.mContext = mContext;
        this.mData = mData;
        this.connexionState = connexionState;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, viewGroup, false);
        return new VideoAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, final int position) {

        holder.ll_one_video.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        holder.tv_title.setText(mData.get(position).getName());
        holder.tv_description.setText(mData.get(position).getDescription());

        if (this.connexionState){
            Picasso.get().load(mData.get(position).getCaptionPath()).into(holder.iv_video);
        }else {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile(),ajustFilePath(mData.get(position).getCaptionPath()));
            Picasso.get().load(file).into(holder.iv_video);
        }

        holder.ll_one_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, mData.get(position).getName()+"/"+mData.get(position).getDescription()+"/"+mData.get(position).getCaptionPath()+"/"+mData.get(position).getVideosPaths(), Toast.LENGTH_LONG).show();
                mContext.startActivity(new Intent(mContext, VideoPlayerActivity.class)
                        .putExtra("video", mData.get(position))
                        .putExtra("connexionState", connexionState));
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_description;
        ImageView iv_video;
        LinearLayout ll_one_video;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            iv_video = itemView.findViewById(R.id.iv_video);
            ll_one_video = itemView.findViewById(R.id.ll_one_video);
        }
    }

    private String firstVideo(String concatedLinks){
        StringTokenizer stringTokenizer = new StringTokenizer(concatedLinks, ";");
        return stringTokenizer.nextToken();
    }

    public String ajustFilePath(String path){
        return path.substring(8); //Download
    }


}
