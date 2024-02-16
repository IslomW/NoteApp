package com.example.noteapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.example.noteapp.base.BaseRecyclerAdapter;
import com.example.noteapp.base.BaseViewHolder;
import com.example.noteapp.databinding.ItemUserBinding;
import com.example.noteapp.model.User;

import java.util.ArrayList;


public class UserListAdapter extends BaseRecyclerAdapter {
    private ArrayList<User> userArrayList;

    UserSelectListener userSelectListener;

    public void setUserSelectListener(UserSelectListener userSelectListener) {
        this.userSelectListener = userSelectListener;
    }

    public UserListAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding itemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new UserViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userSelectListener != null){
                    userSelectListener.onSelected(userArrayList.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserViewHolder extends BaseViewHolder{
        ItemUserBinding binding;

        public UserViewHolder(@NonNull ItemUserBinding binding) {
            super(binding);
            this.binding = binding;
        }


        @Override
        protected void onBind(int positon) {
            User user = userArrayList.get(positon);

            binding.textViewUserFullName.setText(user.getEmail());
        }
    }


    public interface UserSelectListener{
        void onSelected(User user);
    }

}


