package com.seek.stocks.utils;


import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SignUtil {

    public static final String KEY_MD5 = "guodong";

    public static Map<Integer,String> generatorRSAKey() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥å
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        //0表示公钥
        Map<Integer,String> keyMap = new HashMap<Integer, String>();
        keyMap.put(0, publicKeyString);
        //1表示私钥
        keyMap.put(1, privateKeyString);
        System.out.println(publicKeyString);
        System.out.println(privateKeyString);

        return keyMap;
    }

    /***
     * MD5加密（生成唯一的MD5值）
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest().toString();
    }

    public static void main(String[] args) throws Exception {
        //generatorRSAKey();
        System.out.println(encryMD5("123456".getBytes()));
    }
}
