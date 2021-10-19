#import "WifiInfoPlugin.h"
#import <wifi_info_plugin_plus/wifi_info_plugin_plus-Swift.h>

@implementation WifiInfoPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftWifiInfoPlugin registerWithRegistrar:registrar];
}
@end
