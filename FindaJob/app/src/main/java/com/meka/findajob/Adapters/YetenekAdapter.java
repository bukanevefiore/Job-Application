package com.meka.findajob.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Models.SilModel;
import com.meka.findajob.Models.YetenekListeleModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;
import com.meka.findajob.ViewHolder.YetenekViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.dereceYetenekListeleTextView.setText(list.get(position).getYetenekderece());
        // progres barı ayarlama
        String derece=list.get(position).getYetenekderece();
        Log.i("derecee",derece);
        int dereceint=Integer.parseInt(derece);
        Log.i("derecee2", String.valueOf(dereceint));

        int i=0;
        for(i=0;i<dereceint;i++) {
            holder.progressyetenekListele.setProgress(i);
        }

        // yetenek sil
        holder.yetenektextDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                yetenekSilRequest(list.get(position).getId(),position);

            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    // yetenek sil request
    public void yetenekSilRequest(String id,int position){
        Call<SilModel> request= ManagerAll.getInstance().yetenekSil(id);
        request.enqueue(new Callback<SilModel>() {
            @Override
            public void onResponse(Call<SilModel> call, Response<SilModel> response) {
                if(response.isSuccessful()){
                    deleteToList(position);
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SilModel> call, Throwable t) {

            }
        });
    }

    // listeden silinen yeteneği kaldırma
    public void deleteToList(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}


