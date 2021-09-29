package ru.whalemare.sheetmenu.library

/*
  MapUtil exposes a set of helper methods for working with
  ReadableMap (by React Native), Map<String, Object>, and JSONObject.
 */

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.facebook.react.bridge.WritableMap
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

fun ReadableMap?.toJSONObject(): JSONObject {
    val readableMap = if (this === null) Arguments.createMap() else this
    val jsonObject = JSONObject()

    val iterator = readableMap.keySetIterator()

    while (iterator.hasNextKey()) {
      val key = iterator.nextKey()
      val type = readableMap.getType(key)

      when (type) {
        ReadableType.Null -> jsonObject.put(key, null)
        ReadableType.Boolean -> jsonObject.put(key, readableMap.getBoolean(key))
        ReadableType.Number -> jsonObject.put(key, readableMap.getDouble(key))
        ReadableType.String -> jsonObject.put(key, readableMap.getString(key))
        ReadableType.Map -> jsonObject.put(key, readableMap.getMap(key)?.toJSONObject())
        ReadableType.Array -> jsonObject.put(key, readableMap.getArray(key)?.toJSONArray())
      }
    }

    return jsonObject
  }

  fun JSONObject?.toMap(): Map<String, Any> {
    val jsonObject = this ?: JSONObject()
    val map = HashMap<String, Any>()
    val iterator = jsonObject.keys()

    while (iterator.hasNext()) {
      val key = iterator.next()
      val value = jsonObject.get(key)

      if (value is JSONObject) {
        map[key] = value.toMap()
      }
      if (value is JSONArray) {
        map[key] = value.toList()
      }
    }

    return map
  }

  fun ReadableMap?.toMap(): Map<String, Any> {
    val readableMap = this ?: Arguments.createMap()
    val map = mutableMapOf<String, Any>()
    val iterator = readableMap.keySetIterator()

    while (iterator.hasNextKey()) {
      val key = iterator.nextKey()
      val type = readableMap.getType(key)

      when (type) {
//        ReadableType.Null -> map[key] = null
        ReadableType.Boolean -> map[key] = readableMap.getBoolean(key)
        ReadableType.Number -> map[key] = readableMap.getDouble(key)
        ReadableType.String -> map[key] = readableMap.getString(key) ?: ""
        ReadableType.Map -> map[key] = readableMap.getMap(key)?.toMap() ?: emptyMap<String, Any>()
        ReadableType.Array -> map[key] = readableMap.getArray(key)?.toList() ?: emptyList<Any>()
      }
    }

    return map
  }

  fun Map<String, Any>.toWritableMap(): WritableMap {
    val writableMap = Arguments.createMap()
    val iterator = entries.iterator()

    while (iterator.hasNext()) {
      val pair = iterator.next() as Map.Entry<*, *>
      val value = pair.value

      if (value == null) {
        writableMap.putNull(pair.key as String)
      } else if (value is Boolean) {
        writableMap.putBoolean(pair.key as String, value)
      } else if (value is Double) {
        writableMap.putDouble(pair.key as String, value)
      } else if (value is Int) {
        writableMap.putInt(pair.key as String, value)
      } else if (value is String) {
        writableMap.putString(pair.key as String, value)
      } else if (value is Map<*, *>) {
        writableMap.putMap(pair.key as String, (value as Map<String, Any>).toWritableMap())
      } else if (value!!.javaClass != null && value.javaClass.isArray) {
        writableMap.putArray(pair.key as String, (value as Array<Any>).toWritableArray())
      }
    }

    return writableMap
}