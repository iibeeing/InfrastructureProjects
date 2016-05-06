package com.infrastructure.project.common.extension;


import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import org.apache.tomcat.util.codec.binary.Base64;
//import org.apache.commons.codec.binary.Base64;

/**
* @ClassName: DESUtils
* @Description: TODO(加密解密)
* @author BEE 
* @date 2015-12-4 下午4:57:08
 */
public class DESUtils {
	private static Key key;
	private static String KEY_STR = "myKey";
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	* @Title: getEncryptString
	* @Description: TODO(对str进行DES加密)
	* @param @param str
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String encryptString(String str) {
		try {
			byte[] strBytes = str.getBytes("UTF8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return new String(Base64.encodeBase64Chunked(encryptStrBytes));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	* @Title: getDecryptString
	* @Description:(对str进行DES解密)
	* @param str
	* @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String decryptString(String str) {
		try {
			byte[] strBytes = Base64.decodeBase64(new String(str).getBytes()); 
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptStrBytes = cipher.doFinal(strBytes);
			return new String(decryptStrBytes, "UTF8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	* @Title: main
	* @Description: TODO(主函数，在此给明文加密)
	* @param @param args
	* @param @throws Exception    设定文件
	* @return void    返回类型
	* @throws
	 */
	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 1) {
			System.out.println("请输入要加密的字符，用空格分隔.");
		} else {
			for (String arg : args) {
				System.out.println(arg + ":" + encryptString(arg));
			}
		}
		//System.out.println(getDecryptString("WnplV/ietfQ="));
		//System.out.println(getDecryptString("gJQ9O+q34qk="));
	}
	
	
	/**
	* @Title: Md5
	* @Description: TODO(Md5算法)
	* @param @param pass
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static  String Md5(String pass) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = pass.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }	
}
