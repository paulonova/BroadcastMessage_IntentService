package com.example.android.restful;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.restful.services.MyService;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    TextView output;


    /**So now whenever the message is received by the broadcast receiver,
     * I'll simply display the message in my text view*/
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MyService.MY_SERVICE_PAYLOAD);
            // aqui posso salvar a mensagem onde eu quiser
            output.append(message + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);
        /**register to listen for the message using an intent filter that looks
         *  for the message identifier string*/
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mBroadcastReceiver, new IntentFilter(MyService.MY_SERVICE_MESSAGE));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**Need to unregister the BroadcastReceiver*/
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
    }

    public void runClickHandler(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.setData(Uri.parse(JSON_URL));
        startService(intent);
        startService(intent);
        startService(intent);
    }

    public void clearClickHandler(View view) {
        output.setText("");
    }

}
