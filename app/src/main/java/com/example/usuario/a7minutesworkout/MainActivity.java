package com.example.usuario.a7minutesworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    int treinoSelecionado;
    TextToSpeech toSpeech;

    ImageView imagemTreinos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView;

        String[] lista_nomes_treino;
        String[] inicial_treinos;

        TreinosAdapter adapter;

        int[] lista_e_icones = {R.drawable.gymgym, R.drawable.gymgym, R.drawable.gymgym, R.drawable.gymgym, R.drawable.gymgym,
                R.drawable.gymgym, R.drawable.gymgym, R.drawable.gymgym, R.drawable.gymgym, R.drawable.gymgym};

        listView = (ListView)findViewById(R.id.listaTreinos);
        imagemTreinos = (ImageView)findViewById(R.id.imagemTreinos);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            imagemTreinos.animate().alpha(1f).setDuration(2000);
        }

        lista_nomes_treino = getResources().getStringArray(R.array.lista_treinos);
        inicial_treinos = getResources().getStringArray(R.array.inicial_treinos);

        int i =0;
        adapter = new TreinosAdapter(getApplicationContext(), R.layout.celula_treino);
        listView.setAdapter(adapter);

        for (String titles:lista_nomes_treino){
            TreinosDataProvider dataProvider = new TreinosDataProvider(lista_e_icones[i], titles, inicial_treinos[i]);
            adapter.add(dataProvider);
            i++;
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                treinoSelecionado = position;
                Toast.makeText(getApplicationContext(), "Treino " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                loadTelaExercicios();
            }
        });

        toSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    toSpeech.speak("Select your trainning!", TextToSpeech.QUEUE_FLUSH, null);
                }else {
                    Toast.makeText(getApplicationContext(), "Não suporta VoiceToSpeech!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadTelaExercicios() {
        Intent intent = new Intent(getApplicationContext(), TelaExercicios.class);
        intent.putExtra("numeroTreino", treinoSelecionado);
        startActivity(intent);
    }


}
