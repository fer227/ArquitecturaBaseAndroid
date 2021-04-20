package com.app.server.api;

import com.app.server.models.Modelo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("/canciones")
    @FormUrlEncoded
    Call<Modelo> postCancion(@Field("artista") String artista,
                             @Field("cancion") String cancion,
                             @Field("duracion") String duracion,
                             @Field("productores") String productores);
}
