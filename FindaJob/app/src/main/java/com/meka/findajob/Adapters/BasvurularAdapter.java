package com.meka.findajob.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Fragments.IlanDetayFragment;
import com.meka.findajob.Models.BasvuruListeleModel;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.R;
import com.meka.findajob.Utils.ChangeFragments;
import com.meka.findajob.ViewHolder.IlanViewHolder;

import java.util.List;

public class BasvurularAdapter extends RecyclerView.Adapter<BasvurularAdapter.ViewHolder> {

    List<BasvuruListeleModel> list;
    Context context;
    Activity activity;


    public BasvurularAdapter(List<BasvuruListeleModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.basvurular_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView basvuruKullaniciAdiText,basvuruKullaniciMailText;
        Button basvuruKabulButon,basvuruRedButon;
        LinearLayout basvurularAnaLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            basvuruKullaniciAdiText=itemView.findViewById(R.id.basvuruKullaniciAdiText);
            basvuruKullaniciMailText=itemView.findViewById(R.id.basvuruKullaniciMailText);
            basvuruKabulButon=itemView.findViewById(R.id.basvuruKabulButon);
            basvuruRedButon=itemView.findViewById(R.id.basvuruRedButon);
            basvurularAnaLayout=itemView.findViewById(R.id.basvurularAnaLayout);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.basvuruKullaniciAdiText.setText(list.get(position).getKullaniciadi().toString());
        holder.basvuruKullaniciMailText.setText(list.get(position).getMailadres().toString());
        holder.basvurularAnaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*
                ChangeFragments changeFragment=new ChangeFragments(context);
                changeFragment.changeWithParemeters(new IlanDetayFragment(),list.get(position).getId(),
                        list.get(position).getKid().toString());
                         */
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
