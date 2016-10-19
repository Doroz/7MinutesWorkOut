package com.example.usuario.a7minutesworkout;

/**
 * Created by Usuario on 12/05/2016.
 */
public class TreinosDataProvider {

    private int listaIcones;
    private String listaTreinos;
    private String listaIniciais;

    public TreinosDataProvider(int listaIcones, String listaTreinos, String listaIniciais) {
        this.setListaIcones(listaIcones);
        this.setListaTreinos(listaTreinos);
        this.setListaIniciais(listaIniciais);
    }

    public String getListaTreinos() {
        return listaTreinos;
    }

    public void setListaTreinos(String listaTreinos) {
        this.listaTreinos = listaTreinos;
    }

    public String getListaIniciais() {
        return listaIniciais;
    }

    public void setListaIniciais(String listaIniciais) {
        this.listaIniciais = listaIniciais;
    }

    public int getListaIcones() {
        return listaIcones;
    }

    public void setListaIcones(int listaIcones) {
        this.listaIcones = listaIcones;
    }
}
