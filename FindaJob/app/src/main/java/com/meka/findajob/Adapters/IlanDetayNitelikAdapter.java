package com.meka.findajob.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Fragments.IlanDetayFragment;
import com.meka.findajob.Models.IlanDetayNitelikModel;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.R;
import com.meka.findajob.Utils.ChangeFragments;
import com.meka.findajob.ViewHolder.IlanViewHolder;

import java.util.List;

public class IlanDetayNitelikAdapter extends RecyclerView.Adapter<IlanViewHolder> {

    List<IlanDetayNitelikModel> ilanModelList;
    Context context;
    Activity activity;


    public IlanDetayNitelikAdapter(List<IlanDetayNitelikModel> ilanModelList, Context context) {
        this.ilanModelList = ilanModelList;
        this.context = context;
    }



    @NonNull
    @Override
    public IlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ilandetay_nitelik_recycler_item,parent,false);
        return new IlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IlanViewHolder holder, int position) {

        holder.ilandetayNitelikText.setText(ilanModelList.get(position).getNitelik().toString());

    }

    @Override
    public int getItemCount() {
        return ilanModelList.size();
    }
}
