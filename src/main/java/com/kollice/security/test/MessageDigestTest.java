package com.kollice.security.test;

import org.apache.tools.ant.filters.StringInputStream;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;

/**
 * Created by 00259 on 2016/11/29.
 */
public class MessageDigestTest {
    public static void main(String args[]) {
        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("sha");
//            byte[] source = "my name is baijianye".getBytes();
//            messageDigest.update(source);
//            byte[] target = messageDigest.digest();
//            System.out.println(target.toString());



//            byte[] source = "my name is baijianye".getBytes();
//            MessageDigest messageDigest = MessageDigest.getInstance("md5");
//            DigestInputStream digestInputStream = new DigestInputStream(new ByteArrayInputStream(source),messageDigest);
//            digestInputStream.read(source,0,source.length);
//            byte[] target = digestInputStream.getMessageDigest().digest();
//            digestInputStream.close();
//            System.out.println(target);

            byte[] source = "my name is baijianye".getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            DigestOutputStream digestOutputStream = new DigestOutputStream(new ByteArrayOutputStream(),messageDigest);
            digestOutputStream.write(source, 0, source.length);
            byte[] target = digestOutputStream.getMessageDigest().digest();
            digestOutputStream.flush();
            digestOutputStream.close();
            System.out.println(target);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
