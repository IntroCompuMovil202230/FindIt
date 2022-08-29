package com.ntn.findtit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ntn.findtit.R;
import com.ntn.findtit.entity.Desafio;

import java.util.ArrayList;

public class DesafioEditarAdapter extends BaseAdapter {

    ArrayList<Desafio> modelArrayList = new ArrayList<>();
    Context context;

    public DesafioEditarAdapter(Context context, ArrayList<Desafio> modelArrayList) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }


    @Override
    public Desafio getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_desafio_item, parent, false);
        }

        TextView nombre_desafio = convertView.findViewById(R.id.nombre_desafio);
        TextView n_visitas = convertView.findViewById(R.id.n_visualizaciones);
        TextView n_pistas = convertView.findViewById(R.id.n_pistas);
        RatingBar rating = convertView.findViewById(R.id.rating);

        Desafio p = this.getItem(position);

        nombre_desafio.setText(p.getNombre_desafio());
        n_visitas.setText(p.getN_visitas()+" visitas");
        n_pistas.setText(p.getN_pistas()+" pistas");
        rating.setRating((float)p.getRating());

        return convertView;

    }
}
