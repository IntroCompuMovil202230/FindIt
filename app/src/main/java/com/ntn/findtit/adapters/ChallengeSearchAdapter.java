package com.ntn.findtit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.ntn.findtit.R;
import com.ntn.findtit.entity.Desafio;

import java.util.ArrayList;

public class ChallengeSearchAdapter extends BaseAdapter {

    ArrayList<Desafio> modelArrayList = new ArrayList<>();
    Context context;

    public ChallengeSearchAdapter(Context context, ArrayList<Desafio> modelArrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_search, parent, false);
        }

        TextView nombre_desafio = convertView.findViewById(R.id.nombre_desafio_search);
        TextView n_pistas = convertView.findViewById(R.id.n_pistas_search);
        RatingBar rating = convertView.findViewById(R.id.rating_search);
        Chip dificultad = convertView.findViewById(R.id.dificultad_search);

        Desafio p = this.getItem(position);

        nombre_desafio.setText(p.getNombre_desafio());
        dificultad.setText(p.getDificultad());
        n_pistas.setText(p.getN_pistas()+" pistas");
        rating.setRating((float)p.getRating());

        return convertView;

    }
}

