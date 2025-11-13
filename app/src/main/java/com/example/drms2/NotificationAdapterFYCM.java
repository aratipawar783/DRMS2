package com.example.drms2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapterFYCM extends ArrayAdapter<NotificationItemFYCM> {

    private Context context;
    private List<NotificationItemFYCM> notificationList;

    public NotificationAdapterFYCM(Context context, List<NotificationItemFYCM> notificationList) {
        super(context, 0, notificationList);
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        NotificationItemFYCM notificationItem = notificationList.get(position);

        TextView titleTextView = convertView.findViewById(android.R.id.text1);
        TextView messageTextView = convertView.findViewById(android.R.id.text2);

        titleTextView.setText(notificationItem.getTitle());
        messageTextView.setText(notificationItem.getMessage());

        return convertView;
    }
}
