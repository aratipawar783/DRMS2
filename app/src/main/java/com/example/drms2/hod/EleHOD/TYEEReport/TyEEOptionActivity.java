package com.example.drms2.hod.EleHOD.TYEEReport; // Change this to your actual package name

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.drms2.NotificationAdapterTYEE;
import com.example.drms2.NotificationItemTYEE;
import com.example.drms2.NotificationManagerTYEE;
import com.example.drms2.R;

import java.util.Calendar;
import java.util.List;

public class TyEEOptionActivity extends AppCompatActivity {

    private String[] menuItems = {"View Today's Report", "History"};
    private Button sendNotificationButton, rejectReport;
    private ImageButton displayNotificationButton;
    private ListView notificationListView;

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
                    Intent intent = new Intent(TyEEOptionActivity.this, ViewTodayReportbyEETYHOD.class);
                    startActivity(intent);
                } else if (position == 1) {
                    // Handle History menu item click
                    Intent intent = new Intent(TyEEOptionActivity.this, TYEEHistoryActivity.class);
                    startActivity(intent);
                }

            }
        });
        sendNotificationButton = findViewById(R.id.sendNotificationButton);
        displayNotificationButton = findViewById(R.id.displayNotificationButton);
        notificationListView = findViewById(R.id.notificationListView);
        rejectReport = findViewById(R.id.rejectReport);

        // Set up the click listener for the reject report button
        rejectReport.setOnClickListener(view -> showDatePickerDialog("Report Rejected"));

        // Send notification when the sendNotificationButton is clicked
        sendNotificationButton.setOnClickListener(view -> showDatePickerDialog("Report Accepted"));

        // Start NotificationListActivity when the displayNotificationButton is clicked
        displayNotificationButton.setOnClickListener(view -> {
            Intent intent = new Intent(TyEEOptionActivity.this,TYEEListNotifications.class);
            startActivity(intent);
        });

        // Handle item click in ListView to navigate
        notificationListView.setOnItemClickListener((parent, view, position, id) -> {
            NotificationItemTYEE clickedNotification = NotificationManagerTYEE.getInstance(TyEEOptionActivity.this).getNotifications().get(position);
            Intent intent = new Intent(TyEEOptionActivity.this, clickedNotification.getActivityClass());
            startActivity(intent);
        });
    }

    private void showDatePickerDialog(String status) {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            // Format the selected date
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear; // Month is 0-based
            sendNotification("Notification from HOD [Electrical Engineering]", status + " on " + date);
        }, year, month, day);

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

    private void sendNotification(String title, String message) {
        // Add the notification to the list (this simulates sending the notification)
        NotificationItemTYEE notificationItem = new NotificationItemTYEE(title, message,com.example.drms2.admin.adminEE.adminEETY.AdminEETYOptions.class);
        NotificationManagerTYEE.getInstance(TyEEOptionActivity.this).addNotification(notificationItem);
        Toast.makeText(this, "Notification sent: " + message, Toast.LENGTH_SHORT).show(); // Show a toast for confirmation
    }

    private void displayNotifications() {
        // Get the notifications from NotificationManager
        List<NotificationItemTYEE> notifications = NotificationManagerTYEE.getInstance(TyEEOptionActivity.this).getNotifications();
        // Create and set the custom adapter for the ListView
        NotificationAdapterTYEE adapter = new NotificationAdapterTYEE(this, notifications);
        notificationListView.setAdapter(adapter);
    }
}



