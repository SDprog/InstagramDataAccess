package ru.developersementsov.instagramaccess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.developersementsov.instagramaccess.ui.MyDialogFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "My logs MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick(View view) {
        startApp();
    }

    private void startApp() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(launchIntent);
            Log.d(TAG, "startApp");
        } else {
            Toast.makeText(this, "Instagram на устройстве не установлен!",
                    Toast.LENGTH_LONG).show();
            Log.d(TAG, "Instagram на устройстве не установлен!");
        }
    }
}