package com.vtechjm.wifi_info_plugin;


import android.content.Context;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.PluginRegistry.ViewDestroyListener;
import io.flutter.view.FlutterNativeView;

/**
 * WifiInfoPlugin
 */
public class WifiInfoPlugin implements FlutterPlugin {

    private MethodChannel channel;

    public static void registerWith(Registrar registrar) {
        final WifiInfoPlugin plugin = new WifiInfoPlugin();
        plugin.startListening(registrar.context(), registrar.messenger());

        registrar.addViewDestroyListener(new ViewDestroyListener() {
            @Override
            public boolean onViewDestroy(FlutterNativeView view) {
                plugin.stopListening();

                return false;
            }
        });
    }


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        startListening(binding.getApplicationContext(), binding.getBinaryMessenger());
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        stopListening();
    }

    private void startListening(Context context, BinaryMessenger messenger) {
        channel = new MethodChannel(messenger, "wifi_info_plugin");
        channel.setMethodCallHandler(new MethodCallHandlerImpl(context));
    }

    private void stopListening() {
        channel.setMethodCallHandler(null);
        channel = null;
    }


}
