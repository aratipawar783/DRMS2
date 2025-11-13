package com.example.drms2.principal.EJ.EJTY; // Change this to your actual package name

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.NotificationAdapterTYEJ;
import com.example.drms2.NotificationItemTYEJ;
import com.example.drms2.NotificationManagerTYEJ;
import com.example.drms2.R;

import java.util.Calendar;
import java.util.List;

public class PrincipalJTyOption extends AppCompatActivity {

    private String[] menuItems = {"View Today's Report", "History"," View Feedback "};
    private Button sendNotificationButton, rejectNotification;
    private ImageButton displayNotificationButton;
    private ListView notificationListView;
    private String notificationStatus; // To hold the status (Accepted/Rejected)
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_option);

        ListView menuListView = findViewById(R.id.menuListView);

        // Create an ArrayAdapter to display the menu items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
        menuListView.setAdapter(adapter);

        // Set an item click listener for the ListView
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Handle New Report menu item click
                    Intent intent = new Intent(PrincipalJTyOption.this,com.example.drms2.principal.EJ.EJTY.ViewTodayReportEJTy.class);
                    startActivity(intent);
                } else if (position == 1) {
                    // Handle History menu item click
                    Intent intent = new Intent(PrincipalJTyOption.this,com.example.drms2.principal.EJ.EJTY.ViewHistoryEJTy.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    // Handle History menu item click
                    Intent intent = new Intent(PrincipalJTyOption.this,com.example.drms2.principal.EJ.EJTY.ViewFeedbackEJTy.class);
                    startActivity(intent);
                }
            }
        });
        sendNotificationButton = findViewById(R.id.sendNotificationButton);
        rejectNotification = findViewById(R.id.rejectReport);
        displayNotificationButton = findViewById(R.id.displayNotificationButton);
        notificationListView = findViewById(R.id.notificationListView);

        // Send notification when the sendNotificationButton is clicked
        sendNotificationButton.setOnClickListener(view -> {
            notificationStatus = "Accepted"; // Set status for accepted notification
            showDatePickerDialog();
        });

        rejectNotification.setOnClickListener(view -> {
            notificationStatus = "Rejected"; // Set status for rejected notification
            showDatePickerDialog();
        });

        // Start NotificationListActivity when the displayNotificationButton is clicked
        displayNotificationButton.setOnClickListener(view -> {
            Intent intent = new Intent(PrincipalJTyOption.this, PrincipalTYEJListNotifications.class);
            startActivity(intent);
        });

        // Handle item click in List View to show dialog for close/remove
        notificationListView.setOnItemClickListener((parent, view, position, id) -> {
            showNotificationDialog(position);
        });
    }

    private void showDatePickerDialog() {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            // Format the selected date
            selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear; // Month is 0-based
            sendNotification("Notification from Principal Sir", "TYEJ Report " + notificationStatus + " on " + selectedDate);
        }, year, month, day);

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

    private void sendNotification(String title, String message) {
        // Add the notification to the list (this simulates sending the notification)
        NotificationItemTYEJ notificationItem = new NotificationItemTYEJ(title, message, com.example.drms2.hod.EJHOD.TYEJReport.TyEJOptionActivity.class);
        NotificationManagerTYEJ.getInstance(PrincipalJTyOption.this).addNotification(notificationItem);
        Toast.makeText(this, "Notification sent: " + message, Toast.LENGTH_SHORT).show(); // Show a toast for confirmation
    }

    private void showNotificationDialog(int position) {
        // Create a dialog with options to "Close" or "Remove"
        new AlertDialog.Builder(this)
                .setMessage("Do you want to remove this notification?")
                .setPositiveButton("Remove", (dialog, which) -> {
                    // Remove the notification from the list
                    NotificationManagerTYEJ.getInstance(PrincipalJTyOption.this).getNotifications().remove(position);
                    Toast.makeText(PrincipalJTyOption.this, "Notification removed", Toast.LENGTH_SHORT).show();
                    // Refresh the ListView
                    refreshNotificationList();
                })
                .setNegativeButton("Close", (dialog, which) -> {
                    // Just close the dialog
                    dialog.dismiss();
                })
                .show();
    }

    private void refreshNotificationList() {
        // Refresh the ListView with updated notifications
        List<NotificationItemTYEJ> notifications = NotificationManagerTYEJ.getInstance(PrincipalJTyOption.this).getNotifications();
        NotificationAdapterTYEJ adapter = new NotificationAdapterTYEJ(this, notifications);
        notificationListView.setAdapter(adapter);
    }
}



