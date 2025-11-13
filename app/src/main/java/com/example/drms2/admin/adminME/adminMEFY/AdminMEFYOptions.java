package com.example.drms2.admin.adminME.adminMEFY; // Change this to your actual package name

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

import com.example.drms2.NotificationItemFYME;
import com.example.drms2.NotificationManagerFYME;
import com.example.drms2.R;

import java.util.Calendar;

public class AdminMEFYOptions extends AppCompatActivity {

    private String[] menuItems = {"New Report", "History","Feedback"};
    private Button sendNotificationButton;
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
                    Intent intent = new Intent(AdminMEFYOptions.this, AdminFYMENewReport.class);
                    startActivity(intent);
                } else if (position == 1) {
                    // Handle History menu item click
                    Intent intent = new Intent(AdminMEFYOptions.this, AdminFYMEHistory.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    // Handle History menu item click
                    Intent intent = new Intent(AdminMEFYOptions.this,FYMEFeedback.class);
                    startActivity(intent);
                }
            }
        });
        sendNotificationButton = findViewById(R.id.sendNotificationButton);
        displayNotificationButton = findViewById(R.id.displayNotificationButton);
        notificationListView = findViewById(R.id.notificationListView);
        Button reject=findViewById(R.id.rejectReport);
        sendNotificationButton.setText("Submit Report");
        reject.setVisibility(View.GONE);


        // Send notification when the sendNotificationButton is clicked
        sendNotificationButton.setOnClickListener(view -> showDatePickerDialog());

        // Start NotificationListActivity when the displayNotificationButton is clicked
        displayNotificationButton.setOnClickListener(view -> {
            Intent intent = new Intent(AdminMEFYOptions.this, AdminMEFYLIstNotifications.class);
            startActivity(intent);
        });

        // Handle item click in ListView to navigate
        notificationListView.setOnItemClickListener((parent, view, position, id) -> {
            NotificationItemFYME clickedNotification = NotificationManagerFYME.getInstance(AdminMEFYOptions.this).getNotifications().get(position);
            Intent intent = new Intent(AdminMEFYOptions.this, clickedNotification.getActivityClass());
            startActivity(intent);
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
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear; // Month is 0-based
            sendNotification("Notification from First Year CC [Mechanical Engineering]", "Report Submitted on " + date);
        }, year, month, day);

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

    private void sendNotification(String title, String message) {
        // Add the notification to the list (this simulates sending the notification)
        NotificationItemFYME notificationItem = new NotificationItemFYME(title, message, com.example.drms2.hod.MEHOD.FYMEReport.FyMEOptionActivity.class);
        NotificationManagerFYME.getInstance(AdminMEFYOptions.this).addNotification(notificationItem);
        Toast.makeText(this, "Notification sent: " + message, Toast.LENGTH_SHORT).show(); // Show a toast for confirmation
    }

}



