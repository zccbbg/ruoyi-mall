package com.cyl.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

public class AESForWeixinGetPhoneNumber {
  //加密方式
  private static String keyAlgorithm = "AES";
  //避免重复new生成多个BouncyCastleProvider对象，因为GC回收不了，会造成内存溢出
  //只在第一次调用decrypt()方法时才new 对象
  private static boolean initialized = false;
  //用于Base64解密
  private Base64.Decoder decoder = Base64.getDecoder();

  //待解密的数据
  private String originalContent;
  //会话密钥sessionKey
  private String encryptKey;
  //加密算法的初始向量
  private String iv;

  public AESForWeixinGetPhoneNumber(String originalContent, String encryptKey, String iv) {
    this.originalContent = originalContent;
    this.encryptKey = encryptKey;
    this.iv = iv;
  }

  /**
   * AES解密
   * 填充模式AES/CBC/PKCS7Padding
   * 解密模式128
   *
   * @return 解密后的信息对象
   */
  public JSONObject decrypt() {
    initialize();
    try {
      //数据填充方式
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
//      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
      Key sKeySpec = new SecretKeySpec(decoder.decode(this.encryptKey), keyAlgorithm);
      // 初始化
      cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(decoder.decode(this.iv)));
      byte[]data = cipher.doFinal(decoder.decode(this.originalContent));
      String datastr = new String(data, StandardCharsets.UTF_8);
      return JSON.parseObject(datastr);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**BouncyCastle作为安全提供，防止我们加密解密时候因为jdk内置的不支持改模式运行报错。**/
  private static void initialize() {
    if (initialized) {
      return;
    }
    Security.addProvider(new BouncyCastleProvider());
    initialized = true;
  }

  // 生成iv
  private static AlgorithmParameters generateIV(byte[] iv) throws NoSuchAlgorithmException, InvalidParameterSpecException {
    AlgorithmParameters params = AlgorithmParameters.getInstance(keyAlgorithm);
    params.init(new IvParameterSpec(iv));
    return params;
  }
}
