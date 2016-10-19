package com.example.usuario.a7minutesworkout;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TelaTreinoRun extends Activity {

    String[] listaTreino = {"Treino A", "Treino B", "Treino C", "Treino D", "Treino E",
            "Treino F", "Treino G", "Treino H", "Treino I", "Treino J", };
    int[] listaTempos = {20,20,20,20,30,30,30,40,40,40};

    int exercicioAtual = 0;
    int tempoExercicio;
    int tempoDescanso = 45;
    boolean estadoTreino = false;
    boolean falou3segs;

    ProgressBar progressBarTreinoRun;
    TextView textoExercicioAtual;
    TextView textoProximoExercicio;
    TextView textoTituloProximo;

    TextView textoTempo;
    ImageView imagemGym;
    RelativeLayout fundoTela;
    Button botaoPauseReset;
    CountDownTimer countDownTimer;

    TextToSpeech ttsObject;
    int result;
    int treinoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_treino_run);

        botaoPauseReset = (Button) findViewById(R.id.botaoInicio);
        textoExercicioAtual = (TextView) findViewById(R.id.textoExercicioAtual);
        textoProximoExercicio = (TextView) findViewById(R.id.textoProximoExercicio);
        textoTituloProximo = (TextView) findViewById(R.id.textoTituloProximoExercicio);
        textoTempo = (TextView) findViewById(R.id.textoTempo);
        progressBarTreinoRun = (ProgressBar) findViewById(R.id.progressBarTreinoRun);
        imagemGym = (ImageView) findViewById(R.id.imagemGym);
        fundoTela = (RelativeLayout) findViewById(R.id.fundoViewTreino);

        tempoExercicio = listaTempos[exercicioAtual];
        textoTempo.setText(String.valueOf(tempoExercicio));
        progressBarTreinoRun.setMax(listaTreino.length * 2);
        progressBarTreinoRun.setProgress(1);

        treinoSelecionado = getIntent().getExtras().getInt("numeroTreino");
        String[] listaTreino = {};
        switch (treinoSelecionado){
            case 1: listaTreino = new String[]{"Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", "Treino 1", };
                break;
            case 2: listaTreino = new String[]{"Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", "Treino 2", };
                break;
            case 3: listaTreino = new String[]{"Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", "Treino 3", };
                break;
            default: listaTreino = new String[]{"Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", "Treino 4", };
                break;
        }

        textoExercicioAtual.setText(String.valueOf(listaTreino[exercicioAtual]));
        textoProximoExercicio.setText(String.valueOf(listaTreino[exercicioAtual + 1]));

        ttsObject = new TextToSpeech(TelaTreinoRun.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    ttsObject.speak("Get Ready!", TextToSpeech.QUEUE_FLUSH, null);
                }else {
                    Toast.makeText(getApplicationContext(), "Não suporta VoiceToSpeech!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clicaBotao(View view){
        if (!estadoTreino){
            estadoTreino=true;
            botaoPauseReset.setText("Pause");
            proximoExercicio();
        }else {
            estadoTreino=false;
            botaoPauseReset.setText("Continue");
            stopTempo();
        }
    }

    public void atualizaLabelTempo(int secondsLeft){
        if (secondsLeft<4 && !falou3segs){
            falou3segs=true;
            ttsObject.speak("3 seconds!", TextToSpeech.QUEUE_FLUSH, null);
        }
        textoTempo.setText(Integer.toString(secondsLeft));
    }

    public void stopTempo(){
        countDownTimer.cancel();
    }

    public void proximoExercicio(){
        ttsObject.speak("Start!", TextToSpeech.QUEUE_FLUSH,null);
        falou3segs=false;
        progressBarTreinoRun.setProgress(progressBarTreinoRun.getProgress()+1);
        countDownTimer = new CountDownTimer(tempoExercicio*100, 100){
            @Override
            public void onTick(long millisUntilFinished) {
                atualizaLabelTempo((int) millisUntilFinished / 100);
            }

            @Override
            public void onFinish() {
                if (exercicioAtual<listaTreino.length){
                    iniciaDescanso();
                }else {
                    acabouTreino();
                }
            }
        }.start();

        fundoTela.setBackgroundColor(Color.parseColor("#353535"));
        textoExercicioAtual.setText(String.valueOf(listaTreino[exercicioAtual]));
        if (exercicioAtual<listaTreino.length-1){
            textoProximoExercicio.setText(String.valueOf(listaTreino[exercicioAtual+1]));
        }else {
            textoProximoExercicio.setText("...");
        }
        textoTempo.setText(String.valueOf(tempoExercicio));
        exercicioAtual++;
    }

    public void iniciaDescanso(){
        ttsObject.speak("Rest!", TextToSpeech.QUEUE_FLUSH, null);
        falou3segs=false;
        fundoTela.setBackgroundColor(Color.parseColor("577EC3"));
        textoExercicioAtual.setText("Descansar!");
        tempoExercicio = tempoDescanso;
        progressBarTreinoRun.setProgress(progressBarTreinoRun.getProgress() + 1);
        countDownTimer = new CountDownTimer(tempoExercicio*100, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                atualizaLabelTempo((int) millisUntilFinished / 100);
            }

            @Override
            public void onFinish() {
                proximoExercicio();
            }
        }.start();
    }

    public void acabouTreino(){
        textoExercicioAtual.setText("Treino Concluído!");
        imagemGym.setScaleX(0);
        imagemGym.setScaleY(0);
        imagemGym.animate()
                .scaleX(0.85f)
                .scaleY(0.85f)
                .rotationBy(720)
                .setDuration(1000);
        imagemGym.animate().alpha(1f).setDuration(1000);
        fundoTela.setBackgroundColor(Color.parseColor("#383838"));
        MediaPlayer som = MediaPlayer.create(getApplicationContext(), R.raw.flirtwhistle);
        som.start();

        botaoPauseReset.setEnabled(false);
        textoExercicioAtual.setAlpha(0);
        textoProximoExercicio.setAlpha(0);
        textoTempo.setAlpha(0);
        textoTituloProximo.setText("Treino Concluído!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ttsObject != null){
            ttsObject.stop();
            ttsObject.shutdown();
        }
    }
}
