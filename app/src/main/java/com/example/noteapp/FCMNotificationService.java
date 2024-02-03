package com.example.noteapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMNotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        Log.d("From: ", message.getFrom());

        if (message.getData().size() > 0) {
            Log.d("message data payload: ", message.getData().toString());
        }


        if (message.getNotification() != null){
            Log.d("Message Notification Body", message.getNotification().getBody());
        }
    }
}
