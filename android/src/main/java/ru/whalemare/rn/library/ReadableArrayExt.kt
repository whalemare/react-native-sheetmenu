package ru.whalemare.rn.library

/*
  ArrayUtil exposes a set of helper methods for working with
  ReadableArray (by React Native), Object[], and JSONArray.
 */

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableType
import com.facebook.react.bridge.WritableArray
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

  @Throws(JSONException::class)
  fun ReadableArray.toJSONArray(): JSONArray {
    val jsonArray = JSONArray()

    for (i in 0 until this.size()) {
      val type = this.getType(i)

      when (type) {
        ReadableType.Null -> jsonArray.put(i, null)
        ReadableType.Boolean -> jsonArray.put(i, this.getBoolean(i))
        ReadableType.Number -> jsonArray.put(i, this.getDouble(i))
        ReadableType.String -> jsonArray.put(i, this.getString(i))
        ReadableType.Map -> jsonArray.put(i, this.getMap(i)?.toJSONObject())
        ReadableType.Array -> jsonArray.put(i, this.getArray(i)?.toJSONArray())
      }
    }

    return jsonArray
  }


  @Throws(JSONException::class)
  fun JSONArray.toList(): List<Any> {
    val array = mutableListOf<Any>()

    for (i in 0 until this.length()) {
      val value = this.get(i)

      if (value is JSONObject) {
        array.add(value.toMap())
      }
      if (value is JSONArray) {
         array.add(value.toList())
      }
    }

    return array
  }

  fun ReadableArray.toList(): List<Any> {
    val array = mutableListOf<Any>()

    for (i in 0 until this.size()) {
      val type = this.getType(i)

      when (type) {
//        ReadableType.Null -> array[i] = null
        ReadableType.Boolean -> array.add(this.getBoolean(i))
        ReadableType.Number -> array.add(this.getDouble(i))
        ReadableType.String -> array.add(this.getString(i) ?: "")
        ReadableType.Map -> array.add(this.getMap(i)?.toMap() ?: emptyMap<String, Any>())
        ReadableType.Array -> array.add(this.getArray(i)?.toList() ?: emptyList<String>())
      }
    }

    return array
  }

  fun Array<Any>.toWritableArray(): WritableArray {
    val writableArray = Arguments.createArray()

    for (i in this.indices) {
      val value = this[i]

      if (value == null) {
        writableArray.pushNull()
      }
      if (value is Boolean) {
        writableArray.pushBoolean(value)
      }
      if (value is Double) {
        writableArray.pushDouble(value)
      }
      if (value is Int) {
        writableArray.pushInt(value)
      }
      if (value is String) {
        writableArray.pushString(value)
      }
      if (value is Map<*, *>) {
        writableArray.pushMap((value as Map<String, Any>).toWritableMap())
      }
      if (value.javaClass.isArray) {
        writableArray.pushArray((value as Array<Any>).toWritableArray())
      }
    }

    return writableArray
  }
