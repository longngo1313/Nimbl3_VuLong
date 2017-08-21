package test.simple.com.nimbl3.ultis;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by NGUYEN VU LONG on 8/21/2017.
 */
public class Utils {

    // Check Mobile Internet and Wifi Connected or Connecting
    public static boolean checkNetworkConnectStatus(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}
