import 'dart:async';
import 'dart:collection';

import 'package:flutter/services.dart';

class WifiInfoPlugin {
  static const MethodChannel _channel = const MethodChannel('wifi_info_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<WifiInfoWrapper> get wifiDetails async {
    final Map<dynamic, dynamic> data =
        await _channel.invokeMethod('getWifiDetails');

    WifiInfoWrapper wifiWrapper = new WifiInfoWrapper.withMap(data);
    return wifiWrapper;
  }
}

class WifiInfoWrapper{
  String _bssid ="missing";
  String _ssid="missing";
  String _ip="missing";
  String _macAddress="missing";
  int _linkSpeed=0;
  int _singalStrength=0;
  int _frequency=0;
  int _networkid=0;
  String _connectionType="missing";
  bool _isHiddenSSID=false;
  String _routerIp ="unknown";
  String _dns1Ip ="";
  String _dns2Ip ="";

  WifiInfoWrapper();
  WifiInfoWrapper.withMap(Map<dynamic,dynamic> nativeInfo)
  {
    if(nativeInfo!=null) {
      this._bssid = nativeInfo["BSSID"];
      this._ssid = nativeInfo["SSID"];
      this._ip = nativeInfo["IP"];
      this._macAddress = nativeInfo["MACADDRESS"];
      this._linkSpeed = nativeInfo["LINKSPEED"];
      this._singalStrength = nativeInfo["SIGNALSTRENGTH"];
      this._frequency = nativeInfo["FREQUENCY"];
      this._networkid = nativeInfo["NETWORKID"];
      this._connectionType = nativeInfo["CONNECTIONTYPE"];
      this._isHiddenSSID = nativeInfo["ISHIDDEDSSID"];
      this._routerIp = nativeInfo["ROUTERIP"];
      this._dns1Ip = nativeInfo["DNS1"];
      this._dns2Ip = nativeInfo["DNS2"];
    }
  }


  String get ipAddress
  {
    return this._ip;
  }

  String get routerIp
  {
    return this._routerIp;
  }
  String get dns1
  {
    return this._dns1Ip;
  }
  String get dns2
  {
    return this._dns2Ip;
  }
  String get bssId
  {
    return this._bssid;
  }


  String get ssid
  {
    return this._ssid;
  }
  String get macAddress
  {
    return this._macAddress;
  }

  int get linkSpeed
  {
    return this._linkSpeed;
  }

  int get signalStrength
  {
    return this._singalStrength;
  }

  int get frequency
  {
    return this._frequency;
  }

  int get networkId
  {
    return this._networkid;
  }

  String get connectionType
  {
    return this._connectionType;
  }

  bool get isHiddenSSid
  {
    return this._isHiddenSSID;
  }



}
