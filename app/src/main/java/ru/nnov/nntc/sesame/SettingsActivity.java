package ru.nnov.nntc.sesame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final String androidId = Device.getAndroidId(this);

        EditText androidIdField = findViewById(R.id.android_id);
        androidIdField.setText(androidId);
        androidIdField.setEnabled(false);

        findViewById(R.id.copy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Device.copyToClipboard(SettingsActivity.this, "Android ID", androidId);
                Toast.makeText(SettingsActivity.this, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
