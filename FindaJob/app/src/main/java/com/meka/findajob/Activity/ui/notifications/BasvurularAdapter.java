package com.meka.findajob.Activity.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.Models.BasvuruListeleModel;
import com.meka.findajob.Models.BasvuruOnayRedModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        // basvuru onayla
        holder.basvuruKabulButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Log.i("butonabasıldı","basıldı");
                basvuruOnaylaRequest(list.get(position).getId().toString(),position);
            }
        });
        // basvuru reddet
        holder.basvuruRedButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basvuruRedRequest(list.get(position).getId().toString(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void basvuruOnaylaRequest(String basvuruid,int position){

        Call<BasvuruOnayRedModel> request= ManagerAll.getInstance().basvuruOnayla(basvuruid);
        request.enqueue(new Callback<BasvuruOnayRedModel>() {
            @Override
            public void onResponse(Call<BasvuruOnayRedModel> call, Response<BasvuruOnayRedModel> response) {
                if(response.isSuccessful()){
                    if(response.body().isTf()){
                        Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                        deleteToList(position);
                    }
                }
            }

            @Override
            public void onFailure(Call<BasvuruOnayRedModel> call, Throwable t) {

            }
        });
    }
    public void basvuruRedRequest(String basvuruid,int position){
        Log.i("basvuruonay","burdaa");
        Call<BasvuruOnayRedModel> request=ManagerAll.getInstance().basvuruRed(basvuruid);
        request.enqueue(new Callback<BasvuruOnayRedModel>() {
            @Override
            public void onResponse(Call<BasvuruOnayRedModel> call, Response<BasvuruOnayRedModel> response) {
                if(response.body().isTf()){

                    Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                    deleteToList(position);
                }
            }

            @Override
            public void onFailure(Call<BasvuruOnayRedModel> call, Throwable t) {

            }
        });
    }


    // listeden basvuruyu kaldırma
    public void deleteToList(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}
