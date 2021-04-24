package com.app.server.api;

import com.app.server.models.Modelo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @POST("/canciones")
    @FormUrlEncoded
    Call<Modelo> postCancion(@Field("artista") String artista,
                             @Field("cancion") String cancion,
                             @Field("duracion") String duracion,
                             @Field("productores") String productores);


    @GET("/canciones/{id}")
    Call<Modelo> getCancion(@Path("id") int id);

    @GET("/canciones")
    Call<Map<Integer, Modelo>> getCanciones();

    @DELETE("/canciones/{id}")
    Call<Void> deleteCancion(@Path("id") int id);

}
