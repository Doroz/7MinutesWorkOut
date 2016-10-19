package com.example.usuario.a7minutesworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 12/05/2016.
 */
public class TreinosAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public TreinosAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        row = convertView;
        DataHandler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.celula_treino, parent, false);
            handler = new DataHandler();
            handler.icone = (ImageView)row.findViewById(R.id.imageView);
            handler.treino = (TextView)row.findViewById(R.id.nomeTreino);
            handler.inicial = (TextView)row.findViewById(R.id.IdTreino);
            row.setTag(handler);
        }else {
            handler = (DataHandler)row.getTag();
        }

        TreinosDataProvider dataProvider;
        dataProvider = (TreinosDataProvider) this.getItem(position);
        handler.icone.setImageResource(dataProvider.getListaIcones());
        handler.treino.setText(dataProvider.getListaTreinos());
        handler.inicial.setText(dataProvider.getListaIniciais());
        return row;
    }

    static class DataHandler {
        ImageView icone;
        TextView treino;
        TextView inicial;
    }
}
