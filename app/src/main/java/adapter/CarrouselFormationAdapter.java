package adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import entity.ImageCarrousel;

public class CarrouselFormationAdapter extends RecyclerView.Adapter <CarrouselFormationAdapter.CarrouselFormationViewHolder>{

    private Context mContext;
    private ArrayList<ImageCarrousel> mData;
    View view;
    boolean connexionState;
    int diapo;
    ArrayList<Integer> examList = new ArrayList<>();
    public static final  int DIAPO_MAX_INDEX = 27;
    final int sdk = android.os.Build.VERSION.SDK_INT;

    public CarrouselFormationAdapter(Context mContext, ArrayList<ImageCarrousel> mData, boolean connexioState, int diapo) {
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
        return new CarrouselFormationAdapter.CarrouselFormationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarrouselFormationViewHolder holder, final int position) {

        if (diapo == DIAPO_MAX_INDEX){
            //Toast.makeText(mContext, "in 1", Toast.LENGTH_LONG).show();
            //holder.iv_formation_image.requestLayout();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,200
            );
            holder.iv_formation_image.requestLayout();
            holder.iv_formation_image.setLayoutParams(params);

            //holder.iv_formation_image.setBackgroundResource(R.drawable.background_iv_exam);
        }else {
            //Toast.makeText(mContext, "in the "+diapo, Toast.LENGTH_LONG).show();
            //holder.iv_formation_image.requestLayout();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,450
            );
            holder.iv_formation_image.requestLayout();
            holder.iv_formation_image.setLayoutParams(params);
            //holder.iv_formation_image.setBackground(null);
        }


        if (connexionState){
            Picasso.get().load(Uri.parse(mData.get(position).getUrl())).into(holder.iv_formation_image);
        }else {
            File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(mData.get(position).getBaseUri())));
            Picasso.get().load(file).into(holder.iv_formation_image);
        }

        holder.iv_formation_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (diapo == DIAPO_MAX_INDEX){

                    //holder.iv_formation_image.setBackground(ContextCompat.getDrawable(mContext, R.drawable.background_iv_exam));

                    int check = getImageIndex(examList, position);
                    if (check != -1){
                        examList.remove(check);
                        System.out.println("Exam list from remove is : "+ examList.toString());
                        System.out.println("Valid exam is : "+ validExam(examList));
                        //remove background color
                    }else {
                        examList.add(position);
                        System.out.println("Exam list from add is : "+ examList.toString());
                        System.out.println("Valid exam is : "+ validExam(examList));
                        //May be color in background
                    }
                }else{
                    //Toast.makeText(mContext, "Ce n'est pas encore l'heure de l\'examen", Toast.LENGTH_LONG).show();
                }
            }
        });

        //holder.iv_formation_image.setImageResource(mData.get(position).getImage());
        System.out.println("Image Data : "+mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CarrouselFormationViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_formation_image;
        LinearLayout ll_formation_image;
        public CarrouselFormationViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_formation_image = itemView.findViewById(R.id.iv_formation_image);
            ll_formation_image = itemView.findViewById(R.id.ll_formation_image);
        }
    }

    public int getImageIndex(ArrayList<Integer> list, int position){
        for (int i=0;i<list.size();i++){
            if (list.get(i) == position){
                return i;
            }
        }
        return -1;
    }

    public boolean validExam(ArrayList<Integer> examList){
        for (int i=0; i<examList.size(); i++){
            if ((i<examList.size()-1)){
                if (examList.get(i) >= examList.get(i+1)){
                    return false;
                }
            }
        }
        return true;
    }
}
