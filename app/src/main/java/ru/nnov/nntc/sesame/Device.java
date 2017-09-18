package ru.nnov.nntc.sesame;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.provider.Settings;

/**
 * Created by Anton Alexeev on 13/09/2017.
 */

class Device {
    static String getAndroidId(Context ctx) {
        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    static void copyToClipboard(Context ctx, String label, String text) {
        ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }
}
