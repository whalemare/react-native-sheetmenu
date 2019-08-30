package ru.whalemare.rn.library

import android.graphics.drawable.Drawable
import android.util.Log
import com.facebook.react.bridge.*
import ru.whalemare.sheetmenu.ActionItem
import ru.whalemare.sheetmenu.SheetMenu
import java.util.*

class NativeLibrary(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  // With this name your library will be available from ReactNative
  override fun getName() = this.javaClass.simpleName

  var sheetMenu: SheetMenu? = null

  override fun getConstants(): Map<String, Any> {
    // Export any constants to be used in your native module
    // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
    val constants = HashMap<String, Any>()
    constants["EXAMPLE_CONSTANT"] = "example"

    return constants
  }

  @ReactMethod
  fun show(config: ReadableMap?, onPress: Callback) {
    Log.d("NativeLibrary: show", config.toString())
    val title = config?.getString("title")
    val actions = config?.getArray("actions").toActionItems()
    sheetMenu = SheetMenu(title, actions, onClick = { item ->
      onPress(item.id)
    })
    sheetMenu?.show(currentActivity!!)
  }

  @ReactMethod
  fun getValue(promise: Promise) {
    promise.resolve("A real native value")
  }

  @ReactMethod
  fun getParams(promise: Promise?) {
    val map = Arguments.createMap()
    map.putInt("number", 1)
    map.putString("value", "number one")
    promise?.resolve(map)
  }

  fun ReadableArray?.toActionItems(): List<ActionItem> {
    return this?.toList()?.mapIndexed { index, item ->
      val map = item as Map<String, Any>
      val title = map["title"] as? String ?: ""
      val iconName = map["iconName"] as? String
      val image = if (iconName == null) null else iconByName(iconName)
      ActionItem(index, title, image)
    } ?: emptyList()
  }

  fun iconByName(name: String): Drawable? {
    val resources = reactApplicationContext.resources
    val drawableResId = resources.getIdentifier(name, "drawable", reactApplicationContext.packageName)
    if (drawableResId != 0) {
      return resources.getDrawable(drawableResId)
    }

    val mipmapResId = resources.getIdentifier(name, "mipmap", reactApplicationContext.packageName)
    if (mipmapResId != 0) {
      return resources.getDrawable(mipmapResId)
    }

    return null
  }

}