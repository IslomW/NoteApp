package com.example.noteapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.example.noteapp.R;
import com.example.noteapp.db.DataBaseHelper;
import com.example.noteapp.remote.MainApi;
import com.example.noteapp.util.PreferenceManger;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {

    protected T binding;

    protected abstract T inflateViewBinding(LayoutInflater inflater);

    public PreferenceManger preferenceManger;

    public DataBaseHelper dataBaseHelper;

    public MainApi mainApi;
    LoadingBarDialog loadingBarDialog;

    protected boolean hasBackButton() {
        return false;
    }
    protected int backButtonDrawable() {
        return 0;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManger = PreferenceManger.getInstance(getApplicationContext());

        dataBaseHelper = new DataBaseHelper(this);

        loadingBarDialog = new LoadingBarDialog(this);

        Toolbar toolbar = (binding.getRoot()).findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton());

            if (hasBackButton()){
                if (backButtonDrawable() != 0)
                    getSupportActionBar().setHomeAsUpIndicator(backButtonDrawable());
            }
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.note.annyong.store")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mainApi = retrofit.create(MainApi.class);


    }

    public String getBearerToken(){
        String access_token = (String) preferenceManger.getValue(String.class, "accessToken", "");
        String bearerToken = "Bearer " + access_token;
        return bearerToken;
    }

    public void showLoading(){
        if (!loadingBarDialog.isShowing())
            loadingBarDialog.show();

    }

    public void hideLoading(){
        loadingBarDialog.hide();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
