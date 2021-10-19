# wifi_info_plugin_plus

Wifi info wrapper android plugin
This package is a branch of **wifi_info_plugin**
## Getting Started
This Plugin currently only supports android.

Below code exhibits usage of  the plugin in  a flutter application to retrieve Network info.

```dart
import 'package:flutter/services.dart';
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:wifi_info_plugin_plus/wifi_info_plugin_plus.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  WifiInfoWrapper? _wifiObject;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    WifiInfoWrapper? wifiObject;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      wifiObject = await WifiInfoPlugin.wifiDetails;
    } on PlatformException {}
    if (!mounted) return;

    setState(() {
      _wifiObject = wifiObject;
    });
  }

  @override
  Widget build(BuildContext context) {
    String ipAddress = _wifiObject != null ? _wifiObject!.ipAddress.toString() : "...";

    String macAddress = _wifiObject != null ? _wifiObject!.macAddress.toString() : '...';
    String connectionType = _wifiObject != null ? _wifiObject!.connectionType.toString() : 'unknown';

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
          Center(
            child: Text('Running on IP:' + ipAddress),
          ),
          Center(
            child: Text('Running on Mac:' + macAddress),
          ),
          Center(
            child: Text('Connection type:' + connectionType),
          ),
        ]),
      ),
    );
  }
}

```
Below are valid getters on the WifiWrapper Class at instantiation

  * ipAddress
  * routerIp
  * dns1
  * dns2
  * bssId
  * ssid
  * macAddress
  * linkSpeed
  * signalStrength
  * frequency
  * networkId
  * connectionType
  * isHiddenSSid












