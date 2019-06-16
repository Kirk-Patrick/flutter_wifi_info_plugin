#import "WifiInfoPlugin.h"
#import <wifi_info_plugin/wifi_info_plugin-Swift.h>

@implementation WifiInfoPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftWifiInfoPlugin registerWithRegistrar:registrar];
}
@end
