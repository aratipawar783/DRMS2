package com.example.drms2;

import java.io.Serializable;

public class NotificationItemFYCE implements Serializable {
    private String title;
    private String message;
    private String activityClassName; // Store the class name as a String

    public NotificationItemFYCE(String title, String message, Class<?> activityClass) {
        this.title = title;
        this.message = message;
        this.activityClassName = activityClass.getName(); // Store the class name
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    // Convert the stored class name back to a Class object
    public Class<?> getActivityClass() {
        try {
            return Class.forName(activityClassName); // Convert class name back to Class object
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
