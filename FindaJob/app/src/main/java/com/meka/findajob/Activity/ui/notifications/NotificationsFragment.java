package com.meka.findajob.Activity.ui.notifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.meka.findajob.R;
import com.meka.findajob.Utils.GetSharedPref;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    String ilanid;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);

        tanimlamalar();

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void tanimlamalar(){

        SharedPreferences sharedPreferences;
        GetSharedPref getSharedPref=new GetSharedPref(getActivity());
        sharedPreferences=getSharedPref.getSession();
        ilanid=sharedPreferences.getString("ilanid",null);
        Log.i("gelenNotifiilanid",sharedPreferences.getString("ilanid",null));

    }
}