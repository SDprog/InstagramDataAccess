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

import java.util.List;

public class InstagramAccessibilityService extends AccessibilityService {
    private final String TAG = "My logs";

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "service running");
        Log.d(TAG, "onServiceConnected");
//        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
//        info.flags = AccessibilityServiceInfo.DEFAULT |
//                AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS |
//                AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
//
//
//        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED;
////        info.eventTypes = AccessibilityEvent.TYPE_VIEW_SCROLLED;
////        info.eventTypes = AccessibilityEvent.TYPE_VIEW_SELECTED;
////        info.eventTypes = AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;
////        info.eventTypes = AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED;
////        info.eventTypes = AccessibilityEvent.TYPE_VIEW_FOCUSED;
//
//        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
//        info.packageNames = new String[]{"com.instagram.android"};
//        setServiceInfo(info);
        startApp();


    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        //getText();
        debugClick(event);

    }


    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt");

    }

    private void debugClick(AccessibilityEvent event) {
//    Log.d(TAG, "debugClick event " + event.toString());
//    Log.d(TAG, "debugClick getItemCount " + event.getItemCount());
//    Log.d(TAG, "debugClick getSource " + event.getSource());
//    Log.d(TAG, "debugClick getWindowId " + event.getWindowId());
//    Log.d(TAG, "debugClick getCurrentItemIndex " + event.getCurrentItemIndex());
//    Log.d(TAG, "debugClick getContentChangeTypes " + event.getContentChangeTypes());
//    Log.d(TAG, "*******************************************************************");
        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            AccessibilityNodeInfo nodeInfo = event.getSource();
//      Log.d(TAG, "nodeInfo: " + nodeInfo.toString());
            if (nodeInfo == null) {
                return;
            }
            nodeInfo.refresh();
            Log.d(TAG, "ClassName:" + nodeInfo.getClassName() +
                    " Text:" + nodeInfo.getText() +
                    " ViewIdResourceName:" + nodeInfo.getViewIdResourceName() +
                    " isClickable:" + nodeInfo.isClickable());
        }
    }


//    public void getText() {
//            AccessibilityNodeInfo nodeInfo = new AccessibilityNodeInfo();
//            nodeInfo.findAccessibilityNodeInfosByViewId(String.valueOf(R.string.instagram_username));
//            nodeInfo.getText();
//        Log.d(TAG, "Text:" + nodeInfo.getText());
//        }


    private void startApp() {
        Log.d(TAG, "startApp");
        // Указываем пакет нужного приложения
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        // Запуск из нужного места без предыстории приложения
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(launchIntent);

    }
}
