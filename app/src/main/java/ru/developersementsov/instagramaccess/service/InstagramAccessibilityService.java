package ru.developersementsov.instagramaccess.service;


import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

import ru.developersementsov.instagramaccess.db.AccountRepository;
import ru.developersementsov.instagramaccess.model.Account;

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

    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt");
    }

    private void myObserver(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            AccessibilityNodeInfo nodeInfo = event.getSource();
            if (nodeInfo == null) {
                return;
            }
            mySearch(nodeInfo);
            nodeInfo.refresh();
//            Log.d(TAG, "ClassName:" + nodeInfo.getClassName() +
//                    " Text:" + nodeInfo.getText() +
//                    " ViewIdResourceName:" + nodeInfo.getViewIdResourceName() +
//                    " isClickable:" + nodeInfo.isClickable());
        }
    }

    private boolean mySearch(AccessibilityNodeInfo nodeInfo) {
        List<AccessibilityNodeInfo> listProfile = nodeInfo.findAccessibilityNodeInfosByViewId("com.instagram.android:id/profile_tab");
        if (listProfile.size() > 0) {
            for (AccessibilityNodeInfo node : listProfile) {
               // Log.d(TAG, "profileTab " + node.getViewIdResourceName());
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.d(TAG, "profileTab CLICK");
                List<AccessibilityNodeInfo> listUserName = nodeInfo.findAccessibilityNodeInfosByViewId("com.instagram.android:id/title_view");
                if (listUserName.size() > 0) {
                    for (AccessibilityNodeInfo nodeUser : listUserName) {
                        String accountName = nodeUser.getText().toString();
                       // Log.d(TAG, "accountName: " + accountName);
                        Account account = new Account(accountName);
                       // Log.d(TAG, "New account: " + account.getAccountName());
                        AccountRepository accountRepository = new AccountRepository(getApplication());
                        accountRepository.insert(account);
                        Log.d(TAG, "Сделана запись в БД: " + account.getAccountName());
                    }
                }
            }
            return true;
        }
        return false;
    }

    private void startApp() {
        // Указываем пакет нужного приложения
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (launchIntent != null) {
            // Запуск из нужного места без предыстории приложения
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(launchIntent);
            Log.d(TAG, "startApp");
        } else {
            Toast.makeText(this, "Instagram на устройстве не установлен!",
                    Toast.LENGTH_LONG).show();
            Log.d(TAG, "Instagram на устройстве не установлен!");
            onInterrupt();
        }
    }
}
