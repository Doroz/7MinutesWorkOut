package com.example.usuario.a7minutesworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TelaExercicios extends AppCompatActivity {

    ListView listaExercicios;
    String exercicioSelecionado;
    int treinoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exercicios);
        listaExercicios = (ListView)findViewById(R.id.listaExercicios);

        treinoSelecionado = getIntent().getExtras().getInt("numeroTreino");
        String[] listaTreino = {};
        switch (treinoSelecionado){
            case 1: listaTreino = new String[]{"Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1"};
                break;
            case 2: listaTreino = new String[]{"Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2"};
                break;
            case 3: listaTreino = new String[]{"Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3"};
                break;
            case 4: listaTreino = new String[]{"Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4"};
                break;
            case 5: listaTreino = new String[]{"Treino 5", "Treino 5", "Treino 5", "Treino 5", "Treino 5", "Treino 5", "Treino 5", "Treino 5", "Treino 5", "Treino 5"};
                break;
            case 6: listaTreino = new String[]{"Treino 6", "Treino 6", "Treino 6", "Treino 6", "Treino 6", "Treino 6", "Treino 6", "Treino 6", "Treino 6", "Treino 6"};
                break;
            case 7: listaTreino = new String[]{"Treino 7", "Treino 7", "Treino 7", "Treino 7", "Treino 7", "Treino 7", "Treino 7", "Treino 7", "Treino 7", "Treino 7"};
                break;
            case 8: listaTreino = new String[]{"Treino 8", "Treino 8", "Treino 8", "Treino 8", "Treino 8", "Treino 8", "Treino 8", "Treino 8", "Treino 8", "Treino 8"};
                break;
            case 9: listaTreino = new String[]{"Treino 9", "Treino 9", "Treino 9", "Treino 9", "Treino 9", "Treino 9", "Treino 9", "Treino 9", "Treino 9", "Treino 9"};
                break;
            default: listaTreino = new String[]{"Treino 10", "Treino 10", "Treino 10", "Treino 10", "Treino 10", "Treino 10", "Treino 10", "Treino 10", "Treino 10", "Treino 10"};
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listaTreino);

        listaExercicios.setAdapter(adapter);

        listaExercicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listaExercicios.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Item :"+itemPosition+" ListItem: " +itemValue, Toast.LENGTH_SHORT).show();
                exercicioSelecionado = itemValue;
                loadTelaVideos();
            }
        });
    }

    public void loadTelaVideos(){
        Intent intent = new Intent(getApplicationContext(), TelaVideos.class);
        intent.putExtra("nomeVideo", exercicioSelecionado);
        startActivity(intent);
    }

    public void loadTelaTreinoRun(View view){
        Intent intent = new Intent(getApplicationContext(), TelaTreinoRun.class);
        intent.putExtra("numeroTreino", treinoSelecionado);
        startActivity(intent);
    }
}


