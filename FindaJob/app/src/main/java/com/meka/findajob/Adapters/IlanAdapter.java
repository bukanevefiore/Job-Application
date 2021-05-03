package com.meka.findajob.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Activity.SignInActivity;
import com.meka.findajob.Fragments.IlanDetayFragment;
import com.meka.findajob.Models.IlanModel;
import com.meka.findajob.R;
import com.meka.findajob.Utils.ChangeFragments;
import com.meka.findajob.Utils.GetSharedPref;
import com.meka.findajob.ViewHolder.IlanViewHolder;

import java.util.List;

public class IlanAdapter extends RecyclerView.Adapter<IlanViewHolder> {

    List<IlanModel> ilanModelList;
    Context context;
    Activity activity;


    public IlanAdapter(List<IlanModel> ilanModelList, Context context) {
        this.ilanModelList = ilanModelList;
        this.context = context;
    }



    @NonNull
    @Override
    public IlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ilan_listele_layout,parent,false);
        return new IlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IlanViewHolder holder, int position) {

        holder.ilanBaslikText.setText(ilanModelList.get(position).getBaslik().toString());
        holder.ilanAciklamaText.setText(ilanModelList.get(position).getAciklama().toString());
        holder.ilanAdresText.setText(ilanModelList.get(position).getAdres().toString());
        holder.ilanlarAnaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ChangeFragments changeFragment=new ChangeFragments(context);
                //changeFragment.changeWithParemeters(new IlanDetayFragment(),ilanModelList.get(position).getId());

            }
        });

        GetSharedPref getSharedPref=new GetSharedPref(activity);
        getSharedPref.setSessioniki(ilanModelList.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return ilanModelList.size();
    }
}
