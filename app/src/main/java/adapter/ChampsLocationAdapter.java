package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.ui.NewChampsActivity;
import bj.app.wapi.R;
import entityBackend.ChampsLocation;

public class ChampsLocationAdapter extends RecyclerView.Adapter <ChampsLocationAdapter.ChampsLocationViewHolder>{

    private Context mContext;
    private ArrayList<ChampsLocation> mData;
    View view;
    public ChampsLocationAdapter(Context mContext, ArrayList<ChampsLocation> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ChampsLocationAdapter.ChampsLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_champs_location, parent, false);
        return new ChampsLocationAdapter.ChampsLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChampsLocationAdapter.ChampsLocationViewHolder holder, int position) {
        holder.tv_longitude.setText("Longitude : "+mData.get(position).getLongitutde().toString());
        holder.tv_latitude.setText("Latitude : "+mData.get(position).getLatitude().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(position);
                ChampsLocationAdapter.this.notifyDataSetChanged();
                if (mContext instanceof NewChampsActivity) {
                    ((NewChampsActivity)mContext).updateActivityUI(mData.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChampsLocationViewHolder  extends RecyclerView.ViewHolder{
        TextView tv_longitude, tv_latitude;

        public ChampsLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_longitude = itemView.findViewById(R.id.tv_longitute);
            tv_latitude = itemView.findViewById(R.id.tv_latitude);
        }
    }

    public interface UiInterface{
        void updateActivityUI(int size);
    }
}