package com.meka.findajob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Models.EgitimListeleModel;
import com.meka.findajob.Models.SilModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.ViewHolder.EgitimViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EgitimAdapter extends RecyclerView.Adapter<EgitimViewHolder> {

    List<EgitimListeleModel> list;
    private Context context;

    public EgitimAdapter(List<EgitimListeleModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public EgitimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.egitim_listele_layout,parent,false);
        return new EgitimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EgitimViewHolder holder, int position) {

        holder.universiteName.setText(list.get(position).getUniversite());
        holder.üniBolum.setText(list.get(position).getBolum());
        holder.egitimYili.setText(list.get(position).getBaslangic()+"-"+list.get(position).getBitis());

        holder.egitimtextDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                egitimSilRequest(list.get(position).getId(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // egitim silme
    public void egitimSilRequest(String id,int position){

        Call<SilModel> request= ManagerAll.getInstance().egitimSil(id);
        request.enqueue(new Callback<SilModel>() {
            @Override
            public void onResponse(Call<SilModel> call, Response<SilModel> response) {
                if(response.isSuccessful()){
                    deleteToList(position);
                }
            }

            @Override
            public void onFailure(Call<SilModel> call, Throwable t) {

            }
        });
    }

    // listeden silinen deneyimi kaldırma
    public void deleteToList(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
