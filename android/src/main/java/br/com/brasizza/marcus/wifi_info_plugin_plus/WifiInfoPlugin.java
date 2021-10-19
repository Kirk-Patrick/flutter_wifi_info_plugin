package br.com.brasizza.marcus.wifi_info_plugin_plus;


import android.content.Context;
// import android.content.ContextWrapper;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * WifiInfoPlugin
 */
public class WifiInfoPlugin implements MethodCallHandler,FlutterPlugin {
    /**
     * Plugin registration.
     */

    private Context  context;

  private MethodChannel channel;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "wifi_info_plugin");
        context = flutterPluginBinding.getApplicationContext();
        channel.setMethodCallHandler(this);
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

                Map<String, Object> data = new HashMap<String, Object>();
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

    @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
