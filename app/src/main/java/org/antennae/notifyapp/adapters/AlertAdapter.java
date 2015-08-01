package org.antennae.notifyapp.adapters;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import org.antennae.gcmtests.gcmtest.R;
import org.antennae.notifyapp.model.Alert;
import org.antennae.notifyapp.model.AlertSeverityEnum;

public class AlertAdapter extends ArrayAdapter<Alert> {

    public AlertAdapter(Context context, List<Alert> users) {
        super(context, R.layout.layout_message, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Alert alert = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_message, parent, false);
        }

        // Lookup view for data population
        TextView tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        View vwSeverity = (View) convertView.findViewById(R.id.vwSeverity);
        vwSeverity.setBackgroundColor(convertToSeverity(alert).getColor());

        // Populate the data into the template view using the data object
        tvMessage.setText(alert.getTitle());
        tvTime.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date()));

        // vwSeverity.setBackgroundColor(Color.GREEN);
        // Return the completed view to render on screen
        return convertView;
    }

    public AlertSeverityEnum convertToSeverity(Alert alert) {

        if("UNDER WATCH".equalsIgnoreCase(alert.getSeverity())) {
            return AlertSeverityEnum.UNDER_WATCH;
        } else {
            return AlertSeverityEnum.valueOf(alert.getSeverity());
        }
    }
}
