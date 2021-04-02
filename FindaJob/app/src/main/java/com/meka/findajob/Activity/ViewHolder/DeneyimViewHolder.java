package com.meka.findajob.Activity.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.meka.findajob.Models.DeneyimEkleModel;
import com.meka.findajob.R;

// tanımlama işlemleri
public class DeneyimViewHolder extends RecyclerView.ViewHolder {

    public TextView deneyimbolum,deneyimsirket,deneyimyil,textEdit,textDelete;
    public SwipeRevealLayout swipeLayout;

    // değişkenleri eşleme
    public DeneyimViewHolder(@NonNull View itemView) {
        super(itemView);

        deneyimbolum=itemView.findViewById(R.id.deneyimbolum);
        deneyimsirket=itemView.findViewById(R.id.deneyimsirket);
        deneyimyil=itemView.findViewById(R.id.deneyimyil);
        textEdit=itemView.findViewById(R.id.textEdit);
        textDelete=itemView.findViewById(R.id.textDelete);
        swipeLayout=itemView.findViewById(R.id.swipeLayout);




    }
}