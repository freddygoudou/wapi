package examen;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entity.ImageCarrousel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {

    public ArrayList<ImageCarrousel> getData() {
        return data;
    }

    private ArrayList<ImageCarrousel> originalData;
    private ArrayList<ImageCarrousel> data;
    private Context context;
    private MyInterface myInterface;

    public void setMyInterface(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private ImageView imageView;
        View rowView;

        public MyViewHolder(View itemView) {
            super(itemView);

            rowView = itemView;
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public RecyclerViewAdapter(ArrayList<ImageCarrousel> data, Context context) {
        this.data = data;
        this.originalData= new ArrayList<>(data);

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Picasso.get().load(Environment.getExternalStoragePublicDirectory(data.get(position).getBaseUri()))
                .placeholder(R.drawable.wapi)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        originalData= new ArrayList<>(data);
        
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);

        if(myInterface!=null){
            myInterface.onUpdate(originalData, data);
        }
    }

    @Override
    public void onRowSelected(MyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(MyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
    }

    public interface MyInterface{
        public void onUpdate(ArrayList<ImageCarrousel> originalData, ArrayList<ImageCarrousel> newData);
    }
}



