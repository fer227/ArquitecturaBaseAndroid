package com.app.server.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modelo {
    @SerializedName("artista")
    @Expose
    private String artista;

    @SerializedName("cancion")
    @Expose
    private String cancion;

    @SerializedName("duracion")
    @Expose
    private String duracion;

    @SerializedName("productores")
    @Expose
    private String productores;

    public Modelo(String artista, String cancion, String duracion, String productores) {
        this.artista = artista;
        this.cancion = cancion;
        this.duracion = duracion;
        this.productores = productores;
    }

    public String getArtista(){
        return artista;
    }

    public void setArtista(String art){
        artista =art;
    }

    public String getCancion(){
        return cancion;
    }

    public void setCancion(String c){
        cancion =c;
    }

    public String getDuracion(){
        return duracion;
    }

    public void setDuracion(String d){
        duracion =d;
    }

    public String getProductores(){
        return productores;
    }

    public void setProductores(String p){
        productores =p;
    }
}
