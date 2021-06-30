package com.example.flutter_jitsi

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import org.jitsi.meet.sdk.*
import java.net.URL

class MainActivity : FlutterActivity()  {
  private val CHANNEL = "samples.flutter.dev/battery"

  override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
    super.configureFlutterEngine(flutterEngine)
    MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
        call,
        result ->
      // Note: this method is invoked on the main thread.
      if (call.method == "getBatteryLevel") {
          val options = JitsiMeetConferenceOptions.Builder()
      .setServerURL(URL("https://meet.jit.si"))
      .setRoom("test123")
      .setAudioMuted(false)
      .setVideoMuted(false)
      .setAudioOnly(false)
      .setWelcomePageEnabled(false)
      .build();
        JitsiMeetActivity.launch(this, options)

        // val intent : Intent = Intent(this, SecondActivity::class.java) // cach di chuyen giua cac man hinh
        // startActivity(intent) // cach di chuyen giua cac man hinh

//        val batteryLevel = getBatteryLevel()
//        showName("khang")
//        if (batteryLevel != -1) {
//
//          result.success(batteryLevel)
//        } else {
//          result.error("UNAVAILABLE", "Battery level not available.", null)
//        }
      } else {
        result.notImplemented()
      }
      // ! khai bao bien va kieu du lieu
      // var a : String = ""
      // a = "khang"
      // val b : String = "abc"
      // b = "khang"  // var duoc set kieu val khong duoc set kieu

      // ! ? neu muon gia tri do co kha nang null va ! bat buoc khong null
      // var a : String? = null;
      // var a : String = "ab"!;

      // ! WHEN
      // var a: Int = 15
      // when (a) {
      //   in 1..3 -> Log.d("aaa", "Q1") // in 1..3 -> khoang tu 1 den 3
      // else -> Log.d("aaa", "chay vao else")
      // }

      // ! while
      // var a: Int = 0
      // while (a < 10) {
      //   Log.d("aaa", "hello " + a)
      //   a++
      // }

      // ! for cai tien trong kolin
      // for(i in 0..10) {
      //   Log.d("aaa", "hello" + i)
      // }
      // var a : Int = 5
      // for(i in 0 until a) { // until tuc la lay gia tri cua a - 1 roi ket thuc vong lap
      //   Log.d("aaa", "hello" + i)
      // }
      // var a : Int = 5
      // for(i in a downTo 0) { // downTo tuc la i chay tu a -> 0
      //   Log.d("aaa", "hello" + i)
      // }
      // var a : Int = 10
      // for(i in 0..a step 2) { // moi lan lap i nhay len 2 gia tri tuc la 0 - 2 - 4...
      //   Log.d("aaa", "hello" + i)
      // }

      // ! Array
      // ? cach 1
      // var mangso : IntArray = intArrayOf(1,2,3,4,5,6,7,8,9) // kotlin khong co String array
      // mangso.get(1)
      // ? cach 2
      // var mangten : List<String> = listOf("khang", "hoa", "huy")
      // mangten.get(1)
      // ? cach 3
      // var mangten : ArrayList<String> = ArrayList()
      // them phan tu cho mang
      // mangten.add("khang")
      // mangten.add("khang1")
      // mangten.add("khang2")
      // kiem tra so luong phan tu trong mang
      // mangten.size
      // remove phan tu trong mang
      // mangten.remove("hoa")
      // mangten.removeAt(3)
      // sua gia tri
      // mangten.set(2, "khang3")

    }
  }

  // ! Func
  private fun showName(name: String) {
    var sv: SinhVien = SinhVien(name, "abccc", 2000)
    Log.d("func", "func run name " + sv.getNamSinh())
  }

  private fun getBatteryLevel(): Int {
    val batteryLevel: Int
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
      batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    } else {
      val intent =
          ContextWrapper(applicationContext)
              .registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
      batteryLevel =
          intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 /
              intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
    }

    return batteryLevel
  }
}
