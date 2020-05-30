package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.NewChampsActivity;
import bj.app.wapi.R;
import entityBackend.SaisonCulture;

public class SaisonAdapter extends RecyclerView.Adapter <SaisonAdapter.ChampsLocationViewHolder>{

    private Context mContext;
    private ArrayList<SaisonCulture> mData;
    View view;
    public SaisonAdapter(Context mContext, ArrayList<SaisonCulture> mData) {
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
        /*holder.tv_nom_culture.setText("Culture de  : "+mData.get(position).getNomCulture());
        holder.tv_annee_culture.setText("Année : "+mData.get(position).getAnnéeCulture());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_nom_culture, tv_annee_culture;

        public ChampsLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nom_culture = itemView.findViewById(R.id.tv_nom_culture);
            tv_annee_culture = itemView.findViewById(R.id.tv_annne_culture);
        }
    }

    public interface UiInterface{
        void updateActivityUI(int size);
    }
}