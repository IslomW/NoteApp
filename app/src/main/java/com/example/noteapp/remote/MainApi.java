package com.example.noteapp.remote;

import com.example.noteapp.model.Book;
import com.example.noteapp.model.News;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.User;
import com.google.gson.JsonObject;


import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    //Login and user
    @POST("/v1/auth-token/")
    Call<User> login(@Body User user);

    @GET("v1/user/")
    Call<ArrayList<User>> getUserList();

    @POST("v1/user/")
    Call<User> createUser(@Body User user);

    @PUT("v1/user/{id}")
    Call<User> registerDeviceToken( @Path("id") int id, @Body User user);


    //Note
    @GET("/v1/note")
    Call<ArrayList<Note>> getNote();

    @POST("/v1/note/")
    Call<Note> createNote(@Body Note note);

    @DELETE("/v1/note/{id}/")
    Call<Void> deleteNote( @Path("id") int id);

    @PUT("/v1/note/{id}/")
    Call<Note> updateNote( @Path("id") int id, @Body Note note);


    //News

    @GET("/v1/news")
    Call<ArrayList<News>> getNews(@Query("page") int page);


    //Books
    @GET("/v1/book/")
    Call<ArrayList<Book>> getBooks();

    @Multipart
    @PUT("/v1/book/{id}/")
    Call<Book> updateBook( @Path("id") int id,
                           @Part("title") RequestBody title,
                           @Part("descripton") RequestBody description,
                           @Part MultipartBody.Part image);
    @DELETE("/v1/book/{id}/")
    Call<Void> deleteBook( @Path("id") int id);


    @Multipart
    @POST("/v1/book/")
    Call<Book> createBook(
                          @Part("title") RequestBody title,
                          @Part("description") RequestBody description,
                          @Part MultipartBody.Part image
    );

    //Share
    @POST("/v1/news/{id}/share/")
    Call<String> shareNews( @Path("id") int newsId, @Body JsonObject body);


}

//
//    @GET("/v1/news")
//    Call<Note> getNews(@Header("Authorization")String bearerToken, @QueryMap HashMap<String, String > params);