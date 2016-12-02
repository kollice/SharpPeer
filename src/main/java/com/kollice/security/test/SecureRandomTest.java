package com.kollice.security.test;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by 00259 on 2016/11/29.
 */
public class SecureRandomTest {
    public static void main(String[] args) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(secureRandom);
            Key key = keyGenerator.generateKey();
            byte[] result = key.getEncoded();
            System.out.println(result.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
