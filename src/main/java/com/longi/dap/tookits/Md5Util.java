package com.longi.dap.tookits;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

    /**
     * 拼接秘钥加密
     * @param text 明文
     * @param key 密钥
     * @return 密文
     */
    public static String md5(String text, String key) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:" + md5str);
        return md5str;
    }

    /**
     * 不带秘钥加密
     * @param text 明文
     * @return
     * @throws Exception
     */
    public static String md52(String text) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text);
        return md5str;
    }

    /**
     * MD5验证方法-根据传入的密钥进行验证
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        String md5str = md5(text, key);
        if (md5str.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }
}
