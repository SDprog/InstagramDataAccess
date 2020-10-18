package ru.developersementsov.instagramaccess;


import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class InstagramAccessibilityService extends AccessibilityService {
    public static final String TAG = "My logs Service";


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
        startApp();
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        myObserver(event);

    }

    private boolean mySearch(AccessibilityNodeInfo nodeInfo) {
        List<AccessibilityNodeInfo> listProfile = nodeInfo.findAccessibilityNodeInfosByViewId("com.instagram.android:id/profile_tab");
        if (listProfile.size() > 0) {
            for (AccessibilityNodeInfo node : listProfile) {
                Log.d(TAG, "profileTab " + node.getViewIdResourceName());
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);

                List<AccessibilityNodeInfo> listUserName = nodeInfo.findAccessibilityNodeInfosByViewId("com.instagram.android:id/title_view");
                if (listUserName.size() > 0) {
                    for (AccessibilityNodeInfo nodeUser : listUserName) {
                        String accountName = nodeUser.getText().toString();
                        Log.d(TAG, "User name: " + nodeUser.getText() + ", accountName: " + accountName);
                        //parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }}}
            return true;
        } else
            return false;
    }


    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt");

    }

    private void myObserver(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            AccessibilityNodeInfo nodeInfo = event.getSource();
            if (nodeInfo == null) {
                return;
            }
            mySearch(nodeInfo);
            nodeInfo.refresh();
            Log.d(TAG, "ClassName:" + nodeInfo.getClassName() +
                    " Text:" + nodeInfo.getText() +
                    " ViewIdResourceName:" + nodeInfo.getViewIdResourceName() +
                    " isClickable:" + nodeInfo.isClickable());
        }
    }

    private void startApp() {
        Log.d(TAG, "startApp");
        // Указываем пакет нужного приложения
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        // Запуск из нужного места без предыстории приложения
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(launchIntent);
    }

}
