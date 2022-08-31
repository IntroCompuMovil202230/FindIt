package com.ntn.findtit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ntn.findtit.R;
import com.ntn.findtit.entity.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {
    ArrayList<Notification> modelArrayList = new ArrayList<>();
    Context context;

    public NotificationAdapter(Context context, ArrayList<Notification> modelArrayList) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Notification getItem(int i) {
        return this.modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_notification_cold_hot, viewGroup, false);
        }

        TextView titulo,descripcion;
        titulo = view.findViewById(R.id.notification_title);
        descripcion = view.findViewById(R.id.notification_desc);

        Notification n = this.getItem(i);

        titulo.setText(n.getTitulo());
        descripcion.setText(n.getDescripcion());

        return view;
    }
}
