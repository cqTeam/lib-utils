package com.cqteam.lib.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.log10
import kotlin.math.pow

/**
 *
 * @Description:    String工具类
 * @Author:         koloces
 * @CreateDate:     2020/6/17 9:58
 */
object StringUtils {
    /**
     * 字符串是否为空（包括null或者NULL都算空）
     *
     * @param s
     * @return
     */
    fun isEmpty(s: String?): Boolean {
        if (null == s) return true
        if (s.trim().isEmpty()) return true
        if ("null" == s) return true
        return "NULL" == s
    }

    /**
     * 保留小数点后两位
     */
    fun getDoubleFormat(double: Double) : String{
        return DecimalFormat("#.###").format(double)
    }

    /**
     * 判断所输入的字符串是否存在为空的字符
     */
    fun isEmpty(vararg strs: String?): Boolean {
        for (s in strs) {
            if (isEmpty(s)) {
                return true
            }
        }
        return false
    }

    /**
     * byte kb m g 转换
     *
     * @param size 单位byte
     * @return
     */
    fun readableFileSize(size: Long): String? {
        if (size <= 0) return "0"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups =
            (log10(size.toDouble()) / log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#")
            .format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }

    /**
     * 校验Tag Alias 只能是数字,英文字母和中文
     *
     * @param s
     * @return
     */
    fun isNumEnglishOrChinese(s: String?): Boolean {
        val p =
            Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$")
        val m = p.matcher(s)
        return m.matches()
    }

    /**
     * 判断字符串是否是数字
     *
     * @return
     */
    fun isNumberic(str: String?): Boolean {
        if (isEmpty(str)) return false
        val pattern = Pattern.compile("[0-9]*")
        val isNum = pattern.matcher(str)
        return isNum.matches()
    }

    /**
     * 是否是中文
     *
     * @param c
     * @return
     */
    fun isChinese(c: Char): Boolean {
        val ub = Character.UnicodeBlock.of(c)
        return ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub === Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
    }

    /**
     * 是否是英文
     *
     * @param charaString
     * @return
     */
    fun isEnglish(charaString: String): Boolean {
        return charaString.matches(Regex("^[a-zA-Z]*"))
    }

    /**
     * 判断字符串中是否含有表情
     * @param source
     * @return
     */
    fun containsEmoji(source: String): Boolean {
        val len = source.length
        val isEmoji = false
        for (i in 0 until len) {
            val hs = source[i]
            if (hs.toInt() in 0xd800..0xdbff) {
                if (source.length > 1) {
                    val ls = source[i + 1]
                    val uc = (hs.toInt() - 0xd800) * 0x400 + (ls.toInt() - 0xdc00) + 0x10000
                    if (uc in 0x1d000..0x1f77f) {
                        return true
                    }
                }
            } else {
                // non surrogate
                if (hs.toInt() in 0x2100..0x27ff && hs.toInt() != 0x263b) {
                    return true
                } else if (hs.toInt() in 0x2B05..0x2b07) {
                    return true
                } else if (hs.toInt() in 0x2934..0x2935) {
                    return true
                } else if (hs.toInt() in 0x3297..0x3299) {
                    return true
                } else if (hs.toInt() == 0xa9 || hs.toInt() == 0xae || hs.toInt() == 0x303d || hs.toInt() == 0x3030 || hs.toInt() == 0x2b55 || hs.toInt() == 0x2b1c || hs.toInt() == 0x2b1b || hs.toInt() == 0x2b50 || hs.toInt() == 0x231a) {
                    return true
                }
                if (!isEmoji && source.length > 1 && i < source.length - 1) {
                    val ls = source[i + 1]
                    if (ls.toInt() == 0x20e3) {
                        return true
                    }
                }
            }
        }
        return isEmoji
    }

    /**
     * 判断字符是否是emoji
     * @param codePoint
     * @return
     */
    fun isEmojiCharacter(codePoint: Char): Boolean {
        return codePoint.toInt() == 0x0 ||
                codePoint.toInt() == 0x9 ||
                codePoint.toInt() == 0xA ||
                codePoint.toInt() == 0xD ||
                codePoint.toInt() in 0x20..0xD7FF ||
                codePoint.toInt() in 0xE000..0xFFFD ||
                codePoint.toInt() in 0x10000..0x10FFFF
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source
     * @return
     */
    fun filterEmoji(source: String): String? {
        if (!containsEmoji(source)) {
            return source //如果不包含，直接返回
        }
        //到这里铁定包含
        var buf: StringBuffer? = null
        val len = source.length
        for (i in 0 until len) {
            val codePoint = source[i]
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = StringBuffer()
                }
                buf.append(codePoint)
            }
        }
        return if (buf == null) {
            source //如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length == len) { //这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null
                source
            } else {
                buf.toString()
            }
        }
    }

    /**
     * 校验密码,位的数字英文组合
     * @param pass
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return
     */
    fun isPsw(pass: String, minLength: Int, maxLength: Int): Boolean {
        if (isEmpty(pass)) {
            return false
        }
        val passRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{$minLength,$maxLength}$"
        return pass.matches(Regex(passRegex))
    }

    /**
     * 获取富文本中的图片资源
     * @param htmlStr
     * @return
     */
    fun getImgsFromStr(htmlStr: String?): List<String>? {
        val list: MutableList<String> =
            ArrayList()
        var img = ""
        val p_image: Pattern
        val m_image: Matcher
        // String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        val regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>"
        p_image =
            Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE)
        m_image = p_image.matcher(htmlStr)
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group()
            // 匹配<img>中的src数据
            val m =
                Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img)
            while (m.find()) {
                list.add(m.group(1))
            }
        }
        return list
    }

    /**
     * 只保留数字
     *
     * @param str
     * @return
     */
    fun getNumber(str: String?): String? {
        if (str == null) {
            return ""
        }
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(str)
        return m.replaceAll("").trim { it <= ' ' }
    }

    /**
     * URL编码
     *
     * @param string
     * @return
     */
    fun URLEncode(string: String?): String? {
        return Uri.encode(string)
    }

    /**
     * URL解码
     *
     * @param string
     * @return
     */
    fun URLDecode(string: String?): String? {
        return if (isEmpty(string)) string else Uri.decode(string)
    }

    /**
     * @param countent 文本内容
     * @param find     查找字符
     * @return 次数
     */
    fun getStringCount(countent: String, find: String?): Int {
        var count = 0
        var per = -1 //用于保存前一次的索引位置
        for (i in countent.indices) {
            if (countent.indexOf(find!!, i) != -1) {
                if (countent.indexOf(find, i) != per) { //如果这次的找到的索引和上次不一样,那么才算一次
                    per = countent.indexOf(find, i) //记录这次找到的索引
                    count++ //次数+1;
                }
            }
            // indexOf(String str, int fromIndex)
            // 返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始。
            // 也就是说 比如 天涯天涯 中查找  天涯
            // 第一次循环 天涯天涯 包含了天涯 出现次数1次,索引位置 0;
            // 第二次循环 涯天涯 包含了天涯 出现次数2次,索引位置2;
            // 第三次循环 天涯 包含了天涯 出现次数3次,索引位置还是2;
            // 第四次循环 涯 没有查找到天涯,索引位置-1;
            // 所以一定要判断 索引位置是否相同,相同就不要重复计算次数了
        }
        return count
    }

    /**
     * 去除一段文本最前面 和 最后面的换行
     * 如:
     * 前：/n/n试试/n/n再试试/n/n
     * 后：试试/n/n再试试
     *
     * @param content
     * @return
     */
    fun substringTrimN(content: String): String? {
        var substring = content
        val length = content.length
        for (i in 0 until length) {
            val item = content.substring(length - i - 1, length - i)
            substring = if (item == "\n") {
                content.substring(0, length - i - 1)
            } else {
                break
            }
        }
        for (i in 0 until substring.length) {
            val item2 = substring.substring(0, 1)
            substring = if (item2 == "\n") {
                substring.substring(1)
            } else {
                break
            }
        }
        return substring
    }

    /**
     * 是不是超链接
     *
     * @return
     */
    fun isLink(url: String?): Boolean {
        val regEx =
            "(https?://|ftp://|file://|www)[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]" // url
        val pattern = Pattern.compile(regEx)
        val matcher = pattern.matcher(url)
        return matcher.matches()
    }

    /**
     * 手机号码隐藏中间四位
     *
     * @param phone
     * @return
     */
    fun getSafePhone(phone: String): String? {
        return if (isEmpty(phone)) {
            ""
        } else phone.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
    }

    /**
     * 银行卡号隐藏中间八位
     *
     * @param bankCard
     * @return
     */
    fun getSafeCardNum(bankCard: String): String? {
        if (isEmpty(bankCard)) return ""
        val hideLength = 8 //替换位数
        val sIndex = bankCard.length / 2 - hideLength / 2
        val replaceSymbol = "*"
        val sBuilder = StringBuilder()
        for (i in bankCard.indices) {
            val number = bankCard[i]
            if (i >= sIndex - 1 && i < sIndex + hideLength) {
                sBuilder.append(replaceSymbol)
            } else {
                sBuilder.append(number)
            }
        }
        return sBuilder.toString()
    }

    /**
     * 字符串转Base64
     *
     * @param str
     * @return
     */
    fun encodeToString(str: String): String? {
        return Base64.encodeToString(str.toByteArray(), Base64.DEFAULT)
    }

    /**
     * 通过文件Uri地址获取文件真实路径
     */
    fun getRealUriPath(context: Context, fileUrl: Uri?): String? {
        var fileName: String? = null
        if (fileUrl != null) {
            if (fileUrl.scheme.toString().compareTo("content") == 0) // content://开头的uri
            {
                val cursor =
                    context.contentResolver.query(fileUrl, null, null, null, null)
                if (cursor != null && cursor.moveToFirst()) {
                    try {
                        val column_index =
                            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                        fileName = cursor.getString(column_index) // 取出文件路径
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
            } else if (fileUrl.scheme!!.compareTo("file") == 0) // file:///开头的uri
            {
                fileName = fileUrl.path
            }
        }
        return fileName
    }
}