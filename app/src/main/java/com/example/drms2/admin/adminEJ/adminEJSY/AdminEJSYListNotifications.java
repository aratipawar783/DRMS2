package com.example.drms2.admin.adminEJ.adminEJSY;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.NotificationAdapterSYEJ;
import com.example.drms2.NotificationItemSYEJ;
import com.example.drms2.NotificationManagerSYEJ;
import com.example.drms2.R;

import java.util.List;

public class AdminEJSYListNotifications extends AppCompatActivity {

    private ListView notificationListView;
    private NotificationAdapterSYEJ adapter;
    private List<NotificationItemSYEJ> notifications;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_aefylist_notifications);

        notificationListView = findViewById(R.id.notificationListView);

        // Get the notifications from NotificationManager
        notifications = NotificationManagerSYEJ.getInstance(this).getNotifications();

        // Create and set the custom adapter for the ListView
        adapter = new NotificationAdapterSYEJ(this, notifications);
        notificationListView.setAdapter(adapter);

        // Handle item click in ListView to show dialog for delete notification
        notificationListView.setOnItemClickListener((parent, view, position, id) -> {
            showDeleteConfirmationDialog(position);
        });
    }

    private void showDeleteConfirmationDialog(int position) {
        // Get the notification to be deleted
        NotificationItemSYEJ notificationToDelete = notifications.get(position);

        // Create the confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Notification")
                .setMessage("Are you sure you want to delete this notification?\n\n" +
                        "Title: " + notificationToDelete.getTitle() + "\n" +
                        "Message: " + notificationToDelete.getMessage())
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Handle deletion of the selected notification
                    deleteNotification(position);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void deleteNotification(int position) {
        // Remove the notification from the list
        notifications.remove(position);
        adapter.notifyDataSetChanged();
        NotificationManagerSYEJ.getInstance(this).updateNotifications(notifications); // Update the NotificationManager if needed
    }
}