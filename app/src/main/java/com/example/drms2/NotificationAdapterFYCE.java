package com.example.drms2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapterFYCE extends ArrayAdapter<NotificationItemFYCE> {

    private Context context;
    private List<NotificationItemFYCE> notificationList;

    public NotificationAdapterFYCE(Context context, List<NotificationItemFYCE> notificationList) {
        super(context, 0, notificationList);
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        NotificationItemFYCE notificationItem = notificationList.get(position);

        TextView titleTextView = convertView.findViewById(android.R.id.text1);
        TextView messageTextView = convertView.findViewById(android.R.id.text2);

        titleTextView.setText(notificationItem.getTitle());
        messageTextView.setText(notificationItem.getMessage());

        return convertView;
    }
}
