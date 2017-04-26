package team.sevendwarfs.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 系统工具类
 * Created by deng on 2017/4/26.
 */
public class Util {
    /**
     * @Description 得到 MD5 摘要值
     * @param input 输入字符串
     * @return input 的 MD5
     */
    static public String MD5(String input) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            //BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
