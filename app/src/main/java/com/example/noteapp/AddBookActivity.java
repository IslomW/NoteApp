package com.example.noteapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddBookBinding;
import com.example.noteapp.model.Book;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends BaseActivity<ActivityAddBookBinding> {

    private File selectFile;
    private Book book;
    @Override
    protected ActivityAddBookBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddBookBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (Book) getIntent().getSerializableExtra("book");
        if (book != null){
            binding.buttonCreateBook.setText("Update Book");
            binding.editTextTitle.setText(book.getTitle());
            binding.editTextDescription.setText(book.getDescription());

            Glide.with(binding.imageViewBook).load(book.getImageUrl()).into(binding.imageViewBook);
        }else{
            setTitle("Create Book");
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2000);
        }

        binding.imageViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Seclet Photos"), 1000);
            }
        });

        binding.buttonCreateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.editTextTitle.getText().toString();
                String description = binding.editTextDescription.getText().toString();



//                if (title.isEmpty() && description.isEmpty() && selectFile == null) {
//                    return;
//                }

                if (book == null) {

                    RequestBody titleRb = RequestBody.create(MultipartBody.FORM, title);
                    RequestBody descriptionRb = RequestBody.create(MultipartBody.FORM, description);
                    String mediaType = "jpg";
                    RequestBody imageRb = RequestBody.create(MediaType.parse(mediaType), selectFile);

                    MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", selectFile.getName(), imageRb);

                    Call<Book> call = mainApi.createBook(titleRb, descriptionRb, imagePart);

                    showLoading();
                    call.enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            hideLoading();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            hideLoading();
                        }
                    });
                }else {

                    RequestBody titleRb = RequestBody.create(MultipartBody.FORM, title);
                    RequestBody descriptionRb = RequestBody.create(MultipartBody.FORM, description);


                    Call<Book> call = mainApi.updateBook(book.getId(), titleRb, descriptionRb);
                    showLoading();

                    call.enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            hideLoading();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }

    private File getPathFromUri(Uri contentUri) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Cursor returnCursor = getContentResolver().query(contentUri, null, null, null);
            int columnIndex = returnCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            returnCursor.moveToFirst();
            String filePath = returnCursor.getString(columnIndex);
            return new File(filePath);
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            Uri selectImageUri = data.getData();
            binding.imageViewBook.setImageURI(selectImageUri);
            selectFile = getPathFromUri(selectImageUri);
        }
    }
}
