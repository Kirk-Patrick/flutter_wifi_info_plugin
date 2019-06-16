package com.vtechjm.wifi_info_plugin;


import android.content.Context;
import android.content.ContextWrapper;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * WifiInfoPlugin
 */
public class WifiInfoPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    static Context context;

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "wifi_info_plugin");
        context = registrar.context();
        channel.setMethodCallHandler(new WifiInfoPlugin());
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        WifiInfoWrapper wifiWrapper = new WifiInfoWrapper(context);
        switch (call.method) {
            case "getPlatformVersion": {
                result.success("An " + android.os.Build.VERSION.RELEASE);
            }
            break;
            case "getBSSID": {
//        String ss = wifiWrapper.getSSID();
//                result.success();

            }
            case "getWifiDetails": {

                Map<String, Object> data = new HashMap();
                data.put("SSID", wifiWrapper.getSSID());
                data.put("BSSID", wifiWrapper.getBssId());
                data.put("IP", wifiWrapper.getIpAddress());
                data.put("MACADDRESS", wifiWrapper.getMacAdress());
                data.put("LINKSPEED", wifiWrapper.getLinkSpeedMbps());
                data.put("SIGNALSTRENGTH", wifiWrapper.getSignalStrength());
                data.put("FREQUENCY", wifiWrapper.getFrequency());
                data.put("NETWORKID", wifiWrapper.getNetworkId());
                data.put("CONNECTIONTYPE", wifiWrapper.getNetworkConnectionType());
                data.put("ISHIDDEDSSID", wifiWrapper.getHiddenSSID());
                data.put("ROUTERIP", wifiWrapper.getRouterIp());
                data.put("DNS1", wifiWrapper.getDns1Ip());
                data.put("DNS2", wifiWrapper.getDns2Ip());

                result.success(data);


            }
            break;
            default:
                result.notImplemented();

        }


    }
}
