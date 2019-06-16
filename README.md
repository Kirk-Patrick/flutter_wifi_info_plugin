# wifi_info_plugin

Wifi info wrapper android plugin

## Getting Started
This Plugin is currently only supports android. IOS implementation to be release soon.
Import the plugin.
Listed are all the supported getter methods to query and retrieve Network Information on your android device.

Example instantiation code:

 Future<void> initPlatformState() async {
    WifiInfoWrapper wifiObject;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      wifiObject = await  WifiInfoPlugin.wifiDetails; //assign result from async call

    }
    on PlatformException{

      // handle exception
    }
    if (!mounted) return;

    setState(() {

      _wifiObject = wifiObject;  // set returned native code data to widget local state.
    });
  }

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
   
   







