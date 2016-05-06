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
* @Description: TODO(���ܽ���)
* @author BEE 
* @date 2015-12-4 ����4:57:08
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
	* @Description: TODO(��str����DES����)
	* @param @param str
	* @param @return    �趨�ļ�
	* @return String    ��������
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
	* @Description:(��str����DES����)
	* @param str
	* @return    �趨�ļ�
	* @return String    ��������
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
	* @Description: TODO(���������ڴ˸����ļ���)
	* @param @param args
	* @param @throws Exception    �趨�ļ�
	* @return void    ��������
	* @throws
	 */
	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 1) {
			System.out.println("������Ҫ���ܵ��ַ����ÿո�ָ�.");
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
	* @Description: TODO(Md5�㷨)
	* @param @param pass
	* @param @return    �趨�ļ�
	* @return String    ��������
	* @throws
	 */
	public static  String Md5(String pass) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = pass.getBytes();
            // ���MD5ժҪ�㷨�� MessageDigest ����
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // ʹ��ָ�����ֽڸ���ժҪ
            mdInst.update(btInput);
            // �������
            byte[] md = mdInst.digest();
            // ������ת����ʮ�����Ƶ��ַ�����ʽ
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
