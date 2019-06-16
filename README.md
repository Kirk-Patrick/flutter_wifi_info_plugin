# wifi_info_plugin

Wifi info wrapper android plugin

## Getting Started
This Plugin is currently only supports android. IOS implementation to be release soon.
Import the plugin.
Listed are all the supported getter methods to query and retrieve Network Information on your android device.

Below is an Example code of using the plugin in  flutter application to retrieve android only Network info.
The WifiInfoWrapper class contains methods for some of the most useful Network infomation to be requested.

```dart
import 'package:flutter/services.dart';
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:wifi_info_plugin/wifi_info_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  WifiInfoWrapper _wifiObject;


  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    WifiInfoWrapper wifiObject;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      wifiObject = await  WifiInfoPlugin.wifiDetails;

    }
    on PlatformException{

    }
    if (!mounted) return;

    setState(() {

      _wifiObject = wifiObject;
    });
  }

  @override
  Widget build(BuildContext context) {
   String ipAddress = _wifiObject!=null?_wifiObject.ipAddress.toString():"ip";
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on:'+ ipAddress),
        ),
      ),
    );
  }
}
```
  returns ipv4 address of device
  <String> ipAddress

   return the ipv4 address of the router for  a connection
  <String> routerIp

   returns the DNS server1 IpAddress
  <String> dns1

   returns the DNS server2 IpAddress
  <String> dns2

   returns the(BSSID) MAC address of the wireless access point
  <String>bssId

   returns the(SSID) Network Name
  <String> ssid

   returns mac adress of device
  <String> macAddress

   returns the current link speed
  <int>linkSpeed

   returns current signal strength 10 being the maximum
  <int> signalStrength

  returns current frequency in MHZ
  <int> frequency

  returns unique small integer ID for the connected network
  <int> networkId

   returns  device connection Type Wifi or Mobile
   <String> connectionType

   returns true/false determining if the connection is a hidden Ssid
   <bool> get isHiddenSSid









