package com.example.noteapp.remote;

import com.example.noteapp.model.Book;
import com.example.noteapp.model.News;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.User;


import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MainApi {

    //Login and user
    @POST("/v1/auth-token/")
    Call<User> login(@Body User user);

    @POST("v1/user/")
    Call<User> createUser(@Body User user);


    //Note
    @GET("/v1/note")
    Call<ArrayList<Note>> getNote(@Header("Authorization") String bearerToken);

    @POST("/v1/note/")
    Call<Note> createNote(@Header("Authorization") String bearerToken, @Body Note note);

    @DELETE("/v1/note/{id}/")
    Call<Void> deleteNote(@Header("Authorization") String bearerToken, @Path("id") int id);

    @PUT("/v1/note/{id}/")
    Call<Note> updateNote(@Header("Authorization") String bearerToken, @Path("id") int id, @Body Note note);


    //News

    @GET("/v1/news")
    Call<ArrayList<News>> getNews(@Header("Authorization") String bearerToken, @Query("page") int page);


    //Books
    @GET("/v1/book/")
    Call<ArrayList<Book>> getBooks(@Header("Authorization") String bearerToken);

    @Multipart
    @POST("/v1/book/")
    Call<Book> createBook(@Header("Authorization") String bearerToken,
                          @Part("title") RequestBody title,
                          @Part("description") RequestBody description,
                          @Part MultipartBody.Part image
    );


}

//
//    @GET("/v1/news")
//    Call<Note> getNews(@Header("Authorization")String bearerToken, @QueryMap HashMap<String, String > params);