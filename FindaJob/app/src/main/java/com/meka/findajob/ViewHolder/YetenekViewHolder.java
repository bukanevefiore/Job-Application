package com.meka.findajob.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meka.findajob.R;

public class YetenekViewHolder extends RecyclerView.ViewHolder {

    public TextView yetenekisim,yetenektextEdit,yetenektextDelete;


    public YetenekViewHolder(@NonNull View itemView) {
        super(itemView);

        yetenekisim=itemView.findViewById(R.id.yetenekisim);
        yetenektextEdit=itemView.findViewById(R.id.yetenektextEdit);
        yetenektextDelete=itemView.findViewById(R.id.yetenektextDelete);
    }
}
