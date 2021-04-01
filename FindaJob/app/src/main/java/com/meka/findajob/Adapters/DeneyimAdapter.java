package com.meka.findajob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.meka.findajob.Models.DeneyimListeleModel;
import com.meka.findajob.R;

import java.util.List;

public class DeneyimAdapter extends RecyclerView.Adapter<DeneyimAdapter.ViewHolder> {

    private List<DeneyimListeleModel> list;
    private Context context;


    public DeneyimAdapter(List<DeneyimListeleModel> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override     // adapterinin oluşturduğumuz layout un tanımlama işlemi
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.deneyim_listele_layout,parent,false);
        return new ViewHolder(view);
    }

    // değişkenlere değer atama işlemleri
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.deneyimbolum.setText(list.get(position).getDeneyimalan().toString());
        holder.deneyimsirket.setText(list.get(position).getSirket().toString());
        holder.deneyimyil.setText(list.get(position).getYil());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // tanımlama işlemleri
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView deneyimbolum,deneyimsirket,deneyimyil,textEdit,textDelete;
        private SwipeRevealLayout swipeLayout;

        // değişkenleri eşleme
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deneyimbolum=itemView.findViewById(R.id.deneyimbolum);
            deneyimsirket=itemView.findViewById(R.id.deneyimsirket);
            deneyimyil=itemView.findViewById(R.id.deneyimyil);
           // textEdit=itemView.findViewById(R.id.textEdit);
           // textDelete=itemView.findViewById(R.id.textDelete);
           // swipeLayout=itemView.findViewById(R.id.swipeLayout);


        }
    }



}
