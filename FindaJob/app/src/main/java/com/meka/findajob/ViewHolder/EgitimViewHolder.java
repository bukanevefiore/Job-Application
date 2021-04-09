package com.meka.findajob.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.meka.findajob.R;

public class EgitimViewHolder extends RecyclerView.ViewHolder {

    public TextView universiteName,üniBolum,egitimYili,egitimtextEdit,egitimtextDelete;
    public SwipeRevealLayout egitimSwipeLayout;

    public EgitimViewHolder(@NonNull View itemView) {
        super(itemView);

        universiteName=itemView.findViewById(R.id.universiteName);
        üniBolum=itemView.findViewById(R.id.üniBolum);
        egitimYili=itemView.findViewById(R.id.egitimYili);
        egitimtextEdit=itemView.findViewById(R.id.egitimtextEdit);
        egitimtextDelete=itemView.findViewById(R.id.egitimtextDelete);
        egitimSwipeLayout=itemView.findViewById(R.id.egitimSwipeLayout);
    }
}
