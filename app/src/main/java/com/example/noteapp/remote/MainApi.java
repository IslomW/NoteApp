package com.example.noteapp.remote;

import com.example.noteapp.model.News;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.User;


import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MainApi{


    @POST("/v1/auth-token/")
    Call<User> login(@Body User user);

    @GET("/v1/note")
    Call<ArrayList<Note>> getNote(@Header("Authorization")String bearerToken);

    @POST("/v1/note/")
    Call<Note> createNote(@Header("Authorization")String bearerToken, @Body Note note);

    @DELETE("/v1/note/{id}/")
    Call<Void> deleteNote(@Header("Authorization")String bearerToken, @Path("id") int id);

    @PUT("/v1/note/{id}/")
    Call<Note> updateNote(@Header("Authorization")String bearerToken,@Path("id") int id,@Body Note note);

    @GET("/v1/news")
    Call<ArrayList<News>> getNews(@Header("Authorization")String bearerToken, @Query("page") int page);



}

//
//    @GET("/v1/news")
//    Call<Note> getNews(@Header("Authorization")String bearerToken, @QueryMap HashMap<String, String > params);