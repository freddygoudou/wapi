package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entityBackend.SaisonCulture;

public class SaisonAdapter extends RecyclerView.Adapter <SaisonAdapter.ChampsLocationViewHolder>{

    private Context mContext;
    private List<SaisonCulture> mData;
    View view;
    public SaisonAdapter(Context mContext, List<SaisonCulture> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public SaisonAdapter.ChampsLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_saison, parent, false);
        return new SaisonAdapter.ChampsLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaisonAdapter.ChampsLocationViewHolder holder, int position) {
        holder.tv_nom_culture.setText(mContext.getResources().getString(R.string.culture_of)+mData.get(position).getNomCulture());
        holder.tv_date_semie.setText(mContext.getResources().getString(R.string.dateSemie)+mData.get(position).getDateSemie());
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(position);
                SaisonAdapter.this.notifyDataSetChanged();
                if (mContext instanceof NewChampsActivity) {
                    ((NewChampsActivity)mContext).updateActivityUI(mData.size());
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChampsLocationViewHolder  extends RecyclerView.ViewHolder{
        TextView tv_nom_culture, tv_date_semie;

        public ChampsLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nom_culture = itemView.findViewById(R.id.tv_nom_culture);
            tv_date_semie = itemView.findViewById(R.id.tv_date_semie);
        }
    }

    public interface UiInterface{
        void updateActivityUI(int size);
    }
}