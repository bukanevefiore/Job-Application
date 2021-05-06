package com.meka.findajob.ViewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.R;

public class IlanlarimViewHolder extends RecyclerView.ViewHolder {

    public TextView ilanlarimBaslikText,ilanlarimAciklamaText,ilanlarimAdresText,ilanlarimdetayNitelikText;
    public LinearLayout ilanlarimAnaLayout;

    public IlanlarimViewHolder(@NonNull View itemView) {
        super(itemView);

        ilanlarimBaslikText=itemView.findViewById(R.id.ilanlarimBaslikText);
        ilanlarimAciklamaText=itemView.findViewById(R.id.ilanlarimAciklamaText);
        ilanlarimAdresText=itemView.findViewById(R.id.ilanlarimAdresText);
        ilanlarimAnaLayout=itemView.findViewById(R.id.ilanlarimAnaLayout);


    }
}
