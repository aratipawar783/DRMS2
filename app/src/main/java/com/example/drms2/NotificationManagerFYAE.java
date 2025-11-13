package com.example.drms2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NotificationManagerFYAE {

    private static NotificationManagerFYAE instance;
    private List<NotificationItemFYAE> notifications;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "notification_preferencesaefy";
    private static final String NOTIFICATION_LIST_KEY = "notificationsaefy";

    private NotificationManagerFYAE(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        notifications = loadNotifications();
    }

    // Get the singleton instance
    public static NotificationManagerFYAE getInstance(Context context) {
        if (instance == null) {
            instance = new NotificationManagerFYAE(context);
        }
        return instance;
    }

    // Add a notification to the list and save to SharedPreferences
    public void addNotification(NotificationItemFYAE notification) {
        notifications.add(notification);
        saveNotifications();
    }

    // Remove a specific notification by index
    public void removeNotification(int position) {
        if (position >= 0 && position < notifications.size()) {
            notifications.remove(position);
            saveNotifications();
        }
    }

    // Update the notifications list and save to SharedPreferences
    public void updateNotifications(List<NotificationItemFYAE> updatedNotifications) {
        this.notifications = updatedNotifications;
        saveNotifications();
    }

    // Get the list of notifications
    public List<NotificationItemFYAE> getNotifications() {
        return notifications;
    }

    // Save the notifications to SharedPreferences
    private void saveNotifications() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(notifications);
        editor.putString(NOTIFICATION_LIST_KEY, json);
        editor.apply();
    }

    // Load notifications from SharedPreferences
    private List<NotificationItemFYAE> loadNotifications() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(NOTIFICATION_LIST_KEY, null);
        Type type = new TypeToken<List<NotificationItemFYAE>>() {}.getType();
        if (json != null) {
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }
}