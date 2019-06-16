import 'dart:async';
import 'package:flutter/services.dart';

class WifiInfoPlugin {
  static const MethodChannel _channel = const MethodChannel('wifi_info_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  ///
  /// Wifi details  represents a case for a method that can be called in the method handler
  ///
  static Future<WifiInfoWrapper> get wifiDetails async {
    final Map<dynamic, dynamic> data =
        await _channel.invokeMethod('getWifiDetails');

    WifiInfoWrapper wifiWrapper = new WifiInfoWrapper.withMap(data);
    return wifiWrapper;
  }
}

/// represents a wrapper object for mainly android wifi info class
class WifiInfoWrapper {
  String _bssid = "missing";
  String _ssid = "missing";
  String _ip = "missing";
  String _macAddress = "missing";
  int _linkSpeed = 0;
  int _singalStrength = 0;
  int _frequency = 0;
  int _networkid = 0;
  String _connectionType = "missing";
  bool _isHiddenSSID = false;
  String _routerIp = "unknown";
  String _dns1Ip = "";
  String _dns2Ip = "";

  WifiInfoWrapper();

  WifiInfoWrapper.withMap(Map<dynamic, dynamic> nativeInfo) {
    if (nativeInfo != null) {
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

  /// IPV4 address for connected device
  String get ipAddress {
    return this._ip;
  }

  /// IPV4 address for router of the connected device
  String get routerIp {
    return this._routerIp;
  }

  /// IPV4 address of the dns1 server
  String get dns1 {
    return this._dns1Ip;
  }

  /// IPV4 address of the dns2 server
  String get dns2 {
    return this._dns2Ip;
  }

  /// returns BSSID
  String get bssId {
    return this._bssid;
  }

  /// returns the Name of the Network
  String get ssid {
    return this._ssid;
  }

  ///returns device Mac Address
  String get macAddress {
    return this._macAddress;
  }

  /// returns current link Speed
  int get linkSpeed {
    return this._linkSpeed;
  }

  /// returns current signalStrength
  int get signalStrength {
    return this._singalStrength;
  }

  /// returns current frequency
  int get frequency {
    return this._frequency;
  }

  /// returns current Id of your network

  int get networkId {
    return this._networkid;
  }

  /// returns connection type eg wifi or mobile
  String get connectionType {
    return this._connectionType;
  }

  /// return boolean for  ssid  is hidden
  bool get isHiddenSSid {
    return this._isHiddenSSID;
  }
}
