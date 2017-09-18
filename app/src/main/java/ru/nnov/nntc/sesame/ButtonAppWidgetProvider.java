package ru.nnov.nntc.sesame;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Anton Alexeev on 13/09/2017.
 */

public class ButtonAppWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.button_appwidget);
            views.setOnClickPendingIntent(R.id.open_button, buildButtonPendingIntent(context));

            pushWidgetUpdate(context, views);
        }
    }

    public static PendingIntent buildButtonPendingIntent(Context context) {
        Intent i = new Intent();
        i.setAction("ru.nnov.nntc.sesame.SEND_OPEN_REQUEST");
        return PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        ComponentName myWidget = new ComponentName(context, ButtonAppWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }
}
