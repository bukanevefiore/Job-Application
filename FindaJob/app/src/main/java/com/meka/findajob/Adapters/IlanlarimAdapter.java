package com.meka.findajob.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Activity.IlanlarimNavigationActivity;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.R;
import com.meka.findajob.ViewHolder.IlanlarimViewHolder;

import java.util.List;

public class IlanlarimAdapter extends RecyclerView.Adapter<IlanlarimViewHolder> {

    List<IlanModel> ilanModelList;
    Context context;
    Activity activity;


    public IlanlarimAdapter(List<IlanModel> ilanModelList, Context context,Activity activity) {
        this.ilanModelList = ilanModelList;
        this.context = context;
        this.activity=activity;
    }



    @NonNull
    @Override
    public IlanlarimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ilanlarim_recyclerview_item,parent,false);
        return new IlanlarimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IlanlarimViewHolder holder, int position) {

        holder.ilanlarimBaslikText.setText(ilanModelList.get(position).getBaslik().toString());
        holder.ilanlarimAciklamaText.setText(ilanModelList.get(position).getAciklama().toString());
        holder.ilanlarimAdresText.setText(ilanModelList.get(position).getAdres().toString());
        holder.ilanlarimAnaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putString("ilanid",ilanModelList.get(position).getId().toString());
                Intent intent=new Intent(activity, IlanlarimNavigationActivity.class);
                activity.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return ilanModelList.size();
    }
}
