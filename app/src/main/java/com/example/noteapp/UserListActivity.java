package com.example.noteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noteapp.adapters.UserListAdapter;
import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityUserListBinding;
import com.example.noteapp.model.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends BaseActivity<ActivityUserListBinding> {
    @Override
    protected ActivityUserListBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityUserListBinding.inflate(inflater);
    }

    private UserListAdapter adapter;

    private ArrayList<User> userArrayList =new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new UserListAdapter(userArrayList);

        adapter.setUserSelectListener(new UserListAdapter.UserSelectListener() {
            @Override
            public void onSelected(User user) {
                shareNews(user);
            }
        });
        binding.recyclerViewUser.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewUser.setAdapter(adapter);

        Call<ArrayList<User>> call = mainApi.getUserList();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userArrayList.clear();
                if (response.body() != null)
                    userArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();

                Log.d("News", userArrayList.toString());
            }


            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });

    }

    private void shareNews(User user) {
        int newId = getIntent().getIntExtra("news_id", 0);
        if (newId >0){
            JsonObject body = new JsonObject();
            body.addProperty("type",  user.getId());
            body.addProperty("type", "notification");
//            body.addProperty("type", "data");

            Call<String> call = mainApi.shareNews( newId, body);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        }
    }
}
