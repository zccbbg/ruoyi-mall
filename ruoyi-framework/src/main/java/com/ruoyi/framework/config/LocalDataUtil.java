package com.ruoyi.framework.config;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LocalDataUtil {

  private static ThreadLocal<Map<String, Object>> LOCAL_VARS = new ThreadLocal<Map<String, Object>>();

  public static void setVar(String key, Object obj) {
    Map<String, Object> stringObjectMap = LOCAL_VARS.get();
    if (null == stringObjectMap) {
      stringObjectMap = new HashMap<>();
      LOCAL_VARS.set(stringObjectMap);
    }
    stringObjectMap.put(key, obj);
  }

  public static Object getVar(String key) {
    Map<String, Object> stringObjectMap = LOCAL_VARS.get();
    return null != stringObjectMap ? stringObjectMap.get(key) : null;
  }

}
