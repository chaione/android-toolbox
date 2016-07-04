package com.moldedbits.android.toolbox.utils.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility class to get internet connection changed status as it is changed
 * to be used like a normal broadcast receiver
 *
 * Registering this receiver in an activity with intent filter as following
 * mConnectionReceiver = registerReceiver(mReceiver, new IntentFilter(INTENT_CONNECTIVITY_CHANGE));
 *
 * Unregistering
 * unregisterReceiver(mConnectionReceiver)
 *
 * Created by abhishek on 04/07/16.
 */
public class ConnectionStatusReceiver extends BroadcastReceiver {

    /**
     * Receiver calling component to implement following interface
     */
    public interface OnConnectionChangedListener {
        void onInternetConnected();
        void onInternetDisconnected();
    }

    private final OnConnectionChangedListener mConnectionChangedListener;
    public static final String INTENT_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

    public ConnectionStatusReceiver(OnConnectionChangedListener listener) {
        if (listener == null)
            throw new IllegalArgumentException("Connection changed listener cannot be null");

        mConnectionChangedListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(INTENT_CONNECTIVITY_CHANGE)) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                // Network is available
                mConnectionChangedListener.onInternetConnected();
            } else {
                // No network
                mConnectionChangedListener.onInternetDisconnected();
            }
        }
    }
}
