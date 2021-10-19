package br.com.brasizza.marcus.wifi_info_plugin_plus;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.net.NetworkCapabilities;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;

public class WifiInfoWrapper {

    private Context context;
    private WifiInfo wifiInfo;

    public WifiInfoWrapper(Context context) {

        this.context = context;
        init(context);
    }


    private Boolean init(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
            wifiInfo = wifiManager.getConnectionInfo();
            return true;

        } else {
            return false;
        }


    }


    public String getRouterIp() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcp = wifiManager.getDhcpInfo();
        int ip = dhcp.gateway;
        String routerIp = formatIP(ip);
        return routerIp;
    }

    public String getDns1Ip() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcp = wifiManager.getDhcpInfo();
        int ip = dhcp.dns1;
        String routerIp = formatIP(ip);
        return routerIp;
    }

    public String getDns2Ip() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcp = wifiManager.getDhcpInfo();
        int ip = dhcp.dns2;
        String routerIp = formatIP(ip);
        return routerIp;
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    String getIpAddress() {
        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
        String ip = formatIP(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

    String getBssId() {
        return wifiInfo.getBSSID();
    }

    String getSSID() {
        return wifiInfo.getSSID();
    }

    public boolean getHiddenSSID() {
        return wifiInfo.getHiddenSSID();
    }

    int getLinkSpeedMbps() {
        return wifiInfo.getLinkSpeed();
    }

    int getFrequency() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return wifiInfo.getFrequency();
        } else {
            return 001;
        }
    }

    int getSignalStrength() {

        int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 10);
        int percentage = (int) ((level / 10.0) * 100);
        return level;

    }

    int getNetworkId() {
        return wifiInfo.getNetworkId();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    String getMacAdress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                 for (byte b : macBytes) {
                    res1.append(
                        String.format(
                            "%1$2s",
                            Integer.toHexString(b & 0xFF)
                        ).replace(' ','0') + ":"
                    );
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }

//        return wifiInfo.getMacAddress();


   





public String getNetworkConnectionType() {
    String                         result = "unknown";

    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (cm != null) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    result = "Mobile data";
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    result = "WIFI";
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    result = "VPN";
                }
            }
        }else{
                        result = "unknown";

        }
    } else {
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    result = "Mobile data";
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    result = "WIFI";
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_VPN) {
                    result = "VPN";
                }
            }
            result = "unknown";
        }
    }

        return result;

}




    private String formatIP(int ip) {
        return String.format(
                "%d.%d.%d.%d",
                (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff)
        );
    }
}