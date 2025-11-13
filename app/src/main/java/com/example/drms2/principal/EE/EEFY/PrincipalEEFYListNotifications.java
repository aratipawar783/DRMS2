package com.example.drms2.principal.EE.EEFY;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.NotificationAdapterFYEE;
import com.example.drms2.NotificationItemFYEE;
import com.example.drms2.NotificationManagerFYEE;
import com.example.drms2.R;

import java.util.List;

public class PrincipalEEFYListNotifications extends AppCompatActivity {

    private ListView notificationListView;
    private NotificationAdapterFYEE adapter;
    private List<NotificationItemFYEE> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_aefylist_notifications); // Ensure this layout contains the ListView

        notificationListView = findViewById(R.id.notificationListView);

        // Get the notifications from NotificationManager
        notifications = NotificationManagerFYEE.getInstance(this).getNotifications();

        // Create and set the custom adapter for the ListView
        adapter = new NotificationAdapterFYEE(this, notifications);
        notificationListView.setAdapter(adapter);

        // Handle item click in ListView to show dialog for delete notification
        notificationListView.setOnItemClickListener((parent, view, position, id) -> {
            showDeleteConfirmationDialog(position);
        });
    }

    private void showDeleteConfirmationDialog(int position) {
        // Get the notification to be deleted
        NotificationItemFYEE notificationToDelete = notifications.get(position);

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
        NotificationManagerFYEE.getInstance(this).updateNotifications(notifications); // Update the NotificationManager if needed
    }
}