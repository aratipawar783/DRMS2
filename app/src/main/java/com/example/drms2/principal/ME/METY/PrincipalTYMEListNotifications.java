package com.example.drms2.principal.ME.METY;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.NotificationAdapterTYME;
import com.example.drms2.NotificationItemTYME;
import com.example.drms2.NotificationManagerTYME;
import com.example.drms2.R;

import java.util.List;

public class PrincipalTYMEListNotifications extends AppCompatActivity {

    private ListView notificationListView;
    private NotificationAdapterTYME adapter;
    private List<NotificationItemTYME> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_aefylist_notifications); // Ensure this layout contains the ListView

        notificationListView = findViewById(R.id.notificationListView);

        // Get the notifications from NotificationManager
        notifications = NotificationManagerTYME.getInstance(this).getNotifications();

        // Create and set the custom adapter for the ListView
        adapter = new NotificationAdapterTYME(this, notifications);
        notificationListView.setAdapter(adapter);

        // Handle item click in ListView to show dialog for delete notification
        notificationListView.setOnItemClickListener((parent, view, position, id) -> {
            showDeleteConfirmationDialog(position);
        });
    }

    private void showDeleteConfirmationDialog(int position) {
        // Get the notification to be deleted
        NotificationItemTYME notificationToDelete = notifications.get(position);

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
        NotificationManagerTYME.getInstance(this).updateNotifications(notifications); // Update the NotificationManager if needed
    }
}