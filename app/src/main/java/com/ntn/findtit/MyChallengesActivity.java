package com.ntn.findtit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MyChallengesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Desafio> desafios = new ArrayList<>();
        setContentView(R.layout.activity_my_challenges);

        for(int i=0; i<25; i++) {
            Desafio de = new Desafio("numero"+i, "na", i, i,5);
            desafios.add(de);
        }
        DesafioEditarAdapter d = new DesafioEditarAdapter(this, desafios);
        ListView list = findViewById(R.id.lista);
        list.setAdapter(d);
    }
}