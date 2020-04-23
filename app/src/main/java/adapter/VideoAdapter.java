package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import de.hdodenhof.circleimageview.CircleImageView;
import entity.Article;
import entity.Video;

public class VideoAdapter extends RecyclerView.Adapter <VideoAdapter.VideoViewHolder>{

    private Context mContext;
    private ArrayList<Video> mData;
    FragmentActivity fragmentActivity;

    public VideoAdapter(FragmentActivity fragmentActivity, Context mContext, ArrayList<Video> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.fragmentActivity = fragmentActivity;
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
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_description.setText(mData.get(position).getDescription());

        holder.ll_one_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, mData.get(position).getTitle()+"/"+mData.get(position).getDescription()+"/"+mData.get(position).getImage()+"/"+mData.get(position).getVideo(), Toast.LENGTH_LONG).show();
                //Navigation.createNavigateOnClickListener(R.id.action_navigation_formation_to_navigation_suivre_video);
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


}
