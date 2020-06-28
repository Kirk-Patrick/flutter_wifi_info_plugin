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


  <p>To help support this package donations are welcome here</p>

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=28X789TZQ7AX8)

<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
<input type="hidden" name="cmd" value="_s-xclick" />
<input type="hidden" name="hosted_button_id" value="28X789TZQ7AX8" />
<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" border="0" name="submit" title="PayPal - The safer, easier way to pay online!" alt="Donate with PayPal button" />
<img alt="" border="0" src="https://www.paypal.com/en_JM/i/scr/pixel.gif" width="1" height="1" />
</form>











