package org.antennae.notifyapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.antennae.gcmtests.gcmtest.R;
import org.antennae.notifyapp.model.Alert;
import org.antennae.notifyapp.model.AlertSeverityEnum;

public class MessageDetailActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        Intent intent = getIntent();

        Alert alert = (Alert) intent.getSerializableExtra(MainActivity.ALERT_PARAM);

        setTitle("");

        TextView tvTitleDetails = (TextView) findViewById(R.id.tvTitleDetail);
        tvTitleDetails.setText(alert.getTitle());


        TextView tvMessage = (TextView) findViewById(R.id.tvMessage);
        tvMessage.setText(alert.getMessage());

        TextView tvSeverityDetail = (TextView) findViewById(R.id.tvSeverityDetail);
        tvSeverityDetail.setBackgroundColor(AlertSeverityEnum.valueOf(alert.getSeverity()).getColor());

        TextView tvAction = (TextView) findViewById(R.id.tvAction);
        tvAction.setText(alert.getAction());

        Button btnDial = (Button) findViewById(R.id.btnDial);
        Button btnChat = (Button) findViewById(R.id.btnChat);
        TextView tvCallinInfo = (TextView) findViewById(R.id.tvCallinInfo);

        // TODO for now
        btnChat.setVisibility(Button.INVISIBLE);

        if(AlertSeverityEnum.shouldCall(alert)) {
            btnDial.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Uri number = Uri.parse("tel:4089676530");
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                }
            });

            btnChat.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View paramView) {
                    Alert alert = new Alert("title", "msg", "sev", "action");

                    // TODO: fix notificationsReceiver
                    //NotificationsReceiver.showNotification(MessageDetailActivity.this, alert);
                }
            });

        } else {
            btnDial.setVisibility(Button.GONE);
            tvCallinInfo.setVisibility(TextView.INVISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
