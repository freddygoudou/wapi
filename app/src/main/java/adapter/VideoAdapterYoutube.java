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

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.videoplayer.VideoPlayerActivity;
import entity.VideoYoutube;
import entityBackend.Video;

public class VideoAdapterYoutube extends RecyclerView.Adapter <VideoAdapterYoutube.VideoViewHolder>{

    private Context mContext;
    private ArrayList<VideoYoutube> mData;
    private boolean connexionState;

    public VideoAdapterYoutube(Context mContext, ArrayList<VideoYoutube> mData, boolean connexionState) {
        this.mContext = mContext;
        this.mData = mData;
        this.connexionState = connexionState;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_youtube, viewGroup, false);
        return new VideoAdapterYoutube.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, final int position) {

        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(mData.get(position).getVideoId(), 0);
                //youTubePlayer.play();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        YouTubePlayerView youTubePlayerView;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            youTubePlayerView = itemView.findViewById(R.id.youtube_player_view);
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
