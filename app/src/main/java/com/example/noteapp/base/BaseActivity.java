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
import com.example.noteapp.util.PreferenceManger;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {

    protected T binding;

    protected abstract T inflateViewBinding(LayoutInflater inflater);

    public PreferenceManger preferenceManger;

    public DataBaseHelper dataBaseHelper;

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

        Toolbar toolbar = (binding.getRoot()).findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton());

            if (hasBackButton()){
                if (backButtonDrawable() != 0)
                    getSupportActionBar().setHomeAsUpIndicator(backButtonDrawable());
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
