package ru.nnov.nntc.sesame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Anton Alexeev on 13/09/2017.
 */

public class ButtonAppWidgetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("ru.nnov.nntc.sesame.SEND_OPEN_REQUEST")){
            sendRequest(context);
            updateButtonListener(context);
        }
    }

    private void sendRequest(final Context ctx) {

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                String androidId = Device.getAndroidId(ctx);

                try {
                    URL url = new URL("http://nntc.gksu.me/test-sesame?androidId="+androidId);
                    HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                    httpCon.setRequestMethod("PUT");
                    httpCon.connect();
                    int resCode = httpCon.getResponseCode();
                    httpCon.disconnect();

                    if (resCode == 200) {
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean requestSuccessful) {
                String message = "";
                if (requestSuccessful) {
                    message = ctx.getString(R.string.request_successful);
                } else {
                    message = ctx.getString(R.string.request_fail);
                }
                Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void updateButtonListener(Context ctx) {
        RemoteViews views = new RemoteViews(ctx.getPackageName(), R.layout.button_appwidget);
        views.setOnClickPendingIntent(R.id.open_button, ButtonAppWidgetProvider.buildButtonPendingIntent(ctx));
        ButtonAppWidgetProvider.pushWidgetUpdate(ctx.getApplicationContext(), views);
    }
}
