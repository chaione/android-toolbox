package com.moldedbits.android.toolbox.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Collection of Network related general purpose methods
 * Created by abhishek on 04/07/16.
 */

public class UtilityMethods {

    /**
     * Synchronous way of determining if network is available
     * @return true if connection is available false otherwise
     * todo returns true if wifi is connected but internet is not
     */
    public static boolean isConnectionAvailable(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
