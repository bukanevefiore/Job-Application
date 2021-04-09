package com.meka.findajob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.meka.findajob.ViewHolder.DeneyimViewHolder;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.Models.DeneyimSilModel;
import com.meka.findajob.R;
import com.meka.findajob.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeneyimAdapter extends RecyclerView.Adapter<DeneyimViewHolder> {

    private List<DeneyimListeleModel> list;
    private Context context;
    private final ViewBinderHelper viewBinderHelper=new ViewBinderHelper();



    public DeneyimAdapter(List<DeneyimListeleModel> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override     // adapterinin oluşturduğumuz layout un tanımlama işlemi
    public DeneyimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.deneyim_listele_layout,parent,false);

        return new DeneyimViewHolder(view);
    }

    // değişkenlere değer atama işlemleri
    @Override
    public void onBindViewHolder(@NonNull DeneyimViewHolder holder, int position) {
/*
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.swipeLayout,String.valueOf(list.get(position).getId()));
        viewBinderHelper.closeLayout(String.valueOf(list.get(position).getId()));
*/
        holder.deneyimbolum.setText(list.get(position).getDeneyimalan().toString());
        holder.deneyimsirket.setText(list.get(position).getSirket().toString());
        holder.deneyimyil.setText(list.get(position).getYil());

        holder.textDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deneyimSil(list.get(position).getId().toString(),position);

            }
        });

    }
/*
    // tanımlama işlemleri
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView deneyimbolum,deneyimsirket,deneyimyil,textEdit,textDelete;
        public SwipeRevealLayout swipeLayout;

        // değişkenleri eşleme
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deneyimbolum=itemView.findViewById(R.id.deneyimbolum);
            deneyimsirket=itemView.findViewById(R.id.deneyimsirket);
            deneyimyil=itemView.findViewById(R.id.deneyimyil);
            textEdit=itemView.findViewById(R.id.textEdit);
            textDelete=itemView.findViewById(R.id.textDelete);
            swipeLayout=itemView.findViewById(R.id.swipeLayout);




        }
    }

 */


    @Override
    public int getItemCount() {
        return list.size();
    }





    public void deneyimSil(String id,int position){

        Call<DeneyimSilModel> request= ManagerAll.getInstance().deneyimSil(id);
        request.enqueue(new Callback<DeneyimSilModel>() {
            @Override
            public void onResponse(Call<DeneyimSilModel> call, Response<DeneyimSilModel> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                    deleteToList(position);
                }
            }

            @Override
            public void onFailure(Call<DeneyimSilModel> call, Throwable t) {

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
