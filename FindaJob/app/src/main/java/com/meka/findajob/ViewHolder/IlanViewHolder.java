package com.meka.findajob.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.R;

public class IlanViewHolder extends RecyclerView.ViewHolder {

    public TextView ilanBaslikText,ilanAciklamaText,ilanAdresText;

    public IlanViewHolder(@NonNull View itemView) {
        super(itemView);

        ilanBaslikText=itemView.findViewById(R.id.ilanBaslikText);
        ilanAciklamaText=itemView.findViewById(R.id.ilanAciklamaText);
        ilanAdresText=itemView.findViewById(R.id.ilanAdresText);

    }
}
