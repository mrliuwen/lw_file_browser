package com.ash.file_browser

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

import android.os.Environment


class FileBrowserPlugin: MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "file_browser")
      channel.setMethodCallHandler(FileBrowserPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if(call.method == "getExternalStorageDirectory"){
      var externalStorageDirectory = this.getExternalStorageDirectory();
      result.success(externalStorageDirectory);
    } else {
      result.notImplemented()
    }
  }

  fun getExternalStorageDirectory() :String {
      return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
}
