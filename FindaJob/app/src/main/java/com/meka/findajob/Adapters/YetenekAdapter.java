package com.meka.findajob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Models.YetenekListeleModel;
import com.meka.findajob.R;
import com.meka.findajob.ViewHolder.YetenekViewHolder;

import java.util.List;

public class YetenekAdapter extends RecyclerView.Adapter<YetenekViewHolder> {

    List<YetenekListeleModel> list;
    Context context;


    public YetenekAdapter(List<YetenekListeleModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public YetenekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.yetenek_listele_layout,parent,false);
        return new YetenekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YetenekViewHolder holder, int position) {

        holder.yetenekisim.setText(list.get(position).getYetenek());

        holder.yetenektextDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
