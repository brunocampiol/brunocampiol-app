package com.brunocampiol.app;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Change toolbar title color programmatically if needed
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        // Setup button click listener
        Button buttonAbout = findViewById(R.id.button_about);
        buttonAbout.setOnClickListener(v ->
                {
                    String versionInfo = getVersionInfo();
                    Toast.makeText(MainActivity.this, versionInfo, Toast.LENGTH_LONG).show();
                }
        );
    }

    private String getVersionInfo() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;
            return "Version: " + versionName + " (" + versionCode + ")";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Version info not available";
        }
    }
}