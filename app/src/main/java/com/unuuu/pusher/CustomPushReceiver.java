package com.unuuu.pusher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kashima on 15/07/26.
 */
public class CustomPushReceiver extends ParsePushBroadcastReceiver {

    protected void onPushOpen(Context context, Intent intent) {
        Log.d("CustomPushReceiver", "onPushOpen");

        Bundle bundle = intent.getExtras();

        //遷移先のActivityを設定
        Intent i = new Intent(context,MainActivity.class);
        i.putExtras(bundle);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

    protected void onPushReceive(Context context, Intent intent) {
        String message = "";
        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));
            Log.d("PUSHER", pushData.toString());
            message = pushData.getString("alert");
        } catch (JSONException var7) {
            Log.e("PUSHER", "Unexpected JSONException when receiving push data: ", var7);
        }

        if (message.equals("")) {
            return;
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
