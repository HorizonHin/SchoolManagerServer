package com.schoolmanagerserver.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    // 获取字符串的MD5值
    public static String getMD5(String input) {
        try {
            // 获取MD5 MessageDigest对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 更新输入数据
            md.update(input.getBytes());

            // 获取计算后的字节数组
            byte[] digest = md.digest();

            // 转换成16进制的字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                // 以16进制方式输出字节
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append("0");  // 确保每个字节两位数
                }
                hexString.append(hex);
            }
            return hexString.toString();  // 返回MD5值
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm exception", e);
        }
    }

}
