package com.meka.findajob.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.SignInButton;
import com.meka.findajob.Models.BasvurdugumIlanlarListeleModel;
import com.meka.findajob.Models.BasvuruListeleModel;
import com.meka.findajob.Models.BasvuruOnayRedModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasvurdugumIlanlarAdapter extends RecyclerView.Adapter<BasvurdugumIlanlarAdapter.ViewHolder> {

    List<BasvurdugumIlanlarListeleModel> list;
    Context context;
    Activity activity;


    public BasvurdugumIlanlarAdapter(List<BasvurdugumIlanlarListeleModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.basvurdugum_ilanlar_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView basvurdugumIlanBaslikText,basvurdugumIlanAciklamaText,basvurdugumIlanMailText,onayRedText;
        LinearLayout basvurdugumIlanlarAnaLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            basvurdugumIlanBaslikText=itemView.findViewById(R.id.basvurdugumIlanBaslikText);
            basvurdugumIlanAciklamaText=itemView.findViewById(R.id.basvurdugumIlanAciklamaText);
            basvurdugumIlanMailText=itemView.findViewById(R.id.basvurdugumIlanMailText);
            basvurdugumIlanlarAnaLayout=itemView.findViewById(R.id.basvurdugumIlanlarAnaLayout);
            onayRedText=itemView.findViewById(R.id.onayRedText);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.basvurdugumIlanBaslikText.setText(list.get(position).getBaslik().toString());
        holder.basvurdugumIlanAciklamaText.setText(list.get(position).getAciklama().toString());
        holder.basvurdugumIlanMailText.setText(list.get(position).getMailadres().toString());
        holder.basvurdugumIlanlarAnaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*
                ChangeFragments changeFragment=new ChangeFragments(context);
                changeFragment.changeWithParemeters(new IlanDetayFragment(),list.get(position).getId(),
                        list.get(position).getKid().toString());
                         */
            }
        });
        // ona red text
        String onayRedDurum=list.get(position).getDurum();
        if(Integer.parseInt(onayRedDurum) == 0){
            holder.onayRedText.setText("No Answer..");
            holder.onayRedText.setTextColor(Color.parseColor("#ffc400"));
        }
        else if(Integer.parseInt(onayRedDurum) == 1){
            holder.onayRedText.setText("                Approved ✓\n Please contact by mail..");
            holder.onayRedText.setTextColor(Color.parseColor("#64dd17"));
        }
        else{
            holder.onayRedText.setText("Denied!!");
            holder.onayRedText.setTextColor(Color.parseColor("#BC1515"));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    // listeden basvuruyu kaldırma
    public void deleteToList(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}
