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
import bj.app.wapi.NewChampsActivity;
import bj.app.wapi.R;
import entityBackend.ContactEmployee;
import entityBackend.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter <EmployeeAdapter.ChampsLocationViewHolder>{

    private Context mContext;
    private List<Employee> mData;
    View view;
    public EmployeeAdapter(Context mContext, List<Employee> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ChampsLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_employee, parent, false);
        return new EmployeeAdapter.ChampsLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ChampsLocationViewHolder holder, int position) {
        String allContact = getAllEmployeeContacts(mData.get(position).getContactEmployees());
        holder.tv_nom.setText("Nom : "+mData.get(position).getNom());
        holder.tv_contact.setText("Téléphone : "+allContact);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(position);
                EmployeeAdapter.this.notifyDataSetChanged();
                if (mContext instanceof NewChampsActivity) {
                    ((NewChampsActivity)mContext).updateActivityUI(mData.size());
                }
            }
        });
    }

    private String getAllEmployeeContacts(List<ContactEmployee> contactEmployees) {
        String allContact = "";
        if (contactEmployees != null){
            for (int i=0; i<contactEmployees.size(); i++){
                allContact = allContact+" , "+contactEmployees.get(i);
            }
        }
        return allContact;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChampsLocationViewHolder  extends RecyclerView.ViewHolder{
        TextView tv_nom, tv_contact;

        public ChampsLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nom = itemView.findViewById(R.id.tv_name);
            tv_contact = itemView.findViewById(R.id.tv_contact);
        }
    }

    public interface UiInterface{
        void updateActivityUI(int size);
    }
}