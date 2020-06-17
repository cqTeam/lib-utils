package com.cqteam.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import com.cqteam.lib.UtilsManager;

/**
 * Created by koloces on 2020/5/8
 */
public class SP {
    private static SharedPreferences spf;
    private static ArrayMap<String, SharedPreferences> spfs;
    private SP() {
    }
    public static void init() {
        spf = UtilsManager.INSTANCE.getContext().getSharedPreferences("publicSP", Context.MODE_PRIVATE);
    }

    public static SharedPreferences getTagSpf(String tag) {
        if (StringUtils.INSTANCE.isEmpty(tag)) {
            return spf;
        }
        if (spfs == null) {
            spfs = new ArrayMap<>();
        }
        SharedPreferences sharedPreferences = spfs.get(tag);
        if (sharedPreferences == null) {
            sharedPreferences = UtilsManager.INSTANCE.getContext().getSharedPreferences(tag, Context.MODE_PRIVATE);
            spfs.put(tag, sharedPreferences);
        }
        return sharedPreferences;
    }

    public static void put(String key, Object obj) {
        put(null, key, obj);
    }

    /**
     * 通过 instanceof 来判断数据类型再强转成对应的数据类型进行存储
     *
     * @param key
     * @param obj
     */
    public static void put(String tag, String key, Object obj) {
        if (obj == null) {
            return;
        }
        SharedPreferences.Editor editor = getTagSpf(tag).edit();
        String simpleName = obj.getClass().getSimpleName();

        if (obj instanceof String || "String".equals(simpleName)) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Long || "Long".equals(simpleName)) {
            editor.putLong(key, (Long) obj);
        } else if (obj instanceof android.R.integer || "Integer".equals(simpleName)) {
            editor.putInt(key, (int) obj);
        } else if (obj instanceof Boolean || "Boolean".equals(simpleName)) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float || "Float".equals(simpleName)) {
            editor.putFloat(key, (Float) obj);
        }
        editor.apply();
    }

    /**
     * 通过instanceof 来判断数据类型再进行取值然后再进行类型转换，这种方法比较麻烦取出值之后在应用的地方还要进行类型转换
     *
     * @param key
     * @param obj
     * @return
     */
    public static Object get(String tag, String key, Object obj) {
        if (obj instanceof String) {
            return getTagSpf(tag).getString(key, (String) obj);
        } else if (obj instanceof String) {
            return getTagSpf(tag).getLong(key, (Long) obj);
        } else if (obj instanceof android.R.integer) {
            return getTagSpf(tag).getInt(key, (int) obj);
        } else if (obj instanceof Boolean) {
            return getTagSpf(tag).getBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            return getTagSpf(tag).getFloat(key, (Float) obj);
        }

        return null;
    }

    public static Object get(String key, Object obj) {
        return get(null, key, obj);
    }

    /**
     * 全部清除文件中的内容
     */
    public static void clearSpf(String tag) {
        SharedPreferences.Editor editor = getTagSpf(tag).edit();
        editor.clear().commit();
    }

    public static void clearSpf() {
        clearSpf(null);
    }

    /**
     * 清除指定key 的内容
     *
     * @param key
     */
    public static void remove(String tag, String key) {
        SharedPreferences.Editor editor = getTagSpf(tag).edit();
        editor.remove(key).commit();
    }

    public static void remove(String key) {
        remove(null, key);
    }


    /**
     * 获取String值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String tag, String key, String defValue) {
        return getTagSpf(tag).getString(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return getString(null, key, defValue);
    }

    public static String getString(String key) {
        return getString(null, key, "");
    }

    /**
     * 获取int值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(String tag, String key, int defValue) {
        return getTagSpf(tag).getInt(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return getInt(null, key, defValue);
    }

    /**
     * 获取long值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(String tag, String key, long defValue) {
        return getTagSpf(tag).getLong(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return getLong(null, key, defValue);
    }

    /**
     * 获取float值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(String tag, String key, float defValue) {
        return getTagSpf(tag).getFloat(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return getFloat(null, key, defValue);
    }

    /**
     * 获取bo0lean值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String tag, String key, boolean defValue) {
        return getTagSpf(tag).getBoolean(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getBoolean(null, key, defValue);
    }
}
